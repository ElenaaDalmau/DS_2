package edu.ub.ds2627.anotub.model;

import java.util.Objects;

public final class Categoria {
    private final String nom;

    public Categoria(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Categoria categoria)) {
            return false;
        }
        return Objects.equals(nom, categoria.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return nom;
    }
}
