package cz.vutbr.feec;

import java.util.List;

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
    db.addEmployee(new Assistant(1339, "name"));
    db.addEmployee(new Assistant(1338, "name"));
    
    for (int i = 0; i < 1; i++) {
      db.addJob(new AssistJob());
      db.addJob(new AssistJob());
    }

    db.rebalanceJobs();
    
    List<AJob> jobs = db.getWorkerJobs(db.getEmployee(1338));
    System.out.println(jobs.toString());

    
    List<AEmployee> departmentWorkers = db.getDepartmentWorkers(new Assistant());
    System.out.println(departmentWorkers.toString());
    
    int freeHours = db.getDepartmentFreeHours(new Assistant());
    System.out.println(freeHours);
    
    int budget = db.getMonthlyBudget();
    System.out.println(budget);
    
    System.out.println(db.getEmployee(1338).toString());
  }
}
