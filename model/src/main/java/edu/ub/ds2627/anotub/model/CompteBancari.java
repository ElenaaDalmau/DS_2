package edu.ub.ds2627.anotub.model;

import java.util.Objects;

public final class CompteBancari {
  private final String iban;
  private final String swift;

  public CompteBancari(String iban, String swift)  {
    this.iban = iban;
    this.swift = swift;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CompteBancari that)) {
      return false;
    }
    return Objects.equals(iban, that.iban);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iban);
  }
}
