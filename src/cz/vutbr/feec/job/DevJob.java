package cz.vutbr.feec.job;

public class DevJob extends AJob implements IJob {

  public DevJob() {
    super();
  }

  @Override
  public void doJob() {
    String rev = new StringBuilder(worker.getName()).reverse().toString();
    System.out.println("Me jmeno pozpatku je " + rev);
  }

}
