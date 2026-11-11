package edu.ub.ds2627.anotub.model;

public final class Pixel {
    private final Punt2D punt;
    private final int vermell;
    private final int verd;
    private final int blau;

    public Pixel(Punt2D punt, int vermell, int verd, int blau) {
        this.punt = punt;
        this.vermell = vermell;
        this.verd = verd;
        this.blau = blau;
    }

    public Punt2D getPunt() {
        return punt;
    }

    public int getVermell() {
        return vermell;
    }

    public int getVerd() {
        return verd;
    }

    public int getBlau() {
        return blau;
    }
}
