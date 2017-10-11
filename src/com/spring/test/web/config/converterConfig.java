package com.spring.test.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.spring.test.web.dao.CategoryConverter;
import com.spring.test.web.service.KPACategoryService;

@EnableWebMvc
@Configuration

public class converterConfig extends WebMvcConfigurerAdapter{
	/* @Bean
	 public KPACategoryService kpaCategoryService(){
	        return new KPACategoryService();
	    }*/
	@Override
    public void addFormatters (FormatterRegistry registry) {
		System.out.println("CONVVVVVVV");
        //registry.addConverter(new CategoryConverter(kpaCategoryService()));
    }

}
