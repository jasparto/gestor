/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.gestor.dao;

import com.gestor.gestor.EvaluacionPlanAccion;
import com.gestor.gestor.EvaluacionPlanAccionDetalle;
import conexion.Consulta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author juliano
 */
public class EvaluacionPlanAccionDAO {

    private Connection conexion;

    public EvaluacionPlanAccionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void upsertEvaluacionPlanAccion(EvaluacionPlanAccion ep) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO gestor.evaluacion_plan_accion("
                    + " cod_evaluacion, codigo_establecimiento, cod_plan, documento_usuario,"
                    + " fecha_registro, documento_usuario_modifica, fecha_actualiza, estado)"
                    + " VALUES (" + ep.getEvaluacionPlanAccionPK().getCodEvaluacion() + ", " + ep.getEvaluacionPlanAccionPK().getCodigoEstablecimiento() + ", " + ep.getEvaluacionPlanAccionPK().getCodPlan() + ","
                    + " '" + ep.getDocumentoUsuario() + "', NOW(), '" + ep.getDocumentoUsuarioModifica() + "', NOW(), '" + ep.getEstado() + "')"
                    + " ON CONFLICT (cod_evaluacion, codigo_establecimiento, cod_plan)"
                    + " DO UPDATE SET documento_usuario_modifica=excluded.documento_usuario_modifica, fecha_actualiza=excluded.fecha_actualiza, estado=excluded.estado"
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void insertaEvaluacionPlanAccionDetalle(EvaluacionPlanAccionDetalle epd) throws SQLException {

        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO gestor.evaluacion_plan_accion_detalle("
                    + " cod_evaluacion, codigo_establecimiento, cod_plan, cod_plan_detalle,"
                    + " cod_ciclo, cod_seccion, cod_detalle, cod_item, nombre, descripcion,"
                    + " estado)"
                    + " VALUES (" + epd.getEvaluacionPlanAccionDetallePK().getCodEvaluacion() + ", " + epd.getEvaluacionPlanAccionDetallePK().getCodigoEstablecimiento()
                    + " ," + epd.getEvaluacionPlanAccionDetallePK().getCodPlan() + ","
                    + " 'DEFAULT', '" + epd.getCodCiclo() + "', " + epd.getCodSeccion() + ", " + epd.getCodDetalle()
                    + " ," + epd.getCodItem() + ", '" + epd.getNombre() + "', '" + epd.getDescripcion() + "', '" + epd.getEstado() + "');"
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

}
