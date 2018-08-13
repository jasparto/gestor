/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.gestor.controlador;

import com.gestor.controller.Gestor;
import com.gestor.gestor.EvaluacionPlanAccion;
import com.gestor.gestor.EvaluacionPlanAccionDetalle;
import com.gestor.gestor.dao.EvaluacionPlanAccionDAO;

/**
 *
 * @author juliano
 */
public class GestorEvaluacionPlanAccion extends Gestor {

    public GestorEvaluacionPlanAccion() throws Exception {
        super();
    }

    public EvaluacionPlanAccionDetalle validarEvaluacionPlanAccionDetalle(EvaluacionPlanAccionDetalle epd) {

        return epd;
    }

    public void procesarPlanAccion(EvaluacionPlanAccion ep) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            EvaluacionPlanAccionDAO evaluacionPlanAccionDAO = new EvaluacionPlanAccionDAO(conexion);
            evaluacionPlanAccionDAO.upsertEvaluacionPlanAccion(ep);
            evaluacionPlanAccionDAO.insertaEvaluacionPlanAccionDetalle(ep.getEvaluacionPlanAccionDetalle());
            
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }
}