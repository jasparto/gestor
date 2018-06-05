/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.modelo;


import com.gestor.entity.Dialogo;
import com.gestor.publico.Establecimiento;
import com.gestor.publico.Usuarios;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author juliano
 */
@ManagedBean
@SessionScoped
public class Sesion {

    private Dialogo dialogo;
    private Usuarios usuarios;
    private Establecimiento establecimiento;
    private boolean logueado;
    private HashMap parametros = new HashMap();
    private HashMap<Integer, Boolean> permisos = new HashMap<>();

    /**
     * @return the dialogo
     */
    public Dialogo getDialogo() {
        return dialogo;
    }

    /**
     * @param dialogo the dialogo to set
     */
    public void setDialogo(Dialogo dialogo) {
        this.dialogo = dialogo;
    }

    /**
     * @return the usuarios
     */
    public Usuarios getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the logueado
     */
    public boolean isLogueado() {
        return logueado;
    }

    /**
     * @param logueado the logueado to set
     */
    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }

    /**
     * @return the parametros
     */
    public HashMap getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(HashMap parametros) {
        this.parametros = parametros;
    }


    /**
     * @return the permisos
     */
    public HashMap<Integer, Boolean> getPermisos() {
        return permisos;
    }

    /**
     * @param permisos the permisos to set
     */
    public void setPermisos(HashMap<Integer, Boolean> permisos) {
        this.permisos = permisos;
    }

    /**
     * @return the establecimiento
     */
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
}
