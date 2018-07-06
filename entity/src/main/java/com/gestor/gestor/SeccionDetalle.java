/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.gestor;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author juliano
 */
@Entity
@Table(name = "seccion_detalle")
@NamedQueries({
    @NamedQuery(name = "SeccionDetalle.findAll", query = "SELECT s FROM SeccionDetalle s")})
public class SeccionDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SeccionDetallePK seccionDetallePK;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "orden")
    private Integer orden;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso")
    private Double peso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seccionDetalle")
    private List<SeccionDetalleItems> seccionDetalleItemsList;
    @JoinColumns({
        @JoinColumn(name = "cod_seccion", referencedColumnName = "cod_seccion", insertable = false, updatable = false),
        @JoinColumn(name = "cod_ciclo", referencedColumnName = "cod_ciclo", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Seccion seccion;

    public SeccionDetalle() {
    }

    public SeccionDetalle(SeccionDetallePK seccionDetallePK) {
        this.seccionDetallePK = seccionDetallePK;
    }

    public SeccionDetalle(String codCiclo, int codSeccion, int codDetalle) {
        this.seccionDetallePK = new SeccionDetallePK(codCiclo, codSeccion, codDetalle);
    }

    public SeccionDetallePK getSeccionDetallePK() {
        return seccionDetallePK;
    }

    public void setSeccionDetallePK(SeccionDetallePK seccionDetallePK) {
        this.seccionDetallePK = seccionDetallePK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public List<SeccionDetalleItems> getSeccionDetalleItemsList() {
        return seccionDetalleItemsList;
    }

    public void setSeccionDetalleItemsList(List<SeccionDetalleItems> seccionDetalleItemsList) {
        this.seccionDetalleItemsList = seccionDetalleItemsList;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seccionDetallePK != null ? seccionDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeccionDetalle)) {
            return false;
        }
        SeccionDetalle other = (SeccionDetalle) object;
        if ((this.seccionDetallePK == null && other.seccionDetallePK != null) || (this.seccionDetallePK != null && !this.seccionDetallePK.equals(other.seccionDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestor.gestor.SeccionDetalle[ seccionDetallePK=" + seccionDetallePK + " ]";
    }
    
}