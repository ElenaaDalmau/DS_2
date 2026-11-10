Feature: US-10: Consultar la quota anual d'una empresa col·laboradora

  Scenario: Consultar la quota d'una empresa petita
    When consulto la quota anual del tipus d'empresa "PETITA"
    Then el resultat és "La quota anual és de 300 euros."

  Scenario: Consultar la quota d'una empresa mitjana
    When consulto la quota anual del tipus d'empresa "MITJANA"
    Then el resultat és "La quota anual és de 700 euros."

  Scenario: Consultar la quota d'una empresa gran
    When consulto la quota anual del tipus d'empresa "GRAN"
    Then el resultat és "La quota anual és de 1500 euros."

  Scenario: El tipus d'empresa no existeix
    When consulto la quota anual del tipus d'empresa "MICRO"
    Then el resultat és "Tipus d'empresa desconegut: MICRO"