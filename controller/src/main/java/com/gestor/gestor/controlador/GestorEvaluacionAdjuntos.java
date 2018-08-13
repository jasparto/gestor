/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.gestor.controlador;

import com.gestor.controller.Gestor;
import com.gestor.entity.UtilLog;
import com.gestor.gestor.dao.EvaluacionAdjuntosDAO;
import com.gestor.publico.EvaluacionAdjuntos;

/**
 *
 * @author juliano
 */
public class GestorEvaluacionAdjuntos extends Gestor {

    public GestorEvaluacionAdjuntos() throws Exception {
        super();
    }

    public EvaluacionAdjuntos validarEvaluacionAdjuntos(EvaluacionAdjuntos evaluacionAdjuntos) throws Exception {
        if (evaluacionAdjuntos.getEvaluacionAdjuntosPK() == null) {
            throw new Exception("No se pudo cargar la información de la evaluación.", UtilLog.TW_VALIDACION);
        }
        if (evaluacionAdjuntos.getNombre() == null || evaluacionAdjuntos.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre del adjunto.", UtilLog.TW_VALIDACION);
        }
        if (evaluacionAdjuntos.getDescripcion() == null || evaluacionAdjuntos.getDescripcion().equalsIgnoreCase("")) {
            throw new Exception("Ingresa la descripción del adjunto.", UtilLog.TW_VALIDACION);
        }
        evaluacionAdjuntos.setNombre(evaluacionAdjuntos.getNombre().toUpperCase().trim());
        evaluacionAdjuntos.setDescripcion(evaluacionAdjuntos.getDescripcion().trim());
        return evaluacionAdjuntos;
    }

    public void procesarEvaluacionAdjuntos(EvaluacionAdjuntos evaluacionAdjuntos) throws Exception {
        try {
            this.abrirConexion();

            EvaluacionAdjuntosDAO evaluacionAdjuntosDAO = new EvaluacionAdjuntosDAO(conexion);
            evaluacionAdjuntosDAO.insertaEvaluacionAdjuntos(evaluacionAdjuntos);
        } finally {
            this.cerrarConexion();
        }
    }
}
