package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.AnotadoraNoExistentException;
import edu.ub.ds2627.anotub.model.excepcions.NomUsuariJaExistentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarteraTasques {
  private final List<Anotadora> tasques = new ArrayList<>();






  public Optional<Anotadora> buscar(String nomUsuari) {
    return tasques.stream()
        .filter(tasques -> tasques
        .findFirst();
  }





  public Anotadora obtenir(String nomUsuari) throws AnotadoraNoExistentException {
    return buscar(nomUsuari).orElseThrow(AnotadoraNoExistentException::new);
  }

  public boolean existeix(String nomUsuari) {
    return buscar(nomUsuari).isPresent();
  }

  public void afegir(Anotadora anotadora) throws NomUsuariJaExistentException {
    if (existeix(anotadora.getNomUsuari())) {
      throw new NomUsuariJaExistentException();
    }
    anotadores.add(anotadora);
  }

  public boolean eliminar(String nomUsuari) {
    return buscar(nomUsuari).map(anotadores::remove).orElse(false);
  }
}
