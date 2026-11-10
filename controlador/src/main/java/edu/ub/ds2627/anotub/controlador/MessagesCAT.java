package edu.ub.ds2627.anotub.controlador;

import edu.ub.ds2627.anotub.model.excepcions.AtributFaltantException;

public enum MessagesCAT {
  NomUsuariJaExistentException("No s'ha pogut registrar l'usuari. Ja existeix un usuari amb aquest nom."),
  ContrasenyaNoSeguraException("L'usuari no s'ha pogut registrar perquè la contrasenya no és prou segura."),
  AnotadoraNoExistentException("No s'ha pogut identificar l'usuari. No existeix cap usuari amb aquest nom."),
  ContrasenyaIncorrectaException("No s'ha pogut identificar l'usuari. La contrasenya no és correcta."),
  RegistreAnotadoraCorrecte("L'usuari s'ha registrat amb èxit."),
  IdentificacioCorrecta("Identificació correcta.");

  private final String message;

  MessagesCAT(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public static String atributFaltantAnotadora(AtributFaltantException e) {
    return "L'usuari no s'ha pogut registrar perquè hi faltava l'atribut '" + e.getAtribut() + "'.";
  }

  public static String translate(Exception e) {
    if (e instanceof AtributFaltantException atributFaltant) {
      return atributFaltantAnotadora(atributFaltant);
    }
    try {
      return valueOf(e.getClass().getSimpleName()).getMessage();
    } catch (IllegalArgumentException ignored) {
      return e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage();
    }
  }
}
