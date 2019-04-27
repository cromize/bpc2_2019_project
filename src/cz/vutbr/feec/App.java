package cz.vutbr.feec;

import cz.vutbr.feec.core.Database;
import cz.vutbr.feec.empl.AEmployee;
import cz.vutbr.feec.empl.Assistant;
import cz.vutbr.feec.empl.CEO;
import cz.vutbr.feec.empl.Dev;
import cz.vutbr.feec.empl.Tech;
import cz.vutbr.feec.job.AJob;
import cz.vutbr.feec.job.AssistJob;
import cz.vutbr.feec.job.DevJob;
import cz.vutbr.feec.job.TechJob;

public class App {
  public static void main(String[] args) {
    Database db = new Database();
    AEmployee.setMaxWorkHours(1);
    //db.addEmployee(new CEO(1337, "name"));
    db.addEmployee(new Dev(1339, "name"));
    db.addEmployee(new Assistant(1338, "name"));
    
    for (int i = 0; i < 1; i++) {
      db.addJob(new AssistJob());
      db.addJob(new AssistJob());
    }
    
    //db.removeJob(new AssistJob());

    for (AJob x : db.jobs) {
      System.out.println(x.getWorker());
    }
    //db.addJob(new TechJob());
    //db.addJob(new AssistJob());
    //db.addJob(new DevJob());
    //db.removeJob(new AssistJob());
    
  }
}
