package cz.vutbr.feec.empl;

public class CEO extends AEmployee {
  public CEO(int id, String name) {
    super(id, name, 350);
  }
  
  @Override
  public void work() {
    System.out.printf("%s: %d kc/h\n", this.getClass().getSimpleName(), salary);
  }

}
