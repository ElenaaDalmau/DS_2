package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import state.WorldState;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistreUsuariStepDefs {
  private final WorldState state;

  public RegistreUsuariStepDefs(WorldState state) {
    this.state = state;
  }

  @Given("l'usuari {string} no està registrat")
  public void l_usuari_no_està_registrat(String nomUsuari) {
    state.getControlador().eliminarUsuari(nomUsuari);
  }

  @Given("l'usuari {string} ja està registrat")
  public void lUsuariJaEstaRegistrat(String nomUsuari) {
    registrar(nomUsuari, "Password123");
  }

  @Given("l'usuari {string} està registrat amb la contrasenya {string}")
  public void l_usuari_està_registrat_amb_la_contrasenya(String nomUsuari, String contrasenya) {
    registrar(nomUsuari, contrasenya);
  }

  @When("un usuari demana registrar-se amb les dades següents:")
  public void un_usuari_demana_registrar_se_amb_les_dades_següents(DataTable dataTable) {
    Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);
    String resultat = state.getControlador().registrarUsuari(
        row.get("nomUsuari"),
        row.get("contrasenya"),
        row.get("passaportNom"),
        row.get("passaportCognoms"),
        row.get("passaportPais"),
        row.get("passaportNumero"),
        parseData(row.get("passaportCaducitat")),
        row.get("compteIban"),
        row.get("compteSwift"),
        row.get("email"),
        row.get("telèfon"),
        partirLlista(row.get("experteses"))
    );
    state.setResultat(resultat);
  }

  @Then("l'usuari {string} està registrat")
  public void l_usuari_està_registrat(String nomUsuari) {
    assertTrue(state.getControlador().estaRegistrat(nomUsuari));
  }

  private void registrar(String nomUsuari, String contrasenya) {
    state.getControlador().eliminarUsuari(nomUsuari);
    state.getControlador().registrarUsuari(
        nomUsuari,
        contrasenya,
        "John",
        "Doe",
        "ESP",
        "ABC123456",
        LocalDate.parse("2030-12-31"),
        "ES9121000418450200051332",
        "CAIXESBBXXX",
        "john@example.com",
        "600123123",
        List.of("Java", "Bases de dades")
    );

  }

  private LocalDate parseData(String valor) {
    if (valor == null || valor.isBlank()) {
      return null;
    }
    return LocalDate.parse(valor);
  }

  private List<String> partirLlista(String text) {
    if (text == null || text.isBlank()) {
      return List.of();
    }
    return Arrays.stream(text.split(";"))
        .map(String::trim)
        .toList();
  }
}
