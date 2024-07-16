package com.alurachallenges.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String datosJson, Class<T> tClass) {
        try {
            T cualquierCosa = objectMapper.readValue(datosJson, tClass);
            System.out.println(cualquierCosa);
            return cualquierCosa;
        }catch (JsonProcessingException e){
            System.out.println("lo que sea");
            throw new RuntimeException(e);
        }
    }
}
