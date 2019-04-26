package cz.vutbr.feec;

import cz.vutbr.feec.core.Database;
import cz.vutbr.feec.empl.AEmployee;
import cz.vutbr.feec.empl.Assistant;
import cz.vutbr.feec.empl.CEO;
import cz.vutbr.feec.empl.Dev;
import cz.vutbr.feec.empl.Tech;

public class App {
  public static void main(String[] args) {
    Database db = new Database();
    db.addEmployee("name", 1337, new CEO(1337, "name"));
    
    AEmployee ceo = new CEO(1337, "name");
    AEmployee ass = new Assistant(1337, "name");
    AEmployee tech = new Tech(1337, "name");
    AEmployee dev = new Dev(1337, "name");
    ceo.work();
    ass.work();
    tech.work();
    dev.work();
  }
}
