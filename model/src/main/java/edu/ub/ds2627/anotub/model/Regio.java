package edu.ub.ds2627.anotub.model;


public class Regio extends Dada {
    private final int id;
    private final Punt2D origen;
    private final Punt2D fi;

    public Regio(int id, Punt2D origen, Punt2D fi) {
        this.id = id;
        this.origen = origen;
        this.fi = fi;
    }

    public int getId() {
        return id;
    }

    public Punt2D getOrigen() {
        return origen;
    }

    public Punt2D getFi() {
        return fi;
    }
}
