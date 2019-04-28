package cz.vutbr.feec.model.empl;

public class CEO extends AEmployee {
  public CEO() {
    this.setWage(350);
  }

  public CEO(int id, String name) {
    super(id, name, 350);
  }
  
}
