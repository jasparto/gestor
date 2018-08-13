/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.gestor;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author juliano
 */
@Entity
@Table(name = "evaluacion_plan_accion_detalle")
@NamedQueries({
    @NamedQuery(name = "EvaluacionPlanAccionDetalle.findAll", query = "SELECT e FROM EvaluacionPlanAccionDetalle e")})
public class EvaluacionPlanAccionDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EvaluacionPlanAccionDetallePK evaluacionPlanAccionDetallePK;
    @Column(name = "cod_ciclo")
    private String codCiclo;
    @Column(name = "cod_seccion")
    private Integer codSeccion;
    @Column(name = "cod_detalle")
    private Integer codDetalle;
    @Column(name = "cod_item")
    private Integer codItem;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;

    public EvaluacionPlanAccionDetalle() {
    }

    public EvaluacionPlanAccionDetalle(EvaluacionPlanAccionDetallePK evaluacionPlanAccionDetallePK) {
        this.evaluacionPlanAccionDetallePK = evaluacionPlanAccionDetallePK;
    }

    public EvaluacionPlanAccionDetalle(EvaluacionPlanAccionDetallePK evaluacionPlanAccionDetallePK, String estado) {
        this.evaluacionPlanAccionDetallePK = evaluacionPlanAccionDetallePK;
        this.estado = estado;
    }

    public EvaluacionPlanAccionDetalle(int codEvaluacion, int codigoEstablecimiento, Long codPlan, int codPlanDetalle) {
        this.evaluacionPlanAccionDetallePK = new EvaluacionPlanAccionDetallePK(codEvaluacion, codigoEstablecimiento, codPlan, codPlanDetalle);
    }

    public EvaluacionPlanAccionDetallePK getEvaluacionPlanAccionDetallePK() {
        return evaluacionPlanAccionDetallePK;
    }

    public void setEvaluacionPlanAccionDetallePK(EvaluacionPlanAccionDetallePK evaluacionPlanAccionDetallePK) {
        this.evaluacionPlanAccionDetallePK = evaluacionPlanAccionDetallePK;
    }
    
     public void setEvaluacionPlanAccionDetallePK(EvaluacionPlanAccionDetallePK evaluacionPlanAccionDetallePK, String estado) {
        this.evaluacionPlanAccionDetallePK = evaluacionPlanAccionDetallePK;
    }

    public String getCodCiclo() {
        return codCiclo;
    }

    public void setCodCiclo(String codCiclo) {
        this.codCiclo = codCiclo;
    }

    public Integer getCodSeccion() {
        return codSeccion;
    }

    public void setCodSeccion(Integer codSeccion) {
        this.codSeccion = codSeccion;
    }

    public Integer getCodDetalle() {
        return codDetalle;
    }

    public void setCodDetalle(Integer codDetalle) {
        this.codDetalle = codDetalle;
    }

    public Integer getCodItem() {
        return codItem;
    }

    public void setCodItem(Integer codItem) {
        this.codItem = codItem;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluacionPlanAccionDetallePK != null ? evaluacionPlanAccionDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionPlanAccionDetalle)) {
            return false;
        }
        EvaluacionPlanAccionDetalle other = (EvaluacionPlanAccionDetalle) object;
        if ((this.evaluacionPlanAccionDetallePK == null && other.evaluacionPlanAccionDetallePK != null) || (this.evaluacionPlanAccionDetallePK != null && !this.evaluacionPlanAccionDetallePK.equals(other.evaluacionPlanAccionDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestor.gestor.EvaluacionPlanAccionDetalle[ evaluacionPlanAccionDetallePK=" + evaluacionPlanAccionDetallePK + " ]";
    }
    
}
