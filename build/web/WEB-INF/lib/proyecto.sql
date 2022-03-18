/*==============================================================*/
/* Table: ACCESOS                                               */
/*==============================================================*/
create table ACCESOS (
   CODACCESO            SERIAL               not null,
   CODUSUARIO           INT4                 null,
   FECHAAINGRESO        DATE                 not null,
   HORAINGRESO          TIME                 not null,
   IPOCULTA             VARCHAR(50)          null,
   IPVISIBLE            VARCHAR(50)          null
);

alter table ACCESOS
   add constraint PK_ACCESOS primary key (CODACCESO);

/*==============================================================*/
/* Table: EQUIPO                                                */
/*==============================================================*/
create table EQUIPO (
   CODEQUIPO            SERIAL               not null,
   CODRECURSO           INT4                 null,
   CODTIPO              INT4                 null,
   NOMBRE               VARCHAR(30)          not null,
   MARCA                VARCHAR(30)          not null,
   SERIAL               VARCHAR(100)         not null,
   NUMINVENTARIO        VARCHAR(100)         not null,
   ESTADO               INT2                 not null
);

alter table EQUIPO
   add constraint PK_EQUIPO primary key (CODEQUIPO);

alter table EQUIPO
   add constraint UQ1_EQUIPO_NUMINVENTARIO unique (NUMINVENTARIO);

alter table EQUIPO
   add constraint UQ2_EQUIPO_SERIAL unique (SERIAL);

/*==============================================================*/
/* Table: FUNCIONALIDADES                                       */
/*==============================================================*/
create table FUNCIONALIDADES (
   CODFUNC              SERIAL               not null,
   CODPADRE             INT4                 null,
   NOMBRE               VARCHAR(100)         not null,
   IDENTIFICADOR        VARCHAR(30)          not null,
   ORDEN                INT4                 not null,
   URLPAGINA            VARCHAR(100)         null,
   TARGET               VARCHAR(50)          not null,
   ICONO                VARCHAR(100)         null,
   TIPO                 VARCHAR(10)          not null
);

alter table FUNCIONALIDADES
   add constraint PK_FUNCIONALIDADES primary key (CODFUNC);

/*==============================================================*/
/* Table: PERFIL                                                */
/*==============================================================*/
create table PERFIL (
   CODPERFIL            SERIAL               not null,
   NOMBREPERFIL         VARCHAR(30)          not null
);

alter table PERFIL
   add constraint PK_PERFIL primary key (CODPERFIL);

/*==============================================================*/
/* Table: RECURSOFISICO                                         */
/*==============================================================*/
create table RECURSOFISICO (
   CODRECURSO           SERIAL               not null,
   NOMBRERECURSO        VARCHAR(30)          not null,
   NOMENCLATURA         VARCHAR(8)           not null,
   EDIFICIO             VARCHAR(1)           not null
);

alter table RECURSOFISICO
   add constraint PK_RECURSOFISICO primary key (CODRECURSO);

/*==============================================================*/
/* Table: REL_FUNCTIPOUSU                                       */
/*==============================================================*/
create table REL_FUNCTIPOUSU (
   CONFUNC              INT4                 not null,
   CODPERFIL            INT4                 not null
);

alter table REL_FUNCTIPOUSU
   add constraint PK_REL_FUNCTIPOUSU primary key (CONFUNC, CODPERFIL);

/*==============================================================*/
/* Table: REL_FUNCUSUARIOS                                      */
/*==============================================================*/
create table REL_FUNCUSUARIOS (
   CODUSUARIO           INT4                 not null,
   CODFUNC              INT4                 not null,
   FAVORITO             INT2                 not null
);

alter table REL_FUNCUSUARIOS
   add constraint PK_REL_FUNCUSUARIOS primary key (CODUSUARIO, CODFUNC);

/*==============================================================*/
/* Table: REPARACION                                            */
/*==============================================================*/
create table REPARACION (
   CODREPARACION        SERIAL               not null,
   CODSOLICITUD         INT4                 not null,
   FECHA                DATE                 null,
   HORA                 TIME                 null,
   SOLUCION             VARCHAR(300)         null,
   OBSERVACIONES        VARCHAR(300)         null
);

alter table REPARACION
   add constraint PK_REPARACION primary key (CODREPARACION);

alter table REPARACION
   add constraint UQ_CODSOLICITUD_REPARACION unique (CODSOLICITUD);

/*==============================================================*/
/* Table: SOLICITUD                                             */
/*==============================================================*/
create table SOLICITUD (
   CODSOLICITUD         SERIAL               not null,
   CODEQUIPO            INT4                 not null,
   CODUSUARIO_SOLICITA  INT4                 not null,
   CODUSUARIO_ASIGNA    INT4                 null,
   CODUSUARIO_TECNICO   INT4                 null,
   FECHASOLICITUD       DATE                 not null,
   HORASOLICITUD        TIME                 not null,
   FECHAASIGNACION      DATE                 null,
   HORAASIGNACION       TIME                 null,
   ESTADO               INT2                 not null
      constraint CKC_ESTADO_SOLICITU check (ESTADO IN (1,2)),
   DESCRIPCION          VARCHAR(300)         not null
);

alter table SOLICITUD
   add constraint PK_SOLICITUD primary key (CODSOLICITUD);

/*==============================================================*/
/* Table: TIPO                                                  */
/*==============================================================*/
create table TIPO (
   CODTIPO              SERIAL               not null,
   NOMBRETIPO           VARCHAR(30)          not null
);

alter table TIPO
   add constraint PK_TIPO primary key (CODTIPO);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   CODUSUARIO           SERIAL               not null,
   CODRECURSO           INT4                 null,
   APELLIDOS            VARCHAR(30)          not null,
   NOMBRES              VARCHAR(30)          not null,
   TIPODOCUMENTO        VARCHAR(3)           not null
      constraint CKC_TIPODOCUMENTO_USUARIO check (TIPODOCUMENTO IN ('CC','TI','PA','CE','NIT')),
   DOCUMENTO            VARCHAR(15)          not null,
   GENERO               INT2                 not null
      constraint CKC_GENERO_USUARIO check (GENERO IN (1,2)),
   CORREO               VARCHAR(50)          not null,
   TELEFONO             VARCHAR(20)          not null,
   NOMBREUSUARIO        VARCHAR(30)          not null,
   CLAVE                VARCHAR(255)         not null
);

alter table USUARIO
   add constraint PK_USUARIO primary key (CODUSUARIO);

alter table USUARIO
   add constraint UQ3_USUARIO_NOMBREUSUARIO unique (NOMBREUSUARIO);

/*==============================================================*/
/* Table: USUPERFIL                                             */
/*==============================================================*/
create table USUPERFIL (
   CODPERFIL            INT4                 not null,
   NOMBREUSUARIO        VARCHAR(30)          not null
);

alter table USUPERFIL
   add constraint PK_USUPERFIL primary key (CODPERFIL, NOMBREUSUARIO);

alter table ACCESOS
   add constraint FK_ACCESOS_REFERENCE_USUARIO foreign key (CODUSUARIO)
      references USUARIO (CODUSUARIO)
      on delete cascade on update cascade;

alter table EQUIPO
   add constraint FK_EQUIPO_REFERENCE_RECURSOF foreign key (CODRECURSO)
      references RECURSOFISICO (CODRECURSO)
      on delete cascade on update cascade;

alter table EQUIPO
   add constraint FK_EQUIPO_REFERENCE_TIPO foreign key (CODTIPO)
      references TIPO (CODTIPO)
      on delete cascade on update cascade;

alter table FUNCIONALIDADES
   add constraint FK_FUNCIONA_REFERENCE_FUNCIONA foreign key (CODPADRE)
      references FUNCIONALIDADES (CODFUNC)
      on delete cascade on update cascade;

alter table REL_FUNCTIPOUSU
   add constraint FK_REL_FUNC_REFERENCE_FUNCIONA foreign key (CONFUNC)
      references FUNCIONALIDADES (CODFUNC)
      on delete cascade on update cascade;

alter table REL_FUNCTIPOUSU
   add constraint FK_REL_FUNC_REFERENCE_PERFIL foreign key (CODPERFIL)
      references PERFIL (CODPERFIL)
      on delete cascade on update cascade;

alter table REL_FUNCUSUARIOS
   add constraint FK_REL_FUNC_REFERENCE_USUARIO foreign key (CODUSUARIO)
      references USUARIO (CODUSUARIO)
      on delete cascade on update cascade;

alter table REL_FUNCUSUARIOS
   add constraint FK_REL_FUNC_REFERENCE_FUNCIONA foreign key (CODFUNC)
      references FUNCIONALIDADES (CODFUNC)
      on delete cascade on update cascade;

alter table REPARACION
   add constraint FK_REPARACI_REFERENCE_SOLICITU foreign key (CODSOLICITUD)
      references SOLICITUD (CODSOLICITUD)
      on delete cascade on update cascade;

alter table SOLICITUD
   add constraint FK_SOLICITUD_REFERENCE_USUARIO1 foreign key (CODUSUARIO_SOLICITA)
      references USUARIO (CODUSUARIO)
      on delete cascade on update cascade;

alter table SOLICITUD
   add constraint FK_SOLICITUD_REFERENCE_USUARIO2 foreign key (CODUSUARIO_ASIGNA)
      references USUARIO (CODUSUARIO)
      on delete cascade on update cascade;

alter table SOLICITUD
   add constraint FK_SOLICITUD_REFERENCE_USUARIO3 foreign key (CODUSUARIO_TECNICO)
      references USUARIO (CODUSUARIO)
      on delete cascade on update cascade;

alter table SOLICITUD
   add constraint FK_SOLICITU_REFERENCE_EQUIPO foreign key (CODEQUIPO)
      references EQUIPO (CODEQUIPO)
      on delete cascade on update cascade;

alter table USUARIO
   add constraint FK_USUARIO_REFERENCE_RECURSOF foreign key (CODRECURSO)
      references RECURSOFISICO (CODRECURSO)
      on delete cascade on update cascade;

alter table USUPERFIL
   add constraint FK_USUPERFI_REFERENCE_PERFIL foreign key (CODPERFIL)
      references PERFIL (CODPERFIL)
      on delete cascade on update cascade;

alter table USUPERFIL
   add constraint FK_USUPERFI_REFERENCE_USUARIO foreign key (NOMBREUSUARIO)
      references USUARIO (NOMBREUSUARIO)
      on delete cascade on update cascade;

