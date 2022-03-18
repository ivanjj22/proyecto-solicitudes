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
 * @author Riuzaky
 */
@Entity
@Table(name="solicitud")
public class Solicitud implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codsolicitud")
    private int codsolicitud;
    
    @JoinColumn(name = "codequipo",referencedColumnName = "codequipo")//cual es el campo ,,, y con cual tabla esta referenciado
    @ManyToOne(optional = false)
    private Equipo codequipo;
    
    @JoinColumn(name = "codusuario_solicita",referencedColumnName = "codusuario")//cual es el campo ,,, y con cual tabla esta referenciado
    @ManyToOne(optional = false)
    //@Column(name = "codusuario_solicita")
    private Usuario codusuario_solicita;
    
    @JoinColumn(name = "codusuario_asigna",referencedColumnName = "codusuario")//cual es el campo ,,, y con cual tabla esta referenciado
    @ManyToOne(optional = false)
    //@Column(name = "codusuario_asigna")
    private Usuario codusuario_asigna;
    
    @JoinColumn(name = "codusuario_tecnico",referencedColumnName = "codusuario")//cual es el campo ,,, y con cual tabla esta referenciado
    @ManyToOne(optional = false)
    //@Column(name = "codusuario_tecnico")
    private Usuario codusuario_tecnico;
    
    @Column(name = "fechasolicitud")
    @Temporal(TemporalType.DATE)
    private Date fechasolicitud;
    
    @Column(name = "horasolicitud")
    @Temporal(TemporalType.TIME)
    private Date horasolicitud;
    
    @Column(name = "fechaasignacion")
    @Temporal(TemporalType.DATE)
    private Date fechaasignacion;
    
    @Column(name = "horaasignacion")
    @Temporal(TemporalType.TIME)
    private Date horaasignacion;
    
    @Column(name = "estado")
    private int estado;
    
    @NotNull
    @Size(min = 1,max = 800)
    @Column(name = "descripcion")
    private String descripcion;

    public Solicitud() {
    }

    public int getCodsolicitud() {
        return codsolicitud;
    }

    public void setCodsolicitud(int codsolicitud) {
        this.codsolicitud = codsolicitud;
    }

    public Equipo getCodequipo() {
        return codequipo;
    }

    public void setCodequipo(Equipo codequipo) {
        this.codequipo = codequipo;
    }

    public Usuario getCodusuario_solicita() {
        return codusuario_solicita;
    }

    public void setCodusuario_solicita(Usuario codusuario_solicita) {
        this.codusuario_solicita = codusuario_solicita;
    }

    public Usuario getCodusuario_asigna() {
        return codusuario_asigna;
    }

    public void setCodusuario_asigna(Usuario codusuario_asigna) {
        this.codusuario_asigna = codusuario_asigna;
    }

    public Usuario getCodusuario_tecnico() {
        return codusuario_tecnico;
    }

    public void setCodusuario_tecnico(Usuario codusuario_tecnico) {
        this.codusuario_tecnico = codusuario_tecnico;
    }

    public Date getFechasolicitud() {
        return fechasolicitud;
    }

    public void setFechasolicitud(Date fechasolicitud) {
        this.fechasolicitud = fechasolicitud;
    }

    public Date getHorasolicitud() {
        return horasolicitud;
    }

    public void setHorasolicitud(Date horasolicitud) {
        this.horasolicitud = horasolicitud;
    }

    public Date getFechaasignacion() {
        return fechaasignacion;
    }

    public void setFechaasignacion(Date fechaasignacion) {
        this.fechaasignacion = fechaasignacion;
    }

    public Date getHoraasignacion() {
        return horaasignacion;
    }

    public void setHoraasignacion(Date horaasignacion) {
        this.horaasignacion = horaasignacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        //return "Roles{" + "codrol=" + codrol + '}';
        return ""+codsolicitud;
    }

    @Override
    public int hashCode(){
        int hash = 31;
        hash = 71*hash + this.codsolicitud;
        return  hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Solicitud)) {
            return false;
        }
       
        return ((Solicitud)obj).codsolicitud==this.codsolicitud;
    }
    
    
    
}
