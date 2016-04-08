package com.norg.mafia.engine;

import java.util.*;

/**
 * Class for main engine of Mafia board game
 * Created by Norg on 13.02.2016.
 */
public class MafiaCore {
    private int curPlayer=-1, nextPlayer=0;
    GamePhase phase;
    private InGameLog gameLog = new InGameLog();
    Timer mTimer;
    private Player[] players = new Player[10];
    private int curNight, curDay;
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

    public boolean nextTurn(int speechDuration) { // TODO: 08.04.2016 move to classes Master and Game
        //TODO check phase, getNextPhase, split for 2 methods in 2 classes
        if (getPhase() != GamePhase.DAY && getPhase() != GamePhase.NIGHT) return false;
        curPlayer = nextPlayer;
        calcNextPlayer();
        setPhase(GamePhase.SPEECH);
        mTimer = new Timer();
        SpeechTimerTask thisTurn = new SpeechTimerTask();
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

    public boolean denominate(int from, int to) {
        //TODO check whether correct
        if (getPhase()==GamePhase.SPEECH) {
            return true;
        } else return false;
    }

    public GamePhase getPhase() {
        return phase;
    } // TODO: 08.04.2016 move to class Game

    public int addFault(int player) {
        return (players[player] = players[player].addFault()).faults();
    } // TODO: 08.04.2016 move to class Master

    public int getDay() {
        return curDay;
    } // TODO: 08.04.2016 move to class Game

    public int getNominations(int turn, int player) {
        //TODO if player == -1 || turn == -1
        return gameLog.getNominations(turn, player);
    }

    public int nominatedBy(int turn, int player) {

        return -1;
    }

    public void shot(int player) {
        players[player] = players[player].withNewState(PlayerState.KILLED);
    }

    class SpeechTimerTask extends TimerTask {

        @Override
        public void run() {
            endSpeech();
        }
    }

    public void endSpeech() { //TODO move to class Master
        setPhase(GamePhase.DAY); // TODO: 08.04.2016 move setPhase() to class Game and set not only DAY phase
        System.out.println("Спасибо, игрок №" + (curPlayer));
        mTimer.cancel();
        mTimer = null;
        curPlayer = -1;
    }
}
