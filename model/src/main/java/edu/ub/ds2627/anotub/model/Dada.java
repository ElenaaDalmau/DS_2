package edu.ub.ds2627.anotub.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// MD: qualsevol cosa anotable amb categories (dades completes o regions)
public abstract class Dada {

    private boolean anotada;
    private final List<Categoria> etiquetes;

    protected Dada() {
        this.anotada = false;
        this.etiquetes = new ArrayList<>();
    }

    public boolean isAnotada() {
        return anotada;
    }

    public void marcarAnotada() {
        this.anotada = true;
    }

    public void afegirEtiqueta(Categoria etiqueta) {
        if (!etiquetes.contains(etiqueta)) {
            etiquetes.add(etiqueta);
        }
    }

    public List<Categoria> getEtiquetes() {
        return Collections.unmodifiableList(etiquetes);
    }
}
