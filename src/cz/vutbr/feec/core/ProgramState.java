package cz.vutbr.feec.core;

public enum ProgramState {
  ADD_EMPL(1),
  DEL_EMPL(2),
  SET_SICK(3),
  UNSET_SICK(4),
  ADD_JOB(5),
  DEL_JOB(6),
  DO_JOB(7),
  SET_MAX_WORK_HOURS(8),
  PRINT_EMPL_DEPART_COUNT(9),
  PRINT_MONTHLY_BUDGET(10),
  PRINT_ALL_EMPL(11),
  SAVE_DB(12),
  LOAD_DB(13);
  
  public final int id;
  private ProgramState(int id) {
    this.id = id;
  }
  
  public static ProgramState valueOfId(int id) {
    for (ProgramState e : values()) {
      if (e.id == id) {
        return e;
      }
    }
    return null;
  }
  
}
