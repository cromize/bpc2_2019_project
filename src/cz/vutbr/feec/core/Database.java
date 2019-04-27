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
  // TODO: make private
  public List<AJob> jobs;
  
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

    // non CEO
    employees.put(empl.getId(), empl);
  }
  
  public void removeEmployee(int id) {
    if (employees.get(id) instanceof CEO) {
      ceoInHouse = false;
    }
    employees.remove(id);
  }
  
  public void addJob(AJob job) {
    Entry<Integer, AEmployee> entry = employees.entrySet()
                                      .stream()
                                      .filter(a -> a.getValue().getType() == EmployeeType.ACTIVE &&
                                                   a.getValue().canDoJob(job) &&
                                                   a.getValue().canWorkMore())
                                      .min(Map.Entry.comparingByValue(Comparator.comparingInt(AEmployee::getWage)))
                                      .get();

    entry.getValue().increaseWorkHours(1);
    job.setWorker(entry.getValue());
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
