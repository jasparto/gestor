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

    
    
//    public static String USUARIO_MENUS_COD_MENU_SEQ = "usuario.menus_cod_menu_seq";
    
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

}
