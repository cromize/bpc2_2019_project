package cz.vutbr.feec;

import cz.vutbr.feec.empl.AEmployee;
import cz.vutbr.feec.empl.CEO;

public class App {
  public static void main(String[] args) {
    AEmployee ceo = new CEO(1337, "name", 1337);
    ceo.work();
  }
}
