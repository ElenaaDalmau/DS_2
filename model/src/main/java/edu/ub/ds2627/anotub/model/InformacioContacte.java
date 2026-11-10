package edu.ub.ds2627.anotub.model;


import java.util.Objects;

public final class InformacioContacte {
  private final String email;
  private final String telefon;

  public InformacioContacte(String email, String telefon)  {
    this.email = email;
    this.telefon = telefon;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof InformacioContacte that)) {
      return false;
    }
    return Objects.equals(email, that.email) && Objects.equals(telefon, that.telefon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }
}
