package cz.vutbr.feec.model.empl;

public class Assistant extends AEmployee {
  public Assistant() {
    this.setWage(150);
  }

  public Assistant(int id, String name) {
    super(id, name, 150);
  }

}
