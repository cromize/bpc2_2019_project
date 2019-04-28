package cz.vutbr.feec.core;

import cz.vutbr.feec.view.CLIView;

public class Core {
  protected CLIView view;
  protected Database db;
  protected Prompt cliPrompt;
  protected Features ft;

  public Core(CLIView view, Database db) {
    this.view = view;
    this.db = db;
    this.cliPrompt = new Prompt(this);
    this.ft = new Features(this);
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
    switch (ProgramState.valueOfId(operation)) {
    case ADD_EMPL:
      ft.userAddEmployee();
      break;
      
    case DEL_EMPL:
      ft.userDeleteEmployee();
      break;
      
    case SET_SICK:
      ft.userSetSick();
      break;

    case UNSET_SICK:
      ft.userUnsetSick();
      break;
    
    case ADD_JOB:
      ft.userAddJob();
      break;

    case DEL_JOB:
      ft.userDeleteJob();
      break;
    
    case DO_JOB:
      ft.userDoJob();
      return;

    case SET_MAX_WORK_HOURS:
      ft.userSetMaxHours();
      break;

    case PRINT_EMPL_DEPART_COUNT:
      ft.userPrintEmployeeDepartCount();
      break;
    
    case PRINT_MONTHLY_BUDGET:
      ft.userPrintMonthlyBudget();
      break;
      
    case PRINT_ALL_EMPL:
      break;
      
    case SAVE_DB:
      ft.userSaveDB();
      break;

    case LOAD_DB:
      ft.userLoadDB();
      break;
    }
    
  }
  
  public CLIView getView() {
    return view;
  }
  
  public Prompt getPrompt() {
    return cliPrompt;
  }
  
}
