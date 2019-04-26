package cz.vutbr.feec.job;

public class AssistJob extends AJob implements IJob {

  public AssistJob() {
    super();
  }

  @Override
  public void doJob() {
    System.out.println("Vsechny informace o " + worker.getName());
  }

}
