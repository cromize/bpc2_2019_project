package cz.vutbr.feec.view;

import java.util.List;

import cz.vutbr.feec.model.empl.AEmployee;

public class CLIView {
  public CLIView() {
  }
  
  public void printWelcome() {
    System.out.printf("*** Manazer Zamestnancu ***\n");
  }
  
  public void printMenu() {
    System.out.printf("\n*** Moznosti: \n"
                    + " 1) Pridat zamestnance\n"
                    + " 2) Propustit zamestnance\n"
                    + " 3) Nastavit status nemocny\n"
                    + " 4) Nastavit status uzdraveny\n"
                    + "\n"
                    + " 5) Zadat praci\n"
                    + " 6) Zrusit praci\n"
                    + " 7) Aktivace zamestnance\n"
                    + " 8) Nastavit maximalni uvazek\n"
                    + "\n"
                    + " 9) Vypis poctu zamestnancu oddeleni\n"
                    + "10) Vypis financnich nakladu za mesic\n"
                    + "11) Vypis vsech zamestancu\n"
                    + "\n"
                    + "12) Ulozit databazi\n"
                    + "13) Nahrat databazi\n");
  }
  
  public void printEmployeePositions() {
    System.out.printf("\n*** Pozice: \n"
                    + " 1) Asistent\n"
                    + " 2) Technicky pracovnik\n"
                    + " 3) Vyvojovy pracovnik\n"
                    + " 4) Reditel\n");
  }
  
  public void printJobTypes() {
    System.out.printf("\n*** Typ prace: \n"
                    + " 1) Administrativni prace\n"
                    + " 2) Technicka prace\n"
                    + " 3) Vyvojova prace\n");
  }

  public void printEmployeeDepartCount(String pos, int workerCount, String freeHours) {
    System.out.printf("\nPozice: %s\t\tPocet zamestnancu: %d\tVolne uvazky (h/mesic): %s",
                      pos, workerCount, freeHours);
  }

}
