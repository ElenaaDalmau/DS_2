Feature: US4 - Assignar una tasca a un anotador
  Com a anotador
  Vull consultar les tasques disponibles i assignar-me'n una
  Per així jo poder començar a anotar-ne dades

  Scenario: Hi ha tasques disponibles i l'anotador se n'assigna una
    Given anotador identificat "alvaro" "cardoso" i hi ha una tasca "T1" disponible d'expertesa "Astronomia"
    When anotador "alvaro" selecciona la tasca "T1" disponible i demana assignar-se-la
    Then el sistema li assigna la tasca "T1" a l'anotador "alvaro"
    And la tasca "T1" deixa de estar disponible per a la resta d anotadors

  Scenario: No hi ha tasques disponibles
    Given no hi ha tasques disponibles per a l'anotador "alvaro" "cardoso"
    When l'anotador "alvaro" consulta les tasques disponibles
    Then el sistema no mostra cap tasca disponible

  Scenario: Hi ha una tasca completa i l'anotador intenta assignar-se-la
    Given hi ha una tasca "T3" completada i tenim un usuari "alvaro" "cardoso"
    When l'anotador "alvaro" intenta assignar-se la tasca "T3" completada
    Then el sistema no li permet assignar-se la tasca "T3" completada
