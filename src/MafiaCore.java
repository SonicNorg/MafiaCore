import java.util.*;

/**
 * Created by Norg on 13.02.2016.
 */
public class MafiaCore {
    private int curPlayer=-1, nextPlayer=0;
    GamePhase phase;
    private GameLog gameLog = new GameLog();
    Timer mTimer;
    private Player[] players = new Player[10];
    private int curNight, curDay;
//    public static int DON = 20, SHERIFF = 21, MAFIA = 22, CITIZEN = 23;
//    public static int ZERO_NIGHT = 10, DAY = 11, SHOOT = 12, DON_CHECKING = 13, SHERIFF_CHECKING = 14, SPEECH = 15, VOTE = 16, FIGHT = 17;
//    public static int IN_GAME = 30, KILLED = 31, VOTED_OUT = 32, MUTED = 33, LEFT = 34, DISQUALIFIED = 35;
    //TODO backup(); restoreLastState(); restoreState();
    //TODO game log

    public MafiaCore() {
        phase = GamePhase.ZERO_NIGHT;
        curDay = curNight = 0;
    }

    public void initPlayer(int number, boolean sex, String name, Role role) {
        players[number] = new Player(number, name, sex, role);
    }
    public void initPlayer(int number, boolean sex, Role role) {
        players[number] = new Player(number, sex, role);
    }
    public void setPhase(GamePhase newPhase) {
        phase = newPhase;
    }

    public int startDay() {
        setPhase(GamePhase.DAY);
        return ++curDay;
    }

    public boolean nextTurn(int speechDuration) {
        //TODO проверить состояние, отклонить или принять следующий ход
        curPlayer = nextPlayer;
        calcNextPlayer();
        setPhase(GamePhase.SPEECH);
        mTimer = new Timer();
        TurnTimerTask thisTurn = new TurnTimerTask();
        players[curPlayer].speech(speechDuration);
        mTimer.schedule(thisTurn, speechDuration*1000);
        return true;
    }

    private void calcNextPlayer() {
        //TODO get next alive not muted player
        nextPlayer++;
    }

    public boolean nominate(int from, int to) {
        gameLog.addNomination(curDay, from, to);
        System.out.println("Игрок " + to + " принят на голосование.");
        return true;
    }

    public boolean nominate(int to) {
        if (phase == GamePhase.SPEECH) //during speech
            if (nominatedBy(curDay, to)==-1) //if not nominated already
                if (getNominations(curDay, curPlayer)==-1)
                    return nominate(curPlayer, to);
        return false;
    }

    public void pass() {
        setPhase(GamePhase.DAY);
        mTimer.cancel();
        mTimer = null;
        curPlayer = -1;
    }

    public boolean denominate(int from, int to) {
        if (getPhase()==GamePhase.SPEECH) {
            return true;
        } else return false;
    }

    public GamePhase getPhase() {
        return phase;
    }

    public int addFault(int player) {
        return ++(players[player].fault);
    }

    public int getDay() {
        return curDay;
    }

    public int getNominations(int turn, int player) {
        //TODO if player == -1 || turn == -1
        return gameLog.getNominations(turn, player);
    }

    public int nominatedBy(int turn, int player) {

        return -1;
    }

    public void shot(int player) {
        players[player].state = PlayerState.KILLED;
    }

    class TurnTimerTask extends TimerTask {

        @Override
        public void run() {
            setPhase(GamePhase.DAY);
            System.out.println("Спасибо, игрок №" + (curPlayer));
            mTimer.cancel();
            mTimer = null;
        }
    }

    class GameLog {
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

}
