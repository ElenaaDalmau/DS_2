package edu.ub.ds2627.anotub.model;

public class Document extends DadaCompleta {
    private final String text;

    public Document(String titol, String text) {
        super(titol);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
