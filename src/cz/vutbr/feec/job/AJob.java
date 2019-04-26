package cz.vutbr.feec.job;

import cz.vutbr.feec.empl.AEmployee;

public abstract class AJob {
  protected AEmployee worker;
  
  public AJob() {
  }
  
  public abstract void doJob();
  
  public void setWorker(AEmployee worker) {
    this.worker = worker;
  }
  
  public AEmployee getWorker() {
    return worker;
  }
}
