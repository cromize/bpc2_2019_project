package cz.vutbr.feec.empl;

public abstract class AEmployee {
  protected int id;
  protected String name;
  protected long salary;

  public AEmployee(int id, String name, long wage) {
    super();
    setId(id);
    setName(name);
    setSalary(wage);
  }
  
  public abstract void work();
  
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

  public long getSalary() {
    return salary;
  }

  public void setSalary(long salary) {
    this.salary = salary;
  }
}
