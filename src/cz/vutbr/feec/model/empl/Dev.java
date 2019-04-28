package cz.vutbr.feec.model.empl;

public class Dev extends AEmployee {
  public Dev() {
    this.setWage(250);
  }

  public Dev(int id, String name, String surname) {
    super(id, name, surname, 250);
  }

}
