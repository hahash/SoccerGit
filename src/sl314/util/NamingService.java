package sl314.util;

import java.util.Map;
import java.util.HashMap;


public final class NamingService {

 
  public static NamingService getInstance() {
    return theObject;
  }

 
  public Object getAttribute(String name) {
    return nameValuePairs.get(name);
  }

 
  public void setAttribute(String name, Object object) {
    if ( nameValuePairs.get(name) == null ) {
      nameValuePairs.put(name, object);
    } else {
      throw new IllegalArgumentException("This name, " + name
                                         + ", is already in use.");
    }
  }

  
  public void removeAttribute(String name) {
    nameValuePairs.remove(name);
  }

  
  private static NamingService theObject = new NamingService();

  private Map nameValuePairs;

  private NamingService() {
    nameValuePairs = new HashMap();
  }
}
