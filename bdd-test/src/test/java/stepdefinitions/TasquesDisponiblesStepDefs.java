package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import state.WorldState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasquesDisponiblesStepDefs {
    private final WorldState state;
    public List<Map<String, String>> tasquesMostrades = new ArrayList<Map<String, String>>();

    public TasquesDisponiblesStepDefs() {
        this.state = new WorldState();
    }

    public TasquesDisponiblesStepDefs(WorldState state) {
        this.state = state;
    }

}
