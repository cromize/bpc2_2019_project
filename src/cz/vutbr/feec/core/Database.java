package cz.vutbr.feec.core;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cz.vutbr.feec.empl.AEmployee;
import cz.vutbr.feec.empl.Assistant;
import cz.vutbr.feec.empl.EmployeeType;
import cz.vutbr.feec.job.AJob;

public class Database {
  private Map<Integer, AEmployee> employees;
  private List<AJob> jobs;
  
  public Database() {
    employees = new HashMap<>();
    jobs = new LinkedList<>();
  }
  
  public void addEmployee(AEmployee empl) {
    employees.put(empl.getId(), empl);
  }
  
  public void removeEmployee(int id) {
    employees.remove(id);
  }
  
  public void addJob(AJob job) {
    for (AEmployee empl : employees.values()) {
      
    }
    job.setWorker(new Assistant(1, "abaa"));
    job.doJob();
    jobs.add(job);
  }
  
  public void removeJob(AJob job) {
    int lastWage = 0;
    AJob lastJob = null;
    for (AJob x : this.jobs) {
      if (job.getClass().isInstance(x)) {
        int wage = x.getWorker().getWage();
        if (lastWage < wage) {
          lastWage = wage;
          lastJob = x;
        }
      }
    }
    lastJob.getWorker().decreaseWorkHours(1);
    this.jobs.remove(lastJob);
  }
  
  public AEmployee getHighestPaidEmpl() {
    Entry<Integer, AEmployee> entry = employees.entrySet()
                                      .stream()
                                      .filter(a -> a.getValue().getType() == EmployeeType.ACTIVE)
                                      .max(Map.Entry.comparingByValue(Comparator.comparingInt(AEmployee::getWage)))
                                      .get();
    return entry.getValue();
  }

  public AEmployee getLowestPaidEmpl() {
    Entry<Integer, AEmployee> entry = employees.entrySet()
                                      .stream()
                                      .filter(a -> a.getValue().getType() == EmployeeType.ACTIVE)
                                      .min(Map.Entry.comparingByValue(Comparator.comparingInt(AEmployee::getWage)))
                                      .get();
    return entry.getValue();
  }

}
