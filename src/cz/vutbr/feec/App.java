package cz.vutbr.feec;

import cz.vutbr.feec.empl.AEmployee;
import cz.vutbr.feec.empl.Assistant;
import cz.vutbr.feec.empl.CEO;
import cz.vutbr.feec.empl.Dev;
import cz.vutbr.feec.empl.Tech;

public class App {
  public static void main(String[] args) {
    AEmployee ceo = new CEO(1337, "name", 1337);
    AEmployee ass = new Assistant(1337, "name", 1337);
    AEmployee tech = new Tech(1337, "name", 1337);
    AEmployee dev = new Dev(1337, "name", 1337);
    ceo.work();
    ass.work();
    tech.work();
    dev.work();
  }
}
