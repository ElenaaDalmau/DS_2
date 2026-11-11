package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.TascaNoDisponibleException;

public class TascaAssignada extends EstatTasca {

    @Override
    public boolean esAssignada() {
        return true;
    }

    @Override
    public EstatTasca assignar() throws TascaNoDisponibleException {
        throw new TascaNoDisponibleException();
    }

    @Override
    public EstatTasca completar() {
        return new TascaCompletada();
    }
}