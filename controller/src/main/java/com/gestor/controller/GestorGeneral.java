/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.controller;

import com.gestor.dao.GeneralDAO;
import com.gestor.gestor.Ciclo;
import com.gestor.gestor.Seccion;
import com.gestor.gestor.SeccionDetalle;
import com.gestor.gestor.dao.CicloDAO;
import com.gestor.gestor.dao.SeccionDAO;
import com.gestor.gestor.dao.SeccionDetalleDAO;
import com.gestor.gestor.dao.SeccionDetalleItemsDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juliano
 */
public class GestorGeneral extends Gestor implements Serializable {

//    public static String USUARIO_MENUS_COD_MENU_SEQ = "usuario.menus_cod_menu_seq";
    public static String GESTOR_EVALUACION_COD_EVALUACION_SEQ = "gestor.evaluacion_cod_evaluacion_seq";
    public static String GESTOR_EVALUACION_PLAN_ACCION_COD_PLAN_SEQ = "gestor.evaluacion_cod_evaluacion_seq";
    public static String GESTOR_EVALUACION_ADJUNTOS_COD_ADJUNTO_SEQ = "gestor.evaluacion_adjuntos_cod_adjunto_seq";
    public static String GESTOR_EVALUACION_CAPACITACION_COD_CAPACITACION_SEQ = "gestor.evaluacion_capacitacion_cod_capacitacion_seq";

    public static boolean VALIDA_PK = true;
    public static boolean NO_VALIDA_PK = false;

    public GestorGeneral() throws Exception {
        super();
    }

    public Long nextval(String secuencia) throws Exception {
        try {
            this.abrirConexion();
            return new GeneralDAO(this.conexion).nextval(secuencia);
        } finally {
            this.cerrarConexion();
        }
    }

    public int siguienteCodigoEntidad(String campo, String entidad) throws Exception {
        try {
            this.abrirConexion();
            GeneralDAO generalDAO = new GeneralDAO(this.conexion);
            return generalDAO.siguienteCodigoEntidad(campo, entidad);
        } finally {
            this.cerrarConexion();
        }
    }

    public List<Ciclo> cargarCiclosEvaluacion() throws Exception {
        try {
            this.abrirConexion();
            List<Ciclo> ciclos = new ArrayList<>();

            CicloDAO cicloDAO = new CicloDAO(conexion);
            SeccionDAO seccionDAO = new SeccionDAO(conexion);
            SeccionDetalleDAO seccionDetalleDAO = new SeccionDetalleDAO(conexion);
            SeccionDetalleItemsDAO seccionDetalleItemsDAO = new SeccionDetalleItemsDAO(conexion);

            ciclos.addAll(cicloDAO.cargarListaCiclos());
            for (Ciclo c : ciclos) {
                c.setSeccionList(seccionDAO.cargarListaSeccion(c.getCodCiclo()));
                for (Seccion s : c.getSeccionList()) {
                    s.setSeccionDetalleList(
                            seccionDetalleDAO.cargarListaSeccionDetalle(s.getSeccionPK().getCodCiclo(), s.getSeccionPK().getCodSeccion())
                    );
                    for (SeccionDetalle sd : s.getSeccionDetalleList()) {
                        sd.setSeccionDetalleItemsList(
                                seccionDetalleItemsDAO.cargarListaSeccionDetalleItems(sd.getSeccionDetallePK().getCodCiclo(), sd.getSeccionDetallePK().getCodSeccion(), sd.getSeccionDetallePK().getCodDetalle())
                        );
                    }
                }
            }

            return ciclos;
        } finally {
            this.cerrarConexion();
        }
    }

}
