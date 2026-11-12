package edu.ub.ds2627.anotub.model;

import java.time.LocalDate;

public class TascaDocuments extends Tasca {

    public TascaDocuments(String id, LocalDate dataLimit, EspecificacioTasca especificacio, Empresa empresa) {
        super(id, dataLimit, especificacio, empresa);
    }

    @Override
    public void afegirDocument(String titol, String text) {
        afegirDada(new Document(titol, text));
    }
}
