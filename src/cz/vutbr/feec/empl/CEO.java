package cz.vutbr.feec.empl;

public class CEO extends AEmployee {
  public CEO(int id, String name) {
    super(id, name);
  }
  
  @Override
  public void work() {
    System.out.println("CEO");
  }
  

}
