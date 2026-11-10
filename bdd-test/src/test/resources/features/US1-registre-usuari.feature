Feature: US1 - Registre d'usuari
  Com a usuari no registrat
  Vull registrar-me al sistema
  Per poder accedir a les funcionalitats restringides

  Scenario: L'usuari demana registrar-se amb dades vàlides
    Given l'usuari "john_doe" no està registrat
    When un usuari demana registrar-se amb les dades següents:
      | nomUsuari | contrasenya | passaportNom | passaportCognoms | passaportPais | passaportNumero | passaportCaducitat | compteIban               | compteSwift | email            | telèfon   | experteses           |
      | john_doe  | Password123 | John         | Doe              | ESP           | ABC123456       | 2030-12-31         | ES9121000418450200051332 | CAIXESBBXXX | john@example.com | 600123123 | Java; Bases de dades |
    Then el sistema mostra un missatge "L'usuari s'ha registrat amb èxit."
    And l'usuari "john_doe" està registrat

  Scenario Outline: L'usuari demana registrar-se no proporcionant les dades necessàries
    Given l'usuari "<nomUsuari>" no està registrat
    When un usuari demana registrar-se amb les dades següents:
      | nomUsuari   | contrasenya   | passaportNom   | passaportCognoms   | passaportPais   | passaportNumero   | passaportCaducitat   | compteIban   | compteSwift   | email   | telèfon   | experteses   |
      | <nomUsuari> | <contrasenya> | <passaportNom> | <passaportCognoms> | <passaportPais> | <passaportNumero> | <passaportCaducitat> | <compteIban> | <compteSwift> | <email> | <telèfon> | <experteses> |
    Then el sistema mostra un missatge "L'usuari no s'ha pogut registrar perquè hi faltava l'atribut '<atributFaltant>'."

    Examples:
      | nomUsuari | contrasenya | passaportNom | passaportCognoms | passaportPais | passaportNumero | passaportCaducitat | compteIban               | compteSwift | email            | telèfon   | experteses           | atributFaltant     |
      |           | Password123 | John         | Doe              | ESP           | ABC123456       | 2030-12-31         | ES9121000418450200051332 | CAIXESBBXXX | john@example.com | 600123123 | Java; Bases de dades | nomUsuari          |
      | john_doe  |             | John         | Doe              | ESP           | ABC123456       | 2030-12-31         | ES9121000418450200051332 | CAIXESBBXXX | john@example.com | 600123123 | Java; Bases de dades | contrasenya        |
      | john_doe  | Password123 |              | Doe              | ESP           | ABC123456       | 2030-12-31         | ES9121000418450200051332 | CAIXESBBXXX | john@example.com | 600123123 | Java; Bases de dades | passaportNom       |
      | john_doe  | Password123 | John         |                  | ESP           | ABC123456       | 2030-12-31         | ES9121000418450200051332 | CAIXESBBXXX | john@example.com | 600123123 | Java; Bases de dades | passaportCognoms   |
      | john_doe  | Password123 | John         | Doe              |               | ABC123456       | 2030-12-31         | ES9121000418450200051332 | CAIXESBBXXX | john@example.com | 600123123 | Java; Bases de dades | passaportPais      |
      | john_doe  | Password123 | John         | Doe              | ESP           |                 | 2030-12-31         | ES9121000418450200051332 | CAIXESBBXXX | john@example.com | 600123123 | Java; Bases de dades | passaportNumero    |
      | john_doe  | Password123 | John         | Doe              | ESP           | ABC123456       |                     | ES9121000418450200051332 | CAIXESBBXXX | john@example.com | 600123123 | Java; Bases de dades | passaportCaducitat |
      | john_doe  | Password123 | John         | Doe              | ESP           | ABC123456       | 2030-12-31         |                          | CAIXESBBXXX | john@example.com | 600123123 | Java; Bases de dades | compteIban         |
      | john_doe  | Password123 | John         | Doe              | ESP           | ABC123456       | 2030-12-31         | ES9121000418450200051332 |             | john@example.com | 600123123 | Java; Bases de dades | compteSwift        |
      | john_doe  | Password123 | John         | Doe              | ESP           | ABC123456       | 2030-12-31         | ES9121000418450200051332 | CAIXESBBXXX |                  | 600123123 | Java; Bases de dades | email              |
      | john_doe  | Password123 | John         | Doe              | ESP           | ABC123456       | 2030-12-31         | ES9121000418450200051332 | CAIXESBBXXX | john@example.com |           | Java; Bases de dades | telèfon            |
      | john_doe  | Password123 | John         | Doe              | ESP           | ABC123456       | 2030-12-31         | ES9121000418450200051332 | CAIXESBBXXX | john@example.com | 600123123 |                      | experteses         |

  Scenario: L'usuari demana registrar-se amb un nom d'usuari ja registrat
    Given l'usuari "john_doe" ja està registrat
    When un usuari demana registrar-se amb les dades següents:
      | nomUsuari | contrasenya | passaportNom | passaportCognoms | passaportPais | passaportNumero | passaportCaducitat | compteIban               | compteSwift | email            | telèfon   | experteses           |
      | john_doe  | Password123 | John         | Doe              | ESP           | ABC123456       | 2030-12-31         | ES9121000418450200051332 | CAIXESBBXXX | john@example.com | 600123123 | Java; Bases de dades |
    Then el sistema mostra un missatge "No s'ha pogut registrar l'usuari. Ja existeix un usuari amb aquest nom."

  Scenario Outline: L'usuari demana registrar-se amb una contrasenya poc segura
    Given l'usuari "john_doe" no està registrat
    When un usuari demana registrar-se amb les dades següents:
      | nomUsuari | contrasenya   | passaportNom | passaportCognoms | passaportPais | passaportNumero | passaportCaducitat | compteIban               | compteSwift | email            | telèfon   | experteses           |
      | john_doe  | <contrasenya> | John         | Doe              | ESP           | ABC123456       | 2030-12-31         | ES9121000418450200051332 | CAIXESBBXXX | john@example.com | 600123123 | Java; Bases de dades |
    Then el sistema mostra un missatge "L'usuari no s'ha pogut registrar perquè la contrasenya no és prou segura."

    Examples:
      | contrasenya | motiu                       |
      | Pass1       | massa curta                 |
      | password123 | sense majúscula             |
      | PASSWORD123 | sense minúscula             |
      | Passwordxx  | sense número                |