package cz.vutbr.feec.empl;

public class Dev extends AEmployee {
  public Dev(int id, String name) {
    super(id, name, 250);
  }

  @Override
  public void work() {
    System.out.printf("%s: %d kc/h\n", this.getClass().getSimpleName(), salary);
  }

}
