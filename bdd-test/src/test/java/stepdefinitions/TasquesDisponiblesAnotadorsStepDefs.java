package stepdefinitions;

import edu.ub.ds2526.anotub.model.EtiquetesCategoriques.TipusEtiquetesCategoriques;
import edu.ub.ds2526.anotub.model.TascaAnotacio;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import state.WorldState;

import java.time.LocalDate;
import java.util.List;

public class TasquesDisponiblesAnotadorsStepDefs {

    private final WorldState state;

    public TasquesDisponiblesAnotadorsStepDefs() {
        this.state = new WorldState();
    }

    public TasquesDisponiblesAnotadorsStepDefs(WorldState state) {
        this.state = state;
    }

    @Given("l'anotador-a identificat  {string} de cognoms {string} té la expertesa de {string}")
    public void l_anotador_amb_expertesa(String nom, String cognoms, String expertesa) {
        state.getControlador().eliminarUsuari(nom);
        state.getControlador().registrarUsuari(
                nom,
                "Password123",
                nom,
                cognoms,
                "ESP",
                "ABC123456",
                LocalDate.parse("2030-12-31"),
                "ES9121000418450200051332",
                "CAIXESBBXXX",
                nom + "@example.com",
                "600123123",
                List.of(expertesa)
        );

    }

    @When("l'anotador-a accedeix a la llista de tasques disponibles de expertesa {string}")
    public void l_anotador_consulta_les_tasques_disponibles(String expertesa) {
        state.controlador.eliminarTasca("Tasca Testable");
        state.controlador.afegirTascaAnotacioImatge("Tasca Testable",
                "Anotar les diferencies de les imatges", expertesa, 100.0f,
                LocalDate.parse("2030-12-31"),
                List.of(TipusEtiquetesCategoriques.GLOBAL_IMATGE, TipusEtiquetesCategoriques.LOCALITZACIO_OBJECTE));
    }

    @Then("el sistema mostra totes les tasques disponibles per la expertesa {string}")
    public void l_anotador_veu_les_tasques_disponibles(String expertesa) {
        String tasca = state.controlador.trobarTasquesDisponibles(expertesa);

        String expected = "TascaAnotacio{titol=Tasca Testable, instrucions=Anotar les diferencies de les imatges" +
                ", expertesaRequerida=" + expertesa + ", remuneracio=" + 0.0f + ", remuneracioPerDada=" + 100.0f +
                ", dataLimit=2030-12-31, dadesFetes=0, dadesPendents=0, anotadoraAsignada=Cap, estatTasca=DISPONIBLE, " +
                "etiquetesPermeses=[GLOBAL_IMATGE, LOCALITZACIO_OBJECTE], tipusTasca=TascaAnotacioImatge}";
        assert(tasca.contains(expected));
    }

    @Then("el sistema mostra un missatge d'error {string}, expertesa diferent de {string}")
    public void cap_tasca_amb_expertesa_disponible(String missatge, String expertesa){
        assert(state.controlador.trobarTasquesDisponibles(expertesa).equals(missatge));
    }

    @Then("el sistema mostra un missatge d'error {string}, tasca acabada de {string}")
    public void cap_tasca_no_acabada_disponible(String missatge, String expertesa){
        state.controlador.canviarEstatTasca(TascaAnotacio.EstatsTasca.COMPLETA, "Tasca Testable");

        assert(state.controlador.trobarTasquesDisponibles(expertesa).equals(missatge));
    }

    @Then("el sistema mostra un missatge d'error {string}, tasca expirada de {string}")
    public void cap_tasca_pendent_disponible(String missatge, String expertesa){
        state.controlador.canviarEstatTasca(TascaAnotacio.EstatsTasca.ASSIGNADA, "Tasca Testable");

        assert(state.controlador.trobarTasquesDisponibles(expertesa).equals(missatge));
    }

}
