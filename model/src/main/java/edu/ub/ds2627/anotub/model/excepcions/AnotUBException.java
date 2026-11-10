package edu.ub.ds2627.anotub.model.excepcions;

public abstract class AnotUBException extends Exception {
  protected AnotUBException() {
  }

  protected AnotUBException(String message) {
    super(message);
  }
}
