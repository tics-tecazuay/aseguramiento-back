PGDMP     )        
        
    {            Vinculacion    15.3    15.3     Y           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            Z           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            [           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            \           1262    16398    Vinculacion    DATABASE     �   CREATE DATABASE "Vinculacion" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Ecuador.1252';
    DROP DATABASE "Vinculacion";
                postgres    false            �            1259    16517    usuarios    TABLE     �   CREATE TABLE public.usuarios (
    id bigint NOT NULL,
    enabled boolean NOT NULL,
    password character varying(255),
    username character varying(255),
    visible boolean,
    persona_id_persona bigint
);
    DROP TABLE public.usuarios;
       public         heap    postgres    false            �            1259    16522    usuarios_id_seq    SEQUENCE     x   CREATE SEQUENCE public.usuarios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.usuarios_id_seq;
       public          postgres    false    249            ]           0    0    usuarios_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;
          public          postgres    false    250            �           2604    20139    usuarios id    DEFAULT     j   ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);
 :   ALTER TABLE public.usuarios ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    250    249            U          0    16517    usuarios 
   TABLE DATA           `   COPY public.usuarios (id, enabled, password, username, visible, persona_id_persona) FROM stdin;
    public          postgres    false    249   �       ^           0    0    usuarios_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.usuarios_id_seq', 23, true);
          public          postgres    false    250            �           2606    16600    usuarios usuarios_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT usuarios_pkey;
       public            postgres    false    249            �           2606    16711 $   usuarios fkq1l7b7bice5uysvjoo457towq    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fkq1l7b7bice5uysvjoo457towq FOREIGN KEY (persona_id_persona) REFERENCES public.persona(id_persona);
 N   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT fkq1l7b7bice5uysvjoo457towq;
       public          postgres    false    249            U     x�M���JE��w�M�� ���M@rP�_��{�t$����pկ� �t�I��I��F�І���Q7�"��[��.�׼J������Y�Y�<ސ�N���C.�	7�9��/��)��4��U�nq[;��m뼡�B{�Gm ��8�y� F~yur��B�NQ_߯'ҞC�p�u/Q�Y�	e�ra��Qƴ�0�����B`X�MB���a�S�e�Gpn�%ig��5������C$0�-o��i�Q,�$|�H����O$Ӈ������=F��Ǎ��Y�V] M�5����\�TW�|c��(� ��1_^Łl��RxF[�b�^E�ZS��]�juq=O����{{��I�1�쏤I�e��_^x8�n����L�@0��y��8U��L�a���z��9R3���>�>�!D3Բ9�~���B���Č��ɃP	��7��p�����k�3w&���"h���0���b�����	K
���������ᴳ!�ăk9�h+���͓�T��y�o�A�^�FN��:j�M|2.нB���'d�������]�'�����; E/��I<��y�&�gX�ts�/g�i
ZJx��͛�^�f���.�jø^yɨ��4ðKW ���#����o���Z��Qo��B	7��j��������U�Ud�`�}2�C�B��K�;B,<�Am�P��}�[[����ȭh�N�'R{:B�7òWg(�
Q�L�:�!@���&Z��)�R7WO�(�	sx��o~�[|L{%l���?؏E�շY�HY��oi_%���@6��˼��Ӑ��f��=�ܝ�i��Uu����A�C�4j��U�@�Lx���Zќ���Zn�E�Ư�+�n@s?3�ކ ���� ��OsW�!]�rO?&R���,2z��.q�N"���Π�U�XQ���Xj)��;� ��Cs�8���Tr��D(+�lL'�����,cn��9��.Å�b��]�I����v�}O��������l�\����R*βhs�c�8MC@ȫ>����~����,��Bmh*L,x�h���������
���5�����)��h�[��~Ġ�[5{]�]�oW=��4�5+��L�2y�#e����e�D]����F�-{ܣ0[~��0�c���=k���[암�Y� ^���*��B�TOnl�q�Q(��Ce�񍤗�"�Џ�#�Ə���V�ۨ?;S�6���Y�l����~��)��y=����$��e�%�0�8��"��<|�-����`�     