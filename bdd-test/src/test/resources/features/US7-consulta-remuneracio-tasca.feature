Feature: US7 - Consulta de remuneració aconseguida amb Tasques Completades
  Com anotador-a
  Vull consultar la remuneració econòmica total que he acumulat per les meves tasques completades
  Per així jo portar un control dels meus pagaments en l'aplicació

  Scenario: Anotador-a consulta la remuneració d’una tasca
    Given anotador "eduardo" "funez" amb les tasques "T1" i "T2", 70.0 € per dada les dues, completades
    When consultar la remuneració de "T1" que ha fet 2 dades
    Then el sistema li mostra el total que serà de 140.0 €

  Scenario: Anotador/a consulta la remuneració total d’un conjunt de tasques acumulades
    Given anotador "eduardo" "funez" amb les tasques "T1" i "T2", 30.0 € per dada les dues, completades
    When consulta la remuneració total "T1" i "T2" amb una dada feta cadascuna
    Then el sistema li mostra el total que serà de 60.0 €

  Scenario: Anotador/a consulta la remuneració de les tasques completades, amb una assignada
    Given anotador "eduardo" "funez" amb les tasques "T1" completada, "T2" assignada i 45.5 € per dada les dues
    When "eduardo" consulta la remuneració total de "T1" amb 2 dades fetes i "T2" en conjunt
    Then el sistema només lo mostra la tasca completa "T1" i la remuneració 91 €