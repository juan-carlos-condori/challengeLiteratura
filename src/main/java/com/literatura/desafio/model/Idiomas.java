package com.literatura.desafio.model;

public enum Idiomas {
    desconocido("nd"),
    español("es"),
    inglés("en"),
    francés("fr"),
    italiano("it");

    private String idiomasLibro;

    Idiomas (String idiomasLibro) {
        this.idiomasLibro = idiomasLibro;
    }

    public static Idiomas fromString(String text) {
        for(Idiomas idiomas : Idiomas.values()) {
            if(idiomas.idiomasLibro.equalsIgnoreCase(text)) {
                return idiomas;
            }
        }
        throw new IllegalArgumentException("idioma no encontrado " + text);
    }
}
