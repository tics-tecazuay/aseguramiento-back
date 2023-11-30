package com.sistema.examenes.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryPracticas", transactionManagerRef = "transactionManagerPracticas", basePackages = {
        "com.sistema.examenes.repository" } // La posición de la capa Dao/Repository
)
public class PracticasConfig {

    @Autowired
    @Qualifier("practicasDataSource")
    private DataSource practicasDataSource;

    @Autowired
    @Qualifier("vendorProperties")
    private Map<String, Object> vendorProperties;

    @Bean(name = "entityManagerFactoryPracticas")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(practicasDataSource)
                .properties(vendorProperties)
                .packages("com.sistema.examenes.entity")// La ubicación de la clase de entidad
                .persistenceUnit("practicasPersistenceUnit")
                .build();
    }

    @Bean(name = "entityManagerPracticas")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder, EntityManagerFactory object) {
        return object.createEntityManager();
    }

    @Bean(name = "transactionManagerPracticas")
    @Primary
    PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}
