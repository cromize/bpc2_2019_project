package cz.vutbr.feec.empl;

public class Dev extends AEmployee {
  public Dev(int id, String name) {
    super(id, name);
  }

  @Override
  public void work() {
    System.out.println("Dev");
  }

}
