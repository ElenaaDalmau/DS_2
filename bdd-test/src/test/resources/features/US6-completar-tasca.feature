Feature: US6 - Completar una tasca assignada
  Com a anotador amb una tasca assignada
  Vull marcar una dada com anotada
  Per així jo poder completar la tasca quan estiguin totes les dades anotades

  Scenario: L'anotador té una tasca assignada i anota l'última dada com anotada
    Given l'anotador "pablo" "cano" té una tasca "T1" assignada
    And només li queda dada "D1" per completar
    When l'anotador "pablo" marca aquesta dada "D1" com anotada
    Then el sistema marca la dada "D1" com anotada
    And el sistema marca la tasca "T1" com a completada

  Scenario: L'anotador té una tasca assignada, anota una dada com anotada però no és l'ultima
    Given l'anotador "sergio" "santos" té una tasca "T1" assignada amb diverses dades per anotar
    When l'anotador "sergio" marca aquesta dada "D1" com anotada
    Then el sistema marca la dada "D1" com anotada
    And la tasca "T1" continua com a pendent de completar

  Scenario: L'anotador no té una tasca assignada i intenta consultar les dades
    Given l'anotador "sergio" "santos" no té una tasca "T1" assignada
    When l'anotador "sergio" intenta consultar les dades disponibles de la tasca "T1"
    Then el sistema no li mostra cap dada, ja que no té cap tasca assignada
