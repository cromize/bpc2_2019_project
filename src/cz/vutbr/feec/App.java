package cz.vutbr.feec;

import cz.vutbr.feec.core.Database;
import cz.vutbr.feec.empl.AEmployee;
import cz.vutbr.feec.empl.Assistant;
import cz.vutbr.feec.empl.CEO;
import cz.vutbr.feec.empl.Dev;
import cz.vutbr.feec.empl.Tech;
import cz.vutbr.feec.job.AssistJob;
import cz.vutbr.feec.job.DevJob;

public class App {
  public static void main(String[] args) {
    Database db = new Database();
    db.addEmployee(new CEO(1337, "name"));
    db.addEmployee(new Assistant(1338, "name"));
    db.removeEmployee(1337);
    
    db.addJob(new AssistJob());
    db.addJob(new DevJob());
    db.addJob(new AssistJob());
    db.removeJob(new AssistJob());
  }
}
