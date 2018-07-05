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
@Table(name = "seccion")
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s")})
public class Seccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SeccionPK seccionPK;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private Boolean activo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso")
    private Double peso;
    @JoinColumn(name = "cod_ciclo", referencedColumnName = "cod_ciclo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ciclo ciclo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seccion")
    private List<SeccionDetalle> seccionDetalleList;

    public Seccion() {
    }

    public Seccion(SeccionPK seccionPK) {
        this.seccionPK = seccionPK;
    }

    public Seccion(String codCiclo, int codSeccion) {
        this.seccionPK = new SeccionPK(codCiclo, codSeccion);
    }

    public SeccionPK getSeccionPK() {
        return seccionPK;
    }

    public void setSeccionPK(SeccionPK seccionPK) {
        this.seccionPK = seccionPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public List<SeccionDetalle> getSeccionDetalleList() {
        return seccionDetalleList;
    }

    public void setSeccionDetalleList(List<SeccionDetalle> seccionDetalleList) {
        this.seccionDetalleList = seccionDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seccionPK != null ? seccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.seccionPK == null && other.seccionPK != null) || (this.seccionPK != null && !this.seccionPK.equals(other.seccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestor.gestor.Seccion[ seccionPK=" + seccionPK + " ]";
    }
    
}
