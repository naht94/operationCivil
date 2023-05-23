package com.nhnacademy.operationcivil.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.nhnacademy.operationcivil.**",
excludeFilters = @ComponentScan.Filter(Controller.class))
@RequiredArgsConstructor
public class RootConfig {
    private final DatabaseProperties databaseProperties;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(databaseProperties.getDriverClassName());
        dataSource.setUrl(databaseProperties.getUrl());
        dataSource.setUsername(databaseProperties.getUserName());
        dataSource.setPassword(databaseProperties.getPassword());

        dataSource.setInitialSize(databaseProperties.getInitialSize());
        dataSource.setMaxTotal(databaseProperties.getMaxTotal());
        dataSource.setMinIdle(databaseProperties.getMinIdle());
        dataSource.setMaxIdle(databaseProperties.getMaxIdle());

        dataSource.setMaxWaitMillis(databaseProperties.getMaxWaitMillis());

        dataSource.setTestOnBorrow(databaseProperties.isTestOnBorrow());
        dataSource.setTestOnReturn(databaseProperties.isTestOnReturn());
        dataSource.setTestWhileIdle(databaseProperties.isTestWhileIdle());


        return dataSource;
    }

    @Bean
    public DataSourceInitializer firstDataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(
                new ClassPathResource(databaseProperties.getSchemaPath())
        ));
        initializer.setEnabled(true);
        initializer.afterPropertiesSet();
        return initializer;
    }

    @Bean
    public ObjectMapper jsonObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    @Bean
    public ObjectMapper xmlObjectMapper() {
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}
