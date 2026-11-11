package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.TascaJaCompletadaException;
import edu.ub.ds2627.anotub.model.excepcions.TascaNoAssignadaException;
import edu.ub.ds2627.anotub.model.excepcions.TascaNoDisponibleException;

public abstract class EstatTasca {

    public boolean esDisponible() {
        return false;
    }

    public boolean esAssignada() {
        return false;
    }

    public boolean esCompletada() {
        return false;
    }

    public abstract EstatTasca assignar()
            throws TascaNoDisponibleException, TascaJaCompletadaException;

    public abstract EstatTasca completar()
            throws TascaNoAssignadaException, TascaJaCompletadaException;
}