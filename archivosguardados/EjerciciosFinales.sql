/*EJERCICIOS RESUELTOS*/
/*EJERCICIOS 7 PARTE 1//////////////////////////////////////////*/
    create table articulos(
      codigo number(5),
      nombre varchar2(20),
      descripcion varchar2(30),
      precio number(6,2),
      cantidad number(3)
     );

 describe articulos;

 insert into articulos (codigo, nombre, descripcion, precio,cantidad)
  values (1,'impresora','Epson Stylus C45',400.80,20);
 insert into articulos (codigo, nombre, descripcion, precio,cantidad)
  values (2,'impresora','Epson Stylus C85',500,30);
 insert into articulos (codigo, nombre, descripcion, precio,cantidad)
  values (3,'monitor','Samsung 14',800,10);
 insert into articulos (codigo, nombre, descripcion, precio,cantidad)
  values (4,'teclado','ingles Biswal',100,50);
 insert into articulos (codigo, nombre, descripcion, precio,cantidad)
  values (5,'teclado','español Biswal',90,50);

 select *from articulos
  where nombre='impresora';

 select *from articulos
  where precio>=400;

 select codigo,nombre
  from articulos
  where cantidad<30;

 select nombre, descripcion
  from articulos
  where precio<>100;
  
  /*EJERCICIOS 7 PARTE 2//////////////////////////////////////////*/
   create table peliculas(
      titulo varchar2(20),
      actor varchar2(20),
      duracion number(3),
      cantidad number(1)
     );

 insert into peliculas (titulo, actor, duracion, cantidad)
  values ('Mision imposible','Tom Cruise',120,3);
 insert into peliculas (titulo, actor, duracion, cantidad)
  values ('Mision imposible 2','Tom Cruise',180,4);
 insert into peliculas (titulo, actor, duracion, cantidad)
  values ('Mujer bonita','Julia R.',90,1);
 insert into peliculas (titulo, actor, duracion, cantidad)
  values ('Elsa y Fred','China Zorrilla',80,2);

 select *from peliculas
  where duracion<=90;

 select *from peliculas
  where actor<>'Tom Cruise';

 select titulo,actor,cantidad
  from peliculas
  where cantidad >2;
  
  /*EJERCICIO 12 PARTE 1//////////////////////////////////////////*/
    create table medicamentos(
      codigo number(5) not null,
      nombre varchar2(20) not null,
      laboratorio varchar2(20),
      precio number(5,2),
      cantidad number(3,0) not null
     );

 describe medicamentos;

 insert into medicamentos (codigo,nombre,laboratorio,precio,cantidad)
  values(1,'Sertal gotas',null,null,100); 
 insert into medicamentos (codigo,nombre,laboratorio,precio,cantidad)
  values(2,'Sertal compuesto',null,8.90,150);
 insert into medicamentos (codigo,nombre,laboratorio,precio,cantidad)
  values(3,'Buscapina','Roche',null,200);

 select *from medicamentos;

 insert into medicamentos (codigo,nombre, laboratorio,precio,cantidad)
  values(4,'Bayaspirina','',0,150);

 insert into medicamentos (codigo,nombre,laboratorio,precio,cantidad)
  values(0,'','Bayer',15.60,200);

 insert into medicamentos (codigo,nombre,laboratorio,precio,cantidad)
  values(null,'Amoxidal jarabe','Bayer',25,120);

 select *from medicamentos
  where laboratorio is null;

 select *from medicamentos where precio is null;
 select *from medicamentos where precio=0;

 select *from medicamentos where laboratorio is not null;

 select *from medicamentos where precio<>0;
 select *from medicamentos where precio is not null;

 insert into medicamentos (codigo,nombre, laboratorio,precio,cantidad)
  values(5,'Geniol',' ',0.5,200);

 select *from medicamentos where laboratorio is null;
 select *from medicamentos where laboratorio=' ';

 select *from medicamentos where laboratorio<>' ';
 select *from medicamentos where laboratorio is not null;
 
 /*EJERCICIO 12 PARTE 2//////////////////////////////////////////*/
     create table peliculas(
      codigo number(4) not null,
      titulo varchar2(40) not null,
      actor varchar2(20),
      duracion number(3)
     );

 describe peliculas;

 insert into peliculas (codigo,titulo,actor,duracion)
  values(1,'Mision imposible','Tom Cruise',120);
 insert into peliculas (codigo,titulo,actor,duracion)
  values(2,'Harry Potter y la piedra filosofal',null,180);
 insert into peliculas (codigo,titulo,actor,duracion)
  values(3,'Harry Potter y la camara secreta','Daniel R.',null);
 insert into peliculas (codigo,titulo,actor,duracion)
  values(0,'Mision imposible 2','',150);
 insert into peliculas (codigo,titulo,actor,duracion)
  values(4,'Titanic','L. Di Caprio',220);
 insert into peliculas (codigo,titulo,actor,duracion)
  values(5,'Mujer bonita','R. Gere-J. Roberts',0);

 select *from peliculas;

 insert into peliculas (codigo,titulo,actor,duracion)
  values(null,'Mujer bonita','R. Gere-J. Roberts',190);

 select *from peliculas where actor is null;

 update peliculas set duracion=120 where duracion is null;

 update peliculas set actor='Desconocido'
  where actor is null;

 select *from peliculas;

 select *from peliculas where actor is null;

 update peliculas set duracion=null where duracion=0;

 select *from peliculas;

 delete from peliculas
  where duracion is null;

 select *from peliculas;
 
   /*EJERCICIO 19///////////////////////////////////////////////////////////////*/
    create table articulos(
          codigo number(4),
          nombre varchar2(20),
          descripcion varchar2(30),
          precio number(8,2),
          cantidad number(3) default 0,
          primary key (codigo)
     );

 insert into articulos
  values (101,'impresora','Epson Stylus C45',400.80,20);
 insert into articulos
  values (203,'impresora','Epson Stylus C85',500,30);
 insert into articulos
  values (205,'monitor','Samsung 14',800,10);
 insert into articulos
  values (300,'teclado','ingles Biswal',100,50);

 update articulos set precio=precio+(precio*0.15);

 select *from articulos;
 select nombre||','||descripcion
  from articulos;

 update articulos set cantidad=cantidad-5
 where nombre='impresora';

 select *from articulos where nombre='impresora';

 select 'Cod. '||codigo||': '||nombre||' '||descripcion||' $'||precio||' ('||cantidad||')'
  from articulos;
  
  /*EJERCICIO 20///////////////////////////////////////////////////////////////*/
      create table articulos(
          codigo number(4),
          nombre varchar2(20),
          descripcion varchar2(30),
          precio number(8,2),
          cantidad number(3) default 0,
          primary key (codigo)
     );

 insert into articulos
  values (101,'impresora','Epson Stylus C45',400.80,20);
 insert into articulos
  values (203,'impresora','Epson Stylus C85',500,30);
 insert into articulos
  values (205,'monitor','Samsung 14',800,10);
 insert into articulos
  values (300,'teclado','ingles Biswal',100,50);

 select codigo, nombre, descripcion, precio-(precio*.15) as "precio mayorista"
 from articulos;

 select nombre||' '||descripcion articulo, precio
  from articulos;

 select codigo,nombre,descripcion, precio, cantidad,
  precio*cantidad "Monto Total"
  from articulos;

 select descripcion, precio+(precio*.2) as recargado
   from articulos where nombre='impresora';
   
   /*EJERCICIO 25 PARTE 1///////////////////////////////////////////////////////////////*/
    create table medicamentos(
      codigo number(5),
      nombre varchar2(20),
      laboratorio varchar2(20),
      precio number(5,2),
      cantidad number(3),
      primary key(codigo)
    );

 insert into medicamentos values(100,'Sertal','Roche',5.2,100);
 insert into medicamentos values(102,'Buscapina','Roche',4.10,200);
 insert into medicamentos values(205,'Amoxidal 500','Bayer',15.60,100);
 insert into medicamentos values(230,'Paracetamol 500','Bago',1.90,200);
 insert into medicamentos values(345,'Bayaspirina','Bayer',2.10,150); 
 insert into medicamentos values(347,'Amoxidal jarabe','Bayer',5.10,250); 

 select codigo,nombre
  from medicamentos
  where laboratorio='Roche' and
  precio<5;

 select * from medicamentos
  where laboratorio='Roche' or
  precio<5;

 select * from medicamentos
  where laboratorio='Bayer' and
  not cantidad=100;

 select * from medicamentos
  where not laboratorio='Bayer' and
  cantidad=100;

 select nombre from medicamentos
  where precio>=2 and
  precio <=5;

 delete from medicamentos
  where laboratorio='Bayer' and
  precio>10;

 update medicamentos set cantidad=200
  where laboratorio='Roche' and
  precio>5;

 select *from medicamentos;

 delete from medicamentos
  where laboratorio='Bayer' or
  precio<3;
  
  /*EJERCICIO 25 PARTE 2///////////////////////////////////////////////////////////////*/
    create table peliculas(
      codigo number(4),
      titulo varchar2(40) not null,
      actor varchar2(20),
      duracion number(3),
      primary key (codigo)
    );

 insert into peliculas
  values(1020,'Mision imposible','Tom Cruise',120);
 insert into peliculas
  values(1021,'Harry Potter y la piedra filosofal','Daniel R.',180);
 insert into peliculas
  values(1022,'Harry Potter y la camara secreta','Daniel R.',190);
 insert into peliculas
  values(1200,'Mision imposible 2','Tom Cruise',120);
 insert into peliculas
  values(1234,'Mujer bonita','Richard Gere',120);
 insert into peliculas
  values(900,'Tootsie','D. Hoffman',90);
 insert into peliculas
  values(1300,'Un oso rojo','Julio Chavez',100);
 insert into peliculas
  values(1301,'Elsa y Fred','China Zorrilla',110);

 select *from peliculas
  where actor='Tom Cruise' or
  actor='Richard Gere';

 select *from peliculas
  where actor='Tom Cruise' and
  duracion<100;

 select titulo from peliculas
  where duracion>=100 and
  duracion<=120;

 update peliculas set duracion=200
  where actor='Daniel R.' and
  duracion=180;

 select *from peliculas;

 delete from peliculas
  where not actor='Tom Cruise' and
  duracion<=100;
  
   /*EJERCICIO 26 PARTE 1///////////////////////////////////////////////////////////////*/
    create table visitas (
      nombre varchar2(30) default 'Anonimo',
      mail varchar2(50),
      pais varchar2(20),
      fecha date
    );

 insert into visitas
  values ('Ana Maria Lopez','AnaMaria@hotmail.com','Argentina','10/10/2016');
 insert into visitas
  values ('Gustavo Gonzalez','GustavoGGonzalez@gotmail.com','Chile','10/10/2016');
 insert into visitas
  values ('Juancito','JuanJosePerez@hotmail.com','Argentina','11/10/2016');
 insert into visitas 
  values ('Fabiola Martinez','MartinezFabiola@hotmail.com','Mexico','12/10/2016');
 insert into visitas
  values ('Fabiola Martinez','MartinezFabiola@hotmail.com','Mexico','12/09/2016');
 insert into visitas
  values ('Juancito','JuanJosePerez@gmail.com','Argentina','12/09/2016');
 insert into visitas 
  values ('Juancito','JuanJosePerez@hotmail.com','Argentina','15/09/2016');
 insert into visitas 
  values ('Federico1','federicogarcia@xaxamail.com','Argentina',null);

 select *from visitas
  where fecha between '12/09/2016' and '11/10/2016';
  
   /*EJERCICIO 26 PARTE 2///////////////////////////////////////////////////////////////*/
    create table medicamentos(
      codigo number(6) not null,
      nombre varchar2(20),
      laboratorio varchar2(20),
      precio number(6,2),
      cantidad number(4),
      fechavencimiento date not null,
      primary key(codigo)
    );

 insert into medicamentos
  values(102,'Sertal','Roche',5.2,10,'01/02/2020');
 insert into medicamentos 
  values(120,'Buscapina','Roche',4.10,200,'01/12/2017');
 insert into medicamentos 
  values(230,'Amoxidal 500','Bayer',15.60,100,'28/12/2017');
 insert into medicamentos
  values(250,'Paracetamol 500','Bago',1.90,20,'01/02/2018');
 insert into medicamentos 
  values(350,'Bayaspirina','Bayer',2.10,150,'01/12/2019'); 
 insert into medicamentos 
  values(456,'Amoxidal jarabe','Bayer',5.10,250,'01/10/2020'); 

 select nombre,precio from medicamentos
  where precio between 5 and 15;

 select *from medicamentos
  where cantidad between 100 and 200;

 select *from medicamentos
  where fechavencimiento between sysdate and '01/01/2028';

 delete from medicamentos
  where extract(year from fechavencimiento) between '2017' and '2018';
  
   /*EJERCICIO 27 PARTE 1///////////////////////////////////////////////////////////////*/
    create table medicamentos(
      codigo number(5),
      nombre varchar2(20),
      laboratorio varchar2(20),
      precio number(6,2),
      cantidad number(3) not null,
      fechavencimiento date not null,
      primary key(codigo)
    );

 insert into medicamentos
  values(100,'Sertal','Roche',5.2,1,'01/02/2015');
 insert into medicamentos 
  values(230,'Buscapina',null,4.10,3,'01/03/2016');
 insert into medicamentos 
  values(280,'Amoxidal 500','Bayer',15.60,100,'01/05/2017');
 insert into medicamentos
  values(301,'Paracetamol 500','Bago',1.90,10,'01/02/2018');
 insert into medicamentos 
  values(400,'Bayaspirina','Bayer',2.10,150,'01/08/2019'); 
 insert into medicamentos 
  values(560,'Amoxidal jarabe','Bayer',5.10,250,'01/10/2020'); 

 select nombre,precio from medicamentos
  where laboratorio in ('Bayer','Bago');

 select nombre,precio from medicamentos
  where laboratorio not in ('Bayer','Bago');

 select *from medicamentos
  where cantidad between 1 and 5;
 select *from medicamentos
  where cantidad in (1,2,3,4,5);

 select *from medicamentos
  where fechavencimiento between '01/01/2015' and '01/01/2017';

 select *from medicamentos
  where extract(year from fechavencimiento) in (2015,2016);
  
  /*EJERCICIO 28 PARTE 1///////////////////////////////////////////////////////////////*/
    create table empleados(
      nombre varchar2(30),
      documento char(8) not null,
      domicilio varchar2(30),
      fechaingreso date,
      seccion varchar2(20),
      sueldo number(6,2),
      primary key(documento)
    );

 insert into empleados
  values('Juan Perez','22333444','Colon 123','08/10/1990','Gerencia',900.50);
 insert into empleados
  values('Ana Acosta','23444555','Caseros 987','18/12/1995','Secretaria',590.30);
 insert into empleados
  values('Lucas Duarte','25666777','Sucre 235','15/05/2005','Sistemas',790);
 insert into empleados
  values('Pamela Gonzalez','26777888','Sarmiento 873','12/02/1999','Secretaria',550);
 insert into empleados
  values('Marcos Juarez','30000111','Rivadavia 801','22/09/2002','Contaduria',630.70);
 insert into empleados
  values('Yolanda perez','35111222','Colon 180','08/10/1990','Administracion',400);
 insert into empleados
  values('Rodolfo perez','35555888','Coronel Olmedo 588','28/05/1990','Sistemas',800);

 select *from empleados
  where nombre like '%Perez%';

 select *from empleados
  where domicilio like 'Co%8%';

 select *from empleados
  where documento like '%1' or
  documento like '%4';

 select *from empleados
  where documento not like '2%' and
  nombre like '%ez';

 select nombre from empleados
  where nombre like '%G%' or
   nombre like '%J%';

 select nombre,seccion from empleados
  where seccion like 'S_______' or
  seccion like 'G_______';

 select nombre,seccion from empleados
  where seccion not like 'S%';

 select nombre,sueldo from empleados
  where sueldo like '5__%';

 select *from empleados
  where fechaingreso like '%9_';

 update empleados set sueldo=sueldo+0.50
  where sueldo not like '%,%';
 select sueldo from empleados;

 delete from empleados
  where nombre like '% p%';