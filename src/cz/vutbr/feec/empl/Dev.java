package cz.vutbr.feec.empl;

public class Dev extends AEmployee {
  public Dev(int id, String name, long salary) {
    super(id, name, salary);
  }

  @Override
  public void work() {
    System.out.println("Dev");
  }

}
