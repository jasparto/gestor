/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.gestor.dao;

import com.gestor.publico.EvaluacionAdjuntos;
import conexion.Consulta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author juliano
 */
public class EvaluacionAdjuntosDAO {

    private Connection conexion;

    public EvaluacionAdjuntosDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertaEvaluacionAdjuntos(EvaluacionAdjuntos ea) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO gestor.evaluacion_adjuntos("
                    + " cod_evaluacion, codigo_establecimiento, cod_ciclo, cod_seccion,"
                    + " cod_detalle, cod_item, cod_adjunto, nombre, descripcion, archivo,"
                    + " extension, fecha, documento_usuario, estado)"
                    + " VALUES (" + ea.getEvaluacionAdjuntosPK().getCodEvaluacion() + ", " + ea.getEvaluacionAdjuntosPK().getCodigoEstablecimiento() + ","
                    + " '" + ea.getEvaluacionAdjuntosPK().getCodCiclo() + "', " + ea.getEvaluacionAdjuntosPK().getCodSeccion()
                    + " , " + ea.getEvaluacionAdjuntosPK().getCodDetalle() + ", " + ea.getEvaluacionAdjuntosPK().getCodItem() + ", DEFAULT,"
                    + " '" + ea.getNombre() + "', '" + ea.getDescripcion() + "', '" + ea.getArchivo() + "', '" + ea.getExtension() + "', NOW(), '" + ea.getDocumentoUsuario() + "', '" + ea.getEstado() + "');"
            );
            consulta.actualizar(sql);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

}
