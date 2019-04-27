package cz.vutbr.feec.core;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cz.vutbr.feec.empl.AEmployee;
import cz.vutbr.feec.empl.CEO;
import cz.vutbr.feec.empl.EmployeeType;
import cz.vutbr.feec.job.AJob;

public class Database {
  private boolean ceoInHouse;
  private Map<Integer, AEmployee> employees;
  private List<AJob> jobs;
  
  public Database() {
    employees = new HashMap<>();
    jobs = new LinkedList<>();
    ceoInHouse = false;
  }
  
  public void addEmployee(AEmployee empl) {
    // there can be only one CEO
    if (empl instanceof CEO) {
      if (ceoInHouse) {
        System.out.println("Nelze pridat dalsi CEO");
        return;
      }
      ceoInHouse = true;
    }

    employees.put(empl.getId(), empl);
  }
  
  public void removeEmployee(int id) {
    if (employees.get(id) instanceof CEO) {
      ceoInHouse = false;
    }
    employees.remove(id);
    this.rebalanceJobs();
  }
  
  public AEmployee getEmployee(int id) {
    return employees.get(id);
  }
  
  public void setEmployeeSick(int id) {
    employees.get(id).setType(EmployeeType.INACTIVE);
    this.rebalanceJobs();
  }

  public void unsetEmployeeSick(int id) {
    AEmployee empl = employees.get(id);
    if (empl.getType() == EmployeeType.INACTIVE) {
      empl.setType(EmployeeType.ACTIVE);
    }
  }

  public void addJob(AJob job) {
    AEmployee empl = getBestWorkerForJob(job);
    empl.increaseWorkHours(1);
    job.setWorker(empl);
    jobs.add(job);
  }
  
  public void removeJob(AJob job) {
    AJob j = jobs.stream()
                 .filter(a -> a.getClass().isInstance(job))
                 .max((o1, o2) -> Integer.compare(o1.getWorker().getWage(),
                                                  o2.getWorker().getWage())).get();

    j.getWorker().decreaseWorkHours(1);
    jobs.remove(j);
  }
  
  public void rebalanceJobs() {
    this.resetAllWorkHours();
    for (AJob x : jobs) {
      AEmployee empl = this.getBestWorkerForJob(x);
      empl.increaseWorkHours(1);
      x.setWorker(empl);
    }
    
  }

  public AEmployee getBestWorkerForJob(AJob job) {
    Entry<Integer, AEmployee> entry = employees.entrySet()
                                      .stream()
                                      .filter(a -> a.getValue().getType() == EmployeeType.ACTIVE &&
                                                   a.getValue().canDoJob(job) &&
                                                   a.getValue().canWorkMore())
                                      .min(Map.Entry.comparingByValue(Comparator.comparingInt(AEmployee::getWage)))
                                      .get();
    return entry.getValue();
  }
  
  public void resetAllWorkHours() {
    for (AEmployee x : employees.values()) {
      x.setWorkHours(0);
    }
  }
  
  public List<AJob> getWorkerJobs(AEmployee empl) {
    List<AJob> workerJobs = new LinkedList<>();
    for (AJob x : jobs) {
      if (x.getWorker().getClass().isInstance(empl)) {
        workerJobs.add(x);
      }
    }
    return workerJobs;
  }
  
  public List<AEmployee> getDepartmentWorkers(AEmployee department) {
    List<AEmployee> workers = new LinkedList<>();
    for (AJob x : jobs) {
      if (x.getWorker().getClass().isInstance(department)) {
        workers.add(x.getWorker());
      }
    }
    return workers;
  }
  
  public int getDepartmentFreeHours(AEmployee department) {
    int freeHours = 0;
    for (AJob x : jobs) {
      if (x.getWorker().getClass().isInstance(department)) {
        // when maxWorkHours is set
        if (AEmployee.getMaxWorkHours() == Integer.MAX_VALUE) {
          return Integer.MAX_VALUE;
        }
        freeHours += AEmployee.getMaxWorkHours() - x.getWorker().getWorkHours();
      }
    }
    return freeHours;
    
  }
  
  public int getMonthlyBudget() {
    int budget = 0;
    for (AJob x : jobs) {
      if (x.getWorker().getWorkHours() == 0) {
        budget += 500;
      } else {
        budget += x.getWorker().getWage();
      }
    }
    return budget;
  }
  
}
