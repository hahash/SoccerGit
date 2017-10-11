package sl314.util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Status {

  private List exceptions;

  public Status() {
    exceptions = new ArrayList();
  }

  public boolean isSuccessful() {
    return (exceptions.size() == 0);
  }

  public void addException(Exception ex) {
    exceptions.add(ex);
  }

  public Iterator getExceptions() {
    return exceptions.iterator();
  }
}
