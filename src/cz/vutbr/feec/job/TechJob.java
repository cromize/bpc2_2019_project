package cz.vutbr.feec.job;

public class TechJob extends AJob implements IJob {

  public TechJob() {
    super();
  }

  @Override
  public void doJob() {
    int counter = 0;
    for (char ch : worker.getName().toCharArray()) {
      if ("aeiou".indexOf(ch) >= 0) {
        counter++;
      }
    }
    System.out.println("Pocet samohlasek v mem jmene je " + counter);
  }

}
