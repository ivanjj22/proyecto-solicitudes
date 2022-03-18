/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.empre.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ivan Jaimes
 */
@Entity
@Table(name="equipo")
public class Equipo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codequipo")
    private int codequipo;
    
    
    @JoinColumn(name = "codrecurso",referencedColumnName = "codrecurso")//cual es el campo ,,, y con cual tabla esta referenciado
    @ManyToOne(optional = false)
    private Recursofisico codrecurso;
    
    @JoinColumn(name = "codtipo",referencedColumnName = "codtipo")//cual es el campo ,,, y con cual tabla esta referenciado
    @ManyToOne(optional = false)
    private Tipo codtipo;
    
    @NotNull
    @Size(min = 1,max = 300)
    @Column(name = "nombre")
    private String nombre;
    
    @NotNull
    @Size(min = 1,max = 300)
    @Column(name = "marca")
    private String marca;
    
    @NotNull
    @Size(min = 1,max = 300)
    @Column(name = "serial")
    private String serial;
    
    @NotNull
    @Size(min = 1,max = 300)
    @Column(name = "numinventario")
    private String numinventario;
    
    
    @Column(name = "estado")
    private int estado;
    
    public Equipo() {
    }

    public int getCodequipo() {
        return codequipo;
    }

    public void setCodequipo(int codequipo) {
        this.codequipo = codequipo;
    }

    public Recursofisico getCodrecurso() {
        return codrecurso;
    }

    public void setCodrecurso(Recursofisico codrecurso) {
        this.codrecurso = codrecurso;
    }

    public Tipo getCodtipo() {
        return codtipo;
    }

    public void setCodtipo(Tipo codtipo) {
        this.codtipo = codtipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNuminventario() {
        return numinventario;
    }

    public void setNuminventario(String numinventario) {
        this.numinventario = numinventario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        //return "Roles{" + "codrol=" + codrol + '}';
        return ""+nombre;
    }

    @Override
    public int hashCode(){
        int hash = 31;
        hash = 71*hash + this.codequipo;
        return  hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Equipo)) {
            return false;
        }
       
        return ((Equipo)obj).codequipo==this.codequipo;
    }

    
}
