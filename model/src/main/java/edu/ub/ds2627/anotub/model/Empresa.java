package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.ContrasenyaNoSeguraException;

public class Empresa extends Usuari {
  private final String nom;
  private final String paisFiscal;
  private final String vat;

  public Empresa(String nomUsuari, String contrasenya, String email, String telefon,
                 String nom, String paisFiscal, String vat) throws ContrasenyaNoSeguraException {
    super(nomUsuari, contrasenya, email, telefon);
    this.nom = nom;
    this.paisFiscal = paisFiscal;
    this.vat = vat;
  }

  public String getNom() {
    return nom;
  }

  public String getPaisFiscal() {
    return paisFiscal;
  }

  public String getVat() {
    return vat;
  }
}
