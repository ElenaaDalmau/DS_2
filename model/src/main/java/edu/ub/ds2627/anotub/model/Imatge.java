package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.DimensionsImatgeNoValidesException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Imatge extends DadaCompleta {
    private final int amplada;
    private final int alcada;
    private final List<Pixel> pixels;

    public Imatge(String titol, int amplada, int alcada) throws DimensionsImatgeNoValidesException {
        super(titol);
        if (amplada <= 0 || alcada <= 0) {
            throw new DimensionsImatgeNoValidesException();
        }
        this.amplada = amplada;
        this.alcada = alcada;
        this.pixels = new ArrayList<>();
        // Creador: la imatge es composa dels seus píxels
        for (int y = 0; y < alcada; y++) {
            for (int x = 0; x < amplada; x++) {
                pixels.add(new Pixel(new Punt2D(x, y), 0, 0, 0));
            }
        }
    }

    public int getAmplada() {
        return amplada;
    }

    public int getAlcada() {
        return alcada;
    }

    public List<Pixel> getPixels() {
        return Collections.unmodifiableList(pixels);
    }
}
