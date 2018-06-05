/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.web;


import com.gestor.entity.Dialogo;
import com.gestor.entity.UtilBinario;
import com.gestor.entity.UtilJSF;
import com.gestor.entity.UtilLog;
import com.gestor.modelo.Sesion;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiSesion")
@SessionScoped

public class UISesion {

//    RsaCliente seguridadRest = new RsaCliente("http://localhost:8080/Seguridad-rest/api");
//    RsaCliente seguridadRest = new RsaCliente(RestURI.SEGURIDAD_REST);
////    RsaCliente usuarioRest = new RsaCliente("http://localhost:8080/Usuario-rest/api");
//    RsaCliente usuarioRest = new RsaCliente(RestURI.USUARIO_REST);
////    RsaCliente configuracionRest = new RsaCliente("http://localhost:8080/Configuracion-rest/api");
//    RsaCliente configuracionRest = new RsaCliente(RestURI.CONFIGURACION_REST);

    public String cerrarSesion() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ((HttpSession) externalContext.getSession(true)).invalidate();
        return ("/loginOut.xhtml?faces-redirect=true");
    }

    public void limpiarDialogo() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo());
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
    }

//    public String cargarUsuario() {
//        Sesion sesion = new Sesion();
//        String valores = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("val");
//
//        if (valores == null || valores.equalsIgnoreCase("") || valores.equals("undefined")) {
//            System.out.println("Por favor realizar el ingreso por SIMA-WEB.");
//            return "";
//        }
//        try {
//            HashMap<String, String> param = GestorCadena.dividirParametros(valores);
//            System.out.println(param.get("empresa") + "|" + param.get("usuario") + "|" + param.get("clave") + "|" + param.get("permisos") + "|" + param.get("bd") + "|" + param.get("port") + "|" + param.get("ip"));
//
//            String login = seguridadRest.consultarGetWS("/seguridadRest/get/validarUsuario/" + param.get("usuario") + "/" + param.get("clave"));
//            if (login != null && login.equalsIgnoreCase("true")) {
//
//                String funcionarioJson = usuarioRest.consultarGetWS("/usuarioRest/get/cargarUsuario/" + TFuncionarios.class.getSimpleName() + "/" + param.get("usuario").toUpperCase());
//                TFuncionarios funcionario = UtilRest.fromJson(funcionarioJson, TFuncionarios.class);
//                if (funcionario == null) {
//                    System.out.println("no se pudo cargar la información del usuario");
//                    return null;
//                }
//                sesion.setUsuarios(new Usuarios(funcionario.getCodFuncionario(), param.get("usuario"), param.get("clave"), funcionario.getUsado(), new Short("0"), null, 0));
//                sesion.getUsuarios().setNombre(funcionario.getNombre());
//                sesion.getUsuarios().setApellido(funcionario.getApellido());
//
//                String respuesta = configuracionRest.consultarGetWS("/configuracionRest/get/cargarConfiguracion/" + TConfGeneral.class.getSimpleName() + "/" + param.get("empresa") + "/" + App.NOMBRE_APP);
//
//                Collection<TConfGeneral> confGeneralList = new Gson().fromJson(UtilRest.toJsonArray(respuesta, new TConfGeneral()).replace("[[", "[").replace("]]", "]"), new TypeToken<List<TConfGeneral>>() {
//                }.getType());
//                for (TConfGeneral c : confGeneralList) {
//                    sesion.getParametros().put(c.getParametro(), c.getValor());
//                }
//                
//                if (sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_DISPENSACION_REST) != null
//                        && !sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_DISPENSACION_REST).equals("")) {
//                    RestURI.DISPENSACION_REST = sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_DISPENSACION_REST).toString();
//                }
//                if (sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_SEGURIDAD_REST) != null
//                        && !sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_SEGURIDAD_REST).equals("")) {
//                    RestURI.SEGURIDAD_REST = sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_SEGURIDAD_REST).toString();
//                }
//                if (sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_USUARIO_REST) != null
//                        && !sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_USUARIO_REST).equals("")) {
//                    RestURI.USUARIO_REST = sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_USUARIO_REST).toString();
//                }
//                if (sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_CONFIGURACION_REST) != null
//                        && !sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_CONFIGURACION_REST).equals("")) {
//                    RestURI.CONFIGURACION_REST = sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_CONFIGURACION_REST).toString();
//                }
//                if (sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_AGENDA_REST) != null
//                        && !sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_AGENDA_REST).equals("")) {
//                    RestURI.AGENDA_REST = sesion.getParametros().get(TConfGeneral.AGENDA_PACIENTES_AGENDA_REST).toString();
//                }
//
//                //permisos
//                HashMap<Integer, Boolean> permisos = new HashMap<>();
//                int permiso = Integer.parseInt(param.get("permisos"));
//                permisos.put(UtilBinario.PERMISO_EJECUTAR, UtilBinario.permisoBinario(UtilBinario.PERMISO_EJECUTAR, permiso));
//                permisos.put(UtilBinario.PERMISO_CREAR, UtilBinario.permisoBinario(UtilBinario.PERMISO_CREAR, permiso));
//                permisos.put(UtilBinario.PERMISO_MODIFICAR, UtilBinario.permisoBinario(UtilBinario.PERMISO_MODIFICAR, permiso));
//                permisos.put(UtilBinario.PERMISO_ELIMINAR, UtilBinario.permisoBinario(UtilBinario.PERMISO_ELIMINAR, permiso));
//                sesion.setPermisos(permisos);
//
//                sesion.setLogueado(true);
//                sesion.setEmpresas(new Empresas(Integer.parseInt(param.get("empresa"))));
//                UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
//                return ("/inicio/principal.xhtml?faces-redirect=true");
//            }
//        } catch (Exception e) {
//            UtilLog.generarLog(this.getClass(), e);
//        }
//        return null;
//    }

    /**
     * @return the guardarActivo
     */
    public boolean isGuardarActivo() {
        HashMap<Integer, Boolean> s = ((Sesion) UtilJSF.getBean("sesion")).getPermisos();
        return (s.get(UtilBinario.PERMISO_CREAR) || s.get(UtilBinario.PERMISO_MODIFICAR));
    }

    /**
     * @return the nuevoActivo
     */
    public boolean isNuevoActivo() {
        return ((Sesion) UtilJSF.getBean("sesion")).getPermisos().get(UtilBinario.PERMISO_CREAR);
    }

    /**
     * @return the eliminarActivo
     */
    public boolean isEliminarActivo() {
        return ((Sesion) UtilJSF.getBean("sesion")).getPermisos().get(UtilBinario.PERMISO_ELIMINAR);
    }

    /**
     * @return the consultarActivo
     */
    public boolean isConsultarActivo() {
        HashMap<Integer, Boolean> s = ((Sesion) UtilJSF.getBean("sesion")).getPermisos();
        return (s.get(UtilBinario.PERMISO_CREAR) || s.get(UtilBinario.PERMISO_MODIFICAR) || s.get(UtilBinario.PERMISO_ELIMINAR));
    }
}
