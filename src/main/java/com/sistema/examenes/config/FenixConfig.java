package com.sistema.examenes.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryFenix", transactionManagerRef = "transactionManagerFenix", basePackages = {
        "com.sistema.examenes.fenix.repository" }// La posición de la capa Dao/Repository
)
public class FenixConfig {

    @Autowired
    @Qualifier("fenixDataSource")
    private DataSource fenixDataSource;

    @Autowired
    @Qualifier("vendorProperties")
    private Map<String, Object> vendorProperties;

    @Bean(name = "entityManagerFactoryFenix")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(fenixDataSource)
                .properties(vendorProperties)
                .packages("com.sistema.examenes.fenix.entity")// La ubicación de la clase de entidad
                .persistenceUnit("fenixPersistenceUnit")
                .build();
    }

    /**
     * @param builder
     * @param object  TODO
     * @return
     */
    @Bean(name = "entityManagerFenix")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder, EntityManagerFactory object) {
        return object.createEntityManager();
    }

    @Bean(name = "transactionManagerFenix")
    PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
    }

}
