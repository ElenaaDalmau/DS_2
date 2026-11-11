package edu.ub.ds2627.anotub.model;

import java.util.List;

// Part immutable i reutilitzable: una mateixa especificació descriu diverses tasques (variacions protegides)
public class EspecificacioTasca {
    private final String titol;
    private final List<Expertesa> experteses;
    private final List<Categoria> categories;
    private final List<Instruccio> instruccions;
    private final double remuneracioPerDada;

    public EspecificacioTasca(String titol, List<String> experteses, List<String> categories,
                              List<String> instruccions, double remuneracioPerDada) {
        this.titol = titol;
        this.experteses = experteses.stream().map(Expertesa::new).toList();
        this.categories = categories.stream().map(Categoria::new).toList();
        this.instruccions = instruccions.stream().map(Instruccio::new).toList();
        this.remuneracioPerDada = remuneracioPerDada;
    }

    public String getTitol() {
        return titol;
    }

    public boolean teTitol(String titol) {
        return this.titol.equals(titol);
    }

    public List<Expertesa> getExpertesesRequerides() {
        return experteses;
    }

    public boolean requereix(Expertesa expertesa) {
        return experteses.contains(expertesa);
    }

    public List<Categoria> getCategories() {
        return categories;
    }

    public boolean imposa(Categoria categoria) {
        return categories.contains(categoria);
    }

    public List<Instruccio> getInstruccions() {
        return instruccions;
    }

    public double getRemuneracioPerDada() {
        return remuneracioPerDada;
    }
}
