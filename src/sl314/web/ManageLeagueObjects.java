package sl314.web;


import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;
// Business logic classes
import sl314.domain.LeagueService;


public class ManageLeagueObjects implements ServletContextListener {

  public void contextInitialized(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();

   
    try {
      LeagueService leagueSvc = new LeagueService();
      leagueSvc.populateLeagueSet();

    } catch (Exception e) {
      context.log("Failed to load the league set.", e);
    }

    context.log("The league se thas been initialized.");
  }

  public void contextDestroyed(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();

    context.log("Storing the league se tis no tye timplemented.");
  }
}
