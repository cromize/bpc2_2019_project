package cz.vutbr.feec.job;

import cz.vutbr.feec.empl.AEmployee;
import cz.vutbr.feec.empl.CEO;
import cz.vutbr.feec.empl.Dev;
import cz.vutbr.feec.empl.Tech;

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
