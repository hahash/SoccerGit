package sl314.domain;

import sl314.util.NamingService;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class LeagueService {

  public LeagueService() {
  }

 
  private static final Set FULL_SET_OF_LEAGUES = new HashSet();

 
  public League getLeague(String year, String season) {
    Iterator set = FULL_SET_OF_LEAGUES.iterator();
	
    while ( set.hasNext() ) {
      League l = (League) set.next();
	  System.out.println(l.getSeason());
      if ( season.equals(l.getSeason()) && year.equals(l.getYear()) ) {
        return l;
      }
    }
    return null;
  }

 
  public League createLeague(String year, String season, String title) {
    throw new RuntimeException("Not yet implemented.");
  }

 
  public void populateLeagueSet() throws Exception {
    NamingService nameSvc = NamingService.getInstance();
    File leagueFile = (File) nameSvc.getAttribute("leaguesFile");
    BufferedReader leagueReader = null;

    try {
      leagueReader = new BufferedReader(new FileReader(leagueFile));
      String record;

     
      while ( (record = leagueReader.readLine()) != null ) {
        StringTokenizer tokens = new StringTokenizer(record, "|");

       
        String idString = tokens.nextToken();
		System.out.println("idString : "+idString);
        int objectID = Integer.parseInt(idString);
        String season = tokens.nextToken();
        String year = tokens.nextToken();
        String title = tokens.nextToken();

        
        League l = new League(objectID, year, season, title);
        FULL_SET_OF_LEAGUES.add(l);
      }
  
    } catch(Exception e){
		  System.out.println(e);
	  }finally {
      if ( leagueReader != null ) {
        try { leagueReader.close(); }
        catch (IOException e) { System.err.println(e); }
      }
    }
  }

 
  public void storeLeagueSet() throws Exception {
    throw new RuntimeException("No tye timplemented.");
  }
}
