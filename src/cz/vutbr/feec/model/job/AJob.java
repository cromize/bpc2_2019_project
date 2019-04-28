package cz.vutbr.feec.model.job;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import cz.vutbr.feec.core.Core;
import cz.vutbr.feec.model.empl.AEmployee;

public abstract class AJob {
  protected AEmployee worker;
  protected AEmployee[] qualified;

  public AJob() {
    super();
  }
  
  public abstract void doJob(Core core);
  
  public void setWorker(AEmployee worker) {
    this.worker = worker;
  }

  public AEmployee getWorker() {
    return worker;
  }

  @JsonProperty(access = Access.READ_ONLY)
  public AEmployee[] getQualifiedList() {
    return qualified;
  }

  public static AJob getType(int type) {
    if (type > 0 && type < 4) {
      AJob[] jobs = new AJob[] {new AssistJob(), new TechJob(),
                                new DevJob()};
      return jobs[type-1];
    }
    return null;
  }
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
