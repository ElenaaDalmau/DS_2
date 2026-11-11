package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.ContrasenyaNoSeguraException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Anotadora extends Usuari {
  private final Passaport passaport;
  private final CompteBancari compteBancari;
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
    super(nomUsuari, contrasenya, email, telefon);
    this.passaport = new Passaport(
        passaportNom,
        passaportCognoms,
        passaportPais,
        passaportNumero,
        passaportCaducitat
    );
    this.compteBancari = new CompteBancari(compteIban, compteSwift);
    this.experteses = new ArrayList<>(experteses.stream().map(Expertesa::new).toList());
  }

  public List<Expertesa> getExperteses() {
    return Collections.unmodifiableList(experteses);
  }

  // Expert: l'anotadora gestiona les seves pròpies experteses
  public void afegirExpertesa(Expertesa expertesa) {
    if (!experteses.contains(expertesa)) {
      experteses.add(expertesa);
    }
  }
}
