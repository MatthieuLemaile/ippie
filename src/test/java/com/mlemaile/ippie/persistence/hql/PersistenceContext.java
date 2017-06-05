package com.mlemaile.ippie.persistence.hql;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc // <mvc:annotation-driven />
@Configuration

@ImportResource("classpath:config.xml")
@ComponentScan(basePackages = { "com.mlemaile.ippie" })
public class PersistenceContext extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers ( ResourceHandlerRegistry registry ) {
    }

    @Override
    public void addViewControllers ( ViewControllerRegistry registry ) {
    }

}