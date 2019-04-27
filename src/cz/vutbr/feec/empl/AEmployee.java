package cz.vutbr.feec.empl;

import cz.vutbr.feec.job.AJob;

public abstract class AEmployee {
  protected int id;
  protected String name;
  protected int wage;
  protected int workHours;
  protected EmployeeType employeeType;
  private static int maxWorkHours = 10;

  public AEmployee()  {
  }

  public AEmployee(int id, String name, int wage) {
    super();
    setId(id);
    setName(name);
    setWage(wage);
    setWorkHours(0);
    employeeType = EmployeeType.ACTIVE;
  }
  
  public void work() {
  }
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getWage() {
    return wage;
  }

  public void setWage(int wage) {
    this.wage = wage;
  }
  
  public void setWorkHours(int workHours) {
    this.workHours = workHours;
  }

  public int getWorkHours() {
    return workHours;
  }

  public EmployeeType getType() {
    return employeeType;
  }

  public void increaseWorkHours(int workHours) {
    this.workHours += workHours;
  }

  public void decreaseWorkHours(int workHours) {
    this.workHours -= workHours;
  }

  public boolean canDoJob(AJob job) {
    for (AEmployee x : job.getQualifiedList()) {
      if (this.getClass().isInstance(x)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean canWorkMore() {
    if (workHours < maxWorkHours) return true;
    return false;
  }
}
