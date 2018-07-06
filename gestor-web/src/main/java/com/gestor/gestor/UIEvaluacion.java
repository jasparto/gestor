/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.gestor;

import com.gestor.controller.GestorGeneral;
import com.gestor.entity.App;
import com.gestor.entity.Dialogo;
import com.gestor.entity.UtilJSF;
import com.gestor.entity.UtilLog;
import com.gestor.entity.UtilMSG;
import com.gestor.gestor.controlador.GestorCiclo;
import com.gestor.gestor.controlador.GestorEvaluacion;
import com.gestor.modelo.Sesion;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiEvaluacion")
@SessionScoped
public class UIEvaluacion {

    public static final String COMPONENTES_REFRESCAR = "dialog";

    private List<Evaluacion> evaluacionList;

    private boolean guardarActivo = false;
    private boolean nuevoActivo = true;
    private boolean eliminarActivo = false;
    private boolean consultarActivo = false;

    public String nuevo() {
        try {
            Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
            sesion.setDialogo(new Dialogo("dialogos/nuevo.xhtml", "Nueva Evaluación", "clip"));
            UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
            UtilJSF.execute("PF('dialog').show();");
        } catch (Exception e) {
            UtilLog.generarLog(this.getClass(), e);
        }
        return "";
    }

    @PostConstruct
    public void init() {
        UtilJSF.setBean("evaluacion", new Evaluacion(), UtilJSF.SESSION_SCOPE);
    }

    public String continuarEvaluacion() {
        try {
            GestorCiclo gestorCiclos = new GestorCiclo();
            Evaluacion e = (Evaluacion) UtilJSF.getBean("varEvaluacion");
            UtilJSF.setBean("evaluacion", e, UtilJSF.SESSION_SCOPE);

            e.setCiclos(gestorCiclos.cargarListaCiclos());

            this.nuevoActivo = Boolean.FALSE;
            this.guardarActivo = Boolean.TRUE;
            return ("/gestor/evaluacion.xhtml?faces-redirect=true");
        } catch (Exception e) {
        }

        return "";
    }

    public String procesarEvaluacion() {
        try {
            Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
            GestorEvaluacion gestorEvaluacion = new GestorEvaluacion();
            GestorGeneral gestorGeneral = new GestorGeneral();
            GestorCiclo gestorCiclos = new GestorCiclo();

            Evaluacion evaluacion = (Evaluacion) UtilJSF.getBean("evaluacion");
            Evaluacion e = new Evaluacion(new EvaluacionPK(gestorGeneral.nextval(GestorGeneral.GESTOR_EVALUACION_COD_EVALUACION_SEQ), sesion.getEstablecimiento().getCodigoEstablecimiento()),
                    sesion.getUsuarios().getUsuariosPK().getDocumentoUsuario(), new Date(), new Date(), App.EVALUACION_ESTADO_ABIERTO);

            e.setFecha(evaluacion.getFecha());
            e = gestorEvaluacion.validarEvaluacion(e);
            gestorEvaluacion.procesarEvaluacion(e);

            e.setCiclos(gestorCiclos.cargarListaCiclos());

            UtilMSG.addSuccessMsg("Auto-evaluación creada, código: " + e.getEvaluacionPK().getCodEvaluacion());
            UtilJSF.setBean("evaluacion", e, UtilJSF.SESSION_SCOPE);

            this.nuevoActivo = Boolean.FALSE;
            this.guardarActivo = Boolean.TRUE;
            return ("/gestor/evaluacion.xhtml?faces-redirect=true");
        } catch (Exception e) {
            if (UtilLog.causaControlada(e)) {
            } else {
                UtilLog.generarLog(this.getClass(), e);
            }
        }
        return "";
    }

    /**
     * @return the evaluacionList
     */
    public List<Evaluacion> getEvaluacionList() {
        if (evaluacionList == null || evaluacionList.isEmpty()) {
            try {
                Sesion s = (Sesion) UtilJSF.getBean("sesion");
                GestorEvaluacion gestorEvaluacion = new GestorEvaluacion();
                evaluacionList = gestorEvaluacion.cargarEvaluacionList(s.getEstablecimiento().getCodigoEstablecimiento(), s.getParametros().get(App.CONFIGURACION_MOSTRAR_EVALUACIONES).toString());
            } catch (Exception e) {
                UtilLog.generarLog(this.getClass(), e);
            }

        }
        return evaluacionList;
    }

    /**
     * @param evaluacionList the evaluacionList to set
     */
    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    public void consultar() {
    }

    public void guardar() {
    }

    public void eliminar() {
    }

    /**
     * @return the guardarActivo
     */
    public boolean isGuardarActivo() {
        return guardarActivo;
    }

    /**
     * @param guardarActivo the guardarActivo to set
     */
    public void setGuardarActivo(boolean guardarActivo) {
        this.guardarActivo = guardarActivo;
    }

    /**
     * @return the nuevoActivo
     */
    public boolean isNuevoActivo() {
        return nuevoActivo;
    }

    /**
     * @param nuevoActivo the nuevoActivo to set
     */
    public void setNuevoActivo(boolean nuevoActivo) {
        this.nuevoActivo = nuevoActivo;
    }

    /**
     * @return the eliminarActivo
     */
    public boolean isEliminarActivo() {
        return eliminarActivo;
    }

    /**
     * @param eliminarActivo the eliminarActivo to set
     */
    public void setEliminarActivo(boolean eliminarActivo) {
        this.eliminarActivo = eliminarActivo;
    }

    /**
     * @return the consultarActivo
     */
    public boolean isConsultarActivo() {
        return consultarActivo;
    }

    /**
     * @param consultarActivo the consultarActivo to set
     */
    public void setConsultarActivo(boolean consultarActivo) {
        this.consultarActivo = consultarActivo;
    }

    public String getComponentesRefrescar() {
        return COMPONENTES_REFRESCAR;
    }

}
