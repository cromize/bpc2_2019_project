package cz.vutbr.feec.core;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import cz.vutbr.feec.io.ReadFromFile;
import cz.vutbr.feec.io.WriteToFile;
import cz.vutbr.feec.model.empl.AEmployee;
import cz.vutbr.feec.model.empl.EmployeeType;
import cz.vutbr.feec.model.job.AJob;

public class Features {
  Core core;
  public Features(Core core) {
    this.core = core;
  }

  // *** Employee ***

  public void userAddEmployee() {
    core.view.printEmployeePositions();
    int position = core.cliPrompt.promptNumber(4, "\nZadejte moznost: ");
    String name = core.cliPrompt.promptString("\nZadejte jmeno: ");
    String surname = core.cliPrompt.promptString("\nZadejte prijmeni: ");
    try {
      int id = core.cliPrompt.promptUserId(true);
      core.db.addEmployee(position, name, surname, id);
      core.db.rebalanceJobs();
      System.out.println("\nZamestnanec byl pridan");
    } catch (NoSuchElementException | InstantiationException e) {
    }
  }
  
  public void userDeleteEmployee() {
    int id = core.cliPrompt.promptUserId(false);
    if (!core.db.getEmployees().containsKey(id)) {
      System.out.println("\nZadany zamestnanec neexistuje");
      return;
    }
    boolean isSafe = core.cliPrompt.promptIsSafe("\nOpravdu chcete smazat (A/N)? ");
    if (isSafe) {
      core.db.removeEmployee(id);
      core.db.rebalanceJobs();
      System.out.println("\nZamestnanec byl odstranen");
    }
  }
  
  public void userSetSick() {
    int id = core.cliPrompt.promptUserId(false);
    core.db.getEmployee(id).setType(EmployeeType.INACTIVE);
    System.out.println("\nZadany zamestnanec je oznacen jako nemocny");
    core.db.rebalanceJobs();
  }
  
  public void userUnsetSick() {
    int id = core.cliPrompt.promptUserId(false);
    core.db.getEmployee(id).setType(EmployeeType.ACTIVE);
    System.out.println("\nZadany zamestnanec je oznacen jako zdravy");
  }

  // *** Job ***
  
  public void userAddJob() {
    AJob job = core.cliPrompt.promptSelectJob();
    int addHours = core.cliPrompt.promptNumber(Integer.MAX_VALUE, "\nZadejte pocet hodin: ");
    try {
      for (int i = 0; i < addHours; i++) {
        core.db.addJob(job);
      }
    } catch (NoSuchElementException e) {
      System.out.println("\nNebyla zadana vsechna prace (nedostatek pracovniku)");
      return;
    }
    System.out.println("\nPrace byla zadana.");
  }
  
  public void userDeleteJob() {
    AJob job = core.cliPrompt.promptSelectJob();
    int delHours = core.cliPrompt.promptNumber(Integer.MAX_VALUE, "\nZadejte pocet hodin: ");
    try {
      for (int i = 0; i < delHours; i++) {
        core.db.removeJob(job);
      }
    } catch (NoSuchElementException e) {
      System.out.println("\nNelze zrusit nezadanou praci");
      return;
    }
    core.db.rebalanceJobs();
    System.out.println("\nPrace byla zrusena.");
  }
  
  public void userDoJob() {
    int id = 0;
    try {
      id = core.cliPrompt.promptUserId(false);
    } catch (NoSuchElementException e) {
      return;
    }

    AJob job = core.cliPrompt.promptSelectJob();
    AEmployee empl = core.db.getEmployee(id);

    for (AJob x : core.db.getJobs()) {
      if (job.getClass().isInstance(x) &&
          core.db.hasAssignedJob(empl, x) &&
          empl.canDoJob(x))
      {
        x.doJob(core);
        return;
      }
      else {
        System.out.println("\nZamestnanec nemuze vykonat nezadanou praci");
        return;
      }
    }
  }
  
  public void userSetMaxHours() {
    int num = core.cliPrompt.promptNumber(Integer.MAX_VALUE, "\nZadejte maximalni delku uvazku: ");
    AEmployee.setMaxWorkHours(num);
    core.db.rebalanceJobs();
    System.out.println("\nMaximalni uvazek byl nastaven");
  }
  
  public void userPrintEmployeeDepartCount() {
    for (int i = 1; AEmployee.getType(i) != null; i++) {
      AEmployee depart = AEmployee.getType(i);
      Integer freeHours = core.db.getDepartmentFreeHours(depart);
      String tmp = (freeHours == Integer.MAX_VALUE) ? "neomezeno" : freeHours.toString(); 

      core.view.printEmployeeDepartCount(depart.getClass().getSimpleName(),
                                    core.db.getDepartmentWorkers(depart).size(),
                                    tmp);
    }
    System.out.println();
  }
  
  public void userPrintMonthlyBudget() {
   System.out.printf("\n*** Mesicni vydaje jsou: %d Kc\n", core.db.getMonthlyBudget());
  }
  
  public void userPrintEmployees() {
    List<AEmployee> empls = core.db.getEmployees().values()
                                             .stream()
                                             .collect(Collectors.toList());
    int choice = core.cliPrompt.promptNumber(2,
                   "\nRadit dle ID(1), dle prijmeni sestupne(2): ");

    if (choice == 2) {
      empls = empls.stream()
                   .sorted((o1, o2) -> o1.getSurname().compareTo(o2.getSurname()))
                   .collect(Collectors.toList());
    }

    System.out.println("\n*** Vypis vsech zamestnancu");
    for (AEmployee x : empls){
      System.out.println(x.toString());
      List<AJob> jobs = core.db.getWorkerJobs(x);
      for (int i = 1; AJob.getType(i) != null; i++) {
        int hours = 0;
        for (AJob j : jobs) {
          if (AJob.getType(i).getClass().isInstance(j)) {
            hours++;
          }
        }
        if (hours > 0)
          System.out.printf("  * %s - %d h/mesic\n",
                            AJob.getType(i).getClass().getSimpleName(),
                            hours);
      }
    System.out.println();
    }
  }
  
  public void userSaveDB() {
    String fn = core.cliPrompt.promptString("\nZadejte jmeno souboru: ");
    String dump = core.db.dumpDatabase();
    WriteToFile.write(new File(fn), dump);
  }

  public void userLoadDB() {
    String fnn = core.cliPrompt.promptString("\nZadejte jmeno souboru: ");
    String recv = ReadFromFile.read(new File(fnn));
    core.db.loadDatabase(recv);
  }

}
