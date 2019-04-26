package cz.vutbr.feec.job;

public class DevJob extends AJob implements IJob {

  public DevJob() {
    super();
  }

  @Override
  public void doJob() {
    System.out.println("do dev");
    
  }

}
