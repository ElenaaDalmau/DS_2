package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.AtributFaltantException;
import edu.ub.ds2627.anotub.model.excepcions.ContrasenyaNoSeguraException;

import java.time.LocalDate;
import java.util.List;

public class Anotadora {
  private final String nomUsuari;
  private final Contrassenya contrasenya;
  private final Passaport passaport;
  private final CompteBancari compteBancari;
  private final InformacioContacte contacte;
  private final List<Expertesa> experteses;

  public Anotadora(
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
  ) throws ContrasenyaNoSeguraException {
    this.passaport = new Passaport(
        passaportNom,
        passaportCognoms,
        passaportPais,
        passaportNumero,
        passaportCaducitat
    );
    this.compteBancari = new CompteBancari(compteIban, compteSwift);
    this.contacte = new InformacioContacte(email, telefon);

    this.nomUsuari = nomUsuari;
    this.contrasenya = new Contrassenya(contrasenya);
    this.experteses = experteses.stream().map(Expertesa::new).toList();
  }

  public String getNomUsuari() {
    return nomUsuari;
  }

  public boolean contrasenyaCorrecta(String contrasenya) {
    return this.contrasenya.equals(contrasenya);
  }

}
