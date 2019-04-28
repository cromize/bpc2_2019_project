package cz.vutbr.feec.model.empl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cz.vutbr.feec.model.job.AJob;

public abstract class AEmployee {
  protected int id;
  protected String name;
  protected String surname;
  protected int wage;
  protected int workHours;
  protected EmployeeType employeeType;
  private static int maxWorkHours = Integer.MAX_VALUE;

  public AEmployee()  {
    super();
    employeeType = EmployeeType.ACTIVE;
  }

  public AEmployee(int id, String name, String surname, int wage) {
    super();
    setId(id);
    setName(name);
    setSurname(surname);
    setWage(wage);
    //setWorkHours(Integer.MAX_VALUE);
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

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
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

  public static AEmployee getType(int type) {
    if (type > 0 && type < 5) {
      AEmployee[] empl = new AEmployee[] {new Assistant(), new Tech(),
                                          new Dev(), new CEO()};
      return empl[type-1];
    }
    return null;
  }
  
  @JsonIgnore
  public String getInfo() {
    String tmp = Integer.valueOf(AEmployee.getMaxWorkHours()).toString();
    if (AEmployee.getMaxWorkHours() == Integer.MAX_VALUE) {
      tmp = "neomezeno";
    }
    return String.format("Jmeno: %s %s"
                       + "\tVytizeni: %d/%s (h/mesic)"
                       + "\tMzda: %d Kc/h"
                       + "\tStatus: %s",
                       this.getName(),
                       this.getSurname(),
                       this.getWorkHours(), tmp,
                       this.getWage(),
                       this.getType());
  }
  
  @Override 
  public String toString() {
    return String.format("ID: %d  Jmeno: %s %s  Pozice: %s",
                         getId(),
                         getName(),
                         getSurname(),
                         getClass().
                         getSimpleName());
  }

}