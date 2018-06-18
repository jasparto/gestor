/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.controller;


import com.gestor.dao.GeneralDAO;
import java.io.Serializable;

/**
 *
 * @author juliano
 */
public class GestorGeneral extends Gestor implements Serializable {

    public static String ATENCION_SECCION_COD_SECCION_SEQ = "atencion.seccion_cod_seccion_seq";
    public static String ATENCION_SECCION_DETALLE_COD_DETALLE_SEQ = "atencion.seccion_detalle_cod_detalle_seq";
    public static String ATENCION_SECCION_DETALLE_COMBOS_COD_COMBO_SEQ = "atencion.seccion_detalle_combos_cod_combo_seq";
    public static String ATENCION_TIPO_RECURSO_COD_TIPO_RECURSO_SEQ = "atencion.tipo_recurso_cod_tipo_recurso_seq";
    public static String ATENCION_RECURSO_COD_RECURSO_SEQ = "atencion.recurso_cod_recurso_seq";
    public static String PARAMETROS_COD_PARAMETRO_SEQ = "parametros_cod_parametro_seq";
    public static String USUARIO_MENUS_COD_MENU_SEQ = "usuario.menus_cod_menu_seq";
    public static String FACTURACION_FACTURAS_CONFIGURACION_COD_CONFIGURACION_SEQ = "facturacion.facturas_configuracion_cod_configuracion_seq";
    public static String ATENCION_SERVICIOS_COD_SERVICIO_SEQ = "atencion.servicios_cod_servicio_seq";
    public static String CLIENTE_PROGRAMAS_COD_PROGRAMA_SEQ = "cliente.programas_cod_programa_seq";
    public static String CLIENTE_PROGRAMAS_CONFIGURACION_COD_CONFIGURACION_SEQ = "cliente.programas_configuracion_cod_configuracion_seq";
    public static String CLIENTE_PARAMETROS_CONFIGURACION_COD_PARAMETRO_SEQ = "cliente.parametros_configuracion_cod_parametro_seq";
    public static boolean VALIDA_PK = true;
    public static boolean NO_VALIDA_PK = false;
    public static String USUARIO_ROLES_CODIGO_ROL_SEQ = "usuario.roles_codigo_rol_seq";

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

//    public Collection<? extends TipoComponentes> cargarTipoComponentes() {
//        return getHibernateTemplate().find("FROM TipoComponentes");
//    }
//
//    public Collection<? extends Parametros> cargarParametros() {
//        return getHibernateTemplate().find("FROM Parametros");
//    }
//
//    public int siguienteCodigoEntidad(Integer codInstitucion, String campo, String entidad) throws Exception {
//        try {
//            this.abrirConexion();
//            GeneralDAO generalDAO = new GeneralDAO(session);
//            return generalDAO.siguienteCodigoEntidad(codInstitucion, campo, entidad);
//        } finally {
//            this.cerrarConexion();
//        }
//    }
//
//    public Collection<? extends PermisosBinarios> cargarPermisos() {
//        return getHibernateTemplate().find("FROM PermisosBinarios PB ORDER BY PB.codPermisoBinario");
//    }

}
