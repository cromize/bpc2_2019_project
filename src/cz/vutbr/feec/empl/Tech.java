package cz.vutbr.feec.empl;

public class Tech extends AEmployee {
  public Tech(int id, String name) {
    super(id, name);
  }

  @Override
  public void work() {
    System.out.println("Tech");
  }

}
