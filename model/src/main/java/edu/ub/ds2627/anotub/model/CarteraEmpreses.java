package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.EmpresaNoExistentException;
import edu.ub.ds2627.anotub.model.excepcions.NomUsuariJaExistentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarteraEmpreses {
  private final List<Empresa> empreses = new ArrayList<>();

  public Optional<Empresa> buscar(String nomUsuari) {
    return empreses.stream()
        .filter(empresa -> empresa.getNomUsuari().equals(nomUsuari))
        .findFirst();
  }

  public Empresa obtenir(String nomUsuari) throws EmpresaNoExistentException {
    return buscar(nomUsuari).orElseThrow(EmpresaNoExistentException::new);
  }

  public boolean existeix(String nomUsuari) {
    return buscar(nomUsuari).isPresent();
  }

  public void afegir(Empresa empresa) throws NomUsuariJaExistentException {
    if (existeix(empresa.getNomUsuari())) {
      throw new NomUsuariJaExistentException();
    }
    empreses.add(empresa);
  }
}
