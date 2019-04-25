package cz.vutbr.feec.empl;

public class Tech extends AEmployee {
  public Tech(int id, String name) {
    super(id, name, 200);
  }

  @Override
  public void work() {
    System.out.printf("%s: %d kc/h\n", this.getClass().getSimpleName(), salary);
  }

}
