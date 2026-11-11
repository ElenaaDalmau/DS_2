package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import state.WorldState;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculQuotaStepDefs {

    private final WorldState state;

    public CalculQuotaStepDefs(WorldState state) {
        this.state = state;
    }

    @When("consulto la quota anual del tipus d'empresa {string}")
    public void consultoQuota(String tipusEmpresa) {
        state.setResultat(state.getControlador().consultarQuotaEmpresa(tipusEmpresa));
    }

    @Then("el resultat és {string}")
    public void comprovoResultat(String resultatEsperat) {
        assertEquals(resultatEsperat, state.getResultat());
    }
}
