package cz.vutbr.feec.core;

import java.util.HashMap;
import java.util.Map;

import cz.vutbr.feec.empl.AEmployee;

public class Database {
  private Map<Integer, AEmployee> employees;
  
  public enum Job {
    Assistant,
    Dev,
    Tech
  }
  
  public Database() {
    employees = new HashMap<>();
  }
  
  public void addEmpl(String name, int id, Job job) {
    try {
      // instantiate using name from Job enum
      AEmployee empl = (AEmployee) Class.forName("cz.vutbr.feec.empl." + job.toString()).getDeclaredConstructor(Integer.TYPE, String.class).newInstance(id, name);
      employees.put(id, empl);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

}
