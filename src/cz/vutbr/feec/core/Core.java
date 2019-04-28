package cz.vutbr.feec.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

import cz.vutbr.feec.model.empl.AEmployee;
import cz.vutbr.feec.model.empl.Assistant;
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
      try {
        id = cliPrompt.promptUserId(true);
        db.addEmployee(position, name, id);
        db.rebalanceJobs();
        System.out.println("\nZamestnanec byl pridan");
      } catch (NoSuchElementException | InstantiationException e) {
      }
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
        System.out.println("\nZamestnanec byl odstranen");
      }
      break;
      
    case SET_SICK:
      id = cliPrompt.promptUserId(false);
      db.getEmployee(id).setType(EmployeeType.INACTIVE);
      System.out.println("\nZadany zamestnanec je oznacen jako nemocny");
      try {
        db.rebalanceJobs();
      } catch (NoSuchElementException e) {
        System.out.println("\nZbyvajici praci nelze prerozdelit (nedostatek pracovniku)");
        
      }
      break;

    case UNSET_SICK:
      id = cliPrompt.promptUserId(false);
      db.getEmployee(id).setType(EmployeeType.ACTIVE);
      System.out.println("\nZadany zamestnanec je oznacen jako zdravy");
      break;
    
    case ADD_JOB:
      AJob job = cliPrompt.promptSelectJob();
      int addHours = cliPrompt.promptNumber(Integer.MAX_VALUE, "\nZadejte pocet hodin: ");
      try {
        for (int i = 0; i < addHours; i++) {
          db.addJob(job);
        }
      } catch (NoSuchElementException e) {
        System.out.println("\nNebyla zadana vsechna prace (nedostatek pracovniku)");
        break;
      }
      System.out.println("\nPrace byla zadana.");
      break;

    case DEL_JOB:
      AJob jobb = cliPrompt.promptSelectJob();
      int delHours = cliPrompt.promptNumber(Integer.MAX_VALUE, "\nZadejte pocet hodin: ");
      for (int i = 0; i < delHours; i++) {
        db.removeJob(jobb);
      }
      db.rebalanceJobs();
      System.out.println("\nPrace byla zrusena.");
      break;
    
    case DO_JOB:
      try {
        id = cliPrompt.promptUserId(false);
      } catch (NoSuchElementException e) {
        break;
      }
      AJob jobbb = cliPrompt.promptSelectJob();
      AEmployee empl = db.getEmployee(id);
      for (AJob x : db.getJobs()) {
        if(jobbb.getClass().isInstance(x)) {
          if (db.hasAssignedJob(empl, x)) {
            if (empl.canDoJob(x)) {
              x.doJob(this);
              return;
            } else {
              System.out.println("\nZamestnanec nemuze vykonat nezadanou praci");
              return;
            }
          } else {
            break;
          }
        }
      }
      System.out.println("\nZamestnanec nemuze vykonat zadany ukol");
      return;

    case SET_MAX_WORK_HOURS:
      int num = cliPrompt.promptNumber(Integer.MAX_VALUE, "\nZadejte maximalni delku uvazku: ");
      AEmployee.setMaxWorkHours(num);
      System.out.println("\nMaximalni uvazek byl nastaven");
      break;

    case PRINT_EMPL_DEPART_COUNT:
      for (int i = 1; AEmployee.getType(i) != null; i++) {
        AEmployee depart = AEmployee.getType(i);
        Integer freeHours = db.getDepartmentFreeHours(depart);
        String tmp = freeHours.toString();
        if (freeHours == Integer.MAX_VALUE) {
          tmp = "neomezeno";
        }
        view.printEmployeeDepartCount(depart.getClass().getSimpleName(),
                                      db.getDepartmentWorkers(depart).size(),
                                      tmp);
      }
      System.out.println();
      break;
    
    case PRINT_MONTHLY_BUDGET:
      System.out.printf("\n*** Mesicni vydaje jsou: %d Kc\n", db.getMonthlyBudget());
    }
    
  }
  
  public CLIView getView() {
    return view;
  }
  
  public Prompt getPrompt() {
    return cliPrompt;
  }
  
}
