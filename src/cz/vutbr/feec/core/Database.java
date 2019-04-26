package cz.vutbr.feec.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cz.vutbr.feec.empl.AEmployee;
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
    jobs.add(job);
  }
  
  public void removeJob(AJob job) {
    for (AJob x : this.jobs) {
      if (job.getClass().isInstance(x)) {
        this.jobs.remove(x);
        return;
      }
    }
  }

}
