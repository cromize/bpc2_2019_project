package cz.vutbr.feec.job;

import cz.vutbr.feec.empl.AEmployee;

public abstract class AJob {
  private AEmployee worker;
  
  public AJob() {
  }
  
  public abstract void doJob();

}
