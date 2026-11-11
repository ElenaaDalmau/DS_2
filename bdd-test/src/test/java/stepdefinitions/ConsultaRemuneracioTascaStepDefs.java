package stepdefinitions;

import edu.ub.ds2627.anotub.controlador.Controlador;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import state.DadesDeProva;
import state.WorldState;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsultaRemuneracioTascaStepDefs {
  private static final double DELTA = 0.001;
  private static final String ESPECIFICACIO_REMUNERADA = "Anotació remunerada";

  private final WorldState state;
  // Les dades fetes s'indiquen al When, per això les tasques es completen en aquell moment
  private final Set<String> tasquesACompletar = new LinkedHashSet<>();
  private double remuneracio;
  private List<String> tasquesRemunerades;

  public ConsultaRemuneracioTascaStepDefs(WorldState state) {
    this.state = state;
  }

  private Controlador controlador() {
    return state.getControlador();
  }

  @Given("anotador {string} {string} amb les tasques {string} i {string}, {double} € per dada les dues, completades")
  public void anotadorAmbTasquesCompletades(String nomUsuari, String cognoms, String tasca1, String tasca2,
                                            double preuPerDada) {
    prepararAnotadoraAmbTasques(nomUsuari, cognoms, preuPerDada, tasca1, tasca2);
    tasquesACompletar.add(tasca1);
    tasquesACompletar.add(tasca2);
  }

  @When("consultar la remuneració de {string} que ha fet {int} dades")
  public void consultarLaRemuneracioTascaFeta(String idTasca, int nombreDades) {
    completarTasca(idTasca, nombreDades);
    completarLaResta(1);
    consultar(List.of(idTasca));
  }

  @When("consulta la remuneració total {string} i {string} amb una dada feta cadascuna")
  public void consultaLaRemuneracioTotalDuesTasquesCompletades(String tasca1, String tasca2) {
    completarLaResta(1);
    consultar(List.of(tasca1, tasca2));
  }

  @Then("el sistema li mostra el total que serà de {double} €")
  public void elSistemaLiMostraElTotalQueSeraDe(double total) {
    assertEquals(total, remuneracio, DELTA);
  }

  @Given("anotador {string} {string} amb les tasques {string} completada, {string} assignada i {double} € per dada les dues")
  public void anotadorAmbLesTasquesCompletadaIAssignada(String nomUsuari, String cognoms, String completada,
                                                       String assignada, double preuPerDada) {
    prepararAnotadoraAmbTasques(nomUsuari, cognoms, preuPerDada, completada, assignada);
    tasquesACompletar.add(completada);
  }

  @When("{string} consulta la remuneració total de {string} amb {int} dades fetes i {string} en conjunt")
  public void consultaLaRemuneracioTotalDeAmbDadesFetesIEnConjunt(String nomUsuari, String completada,
                                                                  int nombreDades, String assignada) {
    completarTasca(completada, nombreDades);
    // La tasca assignada té feina parcial, que no s'ha de remunerar
    DadesDeProva.afegirImatge(controlador(), assignada, assignada + "-D1");
    DadesDeProva.afegirImatge(controlador(), assignada, assignada + "-D2");
    controlador().marcarDadaAnotada(nomUsuari, assignada, assignada + "-D1");
    assertFalse(controlador().estaTascaCompletada(assignada));
    consultar(List.of(completada, assignada));
  }

  @Then("el sistema només lo mostra la tasca completa {string} i la remuneració {double} €")
  public void elSistemaNomesLoMostraLaTascaCompletaRemuneracio(String idTasca, double total) {
    assertEquals(List.of(idTasca), tasquesRemunerades);
    assertEquals(total, remuneracio, DELTA);
  }

  private void prepararAnotadoraAmbTasques(String nomUsuari, String cognoms, double preuPerDada,
                                           String... idsTasques) {
    DadesDeProva.registrarAnotadora(controlador(), nomUsuari, cognoms, List.of(DadesDeProva.EXPERTESA_GENERICA));
    // Les dues tasques són ofertes d'una mateixa especificació ("la mateixa remuneració per dada")
    for (String idTasca : idsTasques) {
      DadesDeProva.registrarTascaImatges(controlador(), idTasca, ESPECIFICACIO_REMUNERADA,
          List.of(DadesDeProva.EXPERTESA_GENERICA), preuPerDada);
      controlador().assignarTasca(nomUsuari, idTasca);
    }
    state.setUsuariActual(nomUsuari);
  }

  private void completarTasca(String idTasca, int nombreDades) {
    for (int i = 1; i <= nombreDades; i++) {
      DadesDeProva.afegirImatge(controlador(), idTasca, idTasca + "-D" + i);
    }
    for (int i = 1; i <= nombreDades; i++) {
      controlador().marcarDadaAnotada(state.getUsuariActual(), idTasca, idTasca + "-D" + i);
    }
    assertTrue(controlador().estaTascaCompletada(idTasca));
    tasquesACompletar.remove(idTasca);
  }

  private void completarLaResta(int nombreDades) {
    for (String idTasca : List.copyOf(tasquesACompletar)) {
      completarTasca(idTasca, nombreDades);
    }
  }

  private void consultar(List<String> idsTasques) {
    remuneracio = controlador().consultarRemuneracio(state.getUsuariActual(), idsTasques);
    tasquesRemunerades = controlador().obtenirTasquesRemunerades(state.getUsuariActual(), idsTasques);
  }
}
