package edu.ub.ds2627.anotub.controlador;

import edu.ub.ds2627.anotub.model.AnotUB;
import edu.ub.ds2627.anotub.model.Anotadora;
import edu.ub.ds2627.anotub.model.CalculQuotaEmpresa;
import edu.ub.ds2627.anotub.model.excepcions.AtributFaltantException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controlador {
  private final AnotUB anotUB;

  public Controlador() {
    this(new AnotUB());
  }

  public Controlador(AnotUB anotUB) {
    this.anotUB = anotUB;
  }

  public String registrarUsuari(
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
  ) {
    try {
      anotUB.registrarAnotadora(
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
      return MessagesCAT.RegistreAnotadoraCorrecte.getMessage();
    } catch (AtributFaltantException e) {
      return MessagesCAT.atributFaltantAnotadora(e);
    } catch (Exception e) {
      return MessagesCAT.translate(e);
    }
  }

  public Boolean estaRegistrat(String nomUsuari) {
    return anotUB.estaAnotadoraRegistrada(nomUsuari);
  }

  public void eliminarUsuari(String nomUsuari) {
  }

  public String identificarUsuari(String nomUsuari, String contrasenya) {
    try {
      anotUB.identificarAnotadora(nomUsuari, contrasenya);
      return MessagesCAT.IdentificacioCorrecta.getMessage();
    } catch (Exception e) {
      return MessagesCAT.translate(e);
    }
  }

  private final CalculQuotaEmpresa calculQuotaEmpresa =
          new CalculQuotaEmpresa();

  public String consultarQuotaEmpresa(String tipusEmpresa) {
    try {
      int quota = calculQuotaEmpresa.quotaAnual(tipusEmpresa);
      return "La quota anual és de " + quota + " euros.";
    } catch (IllegalArgumentException e) {
      return e.getMessage();
    }
  }
}
