package cz.vutbr.feec.empl;

public class Assistant extends AEmployee {
  public Assistant(int id, String name, long salary) {
    super(id, name, salary);
  }

  @Override
  public void work() {
    System.out.println("Assistant");
  }

}
