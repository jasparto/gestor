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
import com.gestor.gestor.controlador.GestorEvaluacionPlanAccion;
import com.gestor.modelo.Sesion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiPlanAccion")
@SessionScoped

public class UIPlanAccion {

    private SeccionDetalleItems sdiSeleccionado;

    public void procesarPlanAccion() {
        try {
            EvaluacionPlanAccionDetalle epd = (EvaluacionPlanAccionDetalle) UtilJSF.getBean("evaluacionPlanAccionDetalle");
            String documentoUsuario = ((Sesion) UtilJSF.getBean("sesion")).getUsuarios().getUsuariosPK().getDocumentoUsuario();

            GestorGeneral gestorGeneral = new GestorGeneral();
            GestorEvaluacionPlanAccion gestorEvaluacionPlanAccion = new GestorEvaluacionPlanAccion();

            EvaluacionPlanAccion ep = new EvaluacionPlanAccion(
                    new EvaluacionPlanAccionPK(epd.getEvaluacionPlanAccionDetallePK().getCodEvaluacion(), epd.getEvaluacionPlanAccionDetallePK().getCodigoEstablecimiento(), gestorGeneral.nextval(GestorGeneral.GESTOR_EVALUACION_PLAN_ACCION_COD_PLAN_SEQ)),
                    App.PLAN_ACCION_ESTADO_ABIERTO,
                    documentoUsuario, documentoUsuario);
            epd.setEvaluacionPlanAccionDetallePK(new EvaluacionPlanAccionDetallePK(ep.getEvaluacionPlanAccionPK().getCodEvaluacion(), ep.getEvaluacionPlanAccionPK().getCodigoEstablecimiento(), ep.getEvaluacionPlanAccionPK().getCodPlan()));
            epd.setEstado(App.PLAN_ACCION_DETALLE_ESTADO_ABIERTO);

            epd.setCodCiclo(sdiSeleccionado.getSeccionDetalleItemsPK().getCodCiclo());
            epd.setCodSeccion(sdiSeleccionado.getSeccionDetalleItemsPK().getCodSeccion());
            epd.setCodDetalle(sdiSeleccionado.getSeccionDetalleItemsPK().getCodDetalle());
            epd.setCodItem(sdiSeleccionado.getSeccionDetalleItemsPK().getCodItem());

            epd = gestorEvaluacionPlanAccion.validarEvaluacionPlanAccionDetalle(epd);

            ep.setEvaluacionPlanAccionDetalle(epd);
            gestorEvaluacionPlanAccion.procesarPlanAccion(ep);

        } catch (Exception ex) {
            Logger.getLogger(UIPlanAccion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void mostrarDialogoPlanAccion() {
        this.sdiSeleccionado = (SeccionDetalleItems) UtilJSF.getBean("varSeccionDetalleItems");
        Dialogo dialogo = new Dialogo("dialogos/plan-accion.xhtml", "Plan Acción", "clip", Dialogo.WIDTH_AUTO);
        UtilJSF.setBean("dialogo", dialogo, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }
}
