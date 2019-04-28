package cz.vutbr.feec;


import cz.vutbr.feec.core.Core;
import cz.vutbr.feec.core.Database;
import cz.vutbr.feec.view.CLIView;

public class App {
  public static void main(String[] args) {
    Database db = new Database();
    CLIView view = new CLIView();
    Core core = new Core(view, db);
    
    core.run();
    System.exit(0);
  }
}
