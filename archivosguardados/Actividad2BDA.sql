/*      ejercicio resuelto      */
--creamos la tabla que se nos pide
create table libros(
      titulo varchar2(20),
      autor varchar2(15),
      editorial varchar2(10),
      precio number(6,2),
      cantidad number(3,0)
 );
 
 --confirmamos que este bien echo
 describe libros;
 
 --ingresamos los siguientes datos
 insert into libros (titulo,autor,editorial,precio,cantidad)
  values ('El aleph','Borges','Emece',25.50,100);
 insert into libros (titulo,autor,editorial,precio,cantidad)
  values ('Matematica estas ahi','Paenza','Siglo XXI',18.8,200);
  
  --se realiza una busqueda para saber que estan dentro
  select * from libros;
  
  --se realiza una prueba para verificar que se respetan las condiciones que establecimos
   insert into libros (titulo,autor,editorial,precio,cantidad)
  values ('Alicia en el pais de las maravillas','Lewis Carroll','Atlantida',10,200);
  
  /*    ejercicios de deber     */
  /*Primer problema:
Un videoclub que alquila películas en video almacena la información de sus películas en una tabla llamada "peliculas"; para cada película necesita los siguientes datos:

 -nombre, cadena de caracteres de 20 de longitud,
 -actor, cadena de caracteres de 20 de longitud,
 -duración, valor numérico entero que no supera los 3 dígitos.
 -cantidad de copias: valor entero de un sólo dígito (no tienen más de 9 copias de cada película).
 
1- Elimine la tabla "peliculas" si ya existe.

2- Cree la tabla eligiendo el tipo de dato adecuado para cada campo.

3- Vea la estructura de la tabla.

4- Ingrese los siguientes registros:

5- Muestre todos los registros (4 registros)

6- Intente ingresar una película con valor de cantidad fuera del rango permitido:

 insert into peliculas (nombre, actor, duracion, cantidad)
  values ('Mujer bonita','Richard Gere',1200,10);
Mensaje de error.
7- Ingrese un valor con decimales en un nuevo registro, en el campo "duracion":

 insert into peliculas (nombre, actor, duracion, cantidad)
  values ('Mujer bonita','Richard Gere',120.20,4);
8- Muestre todos los registros para ver cómo se almacenó el último registro ingresado.

9- Intente ingresar un nombre de película que supere los 20 caracteres.*/
  create table peliculas(
  nombre varchar2(20),
  actor varchar2(20),
  duracion number(3,0),
  cantidad number(1)
 );

 describe peliculas;

 insert into peliculas (nombre, actor, duracion, cantidad)
  values ('Mision imposible','Tom Cruise',128,3);
 insert into peliculas (nombre, actor, duracion, cantidad)
  values ('Mision imposible 2','Tom Cruise',130,2);
 insert into peliculas (nombre, actor, duracion, cantidad)
  values ('Mujer bonita','Julia Roberts',118,3);
 insert into peliculas (nombre, actor, duracion, cantidad)
  values ('Elsa y Fred','China Zorrilla',110,2);

 select *from peliculas;

 insert into peliculas (nombre, actor, duracion, cantidad)
  values ('Mujer bonita','Richard Gere',1200,10);

 insert into peliculas (nombre, actor, duracion, cantidad)
  values ('Mujer bonita','Richard Gere',120.20,4);

 select *from peliculas:

 insert into peliculas (nombre, actor, duracion, cantidad)
  values ('Alicia en el pais de las maravillas','Animados',90,3);
  
  /*Segundo problema:
Una empresa almacena los datos de sus empleados en una tabla "empleados" que guarda los siguientes datos: nombre, documento, sexo, domicilio, sueldobasico.

1- Elimine la tabla si existe.

2- Cree la tabla eligiendo el tipo de dato adecuado para cada campo:

3- Verifique que la tabla existe consultando "all_tables"

4- Vea la estructura de la tabla (5 campos)

5- Ingrese algunos registros:

6- Seleccione todos los registros (3 registros)

7- Intente ingresar un registro con el valor "masculino" en el campo "sexo"
Un mensaje indica que el campo está definido para almacenar 1 solo caracter como máximo y está intentando ingresar 9 caracteres.

8- Intente ingresar un valor fuera de rango, en un nuevo registro, para el campo "sueldobasico"
Mensaje de error.

9- Elimine la tabla

*/
  create table empleados(
  nombre varchar2(20),
  documento varchar2(8),
  sexo varchar2(1),
  domicilio varchar2(30),
  sueldobasico number(6,2)
 );

 select *from all_tables;

 describe empleados;

 insert into empleados (nombre, documento, sexo, domicilio, sueldobasico)
  values ('Juan Perez','22333444','m','Sarmiento 123',500);
 insert into empleados (nombre, documento, sexo, domicilio, sueldobasico)
  values ('Ana Acosta','24555666','f','Colon 134',650);
 insert into empleados (nombre, documento, sexo, domicilio, sueldobasico)
  values ('Bartolome Barrios','27888999','m','Urquiza 479',800);

  select *from empleados;

 insert into empleados (nombre, documento, sexo, domicilio, sueldobasico)
  values ('Carlos Caseres','33556688','masculino','Colon 235',900);

 insert into empleados (nombre, documento, sexo, domicilio, sueldobasico)
  values ('Carlos Caseres','33556688','m','Colon 235',10000.5);

 drop table empleados;