package stepdefinitions;


import edu.ub.ds2627.anotub.controlador.Controlador;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculQuotaStepDefs {

    private final Controlador controlador = new Controlador();
    private String resultat;

    @When("consulto la quota anual del tipus d'empresa {string}")
    public void consultoQuota(String tipusEmpresa) {
        resultat = controlador.consultarQuotaEmpresa(tipusEmpresa);
    }

    @Then("el resultat és {string}")
    public void comprovoResultat(String resultatEsperat) {
        assertEquals(resultatEsperat, resultat);
    }
}