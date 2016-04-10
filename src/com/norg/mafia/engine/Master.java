package com.norg.mafia.engine;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class represents the game master
 * Created by pavel.krizhanovskiy on 08.04.2016.
 */
public final class Master {
    private final Game game;
    private final Log log;

    public Master(Game game, Log log) {
        this.game = game;
        this.log = log;
    }

    public Master nextTurn(int speechDuration) { // TODO: 08.04.2016 move to classes Master and Game
        //TODO check phase, getNextPhase, split for 2 methods in 2 classes

//        setPhase(GamePhase.SPEECH);
//        mTimer = new Timer();
//        SpeechTimerTask thisTurn = new SpeechTimerTask();
//        players[curPlayer].speech(speechDuration);
//        mTimer.schedule(thisTurn, speechDuration*1000);
        return this;
    }

    public void endSpeech() {
//        setPhase(GamePhase.DAY); // TODO: 08.04.2016 move setPhase() to class Game and set not only DAY phase
//        System.out.println("Спасибо, игрок №" + (curPlayer));
//        mTimer.cancel();
//        mTimer = null;
//        curPlayer = -1;
    }

    class SpeechTimerTask extends TimerTask {

        @Override
        public void run() {
            endSpeech();
        }
    }
}
