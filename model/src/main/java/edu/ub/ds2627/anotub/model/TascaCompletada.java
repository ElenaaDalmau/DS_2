package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.TascaJaCompletadaException;

public class TascaCompletada extends EstatTasca {

    @Override
    public boolean esCompletada() {
        return true;
    }

    @Override
    public EstatTasca assignar() throws TascaJaCompletadaException {
        throw new TascaJaCompletadaException();
    }

    @Override
    public EstatTasca completar() throws TascaJaCompletadaException {
        throw new TascaJaCompletadaException();
    }
}