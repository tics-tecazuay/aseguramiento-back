# ProyectoACIBack
Dependencias
spring-boot-starter-data-jpa
spring-boot-starter-web
spring-boot-devtools
postgresql
spring-boot-starter-test
springdoc-openapi-ui -> version: 1.6.
org.springframework.boot
spring-boot-starter-security
io.jsonwebtoken
jjwt -> version: 0.9.1
jaxb-api -> version: 2.3.1
org.projectlombok
lombok
spring-boot-starter-mail

SGBD: Postgres 14


INSTALACIÓN
Requisitos previos:

Java Development Kit (JDK) 8 o superior instalado.
Base de datos compatible instalada (por ejemplo, MySQL, PostgreSQL).
Pasos de instalación:

Descarga el proyecto:

Descarga el proyecto backend desde el repositorio o el archivo proporcionado.

Configura las credenciales de la cuenta de correo:
Desde tu cuenta de gmail, crea una contraseña de aplicación.
En el archivo aplication.properties localiza email.sender y email.password,
en el primero "sender" debera colocar la cuenta de correo que estas configurando,
en el segundo "password" debera colocar la contraseña generada.

Configura la base de datos:

Crea una base de datos vacía en tu servidor de base de datos.
Actualiza las credenciales de acceso y otros parámetros de la base de datos en el archivo de configuración de la aplicación (por ejemplo, application.properties o application.yml).
Compila el proyecto:

Abre una terminal y navega hasta el directorio raíz del proyecto.
Ejecuta el comando ./mvnw clean package si estás utilizando Maven, o ./gradlew build si estás utilizando Gradle. Esto compilará el proyecto y generará un archivo JAR ejecutable.
Ejecuta el proyecto:

Ejecuta el comando java -jar nombre-del-archivo.jar, reemplazando "nombre-del-archivo" por el nombre real del archivo JAR generado en el paso anterior.
Verifica la instalación:

Una vez que el proyecto esté en ejecución, puedes probar su funcionamiento accediendo a las rutas o endpoints definidos en tu aplicación backend.
Puedes utilizar herramientas como Postman o un navegador web para enviar solicitudes HTTP y verificar las respuestas. ten encuenta que el proyecto 
usa una capa de seguridad con tokens, por lo que para probar los servicios deberas a)Generar un token y probarlos servicios enviandolo en el header de tu peticón
b) Desbloquear la capa de seguridad desde MySecurityConfig.java, reemplazando la linea 58 con ".antMatchers("/generate-token","/api/**","/usuarios/**","/swagger-ui/**","/v3/**","/archivo/upload","/archivo/{filename}","/archivo/listar","/send-email").permitAll()
"
