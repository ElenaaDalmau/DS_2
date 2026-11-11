package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.EspecificacioJaExistentException;
import edu.ub.ds2627.anotub.model.excepcions.EspecificacioNoExistentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalegEspecificacions {
    private final List<EspecificacioTasca> especificacions = new ArrayList<>();

    public Optional<EspecificacioTasca> buscar(String titol) {
        return especificacions.stream()
                .filter(especificacio -> especificacio.teTitol(titol))
                .findFirst();
    }

    public EspecificacioTasca obtenir(String titol) throws EspecificacioNoExistentException {
        return buscar(titol).orElseThrow(EspecificacioNoExistentException::new);
    }

    public boolean existeix(String titol) {
        return buscar(titol).isPresent();
    }

    public void afegir(EspecificacioTasca especificacio) throws EspecificacioJaExistentException {
        if (existeix(especificacio.getTitol())) {
            throw new EspecificacioJaExistentException();
        }
        especificacions.add(especificacio);
    }
}
