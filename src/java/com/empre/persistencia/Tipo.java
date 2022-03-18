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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ivan Jaimes
 */
@Entity
@Table(name="tipo")
public class Tipo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codtipo")
    private int codtipo;
    
    @NotNull
    @Size(min = 1,max = 100)
    @Column(name = "nombretipo")
    private String nombretipo;
    
    
    public Tipo() {
    }

    public int getCodtipo() {
        return codtipo;
    }

    public void setCodtipo(int codtipo) {
        this.codtipo = codtipo;
    }

    public String getNombretipo() {
        return nombretipo;
    }

    public void setNombretipo(String nombretipo) {
        this.nombretipo = nombretipo;
    }

    
     @Override
    public String toString() {
        //return "Roles{" + "codrol=" + codrol + '}';
        return ""+nombretipo;
    }

    @Override
    public int hashCode(){
        int hash = 31;
        hash = 71*hash + this.codtipo;
        return  hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tipo)) {
            return false;
        }
       
        return ((Tipo)obj).codtipo==this.codtipo;
    }
    
    
}
