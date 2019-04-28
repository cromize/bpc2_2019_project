package cz.vutbr.feec.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

import cz.vutbr.feec.model.empl.AEmployee;
import cz.vutbr.feec.model.empl.EmployeeType;
import cz.vutbr.feec.model.job.AJob;
import cz.vutbr.feec.model.job.AssistJob;
import cz.vutbr.feec.view.CLIView;

public class Core {
  private CLIView view;
  private Database db;
  private Prompt cliPrompt;

  public Core(CLIView view, Database db) {
    this.view = view;
    this.db = db;
    this.cliPrompt = new Prompt(this);
  }
  
  public Database getDB() {
    return db;
  }
  
  public void run() {
    view.printWelcome();
    while (true) {
      view.printMenu();
      int choice = cliPrompt.promptNumber(ProgramState.values().length, "\nZadejte moznost: ");
      execute(choice);
    }
  }
  
  public void execute(int operation) {
    int id = 0;
    switch (ProgramState.valueOfId(operation)) {
    case ADD_EMPL:
      view.printEmployeePositions();
      int position = cliPrompt.promptNumber(4, "\nZadejte moznost: ");
      String name = cliPrompt.promptString("\nZadejte jmeno a prijmeni: ");
      id = cliPrompt.promptUserId(true);
      db.addEmployee(position, name, id);
      System.out.println(db.getEmployees().get(1).getWage());
      System.out.println(db.getEmployees().get(1).canDoJob(new AssistJob()));
      System.out.println(db.getEmployees().get(1).canWorkMore());
      break;
      
    case DEL_EMPL:
      id = cliPrompt.promptUserId(false);
      if (!db.getEmployees().containsKey(id)) {
        System.out.println("\nZadany zamestnanec neexistuje");
        break;
      }
      boolean isSafe = cliPrompt.promptIsSafe("\nOpravdu chcete smazat (A/N)? ");
      if (isSafe) {
        db.removeEmployee(id);
        db.rebalanceJobs();
      }
      break;
      
    case SET_SICK:
      id = cliPrompt.promptUserId(false);
      db.getEmployee(id).setType(EmployeeType.INACTIVE);
      System.out.println("\nZadany zamestnanec je oznacen jako nemocny");
      db.rebalanceJobs();
      break;

    case UNSET_SICK:
      id = cliPrompt.promptUserId(false);
      db.getEmployee(id).setType(EmployeeType.ACTIVE);
      System.out.println("\nZadany zamestnanec je oznacen jako zdravy");
      break;
    
    case ADD_JOB:
      AJob job = cliPrompt.promptSelectJob();
      int addHours = cliPrompt.promptNumber(Integer.MAX_VALUE, "\nZadejte pocet hodin: ");
      for (int i = 0; i < addHours; i++) {
        db.addJob(job);
      }
      System.out.println("\nPrace byla zadana.");
      break;
    }
  }
  
  public CLIView getView() {
    return view;
  }
  
}
