import java.util.HashMap;

/**
 * Object in-game log
 * Created by pavel.krizhanovskiy on 07.04.2016.
 */
public class InGameLog implements Log {
    private HashMap<HashMap<Integer, Integer>, Integer> noms = new HashMap<>(20, 0.9f);
    private HashMap<Integer, Integer> sheriffChecks = new HashMap<>(10, 0.9f);
    private HashMap<Integer, Integer> donChecks = new HashMap<>(10, 0.9f);

    //TODO change return type
    public void addNomination(int turn, int from, int to) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(turn, from);
        noms.put(map, to);
    }

    //TODO write javadoc about "-1" args
    //TODO change return type
    public int getNominations(int turn, int player) {
        //TODO if player == -1 || turn == -1
        if (noms.size()==0) return -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(turn, player);
        return noms.get(map);
    }

    public int nominatedBy(int turn, int player) {
        return -1;
    }

    public void addSheriffCheck(int turn, int player) {
        sheriffChecks.put(turn, player);
    }

    public void addDonCheck(int turn, int player) {
        donChecks.put(turn, player);
    }

    public int getSheriffCheck(int turn) {
        return sheriffChecks.get(turn);
    }

    public int getDonCheck(int turn) {
        return donChecks.get(turn);
    }
}
