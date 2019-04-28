package cz.vutbr.feec.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

import cz.vutbr.feec.model.empl.AEmployee;
import cz.vutbr.feec.model.empl.EmployeeType;
import cz.vutbr.feec.view.CLIView;

public class Core {
  BufferedReader cliInput;
  CLIView view;
  Database db;

  public Core(CLIView view, Database db) {
    this.view = view;
    this.db = db;
    this.cliInput = new BufferedReader(new InputStreamReader(System.in));
  }
  
  public void run() {
    view.printWelcome();
    while (true) {
      view.printMenu();
      int choice = promptNumber(ProgramState.values().length, "\nZadejte moznost: ");
      execute(choice);
    }
  }
  
  public void execute(int operation) {
    int id = 0;
    switch (ProgramState.valueOfId(operation)) {
    case ADD_EMPL:
      view.printEmployeePositions();
      int position = promptNumber(4, "\nZadejte moznost: ");
      String name = promptName();
      id = promptNumber(Integer.MAX_VALUE, "\nZadejte ID: ");
      db.addEmployee(position, name, id);
      System.out.println(db.getEmployees().toString());
      break;
      
    case DEL_EMPL:
      id = promptNumber(Integer.MAX_VALUE, "\nZadejte ID: ");
      if (!db.getEmployees().containsKey(id)) {
        System.out.println("\nZadany zamestnanec neexistuje");
        break;
      }
      boolean isSafe = promptIsSafe("\nOpravdu chcete smazat (A/N)? ");
      if (isSafe) {
        db.removeEmployee(id);
        db.rebalanceJobs();
      }
      break;
      
    case SET_SICK:
      id = prompUserId();
      db.getEmployee(id).setType(EmployeeType.INACTIVE);
      System.out.println("\nZadany zamestnanec je oznacen jako nemocny");
      db.rebalanceJobs();
      break;

    case UNSET_SICK:
      id = prompUserId();
      db.getEmployee(id).setType(EmployeeType.ACTIVE);
      System.out.println("\nZadany zamestnanec je oznacen jako zdravy");
      break;
    }
    
  }
  
  public int prompUserId() {
      int id = promptNumber(Integer.MAX_VALUE, "\nZadejte ID: ");
      if (!db.getEmployees().containsKey(id)) {
        System.out.println("\nZadany zamestnanec neexistuje");
        throw new NoSuchElementException();
      }
      return id;
  }
  
  public int promptNumber(int max, String msg) {
    do {
      try {
        System.out.print(msg);
        int choice = Integer.parseInt(cliInput.readLine());
        if (choice <= max && choice > 0) return choice;
      } catch (NumberFormatException | IOException e) {
      }
      System.out.println("Spatny vstup");
    } while (true);
  }
  
  public String promptName() {
    do {
      try {
        System.out.print("\nZadejte jmeno a prijmeni: ");
        String name = cliInput.readLine();
        return name;
      } catch (IOException e) {
      }
    } while (true);
  }
  
  public boolean promptIsSafe(String msg) {
    do {
      System.out.print(msg);
      try {
        String choice = cliInput.readLine();
        if (choice.equalsIgnoreCase("a")) {
          return true;
        } else if (choice.equalsIgnoreCase("n")) {
          return false;
        }
      } catch (IOException e) {
      }
    } while (true);
    
  }
}
