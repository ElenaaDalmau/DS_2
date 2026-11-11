package state;

import edu.ub.ds2627.anotub.controlador.AppContainer;
import edu.ub.ds2627.anotub.controlador.Controlador;

public class WorldState {
  private final Controlador controlador;
  private String resultat;
  private String usuariActual;

  public WorldState() {
    controlador = new AppContainer().getControlador();
  }

  public Controlador getControlador() {
    return controlador;
  }

  public String getResultat() {
    return resultat;
  }

  public void setResultat(String resultat) {
    this.resultat = resultat;
  }

  public String getUsuariActual() {
    return usuariActual;
  }

  public void setUsuariActual(String usuariActual) {
    this.usuariActual = usuariActual;
  }
}
