/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.gestor.dao;

import com.gestor.entity.UtilFecha;
import com.gestor.gestor.Evaluacion;
import com.gestor.gestor.EvaluacionPK;
import com.gestor.gestor.EvaluacionPuntajes;
import com.gestor.publico.Usuarios;
import com.gestor.publico.UsuariosPK;
import conexion.Consulta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juliano
 */
public class EvaluacionDAO {

    private Connection conexion;

    public EvaluacionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void upsertEvaluacion(Evaluacion e) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO gestor.evaluacion("
                    + " cod_evaluacion, codigo_establecimiento, documento_usuario, fecha, "
                    + " fecha_registro, estado)"
                    + " VALUES (" + e.getEvaluacionPK().getCodEvaluacion() + ", " + e.getEvaluacionPK().getCodigoEstablecimiento() + ", '" + e.getDocumentoUsuario() + "',"
                    + UtilFecha.formatoFecha(e.getFecha(), null, UtilFecha.PATRON_FECHA_YYYYMMDD, UtilFecha.CARACTER_COMILLA) + " , NOW(), '" + e.getEstado() + "')"
                    + " ON CONFLICT (cod_evaluacion, codigo_establecimiento)"
                    + " DO UPDATE SET documento_usuario=excluded.documento_usuario, fecha=excluded.fecha, estado=excluded.estado"
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public List<Evaluacion> cargarEvaluacionList() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT cod_evaluacion, codigo_establecimiento, documento_usuario, fecha,"
                    + " fecha_registro, estado,"
                    + " U.documento_usuario, U.nombre, U.apellido"
                    + " FROM gestor.evaluacion"
                    + " JOIN public.usuarios U USING (documento_usuario)"
            );
            rs = consulta.ejecutar(sql);
            List<Evaluacion> evaluacions = new ArrayList<>();
            while (rs.next()) {
                Evaluacion e = new Evaluacion(new EvaluacionPK(rs.getLong("cod_evaluacion"), rs.getInt("codigo_establecimiento")), rs.getString("documento_usuario"),
                        rs.getDate("fecha"), rs.getDate("fecha_registro"), rs.getString("estado"));
                e.setUsuarios(new Usuarios(new UsuariosPK(rs.getString("documento_usuario")), rs.getString("nombre"), rs.getString("apellido")));
                evaluacions.add(e);
            }
            return evaluacions;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public List<Evaluacion> cargarEvaluacionList(Integer codigoEstablecimiento) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT cod_evaluacion, codigo_establecimiento, documento_usuario, fecha,"
                    + " fecha_registro, estado,"
                    + " U.documento_usuario, U.nombre, U.apellido"
                    + " FROM gestor.evaluacion"
                    + " JOIN public.usuarios U USING (documento_usuario)"
                    + " WHERE codigo_establecimiento=" + codigoEstablecimiento
            );
            rs = consulta.ejecutar(sql);
            List<Evaluacion> evaluacions = new ArrayList<>();
            while (rs.next()) {
                Evaluacion e = new Evaluacion(new EvaluacionPK(rs.getLong("cod_evaluacion"), rs.getInt("codigo_establecimiento")), rs.getString("documento_usuario"),
                        rs.getDate("fecha"), rs.getDate("fecha_registro"), rs.getString("estado"));
                e.setUsuarios(new Usuarios(new UsuariosPK(rs.getString("documento_usuario")), rs.getString("nombre"), rs.getString("apellido")));
                evaluacions.add(e);
            }
            return evaluacions;

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void insertEvaluacionPuntajes(EvaluacionPuntajes ep) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO gestor.evaluacion_puntajes("
                    + " codigo_establecimiento, cod_evaluacion, cod_puntaje, descripcion, "
                    + " plan_accion, capacitacion, califica)"
                    + " VALUES (" + ep.getEvaluacionPuntajesPK().getCodigoEstablecimiento() + ", " + ep.getEvaluacionPuntajesPK().getCodEvaluacion()
                    + " , '" + ep.getEvaluacionPuntajesPK().getCodPuntaje() + "', '" + ep.getDescripcion() + "'"
                    + " , " + ep.getPlanAccion() + ", " + ep.getCapacitacion() + ", " + ep.getCalifica() + ");"
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

}
