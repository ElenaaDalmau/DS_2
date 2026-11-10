package stepdefinitions;

import io.cucumber.java.en.Then;
import state.WorldState;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SistemaStepDefs {
  private final WorldState state;

  public SistemaStepDefs(WorldState state) {
    this.state = state;
  }

  @Then("el sistema mostra un missatge {string}")
  public void elSistemaMostraUnMissatge(String missatgeEsperat) {
    assertEquals(missatgeEsperat, state.getResultat());
  }
}
