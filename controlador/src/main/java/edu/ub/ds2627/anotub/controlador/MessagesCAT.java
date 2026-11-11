package edu.ub.ds2627.anotub.controlador;

import edu.ub.ds2627.anotub.model.excepcions.AtributFaltantException;
import edu.ub.ds2627.anotub.model.excepcions.TipusEmpresaDesconegutException;

public enum MessagesCAT {
  NomUsuariJaExistentException("No s'ha pogut registrar l'usuari. Ja existeix un usuari amb aquest nom."),
  ContrasenyaNoSeguraException("L'usuari no s'ha pogut registrar perquè la contrasenya no és prou segura."),
  AnotadoraNoExistentException("No s'ha pogut identificar l'usuari. No existeix cap usuari amb aquest nom."),
  ContrasenyaIncorrectaException("No s'ha pogut identificar l'usuari. La contrasenya no és correcta."),
  RegistreAnotadoraCorrecte("L'usuari s'ha registrat amb èxit."),
  IdentificacioCorrecta("Identificació correcta."),
  TascaAfegidaCorrectament("La tasca s'ha afegit correctament."),
  ExpertesaAfegidaCorrectament("L'expertesa s'ha afegit correctament a l'anotadora."),
  CapTascaDisponible("No hi ha tasques disponibles en el sistema."),
  TascaNoExistentException("No existeix cap tasca amb aquest identificador."),
  TascaJaExistentException("Ja existeix una tasca amb aquest identificador."),
  TascaAssignadaCorrectament("La tasca s'ha assignat correctament."),
  TascaNoDisponibleException("La tasca no està disponible."),
  TascaJaCompletadaException("La tasca ja està completada."),
  TascaNoAssignadaException("L'anotadora no té aquesta tasca assignada."),
  DadaAfegidaCorrectament("La dada s'ha afegit correctament a la tasca."),
  DadaNoExistentException("No existeix cap dada amb aquest títol a la tasca."),
  DadaAnotadaCorrectament("La dada s'ha marcat com anotada."),
  EtiquetaAssignadaCorrectament("Assignacio d'etiqueta amb exit"),
  TipusEtiquetaNoPermesaException("Tipus d'etiqueta no permesa"),
  CategoriaNoPermesaException("Categoria no permesa per aquesta tasca"),
  RegistreEmpresaCorrecte("L'empresa s'ha registrat amb èxit."),
  EmpresaNoExistentException("No existeix cap empresa amb aquest nom d'usuari."),
  EspecificacioPublicadaCorrectament("L'especificació de la tasca s'ha publicat correctament."),
  EspecificacioJaExistentException("Ja existeix una especificació de tasca amb aquest títol."),
  EspecificacioNoExistentException("No existeix cap especificació de tasca amb aquest títol."),
  TipusDadaIncorrecteException("Aquesta tasca no admet aquest tipus de dada."),
  DimensionsImatgeNoValidesException("Les dimensions de la imatge no són vàlides.");

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

  public static String atributFaltant(AtributFaltantException e) {
    return "No s'ha pogut completar l'operació perquè hi faltava l'atribut '" + e.getAtribut() + "'.";
  }

  public static String quotaAnual(int quota) {
    return "La quota anual és de " + quota + " euros.";
  }

  public static String tipusEmpresaDesconegut(TipusEmpresaDesconegutException e) {
    return "Tipus d'empresa desconegut: " + e.getTipus();
  }

  public static String translate(Exception e) {
    if (e instanceof AtributFaltantException atributFaltant) {
      return atributFaltantAnotadora(atributFaltant);
    }
    if (e instanceof TipusEmpresaDesconegutException tipusDesconegut) {
      return tipusEmpresaDesconegut(tipusDesconegut);
    }
    try {
      return valueOf(e.getClass().getSimpleName()).getMessage();
    } catch (IllegalArgumentException ignored) {
      return e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage();
    }
  }
}
