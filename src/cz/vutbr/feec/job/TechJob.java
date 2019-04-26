package cz.vutbr.feec.job;

public class TechJob extends AJob implements IJob {

  public TechJob() {
    super();
  }

  @Override
  public void doJob() {
    System.out.println("do tech");
    
  }

}
