/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.controller;

import java.util.Properties;

/**
 *
 * @author juliano
 */
public class GestorPropiedades {

    public Properties cargarPropiedades() throws Exception {
        Properties p = new Properties();
        try {
//            p.setProperty("urlbd", "jdbc:postgresql://127.0.0.1:5433/myapp");
            p.setProperty("urlbd", "jdbc:postgresql://10.1.1.141:5432/myapp");
//            p.setProperty("urlbd", "jdbc:postgresql://localhost:5433/myapp");
            p.setProperty("controlador", "org.postgresql.Driver");
            p.setProperty("usuario", "adminw4baqh8");
            p.setProperty("clave", "NzlgZYzISdKt");

        } catch (Exception e) {
            throw e;
        }
        return p;
    }
}
