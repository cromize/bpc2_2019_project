package cz.vutbr.feec.core;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map.Entry;

import cz.vutbr.feec.model.empl.AEmployee;
import cz.vutbr.feec.model.empl.CEO;
import cz.vutbr.feec.model.empl.EmployeeType;
import cz.vutbr.feec.model.job.AJob;

public class Database {
  private boolean ceoInHouse;
  private Map<Integer, AEmployee> employees;
  private List<AJob> jobs;
  
  public Database() {
    employees = new HashMap<>();
    jobs = new LinkedList<>();
    ceoInHouse = false;
  }
  
  public void addEmployee(AEmployee empl) throws InstantiationException {
    // there can be only one CEO
    if (empl instanceof CEO) {
      if (ceoInHouse) {
        System.out.println("Nelze pridat dalsi CEO");
        throw new InstantiationException();
      }
      ceoInHouse = true;
    }

    employees.put(empl.getId(), empl);
  }

  public void addEmployee(int position, String name, String surname, int id) throws InstantiationException {
    // there can be only one CEO
    AEmployee empl = AEmployee.getType(position);
    if (employees.containsKey(id)) {
      System.out.println("\nNelze pridat zamestnance se stejnym ID");
      return;
    }

    if (AEmployee.getType(position) instanceof CEO) {
      if (ceoInHouse) {
        System.out.println("\nNelze pridat dalsi CEO");
        throw new InstantiationException();
      }
      ceoInHouse = true;
    }

    empl.setName(name);
    empl.setSurname(surname);
    empl.setId(id);
    employees.put(id, empl);
  }
  
  public void removeEmployee(int id) {
    if (employees.get(id) instanceof CEO) {
      ceoInHouse = false;
    }
    employees.remove(id);
  }
  
  public AEmployee getEmployee(int id) {
    return employees.get(id);
  }
  
  public Map<Integer, AEmployee> getEmployees() {
    return employees;
  }
  
  public void setEmployeeSick(int id) {
    employees.get(id).setType(EmployeeType.INACTIVE);
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

    if (j != null) j.getWorker().decreaseWorkHours(1);
    jobs.remove(j);
  }
  
  public List<AJob> getJobs() {
    return jobs;
  }
  
  public void rebalanceJobs() {
    this.resetAllWorkHours();
    for (AJob x : jobs) {
      try {
        AEmployee empl = this.getBestWorkerForJob(x);
        empl.increaseWorkHours(1);
        x.setWorker(empl);
      } catch (NoSuchElementException e) {
        x.setWorker(null);
        System.out.println("\nVsechna zadana prace nelze prerozdelit (nedostatek pracovniku)");
      }
    }
    
  }
  
  public boolean hasAssignedJob(AEmployee worker, AJob job) { 
    for (AJob x : jobs) {
      if (x.getWorker() == null) continue;
      if (x.getWorker().getId() == worker.getId()) {
        return true;
      }
    }
    return false;
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
      if (x.getWorker() == null) continue;
      if (x.getWorker().getClass().isInstance(empl)) {
        workerJobs.add(x);
      }
    }
    return workerJobs;
  }
  
  public List<AEmployee> getDepartmentWorkers(AEmployee department) {
    List<AEmployee> workers = new LinkedList<>();
    for (AEmployee x : employees.values()) {
      if (x.getClass().isInstance(department)) {
        workers.add(x);
      }
    }
    return workers;
  }
  
  public int getDepartmentFreeHours(AEmployee department) {
    int freeHours = 0;
    if (department instanceof CEO) return Integer.MAX_VALUE;
    if (AEmployee.getMaxWorkHours() == Integer.MAX_VALUE) {
      return Integer.MAX_VALUE;
    }
    for (AEmployee x : employees.values()) {
      if (x.getClass().isInstance(department)) {
        freeHours += AEmployee.getMaxWorkHours() - x.getWorkHours();
      }
    }
    return freeHours;
    
  }
  
  @JsonIgnore
  public int getMonthlyBudget() {
    int budget = 0;
    for (AEmployee empl : employees.values()) {
      if (empl.getWorkHours() == 0) {
        budget += 500;
      }
    }
    for (AJob x : jobs) {
      if (x.getWorker() == null) continue;
      if (x.getWorker().getWorkHours() != 0) {
        budget += x.getWorker().getWage();
      }
    }
    return budget;
  }
  
  public String dumpDatabase() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enableDefaultTyping();
    try {
      return mapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } 
    return null;
  }
  
  public void loadDatabase(String dump) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enableDefaultTyping();
    try {
      Database loadedDB = mapper.readValue(dump, new TypeReference<Database>() {});
      this.employees = loadedDB.getEmployees();
      this.jobs = loadedDB.getJobs();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
}
