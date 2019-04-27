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
  
  public int promptMenu(int max) {
    view.printMenu();
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
