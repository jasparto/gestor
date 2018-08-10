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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author juliano
 */
@Entity
@Table(name = "evaluacion_capacitacion_detalle")
@NamedQueries({
    @NamedQuery(name = "EvaluacionCapacitacionDetalle.findAll", query = "SELECT e FROM EvaluacionCapacitacionDetalle e")})
public class EvaluacionCapacitacionDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EvaluacionCapacitacionDetallePK evaluacionCapacitacionDetallePK;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @JoinColumns({
        @JoinColumn(name = "cod_evaluacion", referencedColumnName = "cod_evaluacion", insertable = false, updatable = false),
        @JoinColumn(name = "codigo_establecimiento", referencedColumnName = "codigo_establecimiento", insertable = false, updatable = false),
        @JoinColumn(name = "cod_capacitacion", referencedColumnName = "cod_capacitacion", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private EvaluacionCapacitacion evaluacionCapacitacion;

    public EvaluacionCapacitacionDetalle() {
    }

    public EvaluacionCapacitacionDetalle(EvaluacionCapacitacionDetallePK evaluacionCapacitacionDetallePK) {
        this.evaluacionCapacitacionDetallePK = evaluacionCapacitacionDetallePK;
    }

    public EvaluacionCapacitacionDetalle(EvaluacionCapacitacionDetallePK evaluacionCapacitacionDetallePK, String estado) {
        this.evaluacionCapacitacionDetallePK = evaluacionCapacitacionDetallePK;
        this.estado = estado;
    }

    public EvaluacionCapacitacionDetalle(int codEvaluacion, short codigoEstablecimiento, int codCapacitacion, int codCapacitacionDetalle) {
        this.evaluacionCapacitacionDetallePK = new EvaluacionCapacitacionDetallePK(codEvaluacion, codigoEstablecimiento, codCapacitacion, codCapacitacionDetalle);
    }

    public EvaluacionCapacitacionDetallePK getEvaluacionCapacitacionDetallePK() {
        return evaluacionCapacitacionDetallePK;
    }

    public void setEvaluacionCapacitacionDetallePK(EvaluacionCapacitacionDetallePK evaluacionCapacitacionDetallePK) {
        this.evaluacionCapacitacionDetallePK = evaluacionCapacitacionDetallePK;
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

    public EvaluacionCapacitacion getEvaluacionCapacitacion() {
        return evaluacionCapacitacion;
    }

    public void setEvaluacionCapacitacion(EvaluacionCapacitacion evaluacionCapacitacion) {
        this.evaluacionCapacitacion = evaluacionCapacitacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluacionCapacitacionDetallePK != null ? evaluacionCapacitacionDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionCapacitacionDetalle)) {
            return false;
        }
        EvaluacionCapacitacionDetalle other = (EvaluacionCapacitacionDetalle) object;
        if ((this.evaluacionCapacitacionDetallePK == null && other.evaluacionCapacitacionDetallePK != null) || (this.evaluacionCapacitacionDetallePK != null && !this.evaluacionCapacitacionDetallePK.equals(other.evaluacionCapacitacionDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestor.gestor.EvaluacionCapacitacionDetalle[ evaluacionCapacitacionDetallePK=" + evaluacionCapacitacionDetallePK + " ]";
    }
    
}
