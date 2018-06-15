/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.publico;

import com.gestor.controller.GestorGeneral;
import com.gestor.entity.UtilJSF;
import com.gestor.entity.UtilLog;
import com.gestor.entity.UtilMSG;
import com.gestor.publico.controlador.GestorEstablecimiento;
import com.gestor.publico.controlador.GestorMunicipios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiEstablecimiento")
@SessionScoped

public class UIEstablecimiento implements Serializable {

//    @ManagedProperty("#{gestorEstablecimiento}")
    private GestorEstablecimiento gestorEstablecimiento;
//    @ManagedProperty("#{gestorGeneral}")
    private GestorGeneral gestorGeneral;
//    @ManagedProperty("#{gestorMunicipios}")
    private GestorMunicipios gestorMunicipios;
//    @ManagedProperty("#{gestorFacturas}")

    private Establecimiento establecimiento = new Establecimiento();

//    private Servicios servicios = new Servicios();
//    private List<Servicios> serviciosList = new ArrayList<>();
    private List<Establecimiento> establecimientoList = new ArrayList<>();

//    private DualListModel<TipoRecurso> tipoRecursoDualList = new DualListModel<>();
//    private DualListModel<Seccion> seccionDualList = new DualListModel<>();
    private List<Municipios> municipiosList = new ArrayList<>();
//    private List<Institucion> institucionList = new ArrayList<>();
//    private List<Parametros> parametrosList;
//    private Parametros parametrosNuevo = new Parametros();

    @PostConstruct
    public void init() {
//        this.cargarEstablecimientosInstitucion();
        this.cargarMunicipios();
//        this.cargarInstitucionesUsuario();
//        this.cargarParametros();
    }

    public void subirItemEstablecimiento() {
        establecimiento = (Establecimiento) UtilJSF.getBean("varEstablecimiento");
        establecimientoList.remove(establecimiento);
    }

  

    public void guardarEstablecimiento() {
        try {

            Establecimiento establecimiento = (Establecimiento) UtilJSF.getBean("establecimiento");
            GestorEstablecimiento gestorEstablecimiento = new GestorEstablecimiento();
            GestorGeneral gestorGeneral = new GestorGeneral();

            gestorEstablecimiento.validarEstablecimiento(establecimiento);
            establecimiento.setCodigoEstablecimiento(gestorGeneral.siguienteCodigoEntidad("codigo_establecimiento", "establecimiento"));
            gestorEstablecimiento.almacenarEstablecimiento(establecimiento);

            UtilMSG.addSuccessMsg("Establecimiento Almacenado, se validará la información y se te notificaran los usuarios al correo.");
            UtilJSF.setBean("establecimiento", new Establecimiento(), UtilJSF.SESSION_SCOPE);

        } catch (Exception e) {
            if (UtilLog.causaControlada(e)) {
                UtilMSG.addWarningMsg(e.getMessage());
            } else {
                UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), e);
            }
        }

    }

//    public void guardarTipoRecursoServiciosCantidad() {
//        for (TipoRecurso tr : servicios.getTipoRecursoSet()) {
//            try {
//                if (tr != null && tr.getTipoRecursoServiciosCantidad() != null) {
//                    gestorTipoRecurso.guardarActualizarEntidad(tr.getTipoRecursoServiciosCantidad());
//                }
//            } catch (Exception ex) {
//                if (UtilLog.causaControlada(ex)) {
//                    UtilMSG.addErrorMsg(ex.getMessage(), ex.getCause().getMessage());
//                } else {
//                    UtilMSG.addSupportMsg();
//                    UtilLog.generarLog(this.getClass(), ex);
//                }
//            }
//        }
//    }
//    public void cargarSeccionesServicio() {
//        try {
//            seccionDualList = new DualListModel<>();
//            List<SeccionServicios> seccionServiciosList = new ArrayList<>();
//            if (servicios.getSeccionServiciosSet() != null && Hibernate.isInitialized(servicios.getSeccionServiciosSet())) {
////            for (SeccionServicios ss : servicios.getSeccionServiciosSet()) {
////                seccionAsignadosList.add(ss);
////            }
//                seccionServiciosList.addAll(servicios.getSeccionServiciosSet());
//            }
//
//            List<Seccion> seccionAsignadosList = new ArrayList<>();
//            List<Seccion> seccionDisponiblesList = new ArrayList<>();
//            seccionDisponiblesList.addAll(gestorSeccion.cargarSeccionEstablecimiento(establecimiento, true));
//
//            for (Seccion s : seccionDisponiblesList) {
//                for (SeccionServicios ss : seccionServiciosList) {
//                    if (s.getSeccionPK().getCodSeccion().equals(ss.getSeccionServiciosPK().getCodSeccion())) {
//                        seccionAsignadosList.add(s);
//                        break;
//                    }
//                }
//            }
//            seccionDisponiblesList.removeAll(seccionAsignadosList);
//            seccionDualList = new DualListModel<>(seccionDisponiblesList, seccionAsignadosList);
//
//        } catch (Exception ex) {
//            Logger.getLogger(UIEstablecimiento.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//    public void cargarTipoRecursosServicio() {
//        try {
//            tipoRecursoDualList = new DualListModel<>();
//            List<TipoRecurso> tipoRecursoAsignadosList = new ArrayList<>();
//            if (servicios.getTipoRecursoSetToList() != null) {
//                tipoRecursoAsignadosList.addAll(servicios.getTipoRecursoSetToList());
//            }
//
//            List<TipoRecurso> tipoRecursoDisponiblesList = new ArrayList<>();
//            tipoRecursoDisponiblesList.addAll(gestorTipoRecurso.cargarTipoRecursoInstitucion(establecimiento.getEstablecimientoPK().getCodInstitucion()));
//
//            tipoRecursoDisponiblesList.removeAll(tipoRecursoAsignadosList);
//            tipoRecursoDualList = new DualListModel<>(tipoRecursoDisponiblesList, tipoRecursoAsignadosList);
//
//            for (TipoRecurso tipoRecurso : servicios.getTipoRecursoSet()) {
//                TipoRecursoServiciosCantidad trsc = new TipoRecursoServiciosCantidad();
//                trsc = gestorTipoRecurso.cargarTipoRecursoServiciosCantidad(servicios, tipoRecurso);
//                tipoRecurso.setTipoRecursoServiciosCantidad(trsc);
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(UIEstablecimiento.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//    public void cargarServiciosEstablecimiento() {
//        try {
//            serviciosList = new ArrayList<>();
//            serviciosList.addAll(gestorServicios.cargarServiciosEstablecimiento(establecimiento));
//        } catch (Exception ex) {
//            UtilLog.generarLog(this.getClass(), ex);
//        }
//    }
//    private void cargarParametros() {
//        this.parametrosList = new ArrayList<>();
//        this.parametrosList.addAll(gestorGeneral.cargarParametros());
//    }
//    public void eliminarParametro(ActionListener actionListener) {
//        try {
//            EstablecimientoParametros establecimientoParametros = (EstablecimientoParametros) UtilJSF.getBean("varParametroEstablecimiento");
//            establecimientoParametros.setValor("N");
//
//            establecimiento.getEstablecimientoParametrosSet().add(establecimientoParametros);
//        } catch (Exception ex) {
//            UtilLog.generarLog(this.getClass(), ex);
//        }
//
//    }
    public void limpiar() {
//        this.cargarEstablecimientosInstitucion();
        this.cargarMunicipios();
//        this.cargarInstitucionesUsuario();
//        this.cargarParametros();
        this.establecimiento = new Establecimiento();
    }

//    public void asignarResolucion() {
//        FacturasConfiguracion facturasConfiguracion = establecimiento.getFacturasConfiguracion();
//        try {
//            facturasConfiguracion = gestorFacturas.validarFacturasConfiguracion(establecimiento, facturasConfiguracion);
//            if (facturasConfiguracion.getFacturasConfiguracionPK() == null
//                    || facturasConfiguracion.getFacturasConfiguracionPK().getCodConfiguracion() == 0) {
//                facturasConfiguracion.setFacturasConfiguracionPK(new FacturasConfiguracionPK(establecimiento.getEstablecimientoPK().getCodInstitucion(), establecimiento.getEstablecimientoPK().getCodEstablecimiento(), gestorGeneral.nextval(GestorGeneral.FACTURACION_FACTURAS_CONFIGURACION_COD_CONFIGURACION_SEQ)));
//                facturasConfiguracion.setNumeroFactura(0);
//            }
//            gestorFacturas.validarFacturasConfiguracionPK(facturasConfiguracion.getFacturasConfiguracionPK());
//            FacturasConfiguracion facturasConfiguracionRemover = null;
//            for (FacturasConfiguracion fc : establecimiento.getFacturasConfiguracionSet()) {
//                if (fc.equals(facturasConfiguracion)) {
//                    facturasConfiguracionRemover = fc;
//                    break;
//                }
//            }
//            if (facturasConfiguracionRemover != null) {
//                establecimiento.getFacturasConfiguracionSet().remove(facturasConfiguracionRemover);
//            }
//            establecimiento.getFacturasConfiguracionSet().add(facturasConfiguracion);
//            UtilMSG.addSuccessMsg("Resolución Almacenada Satisfactoriamente", "Registro Guardado, recuerda que se debe modificar el establecimiento para almacenar la configuración");
//            UtilJSF.execute("PF('dialog').hide()");
//        } catch (Exception ex) {
//            if (UtilLog.causaControlada(ex)) {
//                UtilMSG.addWarningMsg(ex.getMessage(), ex.getCause().getMessage());
//            } else {
//                UtilLog.generarLog(this.getClass(), ex);
//                UtilMSG.addSupportMsg();
//            }
//        }
//    }
//    public void asignarParametro() {
//        try {
//            EstablecimientoParametros establecimientoParametros = establecimiento.getEstablecimientoParametros();
//            establecimientoParametros.setEstablecimientoParametrosPK(new EstablecimientoParametrosPK(establecimiento.getEstablecimientoPK().getCodInstitucion(), establecimiento.getEstablecimientoPK().getCodEstablecimiento(), establecimientoParametros.getParametros().getCodParametro()));
//            establecimientoParametros = gestorEstablecimiento.validarParametro(establecimientoParametros);
////            gestorEstablecimiento.guardarEstablecimientoParametros(establecimientoParametros);
//
//            EstablecimientoParametros establecimientoParametrosBorrar = null;
//            for (EstablecimientoParametros ep : establecimiento.getEstablecimientoParametrosSet()) {
//                if (establecimientoParametros.equals(ep)) {
//                    establecimientoParametrosBorrar = ep;
//                }
//            }
//            
//            if (establecimientoParametros != null) {
//                establecimiento.getEstablecimientoParametrosSet().remove(establecimientoParametrosBorrar);
//            }
//            establecimiento.getEstablecimientoParametrosSet().add(establecimientoParametros);
//            establecimiento.setEstablecimientoParametros(new EstablecimientoParametros());
//            UtilJSF.execute("PF('dialog').hide()");
//        } catch (Exception ex) {
//            if (UtilLog.causaControlada(ex)) {
//                UtilMSG.addWarningMsg(ex.getMessage(), ex.getCause().getMessage());
//            } else {
//                UtilLog.generarLog(this.getClass(), ex);
//                UtilMSG.addSupportMsg();
//            }
//        }
//    }
//    public void cargarDialogoCrearServicio() {
//        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
//        establecimiento.setServicios(new Servicios());
//        sesion.setDialogo(new Dialogo("dialogos/servicio.xhtml", "Nuevo Servicio Establecimiento", Dialogo.EFECTO));
//        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
//        UtilJSF.execute("PF('dialog').show();");
//    }
//    public void cargarDialogoCrearResolucion() {
//        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
//        sesion.setDialogo(new Dialogo("dialogos/parametroResolucion.xhtml", "Nuevo Parametro Establecimiento", Dialogo.EFECTO));
//        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
//        UtilJSF.execute("PF('dialog').show();");
//    }
//    public void cargarDialogoModificarResolucion() {
//        FacturasConfiguracion facturasConfiguracion = (FacturasConfiguracion) UtilJSF.getBean("varPrefijosFacturacion");
//        establecimiento.setFacturasConfiguracion(facturasConfiguracion);
//        this.cargarDialogoCrearResolucion();
//    }
//    public void cargarDialogoCrearParametro() {
//        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
//        sesion.setDialogo(new Dialogo("dialogos/parametroEstablecimiento.xhtml", "Nuevo Resolución Establecimiento", Dialogo.EFECTO));
//        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
//        UtilJSF.execute("PF('dialog').show();");
//    }
//    private void cargarInstitucionesUsuario() {
//        Usuarios usuarios = ((Sesion) UtilJSF.getBean("sesion")).getUsuario();
//        this.institucionList = new ArrayList<>();
//        this.institucionList.addAll(usuarios.getInstitucionSet());
//    }
    private void cargarMunicipios() {
        try {
            this.getMunicipiosList().addAll(gestorMunicipios.cargarMunicipios());
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    /**
     * @return the establecimiento
     */
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    /**
     * @return the establecimientoList
     */
    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    /**
     * @param establecimientoList the establecimientoList to set
     */
    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }

    /**
     * @return the gestorEstablecimiento
     */
    public GestorEstablecimiento getGestorEstablecimiento() {
        return gestorEstablecimiento;
    }

    /**
     * @param gestorEstablecimiento the gestorEstablecimiento to set
     */
    public void setGestorEstablecimiento(GestorEstablecimiento gestorEstablecimiento) {
        this.gestorEstablecimiento = gestorEstablecimiento;
    }

    /**
     * @return the gestorGeneral
     */
    public GestorGeneral getGestorGeneral() {
        return gestorGeneral;
    }

    /**
     * @param gestorGeneral the gestorGeneral to set
     */
    public void setGestorGeneral(GestorGeneral gestorGeneral) {
        this.gestorGeneral = gestorGeneral;
    }

    /**
     * @return the gestorMunicipios
     */
    public GestorMunicipios getGestorMunicipios() {
        return gestorMunicipios;
    }

    /**
     * @param gestorMunicipios the gestorMunicipios to set
     */
    public void setGestorMunicipios(GestorMunicipios gestorMunicipios) {
        this.gestorMunicipios = gestorMunicipios;
    }

    /**
     * @return the municipiosList
     */
    public List<Municipios> getMunicipiosList() {
        return municipiosList;
    }

    /**
     * @param municipiosList the municipiosList to set
     */
    public void setMunicipiosList(List<Municipios> municipiosList) {
        this.municipiosList = municipiosList;
    }

}
