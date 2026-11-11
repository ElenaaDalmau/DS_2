package stepdefinitions;

import edu.ub.ds2627.anotub.controlador.Controlador;
import edu.ub.ds2627.anotub.controlador.MessagesCAT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import state.DadesDeProva;
import state.WorldState;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnotacioDadaAmbEtiquetesStepDefs {
  // Conjunt de categories que imposa l'especificació de la tasca dels escenaris
  private static final List<String> CATEGORIES_IMPOSADES = List.of(
      "Planeta Vermell", "Postimpressionisme", "Oleo", "Dibuix net", "Pulcre", "Porta Tori", "Us de goma");

  private final WorldState state;
  private String idTasca;
  private String titolDada;
  private final List<String> etiquetesDemanades = new ArrayList<>();
  private final List<String> resultats = new ArrayList<>();

  public AnotacioDadaAmbEtiquetesStepDefs(WorldState state) {
    this.state = state;
  }

  private Controlador controlador() {
    return state.getControlador();
  }

  @Given("anotador {string} {string} amb la {string} d'expertesa {string} amb {string}")
  public void anotadorAmbLaDExpertesaAmb(String nomUsuari, String cognoms, String idTasca, String expertesa,
                                         String titolDada) {
    DadesDeProva.registrarAnotadora(controlador(), nomUsuari, cognoms, List.of(expertesa));
    DadesDeProva.registrarTascaImatges(controlador(), idTasca, "Tasca " + idTasca, List.of(expertesa),
        CATEGORIES_IMPOSADES);
    DadesDeProva.afegirImatge(controlador(), idTasca, titolDada);
    assertEquals(MessagesCAT.TascaAssignadaCorrectament.getMessage(),
        controlador().assignarTasca(nomUsuari, idTasca));
    state.setUsuariActual(nomUsuari);
  }

  @When("selecciona la dada {string} en {string} per anotar la etiqueta global {string}")
  public void seleccionaLaDadaPerAnotarLaEtiquetaGlobal(String titolDada, String idTasca, String etiqueta) {
    state.setResultat(anotarGlobal(titolDada, idTasca, etiqueta));
  }

  @Then("assigna correctament la etiqueta global {string} a {string} de {string}")
  public void assignaLaEtiquetaGlobalA(String etiqueta, String titolDada, String idTasca) {
    assertEquals(MessagesCAT.EtiquetaAssignadaCorrectament.getMessage(), state.getResultat());
    assertTrue(controlador().obtenirEtiquetesDada(idTasca, titolDada).contains(etiqueta));
  }

  @When("selecciona la dada {string} en {string} per anotar les etiquetes globals {string} i {string}")
  public void seleccionaLaDadaPerAnotarLesEtiquetesGlobalsI(String titolDada, String idTasca, String etiqueta1,
                                                            String etiqueta2) {
    resultats.add(anotarGlobal(titolDada, idTasca, etiqueta1));
    resultats.add(anotarGlobal(titolDada, idTasca, etiqueta2));
  }

  @Then("assigna les etiquetes globals correctament")
  public void assignaLesEtiquetesGlobals() {
    resultats.forEach(r -> assertEquals(MessagesCAT.EtiquetaAssignadaCorrectament.getMessage(), r));
    assertEquals(etiquetesDemanades, controlador().obtenirEtiquetesDada(idTasca, titolDada));
  }

  @When("selecciona la dada {string} en {string} per anotar la etiqueta de localització {string}")
  public void seleccionaLaDadaPerAnotarLaEtiquetaDeLocalitzacio(String titolDada, String idTasca, String etiqueta) {
    state.setResultat(controlador().anotarEtiquetaLocalitzacio(state.getUsuariActual(), idTasca, titolDada, etiqueta));
  }

  @Then("intenta assignar i el sistema respon amb {string}")
  public void intentaAssignarIElSistemaResponAmb(String missatge) {
    assertEquals(missatge, state.getResultat());
  }

  @When("selecciona {string} en {string} intenta anotar dues et. globals {string} i {string} i una de localització {string}")
  public void seleccionaIntentaAnotarDuesEtGlobalsIUnaDeLocalitzacio(String titolDada, String idTasca,
                                                                     String global1, String global2,
                                                                     String localitzacio) {
    resultats.add(anotarGlobal(titolDada, idTasca, global1));
    resultats.add(anotarGlobal(titolDada, idTasca, global2));
    resultats.add(controlador().anotarEtiquetaLocalitzacio(state.getUsuariActual(), idTasca, titolDada,
        localitzacio));
    state.setResultat(String.join(", ", resultats));
  }

  @Then("intenta assignar i el sistema llença {string}")
  public void intentaAssignarFuncionaILlenca(String missatges) {
    assertEquals(missatges, state.getResultat());
    assertEquals(etiquetesDemanades, controlador().obtenirEtiquetesDada(idTasca, titolDada));
  }

  private String anotarGlobal(String titolDada, String idTasca, String etiqueta) {
    this.idTasca = idTasca;
    this.titolDada = titolDada;
    etiquetesDemanades.add(etiqueta);
    return controlador().anotarEtiquetaGlobal(state.getUsuariActual(), idTasca, titolDada, etiqueta);
  }
}
