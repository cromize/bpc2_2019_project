package cz.vutbr.feec.empl;

public class Assistant extends AEmployee {
  public Assistant(int id, String name) {
    super(id, name, 150);
  }

  @Override
  public void work() {
    System.out.printf("%s: %d kc/h\n", this.getClass().getSimpleName(), salary);
  }

}
