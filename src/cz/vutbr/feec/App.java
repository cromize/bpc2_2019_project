package cz.vutbr.feec;

import cz.vutbr.feec.core.Database;
import cz.vutbr.feec.empl.AEmployee;
import cz.vutbr.feec.empl.Assistant;
import cz.vutbr.feec.empl.CEO;
import cz.vutbr.feec.empl.Dev;
import cz.vutbr.feec.empl.Tech;
import cz.vutbr.feec.job.AssistJob;
import cz.vutbr.feec.job.DevJob;
import cz.vutbr.feec.job.TechJob;

public class App {
  public static void main(String[] args) {
    Database db = new Database();
    db.addEmployee(new CEO(1337, "name"));
    db.addEmployee(new Assistant(1338, "name"));
    db.addEmployee(new Dev(1337, "name"));
    db.addEmployee(new Dev(1337, "name"));
    
    db.addJob(new AssistJob());
    db.addJob(new TechJob());
    db.addJob(new AssistJob());
    db.addJob(new DevJob());
    db.removeJob(new AssistJob());
    
    
    System.out.println(new CEO(1337, "lol").canDoJob(new TechJob()));

    System.out.println(db.getHighestPaidEmpl().toString());
    System.out.println(db.getLowestPaidEmpl().toString());
  }
}
