package cz.vutbr.feec.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

import cz.vutbr.feec.model.job.AJob;

public class Prompt {
  private static BufferedReader cliInput;
  private Core core;
  
  public Prompt(Core core) {
    cliInput = new BufferedReader(new InputStreamReader(System.in));
    this.core = core;
  }

  public int promptUserId(boolean failWhenExist) {
      int id = promptNumber(Integer.MAX_VALUE, "\nZadejte ID: ");
      if (failWhenExist && core.getDB().getEmployees().containsKey(id)) {
        System.out.println("\nZadane ID jiz existuje");
        throw new NoSuchElementException();
      }
      else if (!failWhenExist && !core.getDB().getEmployees().containsKey(id)) {
        System.out.println("\nZadane ID neexistuje");
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
  
  public String promptString(String msg) {
    do {
      try {
        System.out.print(msg);
        String str = cliInput.readLine();
        return str;
      } catch (IOException e) {
      }
    } while (true);
  }
  
  public AJob promptSelectJob() {
    core.getView().printJobTypes();
    int type = promptNumber(3, "\nZadejte moznost: ");
    return AJob.getType(type);
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
