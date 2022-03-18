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
@Table(name="recursofisico")
public class Recursofisico implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codrecurso")
    private int codrecurso;
    
    @NotNull
    @Size(min = 1,max = 100)
    @Column(name = "nombrerecurso")
    private String nombrerecurso;
    
    @NotNull
    @Size(min = 1,max = 300)
    @Column(name = "nomenclatura")
    private String nomenclatura;

    @NotNull
    @Size(min = 1,max = 300)
    @Column(name = "edificio")
    private String edificio;
    
    public Recursofisico() {
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public int getCodrecurso() {
        return codrecurso;
    }

    public void setCodrecurso(int codrecurso) {
        this.codrecurso = codrecurso;
    }

    public String getNombrerecurso() {
        return nombrerecurso;
    }

    public void setNombrerecurso(String nombrerecurso) {
        this.nombrerecurso = nombrerecurso;
    }
    
    @Override
    public String toString() {
        //return "Roles{" + "codrol=" + codrol + '}';
        return ""+nombrerecurso;
    }

    @Override
    public int hashCode(){
        int hash = 31;
        hash = 71*hash + this.codrecurso;
        return  hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Recursofisico)) {
            return false;
        }
       
        return ((Recursofisico)obj).codrecurso==this.codrecurso;
    }
    
    
    
    
    
}
