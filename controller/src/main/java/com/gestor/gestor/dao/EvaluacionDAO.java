/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.gestor.dao;

import com.gestor.entity.UtilFecha;
import com.gestor.entity.UtilLog;
import com.gestor.gestor.Evaluacion;
import com.gestor.gestor.EvaluacionPK;
import com.gestor.gestor.EvaluacionPuntajes;
import com.gestor.gestor.EvaluacionPuntajesPK;
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

    public List<EvaluacionPuntajes> cargarEvaluacionPuntajes(int codigoEstablecimiento, Long codEvaluacion) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT codigo_establecimiento, cod_evaluacion, cod_puntaje, descripcion,"
                    + " plan_accion, capacitacion, califica"
                    + " FROM gestor.evaluacion_puntajes"
                    + " WHERE codigo_establecimiento=" + codigoEstablecimiento + " AND cod_evaluacion=" + codEvaluacion
            );
            rs = consulta.ejecutar(sql);
            List<EvaluacionPuntajes> evaluacionPuntajeses = new ArrayList<>();
            while (rs.next()) {
                EvaluacionPuntajes evaluacionPuntajes = new EvaluacionPuntajes(new EvaluacionPuntajesPK(rs.getInt("codigo_establecimiento"), rs.getLong("cod_evaluacion"), rs.getString("cod_puntaje")),
                        rs.getString("descripcion"), rs.getBoolean("plan_accion"), rs.getBoolean("capacitacion"), rs.getBoolean("califica"));
                evaluacionPuntajeses.add(evaluacionPuntajes);
            }
            return evaluacionPuntajeses;

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public EvaluacionPuntajes cargarEvaluacionPuntajes(int codigoEstablecimiento, Long codEvaluacion, String descripcion) throws Exception {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT codigo_establecimiento, cod_evaluacion, cod_puntaje, descripcion,"
                    + " plan_accion, capacitacion, califica"
                    + " FROM gestor.evaluacion_puntajes"
                    + " WHERE codigo_establecimiento=" + codigoEstablecimiento
                    + " AND cod_evaluacion=" + codEvaluacion + " AND descripcion='" + descripcion + "'"
            );
            rs = consulta.ejecutar(sql);
            if (rs.next()) {
                EvaluacionPuntajes evaluacionPuntajes = new EvaluacionPuntajes(new EvaluacionPuntajesPK(rs.getInt("codigo_establecimiento"), rs.getLong("cod_evaluacion"), rs.getString("cod_puntaje")),
                        rs.getString("descripcion"), rs.getBoolean("plan_accion"), rs.getBoolean("capacitacion"), rs.getBoolean("califica"));
                return evaluacionPuntajes;
            } else {
                throw new Exception("No se pudo cargar el puntaje con descripción, " + descripcion, UtilLog.TW_RESTRICCION);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Integer avanceEvaluacion(int codigoEstablecimiento, Long codEvaluacion) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT count(SDI), count(EPSD), ((count(EPSD)::float/count(SDI)::float)*100)::INT AS avance"
                    + " FROM gestor.seccion_detalle_items SDI"
                    + " LEFT JOIN gestor.evaluacion_puntaje_seccion_detalle_combos EPSD on (EPSD.cod_ciclo=SDI.cod_ciclo AND EPSD.cod_seccion=SDI.cod_seccion"
                    + " AND EPSD.cod_detalle=SDI.cod_detalle AND EPSD.cod_item=SDI.cod_item"
                    + " AND codigo_establecimiento=" + codigoEstablecimiento + " AND cod_evaluacion=" + codEvaluacion + ")"
                    + " WHERE SDI.activo"
            );
            rs = consulta.ejecutar(sql);
            rs.next();
            return rs.getInt("avance");

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
