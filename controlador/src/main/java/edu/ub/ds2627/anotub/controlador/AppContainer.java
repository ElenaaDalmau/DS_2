package edu.ub.ds2627.anotub.controlador;

import edu.ub.ds2627.anotub.model.AnotUB;

public class AppContainer {
  private final AnotUB anotUB;
  private final Controlador controlador;

  public AppContainer() {
    this.anotUB = new AnotUB();
    this.controlador = new Controlador(anotUB);
  }

  public AnotUB getAnotUB() {
    return anotUB;
  }

  public Controlador getControlador() {
    return controlador;
  }
}
