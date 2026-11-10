package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.ContrasenyaNoSeguraException;

public class Contrassenya {
  /*
    * Aquesta classe conté mètodes utilitaris per a la validació de la política de contrasenyes.
    * No es pot instanciar.
  */
  private static final int LONGITUD_MINIMA = 8;
  private final String contrasenya;

  public Contrassenya(String contrasenya) throws ContrasenyaNoSeguraException {
    validar(contrasenya);
    this.contrasenya = contrasenya;

  }

  public boolean equals(String contrasenya) {
    return this.contrasenya.equals(contrasenya);
  }

  public static void validar(String contrasenya) throws ContrasenyaNoSeguraException {
    if (contrasenya.length() < LONGITUD_MINIMA
        || contrasenya.chars().noneMatch(Character::isUpperCase)
        || contrasenya.chars().noneMatch(Character::isLowerCase)
        || contrasenya.chars().noneMatch(Character::isDigit)) {
      throw new ContrasenyaNoSeguraException();
    }
  }
}
