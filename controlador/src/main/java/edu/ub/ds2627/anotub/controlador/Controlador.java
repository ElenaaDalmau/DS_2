package edu.ub.ds2627.anotub.controlador;

import edu.ub.ds2627.anotub.model.*;
import edu.ub.ds2627.anotub.model.excepcions.AnotUBException;
import edu.ub.ds2627.anotub.model.excepcions.AnotadoraNoExistentException;
import edu.ub.ds2627.anotub.model.excepcions.AtributFaltantException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Controlador {
  private final AnotUB anotUB;

  public Controlador() {
    this(new AnotUB());
  }

  public Controlador(AnotUB anotUB) {
    this.anotUB = anotUB;
  }

  // Mètodes per a la gestió d'anotadores

  public String registrarUsuari(
          String nomUsuari,
          String contrasenya,
          String passaportNom,
          String passaportCognoms,
          String passaportPais,
          String passaportNumero,
          LocalDate passaportCaducitat,
          String compteIban,
          String compteSwift,
          String email,
          String telefon,
          List<String> experteses
  ) {
    try {
      anotUB.registrarAnotadora(
              nomUsuari,
              contrasenya,
              passaportNom,
              passaportCognoms,
              passaportPais,
              passaportNumero,
              passaportCaducitat,
              compteIban,
              compteSwift,
              email,
              telefon,
              experteses
      );
      return MessagesCAT.RegistreAnotadoraCorrecte.getMessage();
    } catch (AtributFaltantException e) {
      return MessagesCAT.atributFaltantAnotadora(e);
    } catch (Exception e) {
      return MessagesCAT.translate(e);
    }
  }

  public Boolean estaRegistrat(String nomUsuari) {
    return anotUB.estaAnotadoraRegistrada(nomUsuari);
  }

  public void eliminarUsuari(String nomUsuari) {
    anotUB.eliminarAnotadora(nomUsuari);
  }

  public String registrarEmpresa(String nomUsuari, String contrasenya, String email, String telefon,
                                 String nom, String paisFiscal, String vat) {
    try {
      anotUB.registrarEmpresa(nomUsuari, contrasenya, email, telefon, nom, paisFiscal, vat);
      return MessagesCAT.RegistreEmpresaCorrecte.getMessage();
    } catch (AtributFaltantException e) {
      return MessagesCAT.atributFaltant(e);
    } catch (AnotUBException e) {
      return MessagesCAT.translate(e);
    }
  }

  public boolean estaEmpresaRegistrada(String nomUsuari) {
    return anotUB.estaEmpresaRegistrada(nomUsuari);
  }

  public String identificarUsuari(String nomUsuari, String contrasenya) {
    try {
      anotUB.identificarAnotadora(nomUsuari, contrasenya);
      return MessagesCAT.IdentificacioCorrecta.getMessage();
    } catch (Exception e) {
      return MessagesCAT.translate(e);
    }
  }

  public String afegirExpertesaAnotador(String nomUsuari, String expertesa) {
    try {
      anotUB.afegirExpertesaAnotadora(nomUsuari, expertesa);
      return MessagesCAT.ExpertesaAfegidaCorrectament.getMessage();
    } catch (AnotadoraNoExistentException e) {
        return MessagesCAT.translate(e);
    }
  }

  // Mètodes per a la gestió d'empreses

  public String consultarQuotaEmpresa(String tipusEmpresa) {
    try {
      return MessagesCAT.quotaAnual(anotUB.consultarQuotaEmpresa(tipusEmpresa));
    } catch (AnotUBException e) {
      return MessagesCAT.translate(e);
    }
  }

  // Mètodes per a la gestió de tasques

  public String publicarEspecificacio(String titol, List<String> experteses, List<String> categories,
                                      List<String> instruccions, double remuneracioPerDada) {
    try {
      anotUB.publicarEspecificacio(titol, experteses, categories, instruccions, remuneracioPerDada);
      return MessagesCAT.EspecificacioPublicadaCorrectament.getMessage();
    } catch (AtributFaltantException e) {
      return MessagesCAT.atributFaltant(e);
    } catch (AnotUBException e) {
      return MessagesCAT.translate(e);
    }
  }

  public boolean estaEspecificacioPublicada(String titol) {
    return anotUB.estaEspecificacioPublicada(titol);
  }

  public String registrarTascaImatges(String idTasca, String nomEmpresa, String titolEspecificacio,
                                      LocalDate dataLimit) {
    try {
      anotUB.registrarTascaImatges(idTasca, nomEmpresa, titolEspecificacio, dataLimit);
      return MessagesCAT.TascaAfegidaCorrectament.getMessage();
    } catch (AtributFaltantException e) {
      return MessagesCAT.atributFaltant(e);
    } catch (AnotUBException e) {
      return MessagesCAT.translate(e);
    }
  }

  public String registrarTascaDocuments(String idTasca, String nomEmpresa, String titolEspecificacio,
                                        LocalDate dataLimit) {
    try {
      anotUB.registrarTascaDocuments(idTasca, nomEmpresa, titolEspecificacio, dataLimit);
      return MessagesCAT.TascaAfegidaCorrectament.getMessage();
    } catch (AtributFaltantException e) {
      return MessagesCAT.atributFaltant(e);
    } catch (AnotUBException e) {
      return MessagesCAT.translate(e);
    }
  }

  public String afegirImatgeTasca(String idTasca, String titol, int amplada, int alcada) {
    try {
      anotUB.afegirImatgeTasca(idTasca, titol, amplada, alcada);
      return MessagesCAT.DadaAfegidaCorrectament.getMessage();
    } catch (AtributFaltantException e) {
      return MessagesCAT.atributFaltant(e);
    } catch (AnotUBException e) {
      return MessagesCAT.translate(e);
    }
  }

  public String afegirDocumentTasca(String idTasca, String titol, String text) {
    try {
      anotUB.afegirDocumentTasca(idTasca, titol, text);
      return MessagesCAT.DadaAfegidaCorrectament.getMessage();
    } catch (AtributFaltantException e) {
      return MessagesCAT.atributFaltant(e);
    } catch (AnotUBException e) {
      return MessagesCAT.translate(e);
    }
  }

  public List<Map<String, String>> obtenirTasquesDisponibles(String nomUsuari) {
    try {
      TascaMap<Map<String, String>> mapping = new TDD_TascaMapping();
      return anotUB.obtenirTasquesDisponibles(nomUsuari, mapping);
    } catch (AnotUBException e) {
      return List.of();
    }
  }

  public void eliminarTotesLesTasques() {
    anotUB.eliminarTotesLesTasques();
  }

  // Mètodes per a l'assignació de tasques

  public String assignarTasca(String nomUsuari, String idTasca) {
    try {
      anotUB.assignarTasca(nomUsuari, idTasca);
      return MessagesCAT.TascaAssignadaCorrectament.getMessage();
    } catch (AnotUBException e) {
      return MessagesCAT.translate(e);
    }
  }

  public boolean estaTascaDisponible(String idTasca) {
    return anotUB.estaTascaDisponible(idTasca);
  }

  public boolean estaTascaAssignadaA(String idTasca, String nomUsuari) {
    return anotUB.estaTascaAssignadaA(idTasca, nomUsuari);
  }

  public boolean estaTascaCompletada(String idTasca) {
    return anotUB.estaTascaCompletada(idTasca);
  }

  // Mètodes per a l'anotació de dades

  public String anotarEtiquetaGlobal(String nomUsuari, String idTasca, String titolDada, String etiqueta) {
    try {
      anotUB.anotarEtiqueta(nomUsuari, idTasca, titolDada, etiqueta);
      return MessagesCAT.EtiquetaAssignadaCorrectament.getMessage();
    } catch (AtributFaltantException e) {
      return MessagesCAT.atributFaltant(e);
    } catch (AnotUBException e) {
      return MessagesCAT.translate(e);
    }
  }

  public String anotarEtiquetaLocalitzacio(String nomUsuari, String idTasca, String titolDada, String etiqueta) {
    try {
      anotUB.anotarLocalitzacio(nomUsuari, idTasca, titolDada, etiqueta);
      return MessagesCAT.EtiquetaAssignadaCorrectament.getMessage();
    } catch (AtributFaltantException e) {
      return MessagesCAT.atributFaltant(e);
    } catch (AnotUBException e) {
      return MessagesCAT.translate(e);
    }
  }

  public List<String> obtenirEtiquetesDada(String idTasca, String titolDada) {
    try {
      return anotUB.obtenirEtiquetesDada(idTasca, titolDada);
    } catch (AnotUBException e) {
      return List.of();
    }
  }

  public String marcarDadaAnotada(String nomUsuari, String idTasca, String titolDada) {
    try {
      anotUB.marcarDadaAnotada(nomUsuari, idTasca, titolDada);
      return MessagesCAT.DadaAnotadaCorrectament.getMessage();
    } catch (AnotUBException e) {
      return MessagesCAT.translate(e);
    }
  }

  public boolean estaDadaAnotada(String idTasca, String titolDada) {
    try {
      return anotUB.estaDadaAnotada(idTasca, titolDada);
    } catch (AnotUBException e) {
      return false;
    }
  }

  public List<String> obtenirDadesPendents(String nomUsuari, String idTasca) {
    try {
      return anotUB.obtenirDadesPendents(nomUsuari, idTasca);
    } catch (AnotUBException e) {
      return List.of();
    }
  }

  // Mètodes per a la remuneració

  public double consultarRemuneracioCompletades(String nomUsuari) {
    try {
      return anotUB.consultarRemuneracioCompletades(nomUsuari);
    } catch (AnotUBException e) {
      return 0.0;
    }
  }

  public double consultarRemuneracio(String nomUsuari, List<String> idsTasques) {
    try {
      return anotUB.consultarRemuneracio(nomUsuari, idsTasques);
    } catch (AnotUBException e) {
      return 0.0;
    }
  }

  public List<String> obtenirTasquesRemunerades(String nomUsuari, List<String> idsTasques) {
    try {
      return anotUB.obtenirTasquesRemunerades(nomUsuari, idsTasques);
    } catch (AnotUBException e) {
      return List.of();
    }
  }
}
