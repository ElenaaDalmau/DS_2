package edu.ub.ds2627.anotub.model;


import java.time.LocalDate;
import java.util.Objects;

public final class Passaport {
  private final String nom;
  private final String cognoms;
  private final String paisExpedicio;
  private final String numero;
  private final LocalDate dataCaducitat;

  public Passaport(
      String nom,
      String cognoms,
      String paisExpedicio,
      String numero,
      LocalDate dataCaducitat
  )  {

    this.nom = nom;
    this.cognoms = cognoms;
    this.paisExpedicio = paisExpedicio;
    this.numero = numero;
    this.dataCaducitat = dataCaducitat;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Passaport passaport)) {
      return false;
    }
    return
        Objects.equals(numero, passaport.numero)
        && Objects.equals(dataCaducitat, passaport.dataCaducitat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paisExpedicio, numero);
  }
}
