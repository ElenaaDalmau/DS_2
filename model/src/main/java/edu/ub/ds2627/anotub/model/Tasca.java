package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.CategoriaNoPermesaException;
import edu.ub.ds2627.anotub.model.excepcions.DadaNoExistentException;
import edu.ub.ds2627.anotub.model.excepcions.DimensionsImatgeNoValidesException;
import edu.ub.ds2627.anotub.model.excepcions.TipusDadaIncorrecteException;
import edu.ub.ds2627.anotub.model.excepcions.TipusEtiquetaNoPermesaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Tasca {
    private final String id;
    private final LocalDate dataLimit;
    private final EspecificacioTasca especificacio;
    private final Empresa empresa;
    private final List<DadaCompleta> dades;

    protected Tasca(String id, LocalDate dataLimit, EspecificacioTasca especificacio, Empresa empresa) {
        this.id = id;
        this.dataLimit = dataLimit;
        this.especificacio = especificacio;
        this.empresa = empresa;
        this.dades = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public LocalDate getDataLimit() {
        return dataLimit;
    }

    public EspecificacioTasca getEspecificacio() {
        return especificacio;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void afegirImatge(String titol, int amplada, int alcada)
            throws TipusDadaIncorrecteException, DimensionsImatgeNoValidesException {
        throw new TipusDadaIncorrecteException();
    }

    public void afegirDocument(String titol, String text) throws TipusDadaIncorrecteException {
        throw new TipusDadaIncorrecteException();
    }

    protected void afegirDada(DadaCompleta dada) {
        dades.add(dada);
    }

    public boolean satisfetaPer(List<Expertesa> experteses) {
        return experteses.stream().anyMatch(especificacio::requereix);
    }

    public <T> T mapejar(TascaMap<T> mapejador) {
        return mapejador.mapejar(id, especificacio.getTitol(),
                especificacio.getExpertesesRequerides().get(0).toString());
    }

    public DadaCompleta obtenirDada(String titol) throws DadaNoExistentException {
        return dades.stream()
                .filter(dada -> dada.teTitol(titol))
                .findFirst()
                .orElseThrow(DadaNoExistentException::new);
    }

    public void anotarEtiqueta(String titolDada, Categoria categoria)
            throws CategoriaNoPermesaException, DadaNoExistentException {
        if (!especificacio.imposa(categoria)) {
            throw new CategoriaNoPermesaException();
        }
        obtenirDada(titolDada).afegirEtiqueta(categoria);
    }

    public void anotarLocalitzacio(String titolDada, Categoria categoria) throws TipusEtiquetaNoPermesaException {
        throw new TipusEtiquetaNoPermesaException();
    }

    public void marcarDadaAnotada(String titolDada) throws DadaNoExistentException {
        obtenirDada(titolDada).marcarAnotada();
    }

    public boolean totesLesDadesAnotades() {
        return !dades.isEmpty() && dades.stream().allMatch(Dada::isAnotada);
    }

    public List<DadaCompleta> getDadesPendents() {
        return dades.stream().filter(dada -> !dada.isAnotada()).toList();
    }

    public double getRemuneracio() {
        return especificacio.getRemuneracioPerDada() * dades.size();
    }

    @Override
    public String toString() {
        return "{idTasca=" + id +
                ", descripcioTasca=" + especificacio.getTitol() +
                ", expertesaNecessaria=" + especificacio.getExpertesesRequerides().get(0).toString() + "}";
    }
}
