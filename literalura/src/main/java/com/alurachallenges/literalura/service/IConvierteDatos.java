package com.alurachallenges.literalura.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String datosJson, Class<T> tClass);
}
