package edu.ub.ds2627.anotub.model;

import java.util.Objects;

public final class Expertesa {
  private final String nom;

  public Expertesa(String nom) {
    this.nom = nom;
  }

  public String getNom() {
    return nom;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Expertesa expertesa)) {
      return false;
    }
    return nom.equalsIgnoreCase(expertesa.nom);
  }

  @Override
  public int hashCode() {
    return nom.toLowerCase().hashCode();
  }

  @Override
  public String toString() {
    return nom;
  }
}
