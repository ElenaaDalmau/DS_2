Feature: US5 - Anotador/a fa anotacions amb etiquetes categòriques permeses a una tasca
  Com a anotador/a amb experteses i una tasca a realitzar
  Vull assignar una o més etiquetes categòriques (de les permeses per la tasca) per anotar una dada
  Per així jo classificar la dada segons el requisits demanats

  Scenario: Anotador assigna una etiqueta categòrica global a una imatge
    Given anotador "jeser" "guillen" amb la "T1" d'expertesa "Astronomia" amb "Imatge Kepler-1649c"
    When selecciona la dada "Imatge Kepler-1649c" en "T1" per anotar la etiqueta global "Planeta Vermell"
    Then assigna correctament la etiqueta global "Planeta Vermell" a "Imatge Kepler-1649c" de "T1"

  Scenario: Anotador assigna diverses etiquetes categòriques a una imatge
    Given anotador "jeser" "guillen" amb la "T1" d'expertesa "Arte" amb "Imatge Nit Estrellada"
    When selecciona la dada "Imatge Nit Estrellada" en "T1" per anotar les etiquetes globals "Postimpressionisme" i "Oleo"
    Then assigna les etiquetes globals correctament

  Scenario: Anotador intenta assignar una etiqueta no permesa a una dada
    Given anotador "jeser" "guillen" amb la "T1" d'expertesa "Fotografia" amb "Imatge Platja Tsushima"
    When selecciona la dada "Imatge Nit Estrellada" en "T1" per anotar la etiqueta de localització "Porta Tori"
    Then intenta assignar i el sistema respon amb "Tipus d'etiqueta no permesa"

  Scenario: Anotador intenta assignar una etiqueta no permesa, pero varies permeses
    Given anotador "jeser" "guillen" amb la "T1" d'expertesa "Dibuix Tecnic" amb "Imatge Axonometria"
    When selecciona "Imatge Axonometria" en "T1" intenta anotar dues et. globals "Dibuix net" i "Pulcre" i una de localització "Us de goma"
    Then intenta assignar i el sistema llença "Assignacio d'etiqueta amb exit, Assignacio d'etiqueta amb exit, Tipus d'etiqueta no permesa"