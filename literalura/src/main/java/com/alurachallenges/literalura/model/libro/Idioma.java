package com.alurachallenges.literalura.model.libro;

public enum Idioma {

    ESPANOL ("es"),

    INGLES ("en"),

    FRANCES ("fr"),

    PORTUGUES ("pt");

    private String idiomaApi;

    Idioma(String siglas){
        this.idiomaApi = siglas;
    }

    public static Idioma fromString(String siglas){
        for (Idioma idioma : Idioma.values()){
            if (idioma.idiomaApi.equalsIgnoreCase((siglas))){
                return idioma;
            }
        }

        throw new IllegalArgumentException("Idioma no detectado " + siglas);
    }
}
