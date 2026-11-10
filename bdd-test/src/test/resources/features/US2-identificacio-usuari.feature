Feature: US2 - Identificació d'usuari
  Com a usuari registrat
  Vull identificar-me al sistema
  Per poder accedir a les funcionalitats restringides

  Scenario: L'usuari s'identifica amb credencials vàlides
    Given l'usuari "john_doe" està registrat amb la contrasenya "Password123"
    When "john_doe" intenta identificar-se amb la contrasenya "Password123"
    Then el sistema mostra un missatge "Identificació correcta."

  Scenario: L'usuari s'identifica amb una contrasenya incorrecta
    Given l'usuari "john_doe" està registrat amb la contrasenya "Password123"
    When "john_doe" intenta identificar-se amb la contrasenya "ContrasenyaIncorrecta1"
    Then el sistema mostra un missatge "No s'ha pogut identificar l'usuari. La contrasenya no és correcta."

  Scenario: Un usuari no registrat intenta identificar-se
    Given l'usuari "unknown_user" no està registrat
    When "unknown_user" intenta identificar-se amb la contrasenya "Password123"
    Then el sistema mostra un missatge "No s'ha pogut identificar l'usuari. No existeix cap usuari amb aquest nom."
