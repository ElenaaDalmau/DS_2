package edu.ub.ds2627.anotub.model.excepcions;

public class AtributFaltantException extends AnotUBException {
  private final String atribut;

  public AtributFaltantException(String atribut) {
    super(atribut);
    this.atribut = atribut;
  }

  public String getAtribut() {
    return atribut;
  }
}
