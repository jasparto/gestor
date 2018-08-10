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
public class EvaluacionPlanAccionDetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cod_evaluacion")
    private int codEvaluacion;
    @Basic(optional = false)
    @Column(name = "codigo_establecimiento")
    private short codigoEstablecimiento;
    @Basic(optional = false)
    @Column(name = "cod_plan")
    private int codPlan;
    @Basic(optional = false)
    @Column(name = "cod_plan_detalle")
    private int codPlanDetalle;

    public EvaluacionPlanAccionDetallePK() {
    }

    public EvaluacionPlanAccionDetallePK(int codEvaluacion, short codigoEstablecimiento, int codPlan, int codPlanDetalle) {
        this.codEvaluacion = codEvaluacion;
        this.codigoEstablecimiento = codigoEstablecimiento;
        this.codPlan = codPlan;
        this.codPlanDetalle = codPlanDetalle;
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

    public int getCodPlan() {
        return codPlan;
    }

    public void setCodPlan(int codPlan) {
        this.codPlan = codPlan;
    }

    public int getCodPlanDetalle() {
        return codPlanDetalle;
    }

    public void setCodPlanDetalle(int codPlanDetalle) {
        this.codPlanDetalle = codPlanDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codEvaluacion;
        hash += (int) codigoEstablecimiento;
        hash += (int) codPlan;
        hash += (int) codPlanDetalle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionPlanAccionDetallePK)) {
            return false;
        }
        EvaluacionPlanAccionDetallePK other = (EvaluacionPlanAccionDetallePK) object;
        if (this.codEvaluacion != other.codEvaluacion) {
            return false;
        }
        if (this.codigoEstablecimiento != other.codigoEstablecimiento) {
            return false;
        }
        if (this.codPlan != other.codPlan) {
            return false;
        }
        if (this.codPlanDetalle != other.codPlanDetalle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestor.gestor.EvaluacionPlanAccionDetallePK[ codEvaluacion=" + codEvaluacion + ", codigoEstablecimiento=" + codigoEstablecimiento + ", codPlan=" + codPlan + ", codPlanDetalle=" + codPlanDetalle + " ]";
    }
    
}
