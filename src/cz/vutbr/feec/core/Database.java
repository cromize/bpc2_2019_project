package cz.vutbr.feec.core;

import java.util.HashMap;
import java.util.Map;

import cz.vutbr.feec.empl.AEmployee;

public class Database {
  private Map<Integer, AEmployee> employees;
  
  public Database() {
    employees = new HashMap<>();
  }
  
  public void addEmployee(String name, int id, AEmployee empl) {
    employees.put(id, empl);
  }

}
