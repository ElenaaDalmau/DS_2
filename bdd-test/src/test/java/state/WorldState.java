package state;

import edu.ub.ds2627.anotub.controlador.Controlador;

public class WorldState {
  public Controlador controlador;
  public String resultat;
  public String usuariActual;

  public WorldState() {
    controlador = new Controlador();
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
}
