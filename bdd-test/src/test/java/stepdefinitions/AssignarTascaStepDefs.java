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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssignarTascaStepDefs {
  private final WorldState state;
  private String expertesaTasca;
  private List<Map<String, String>> tasquesMostrades;

  public AssignarTascaStepDefs(WorldState state) {
    this.state = state;
  }

  private Controlador controlador() {
    return state.getControlador();
  }

  @Given("anotador identificat {string} {string} i hi ha una tasca {string} disponible d'expertesa {string}")
  public void anotadorIdentificatIHiHaUnaTascaDisponible(String nomUsuari, String cognoms, String idTasca,
                                                         String expertesa) {
    DadesDeProva.registrarAnotadora(controlador(), nomUsuari, cognoms, List.of(expertesa));
    assertEquals(MessagesCAT.IdentificacioCorrecta.getMessage(),
        controlador().identificarUsuari(nomUsuari, DadesDeProva.CONTRASENYA));
    DadesDeProva.registrarTascaImatges(controlador(), idTasca, "Tasca " + idTasca, List.of(expertesa));
    expertesaTasca = expertesa;
    state.setUsuariActual(nomUsuari);
  }

  @When("anotador {string} selecciona la tasca {string} disponible i demana assignar-se-la")
  public void anotadorSeleccionaLaTascaDisponibleIDemanaAssignarSeLa(String nomUsuari, String idTasca) {
    state.setResultat(controlador().assignarTasca(nomUsuari, idTasca));
  }

  @Then("el sistema li assigna la tasca {string} a l'anotador {string}")
  public void elSistemaAssignaLaTascaALAnotador(String idTasca, String nomUsuari) {
    assertEquals(MessagesCAT.TascaAssignadaCorrectament.getMessage(), state.getResultat());
    assertTrue(controlador().estaTascaAssignadaA(idTasca, nomUsuari));
  }

  @And("la tasca {string} deixa de estar disponible per a la resta d anotadors")
  public void laTascaDeixaDeEstarDisponiblePerALaRestaDAnotadors(String idTasca) {
    assertFalse(controlador().estaTascaDisponible(idTasca));

    String altraAnotadora = "altra_anotadora";
    DadesDeProva.registrarAnotadora(controlador(), altraAnotadora, "Altra", List.of(expertesaTasca));
    assertTrue(controlador().obtenirTasquesDisponibles(altraAnotadora).isEmpty());
    assertEquals(MessagesCAT.TascaNoDisponibleException.getMessage(),
        controlador().assignarTasca(altraAnotadora, idTasca));
  }

  @Given("no hi ha tasques disponibles per a l'anotador {string} {string}")
  public void noHiHaTasquesDisponiblesPerALAnotador(String nomUsuari, String cognoms) {
    DadesDeProva.registrarAnotadora(controlador(), nomUsuari, cognoms, List.of(DadesDeProva.EXPERTESA_GENERICA));
    controlador().eliminarTotesLesTasques();
    state.setUsuariActual(nomUsuari);
  }

  @When("l'anotador {string} consulta les tasques disponibles")
  public void lAnotadorConsultaLesTasquesDisponibles(String nomUsuari) {
    tasquesMostrades = controlador().obtenirTasquesDisponibles(nomUsuari);
  }

  @Then("el sistema no mostra cap tasca disponible")
  public void elSistemaNoMostraCapTascaDisponible() {
    assertTrue(tasquesMostrades.isEmpty());
  }

  @Given("hi ha una tasca {string} completada i tenim un usuari {string} {string}")
  public void hiHaUnaTascaCompletadaITenimUnUsuari(String idTasca, String nomUsuari, String cognoms) {
    DadesDeProva.registrarTascaImatges(controlador(), idTasca, "Tasca " + idTasca,
        List.of(DadesDeProva.EXPERTESA_GENERICA));
    DadesDeProva.completarTasca(controlador(), idTasca);
    assertTrue(controlador().estaTascaCompletada(idTasca));

    DadesDeProva.registrarAnotadora(controlador(), nomUsuari, cognoms, List.of(DadesDeProva.EXPERTESA_GENERICA));
    state.setUsuariActual(nomUsuari);
  }

  @When("l'anotador {string} intenta assignar-se la tasca {string} completada")
  public void lAnotadorIntentaAssignarSeLaTascaCompletada(String nomUsuari, String idTasca) {
    state.setResultat(controlador().assignarTasca(nomUsuari, idTasca));
  }

  @Then("el sistema no li permet assignar-se la tasca {string} completada")
  public void elSistemaNoLiPermetAssignarSeLaTascaCompletada(String idTasca) {
    assertEquals(MessagesCAT.TascaJaCompletadaException.getMessage(), state.getResultat());
    assertFalse(controlador().estaTascaAssignadaA(idTasca, state.getUsuariActual()));
    assertTrue(controlador().estaTascaCompletada(idTasca));
  }
}
