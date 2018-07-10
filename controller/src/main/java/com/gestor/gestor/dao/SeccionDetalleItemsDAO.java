/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.gestor.dao;

import com.gestor.gestor.Seccion;
import com.gestor.gestor.SeccionDetalle;
import com.gestor.gestor.SeccionDetalleItems;
import com.gestor.gestor.SeccionDetalleItemsPK;
import com.gestor.gestor.SeccionDetallePK;
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
public class SeccionDetalleItemsDAO {

    private Connection conexion;

    public SeccionDetalleItemsDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public List<SeccionDetalleItems> cargarListaSeccionDetalleItems(String codCiclo, int codSeccion, int codDetalle) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT cod_ciclo, cod_seccion, cod_detalle, cod_item, detalle, peso, activo, imagen, orden"
                    + " FROM gestor.seccion_detalle_items"
                    + " WHERE cod_ciclo='" + codCiclo + "' AND cod_seccion=" + codSeccion + " AND cod_detalle=" + codDetalle
                    + " ORDER BY orden"
            );

            rs = consulta.ejecutar(sql);
            List<SeccionDetalleItems> seccionDetalleItemses = new ArrayList<>();
            while (rs.next()) {
                SeccionDetalleItems sdi = new SeccionDetalleItems(new SeccionDetalleItemsPK(rs.getString("cod_ciclo"), rs.getInt("cod_seccion"), rs.getInt("cod_detalle"), rs.getInt("cod_item")),
                        rs.getString("detalle"), rs.getDouble("peso"), rs.getBoolean("activo"), rs.getString("imagen"), rs.getInt("orden"));
                seccionDetalleItemses.add(sdi);
            }
            return seccionDetalleItemses;
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
