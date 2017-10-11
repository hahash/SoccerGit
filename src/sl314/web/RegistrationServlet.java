package sl314.web;

// Business Logic Componen timports
import sl314.domain.RegisterService;
import sl314.domain.League;
import sl314.domain.Player;

import javax.servlet.RequestDispatcher;
// Servlet imports
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





// Support classes
import sl314.util.Status;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Iterator;

public class RegistrationServlet extends HttpServlet {

  public static final String ACTION_SELECT_LEAGUE   = "SelectLeague";
  public static final String ACTION_ENTER_PLAYER    = "EnterPlayer";
  public static final String ACTION_SELECT_DIVISION = "SelectDivision";

  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
         throws IOException {

    String action = request.getParameter("action");

    if ( action.equals(ACTION_SELECT_LEAGUE) ) {
      processSelectLeague(request, response);

    } else if ( action.equals(ACTION_ENTER_PLAYER) ) {
      processEnterPlayer(request, response);

    } else if ( action.equals(ACTION_SELECT_DIVISION) ) {
      processSelectDivision(request, response);
    }
  }


  public void processSelectLeague(HttpServletRequest request,
                                  HttpServletResponse response)
         throws IOException {
   
    HttpSession session = request.getSession();

   
    RegisterService registerSvc = new RegisterService();

   
    Status status = new Status();
    request.setAttribute("status", status);

    
    String season = request.getParameter("season");
    String year = request.getParameter("year");

   
    if ( season.equals("UNKNOWN") ) {
      status.addException(new Exception("Please select a league season."));
    }
    if ( year.equals("UNKNOWN") ) {
      status.addException(new Exception("Please select a league year."));
    }

   
    League league = registerSvc.getLeague(year, season);
    if ( league == null ) {
      status.addException(
        new Exception("The league you selected does not yet exist;"
                      + " please select another."));
    }

  
    if ( ! status.isSuccessful() ) {
      try {
		generateErrorResponse(request, response);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
      return;
    }

    
    session.setAttribute("league", league);

  
    response.sendRedirect("enter_player.html");
  }

 
  public void processEnterPlayer(HttpServletRequest request,
                                 HttpServletResponse response)
         throws IOException {
   
    HttpSession session = request.getSession();

   
    RegisterService registerSvc = new RegisterService();

    
    Status status = new Status();
    request.setAttribute("status", status);


    String name = request.getParameter("name");
    String address = request.getParameter("address");
    String city = request.getParameter("city");
    String province = request.getParameter("province");
    String postalCode = request.getParameter("postalCode");

  
    if ( (name == null) || (name.length() == 0) ) {
      status.addException(new Exception("Please enter your name."));
    }
    if (   (address == null)    || (address.length() == 0)
        || (city == null)       || (city.length() == 0)
        || (province == null)   || (province.length() == 0)
        || (postalCode == null) || (postalCode.length() == 0) ) {
      status.addException(new Exception("Please enter your full address."));
    }

   
    if ( ! status.isSuccessful() ) {
      try {
		generateErrorResponse(request, response);
	} catch (Exception e) {
	
		e.printStackTrace();
	}
      return;
    }

    Player player = registerSvc.getPlayer(name);
    player.setAddress(address);
    player.setCity(city);
    player.setProvince(province);
    player.setPostalCode(postalCode);

   
    session.setAttribute("player", player);

   
    response.sendRedirect("select_division.html");
  }

 
  public void processSelectDivision(HttpServletRequest request,
                                    HttpServletResponse response)
         throws IOException {
   
    HttpSession session = request.getSession();

  
    League league = (League) session.getAttribute("league");
    Player player = (Player) session.getAttribute("player");

   
    RegisterService registerSvc = new RegisterService();

    
    Status status = new Status();
    request.setAttribute("status", status);

   
    String division = request.getParameter("division");

   
    if ( division.equals("UNKNOWN") ) {
      status.addException(new Exception("Please select a division."));
    }

   
    if ( ! status.isSuccessful() ) {
      try {
		generateErrorResponse(request, response);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
      return;
    }

    
    try {
      registerSvc.register(league, player, division);

   
    } catch (Exception e) {
      status.addException(e);
     
      return;
    }

   
    try {
		generateThankYouResponse(request, response);
	} catch (Exception e) {
		
	}
   
  }

 
  public void generateThankYouResponse(HttpServletRequest request,
                                       HttpServletResponse response)
         throws Exception {
	  
	  RequestDispatcher rdp = request.getRequestDispatcher("thankyouResponse.jsp");
	  rdp.forward(request, response);  
    
  }

 
  public void generateErrorResponse(HttpServletRequest request,
                                    HttpServletResponse response)
         throws Exception {
	  RequestDispatcher rdp = request.getRequestDispatcher("errorResponse.jsp");
	  rdp.forward(request, response);  
  }
}
















