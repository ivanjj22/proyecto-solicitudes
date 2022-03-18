package com.empre.persistencia;

import com.empre.persistencia.Recursofisico;
import com.empre.persistencia.Tipo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-02-12T16:39:34")
@StaticMetamodel(Equipo.class)
public class Equipo_ { 

    public static volatile SingularAttribute<Equipo, Tipo> codtipo;
    public static volatile SingularAttribute<Equipo, String> nombre;
    public static volatile SingularAttribute<Equipo, String> numinventario;
    public static volatile SingularAttribute<Equipo, Integer> estado;
    public static volatile SingularAttribute<Equipo, Recursofisico> codrecurso;
    public static volatile SingularAttribute<Equipo, Integer> codequipo;
    public static volatile SingularAttribute<Equipo, String> marca;
    public static volatile SingularAttribute<Equipo, String> serial;

}