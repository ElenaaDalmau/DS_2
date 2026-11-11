package stepdefinitions;

import edu.ub.ds2627.anotub.controlador.MessagesCAT;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import state.DadesDeProva;
import state.WorldState;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasquesDisponiblesStepDefs {

    private static final String NO_DISPONIBLE = "No Disponible";

    private final WorldState state;
    private List<Map<String, String>> tasquesMostrades;

    public TasquesDisponiblesStepDefs(WorldState state) {
        this.state = state;
    }

    // Step per afegir expertesa a l'usuari actual registrat
    @And("té l'expertesa {string}")
    public void te_l_expertesa(String expertesa) {
        state.setResultat(state.getControlador().afegirExpertesaAnotador(state.getUsuariActual(), expertesa));
    }

    @Given("hi ha les següents tasques disponibles al sistema:")
    public void hi_ha_les_seguents_tasques_disponibles_al_sistema(DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            registrarTasca(row);
        }
    }

    @Given("no hi ha tasques disponibles al sistema")
    public void no_hi_ha_tasques_disponibles_al_sistema() {
        state.getControlador().eliminarTotesLesTasques();
    }

    @Given("hi ha les següents tasques al sistema:")
    public void hi_ha_les_seguents_tasques_al_sistema(DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            registrarTasca(row);
            if (NO_DISPONIBLE.equalsIgnoreCase(row.get("disponibilitat"))) {
                DadesDeProva.ocuparTasca(state.getControlador(), row.get("idTasca"));
            }
        }
    }

    @When("l’anotador {string} demana veure les tasques disponibles")
    public void l_anotador_demana_veure_les_tasques_disponibles(String nomUsuari) {
        this.tasquesMostrades = state.getControlador().obtenirTasquesDisponibles(nomUsuari);
        state.setResultat(tasquesMostrades.isEmpty() ? MessagesCAT.CapTascaDisponible.getMessage()
                : tasquesMostrades.toString());
    }

    @Then("el sistema mostra les següents tasques disponibles per a l’anotador {string}:")
    public void el_sistema_mostra_les_seguents_tasques_disponibles_per_a_l_anotador(
            String nomUsuari, DataTable expectedTasks) {
        assertEquals(expectedTasks.asMaps(String.class, String.class), this.tasquesMostrades);
    }

    private void registrarTasca(Map<String, String> row) {
        DadesDeProva.registrarTascaImatges(state.getControlador(), row.get("idTasca"), row.get("descripcioTasca"),
                List.of(row.get("expertesaNecessaria")));
    }
}
