package com.gaw.dvdrental.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class BeanConfiguration {

    @Bean
    public MessageSource messageSource() {
        System.out.println("### messageSource");
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public ModelMapper modelMapper() {
        System.out.println("### modelMapper");
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        System.out.println("### objectMapper");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        objectMapper.writerWithDefaultPrettyPrinter();
        return objectMapper;
    }
}
