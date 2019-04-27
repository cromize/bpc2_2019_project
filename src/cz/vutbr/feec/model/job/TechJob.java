package cz.vutbr.feec.model.job;

import cz.vutbr.feec.model.empl.AEmployee;
import cz.vutbr.feec.model.empl.CEO;
import cz.vutbr.feec.model.empl.Dev;
import cz.vutbr.feec.model.empl.Tech;

public class TechJob extends AJob implements IJob {

  public TechJob() {
    super();
    qualified = new AEmployee[] {new CEO(), new Dev(), new Tech()};
  }

  @Override
  public void doJob() {
    int counter = 0;
    for (char ch : worker.getName().toCharArray()) {
      if ("aeiou".indexOf(ch) >= 0) {
        counter++;
      }
    }
    System.out.println("Pocet samohlasek v mem jmene je " + counter);
  }

}
