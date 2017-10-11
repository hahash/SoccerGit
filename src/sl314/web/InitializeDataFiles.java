package sl314.web;

// Servlet imports
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;
// Support classes
import sl314.util.MyUtil;
import sl314.util.NamingService;
import java.io.File;

public class InitializeDataFiles implements ServletContextListener {

  public void contextInitialized(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    NamingService nameSvc = NamingService.getInstance();
    String dataDirPath = context.getInitParameter("dataDirPath");
	System.out.println(dataDirPath);
    File dataDirectory = new File(MyUtil.normalizeFilePath(dataDirPath));

   
    nameSvc.setAttribute("leaguesFile",
                         new File(dataDirectory, "leagues.txt"));
    nameSvc.setAttribute("playersFile",
                         new File(dataDirectory, "players.txt"));
    nameSvc.setAttribute("registrationFile",
                         new File(dataDirectory, "registration.txt"));

    context.log("Data files have been initialized.");
  }

  public void contextDestroyed(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();

    
    NamingService nameSvc = NamingService.getInstance();
    nameSvc.removeAttribute("leaguesFile");
    nameSvc.removeAttribute("playersFile");
    nameSvc.removeAttribute("registrationFile");

    context.log("Data files have been extinguished.");
  }
}
