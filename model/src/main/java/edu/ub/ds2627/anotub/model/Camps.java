package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.AtributFaltantException;

import java.util.List;

final class Camps {
  /*
    * Aquesta classe conté mètodes utilitaris per a la validació de camps.
    * No es pot instanciar.
  */
  private Camps() {
  }

  static boolean esBuit(String valor) {
    return valor == null || valor.isBlank();
  }

  static void exigir(String valor, String nomAtribut) throws AtributFaltantException {
    if (esBuit(valor)) {
      throw new AtributFaltantException(nomAtribut);
    }
  }

  static void exigir(Object valor, String nomAtribut) throws AtributFaltantException {
    if (valor == null) {
      throw new AtributFaltantException(nomAtribut);
    }
  }

  static void exigirLlista(List<String> valors, String nomAtribut) throws AtributFaltantException {
    if (valors == null || valors.isEmpty() || valors.stream().anyMatch(Camps::esBuit)) {
      throw new AtributFaltantException(nomAtribut);
    }
  }
}
