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
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codusuario")
    private int codusuario;

    @JoinColumn(name = "codrecurso",referencedColumnName = "codrecurso")//cual es el campo ,,, y con cual tabla esta referenciado
    @ManyToOne(optional = false)
    //@Column(name = "codrecurso")
    private Recursofisico codrecurso;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "apellidos")
    private String apellidos;

    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "nombres")
    private String nombres;

    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "tipodocumento")
    private String tipodocumento;

    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "documento")
    private String documento;

    @Column(name = "genero")
    private int genero;

    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "correo")
    private String correo;

    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "telefono")
    private String telefono;

    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "nombreusuario")
    private String nombreusuario;

    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "clave")
    private String clave;

    public Usuario() {
    }

    public int getCodusuario() {
        return codusuario;
    }

    public void setCodusuario(int codusuario) {
        this.codusuario = codusuario;
    }

    public Recursofisico getCodrecurso() {
        return codrecurso;
    }

    public void setCodrecurso(Recursofisico codrecurso) {
        this.codrecurso = codrecurso;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString(){
        return this.nombres;        
    }
    
    @Override
    public int hashCode(){
        int hash=7;
        hash=79*hash+this.codusuario;
        return hash;        
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Usuario)) {
            return false;
        }

        return ((Usuario)obj).codusuario==this.codusuario;
    }
    

}
