package sl314.domain;

import sl314.util.NamingService;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


public class RegisterService {

  private File playerFile;
  private File regFile;

  public RegisterService() {
    NamingService nameSvc = NamingService.getInstance();
    this.playerFile = (File) nameSvc.getAttribute("playersFile");
    this.regFile = (File) nameSvc.getAttribute("registrationFile");
  }

  
  public League getLeague(String year, String season) {
    LeagueService leagueSvc = new LeagueService();
    return leagueSvc.getLeague(year, season);
  }

  
  public Player getPlayer(String name) {
    return new Player(name);
  }

  
  public void register(League league, Player player, String division)
         throws Exception {
    PrintWriter playerWriter = null;
    PrintWriter regWriter = null;

    try {
      FileWriter playerFW = new FileWriter(playerFile.getAbsolutePath(), true);
      playerWriter = new PrintWriter(playerFW);
      FileWriter regFW = new FileWriter(regFile.getAbsolutePath(), true);
      regWriter = new PrintWriter(regFW);

      
      playerWriter.print(player.name);
      playerWriter.print('|' + player.address);
      playerWriter.print('|' + player.city);
      playerWriter.print('|' + player.province);
      playerWriter.print('|' + player.postalCode );
      playerWriter.println();
  
      regWriter.print(league.objectID);
      regWriter.print('|' + player.name);
      regWriter.print('|' + division );
      regWriter.println();

    } catch (Exception e) {
      throw e;

   
    } finally {
      if ( playerWriter != null ) {
        try { playerWriter.close(); }
        catch (Exception e) { System.err.println(e); }
      }
      if ( regWriter != null ) {
        try { regWriter.close(); }
        catch (Exception e) { System.err.println(e); }
      }
    }
  }
}
