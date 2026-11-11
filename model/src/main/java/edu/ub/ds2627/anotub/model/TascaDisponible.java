package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.TascaNoAssignadaException;

public class TascaDisponible extends EstatTasca {

    @Override
    public boolean esDisponible() {
        return true;
    }

    @Override
    public EstatTasca assignar() {
        return new TascaAssignada();
    }

    @Override
    public EstatTasca completar() throws TascaNoAssignadaException {
        throw new TascaNoAssignadaException();
    }
}