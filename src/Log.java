/**
 * Interface for logging game. Expected implementations:
 *  File log
 *  SQL log
 *  Object log (in-game log)
 *  etc.
 * Created by pavel.krizhanovskiy on 07.04.2016.
 */
public interface Log {

    //TODO change return type
    public void addNomination(int turn, int from, int to);
    //TODO write javadoc about "-1" args
    //TODO change return type
    public int getNominations(int turn, int player);
    int nominatedBy(int turn, int player);
    void addSheriffCheck(int turn, int player);
    void addDonCheck(int turn, int player);
    int getSheriffCheck(int turn);
    int getDonCheck(int turn);
}
