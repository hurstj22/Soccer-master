package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    // dummied up variable; you will need to change this
    //Key: firstName##lastName
    private Hashtable<String, SoccerPlayer> database = new Hashtable<String, SoccerPlayer>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        if(!database.containsKey(firstName + "##" + lastName)) {
            database.put(firstName + "##" + lastName, new SoccerPlayer(firstName, lastName, uniformNumber, teamName));
            return true;
        }
        return false;
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        if(database.containsKey(firstName + "##" + lastName)) {
            database.remove(firstName + "##" + lastName); //yeet
            return true;
        }
        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        if(database.containsKey(firstName + "##" + lastName)) {
            return database.get(firstName + "##" + lastName);
        }
        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        if(database.containsKey(firstName + "##" + lastName)) {
            database.get(firstName + "##" + lastName).bumpGoals(); //bump it up!
            return true;
        }
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        if(database.containsKey(firstName + "##" + lastName)) {
            database.get(firstName + "##" + lastName).bumpYellowCards(); //bump it up!
            return true;
        }
        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        if(database.containsKey(firstName + "##" + lastName)) {
            database.get(firstName + "##" + lastName).bumpRedCards(); //bump it up!
            return true;
        }
        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        Enumeration<SoccerPlayer> values = database.elements();
        int counter = -1;

        if(teamName == null){
            return database.size();
        }
        else{
            while(values.hasMoreElements()){
                if(values.nextElement().getTeamName().equalsIgnoreCase(teamName)){
                    counter++;
                }
            }
            return counter;
        }
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerIndex(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerIndex(int idx, String teamName) {
        Enumeration<SoccerPlayer> values = database.elements();

        if(idx > numPlayers(teamName)){
            return null;
        }
            while(values.hasMoreElements() && idx >= 0){
                SoccerPlayer temp = values.nextElement(); //make a temporary player that's updated each time
                if(teamName !=  null) {
                    if (temp.getTeamName().equalsIgnoreCase(teamName)){ //if there's a teamname, only decrement when a member is found
                        if(idx <= 0){
                            return temp;
                        }
                        idx--;
                    }
                }
                else {
                    if(idx <= 0){
                        return temp;
                    }
                    idx--; //if there's no teamname just decrement each time
                }
            }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        return file.exists();
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        return false;
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        return new HashSet<String>();
    }

    /**
     * Helper method to empty the database and the list of teams in the spinner;
     * this is faster than restarting the app
     */
    public boolean clear() {
        if(database != null) {
            database.clear();
            return true;
        }
        return false;
    }
}
