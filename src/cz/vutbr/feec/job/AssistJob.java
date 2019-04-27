package cz.vutbr.feec.job;

import cz.vutbr.feec.empl.AEmployee;
import cz.vutbr.feec.empl.Assistant;
import cz.vutbr.feec.empl.CEO;
import cz.vutbr.feec.empl.Dev;
import cz.vutbr.feec.empl.Tech;

public class AssistJob extends AJob implements IJob {
  public AssistJob() {
    super();
    qualified = new AEmployee[] {new Assistant(), new CEO(), new Dev(), new Tech()};
  }

  @Override
  public void doJob() {
    System.out.println("Vsechny informace o " + worker.getName());
  }

}
