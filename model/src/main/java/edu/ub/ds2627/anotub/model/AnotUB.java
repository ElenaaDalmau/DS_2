package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.*;

import java.time.LocalDate;
import java.util.List;

public class AnotUB {
  private static final double MARGE_BENEFICI_PER_DEFECTE = 0.0;

  private final double margeBenefici;
  private final CarteraAnotadores anotadores;
  private final CarteraEmpreses empreses;
  private final CatalegEspecificacions especificacions;
  private final CatalegTasques tasques;
  private final CatalegAssignacions assignacions;
  private final CalculQuotaEmpresa calculQuotaEmpresa;

  public AnotUB() {
    this(MARGE_BENEFICI_PER_DEFECTE);
  }

  public AnotUB(double margeBenefici) {
    this.margeBenefici = margeBenefici;
    this.anotadores = new CarteraAnotadores();
    this.empreses = new CarteraEmpreses();
    this.especificacions = new CatalegEspecificacions();
    this.tasques = new CatalegTasques();
    this.assignacions = new CatalegAssignacions();
    this.calculQuotaEmpresa = new CalculQuotaEmpresa();
  }

  public double getMargeBenefici() {
    return margeBenefici;
  }

  // Mètodes per a la gestió d'usuaris (anotadores i empreses)

  public void registrarAnotadora(
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
  ) throws AtributFaltantException, NomUsuariJaExistentException, ContrasenyaNoSeguraException {
    if (!Camps.esBuit(nomUsuari) && existeixUsuari(nomUsuari)) {
      throw new NomUsuariJaExistentException();
    }

    Camps.exigir(nomUsuari, "nomUsuari");
    Camps.exigir(contrasenya, "contrasenya");
    Camps.exigir(passaportNom, "passaportNom");
    Camps.exigir(passaportCognoms, "passaportCognoms");
    Camps.exigir(passaportPais, "passaportPais");
    Camps.exigir(passaportNumero, "passaportNumero");
    Camps.exigir(passaportCaducitat, "passaportCaducitat");
    Camps.exigir(compteIban, "compteIban");
    Camps.exigir(compteSwift, "compteSwift");
    Camps.exigir(email, "email");
    Camps.exigir(telefon, "telèfon");
    Camps.exigirLlista(experteses, "experteses");

    Anotadora anotadora = new Anotadora(
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
    anotadores.afegir(anotadora);
  }

  public void registrarEmpresa(String nomUsuari, String contrasenya, String email, String telefon,
                               String nom, String paisFiscal, String vat)
      throws AtributFaltantException, NomUsuariJaExistentException, ContrasenyaNoSeguraException {
    if (!Camps.esBuit(nomUsuari) && existeixUsuari(nomUsuari)) {
      throw new NomUsuariJaExistentException();
    }

    Camps.exigir(nomUsuari, "nomUsuari");
    Camps.exigir(contrasenya, "contrasenya");
    Camps.exigir(email, "email");
    Camps.exigir(telefon, "telèfon");
    Camps.exigir(nom, "nom");
    Camps.exigir(paisFiscal, "paisFiscal");
    Camps.exigir(vat, "vat");

    empreses.afegir(new Empresa(nomUsuari, contrasenya, email, telefon, nom, paisFiscal, vat));
  }

  // El nom d'usuari és únic entre tots els usuaris del sistema
  private boolean existeixUsuari(String nomUsuari) {
    return anotadores.existeix(nomUsuari) || empreses.existeix(nomUsuari);
  }

  public void identificarAnotadora(String nomUsuari, String contrasenya)
      throws AnotadoraNoExistentException, ContrasenyaIncorrectaException {
    Anotadora anotadora = anotadores.obtenir(nomUsuari);
    if (!anotadora.contrasenyaCorrecta(contrasenya)) {
      throw new ContrasenyaIncorrectaException();
    }
  }

  public boolean estaAnotadoraRegistrada(String nomUsuari) {
    return anotadores.existeix(nomUsuari);
  }

  public boolean estaEmpresaRegistrada(String nomUsuari) {
    return empreses.existeix(nomUsuari);
  }

  public boolean eliminarAnotadora(String nomUsuari) {
    return anotadores.eliminar(nomUsuari);
  }

  public void afegirExpertesaAnotadora(String nomUsuari, String expertesa) throws AnotadoraNoExistentException {
    anotadores.afegirExpertesaAnotadora(nomUsuari, expertesa);
  }

  // Mètodes per a la gestió d'especificacions i tasques

  public void publicarEspecificacio(String titol, List<String> experteses, List<String> categories,
                                    List<String> instruccions, double remuneracioPerDada)
      throws AtributFaltantException, EspecificacioJaExistentException {
    Camps.exigir(titol, "titol");
    Camps.exigirLlista(experteses, "experteses");
    Camps.exigirLlista(categories, "categories");
    Camps.exigir((Object) instruccions, "instruccions");

    especificacions.afegir(new EspecificacioTasca(titol, experteses, categories, instruccions, remuneracioPerDada));
  }

  public boolean estaEspecificacioPublicada(String titol) {
    return especificacions.existeix(titol);
  }

  // Creador: AnotUB té l'especificació, l'empresa i les dades per crear la tasca
  public void registrarTascaImatges(String idTasca, String nomEmpresa, String titolEspecificacio, LocalDate dataLimit)
      throws AtributFaltantException, EmpresaNoExistentException, EspecificacioNoExistentException,
      TascaJaExistentException {
    Camps.exigir(idTasca, "id");
    Camps.exigir(dataLimit, "dataLimit");
    registrarTasca(new TascaImatges(idTasca, dataLimit, especificacions.obtenir(titolEspecificacio),
        empreses.obtenir(nomEmpresa)));
  }

  public void registrarTascaDocuments(String idTasca, String nomEmpresa, String titolEspecificacio,
                                      LocalDate dataLimit)
      throws AtributFaltantException, EmpresaNoExistentException, EspecificacioNoExistentException,
      TascaJaExistentException {
    Camps.exigir(idTasca, "id");
    Camps.exigir(dataLimit, "dataLimit");
    registrarTasca(new TascaDocuments(idTasca, dataLimit, especificacions.obtenir(titolEspecificacio),
        empreses.obtenir(nomEmpresa)));
  }

  private void registrarTasca(Tasca tasca) throws TascaJaExistentException {
    tasques.afegir(tasca);
    assignacions.registrarAssignacio(new Assignacio(tasca, new TascaDisponible()));
  }

  public boolean eliminarTasca(String idTasca) {
    assignacions.eliminarPerTasca(idTasca);
    return tasques.eliminar(idTasca);
  }

  public Tasca obtenirTasca(String idTasca) throws TascaNoExistentException {
    return tasques.obtenir(idTasca);
  }

  public void afegirImatgeTasca(String idTasca, String titol, int amplada, int alcada)
      throws AtributFaltantException, TascaNoExistentException, TipusDadaIncorrecteException,
      DimensionsImatgeNoValidesException {
    Camps.exigir(titol, "titol");
    tasques.obtenir(idTasca).afegirImatge(titol, amplada, alcada);
  }

  public void afegirDocumentTasca(String idTasca, String titol, String text)
      throws AtributFaltantException, TascaNoExistentException, TipusDadaIncorrecteException {
    Camps.exigir(titol, "titol");
    Camps.exigir(text, "text");
    tasques.obtenir(idTasca).afegirDocument(titol, text);
  }

  public <T> List<T> obtenirTasquesDisponibles(String nomUsuari, TascaMap<T> mapping)
      throws AnotadoraNoExistentException {
    Anotadora anotadora = anotadores.obtenir(nomUsuari);
    return assignacions.obtenirTasquesDisponiblesPerExperteses(anotadora.getExperteses()).stream()
        .map(tasca -> tasca.mapejar(mapping))
        .toList();
  }

  public void eliminarTotesLesTasques() {
    assignacions.eliminarTotes();
    tasques.eliminarTotesLesTasques();
  }

  // Mètodes per a la gestió d'assignacions

  public void assignarTasca(String nomUsuari, String idTasca) throws AnotadoraNoExistentException,
      TascaNoExistentException, TascaNoDisponibleException, TascaJaCompletadaException {
    Anotadora anotadora = anotadores.obtenir(nomUsuari);
    assignacions.obtenirPerTasca(idTasca).assignarA(anotadora);
  }

  public boolean estaTascaDisponible(String idTasca) {
    return assignacions.buscarPerTasca(idTasca).map(Assignacio::estaDisponible).orElse(false);
  }

  public boolean estaTascaCompletada(String idTasca) {
    return assignacions.buscarPerTasca(idTasca).map(Assignacio::estaCompletada).orElse(false);
  }

  public boolean estaTascaAssignadaA(String idTasca, String nomUsuari) {
    return anotadores.buscar(nomUsuari)
        .flatMap(anotadora -> assignacions.buscarPerTasca(idTasca)
            .map(assignacio -> assignacio.estaAssignadaA(anotadora)))
        .orElse(false);
  }

  // Mètodes per a l'anotació de dades

  public void anotarEtiqueta(String nomUsuari, String idTasca, String titolDada, String categoria)
      throws AtributFaltantException, AnotadoraNoExistentException, TascaNoExistentException,
      TascaNoAssignadaException, TascaJaCompletadaException, CategoriaNoPermesaException,
      DadaNoExistentException {
    Camps.exigir(categoria, "categoria");
    Anotadora anotadora = anotadores.obtenir(nomUsuari);
    assignacions.obtenirPerTasca(idTasca).anotarEtiqueta(anotadora, titolDada, new Categoria(categoria));
  }

  public void anotarLocalitzacio(String nomUsuari, String idTasca, String titolDada, String categoria)
      throws AtributFaltantException, AnotadoraNoExistentException, TascaNoExistentException,
      TascaNoAssignadaException, TascaJaCompletadaException, TipusEtiquetaNoPermesaException {
    Camps.exigir(categoria, "categoria");
    Anotadora anotadora = anotadores.obtenir(nomUsuari);
    assignacions.obtenirPerTasca(idTasca).anotarLocalitzacio(anotadora, titolDada, new Categoria(categoria));
  }

  public List<String> obtenirEtiquetesDada(String idTasca, String titolDada)
      throws TascaNoExistentException, DadaNoExistentException {
    return tasques.obtenir(idTasca).obtenirDada(titolDada).getEtiquetes().stream()
        .map(Categoria::getNom)
        .toList();
  }

  public void marcarDadaAnotada(String nomUsuari, String idTasca, String titolDada)
      throws AnotadoraNoExistentException, TascaNoExistentException, TascaNoAssignadaException,
      TascaJaCompletadaException, DadaNoExistentException {
    Anotadora anotadora = anotadores.obtenir(nomUsuari);
    assignacions.obtenirPerTasca(idTasca).marcarDadaAnotada(anotadora, titolDada);
  }

  public boolean estaDadaAnotada(String idTasca, String titolDada)
      throws TascaNoExistentException, DadaNoExistentException {
    return tasques.obtenir(idTasca).obtenirDada(titolDada).isAnotada();
  }

  public List<String> obtenirDadesPendents(String nomUsuari, String idTasca)
      throws AnotadoraNoExistentException, TascaNoExistentException, TascaNoAssignadaException,
      TascaJaCompletadaException {
    Anotadora anotadora = anotadores.obtenir(nomUsuari);
    return assignacions.obtenirPerTasca(idTasca).obtenirDadesPendents(anotadora).stream()
        .map(DadaCompleta::getTitol)
        .toList();
  }

  // Mètodes per a la remuneració

  // Atribut derivat del MD: /remuneracióCompletades de l'anotadora
  public double consultarRemuneracioCompletades(String nomUsuari) throws AnotadoraNoExistentException {
    Anotadora anotadora = anotadores.obtenir(nomUsuari);
    return sumarRemuneracio(assignacions.obtenirCompletadesDe(anotadora));
  }

  public double consultarRemuneracio(String nomUsuari, List<String> idsTasques) throws AnotadoraNoExistentException {
    Anotadora anotadora = anotadores.obtenir(nomUsuari);
    return sumarRemuneracio(assignacions.obtenirCompletadesDe(anotadora, idsTasques));
  }

  private double sumarRemuneracio(List<Assignacio> completades) {
    return completades.stream().mapToDouble(Assignacio::calcularRemuneracio).sum();
  }

  public List<String> obtenirTasquesRemunerades(String nomUsuari, List<String> idsTasques)
      throws AnotadoraNoExistentException {
    Anotadora anotadora = anotadores.obtenir(nomUsuari);
    return assignacions.obtenirCompletadesDe(anotadora, idsTasques).stream()
        .map(assignacio -> assignacio.getTasca().getId())
        .toList();
  }

  // Mètodes per a la gestió d'empreses

  public int consultarQuotaEmpresa(String tipusEmpresa) throws TipusEmpresaDesconegutException {
    return calculQuotaEmpresa.quotaAnual(tipusEmpresa);
  }
}
