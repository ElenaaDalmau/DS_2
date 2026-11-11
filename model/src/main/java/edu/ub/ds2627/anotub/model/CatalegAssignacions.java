package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.TascaNoExistentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalegAssignacions {
    private final List<Assignacio> assignacions = new ArrayList<>();

    public void registrarAssignacio(Assignacio assignacio) {
        assignacions.add(assignacio);
    }

    public Optional<Assignacio> buscarPerTasca(String idTasca) {
        return assignacions.stream()
                .filter(a -> a.getTasca().getId().equals(idTasca))
                .findFirst();
    }

    public Assignacio obtenirPerTasca(String idTasca) throws TascaNoExistentException {
        return buscarPerTasca(idTasca).orElseThrow(TascaNoExistentException::new);
    }

    public boolean hiHaAlgunaDisponible() {
        return assignacions.stream().anyMatch(Assignacio::estaDisponible);
    }

    public List<Tasca> obtenirTasquesDisponiblesPerExperteses(List<Expertesa> experteses) {
        return assignacions.stream()
                .filter(Assignacio::estaDisponible)
                .map(Assignacio::getTasca)
                .filter(tasca -> tasca.satisfetaPer(experteses))
                .toList();
    }

    public List<Assignacio> obtenirCompletadesDe(Anotadora anotadora) {
        return assignacions.stream()
                .filter(a -> a.estaCompletada() && a.esDe(anotadora))
                .toList();
    }

    public List<Assignacio> obtenirCompletadesDe(Anotadora anotadora, List<String> idsTasques) {
        return obtenirCompletadesDe(anotadora).stream()
                .filter(a -> idsTasques.contains(a.getTasca().getId()))
                .toList();
    }

    public boolean eliminarPerTasca(String idTasca) {
        return buscarPerTasca(idTasca).map(assignacions::remove).orElse(false);
    }

    public void eliminarTotes() {
        assignacions.clear();
    }
}
