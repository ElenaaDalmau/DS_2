package edu.ub.ds2627.anotub.model;

// Dada sencera que forma part d'una tasca (imatge o document); s'identifica pel títol
public abstract class DadaCompleta extends Dada {
    private final String titol;

    protected DadaCompleta(String titol) {
        this.titol = titol;
    }

    public String getTitol() {
        return titol;
    }

    public boolean teTitol(String titol) {
        return this.titol.equals(titol);
    }
}
