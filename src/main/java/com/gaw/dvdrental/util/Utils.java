package com.gaw.dvdrental.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    @Autowired
    ObjectMapper objectMapper;

    public <T> void printJson(T t) {
        try {
            System.out.println(">>>>> " + t.getClass().getName());
            System.out.println(objectMapper.writeValueAsString(t));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
