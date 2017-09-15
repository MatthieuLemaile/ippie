package com.mlemaile.ippie.config;


import javax.servlet.Filter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
@ComponentScan(basePackages = { "com.mlemaile.ippie" })
@EnableTransactionManagement
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Filter[] getServletFilters() {
      return new Filter[] {
                new CharacterEncodingFilter("UTF-8", true)
      };
    }

    @Override
    protected Class<?>[] getRootConfigClasses () {
        return new Class[] { SpringRootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses () {
        return new Class[] { SpringWebConfig.class };
    }

    @Override
    protected String[] getServletMappings () {
        return new String[] { "/" };
    }

}