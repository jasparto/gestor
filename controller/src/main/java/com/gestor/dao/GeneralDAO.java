/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.dao;

import conexion.Consulta;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author juliano
 */
public class GeneralDAO {

    private Connection conexion;

    public GeneralDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public Long nextval(String secuencia) throws SQLException {

        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder("select nextval('" + secuencia + "')");
            rs = consulta.ejecutar(sql);
            rs.next();
            return rs.getLong(1);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }
    
    public int siguienteCodigoEntidad(String campo, String entidad) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT COALESCE(MAX(" + campo + "),0) + 1"
                    + " FROM " + entidad
            );
            rs = consulta.ejecutar(sql);
            rs.next();
            return rs.getInt(1);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

//    public int siguienteCodigoEntidad(Integer codInstitucion, String campo, String entidad) {
//        Query query = session.createSQLQuery(
//                "SELECT COALESCE(MAX(" + campo + "),0) + 1"
//                + " FROM " + entidad
//                + " WHERE cod_institucion=?"
//        );
//        query.setParameter(0, codInstitucion);
//        return (int) query.uniqueResult();
//    }
}
