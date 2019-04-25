package cz.vutbr.feec.empl;

public class CEO extends AEmployee {
  public CEO(int id, String name, long salary) {
    super(id, name, salary);
  }
  
  @Override
  public void work() {
    System.out.println("CEO");
  }
  

}
