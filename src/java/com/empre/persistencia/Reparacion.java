/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.empre.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ivan Jaimes
 */
@Entity
@Table(name="reparacion")
public class Reparacion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codreparacion")
    private int codreparacion;
    
    
    @JoinColumn(name = "codsolicitud",referencedColumnName = "codsolicitud")//cual es el campo ,,, y con cual tabla esta referenciado
    @ManyToOne(optional = false)
    private Solicitud codsolicitud;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    
    @NotNull
    @Size(min = 1,max = 300)
    @Column(name = "solucion")
    private String solucion;
    
    @NotNull
    @Size(min = 1,max = 300)
    @Column(name = "observaciones")
    private String observaciones;
    
    public Reparacion() {
    }

    public int getCodreparacion() {
        return codreparacion;
    }

    public void setCodreparacion(int codreparacion) {
        this.codreparacion = codreparacion;
    }

    public Solicitud getCodsolicitud() {
        return codsolicitud;
    }

    public void setCodsolicitud(Solicitud codsolicitud) {
        this.codsolicitud = codsolicitud;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

   
    
    @Override
    public String toString() {
        //return "Roles{" + "codrol=" + codrol + '}';
        return ""+codreparacion;
    }

    @Override
    public int hashCode(){
        int hash = 31;
        hash = 71*hash + this.codreparacion;
        return  hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Reparacion)) {
            return false;
        }
       
        return ((Reparacion)obj).codreparacion==this.codreparacion;
    }

    
}
