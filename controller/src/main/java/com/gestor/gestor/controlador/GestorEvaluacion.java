/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.gestor.controlador;

import com.gestor.controller.Gestor;
import com.gestor.entity.UtilLog;
import com.gestor.gestor.Evaluacion;
import com.gestor.gestor.EvaluacionPuntajes;
import com.gestor.gestor.EvaluacionPuntajesPK;
import com.gestor.gestor.Puntajes;
import com.gestor.gestor.dao.EvaluacionDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juliano
 */
public class GestorEvaluacion extends Gestor {

    public GestorEvaluacion() throws Exception {
        super();
    }

    public Evaluacion validarEvaluacion(Evaluacion e) throws Exception {
        if (e.getEvaluacionPK() == null || e.getEvaluacionPK().getCodEvaluacion() == null) {
            throw new Exception("No fue posible generar el consecutivo de la evaluación.", UtilLog.TW_VALIDACION);
        }
        if (e.getEvaluacionPK().getCodigoEstablecimiento() == 0) {
            throw new Exception("No se determino una empresa seleccioanda.", UtilLog.TW_VALIDACION);
        }
        if (e.getDocumentoUsuario() == null || e.getDocumentoUsuario().equalsIgnoreCase("")) {
            throw new Exception("Error en la sesión, por favor cierre sesión e ingresa de nuevo.", UtilLog.TW_VALIDACION);
        }
        return e;
    }

    public void procesarEvaluacion(Evaluacion e) throws Exception {
        try {
            this.abrirConexion();

            EvaluacionDAO evaluacionDAO = new EvaluacionDAO(conexion);
            evaluacionDAO.upsertEvaluacion(e);
            e.getEvaluacionPuntajesList().forEach((ep) -> {
                try {
                    evaluacionDAO.insertEvaluacionPuntajes(ep);
                } catch (SQLException ex) {
                    Logger.getLogger(GestorEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.cerrarConexion();
        }
    }

    public List<Evaluacion> cargarEvaluacionList(Integer codigoEstablecimiento, String mostrarEvaluaciones) throws Exception {
        try {
            this.abrirConexion();
            EvaluacionDAO evaluacionDAO = new EvaluacionDAO(conexion);
            if (mostrarEvaluaciones != null && mostrarEvaluaciones.equalsIgnoreCase("S")) {
                return evaluacionDAO.cargarEvaluacionList();
            } else {
                return evaluacionDAO.cargarEvaluacionList(codigoEstablecimiento);
            }
        } finally {
            this.cerrarConexion();
        }
    }

    public List<EvaluacionPuntajes> generarEvaluacionPuntajes(int codigoEstablecimiento, Long codEvaluacion, List<Puntajes> puntajesList) {
        List<EvaluacionPuntajes> evaluacionPuntajeses = new ArrayList<>();
        puntajesList.forEach((p) -> {
            EvaluacionPuntajes evaluacionPuntajes = new EvaluacionPuntajes(new EvaluacionPuntajesPK(codigoEstablecimiento, codEvaluacion, p.getPuntajesPK().getCodPuntaje()), p.getDescripcion(),
                    p.getPlanAccion(), p.getCapacitacion(), p.getCalifica());
            evaluacionPuntajeses.add(evaluacionPuntajes);
        });
        return evaluacionPuntajeses;
    }
}
