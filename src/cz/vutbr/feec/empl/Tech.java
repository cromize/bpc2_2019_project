package cz.vutbr.feec.empl;

public class Tech extends AEmployee {
  public Tech(int id, String name, long salary) {
    super(id, name, salary);
  }

  @Override
  public void work() {
    System.out.println("Tech");
  }

}
