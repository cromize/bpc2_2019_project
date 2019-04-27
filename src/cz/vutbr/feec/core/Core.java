package cz.vutbr.feec.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
      int choice = promptChoice(ProgramState.values().length);
      execute(choice);
    }
  }
  
  public void execute(int operation) {
    switch (ProgramState.valueOfId(operation)) {
    case ADD_EMPL:
      view.printEmployeePositions();
      int choice = promptChoice(4);
      break;
    }
    
  }
  
  public int promptChoice(int max) {
    do {
      try {
        System.out.print("\nZadejte moznost: ");
        int choice = Integer.parseInt(cliInput.readLine());
        if (choice <= max && choice > 0) return choice;
      } catch (NumberFormatException | IOException e) {
      }
      System.out.println("Spatny vstup");
    } while (true);
  }
}
