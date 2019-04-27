package cz.vutbr.feec.job;

import cz.vutbr.feec.empl.AEmployee;
import cz.vutbr.feec.empl.CEO;
import cz.vutbr.feec.empl.Dev;

public class DevJob extends AJob implements IJob {

  public DevJob() {
    super();
    qualified = new AEmployee[] {new CEO(), new Dev()};
  }

  @Override
  public void doJob() {
    String rev = new StringBuilder(worker.getName()).reverse().toString();
    System.out.println("Me jmeno pozpatku je " + rev);
  }

}
