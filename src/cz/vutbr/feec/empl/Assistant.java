package cz.vutbr.feec.empl;

public class Assistant extends AEmployee {
  public Assistant(int id, String name) {
    super(id, name);
  }

  @Override
  public void work() {
    System.out.println("Assistant");
  }

}
