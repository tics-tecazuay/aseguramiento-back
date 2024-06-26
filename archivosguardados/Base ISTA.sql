PGDMP     #        	            {            Vinculacion    15.2    15.2 �    	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    36657    Vinculacion    DATABASE     �   CREATE DATABASE "Vinculacion" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE "Vinculacion";
                postgres    false            �            1259    36658 	   actividad    TABLE     $  CREATE TABLE public.actividad (
    id_actividad bigint NOT NULL,
    descripcion character varying(10000),
    estado character varying(255),
    fecha_fin date,
    fecha_inicio date,
    nombre character varying(255),
    visible boolean,
    id_evidencia bigint,
    usuario_id bigint
);
    DROP TABLE public.actividad;
       public         heap    postgres    false            �            1259    36663    actividad_id_actividad_seq    SEQUENCE     �   CREATE SEQUENCE public.actividad_id_actividad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.actividad_id_actividad_seq;
       public          postgres    false    214                       0    0    actividad_id_actividad_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.actividad_id_actividad_seq OWNED BY public.actividad.id_actividad;
          public          postgres    false    215            �            1259    36664    archivo    TABLE     �   CREATE TABLE public.archivo (
    id_archivo bigint NOT NULL,
    descripcion character varying(10000),
    enlace character varying(255),
    nombre character varying(10000),
    visible boolean,
    id_actividad bigint
);
    DROP TABLE public.archivo;
       public         heap    postgres    false            �            1259    36669    archivo_id_archivo_seq    SEQUENCE        CREATE SEQUENCE public.archivo_id_archivo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.archivo_id_archivo_seq;
       public          postgres    false    216                       0    0    archivo_id_archivo_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.archivo_id_archivo_seq OWNED BY public.archivo.id_archivo;
          public          postgres    false    217            �            1259    36670    asignacion_admin    TABLE     �   CREATE TABLE public.asignacion_admin (
    id_asignacion bigint NOT NULL,
    visible boolean,
    criterio_id_criterio bigint,
    usuario_id bigint,
    id_modelo bigint
);
 $   DROP TABLE public.asignacion_admin;
       public         heap    postgres    false            �            1259    36673 "   asignacion_admin_id_asignacion_seq    SEQUENCE     �   CREATE SEQUENCE public.asignacion_admin_id_asignacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.asignacion_admin_id_asignacion_seq;
       public          postgres    false    218                       0    0 "   asignacion_admin_id_asignacion_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.asignacion_admin_id_asignacion_seq OWNED BY public.asignacion_admin.id_asignacion;
          public          postgres    false    219            �            1259    36674    asignacion_evidencia    TABLE     �   CREATE TABLE public.asignacion_evidencia (
    id_asignacion_evidencia bigint NOT NULL,
    visible boolean NOT NULL,
    evidencia_id_evidencia bigint,
    usuario_id bigint,
    id_modelo bigint
);
 (   DROP TABLE public.asignacion_evidencia;
       public         heap    postgres    false            �            1259    36677 0   asignacion_evidencia_id_asignacion_evidencia_seq    SEQUENCE     �   CREATE SEQUENCE public.asignacion_evidencia_id_asignacion_evidencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 G   DROP SEQUENCE public.asignacion_evidencia_id_asignacion_evidencia_seq;
       public          postgres    false    220                       0    0 0   asignacion_evidencia_id_asignacion_evidencia_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public.asignacion_evidencia_id_asignacion_evidencia_seq OWNED BY public.asignacion_evidencia.id_asignacion_evidencia;
          public          postgres    false    221            �            1259    36678    asignacion_indicador    TABLE     �   CREATE TABLE public.asignacion_indicador (
    id_asignacion_indicador bigint NOT NULL,
    visible boolean,
    indicador_id_indicador bigint,
    modelo_id_modelo bigint
);
 (   DROP TABLE public.asignacion_indicador;
       public         heap    postgres    false            �            1259    36681 0   asignacion_indicador_id_asignacion_indicador_seq    SEQUENCE     �   CREATE SEQUENCE public.asignacion_indicador_id_asignacion_indicador_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 G   DROP SEQUENCE public.asignacion_indicador_id_asignacion_indicador_seq;
       public          postgres    false    222                       0    0 0   asignacion_indicador_id_asignacion_indicador_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public.asignacion_indicador_id_asignacion_indicador_seq OWNED BY public.asignacion_indicador.id_asignacion_indicador;
          public          postgres    false    223            �            1259    36682    criterio    TABLE     �   CREATE TABLE public.criterio (
    id_criterio bigint NOT NULL,
    descripcion character varying(10000),
    nombre character varying(255),
    visible boolean
);
    DROP TABLE public.criterio;
       public         heap    postgres    false            �            1259    36687    criterio_id_criterio_seq    SEQUENCE     �   CREATE SEQUENCE public.criterio_id_criterio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.criterio_id_criterio_seq;
       public          postgres    false    224                       0    0    criterio_id_criterio_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.criterio_id_criterio_seq OWNED BY public.criterio.id_criterio;
          public          postgres    false    225            �            1259    36688    cualitativa    TABLE     �   CREATE TABLE public.cualitativa (
    id_cualitativa bigint NOT NULL,
    escala character varying(255),
    valor double precision,
    visible boolean
);
    DROP TABLE public.cualitativa;
       public         heap    postgres    false            �            1259    36691    cualitativa_id_cualitativa_seq    SEQUENCE     �   CREATE SEQUENCE public.cualitativa_id_cualitativa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.cualitativa_id_cualitativa_seq;
       public          postgres    false    226                       0    0    cualitativa_id_cualitativa_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.cualitativa_id_cualitativa_seq OWNED BY public.cualitativa.id_cualitativa;
          public          postgres    false    227            �            1259    36692    cuantitativa    TABLE     �   CREATE TABLE public.cuantitativa (
    id_cuantitativa bigint NOT NULL,
    abreviatura character varying(255),
    descripcion character varying(10000),
    visible boolean
);
     DROP TABLE public.cuantitativa;
       public         heap    postgres    false            �            1259    36697     cuantitativa_id_cuantitativa_seq    SEQUENCE     �   CREATE SEQUENCE public.cuantitativa_id_cuantitativa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.cuantitativa_id_cuantitativa_seq;
       public          postgres    false    228                       0    0     cuantitativa_id_cuantitativa_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.cuantitativa_id_cuantitativa_seq OWNED BY public.cuantitativa.id_cuantitativa;
          public          postgres    false    229            �            1259    36698    detalle_evaluacion    TABLE       CREATE TABLE public.detalle_evaluacion (
    id_detalle_evaluacion bigint NOT NULL,
    estado boolean,
    fecha timestamp without time zone,
    observacion character varying(255),
    visible boolean,
    evidencia_id_evidencia bigint,
    usuario_id bigint,
    id_modelo bigint
);
 &   DROP TABLE public.detalle_evaluacion;
       public         heap    postgres    false            �            1259    36701 ,   detalle_evaluacion_id_detalle_evaluacion_seq    SEQUENCE     �   CREATE SEQUENCE public.detalle_evaluacion_id_detalle_evaluacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 C   DROP SEQUENCE public.detalle_evaluacion_id_detalle_evaluacion_seq;
       public          postgres    false    230                       0    0 ,   detalle_evaluacion_id_detalle_evaluacion_seq    SEQUENCE OWNED BY     }   ALTER SEQUENCE public.detalle_evaluacion_id_detalle_evaluacion_seq OWNED BY public.detalle_evaluacion.id_detalle_evaluacion;
          public          postgres    false    231            �            1259    36702    encabezado_evaluar    TABLE     �   CREATE TABLE public.encabezado_evaluar (
    id_encabezado_evaluar bigint NOT NULL,
    visible boolean,
    formula_id_formula bigint,
    indicador_id_indicador bigint
);
 &   DROP TABLE public.encabezado_evaluar;
       public         heap    postgres    false            �            1259    36705 ,   encabezado_evaluar_id_encabezado_evaluar_seq    SEQUENCE     �   CREATE SEQUENCE public.encabezado_evaluar_id_encabezado_evaluar_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 C   DROP SEQUENCE public.encabezado_evaluar_id_encabezado_evaluar_seq;
       public          postgres    false    232                       0    0 ,   encabezado_evaluar_id_encabezado_evaluar_seq    SEQUENCE OWNED BY     }   ALTER SEQUENCE public.encabezado_evaluar_id_encabezado_evaluar_seq OWNED BY public.encabezado_evaluar.id_encabezado_evaluar;
          public          postgres    false    233            �            1259    36706    evaluar_cualitativa    TABLE     �   CREATE TABLE public.evaluar_cualitativa (
    id_evaluar_cualitativa bigint NOT NULL,
    visible boolean,
    id_cualitativa bigint,
    id_indicador bigint
);
 '   DROP TABLE public.evaluar_cualitativa;
       public         heap    postgres    false            �            1259    36709 .   evaluar_cualitativa_id_evaluar_cualitativa_seq    SEQUENCE     �   CREATE SEQUENCE public.evaluar_cualitativa_id_evaluar_cualitativa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 E   DROP SEQUENCE public.evaluar_cualitativa_id_evaluar_cualitativa_seq;
       public          postgres    false    234                       0    0 .   evaluar_cualitativa_id_evaluar_cualitativa_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public.evaluar_cualitativa_id_evaluar_cualitativa_seq OWNED BY public.evaluar_cualitativa.id_evaluar_cualitativa;
          public          postgres    false    235            �            1259    36710    evaluar_cuantitativa    TABLE     �   CREATE TABLE public.evaluar_cuantitativa (
    id_evaluar_cuantitativa bigint NOT NULL,
    valor double precision,
    visible boolean,
    cuantitativa_id_cuantitativa bigint,
    encabezado_evaluar_id_encabezado_evaluar bigint
);
 (   DROP TABLE public.evaluar_cuantitativa;
       public         heap    postgres    false            �            1259    36713 0   evaluar_cuantitativa_id_evaluar_cuantitativa_seq    SEQUENCE     �   CREATE SEQUENCE public.evaluar_cuantitativa_id_evaluar_cuantitativa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 G   DROP SEQUENCE public.evaluar_cuantitativa_id_evaluar_cuantitativa_seq;
       public          postgres    false    236                       0    0 0   evaluar_cuantitativa_id_evaluar_cuantitativa_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public.evaluar_cuantitativa_id_evaluar_cuantitativa_seq OWNED BY public.evaluar_cuantitativa.id_evaluar_cuantitativa;
          public          postgres    false    237            �            1259    36714 	   evidencia    TABLE       CREATE TABLE public.evidencia (
    id_evidencia bigint NOT NULL,
    descripcion character varying(10000),
    enlace character varying(255),
    estado character varying(255),
    nombre character varying(10000),
    visible boolean,
    indicador_id_indicador bigint
);
    DROP TABLE public.evidencia;
       public         heap    postgres    false            �            1259    36719    evidencia_id_evidencia_seq    SEQUENCE     �   CREATE SEQUENCE public.evidencia_id_evidencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.evidencia_id_evidencia_seq;
       public          postgres    false    238                       0    0    evidencia_id_evidencia_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.evidencia_id_evidencia_seq OWNED BY public.evidencia.id_evidencia;
          public          postgres    false    239            �            1259    36720    formula    TABLE     �   CREATE TABLE public.formula (
    id_formula bigint NOT NULL,
    descripcion character varying(10000),
    formula character varying(255),
    visible boolean
);
    DROP TABLE public.formula;
       public         heap    postgres    false            �            1259    36725    formula_id_formula_seq    SEQUENCE        CREATE SEQUENCE public.formula_id_formula_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.formula_id_formula_seq;
       public          postgres    false    240                       0    0    formula_id_formula_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.formula_id_formula_seq OWNED BY public.formula.id_formula;
          public          postgres    false    241            �            1259    36726 	   indicador    TABLE     �  CREATE TABLE public.indicador (
    id_indicador bigint NOT NULL,
    descripcion character varying(10000),
    estandar double precision,
    nombre character varying(255),
    peso double precision,
    porc_obtenido double precision,
    porc_utilida_obtenida double precision,
    tipo character varying(255),
    valor_obtenido double precision,
    visible boolean,
    subcriterio_id_subcriterio bigint
);
    DROP TABLE public.indicador;
       public         heap    postgres    false            �            1259    36731    indicador_id_indicador_seq    SEQUENCE     �   CREATE SEQUENCE public.indicador_id_indicador_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.indicador_id_indicador_seq;
       public          postgres    false    242                       0    0    indicador_id_indicador_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.indicador_id_indicador_seq OWNED BY public.indicador.id_indicador;
          public          postgres    false    243            �            1259    36732    modelo    TABLE       CREATE TABLE public.modelo (
    id_modelo bigint NOT NULL,
    fecha_fin timestamp without time zone,
    fecha_final_act timestamp without time zone,
    fecha_inicio timestamp without time zone,
    nombre character varying(255),
    visible boolean,
    usuario_id bigint
);
    DROP TABLE public.modelo;
       public         heap    postgres    false            �            1259    36735    modelo_id_modelo_seq    SEQUENCE     }   CREATE SEQUENCE public.modelo_id_modelo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.modelo_id_modelo_seq;
       public          postgres    false    244                       0    0    modelo_id_modelo_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.modelo_id_modelo_seq OWNED BY public.modelo.id_modelo;
          public          postgres    false    245            �            1259    36736    notificacion    TABLE     �   CREATE TABLE public.notificacion (
    id bigint NOT NULL,
    fecha timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    mensaje character varying(10000),
    rol character varying(255),
    usuario bigint,
    visto boolean
);
     DROP TABLE public.notificacion;
       public         heap    postgres    false            �            1259    36742    notificacion_id_seq    SEQUENCE     |   CREATE SEQUENCE public.notificacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.notificacion_id_seq;
       public          postgres    false    246                       0    0    notificacion_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.notificacion_id_seq OWNED BY public.notificacion.id;
          public          postgres    false    247            �            1259    36743    observacion    TABLE     �   CREATE TABLE public.observacion (
    id_observacion bigint NOT NULL,
    observacion character varying(10000),
    visible boolean NOT NULL,
    actividad_id_actividad bigint,
    usuario_id bigint
);
    DROP TABLE public.observacion;
       public         heap    postgres    false            �            1259    36748    observacion_id_observacion_seq    SEQUENCE     �   CREATE SEQUENCE public.observacion_id_observacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.observacion_id_observacion_seq;
       public          postgres    false    248                       0    0    observacion_id_observacion_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.observacion_id_observacion_seq OWNED BY public.observacion.id_observacion;
          public          postgres    false    249            �            1259    36749    persona    TABLE     �  CREATE TABLE public.persona (
    id_persona bigint NOT NULL,
    cedula character varying(255),
    celular character varying(255),
    correo character varying(255),
    direccion character varying(255),
    primer_apellido character varying(255),
    primer_nombre character varying(255),
    segundo_apellido character varying(255),
    segundo_nombre character varying(255),
    visible boolean
);
    DROP TABLE public.persona;
       public         heap    postgres    false            �            1259    36754    persona_id_persona_seq    SEQUENCE        CREATE SEQUENCE public.persona_id_persona_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.persona_id_persona_seq;
       public          postgres    false    250                       0    0    persona_id_persona_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.persona_id_persona_seq OWNED BY public.persona.id_persona;
          public          postgres    false    251            �            1259    36755    ponderacion    TABLE     R  CREATE TABLE public.ponderacion (
    id_ponderacion bigint NOT NULL,
    fecha date,
    peso double precision,
    porc_obtenido double precision,
    porc_utilida_obtenida double precision,
    valor_obtenido double precision,
    visible boolean,
    indicador_id_indicador bigint,
    modelo_id_modelo bigint,
    contador bigint
);
    DROP TABLE public.ponderacion;
       public         heap    postgres    false            �            1259    36758    ponderacion_id_ponderacion_seq    SEQUENCE     �   CREATE SEQUENCE public.ponderacion_id_ponderacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.ponderacion_id_ponderacion_seq;
       public          postgres    false    252                        0    0    ponderacion_id_ponderacion_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.ponderacion_id_ponderacion_seq OWNED BY public.ponderacion.id_ponderacion;
          public          postgres    false    253            �            1259    36759    reporte    TABLE     �   CREATE TABLE public.reporte (
    id_reporte bigint NOT NULL,
    enlace character varying(255),
    fecha timestamp without time zone,
    visible boolean NOT NULL,
    modelo_id_modelo bigint
);
    DROP TABLE public.reporte;
       public         heap    postgres    false            �            1259    36762    reporte_id_reporte_seq    SEQUENCE        CREATE SEQUENCE public.reporte_id_reporte_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.reporte_id_reporte_seq;
       public          postgres    false    254            !           0    0    reporte_id_reporte_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.reporte_id_reporte_seq OWNED BY public.reporte.id_reporte;
          public          postgres    false    255                        1259    36763    roles    TABLE     _   CREATE TABLE public.roles (
    rolid bigint NOT NULL,
    rolnombre character varying(255)
);
    DROP TABLE public.roles;
       public         heap    postgres    false                       1259    36766    subcriterio    TABLE     �   CREATE TABLE public.subcriterio (
    id_subcriterio bigint NOT NULL,
    descripcion character varying(10000),
    nombre character varying(255),
    visible boolean,
    id_criterio bigint
);
    DROP TABLE public.subcriterio;
       public         heap    postgres    false                       1259    36771    subcriterio_id_subcriterio_seq    SEQUENCE     �   CREATE SEQUENCE public.subcriterio_id_subcriterio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.subcriterio_id_subcriterio_seq;
       public          postgres    false    257            "           0    0    subcriterio_id_subcriterio_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.subcriterio_id_subcriterio_seq OWNED BY public.subcriterio.id_subcriterio;
          public          postgres    false    258                       1259    36772 
   usuariorol    TABLE     r   CREATE TABLE public.usuariorol (
    usuariorolid bigint NOT NULL,
    rol_rolid bigint,
    usuario_id bigint
);
    DROP TABLE public.usuariorol;
       public         heap    postgres    false                       1259    36775    usuariorol_usuariorolid_seq    SEQUENCE     �   CREATE SEQUENCE public.usuariorol_usuariorolid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.usuariorol_usuariorolid_seq;
       public          postgres    false    259            #           0    0    usuariorol_usuariorolid_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.usuariorol_usuariorolid_seq OWNED BY public.usuariorol.usuariorolid;
          public          postgres    false    260                       1259    36776    usuarios    TABLE     �   CREATE TABLE public.usuarios (
    id bigint NOT NULL,
    enabled boolean NOT NULL,
    password character varying(255),
    username character varying(255),
    visible boolean,
    persona_id_persona bigint
);
    DROP TABLE public.usuarios;
       public         heap    postgres    false                       1259    36781    usuarios_id_seq    SEQUENCE     x   CREATE SEQUENCE public.usuarios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.usuarios_id_seq;
       public          postgres    false    261            $           0    0    usuarios_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;
          public          postgres    false    262            �           2604    36782    actividad id_actividad    DEFAULT     �   ALTER TABLE ONLY public.actividad ALTER COLUMN id_actividad SET DEFAULT nextval('public.actividad_id_actividad_seq'::regclass);
 E   ALTER TABLE public.actividad ALTER COLUMN id_actividad DROP DEFAULT;
       public          postgres    false    215    214            �           2604    36783    archivo id_archivo    DEFAULT     x   ALTER TABLE ONLY public.archivo ALTER COLUMN id_archivo SET DEFAULT nextval('public.archivo_id_archivo_seq'::regclass);
 A   ALTER TABLE public.archivo ALTER COLUMN id_archivo DROP DEFAULT;
       public          postgres    false    217    216            �           2604    36784    asignacion_admin id_asignacion    DEFAULT     �   ALTER TABLE ONLY public.asignacion_admin ALTER COLUMN id_asignacion SET DEFAULT nextval('public.asignacion_admin_id_asignacion_seq'::regclass);
 M   ALTER TABLE public.asignacion_admin ALTER COLUMN id_asignacion DROP DEFAULT;
       public          postgres    false    219    218            �           2604    36785 ,   asignacion_evidencia id_asignacion_evidencia    DEFAULT     �   ALTER TABLE ONLY public.asignacion_evidencia ALTER COLUMN id_asignacion_evidencia SET DEFAULT nextval('public.asignacion_evidencia_id_asignacion_evidencia_seq'::regclass);
 [   ALTER TABLE public.asignacion_evidencia ALTER COLUMN id_asignacion_evidencia DROP DEFAULT;
       public          postgres    false    221    220            �           2604    36786 ,   asignacion_indicador id_asignacion_indicador    DEFAULT     �   ALTER TABLE ONLY public.asignacion_indicador ALTER COLUMN id_asignacion_indicador SET DEFAULT nextval('public.asignacion_indicador_id_asignacion_indicador_seq'::regclass);
 [   ALTER TABLE public.asignacion_indicador ALTER COLUMN id_asignacion_indicador DROP DEFAULT;
       public          postgres    false    223    222            �           2604    36787    criterio id_criterio    DEFAULT     |   ALTER TABLE ONLY public.criterio ALTER COLUMN id_criterio SET DEFAULT nextval('public.criterio_id_criterio_seq'::regclass);
 C   ALTER TABLE public.criterio ALTER COLUMN id_criterio DROP DEFAULT;
       public          postgres    false    225    224            �           2604    36788    cualitativa id_cualitativa    DEFAULT     �   ALTER TABLE ONLY public.cualitativa ALTER COLUMN id_cualitativa SET DEFAULT nextval('public.cualitativa_id_cualitativa_seq'::regclass);
 I   ALTER TABLE public.cualitativa ALTER COLUMN id_cualitativa DROP DEFAULT;
       public          postgres    false    227    226            �           2604    36789    cuantitativa id_cuantitativa    DEFAULT     �   ALTER TABLE ONLY public.cuantitativa ALTER COLUMN id_cuantitativa SET DEFAULT nextval('public.cuantitativa_id_cuantitativa_seq'::regclass);
 K   ALTER TABLE public.cuantitativa ALTER COLUMN id_cuantitativa DROP DEFAULT;
       public          postgres    false    229    228            �           2604    36790 (   detalle_evaluacion id_detalle_evaluacion    DEFAULT     �   ALTER TABLE ONLY public.detalle_evaluacion ALTER COLUMN id_detalle_evaluacion SET DEFAULT nextval('public.detalle_evaluacion_id_detalle_evaluacion_seq'::regclass);
 W   ALTER TABLE public.detalle_evaluacion ALTER COLUMN id_detalle_evaluacion DROP DEFAULT;
       public          postgres    false    231    230            �           2604    36791 (   encabezado_evaluar id_encabezado_evaluar    DEFAULT     �   ALTER TABLE ONLY public.encabezado_evaluar ALTER COLUMN id_encabezado_evaluar SET DEFAULT nextval('public.encabezado_evaluar_id_encabezado_evaluar_seq'::regclass);
 W   ALTER TABLE public.encabezado_evaluar ALTER COLUMN id_encabezado_evaluar DROP DEFAULT;
       public          postgres    false    233    232            �           2604    36792 *   evaluar_cualitativa id_evaluar_cualitativa    DEFAULT     �   ALTER TABLE ONLY public.evaluar_cualitativa ALTER COLUMN id_evaluar_cualitativa SET DEFAULT nextval('public.evaluar_cualitativa_id_evaluar_cualitativa_seq'::regclass);
 Y   ALTER TABLE public.evaluar_cualitativa ALTER COLUMN id_evaluar_cualitativa DROP DEFAULT;
       public          postgres    false    235    234            �           2604    36793 ,   evaluar_cuantitativa id_evaluar_cuantitativa    DEFAULT     �   ALTER TABLE ONLY public.evaluar_cuantitativa ALTER COLUMN id_evaluar_cuantitativa SET DEFAULT nextval('public.evaluar_cuantitativa_id_evaluar_cuantitativa_seq'::regclass);
 [   ALTER TABLE public.evaluar_cuantitativa ALTER COLUMN id_evaluar_cuantitativa DROP DEFAULT;
       public          postgres    false    237    236            �           2604    36794    evidencia id_evidencia    DEFAULT     �   ALTER TABLE ONLY public.evidencia ALTER COLUMN id_evidencia SET DEFAULT nextval('public.evidencia_id_evidencia_seq'::regclass);
 E   ALTER TABLE public.evidencia ALTER COLUMN id_evidencia DROP DEFAULT;
       public          postgres    false    239    238            �           2604    36795    formula id_formula    DEFAULT     x   ALTER TABLE ONLY public.formula ALTER COLUMN id_formula SET DEFAULT nextval('public.formula_id_formula_seq'::regclass);
 A   ALTER TABLE public.formula ALTER COLUMN id_formula DROP DEFAULT;
       public          postgres    false    241    240            �           2604    36796    indicador id_indicador    DEFAULT     �   ALTER TABLE ONLY public.indicador ALTER COLUMN id_indicador SET DEFAULT nextval('public.indicador_id_indicador_seq'::regclass);
 E   ALTER TABLE public.indicador ALTER COLUMN id_indicador DROP DEFAULT;
       public          postgres    false    243    242            �           2604    36797    modelo id_modelo    DEFAULT     t   ALTER TABLE ONLY public.modelo ALTER COLUMN id_modelo SET DEFAULT nextval('public.modelo_id_modelo_seq'::regclass);
 ?   ALTER TABLE public.modelo ALTER COLUMN id_modelo DROP DEFAULT;
       public          postgres    false    245    244            �           2604    36798    notificacion id    DEFAULT     r   ALTER TABLE ONLY public.notificacion ALTER COLUMN id SET DEFAULT nextval('public.notificacion_id_seq'::regclass);
 >   ALTER TABLE public.notificacion ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    247    246            �           2604    36799    observacion id_observacion    DEFAULT     �   ALTER TABLE ONLY public.observacion ALTER COLUMN id_observacion SET DEFAULT nextval('public.observacion_id_observacion_seq'::regclass);
 I   ALTER TABLE public.observacion ALTER COLUMN id_observacion DROP DEFAULT;
       public          postgres    false    249    248            �           2604    36800    persona id_persona    DEFAULT     x   ALTER TABLE ONLY public.persona ALTER COLUMN id_persona SET DEFAULT nextval('public.persona_id_persona_seq'::regclass);
 A   ALTER TABLE public.persona ALTER COLUMN id_persona DROP DEFAULT;
       public          postgres    false    251    250            �           2604    36801    ponderacion id_ponderacion    DEFAULT     �   ALTER TABLE ONLY public.ponderacion ALTER COLUMN id_ponderacion SET DEFAULT nextval('public.ponderacion_id_ponderacion_seq'::regclass);
 I   ALTER TABLE public.ponderacion ALTER COLUMN id_ponderacion DROP DEFAULT;
       public          postgres    false    253    252            �           2604    36802    reporte id_reporte    DEFAULT     x   ALTER TABLE ONLY public.reporte ALTER COLUMN id_reporte SET DEFAULT nextval('public.reporte_id_reporte_seq'::regclass);
 A   ALTER TABLE public.reporte ALTER COLUMN id_reporte DROP DEFAULT;
       public          postgres    false    255    254            �           2604    36803    subcriterio id_subcriterio    DEFAULT     �   ALTER TABLE ONLY public.subcriterio ALTER COLUMN id_subcriterio SET DEFAULT nextval('public.subcriterio_id_subcriterio_seq'::regclass);
 I   ALTER TABLE public.subcriterio ALTER COLUMN id_subcriterio DROP DEFAULT;
       public          postgres    false    258    257            �           2604    36804    usuariorol usuariorolid    DEFAULT     �   ALTER TABLE ONLY public.usuariorol ALTER COLUMN usuariorolid SET DEFAULT nextval('public.usuariorol_usuariorolid_seq'::regclass);
 F   ALTER TABLE public.usuariorol ALTER COLUMN usuariorolid DROP DEFAULT;
       public          postgres    false    260    259            �           2604    36805    usuarios id    DEFAULT     j   ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);
 :   ALTER TABLE public.usuarios ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    262    261            �          0    36658 	   actividad 
   TABLE DATA           �   COPY public.actividad (id_actividad, descripcion, estado, fecha_fin, fecha_inicio, nombre, visible, id_evidencia, usuario_id) FROM stdin;
    public          postgres    false    214   �      �          0    36664    archivo 
   TABLE DATA           a   COPY public.archivo (id_archivo, descripcion, enlace, nombre, visible, id_actividad) FROM stdin;
    public          postgres    false    216   �      �          0    36670    asignacion_admin 
   TABLE DATA           o   COPY public.asignacion_admin (id_asignacion, visible, criterio_id_criterio, usuario_id, id_modelo) FROM stdin;
    public          postgres    false    218   �      �          0    36674    asignacion_evidencia 
   TABLE DATA              COPY public.asignacion_evidencia (id_asignacion_evidencia, visible, evidencia_id_evidencia, usuario_id, id_modelo) FROM stdin;
    public          postgres    false    220   9      �          0    36678    asignacion_indicador 
   TABLE DATA           z   COPY public.asignacion_indicador (id_asignacion_indicador, visible, indicador_id_indicador, modelo_id_modelo) FROM stdin;
    public          postgres    false    222   �      �          0    36682    criterio 
   TABLE DATA           M   COPY public.criterio (id_criterio, descripcion, nombre, visible) FROM stdin;
    public          postgres    false    224   i      �          0    36688    cualitativa 
   TABLE DATA           M   COPY public.cualitativa (id_cualitativa, escala, valor, visible) FROM stdin;
    public          postgres    false    226         �          0    36692    cuantitativa 
   TABLE DATA           Z   COPY public.cuantitativa (id_cuantitativa, abreviatura, descripcion, visible) FROM stdin;
    public          postgres    false    228   s      �          0    36698    detalle_evaluacion 
   TABLE DATA           �   COPY public.detalle_evaluacion (id_detalle_evaluacion, estado, fecha, observacion, visible, evidencia_id_evidencia, usuario_id, id_modelo) FROM stdin;
    public          postgres    false    230   �      �          0    36702    encabezado_evaluar 
   TABLE DATA           x   COPY public.encabezado_evaluar (id_encabezado_evaluar, visible, formula_id_formula, indicador_id_indicador) FROM stdin;
    public          postgres    false    232   �      �          0    36706    evaluar_cualitativa 
   TABLE DATA           l   COPY public.evaluar_cualitativa (id_evaluar_cualitativa, visible, id_cualitativa, id_indicador) FROM stdin;
    public          postgres    false    234   :      �          0    36710    evaluar_cuantitativa 
   TABLE DATA           �   COPY public.evaluar_cuantitativa (id_evaluar_cuantitativa, valor, visible, cuantitativa_id_cuantitativa, encabezado_evaluar_id_encabezado_evaluar) FROM stdin;
    public          postgres    false    236   E      �          0    36714 	   evidencia 
   TABLE DATA           w   COPY public.evidencia (id_evidencia, descripcion, enlace, estado, nombre, visible, indicador_id_indicador) FROM stdin;
    public          postgres    false    238   \      �          0    36720    formula 
   TABLE DATA           L   COPY public.formula (id_formula, descripcion, formula, visible) FROM stdin;
    public          postgres    false    240   �E      �          0    36726 	   indicador 
   TABLE DATA           �   COPY public.indicador (id_indicador, descripcion, estandar, nombre, peso, porc_obtenido, porc_utilida_obtenida, tipo, valor_obtenido, visible, subcriterio_id_subcriterio) FROM stdin;
    public          postgres    false    242   [G      �          0    36732    modelo 
   TABLE DATA           r   COPY public.modelo (id_modelo, fecha_fin, fecha_final_act, fecha_inicio, nombre, visible, usuario_id) FROM stdin;
    public          postgres    false    244   Hb      �          0    36736    notificacion 
   TABLE DATA           O   COPY public.notificacion (id, fecha, mensaje, rol, usuario, visto) FROM stdin;
    public          postgres    false    246   �b      �          0    36743    observacion 
   TABLE DATA           o   COPY public.observacion (id_observacion, observacion, visible, actividad_id_actividad, usuario_id) FROM stdin;
    public          postgres    false    248   i      �          0    36749    persona 
   TABLE DATA           �   COPY public.persona (id_persona, cedula, celular, correo, direccion, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, visible) FROM stdin;
    public          postgres    false    250   �i      �          0    36755    ponderacion 
   TABLE DATA           �   COPY public.ponderacion (id_ponderacion, fecha, peso, porc_obtenido, porc_utilida_obtenida, valor_obtenido, visible, indicador_id_indicador, modelo_id_modelo, contador) FROM stdin;
    public          postgres    false    252   Xm      �          0    36759    reporte 
   TABLE DATA           W   COPY public.reporte (id_reporte, enlace, fecha, visible, modelo_id_modelo) FROM stdin;
    public          postgres    false    254   �o                 0    36763    roles 
   TABLE DATA           1   COPY public.roles (rolid, rolnombre) FROM stdin;
    public          postgres    false    256   �o                0    36766    subcriterio 
   TABLE DATA           `   COPY public.subcriterio (id_subcriterio, descripcion, nombre, visible, id_criterio) FROM stdin;
    public          postgres    false    257   ;p                0    36772 
   usuariorol 
   TABLE DATA           I   COPY public.usuariorol (usuariorolid, rol_rolid, usuario_id) FROM stdin;
    public          postgres    false    259   �y                0    36776    usuarios 
   TABLE DATA           `   COPY public.usuarios (id, enabled, password, username, visible, persona_id_persona) FROM stdin;
    public          postgres    false    261   Gz      %           0    0    actividad_id_actividad_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.actividad_id_actividad_seq', 6, true);
          public          postgres    false    215            &           0    0    archivo_id_archivo_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.archivo_id_archivo_seq', 10, true);
          public          postgres    false    217            '           0    0 "   asignacion_admin_id_asignacion_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.asignacion_admin_id_asignacion_seq', 22, true);
          public          postgres    false    219            (           0    0 0   asignacion_evidencia_id_asignacion_evidencia_seq    SEQUENCE SET     _   SELECT pg_catalog.setval('public.asignacion_evidencia_id_asignacion_evidencia_seq', 11, true);
          public          postgres    false    221            )           0    0 0   asignacion_indicador_id_asignacion_indicador_seq    SEQUENCE SET     `   SELECT pg_catalog.setval('public.asignacion_indicador_id_asignacion_indicador_seq', 224, true);
          public          postgres    false    223            *           0    0    criterio_id_criterio_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.criterio_id_criterio_seq', 8, true);
          public          postgres    false    225            +           0    0    cualitativa_id_cualitativa_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.cualitativa_id_cualitativa_seq', 4, true);
          public          postgres    false    227            ,           0    0     cuantitativa_id_cuantitativa_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.cuantitativa_id_cuantitativa_seq', 31, true);
          public          postgres    false    229            -           0    0 ,   detalle_evaluacion_id_detalle_evaluacion_seq    SEQUENCE SET     Z   SELECT pg_catalog.setval('public.detalle_evaluacion_id_detalle_evaluacion_seq', 7, true);
          public          postgres    false    231            .           0    0 ,   encabezado_evaluar_id_encabezado_evaluar_seq    SEQUENCE SET     [   SELECT pg_catalog.setval('public.encabezado_evaluar_id_encabezado_evaluar_seq', 16, true);
          public          postgres    false    233            /           0    0 .   evaluar_cualitativa_id_evaluar_cualitativa_seq    SEQUENCE SET     ]   SELECT pg_catalog.setval('public.evaluar_cualitativa_id_evaluar_cualitativa_seq', 67, true);
          public          postgres    false    235            0           0    0 0   evaluar_cuantitativa_id_evaluar_cuantitativa_seq    SEQUENCE SET     _   SELECT pg_catalog.setval('public.evaluar_cuantitativa_id_evaluar_cuantitativa_seq', 51, true);
          public          postgres    false    237            1           0    0    evidencia_id_evidencia_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.evidencia_id_evidencia_seq', 221, true);
          public          postgres    false    239            2           0    0    formula_id_formula_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.formula_id_formula_seq', 16, true);
          public          postgres    false    241            3           0    0    indicador_id_indicador_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.indicador_id_indicador_seq', 44, true);
          public          postgres    false    243            4           0    0    modelo_id_modelo_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.modelo_id_modelo_seq', 11, true);
          public          postgres    false    245            5           0    0    notificacion_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.notificacion_id_seq', 55, true);
          public          postgres    false    247            6           0    0    observacion_id_observacion_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.observacion_id_observacion_seq', 7, true);
          public          postgres    false    249            7           0    0    persona_id_persona_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.persona_id_persona_seq', 22, true);
          public          postgres    false    251            8           0    0    ponderacion_id_ponderacion_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.ponderacion_id_ponderacion_seq', 210, true);
          public          postgres    false    253            9           0    0    reporte_id_reporte_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.reporte_id_reporte_seq', 1, false);
          public          postgres    false    255            :           0    0    subcriterio_id_subcriterio_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.subcriterio_id_subcriterio_seq', 16, true);
          public          postgres    false    258            ;           0    0    usuariorol_usuariorolid_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.usuariorol_usuariorolid_seq', 23, true);
          public          postgres    false    260            <           0    0    usuarios_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.usuarios_id_seq', 23, true);
          public          postgres    false    262            �           2606    36807    actividad actividad_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.actividad
    ADD CONSTRAINT actividad_pkey PRIMARY KEY (id_actividad);
 B   ALTER TABLE ONLY public.actividad DROP CONSTRAINT actividad_pkey;
       public            postgres    false    214            �           2606    36809    archivo archivo_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.archivo
    ADD CONSTRAINT archivo_pkey PRIMARY KEY (id_archivo);
 >   ALTER TABLE ONLY public.archivo DROP CONSTRAINT archivo_pkey;
       public            postgres    false    216            �           2606    36811 &   asignacion_admin asignacion_admin_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY public.asignacion_admin
    ADD CONSTRAINT asignacion_admin_pkey PRIMARY KEY (id_asignacion);
 P   ALTER TABLE ONLY public.asignacion_admin DROP CONSTRAINT asignacion_admin_pkey;
       public            postgres    false    218            �           2606    36813 .   asignacion_evidencia asignacion_evidencia_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.asignacion_evidencia
    ADD CONSTRAINT asignacion_evidencia_pkey PRIMARY KEY (id_asignacion_evidencia);
 X   ALTER TABLE ONLY public.asignacion_evidencia DROP CONSTRAINT asignacion_evidencia_pkey;
       public            postgres    false    220                        2606    36815 .   asignacion_indicador asignacion_indicador_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.asignacion_indicador
    ADD CONSTRAINT asignacion_indicador_pkey PRIMARY KEY (id_asignacion_indicador);
 X   ALTER TABLE ONLY public.asignacion_indicador DROP CONSTRAINT asignacion_indicador_pkey;
       public            postgres    false    222                       2606    36817    criterio criterio_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.criterio
    ADD CONSTRAINT criterio_pkey PRIMARY KEY (id_criterio);
 @   ALTER TABLE ONLY public.criterio DROP CONSTRAINT criterio_pkey;
       public            postgres    false    224                       2606    36819    cualitativa cualitativa_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.cualitativa
    ADD CONSTRAINT cualitativa_pkey PRIMARY KEY (id_cualitativa);
 F   ALTER TABLE ONLY public.cualitativa DROP CONSTRAINT cualitativa_pkey;
       public            postgres    false    226                       2606    36821    cuantitativa cuantitativa_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.cuantitativa
    ADD CONSTRAINT cuantitativa_pkey PRIMARY KEY (id_cuantitativa);
 H   ALTER TABLE ONLY public.cuantitativa DROP CONSTRAINT cuantitativa_pkey;
       public            postgres    false    228                       2606    36823 *   detalle_evaluacion detalle_evaluacion_pkey 
   CONSTRAINT     {   ALTER TABLE ONLY public.detalle_evaluacion
    ADD CONSTRAINT detalle_evaluacion_pkey PRIMARY KEY (id_detalle_evaluacion);
 T   ALTER TABLE ONLY public.detalle_evaluacion DROP CONSTRAINT detalle_evaluacion_pkey;
       public            postgres    false    230            
           2606    36825 *   encabezado_evaluar encabezado_evaluar_pkey 
   CONSTRAINT     {   ALTER TABLE ONLY public.encabezado_evaluar
    ADD CONSTRAINT encabezado_evaluar_pkey PRIMARY KEY (id_encabezado_evaluar);
 T   ALTER TABLE ONLY public.encabezado_evaluar DROP CONSTRAINT encabezado_evaluar_pkey;
       public            postgres    false    232                       2606    36827 ,   evaluar_cualitativa evaluar_cualitativa_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public.evaluar_cualitativa
    ADD CONSTRAINT evaluar_cualitativa_pkey PRIMARY KEY (id_evaluar_cualitativa);
 V   ALTER TABLE ONLY public.evaluar_cualitativa DROP CONSTRAINT evaluar_cualitativa_pkey;
       public            postgres    false    234                       2606    36829 .   evaluar_cuantitativa evaluar_cuantitativa_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.evaluar_cuantitativa
    ADD CONSTRAINT evaluar_cuantitativa_pkey PRIMARY KEY (id_evaluar_cuantitativa);
 X   ALTER TABLE ONLY public.evaluar_cuantitativa DROP CONSTRAINT evaluar_cuantitativa_pkey;
       public            postgres    false    236                       2606    36831    evidencia evidencia_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.evidencia
    ADD CONSTRAINT evidencia_pkey PRIMARY KEY (id_evidencia);
 B   ALTER TABLE ONLY public.evidencia DROP CONSTRAINT evidencia_pkey;
       public            postgres    false    238                       2606    36833    formula formula_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.formula
    ADD CONSTRAINT formula_pkey PRIMARY KEY (id_formula);
 >   ALTER TABLE ONLY public.formula DROP CONSTRAINT formula_pkey;
       public            postgres    false    240                       2606    36835    indicador indicador_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.indicador
    ADD CONSTRAINT indicador_pkey PRIMARY KEY (id_indicador);
 B   ALTER TABLE ONLY public.indicador DROP CONSTRAINT indicador_pkey;
       public            postgres    false    242                       2606    36837    modelo modelo_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT modelo_pkey PRIMARY KEY (id_modelo);
 <   ALTER TABLE ONLY public.modelo DROP CONSTRAINT modelo_pkey;
       public            postgres    false    244                       2606    36839    notificacion notificacion_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.notificacion
    ADD CONSTRAINT notificacion_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.notificacion DROP CONSTRAINT notificacion_pkey;
       public            postgres    false    246                       2606    36841    observacion observacion_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.observacion
    ADD CONSTRAINT observacion_pkey PRIMARY KEY (id_observacion);
 F   ALTER TABLE ONLY public.observacion DROP CONSTRAINT observacion_pkey;
       public            postgres    false    248                       2606    36843    persona persona_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id_persona);
 >   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_pkey;
       public            postgres    false    250                        2606    36845    ponderacion ponderacion_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.ponderacion
    ADD CONSTRAINT ponderacion_pkey PRIMARY KEY (id_ponderacion);
 F   ALTER TABLE ONLY public.ponderacion DROP CONSTRAINT ponderacion_pkey;
       public            postgres    false    252            "           2606    36847    reporte reporte_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT reporte_pkey PRIMARY KEY (id_reporte);
 >   ALTER TABLE ONLY public.reporte DROP CONSTRAINT reporte_pkey;
       public            postgres    false    254            $           2606    36849    roles roles_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (rolid);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    256            &           2606    36851    subcriterio subcriterio_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.subcriterio
    ADD CONSTRAINT subcriterio_pkey PRIMARY KEY (id_subcriterio);
 F   ALTER TABLE ONLY public.subcriterio DROP CONSTRAINT subcriterio_pkey;
       public            postgres    false    257                       2606    36853 #   persona ukcm8asjmty9arx6nqo43mfl0i6 
   CONSTRAINT     `   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT ukcm8asjmty9arx6nqo43mfl0i6 UNIQUE (cedula);
 M   ALTER TABLE ONLY public.persona DROP CONSTRAINT ukcm8asjmty9arx6nqo43mfl0i6;
       public            postgres    false    250            �           2606    36855 ,   asignacion_admin ukibrwx0f9aenp1hyvvwwrkhkha 
   CONSTRAINT     �   ALTER TABLE ONLY public.asignacion_admin
    ADD CONSTRAINT ukibrwx0f9aenp1hyvvwwrkhkha UNIQUE (usuario_id, criterio_id_criterio);
 V   ALTER TABLE ONLY public.asignacion_admin DROP CONSTRAINT ukibrwx0f9aenp1hyvvwwrkhkha;
       public            postgres    false    218    218            (           2606    36857    usuariorol usuariorol_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.usuariorol
    ADD CONSTRAINT usuariorol_pkey PRIMARY KEY (usuariorolid);
 D   ALTER TABLE ONLY public.usuariorol DROP CONSTRAINT usuariorol_pkey;
       public            postgres    false    259            *           2606    36859    usuarios usuarios_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT usuarios_pkey;
       public            postgres    false    261            6           2606    36860 .   encabezado_evaluar fk1tbe3tswra2b59apmnnv1yiic    FK CONSTRAINT     �   ALTER TABLE ONLY public.encabezado_evaluar
    ADD CONSTRAINT fk1tbe3tswra2b59apmnnv1yiic FOREIGN KEY (formula_id_formula) REFERENCES public.formula(id_formula);
 X   ALTER TABLE ONLY public.encabezado_evaluar DROP CONSTRAINT fk1tbe3tswra2b59apmnnv1yiic;
       public          postgres    false    240    232    3346            :           2606    36865 0   evaluar_cuantitativa fk5ldgiq7r9592dxeg2o0yrig1o    FK CONSTRAINT     �   ALTER TABLE ONLY public.evaluar_cuantitativa
    ADD CONSTRAINT fk5ldgiq7r9592dxeg2o0yrig1o FOREIGN KEY (encabezado_evaluar_id_encabezado_evaluar) REFERENCES public.encabezado_evaluar(id_encabezado_evaluar);
 Z   ALTER TABLE ONLY public.evaluar_cuantitativa DROP CONSTRAINT fk5ldgiq7r9592dxeg2o0yrig1o;
       public          postgres    false    232    236    3338            ?           2606    36870 '   observacion fk5rs9gdk62sjiwvprw5v308fvb    FK CONSTRAINT     �   ALTER TABLE ONLY public.observacion
    ADD CONSTRAINT fk5rs9gdk62sjiwvprw5v308fvb FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id);
 Q   ALTER TABLE ONLY public.observacion DROP CONSTRAINT fk5rs9gdk62sjiwvprw5v308fvb;
       public          postgres    false    261    3370    248            @           2606    36875 '   observacion fk5xpa20a96xrumiwjdaxsv2nvs    FK CONSTRAINT     �   ALTER TABLE ONLY public.observacion
    ADD CONSTRAINT fk5xpa20a96xrumiwjdaxsv2nvs FOREIGN KEY (actividad_id_actividad) REFERENCES public.actividad(id_actividad);
 Q   ALTER TABLE ONLY public.observacion DROP CONSTRAINT fk5xpa20a96xrumiwjdaxsv2nvs;
       public          postgres    false    214    3318    248            +           2606    36880 %   actividad fk8b4e0dknoqnala1pq3r83mp9u    FK CONSTRAINT     �   ALTER TABLE ONLY public.actividad
    ADD CONSTRAINT fk8b4e0dknoqnala1pq3r83mp9u FOREIGN KEY (id_evidencia) REFERENCES public.evidencia(id_evidencia);
 O   ALTER TABLE ONLY public.actividad DROP CONSTRAINT fk8b4e0dknoqnala1pq3r83mp9u;
       public          postgres    false    214    238    3344            E           2606    36885 &   usuariorol fk93omfx2hj2asw60aghij55eu2    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuariorol
    ADD CONSTRAINT fk93omfx2hj2asw60aghij55eu2 FOREIGN KEY (rol_rolid) REFERENCES public.roles(rolid);
 P   ALTER TABLE ONLY public.usuariorol DROP CONSTRAINT fk93omfx2hj2asw60aghij55eu2;
       public          postgres    false    256    259    3364            .           2606    36890 ,   asignacion_admin fk9i6ddwukhylrc90i5irsmvpvs    FK CONSTRAINT     �   ALTER TABLE ONLY public.asignacion_admin
    ADD CONSTRAINT fk9i6ddwukhylrc90i5irsmvpvs FOREIGN KEY (criterio_id_criterio) REFERENCES public.criterio(id_criterio);
 V   ALTER TABLE ONLY public.asignacion_admin DROP CONSTRAINT fk9i6ddwukhylrc90i5irsmvpvs;
       public          postgres    false    218    3330    224            C           2606    36895 #   reporte fkavx929e89ubn4p9bs42dt77rn    FK CONSTRAINT     �   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT fkavx929e89ubn4p9bs42dt77rn FOREIGN KEY (modelo_id_modelo) REFERENCES public.modelo(id_modelo);
 M   ALTER TABLE ONLY public.reporte DROP CONSTRAINT fkavx929e89ubn4p9bs42dt77rn;
       public          postgres    false    254    3350    244            4           2606    36900 .   detalle_evaluacion fkb13e7pdsc8072fdvh3wopo12u    FK CONSTRAINT     �   ALTER TABLE ONLY public.detalle_evaluacion
    ADD CONSTRAINT fkb13e7pdsc8072fdvh3wopo12u FOREIGN KEY (evidencia_id_evidencia) REFERENCES public.evidencia(id_evidencia);
 X   ALTER TABLE ONLY public.detalle_evaluacion DROP CONSTRAINT fkb13e7pdsc8072fdvh3wopo12u;
       public          postgres    false    230    238    3344            /           2606    36905 ,   asignacion_admin fkbjoerxqw2kcpm9aknpobfnmun    FK CONSTRAINT     �   ALTER TABLE ONLY public.asignacion_admin
    ADD CONSTRAINT fkbjoerxqw2kcpm9aknpobfnmun FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id);
 V   ALTER TABLE ONLY public.asignacion_admin DROP CONSTRAINT fkbjoerxqw2kcpm9aknpobfnmun;
       public          postgres    false    218    261    3370            -           2606    36910 #   archivo fkby7mylqckox95g0yd6e04rptg    FK CONSTRAINT     �   ALTER TABLE ONLY public.archivo
    ADD CONSTRAINT fkby7mylqckox95g0yd6e04rptg FOREIGN KEY (id_actividad) REFERENCES public.actividad(id_actividad);
 M   ALTER TABLE ONLY public.archivo DROP CONSTRAINT fkby7mylqckox95g0yd6e04rptg;
       public          postgres    false    214    3318    216            ,           2606    36915 %   actividad fkc4ash1cpuvnayfpkksu6p21lk    FK CONSTRAINT     �   ALTER TABLE ONLY public.actividad
    ADD CONSTRAINT fkc4ash1cpuvnayfpkksu6p21lk FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id);
 O   ALTER TABLE ONLY public.actividad DROP CONSTRAINT fkc4ash1cpuvnayfpkksu6p21lk;
       public          postgres    false    214    261    3370            5           2606    36920 .   detalle_evaluacion fkda3djq3byuqu5x2654yfyqj16    FK CONSTRAINT     �   ALTER TABLE ONLY public.detalle_evaluacion
    ADD CONSTRAINT fkda3djq3byuqu5x2654yfyqj16 FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id);
 X   ALTER TABLE ONLY public.detalle_evaluacion DROP CONSTRAINT fkda3djq3byuqu5x2654yfyqj16;
       public          postgres    false    261    230    3370            2           2606    36925 0   asignacion_indicador fkdks7lj3plden60xbksr71cc6l    FK CONSTRAINT     �   ALTER TABLE ONLY public.asignacion_indicador
    ADD CONSTRAINT fkdks7lj3plden60xbksr71cc6l FOREIGN KEY (indicador_id_indicador) REFERENCES public.indicador(id_indicador);
 Z   ALTER TABLE ONLY public.asignacion_indicador DROP CONSTRAINT fkdks7lj3plden60xbksr71cc6l;
       public          postgres    false    242    3348    222            0           2606    36930 0   asignacion_evidencia fkf0l7sjf6pueyivficlviuf4q9    FK CONSTRAINT     �   ALTER TABLE ONLY public.asignacion_evidencia
    ADD CONSTRAINT fkf0l7sjf6pueyivficlviuf4q9 FOREIGN KEY (evidencia_id_evidencia) REFERENCES public.evidencia(id_evidencia);
 Z   ALTER TABLE ONLY public.asignacion_evidencia DROP CONSTRAINT fkf0l7sjf6pueyivficlviuf4q9;
       public          postgres    false    238    220    3344            F           2606    36935 &   usuariorol fkfa4kgvbxpaqn2h3qaajrhx0fr    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuariorol
    ADD CONSTRAINT fkfa4kgvbxpaqn2h3qaajrhx0fr FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id);
 P   ALTER TABLE ONLY public.usuariorol DROP CONSTRAINT fkfa4kgvbxpaqn2h3qaajrhx0fr;
       public          postgres    false    259    3370    261            7           2606    36940 .   encabezado_evaluar fkfmph77nlc2qj9yyc7xdjc3qfw    FK CONSTRAINT     �   ALTER TABLE ONLY public.encabezado_evaluar
    ADD CONSTRAINT fkfmph77nlc2qj9yyc7xdjc3qfw FOREIGN KEY (indicador_id_indicador) REFERENCES public.indicador(id_indicador);
 X   ALTER TABLE ONLY public.encabezado_evaluar DROP CONSTRAINT fkfmph77nlc2qj9yyc7xdjc3qfw;
       public          postgres    false    3348    232    242            ;           2606    36945 0   evaluar_cuantitativa fkjm6mta8w5eoq1bpaiy5mffid5    FK CONSTRAINT     �   ALTER TABLE ONLY public.evaluar_cuantitativa
    ADD CONSTRAINT fkjm6mta8w5eoq1bpaiy5mffid5 FOREIGN KEY (cuantitativa_id_cuantitativa) REFERENCES public.cuantitativa(id_cuantitativa);
 Z   ALTER TABLE ONLY public.evaluar_cuantitativa DROP CONSTRAINT fkjm6mta8w5eoq1bpaiy5mffid5;
       public          postgres    false    228    3334    236            3           2606    36950 0   asignacion_indicador fkky8ux2vbvf9wpxy81k5vjmbgu    FK CONSTRAINT     �   ALTER TABLE ONLY public.asignacion_indicador
    ADD CONSTRAINT fkky8ux2vbvf9wpxy81k5vjmbgu FOREIGN KEY (modelo_id_modelo) REFERENCES public.modelo(id_modelo);
 Z   ALTER TABLE ONLY public.asignacion_indicador DROP CONSTRAINT fkky8ux2vbvf9wpxy81k5vjmbgu;
       public          postgres    false    3350    244    222            <           2606    36955 %   evidencia fklmjj8r673bdib02gh74ekwhbe    FK CONSTRAINT     �   ALTER TABLE ONLY public.evidencia
    ADD CONSTRAINT fklmjj8r673bdib02gh74ekwhbe FOREIGN KEY (indicador_id_indicador) REFERENCES public.indicador(id_indicador);
 O   ALTER TABLE ONLY public.evidencia DROP CONSTRAINT fklmjj8r673bdib02gh74ekwhbe;
       public          postgres    false    238    242    3348            =           2606    36960 %   indicador fkner6vcrafmvve69v7mxcw0xmx    FK CONSTRAINT     �   ALTER TABLE ONLY public.indicador
    ADD CONSTRAINT fkner6vcrafmvve69v7mxcw0xmx FOREIGN KEY (subcriterio_id_subcriterio) REFERENCES public.subcriterio(id_subcriterio);
 O   ALTER TABLE ONLY public.indicador DROP CONSTRAINT fkner6vcrafmvve69v7mxcw0xmx;
       public          postgres    false    257    3366    242            >           2606    36965 "   modelo fkpv4lluo4s48wu7kxdycfsoasb    FK CONSTRAINT     �   ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT fkpv4lluo4s48wu7kxdycfsoasb FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id);
 L   ALTER TABLE ONLY public.modelo DROP CONSTRAINT fkpv4lluo4s48wu7kxdycfsoasb;
       public          postgres    false    3370    244    261            G           2606    36970 $   usuarios fkq1l7b7bice5uysvjoo457towq    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fkq1l7b7bice5uysvjoo457towq FOREIGN KEY (persona_id_persona) REFERENCES public.persona(id_persona);
 N   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT fkq1l7b7bice5uysvjoo457towq;
       public          postgres    false    3356    261    250            D           2606    36975 '   subcriterio fkqfonct3pookifdedmp6ejtgq9    FK CONSTRAINT     �   ALTER TABLE ONLY public.subcriterio
    ADD CONSTRAINT fkqfonct3pookifdedmp6ejtgq9 FOREIGN KEY (id_criterio) REFERENCES public.criterio(id_criterio);
 Q   ALTER TABLE ONLY public.subcriterio DROP CONSTRAINT fkqfonct3pookifdedmp6ejtgq9;
       public          postgres    false    257    224    3330            8           2606    36980 /   evaluar_cualitativa fkqk2jb7k4jrdy67agnjt993slq    FK CONSTRAINT     �   ALTER TABLE ONLY public.evaluar_cualitativa
    ADD CONSTRAINT fkqk2jb7k4jrdy67agnjt993slq FOREIGN KEY (id_cualitativa) REFERENCES public.cualitativa(id_cualitativa);
 Y   ALTER TABLE ONLY public.evaluar_cualitativa DROP CONSTRAINT fkqk2jb7k4jrdy67agnjt993slq;
       public          postgres    false    226    3332    234            A           2606    36985 '   ponderacion fkr3pagb02mlidv21hbrcm1oov8    FK CONSTRAINT     �   ALTER TABLE ONLY public.ponderacion
    ADD CONSTRAINT fkr3pagb02mlidv21hbrcm1oov8 FOREIGN KEY (indicador_id_indicador) REFERENCES public.indicador(id_indicador);
 Q   ALTER TABLE ONLY public.ponderacion DROP CONSTRAINT fkr3pagb02mlidv21hbrcm1oov8;
       public          postgres    false    3348    242    252            B           2606    36990 '   ponderacion fkswh44v6tp7ca1kp50pvipikfb    FK CONSTRAINT     �   ALTER TABLE ONLY public.ponderacion
    ADD CONSTRAINT fkswh44v6tp7ca1kp50pvipikfb FOREIGN KEY (modelo_id_modelo) REFERENCES public.modelo(id_modelo);
 Q   ALTER TABLE ONLY public.ponderacion DROP CONSTRAINT fkswh44v6tp7ca1kp50pvipikfb;
       public          postgres    false    252    244    3350            1           2606    36995 0   asignacion_evidencia fkt1o88qbyy76v5eg22unv964fs    FK CONSTRAINT     �   ALTER TABLE ONLY public.asignacion_evidencia
    ADD CONSTRAINT fkt1o88qbyy76v5eg22unv964fs FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id);
 Z   ALTER TABLE ONLY public.asignacion_evidencia DROP CONSTRAINT fkt1o88qbyy76v5eg22unv964fs;
       public          postgres    false    261    220    3370            9           2606    37000 /   evaluar_cualitativa fkta8n4ixf6ncj299120496tkqm    FK CONSTRAINT     �   ALTER TABLE ONLY public.evaluar_cualitativa
    ADD CONSTRAINT fkta8n4ixf6ncj299120496tkqm FOREIGN KEY (id_indicador) REFERENCES public.indicador(id_indicador);
 Y   ALTER TABLE ONLY public.evaluar_cualitativa DROP CONSTRAINT fkta8n4ixf6ncj299120496tkqm;
       public          postgres    false    242    3348    234            �   2  x�e��n� ���)��i���ä�i�]\p;&�J��v��dm֪����ۍ�!��g��f�a���jQ�U��9\֭���;���N4�Rs�S6��#��R��O�{���XF+^�H[�#��D�@vn�`&@���4�n�ں����u-�x��)��#��GQ��XɃs��LsH�h��<B�9�㽒Z�J�Q�?�>re��h����2:�hm���?�xM��̴���e7�xFM��#l�e�ب���	�k����h,���[
��K�H6���|�|�����<      �   �   x��P��0���ȂK���V�`cBB!IiP[W�T|>i�	���g�Ξ�i�6���CNT�ð@%�/4���t*��
�=ao(��4��5�Q!��x�\�wK^K���Q���:=�V�� �X$��x�x�~4j��7�?E��%��2t�������O`���O���E~Lo��i?�@u-ME-���a�����ڜo���>`����      �   i   x�%���0���
��f�N�w'��*8�C���°���{�Ë�t��HD1��Y������6ڱd��@O�1;���P=���g���1�xF�0γ�/�ϩ�      �   S   x�5��� ��0U �%:�N�������:ٱ�0�|�l��jFb��cp�{�Mi��IgS���!sY�
/یf�"�KD>��      �   �   x�-�A!��c*찻�/}F���1�
�l^�뾀��4ޅ�q��-\Ƨ�,܍9u��x�%IO��ק��I��̴�})=�OIq��E�r��Y�Ye�f���f��m��i�7�]�Ź�v��_s{��7@mq��W킼���G{�N��=�k��Y�\�{��e/��N�?���3����T'      �   �  x�uU�n7]����Q���J��@� R�E���2�!�|U~�_�^�e;?�s�=�va��\��y\�.֋��B*�ʋJ�B:��VX�#�?��ݳ�Q�P{�c���h����$V�QH�O��:�=E%P�$�DK�R�OJ�!ېAʷJvO[-ɏā?�(�)�������Zں{��WN����Q"����@��:�U�:d>�GʇXiB6�Nx����Gt��q��6��*��()�=��&.��C�mȸ�<7��9[���L��qT�
�SR{jx@�bs��]���j�~���1�λ�E����ΠDcA 8gon����E���0"���Yӣ���� ,1�2Rp�V8�~���a����O��Gb�v�%��C1��2�����'R*�K]��m��
��I݋� &F���d*t��g	v_I��0�X��j�eG5�R:�� nt�)˄š��ea=4������`��s%��N8Cֹ[TIC�P����`�lE/�0.�˛�d�����~�[O��O�js��L�S@�Z�c=K�������]�K�4�i�� ��L�4�e��S��x�ÏE��ę�A8d<�~k]�� U�5�[],�s_���
���J-w��4�-���}n�d��aB���QnQ��5<0��~��6;�G�c�a)� NO������6���<.n׫��f��m@����_xQf2:X'L��Q.q��^��^T6�e�US�������.�r��$i���!��FK����B�3b�RH�����u2dO���Z۠$����t���Y��Fz�=�a\b���o#.�����ou|ݣ4y�+�o����36��Ջ ����E���-�Q4�}���1 J��3����P�ݢ�&	d��^��9zelcƶ{�X�g���>�;r�vf��L�U��8N>���/�;�o	��=��Kͺ~W�����\^�X��y��W׳�d�^-+1��ru|~-��K��}����Nj�бh!J<G1�X�NRRm� 	Y�wa� {��ㄼ>M�j. �%H21� :z06��'��v�W@��/�w�~��j)�YM���5�{S`�k���ɭ��\���6W�E��6�x���lz�]�a�yI�(�tǚ[UI��q|uu�/*Dt�      �   K   x�3�v�vst����4�,�2�tu��E�1�3�s�;�cH��L8]\�<�=]�B\9�1z\\\ ��      �   u  x��V�n�8=+_�cA�iztg��06BN{S�îD�i��79��C��\�c���ˉ�-�S���8����d��2����rVJ�A5�6�����B7�5zV�F��H�8P;;W�u��/A	��TFHk��N}	�ьDB����g���N�R�������I>�䱅�P���#�JB���cO��8?;�_�=˦W��\Um��}��CiI*�o���lE�̵$�j���
+�L�.�2��,���Sٽ�Ϝsݣ��tŔze"{�7m��eP�S�]}����HM�'���K�%�D��#w,�~�#8��� =F�Ȧ�<���g%���t�J�9��rRKm_�0V���|#h��\_��d��9X�`��Їg���`j-�ҋ�-�����3Q��Ǡ@'R�|c�Xz]���<�W1�T�V\p�#�-:�Ւ�@��6	m{��9(�W���w�Z���V�N�ʲ�ř �٤�	�]I.��d��~��=E�Ԩ^tS�CO�8'*m��y��x	{���L�k���MtӤ�4Ƽ���f���!���`b�����1��m�8ƢŶ��o����-qK%sA<S���Ā3�3�5����57%	
�J,9�x��l2nw/�{����� ���:��G�������`�����pp�����8�m�Av��KU�^3\L{�� =j�U1��}����u�G1X��N�O`�z+^��Ⱦ�J;S��v��x��	*�2��'ܷ���P�gn��W���q�������n��z����۲T>Z#o���4�,��W����E]П~��t�
���`���Jy�?�0&��y���=�C ���K�I,DH
���
��&M|�c�5�M���AF��r�N�C��.4^�����?�J��3������s�QFK�w�%^�!�N5��$�)�����T�9fѼ��&��j���Ev;�3[�;᷏Y�ǧ�M�o��.�L��ɐt0�1oG�o��s�K���������ȻDʌ�nc��p�!]�ҷ�F�f;1[zI1�?101*V`$�
���z����5PD�g��@1�B�aטg�?6��\�8�O仲E�R�����eSɬ�u|tt�\�)�      �   �   x���=n�0��:�.`A$������ʩ�D��CN�Ƞ�CG�#��4�㠍$NȉY������7���x�k�k2	�2�w�h@�s>r�X+v��î�4c�����SֹN5Oui��K����.�(٨(D��6���O��/mlP.��AD�B$����֩�7p[��#˵L_��l[��4*!���W�      �   b   x�ͻ1���1X�l/W��?�����A�t���L�XAA�+X��6tx��������х������	o�>TO$jPݭ�*���K�=$�&6�      �   �   x�5�ˍ 1C�q1��M�˖��kfNx��id�-Y�����&����jK���8U}9n��	�-"-��l�M���H|���C���t;��(�U��z5��sC��4[xM�r���+���f��(L'Ҍ���V%�E�Jvʂ�n��p�{J��C�a�pn_ԉ���ŃxY��*�Ǹ��Lya�#�{Z����� ��1A��)�f�˹ra���H��	v��(I@^�
�(��<s���,kc%      �     x�ERɑ1{C0[��˼7�ͿV�c�%zR�?��
���+��K�haMh'9XO��d�����e;�Z$�|���I@��n���$�|��0��2$��(C�q�K�wD�܊��4��;uьJ�Χ��	��:�>�x�@�9=`�_	`'���p.�׍�ykk���2��;��RQ,ٲ=��S�A�tUh�Z���Y|}+��6�,ד�[]��� �V2����D��2�+Iz]K^�>9��3���aF      �      x��=˒�F�g�+pl�2�f�{椑zf�G��q�F̥TSЀ  zL��~��:�0ᛯ���W��`�I�,��b�H�����̚��|U�UQE��\�+]E�΢J��i���(�O�\�"��bVj�0S�y�JWu�n~ͣ"R��(�D%���Vj�o��NR�K�3͊Y���eQ��:O�(fTRT�h/��W��u�d�\���e�o�%8�b��w ����GވZfi�V�}�y���ͳ�|?ZjX w��X���'��r��)ٯ�������J/���<��72���e���tt{��5<]3 �*4���T�x�R�i�!����p�b���F7�ir���w	x]�����|:�����4�y�*�Q�Ȑ��"�M��w.���=R�
~X ��^Ĭ�1*v��W�����"�Z�	����D�b���uN��Dz?fi�gMǗ� G�)B,c�R�L�#T���E�s��~��n� �7���5����1F]}E��9���( u	g3�1�ǻ:�V�~p �t�g�Z�={�
�-�D�6���<Bt��H�g�3}����U�t�U��7t:J
`�pt`tf���z�m����jIHgyu�Vq]�k���$�Kl�#�_70���$����4
�jɘ(% Yb�ml!���Ej6��Dߥ*Wڼ�I^����e�N���ѿ�,dPs|d�y������?�Ԑ��Z���k��d*U�Z䍜�Y��p�� v�p/q2�N�:bb��r�{��V(�]�i+��0$�N� <�KD7*���y�ʯ�������8	�/@��2���&�Z�a_,��ےP�-׹�:�y2��9�-\�0� ��S��h�d$<`E,�j��jO��z6z�¨�K�:֕@�$���ƻ�%h75C��D�`�(K�9rg����Wx6���= �_�P%��oŦZT�Jb�͇�m��kF_�,<����{��J�%VU������.���6fh�4�@�f�I����i��K��4��;�F��;�ttd͔n�o�V�x��V�[t{V�^�Զ ��$�߭�E�}��	d� A�Ã��%�Q�V5��@FE�r b��Ӱ��G�����Ri�1����u���Pc���ڳ/4DH��POТ�x��r1��bk�%�DYz�ŝ��0[�ɬ�D��TL��;I�JU8ŀ]���S�\voǇ]4Bz&[��	A�8ٕ�YQ�E�2���]u�qmM�|���^����m����2�ҌXSK��1���D��&�K�(։�p���yw�ivW���$f�NxS`�$f����b*/�Rg"��P���
���i���不�P-NB��<>ﬁ���y�~k��|fʑ�æ�#d�GTm]į'�Q���i�\�f�APp�6�^�=�%F���VvvP����b�2�D3������K����H��pU�r�l�i��xJXA� z,�(bU�w��4\H�s�iQ��+g�¢*@;B4YLX�4�+�g��1���: o~=�wez��z�j�Д��������Dt��dt�[���k�Z��5�����7�u�����>A����4�OWNi��v�BG�v�Gm�"�˕c�Z����W%kg�	�k&V�
wd�Z��� S���#)�,�ͧ8uQ<����'"ݥ #�g�&/l1aH�-��y��!8���.���XV�1������D�/���hzyE�������i�bH��j!�5/�|�ޓ/J'd���9X�(࿭X!m��K r�9k�: j�os�9���惃�e>7���/��eb��zƐ��
��Dx�����'����@��S�8G�Be��k΃1ڈ�� s�Lq��~��L�xٛ�s3���eN�d� �N*�� �>Ѱ�����;
0D�_�4��5�2���秝�� '_�����]�eי*] @���^�9��\�^ʑ	drG'���E�e�lB�`�uG�k�]YҞ\3/1qӐ�w6>�0�B��m	�*�]�c���z�ژ���s<��<��k�	`񕱪錑 ��*�B�Յ�',�+OqTM��X�~x����c�8�-V�!6O����T�bl�Lp�($�4�ض��YJ�N���� ����3�ԺF>��ͯhx����"�|O�=�.�>,��q�?�B\�F�։�o~�0&Y��Ow��
����렎5�%e����4�||ڎ�P��y�r<�obm�9�J	u�7a��4���_��Krw�QE��#.�@�rN*N��"$*xC >�L�0e��s�������Y
)���dd�hK:[q�gZ6V�h�e:O)T����h-!���-����oټ]����\#YuI�C����h��h�`��hp�.�� 9�#���n�� ��C�6�Nkx1vPI��B�%]`0H�%�F��mk#6�RIB��-<��+1��ٮ�cf�i�k8X�i���"GvU��]�c���%.V�Z���V����53G��	(�/EI�+,�HQ��!�_��U���v1>;�t�̘.'���8�o�\�ԃ��c4��t�d����T�(%�D,���X%6-�r����1w������b�x���	��(y�9P"Qk���� ]g��j��gA���l�Ls?�UBA=@AN�z�., �PṌ�dFH���* Hūs%I�5:��>����qp������^:�t1��|��Z��v�d�֚�����G����V� HǷ���;"?�2k�&��9��Z*�_��4�����$�{$}o~1LO�*��{on�t�BP@�";-�*@s��Z�.%n��C���l�fn�i,���I��d�Px�@	&6�
<����8�S>��s��څL0�H�'{8��d�?Y̸�;�m	"�R� �[����u����_w��*I��@���D7m�e�/8Ј�[�tfO�&^��aE�
�wU�H
�;Q�߸8K\cK:��#Y@��y��̰X<���P���=7Lv͑m���}�,,
��+��Ey��������!�h�Ф�9��s��P�s��sEG}��u_z��-���g������GK���N;ϳ�Z��8��>�Β\N>b��̉c	O=|k�X�U"�+�I��xһjw;���Gs/s��t�*k��^c�a؉B������^�PWI�Jܐ.�Ÿ�A%���ל �O'�>���yY/)�s)�`��y�#��t׶T��\���T�e��(鬘o>�ߨapof;a��
Lt~�+����0t�@xl�A���u�y���+WP5l+���A��?��B��kJ�.G?öC73������:�@0���H0s2���:����o�'�=��3 «�yOܠQ:D)��9stN���n�"�"5��|���J{
!a
���;�*��d즺����^�/�,�)��<%HD��G����`�s�����ƚ0K��F�F�O��ieTs�8誥�(�	T	��g0;V��$>��-lLN����2��:=t!3�RU�O�тD	��R�tX}+�!���u)^P��:�@l/���C�
v�4�%��y<x"�r ��ao�J6HN[�.jЂ���{�>��'�L�*5���ͧ
�)��"�x ��6�ĉ�-Q\ԡ#��[�Qx<��j{_�?�nr/�a
�y�9�ܲ���1�bYu���M��v�����y�Do/�9��p2�J+c�n�n&�)0�Y�`.n�Ц��{����{����j�W���ڥyя�����ݎ4�qݯ�5*cޣ��,������kDG`ݲ�l7@����b\��K�[O�t|y�e~�/��(���\�~�Z�*8J(y����a}/�w�/v�ܣ�*�o��H�\=#{�5DqM��Ft��!���[}t�P%A(���͕+�7�lU�
�p{A{2�<y p�����7�z�i�^�r�WR/SH��=�n�1��q]�k��e����&�r��w:�U�2n�#���m����d�şc6�#���Ԧ�Y    ��`�w�.CT�~W9W#9�#��[U.��|�t����S`�q��|/<$�v�|QؔZ�£U��9|�U���C ���98��|�����dH+�Z���`��@, ��>�}��Y��6X�uv:�<����d�a�+7ڢ_+�m1�L�,����E�/Bl�z�l�l��(%���,+�E&A���������//����g�b	7�뜚��SL�sWR�ab6*`�&lt�5#m���wu�0 &-�VH�I:6��4Fc�#���hH��s����ʶ��ve�k�j�
Lʗr���
���S�@/���M�y,Q�h5���+,��${:��A7F�x���v��Oõ^�殎;7���t`TLջ7�¦�����M;v�W��N�F/@D�����xT�U�5*�|���0�����������qYbjN�=�\���%�x���.�Qtq�c�0��g��#l�����f1[7�T���� �����Mܽ��^up�Y��o�D���+�A�
	�gV�p\g�*9��h@~�\��Q���F�9�F!8����D�5��f���+l�I��$��b�.ͥ��D L �0��|K|�sŤ+a�!��l�ʋriV¬�eX���6$�НD܀�q0e�.���$z+�����?sjj�qH.tL&rI��<��R�9~�!�;\c��{��U"�FI�,,Sm��\7e�'֙}�
���Aţ�"&i�+�A~��K�L�;���1�EC����b|ua�b�-��U(<Z�. 4b��\��E��c�P���z��6.r����� k���-��	[�����0H�8�Һ����rr\�O��=WK�卓-��(�1u��>��,�ei��ڏ)N/�WW^r�-uPР�(��:_0/�L��>��6:��4C���ǎD�.�K�I�����9jV�E�i�R@$3kK
g&���W��^��p�����5�^n4��hޛ��6��LT�
�ƫ��������ӵ��f^V5�t�c^�Zj'�+�xxP�	��J�{�os=�����򫁈�*o����Ӱ}w��6���6|\��A�y�:趷����U� E�1PNu?�
_x��G�('S����m����P�Ů����3u�nf�'����<)s�ۛu���~g�����=̬$���E������4�썒l�.�X�
m�g4~y�)��ҩ�xN�!o%�zX�^�+pK��M���3(m<�$�� u2�r�?pr?�z|}�1�ߋ��88�yQ���d��ĺ[��_�
Ņi��
�G��\0���*Kˈ���#��?���eW ��(��J�� �GI�7pZ] �,�"�ӯRi�h��z�f9����~�a��^Q�s7�ƙ�s��ky��C��0�`�K.vyOWP�F����V3�jwpw���̾��?�Iڝ������=!���)!����c����	�F���>k缰o+^���z�L/��>dJ���>ofG�E��"# *�v��Yֻ�c<�/F߁ǚˉ$�(e�5��͡�E�^�£�_a����+��m����m���ѷ%sENx�X��@7͙xte���+�(�Ϥ�g�;|����\���f��ǔ��q�D��׾�@�+�3>��`תo�Ø
;W�h���V�%�:.��s烈N���*��$X!I"��I��Q�����Ȃ,c�Ⱦ��`ܸa ���#� ���W dJᖟߡ�����T�A|��69����8�|<���"��081�倎no^<�����-8�)�3��j8���� ��/4��-;�� =}T\�P�н�>{�����"� �?���e2\���;\v�}y�Y�A���q�l���8��yAzc�CJ��	�X�>���m_-�$�؍��"{s0���r;��N��`��Hh��8%�"A��6�&�)To�S��1uy�C��,��g'�xx2�"�ˣ��2���#�ˍ)�L49��~o>�t���<�Ϙl��t��S�ۦ|�^']'(��9cG	�hݘ��Y�#ں*1]U+�b�s��Ki�n;���P]#z� R:�CB(rUW���������J�=�}~]5��C8���Rt��b���8yg�� ��52(rr>��7��4
�Y�p��;B��x�}�:�8G B����Ga�KJ���a�'�鼨���AŚ�5��������g��Z3��5���˝x	�uTct�V�����v�z�Q�O���j�.+�y���ddR���"ke�Fl��K���Q�O�# +h�4O�����ʧw�\��C��4L��{kbIB�����r��ZBc3������s��+}d�m&��U-�ʔ��uQ�2�P�s�m�u#��&��##�����L0�-�����/�\���#qV�l0���m8�aUs��O�cD�����'����HW쏨�Ƈw+�ƍRNu(7-�y��ы�C�5:���1��y����?�$R�˿�����_6LK Lg	��=��P�'�}N4T�pm;���*������JTH���Ѣ�0�1�|�L�Z��t����|��zu{h�Su�?�Q�t�b�h5�\:�}%�V�r��:8ˌiq�Yf"!-z�T 4���X<� |�R��༒}��t�U��1#�%�5�j�H��{�꯲����+�&5@^*������7�̉�����Ts�-3�4�0i8��{@/Գ����JrM1���u���F��jƐ���c�?�������3n� �T$3�N
٬�쁇������s���w9�.�hJӷh�`6QL��̡�=��p�]��b�W˜����6I�mg&M�d�!��a3 H��;�p��KX���.�6Bed×��ģ�����)YFz>�w]�`wB���1�\�EшW��#k�^�^���)��b�Օyixm?֏��
�G����y@�\�4���}���)��ڜ�ՠ�v�p������.d{P_
Jk$�u��bh��.��'�]Č�:;�	�y���2�1��Uf�x�b�]��i���ʛb��������(b1��F��\0���#[7NѡZ�8�L��U3!`�s����6/�؍T��p��C3g�z78N$"�(�]�i��/m>�1�ě�_��
{݅W;�� ��g���vA�Jm������:�N�$ML��:�;�]<s�
���"�& 0��%���_�s�@�"޺�>q%���(l?A�����M�������$��J�� (sy��b��?|.!���F���zٿïw���K0��`�2t��B���b�0�������H�0��{t���%�;���T��ܘ�Օv ^��"]h��zq����B1-�t�W#�>����&��� ߝ!U�Z$�6�e@o�c�E'o�.�%�Zs3�7�0��X�@�pr���xДp�[�ig4�$0,:m�>�Q����`���;Z۷�N4�Ρ`���(�K�|�9�Ζ�>"�}�b~�2AjA0�f���H��A�3ɗ�����W�Dl��oW�C
(���y�ϖ�e�Y�~��F���� ��v@����l*KMf7i?�8��AG�s�Yg���P��m[�^����)�KK��������]�=FFq�"q��wxf�(7�]o�������$�[��$iu��@���I�w�
�����g�O�Z%��?���"�n��1`���Nwf]MË�4h'��ǥ���Ý����s�o��}���T^c�l<|�32a�_�׊��w��5-hZ���B�-2a�p�>{AUl����J�]P���^��O;���������5L�g�y*�9-� #�^��y�ށ�~���q
���0�S�v���רN��.���7)�P�U��1�Ti�\����߾�����f*��*Ŧ���_`�����X�#/�uhf�`�6�#����fo�@L�X�y=���rD�o�������3'q���N�1M_�׺b���nR��邛��6Lr�ޓ�ߜ���x�7N�1H݅�n�<m� I	  l_žmȽ�^�8h~XI��=��d�	�S��h&�5�#/N>g;��	�x�G��WR�B�jZ=���.o.�{RqX{$?����f�����4���O����Y� 
��4�m-���BY�1���n�j�Uxu#b�}�ppl'[q2���w��W��-w%�ڿ��k%����wxع�(7&��Z�d�F<;�W N�x��f+\ۻ-zv;���C���8N����#Tr�bN��)��
F�w'RKv$�Z4
]G~��<G��aw�J��e^��'�=p+�����X?���=[�����Uw�����#��?�yq:�^���Ȟ��������E}cdi\����k��0��R*h��/���0����5}���8��\a��`��]�U]��ض��G{�]6��`G����>�8#��;
��m6k�t%c}��7���1Q����[�o����^	�:�\«tP{@��[��rN�Vj2��頷��6��R:h���b���\�O��Մ���(!�Slܠ��S�tX]�oa� ټ�¿�s����D�AK>P1��-8'�}�`���3�tw�@��pa�@{�U��1ᕔ$�Q���:T���la�������:9>���TB�����F���h�)�i��)�^���]���1�Z"(.>�͇+�/��G��I�޻|���,���)��i��\&�]��WK�G47����h����c�^��^��x���L9�$�lg5�.���]	��9K_C���J�ڒ�:��%��e`��O0�!�c�Ƹ�f�J���_M.�����W-g�^G��}�\F`���V���-�7u�l"E���GKoa�K��`�G#!�g��hc�t����!��2��L���.(�4�T��{�^cs&�&���r�w��X�ۮ�N�����E�q������a��C�L�1&Jh���nN/`�����+MLMa�ˤ�����Ob�r�$5����jb2�$�����OOl�!@���B�+6i2�T���K��ȓp�,D?|[��f����Ι�}q��.�L��a��q8��L�M�'���.�v�t[s	%k��L��p�-7�!M;��u�=m�íԦ7��`����O��\\�^��4K׍������ݏ��S��-::�O]w��j���OT�yHe����� m���KF!<]���{	�kx�%���Rs�q����g�E����ú�7��*q�ު����p����%�-�2����WyIל�%��jgǰ�t�����iO�-歞_�#[���Y��q�u S����m��uzu<�֙���K	��,Sc����zai�4����Yl��4�dE��5]����7_����nOd���%�P('��9�!��'��>��
����KS��@��o?�׺��K��Z�2V졧�K�JG�j�hso1��h.FG����!�w�!�g[چͼ��g�Mvakw��.�j4��_p��;�J�.+��x�?���P[�O	�4ܑ����tɉ���_d{��[`���?�a�^_�ވ�k5؍WK20L���Q�~6��X?�!Gjڜ���EM��J�_��<��j(l.�(�ȫ͊֕Zy�f�vGL����Fp��o�w~��"���"S7�O�uT4�w�4�7�M_���F�\wUH��ϱ�5�5��N��c��*N�o�%0�fp2�o�<��������y��I�~�j�������.G3�hd����2*�pkN\�+� �n]G%�1<x�g��Kɘ���\�4�<c����u�rG�l��@�G�=�H�kC��m9��j	GS�?��H�Q�6U�"���u6W<�9�'����'�]G��w�KXv'c������묞 $��_FHg�^]���}�<��Z�M`��c�=6���h��L���T:[`/m����7G�g�N�f�_9O�9��((_3ɃI0ז�hN�9ԑ<�}t�v:k�/����~z�s��Ä���[�˂Z�(�[��tt4a���q����`������5��@���Ps�,i0+���h�$�V�{�e2
�ٵ��4�q�˹��eu��8�v����9��[��zʸ�����V=�//D��v��g�� Xñ�J�b���Ƨ79e�D��,�te 7������N��R>�{S������蔆Ⱥw<��ҘX��n�o+O٩~�bOp3O�g�G̻�N<�����b,�u�]-E۰����	���7���s)3��=)��D��:����ɩv+&�+P�2z]�%�v����Y�"�p�������G�4��,6���x<���+�      �   �  x�]Q�R�0<�_�c��x�1Ȍ6fh��%��K����̗@q�S6�6o��0�BmH*#&a<M��!�$�8��^��h���H�����Q��%���D�WK��@�d3:,�\�]�g	���$K����.w��$TZ�m�x�,ւC���,��9UφL*���z$`���P6�& �iJv��~�s�8��N%W�zu%)�g)��y�2ǚo8���W���܏���[ Yc��-ޗxg����]{(�U����`;��eST��e��P6m��8�_U��c�������[d��uc���g\BD��������N�	ރ[��v���8����u�ѕ��);[T�?�kX�XBh���r��@7��&�~�/��ߦA�ޟ�      �      x��\�rە����P��1HJ��Z $�H� P5����jU���B������8P�@�K�M�'��Y�q����	l�@?��ϵ���;�ĳ��)�0��4)�f�f�/�M�$J�eb�4[�$�l�h�=�-]�G��L�[ϔE��wQY<����tGkS�Yd6�]���z�2L�m�K=�Ka�$�y6�6f�f^��b���L(��i���lV^�Gxy��ϱF��G���������KIpK��Adb~�gpAL��l�է	������fi`s|�?
��}���q^`i��dE����YɅ.���(ɋ�(陸��a���Qh�M(0�%�]����$�)�{,VA@X�2)H#Y�=�IȢ�wN:���Mf��7�^����r�����)����7�z��?�Ϧ�Y�r�;O:A�%[5����g��H2�<+:�u�k,��n�nh;τ�(��"�'ɥ,�0R��,��=V�j��W<:J��l�'8â��Z-�
�է�H7v�9	�O�{�>��>z{��L ���Z[?�"���Qau6��#��6�p�ŷa��[z
]n�,�.����$)~�lX��4	��L7A�QY�:qv���Gׯk��xץFM��������w��f����� 7�~���H1X����
($��s��O&'#[L��{7�_�����f�(��ƶ���S&58�cl���ߌ3���\��Ώ`�a�ـ}�.̡���,�`�	?�M����n���tC�k���vq6ɮF�/;n{�%Ĺ�y�#�7��<Z�Ien�j��I�6�g�;u`���'Kvn�,���615��r�z��@�AZD�������r����y�%@DJIz�/�����Q���H"'q�j�p�389��G�f>{7^p4����GN���l�I���oG3����lѼs� �� �6l�;xu�Fz�JH�1��Ur�h��j�4ۂ\Kn��i�yJ�^P'g~`}��G|a�a���aһ�
��fbA�z�E��h,S��H�AІ�i A���C!z��'���,�#��j���λ��q�����rD�8l�"�RGn�s5����l�BKO���Ó9-��5��H��"q��=N���n��sd#D�����h6�~?CZ�<��W]��6-8Ḡ���5��� Ol*��P!o�J�g6�AI��\�1 ���GQ��|��m��%��5�p0"��<�8�"	�`�i��^$k *�l��~d`m� �I����ZBa��הdɺq3�i+YVш���Կ����T��s���=��u�n����/a����ڱ���)|s�NW�ȭh��p۰BS��?pIL&O��f�q�K�>)I:�s�y���Қ��0Z�h��{S�L-�b��@��l�U�dS��e"	[lr1��uL���e�4���T�a�B�	�Ҍ�d�"� z�r�G���7 C|����W��Qڈ����n���'�i��֟j��Q���$\�v^���j�53��L_�M��7���1�MV-�jz�;�,��6�6io���`k�������L�����_�ѱ�8�mR�#\�Nh�	�r� �)���-r��Oe��sI����{/��O�����y~V��ӗd��_�b�0�����z4y���Gf��}�#%��֚},����q��8���X�%�P�(%�����D��G<���
������3���F�K��A�������'�'U�d�P����W�2Ih��s$z�z�{N�o��K���|j���7��!��p�X2�x�}4Uz�� ��%z�&,ʣ�.C��������1U��A���(���dͫU�d�t9��eL��	&�SD��@9� 
$��k+ĺ{de 4xs���ݹ{�2c���k ����Q{<�:��5�V�-�����cɔ�ǘ�5�(��`�����Y�R�-z�?J��1�K?� � ���Zg�K+D��"l��)EK�T*U�f.�b����Ҧ��(��d�3���}���-P�@�x��T8�r���_��q]�χ�g��Жt���#u���R-�=U��۵����Ab[�.�����@��r�F�J�Qe�B&�j]ե/�Ѧ�Xa=!�mm�_ ��G����������F(=�+�Bh� T:/N�/��'��X	�@)����:�/8�����͝CP7�^�zKBF����E��k?]��� �˪r-E蕄�sMRM�?���ڡ��"��?8?����$8�G��P�!E�l�5F}�k���zM|H_6����a*3�V��uxN)����I�ܖz/n��KZ�4J�5��b/��c�������x�L6
�
�a�!EA���Ru}	k� ��99���x�HX�6�2�DV�<�yce
=ej�'3��|�/H��۷T��r񍿘�����s��;8{ݹ��_\�nEo|	-��O�?��Gj�F�dvSR��nsXa��˄"��sܹ��g��Q6���	%|���у��b�X�ب�'
%B�k���H�y5�^ X��������K�!��ܮK�d���v�(�S�/����h���A�u���f0q@I2WV
�h�Lt�B�/��	St����E���#����6���S)���d͚V�/���|Ɠ�L��� �ދ�?�^�N��-�I��Y���N��6��'��l
��t�Df#D9�� a8g8���8�=����(K�+!^�����jB��p�}0�s!���%�б�a���w$eB{U�%iM2������Eb2D5B�����P��)��UG�h6���O��<i!*?�О�9���k�!
`%f���_fp���Ɛ���
κ�׏UM9���*���� ���)c�,�Bu��K�ϲ@FO�.v���"�=�K�ʽ���S�g)���S�'�q�6�O��r*�Q\��%�	���Ո�b�֌�X�s��`�;U|�Q�ˉo�5 �
���I	��P�Ek��;��	�5�>�L�>6%ږ���8;���o�(�q�RIG���N6G������C�^�m��6+-(��	�!�9Z�G�� (	����"��9I.�ee��$�E=�S�_�:�ʿzKqħ�Q����lA���ɨq�>R���1K��rp���f{�F�������B��oԹ&(� }�|)}a��t�D\�Ps��@1,�Q�re_��ͤ7�q��C���U��rg���PƵ^��\D����0���%��$:�_F\�;�k=�X��}�86.d��B���w�9@P@����������@�j���d�נjAr��`�_�N�`��`z4�\�e���J�^XL��@Hݞ��Q��x��%��v<�?��0�	����D�z��xt�-�����˟�)"�sA���Qn�
�J�B���;�3�w/[���d����o�$��P�<�뵸tlY]���2YN�\��`�8^���]Q(��X ��*5?�B}g�y�6ļM��@`�Iz��t�؁9N\j�x\��k7�������|E1u�ߊ�#*�/j&�#��q�mXgnV6�҄��F�d��F�߼W�ͭ�'՜��S����'�UA��9�T���M��+&wH�[�*WR��BX��W�fW7������c�jO�[�ja���d�����3����Gܭu�}������o�?�
�A�H�^nʋW�|��=�	�Lp�F��L���Y���*�q�4�o��Y8�]������6�F��g�C|>	����@�������{��Z[��ϋ����B�⫝��y���1ǡ�'g/D��.���T5����e$��
v?�cA\g{� ��$�ي��(�'
R�*���hs���$u"$�V9�#I���vd:�������9ġ4��W���|f��H��
��MEH ���f�X]D��s�:`7M�{��IK�F�}�2�%�qZ 1�
��J�.&p�~Uq��,ӵ��(��6AN��Ͷ���zI�U �"���o`c�犠x�E��]�� �
  ����,]��׮���phƢ��d��O�� W�m{zqH{��2�0��2�4;D�*�2���DY��i����a��.ܴ3__N����B������tO����<���~J�W��6}m'��yą��Ɉ:Bh9gg��Ƽ�:NW��S-s]
e�&�����7DY��\��bɟs�l,�uM+�(�_�� ��I�#U�^UO|���u���˳���8{��.�'���.!w�U&=�Jp�Б�ίix��8�Y�;댥��x1�S�N�Ch@T����i�K�����*F0V�* `�� ��uD��TFeҺUF�8D���!�6�����L�ITǒx��u����4�.�f�b��lmq��T`����aY�"^����&� �:.0:�e\�n��7I�B0�,��/��;����A�c@h$jX��e���<��Ge�Lh�����_�?�*��Jfv23l����?�L�nF����v2����ڼ��0]�sK�`�p�/���9( �1�&��^�[._��`�4c��Ue��?�<cq�[Trc�n;A�˖��4`ҧ;i?���kz �C���;�M�B �}���}&�%�_����qX�tO�����ʼ=.g!w#=���=[�%Ƒ!皐�T�Zay2�hT-4�q�r8�K6Oɓ��'Ƨ��mnhź�XF�i�� ���#��x���jO��g:��Cyh������R��Z�PJϟ��u�}�ì�~�4��`SU��
P�	��ʊ���|-GR��
�	#�B��$Ɉz����3"�T�f�j�ās~��}P��n�Y�����|�qώ��{v
�~`G���#�P�d��/��5�f<L�)��E�m�`WS�x�,q�DT\�O&����xA��-E/���S�q᨞�"�! 
�%YM�k̜�e^�������λggq𚩃�J��t���L���n����'���<m��NFCB����J�oT�'��l�ӭ?�ͷ7��+c�(0D�i��WkP�ˮU��b	ݢ�+�{#��
!�4(]��Z�h�����A��򌻰˹���y�H�����N���Έ�j�g[�t�P��<
R�a��4L�x<�%��A�d��Y]7�sEG��@�I�V=)�S{��<�e(��A��L7mq!1�!���H�n��(O����p5E����ês�I�7BJ.�OIG��ޏ =���A�IJb.)N`A������^������~�,IY��b�0�{���B��4D8��e�,�Up[���.N{�M�*'�J
����y�lC�����j����E�H���$L�����Ԛ�������!�Xҹ,%
���%�x�;w|#�v	~�O]p|r u�}ݙ>8D�;���i�G1qڴ�&�P��cN���(��͐�F��ޢ����S���YOk%~�0��ʚ����&��NO*�j4��
`�4��W�5�$Θޝ��X�1�vH��i����V/����w3�M�:|Δ�t�}cƛ���5S(�$w6�X���#l;���J�r���e�7ǳڛN��z���s�9��u���!�)�B!�iZ_C����:�(�hju�ݨ��Q����:�0>�M\�������^�c�����{_�$�����?���f��_N�i�т��~��.:#�R�l�Q�F��[�q6sgO2�m�Zݫ:�#|X��U4��5�3Uu�3��t[�*\�_ے��tF�GI��o�v���#������͢�u�G��ڌʃ�N�*��ƸL���
e/�
[�NOo���{�Ǚ�=o's�a�Sf��9$.��Oڜ*��Xj��[)Q%�L\S���a���+��{�dv���=�@��~V���e�����T� t���BBaq��K�`?	�����&�(�_>�<��ogs��V<�G�	��;�i\G� HFS@;�E��0���*��!������$�����c�ݬN��2����P�dCΡtX���o�9�h�?�ן��A���������\R�[�ڭ��g�s�
vGi��&�n��=v�Gￓz/f���K��+��B#��i*,K��!���Hrz��j�N's묕��:�R����i�g���DG��S��t�]�o����(����`b��ͥ����0���F�BЌ�D�?jQQ���2T'�hF��`UrH�a����eI�V6��)�B ⻨1|ݾmp��uD-�Y|�c��+ڢ}�́���F6�=2�F�;J��XzK��dr���~�%q��Ӓ'����~��l���A�z|���Q��I���.���G�m�����/<v$M��y/�A/�<
������>���4�	(��*�`���4�IQb�(Z���x��^�]�gѣ�T����:�J�4�UXX*Z�{1�ofsL���V|�>�C[�X1Ǻ$�M�|9�b*�;d��-���Ij����h����o)h_����ي�J�I��Iz��\챣4W�����,��a˩�=1����W@GN��<~�n+����zt�jn��bө86u���ip�?�o8��O�/�-�q�bM����*���;(Oy�?Ы�U%��z�`�_<��#���<�z��"@�28��O����O�Ƶ� ɓ*��:j.|��/=^7Eؚ�':�0���9���<=V�`?���^�2=��l��������ZE'      �   ?   x�34�4202�54�50U00�#��������%��1P (���wq��W0�,������� r�V      �   v  x��Z�r�6������um��;&��;I���t��0�uk,���7��>B^�G2�F �$d��3ف������s$�V����>�¦g��vpg����ɵ;D_J�y�`(�	��pA� $hC�"�H��0H�M3�а�ig8��o;z'�b	Mx��a�8BJ ��i�;Ld��n<� �l^:ɼ#��?��9��ގ��!������#�M��;�jR�hAQ�;�V`�/�Y�d8׳um`�'�|!_��nY�&�EPg4���l�P��4B����\EYB��ܗ������ǳ�w��l�_ѝ;��|4t����;)�}�Y~iG;CgU���p<��������4T��v�w�l��2�0��X���ٮ�;�k����h��^_�E>��*>
��x�W�r����h�'����.B|�D9	��oq�7{ZD-^�p�������������7K�V�������h64�ɸ�&�%��_�T\�G�����C��	�����ǇE��1���8��,/�pχ�<E)M�M{5��%�K��ĈB�/�P~B7'3�+��3��G����<a���Nz�"�%pGh���#}BB[i����3�.Z������e���>���S�w:��+5,�u�45;�* �<̊5��&�G͒�`WsC2�0�8���3C�]�P��I��&'����S.��V�!YԦ��K�#�5W�@'��f���969e?;�dˎB��T�2�ҋ-�ڠ�v�	��yx������B�S�$�9Kp��Og�ya�Id�
��]������d�nƿ��3ۼ���W�Δ�%�VK����9�ej��d���r)�Sއ<Â����
g&�"p���W�5����7����t�Ȩ��EC��O�uB�<�x`�~_��
�0�P�u�g�):����GW>�:�p?q�ZX��ּ�����Ue?�=�j���1����<�'>�n��W�Y>Nh�0l��Xε������ڦ�Z�7x��Z��X�t����M�W$�@A��=2�H
Y������m�R}E�r��K��㙺fǱnz�mY
�vs�4;�L�u$�+� ��tC<P�=�wn�ŷE$��}�Ii��^�B�f �����B�a="y�g���п9�{�� �	#6�mX�<o�b'v/�m0�w[�{�6�W$�v7	�\m�~��c���<X�����e��n�0�w�����Ч�6����m� [F�Ũ�����Hw=��u�b��Q��aI�z�όW�D���t��x�
��r1�nV0�M�(�e&��*~�!X�艑� 0n�۹�h*�	�8�#�%�x�.�Ĭ��PN3��EPtC�rN��C�D�X�{gi^�^�{�?�[9��<�$_�l7���=�Y䗁���YҒ&�3=�`A.Χ��\j$V:_��+0���-m�jIyȤ�kƍ� ����V���9�Gb�M�}�@�&��&����+Ƚ��w�]4�~#*��T��ɢڊ��w�Aq]vmCV���O�q��R+�Z�>	g*��S���Ր,��lKs�f��M�H��.��y[a�y�y�,� ��Bq:U��V��g맻��D�����Ֆ��r]��u����g*�      �   a   x�U�M
� @���)�����m�$s����<x
��s�
,J�S�Z��`	�`
�`��¡���#G
\(3]�SHկ|����2!�E%      �   �  x��U�n�H}._`���m*��t��ۘ�֚�n'D�d������ǶO��3�j$#C�:u���\�2?�q�3�g<�}�;4�]7�nCǗ�Y�����ج�c��Kg.�KЪ��r�U�P�Δ�����#������-��K�O�D�U�)|yV�C�<F�b
�s�ެ��ǫ�mͅ��ԙ��ј���.%L��L��B/�a��e����w9c�@��'B�ݺ7�_P$�� Q �8iQ:*��\I]�?�l*��(�*�P'2�|��y�����F�Gɉ��&�V��3�í"�2��7�H/�X�E���XDQ�,�`��U=��ۺ�/�R�XQ�0��!+r
�.&T���9��@��q���H�n��?M�6�v?>��_0�S\��:Z����B	_I�¬�U�ǵ�`:4A<���%
c�f���<6���*'7odYZ��+��%^��5�2w�e"Ba�I�Y��3��{�_:tEzIc� �Rk��t��@S�Jm1�Z��̓��0,<��5=J��(�������qu�\��ƫ"w�k�0�蟪�fS�ri�D�OC�R��,����ú14�O���y� ��S����&J��	f69���"[�g�͚�~_��B|���M;^u[ؘ����i��zW�;H��4=i_��f����C�dVTM'���T%�c�C�h���vv�E�/���fu����<4�E#�[�P@&o��\�(4�Ӑ�.�j�R|����_��7���vo��n8/w y9�7^>E<���t���;!�i^��1��g��3���o&�l�\�w��GOi�����O�/i
w�?w��F�(�Y���`��mw��|b�{E�V�++�{���ρ�vm��d+���c?^�L%��T�">V&K�v�W/����C�:��}�J�)�Ӵдu�^��펧�#5�|�F�� OP#�      �   o  x���[��0D�o{/�У$��2z�1J�i ��c>�#.�1d.m)�ԟt����-ե6<f�5��.y��Kwiz��]
��%{�c�_�(+���0cr��β��љ��tgZ$׊�������t𼃭��ˉ�z�(�ģm+O�TE)�u(Y���M����E��*�ALA�#R�R���[���K>�V��ÜG9o���"�ŜK��㺯	c�\��nq
�z}�BS�l;��O���ȔȽ 5�L�P_o�T�T�3��H��XS��I֔�"TS(T��o���TM�VM���Ҫ�����ZD&-�Z���P��"%�^��"-����#-w2�z��j��H��lT�f���D�u53��h�7�u��,�+r���6Bu�z����S�2�e��K?&p${rI4�(��0cR�k,���L�6ݙ6Iu�t߄�v���7������W7�#z(K0����M "Uq�HA�"R�o��yL&�s�M b3�@�NH'��|���C���Z���R���U1?����O *��O j��O j��O j��M ڈ,����i}N �H)�@ԑV4�h#�xQGj����>'���&��M&����Q���6+�N`Y�m���bF/      �      x������ � �          7   x�3�tt����2�p�p�9�\�����|\�L8CC��<]]�b���� F��         �	  x��X��������P*�X��N'_q!]�ErUug;�\l3���B\N�`Wm����R���į{$�]�ʁTKb8������8��B�z�ڼR67B����)�5�4"S"�ee��u��23V���Lf=Բ��,�{I늼�U��#1�����y���O.
��+l���gMYY5w|#��l����*��h�p(��]�W5}�����R��;i�)
3��n��o�ג��7C9���PZ\�"3�r�j'��6:�w:�l~�e�ϥ�A�~�c�F���b�����ͫ\St�]#Nh���Lf��:G�[�����\+K�G��4�M^M��x��s&~��2^,���<�����h��*�@��T/�C1� u8�n`��V�no>V>AX; �t��痈���b����g\ƈ�Z�52`�=� |{.vo���Eq�aP�����6��8􈲭��%|L�z���F�L;�H��.����X=I�~�@0:Đ�~�G��d�b����$�2�O�Q4��Z�x���.�x���D6����Y45L��{���R*ď+��W`#�+cKy|b�Ȑ����}�z�ɭmnSNSo#Q�
h0&�BV�Ə���B�}��(�'�Q6�q�0'�*��p̣7����	L!�q��7�E�d��[��/�6_,�������h\#����(W�U!m(܉p5FHoVC1�v#ŵ�X&�S����� �<�b�͏D�]I�+
�:C%*�x^��"�ͭ���9�a�Ƣ���!j �5w�B'����Q��'B[+� ���c��T�JV�5���H�i���z1�|�^f��Й_V����Q�M#���Ag��r���i,��~�З�V���'T	�� �����B:�R*V�h����%D9�%K��GDF]�=E�Q�(��1DM�q������W*�����X�	��o�'��Y3;��D��(�M����
uL�c�^�Ǵ��I-���x�'�Y�[�#�º�\sK�J@�Q���� mh؉"���Ⅱ�ʲdL_���u�1����7^ܞ^ܷ�$5�CѠ��#Ea���f��n�*�"�f�49�HS������	�D�0�6m���Y"
�x/o�0o^�sh��Y4�&���{��T��Ss���1��"��5��:���V�A8�ם�G��in�,Kn�����@�_����|�M�I������6��ֿ��kg��
g���=�<e����0�<�����i%�>������='�9��l�4��g�x����HvZ_ɱ�˒�N%�.:Q��Q�X������o®�ȱ��r��<���?��	��O�5�H&����LTQA>sB������;�$���m����d�ek�N�xb��떁��y����N���36!\HGw�{ ��h4�?�qE�o�/Hf6S�U��1E������+oPE	��j8��T n�����<>�g�%g�S/Q�+-�j�Q6K��9�2���)!;j�:ـ��UH�rH\Y��R���}���;�[("�w�b��
�A���|8�8}Ӄw�}�s^�TP���0�R���B�9V;��+ib8��k?��U����:ݫ l�<����p�Fj�m	�f�����wB�Jm��z��<����m&���d\�>����mKgg�כ �5;z���|EQR�c�g���3鑁���5�~�= �(���\��CSFw����罪$�q�HW� /C�4�o�2.}�,��1��Y[ �.����+S�%�t��1�(_$���<vRS!�`��G:�^N^N'�U�&�IӼ�*�殸�h�#��WG��'F�F���Df��m�������/8��}�:�_@e۟�)u��fU���v����,Z dSk "�Q·� ��;���dW;"���{Dd�7
�sl>�^�n���23�K3r�� {���5�e�㇯�N�8讼���sX����~x�ڹzO�ّ��8~�H�8�l�w2[5�WK<\5�3��ap�4�J1U{�E������l��r��;�,��7�~�X��ÿ}���"�Nkş�鯂=��9�B�7�f~��S����.�? �ͮMo�m�/���iRn�����u'z?:��=�]�Y�J׹+M�`Wt�����#�g�S��{�ӑN��z���ܢ�+���qeO�£�<Yœ��^���j$^<&g7�������,Jؘ6�i�=�
>}S�?)��n]��5�wy����v~�X�d|ٺT1����l|9=����쇯ޑ�WhM��Iu�8\ʻ|��;e]+&��K��`��g��q:�*@����A~J��L�������0�&
o�ز�i�%�y�K�!3=�_p�hY;��0 A��"Y&��$��'��s���5���_�n��n���-����U�a         d   x���D!��Q1;8���뿎uHC0�(2�O$eӜ,s���1��ˋ�����	u�U��M�:����F�}����^�9����'�/��v���?WPm         �  x�M���JE��=��"	b DI�MPrP�_���t�ls랳/\�?(���|���� C�B�0.[qoZNY��u�h��"��!��H�(�����C_�8ꑀP����K�rر��A�n�gC��U���u�����Ҹ'{c ��8�y� F~yMz��B�^ٜ_�'��C�x]� �F�Q��������8g�S�Է�
B@!
0,�"!����d7����rl-��G=;�Ǣ�ݨ�C�0�`4��=�XI�"����c�*vɰkJ6�w���O�P��F���2ExN�|�ڇ��ͣ ���(���j��W�#^��:�Cz_�����	Ճ�#�0�S�)�쏤I�e�$c��hwX�a��l�D0��e�$ә�H%Q������S�ߦ��?�!D3�{s��t�����nw���W\"M&��Ъ����ɷr��O1dnLv�E��3�+a�Ƀ��/o�vS�6;�����)���a�Bl�;��`��wN41w3_fſP�g>��9�H�x�Z5��d@6ɤD�����/VBtA�c^���{f2�����E�1��pϛ�=�����r:
[02"�$�h�[S�}��Xj���Auʩ��4ð�@��GJ^��pX�-]w;���&��~Vl�>��s�P�����u�V`�~2�C�B��K�B,�%���z��w���T�^8�v�@d�`i�㮎�X���g�y�,K)���;)o��_�YV���� �\�����R�]	������GY�u1jRՔ�K�g�s,���	��i��0�j#�Q����&�]uq��o}x��ȟ��D���p1O3�hǪ�og�i'�\w�2x�i���_hN8�G��� 9��5�0����H7�:� L�L�<��A�S��̟{����D5�VVD�(޵�� ��1����J*=�G"R5A�lq�cN}z�Dy�\3Aڣ׃���b1��n�{��6��%^w�N�[�H�Ru+�B��Qk5�8�=��B��B��!�����~���Bc:Lx�x�o��޺���K_D��{�s#�P���޿��~Ġ�k=����9]2���sѸ��EU��{�&/�>H�KM÷�k���~����y�g��;<Xwj�\�H��2z�$�|b.�Z;_N4O�亞GLB5i��7�~��&B��a��,1a��~^'����,qw�d	�[�l���>��L�q�;��I��R�D���1���     