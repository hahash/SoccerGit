package sl314.util;

// Java I/O imports
import java.io.File;

public final class MyUtil {

 
  public static String normalizeFilePath(String path) {
    StringBuffer buffer = new StringBuffer(path);
    for ( int i=0; i < buffer.length(); i++ ) {
      char c = buffer.charAt(i);
      if ( (c == '/') || (c == '\\') ) {
        buffer.setCharAt(i, File.separatorChar);
      }
    }
    return new String(buffer);
  }

}
