package com.sistema.examenes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("https://viaticos.gesinsoft.com") // Permitir solicitudes desde este origen
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permitir estos métodos HTTP
//                .allowedHeaders("Authorization", "Content-Type") // Permitir estas cabeceras.
//                .allowCredentials(true); // Permitir enviar credenciales (por ejemplo, cookies) desde el navegador
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://apps.tecazuay.edu.ec/")
//                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true);
    }

}
