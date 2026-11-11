Feature: L’anotador ha de poder veure les tasques disponibles donada la seva expertesa, suposant que ja hi ha unes tasques introduïdes en el sistema

  Com anotadora amb una certa expertesa
  Vull veure les tasques disponibles que coincideixin amb la meva expertesa
  Per poder triar quines vull realitzar

  Scenario: L’anotador demana veure les tasques disponibles amb una certa expertesa
    Given l'usuari "john_doe" està registrat amb la contrasenya "Password123"
    And té l'expertesa "DadesMèdiques"
    And hi ha les següents tasques disponibles al sistema:
      | idTasca | descripcioTasca | expertesaNecessaria |
      | 1       | Fauna Pirenaica         | Biologia                 |
      | 2       | Deteccio tumors cerebrals         | DadesMèdiques               |
      | 3       | Detecció fractures        | DadesMèdiques       |
      | 4       | Defectes aparelles electrònics         | Electrònica                |
    When l’anotador "john_doe" demana veure les tasques disponibles
    Then el sistema mostra les següents tasques disponibles per a l’anotador "john_doe":
      | idTasca | descripcioTasca | expertesaNecessaria |
      | 2       | Deteccio tumors cerebrals        | DadesMèdiques               |
      | 3       | Detecció fractures        | DadesMèdiques       |

    Scenario: L’anotador demana veure les tasques disponibles amb una certa expertesa però no hi ha cap coincidència
    Given l'usuari "jane_doe" està registrat amb la contrasenya "Password123"
    And té l'expertesa "Astronomia"
    And hi ha les següents tasques disponibles al sistema:
      | idTasca | descripcioTasca | expertesaNecessaria |
      | 1       | Fauna Pirenaica         | Biologia                 |
      | 2       | Deteccio tumors cerebrals         | DadesMèdiques               |
      | 3       | Detecció fractures        | DadesMèdiques       |
      | 4       | Defectes aparelles electrònics         |    Electrònica                |
    When l’anotador "jane_doe" demana veure les tasques disponibles
    Then el sistema mostra un missatge "No hi ha tasques disponibles en el sistema."

  Scenario: No hi han tasques disponibles per anotar
    Given l'usuari "john_doe" està registrat amb la contrasenya "Password123"
    And té l'expertesa "DadesMèdiques"
    And no hi ha tasques disponibles al sistema
    When l’anotador "john_doe" demana veure les tasques disponibles
    Then el sistema mostra un missatge "No hi ha tasques disponibles en el sistema."

    Scenario: Hi ha tasques amb la seva expertesa però no estan disponibles
    Given l'usuari "john_doe" està registrat amb la contrasenya "Password123"
    And té l'expertesa "DadesMèdiques"
    And hi ha les següents tasques al sistema:
      | idTasca | descripcioTasca | expertesaNecessaria | disponibilitat |
      | 1       | Fauna Pirenaica         | Biologia                 | No Disponible |
      | 2       | Deteccio tumors cerebrals         | DadesMèdiques               | No Disponible |
      | 3       | Detecció fractures        | DadesMèdiques       | No Disponible |
      | 4       | Defectes aparelles electrònics         |      Electrònica                | No Disponible |

      When l’anotador "john_doe" demana veure les tasques disponibles
      Then el sistema mostra un missatge "No hi ha tasques disponibles en el sistema."