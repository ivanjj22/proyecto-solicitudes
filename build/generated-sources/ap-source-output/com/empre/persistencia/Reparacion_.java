package com.empre.persistencia;

import com.empre.persistencia.Solicitud;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-02-12T16:39:34")
@StaticMetamodel(Reparacion.class)
public class Reparacion_ { 

    public static volatile SingularAttribute<Reparacion, Date> hora;
    public static volatile SingularAttribute<Reparacion, String> solucion;
    public static volatile SingularAttribute<Reparacion, Integer> codreparacion;
    public static volatile SingularAttribute<Reparacion, Date> fecha;
    public static volatile SingularAttribute<Reparacion, Solicitud> codsolicitud;
    public static volatile SingularAttribute<Reparacion, String> observaciones;

}