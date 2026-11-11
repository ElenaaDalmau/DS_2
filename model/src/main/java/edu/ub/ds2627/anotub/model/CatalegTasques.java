package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.TascaJaExistentException;
import edu.ub.ds2627.anotub.model.excepcions.TascaNoExistentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalegTasques {
  private final List<Tasca> tasques = new ArrayList<>();

  public Optional<Tasca> buscar(String idTasca) {
    return tasques.stream()
        .filter(tasca -> tasca.getId().equals(idTasca))
        .findFirst();
  }

  public Tasca obtenir(String idTasca) throws TascaNoExistentException {
    return buscar(idTasca).orElseThrow(TascaNoExistentException::new);
  }

  public boolean existeix(String idTasca) {
    return buscar(idTasca).isPresent();
  }

  public void afegir(Tasca tasca) throws TascaJaExistentException {
    if (existeix(tasca.getId())) {
      throw new TascaJaExistentException();
    }
    tasques.add(tasca);
  }

  public boolean eliminar(String idTasca) {
    return buscar(idTasca).map(tasques::remove).orElse(false);
  }

  public void eliminarTotesLesTasques() {
    tasques.clear();
  }
}
