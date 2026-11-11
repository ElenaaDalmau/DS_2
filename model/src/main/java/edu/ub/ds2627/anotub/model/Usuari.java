package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.ContrasenyaNoSeguraException;

// Dades comunes d'anotadores i empreses (MD: Usuari)
public abstract class Usuari {
  private final String nomUsuari;
  private final Contrassenya contrasenya;
  private final InformacioContacte contacte;

  protected Usuari(String nomUsuari, String contrasenya, String email, String telefon)
      throws ContrasenyaNoSeguraException {
    this.nomUsuari = nomUsuari;
    this.contrasenya = new Contrassenya(contrasenya);
    this.contacte = new InformacioContacte(email, telefon);
  }

  public String getNomUsuari() {
    return nomUsuari;
  }

  public boolean contrasenyaCorrecta(String contrasenya) {
    return this.contrasenya.equals(contrasenya);
  }

  public InformacioContacte getContacte() {
    return contacte;
  }
}
