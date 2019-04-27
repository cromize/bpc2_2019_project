package cz.vutbr.feec.model.job;

import cz.vutbr.feec.model.empl.AEmployee;
import cz.vutbr.feec.model.empl.Assistant;
import cz.vutbr.feec.model.empl.CEO;
import cz.vutbr.feec.model.empl.Dev;
import cz.vutbr.feec.model.empl.Tech;

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
