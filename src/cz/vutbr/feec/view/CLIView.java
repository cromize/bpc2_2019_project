package cz.vutbr.feec.view;

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

}
