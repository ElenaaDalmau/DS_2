package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.AnotadoraNoExistentException;
import edu.ub.ds2627.anotub.model.excepcions.AtributFaltantException;
import edu.ub.ds2627.anotub.model.excepcions.ContrasenyaIncorrectaException;
import edu.ub.ds2627.anotub.model.excepcions.ContrasenyaNoSeguraException;
import edu.ub.ds2627.anotub.model.excepcions.NomUsuariJaExistentException;

import java.time.LocalDate;
import java.util.List;

public class AnotUB {
  private final CarteraAnotadores anotadores;

  public AnotUB() {
    this.anotadores = new CarteraAnotadores();
  }

  public void registrarAnotadora(
      String nomUsuari,
      String contrasenya,
      String passaportNom,
      String passaportCognoms,
      String passaportPais,
      String passaportNumero,
      LocalDate passaportCaducitat,
      String compteIban,
      String compteSwift,
      String email,
      String telefon,
      List<String> experteses
  ) throws AtributFaltantException, NomUsuariJaExistentException, ContrasenyaNoSeguraException {
    if (!Camps.esBuit(nomUsuari) && anotadores.existeix(nomUsuari)) {
      throw new NomUsuariJaExistentException();
    }

    Camps.exigir(nomUsuari, "nomUsuari");
    Camps.exigir(contrasenya, "contrasenya");
    Camps.exigir(passaportNom, "passaportNom");
    Camps.exigir(passaportCognoms, "passaportCognoms");
    Camps.exigir(passaportPais, "passaportPais");
    Camps.exigir(passaportNumero, "passaportNumero");
    Camps.exigir(passaportCaducitat, "passaportCaducitat");
    Camps.exigir(compteIban, "compteIban");
    Camps.exigir(compteSwift, "compteSwift");
    Camps.exigir(email, "email");
    Camps.exigir(telefon, "telèfon");
    Camps.exigirLlista(experteses, "experteses");

    Anotadora anotadora = new Anotadora(
        nomUsuari,
        contrasenya,
        passaportNom,
        passaportCognoms,
        passaportPais,
        passaportNumero,
        passaportCaducitat,
        compteIban,
        compteSwift,
        email,
        telefon,
        experteses
    );
    anotadores.afegir(anotadora);
  }

  public void identificarAnotadora(String nomUsuari, String contrasenya)
      throws AnotadoraNoExistentException, ContrasenyaIncorrectaException {
    Anotadora anotadora = anotadores.obtenir(nomUsuari);
    if (!anotadora.contrasenyaCorrecta(contrasenya)) {
      throw new ContrasenyaIncorrectaException();
    }
  }

  public boolean estaAnotadoraRegistrada(String nomUsuari) {
    return anotadores.existeix(nomUsuari);
  }

  public boolean eliminarAnotadora(String nomUsuari) {
    return anotadores.eliminar(nomUsuari);
  }
}
