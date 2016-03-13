import java.util.*;

/**
 * Created by Norg on 13.02.2016.
 */
public class MafiaCore {
    private int phase, curPlayer=-1, nextPlayer=0;
    private GameLog gameLog = new GameLog();
    Timer mTimer;
    private Player[] players;
    private int curNight, curDay;
    public static int DON = 20, SHERIFF = 21, MAFIA = 22, CITIZEN = 23;
    public static int ZERO_NIGHT = 10, DAY = 11, SHOOT = 12, DON_CHECKING = 13, SHERIFF_CHECKING = 14, SPEECH = 15, VOTE = 16, FIGHT = 17;
    public static int IN_GAME = 30, KILLED = 31, VOTED_OUT = 32, MUTED = 33, LEFT = 34, DISQUALIFIED = 35;
    //TODO backup(); restoreLastState(); restoreState();
    //TODO game log

    public MafiaCore() {
        phase = ZERO_NIGHT;
        curDay = curNight = 0;
        players = new Player[10];
        for (int i = 0; i<10; i++)
            players[i] = new Player();
    }

    public void initPlayer(int number, boolean sex, String name, int role) {
        players[number].sex = sex;
        if (!name.isEmpty()) players[number].name = name;
        players[number].role = role;
        players[number].number = number;
    }

    public void setPhase(int newPhase) {
        phase = newPhase;
    }

    public int startDay() {
        setPhase(MafiaCore.DAY);
        return ++curDay;
    }

    public boolean nextTurn(int speechDuration) {
        //TODO проверить состояние, отклонить или принять следующий ход
        curPlayer = nextPlayer;
        calcNextPlayer();
        setPhase(SPEECH);
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
        System.err.println("Игрок " + to + " принят на голосование.");
        return true;
    }

    public boolean nominate(int to) {
        if (phase == SPEECH) //during speech
            if (nominatedBy(curDay, to)==-1) //if not nominated already
                if (getNominations(curDay, curPlayer)==-1)
                    return nominate(curPlayer, to);
        return false;
    }

    public void pass() {
        setPhase(DAY);
        mTimer.cancel();
        mTimer = null;
        curPlayer = -1;
    }

    public boolean denominate(int from, int to) {
        if (getPhase()==SPEECH) {
            return true;
        } else return false;
    }

    public int getPhase() {
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
        players[player].state = KILLED;
    }

    class Player {
        boolean sex = true; //false is female
        int number;
        int state = IN_GAME;
        String name = "Игрок";
        int role;
        int fault = 0;

        void speech() {
            speech(60);
        }

        void speech(int seconds) {
            System.err.println("Слово получает игрок №" + (number) + " - " + name);
        }
    }

    class TurnTimerTask extends TimerTask {

        @Override
        public void run() {
            setPhase(DAY);
            System.err.println("Спасибо, игрок №" + (curPlayer));
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
