package cz.vutbr.feec.model.empl;

public class Tech extends AEmployee {
  public Tech() {
     this.setWage(200);
 }

  public Tech(int id, String name, String surname) {
    super(id, name, surname, 200);
  }

}
