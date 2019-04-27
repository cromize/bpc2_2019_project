package cz.vutbr.feec.empl;

import cz.vutbr.feec.job.AJob;

public abstract class AEmployee {
  protected int id;
  protected String name;
  protected int wage;
  protected int workHours;
  protected EmployeeType employeeType;
  private static int maxWorkHours = Integer.MAX_VALUE;

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

  public void setType(EmployeeType type) {
    this.employeeType = type;
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
    if (this instanceof CEO) return true;
    if (workHours < maxWorkHours) return true;
    return false;
  }

  public static int getMaxWorkHours() {
    return maxWorkHours;
  }
  
  public static void setMaxWorkHours(int workHours) {
    maxWorkHours = workHours;
  }
  
  @Override 
  public String toString() {
    return String.format("ID: %d  Jmeno: %s  Pozice: %s", getId(), getName(), getClass().getSimpleName());
  }
  

}
