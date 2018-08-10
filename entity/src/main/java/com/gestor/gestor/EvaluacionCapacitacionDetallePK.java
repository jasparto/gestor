/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.gestor;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author juliano
 */
@Embeddable
public class EvaluacionCapacitacionDetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cod_evaluacion")
    private int codEvaluacion;
    @Basic(optional = false)
    @Column(name = "codigo_establecimiento")
    private short codigoEstablecimiento;
    @Basic(optional = false)
    @Column(name = "cod_capacitacion")
    private int codCapacitacion;
    @Basic(optional = false)
    @Column(name = "cod_capacitacion_detalle")
    private int codCapacitacionDetalle;

    public EvaluacionCapacitacionDetallePK() {
    }

    public EvaluacionCapacitacionDetallePK(int codEvaluacion, short codigoEstablecimiento, int codCapacitacion, int codCapacitacionDetalle) {
        this.codEvaluacion = codEvaluacion;
        this.codigoEstablecimiento = codigoEstablecimiento;
        this.codCapacitacion = codCapacitacion;
        this.codCapacitacionDetalle = codCapacitacionDetalle;
    }

    public int getCodEvaluacion() {
        return codEvaluacion;
    }

    public void setCodEvaluacion(int codEvaluacion) {
        this.codEvaluacion = codEvaluacion;
    }

    public short getCodigoEstablecimiento() {
        return codigoEstablecimiento;
    }

    public void setCodigoEstablecimiento(short codigoEstablecimiento) {
        this.codigoEstablecimiento = codigoEstablecimiento;
    }

    public int getCodCapacitacion() {
        return codCapacitacion;
    }

    public void setCodCapacitacion(int codCapacitacion) {
        this.codCapacitacion = codCapacitacion;
    }

    public int getCodCapacitacionDetalle() {
        return codCapacitacionDetalle;
    }

    public void setCodCapacitacionDetalle(int codCapacitacionDetalle) {
        this.codCapacitacionDetalle = codCapacitacionDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codEvaluacion;
        hash += (int) codigoEstablecimiento;
        hash += (int) codCapacitacion;
        hash += (int) codCapacitacionDetalle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionCapacitacionDetallePK)) {
            return false;
        }
        EvaluacionCapacitacionDetallePK other = (EvaluacionCapacitacionDetallePK) object;
        if (this.codEvaluacion != other.codEvaluacion) {
            return false;
        }
        if (this.codigoEstablecimiento != other.codigoEstablecimiento) {
            return false;
        }
        if (this.codCapacitacion != other.codCapacitacion) {
            return false;
        }
        if (this.codCapacitacionDetalle != other.codCapacitacionDetalle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestor.gestor.EvaluacionCapacitacionDetallePK[ codEvaluacion=" + codEvaluacion + ", codigoEstablecimiento=" + codigoEstablecimiento + ", codCapacitacion=" + codCapacitacion + ", codCapacitacionDetalle=" + codCapacitacionDetalle + " ]";
    }
    
}
