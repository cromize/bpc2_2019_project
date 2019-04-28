package cz.vutbr.feec.model.job;

import java.io.IOException;

import cz.vutbr.feec.core.Core;
import cz.vutbr.feec.core.Prompt;
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
  public void doJob(Core core) {
    System.out.println("\n*** Vypis informaci o zamestnanci");
    int id = core.getPrompt().promptUserId(false);
    System.out.println(core.getDB().getEmployee(id).toString());
  }

}
