package edu.ub.ds2627.anotub.model.excepcions;

public class TipusEmpresaDesconegutException extends AnotUBException {
  private final String tipus;

  public TipusEmpresaDesconegutException(String tipus) {
    super(tipus);
    this.tipus = tipus;
  }

  public String getTipus() {
    return tipus;
  }
}
