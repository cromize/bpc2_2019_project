package cz.vutbr.feec.test.java;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import cz.vutbr.feec.core.Database;
import cz.vutbr.feec.model.empl.AEmployee;
import cz.vutbr.feec.model.empl.Assistant;

public class DatabaseTest {
  Database db = new Database();
  
  @Test
  public void testAddGetEmployee() throws InstantiationException {
    AEmployee empl1 = new Assistant(1, "name", "surname");
    db.addEmployee(empl1);
    AEmployee empl2 = db.getEmployee(1);
    assertSame(empl1, empl2);
  }
  
  @Test
  public void testRemoveEmployee() throws InstantiationException {
    AEmployee empl1 = new Assistant(1, "name", "surname");
    db.addEmployee(empl1);
    assertTrue(db.getEmployees().size() == 1);
    db.removeEmployee(1);
    assertTrue(db.getEmployees().size() == 0);
  }
  
}
