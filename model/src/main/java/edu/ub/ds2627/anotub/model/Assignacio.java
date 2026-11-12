package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.CategoriaNoPermesaException;
import edu.ub.ds2627.anotub.model.excepcions.DadaNoExistentException;
import edu.ub.ds2627.anotub.model.excepcions.TascaJaCompletadaException;
import edu.ub.ds2627.anotub.model.excepcions.TascaNoAssignadaException;
import edu.ub.ds2627.anotub.model.excepcions.TascaNoDisponibleException;
import edu.ub.ds2627.anotub.model.excepcions.TipusEtiquetaNoPermesaException;

import java.util.List;

public class Assignacio {
    private final Tasca tasca;
    private Anotadora anotadora;
    private EstatTasca estat;

    public Assignacio(Tasca tasca, EstatTasca estatInicial) {
        this.tasca = tasca;
        this.estat = estatInicial;
    }

    public Tasca getTasca() {
        return tasca;
    }

    public Anotadora getAnotadora() {
        return anotadora;
    }

    public EstatTasca getEstat() {
        return estat;
    }

    public boolean estaDisponible() {
        return estat.esDisponible();
    }

    public boolean estaCompletada() {
        return estat.esCompletada();
    }

    public boolean esDe(Anotadora anotadora) {
        return this.anotadora != null && this.anotadora == anotadora;
    }

    public boolean estaAssignadaA(Anotadora anotadora) {
        return estat.esAssignada() && esDe(anotadora);
    }

    public void assignarA(Anotadora anotadora)
            throws TascaNoDisponibleException, TascaJaCompletadaException {
        this.estat = this.estat.assignar();
        this.anotadora = anotadora;
    }

    public void completar()
            throws TascaNoAssignadaException, TascaJaCompletadaException {
        this.estat = this.estat.completar();
    }

    public void anotarEtiqueta(Anotadora anotadora, String titolDada, Categoria categoria)
            throws TascaNoAssignadaException, TascaJaCompletadaException,
            CategoriaNoPermesaException, DadaNoExistentException {
        comprovarAssignadaA(anotadora);
        tasca.anotarEtiqueta(titolDada, categoria);
    }

    public void anotarLocalitzacio(Anotadora anotadora, String titolDada, Categoria categoria)
            throws TascaNoAssignadaException, TascaJaCompletadaException, TipusEtiquetaNoPermesaException {
        comprovarAssignadaA(anotadora);
        tasca.anotarLocalitzacio(titolDada, categoria);
    }

    // Quan s'anota l'última dada, la tasca passa a completada
    public void marcarDadaAnotada(Anotadora anotadora, String titolDada)
            throws TascaNoAssignadaException, TascaJaCompletadaException, DadaNoExistentException {
        comprovarAssignadaA(anotadora);
        tasca.marcarDadaAnotada(titolDada);
        if (tasca.totesLesDadesAnotades()) {
            completar();
        }
    }

    public List<DadaCompleta> obtenirDadesPendents(Anotadora anotadora)
            throws TascaNoAssignadaException, TascaJaCompletadaException {
        comprovarAssignadaA(anotadora);
        return tasca.getDadesPendents();
    }

    public double calcularRemuneracio() {
        return estaCompletada() ? tasca.getRemuneracio() : 0.0;
    }

    private void comprovarAssignadaA(Anotadora anotadora)
            throws TascaNoAssignadaException, TascaJaCompletadaException {
        if (estaCompletada()) {
            throw new TascaJaCompletadaException();
        }
        if (!estaAssignadaA(anotadora)) {
            throw new TascaNoAssignadaException();
        }
    }
}
