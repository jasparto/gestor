/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.gestor;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author juliano
 */
@Entity
@Table(name = "seccion_detalle_items")
@NamedQueries({
    @NamedQuery(name = "SeccionDetalleItems.findAll", query = "SELECT s FROM SeccionDetalleItems s")})
public class SeccionDetalleItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SeccionDetalleItemsPK seccionDetalleItemsPK;
    @Column(name = "detalle")
    private String detalle;
    @Column(name = "peso")
    private Integer peso;
    @ManyToMany(mappedBy = "seccionDetalleItemsList")
    private List<EvaluacionPuntajes> evaluacionPuntajesList;
    @JoinColumns({
        @JoinColumn(name = "cod_seccion", referencedColumnName = "cod_seccion", insertable = false, updatable = false),
        @JoinColumn(name = "cod_detalle", referencedColumnName = "cod_detalle", insertable = false, updatable = false),
        @JoinColumn(name = "cod_ciclo", referencedColumnName = "cod_ciclo", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SeccionDetalle seccionDetalle;

    public SeccionDetalleItems() {
    }

    public SeccionDetalleItems(SeccionDetalleItemsPK seccionDetalleItemsPK) {
        this.seccionDetalleItemsPK = seccionDetalleItemsPK;
    }

    public SeccionDetalleItems(String codCiclo, int codSeccion, int codDetalle, int codItem) {
        this.seccionDetalleItemsPK = new SeccionDetalleItemsPK(codCiclo, codSeccion, codDetalle, codItem);
    }

    public SeccionDetalleItemsPK getSeccionDetalleItemsPK() {
        return seccionDetalleItemsPK;
    }

    public void setSeccionDetalleItemsPK(SeccionDetalleItemsPK seccionDetalleItemsPK) {
        this.seccionDetalleItemsPK = seccionDetalleItemsPK;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public List<EvaluacionPuntajes> getEvaluacionPuntajesList() {
        return evaluacionPuntajesList;
    }

    public void setEvaluacionPuntajesList(List<EvaluacionPuntajes> evaluacionPuntajesList) {
        this.evaluacionPuntajesList = evaluacionPuntajesList;
    }

    public SeccionDetalle getSeccionDetalle() {
        return seccionDetalle;
    }

    public void setSeccionDetalle(SeccionDetalle seccionDetalle) {
        this.seccionDetalle = seccionDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seccionDetalleItemsPK != null ? seccionDetalleItemsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeccionDetalleItems)) {
            return false;
        }
        SeccionDetalleItems other = (SeccionDetalleItems) object;
        if ((this.seccionDetalleItemsPK == null && other.seccionDetalleItemsPK != null) || (this.seccionDetalleItemsPK != null && !this.seccionDetalleItemsPK.equals(other.seccionDetalleItemsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestor.gestor.SeccionDetalleItems[ seccionDetalleItemsPK=" + seccionDetalleItemsPK + " ]";
    }
    
}
