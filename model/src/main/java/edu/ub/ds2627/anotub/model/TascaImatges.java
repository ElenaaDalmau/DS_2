package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.DimensionsImatgeNoValidesException;

import java.time.LocalDate;

public class TascaImatges extends Tasca {

    public TascaImatges(String id, LocalDate dataLimit, EspecificacioTasca especificacio, Empresa empresa) {
        super(id, dataLimit, especificacio, empresa);
    }

    @Override
    public void afegirImatge(String titol, int amplada, int alcada) throws DimensionsImatgeNoValidesException {
        afegirDada(new Imatge(titol, amplada, alcada));
    }
}
