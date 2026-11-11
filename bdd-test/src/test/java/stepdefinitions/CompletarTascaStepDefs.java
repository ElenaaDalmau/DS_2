package stepdefinitions;

import edu.ub.ds2627.anotub.controlador.Controlador;
import edu.ub.ds2627.anotub.controlador.MessagesCAT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import state.DadesDeProva;
import state.WorldState;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompletarTascaStepDefs {
  private static final String DADA_JA_ANOTADA = "D0";

  private final WorldState state;
  private String tascaActual;
  private List<String> dadesDisponibles;

  public CompletarTascaStepDefs(WorldState state) {
    this.state = state;
  }

  private Controlador controlador() {
    return state.getControlador();
  }

  @Given("l'anotador {string} {string} té una tasca {string} assignada")
  public void lAnotadorTéUnaTascaAssignada(String nomUsuari, String cognoms, String idTasca) {
    prepararTasca(nomUsuari, cognoms, idTasca);
    controlador().assignarTasca(nomUsuari, idTasca);
  }

  @And("només li queda dada {string} per completar")
  public void nomésLiQuedaDadaPerCompletar(String titolDada) {
    DadesDeProva.afegirImatge(controlador(), tascaActual, DADA_JA_ANOTADA);
    DadesDeProva.afegirImatge(controlador(), tascaActual, titolDada);
    controlador().marcarDadaAnotada(state.getUsuariActual(), tascaActual, DADA_JA_ANOTADA);
    assertEquals(List.of(titolDada), controlador().obtenirDadesPendents(state.getUsuariActual(), tascaActual));
  }

  @Given("l'anotador {string} {string} té una tasca {string} assignada amb diverses dades per anotar")
  public void lAnotadorTéUnaTascaAssignadaAmbDiversesDadesPerAnotar(String nomUsuari, String cognoms,
                                                                   String idTasca) {
    prepararTasca(nomUsuari, cognoms, idTasca);
    for (String titol : List.of("D1", "D2", "D3")) {
      DadesDeProva.afegirImatge(controlador(), idTasca, titol);
    }
    controlador().assignarTasca(nomUsuari, idTasca);
  }

  @When("l'anotador {string} marca aquesta dada {string} com anotada")
  public void lAnotadorMarcaAquestaDadaComAnotada(String nomUsuari, String titolDada) {
    state.setResultat(controlador().marcarDadaAnotada(nomUsuari, tascaActual, titolDada));
  }

  @Then("el sistema marca la dada {string} com anotada")
  public void elSistemaMarcaLaDadaComAnotada(String titolDada) {
    assertEquals(MessagesCAT.DadaAnotadaCorrectament.getMessage(), state.getResultat());
    assertTrue(controlador().estaDadaAnotada(tascaActual, titolDada));
  }

  @And("el sistema marca la tasca {string} com a completada")
  public void elSistemaMarcaLaTascaComACompletada(String idTasca) {
    assertTrue(controlador().estaTascaCompletada(idTasca));
  }

  @And("la tasca {string} continua com a pendent de completar")
  public void laTascaContinuaComAPendentDeCompletar(String idTasca) {
    assertFalse(controlador().estaTascaCompletada(idTasca));
    assertTrue(controlador().estaTascaAssignadaA(idTasca, state.getUsuariActual()));
    assertFalse(controlador().obtenirDadesPendents(state.getUsuariActual(), idTasca).isEmpty());
  }

  @Given("l'anotador {string} {string} no té una tasca {string} assignada")
  public void lAnotadorNoTéUnaTascaAssignada(String nomUsuari, String cognoms, String idTasca) {
    prepararTasca(nomUsuari, cognoms, idTasca);
    DadesDeProva.afegirImatge(controlador(), idTasca, "D1");
  }

  @When("l'anotador {string} intenta consultar les dades disponibles de la tasca {string}")
  public void lAnotadorIntentaConsultarLesDadesDisponiblesDeLaTasca(String nomUsuari, String idTasca) {
    dadesDisponibles = controlador().obtenirDadesPendents(nomUsuari, idTasca);
  }

  @Then("el sistema no li mostra cap dada, ja que no té cap tasca assignada")
  public void elSistemaNoLiMostraCapDadaJaQueNoTéCapTascaAssignada() {
    assertTrue(dadesDisponibles.isEmpty());
    assertFalse(controlador().estaTascaAssignadaA(tascaActual, state.getUsuariActual()));
  }

  private void prepararTasca(String nomUsuari, String cognoms, String idTasca) {
    DadesDeProva.registrarAnotadora(controlador(), nomUsuari, cognoms, List.of(DadesDeProva.EXPERTESA_GENERICA));
    DadesDeProva.registrarTascaImatges(controlador(), idTasca, "Tasca " + idTasca,
        List.of(DadesDeProva.EXPERTESA_GENERICA));
    tascaActual = idTasca;
    state.setUsuariActual(nomUsuari);
  }
}
