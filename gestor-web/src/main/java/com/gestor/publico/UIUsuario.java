/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.publico;

import com.gestor.controller.Propiedades;
import com.gestor.entity.App;
import com.gestor.entity.UtilJSF;
import com.gestor.entity.UtilLog;
import com.gestor.entity.UtilMSG;
import com.gestor.modelo.Sesion;
import com.gestor.publico.controlador.GestorEstablecimiento;
import com.gestor.publico.controlador.GestorUsuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Julian
 */
@ManagedBean(name = "uiUsuario")
@SessionScoped

public class UIUsuario {

    public static final String DIALOGO_CREAR = "dialogoNuevoUsuario";
    public static final String COMPONENTES_REFRESCAR = "formNuevoUsuario";
    private List<Roles> itemsRoles;
    private List<Establecimiento> itemsEstablecimiento;
    private List<String> establecimientosDisponibles = new ArrayList<>();
    private List<String> establecimientosAsignados = new ArrayList<>();
    private DualListModel<String> itemsDualEstablecimientos = new DualListModel<>(establecimientosDisponibles, establecimientosAsignados);
    private String usuarioBuscar;

    public UIUsuario() {
        this.itemsEstablecimiento = new ArrayList<>();
        this.itemsRoles = new ArrayList<>();
        this.cargarRoles();
    }

    public String getDialogoCrearNuevo() {
        return DIALOGO_CREAR;
    }

    public String getComponentesRefrescar() {
        return COMPONENTES_REFRESCAR;
    }

    public String registrarEstablecimiento() {
        return ("/registro.xhtml?faces-redirect=true");
    }

    /**
     * Cargar los datos del usuario.
     *
     * @param establecimiento
     * @param usuario
     *
     */
    private Usuarios cargarDatosUsuario(Establecimiento establecimiento, Usuarios usuario) throws Exception {
        GestorUsuario gestorUsuario = new GestorUsuario();
        return gestorUsuario.cargarDatosUsuario(establecimiento, usuario, App.USUARIOS_FILTRO_USUARIO);
    }

    public void cargarEstablecimientosUsuario(String usuario) {
        try {
            GestorEstablecimiento gestorEstablecimiento = new GestorEstablecimiento();
            GestorUsuario gestorUsuario = new GestorUsuario();
            Usuarios u = (Usuarios) new Usuarios().clone();
            u.setUsuario(usuario);
            u = gestorUsuario.cargarDatosUsuario(u, App.USUARIOS_FILTRO_USUARIO);
            this.itemsEstablecimiento = (List<Establecimiento>) gestorEstablecimiento.cargarListaEstablecimientosUsuario(u.getUsuariosPK().getDocumentoUsuario());
        } catch (Exception ex) {
            UtilMSG.addErrorMsg(ex.getMessage());
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public String validarUsuario() {
        Sesion sesion = new Sesion();
        Usuarios usuario = (Usuarios) UtilJSF.getBean("usuario");

        Establecimiento establecimiento;
        boolean usuarioValido;
        try {
            GestorUsuario gestorUsuario = new GestorUsuario();
            usuarioValido = gestorUsuario.validarUsuario(usuario.getUsuario(), usuario.getClave());
            if (usuarioValido) {
                establecimiento = this.cargarEstablecimiento(usuario.getEstablecimiento().getCodigoEstablecimiento());
                usuario = this.cargarDatosUsuario(establecimiento, usuario);

//                if (establecimiento.getPrefijo() == null || establecimiento.getPrefijo().equalsIgnoreCase("")) {
//                    throw new Exception("No se pudieron cargar los prefijos.", UtilLog.TW_VALIDACION);
//                }
                Properties propiedades = Propiedades.getInstancia().getPropiedades();
//                Sesion.RUTA_SERVICIO = propiedades.getProperty("urlServicio");
                sesion.setUsuarios((Usuarios) usuario.clone());
                sesion.setEstablecimiento(establecimiento);
                usuario = new Usuarios();
                UtilJSF.setBean("usuario", usuario, UtilJSF.SESSION_SCOPE);
                UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
                return ("/inicio/principal.xhtml?faces-redirect=true");
            } else {
                UtilMSG.addWarningMsg("Usuario o clave invalida.");
            }
        } catch (Exception e) {
            if (UtilLog.causaControlada(e)) {
                UtilMSG.addWarningMsg(e.getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), e);
                UtilMSG.addErrorMsg("errorPersistencia");
            }
        }
        return "";
    }

    public List<String> autoCompletaUsuario(String query) {
        List<String> resultado = new ArrayList<>();
        try {
            Establecimiento establecimiento = (Establecimiento) ((Sesion) UtilJSF.getBean("sesion")).getEstablecimiento();
            GestorUsuario gestorUsuario = new GestorUsuario();
            resultado.addAll(gestorUsuario.autoCompletaUsuario(establecimiento, query));

        } catch (Exception ex) {
            UtilMSG.addErrorMsg("Error al consultar el usuario");
            UtilLog.generarLog(this.getClass(), ex);
        }
        return resultado;
    }

    public void cargarUsuario() {

        try {
            Usuarios usuario = (Usuarios) UtilJSF.getBean("usuario");
            Establecimiento establecimiento = (Establecimiento) ((Sesion) UtilJSF.getBean("sesion")).getEstablecimiento();
            usuario.setUsuario(usuarioBuscar);
            usuario = this.cargarDatosUsuario(establecimiento, usuario);
            for (Roles r : itemsRoles) {
                if (usuario.getRoles() != null && usuario.getRoles().getCodigoRol() == r.getCodigoRol()) {
                    usuario.setRoles(r);
                }
            }
            GestorEstablecimiento gestorEstablecimiento = new GestorEstablecimiento();

            this.establecimientosDisponibles = this.transformarLista(gestorEstablecimiento.cargarListaEstablecimientos());
            this.establecimientosAsignados = this.transformarLista(gestorEstablecimiento.cargarListaEstablecimientosUsuario(usuario.getUsuariosPK().getDocumentoUsuario()));
            this.itemsDualEstablecimientos = new DualListModel<>((List<String>) this.removerElementosAsignados(establecimientosDisponibles, establecimientosAsignados), establecimientosAsignados);

            UtilJSF.setBean("usuario", usuario, UtilJSF.SESSION_SCOPE);
        } catch (Exception ex) {
            UtilMSG.addErrorMsg("Error al cargar el usuario");
            UtilLog.generarLog(this.getClass(), ex);
        }

    }

    public List<String> transformarLista(final List<?> objects) {
        List<String> lista = new ArrayList<>();
        for (Object ob : objects) {
            Establecimiento e = (Establecimiento) ob;
            lista.add(e.getNombre());
        }
        return lista;
    }

    public List<?> removerElementosAsignados(List<?> disponibles, List<?> asignados) {
        CopyOnWriteArrayList origen = new CopyOnWriteArrayList(disponibles);
        CopyOnWriteArrayList destino = new CopyOnWriteArrayList(asignados);
        for (Object obj1 : origen) {
            for (Object obj2 : destino) {
                if (obj1.equals(obj2)) {
                    origen.remove(obj2);
                }
            }
        }
        return new ArrayList(origen);
    }

    public String cerrarSesion() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ((HttpSession) externalContext.getSession(true)).invalidate();
        return ("/ingreso.xhtml?faces-redirect=true");
    }

    private Establecimiento cargarEstablecimiento(int codigo) throws Exception {
        Establecimiento establecimiento;
        GestorEstablecimiento gestorEstablecimiento = new GestorEstablecimiento();
        establecimiento = gestorEstablecimiento.cargarEstablecimiento(codigo);
        return establecimiento;
    }

    private List<Establecimiento> cargarEstablecimientosAsignados() throws Exception {
        GestorEstablecimiento gestorEstablecimiento = new GestorEstablecimiento();
        List<Establecimiento> listaEstablecimientosAsignados = new ArrayList<>();
        List<Establecimiento> listaEstablecimientos = (List<Establecimiento>) gestorEstablecimiento.cargarListaEstablecimientos();
        List<String> asignados = this.itemsDualEstablecimientos.getTarget();

        for (Establecimiento obe : listaEstablecimientos) {
            for (String e : asignados) {
                if (obe.getNombre().equalsIgnoreCase(e)) {
                    listaEstablecimientosAsignados.add(obe);
                }
            }
        }
        return listaEstablecimientosAsignados;
    }

    public void nuevo() {
        Usuarios usuario = (Usuarios) UtilJSF.getBean("usuario");
        Establecimiento establecimiento = (Establecimiento) ((Sesion) UtilJSF.getBean("sesion")).getEstablecimiento();
        try {
            GestorUsuario gestorUsuario = new GestorUsuario();
            if (gestorUsuario.existeDocumentoUsuario(usuario)) {
                throw new Exception("El documento de identificación ya existe por favor valide.", UtilLog.TW_VALIDACION);
            }
            gestorUsuario.almacenarUsuario(establecimiento, usuario);
            usuario = new Usuarios();
            this.usuarioBuscar = null;
            UtilJSF.setBean("usuario", usuario, UtilJSF.SESSION_SCOPE);
            UtilMSG.addSuccessMsg("Usuario creado");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage());
            } else {
                UtilMSG.addErrorMsg("Ocurrio una excepción al crear el usuario");
                UtilLog.generarLog(this.getClass(), ex);
            }
        }
    }

    public void guardar() {
        Usuarios usuario = (Usuarios) UtilJSF.getBean("usuario");
        Establecimiento establecimiento = (Establecimiento) ((Sesion) UtilJSF.getBean("sesion")).getEstablecimiento();
        try {
            if (usuario.getRoles() == null || usuario.getRoles().getCodigoRol() == 0) {
                UtilMSG.addWarningMsg("Por favor seleccione el rol del usuario, contacte al adminsitrador del sistema.");
                return;
            }

            GestorUsuario gestorUsuario = new GestorUsuario();
            usuario.setListaEstablecimientos(this.cargarEstablecimientosAsignados());
            gestorUsuario.almacenarUsuario(establecimiento, usuario);
            gestorUsuario.almacenarEstablecimientosUsuario(usuario);
            gestorUsuario.almacenarRolUsuario(establecimiento, usuario);

            usuario = new Usuarios();
            this.usuarioBuscar = null;
            UtilJSF.setBean("usuario", usuario, UtilJSF.SESSION_SCOPE);
            UtilMSG.addSuccessMsg("Usuario modificado correctamente");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage());
            } else {
                UtilMSG.addErrorMsg("Ocurrio una excepción al modificar usuario");
                UtilLog.generarLog(this.getClass(), ex);
            }
        }
    }

    public void eliminar() {
        UtilMSG.addWarningMsg("No se permite eliminar usuario, por favor inactivelo.");
    }

    /**
     * @return the itemsRoles
     */
    public List<Roles> getItemsRoles() {
        return itemsRoles;
    }

    /**
     * @param itemsRoles the itemsRoles to set
     */
    public void setItemsRoles(List<Roles> itemsRoles) {
        this.itemsRoles = itemsRoles;
    }

    private void cargarRoles() {
        try {
            GestorUsuario gestorUsuario = new GestorUsuario();
            this.itemsRoles = gestorUsuario.cargarRoles();
        } catch (Exception ex) {

        }
    }

    /**
     * @return the itemsDualEstablecimientos
     */
    public DualListModel<String> getItemsDualEstablecimientos() {
        return itemsDualEstablecimientos;
    }

    /**
     * @param itemsDualEstablecimientos the itemsDualEstablecimientos to set
     */
    public void setItemsDualEstablecimientos(DualListModel<String> itemsDualEstablecimientos) {
        this.itemsDualEstablecimientos = itemsDualEstablecimientos;
    }

    /**
     * @return the usuarioBuscar
     */
    public String getUsuarioBuscar() {
        return usuarioBuscar;
    }

    /**
     * @param usuarioBuscar the usuarioBuscar to set
     */
    public void setUsuarioBuscar(String usuarioBuscar) {
        this.usuarioBuscar = usuarioBuscar;
    }

    /**
     * @return the itemsEstablecimiento
     */
    public List<Establecimiento> getItemsEstablecimiento() {
        return itemsEstablecimiento;
    }

    /**
     * @param itemsEstablecimiento the itemsEstablecimiento to set
     */
    public void setItemsEstablecimiento(List<Establecimiento> itemsEstablecimiento) {
        this.itemsEstablecimiento = itemsEstablecimiento;
    }

}
