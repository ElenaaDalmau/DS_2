Feature: US3 - Tasques disponibles per a l'anotador
  Com a anotador amb experteses
  Vull veure les tasques disponibles per a mi
  Per així poder seleccionar-ne un per anotar-ne dades

    Scenario: Hi ha tasques disponibles per la expertesa del anotador
        Given l'anotador-a identificat  "alex" de cognoms "bernausgonzales" té la expertesa de "Biologia"
        When l'anotador-a accedeix a la llista de tasques disponibles de expertesa "Biologia"
        Then el sistema mostra totes les tasques disponibles per la expertesa "Biologia"

    Scenario: No hi ha cap tasca disponible per l’expertesa del anotador
        Given l'anotador-a identificat  "alex" de cognoms "bernausgonzales" té la expertesa de "Medicina"
        When l'anotador-a accedeix a la llista de tasques disponibles de expertesa "Medicina"
        Then el sistema mostra un missatge d'error "NO hi ha tasques disponibles", expertesa diferent de "Medicina"

    Scenario: No hi ha cap tasca disponible, ja que està totes finalitzades
        Given l'anotador-a identificat  "alex" de cognoms "bernausgonzales" té la expertesa de "Astronomia"
        When l'anotador-a accedeix a la llista de tasques disponibles de expertesa "Astronomia"
        Then el sistema mostra un missatge d'error "NO hi ha tasques disponibles", tasca acabada de "Astronomia"

    Scenario: No hi ha cap tasca disponible, tasca amb data límit vençuda
        Given l'anotador-a identificat  "alex" de cognoms "bernausgonzales" té la expertesa de "Historia"
        When l'anotador-a accedeix a la llista de tasques disponibles de expertesa "Historia"
        Then el sistema mostra un missatge d'error "NO hi ha tasques disponibles", tasca expirada de "Historia"