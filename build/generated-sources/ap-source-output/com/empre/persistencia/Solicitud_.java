package com.empre.persistencia;

import com.empre.persistencia.Equipo;
import com.empre.persistencia.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-02-12T16:39:34")
@StaticMetamodel(Solicitud.class)
public class Solicitud_ { 

    public static volatile SingularAttribute<Solicitud, Usuario> codusuario_solicita;
    public static volatile SingularAttribute<Solicitud, Date> horaasignacion;
    public static volatile SingularAttribute<Solicitud, Date> fechaasignacion;
    public static volatile SingularAttribute<Solicitud, Integer> estado;
    public static volatile SingularAttribute<Solicitud, String> descripcion;
    public static volatile SingularAttribute<Solicitud, Integer> codsolicitud;
    public static volatile SingularAttribute<Solicitud, Equipo> codequipo;
    public static volatile SingularAttribute<Solicitud, Date> horasolicitud;
    public static volatile SingularAttribute<Solicitud, Date> fechasolicitud;
    public static volatile SingularAttribute<Solicitud, Usuario> codusuario_asigna;
    public static volatile SingularAttribute<Solicitud, Usuario> codusuario_tecnico;

}