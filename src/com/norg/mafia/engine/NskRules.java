package com.norg.mafia.engine;

import java.util.List;

/**
 * Created by pavel.krizhanovskiy on 08.04.2016.
 */
public final class NskRules implements Rules {

    @Override
    public int phaseDuration(GamePhase phase) {
        int dur; //duration in seconds
        switch (phase) {
            case ZERO_NIGHT:
                dur = -1;
                break;
            case DAY:
                dur = -1;
                break;
            case SHOOT:
                dur = -1;
                break;
            case DON_CHECKING:
                dur = 10;
                break;
            case SHERIFF_CHECKING:
                dur = 10;
                break;
            case SPEECH:
                dur = 60;
                break;
            case LAST_SPEECH:
                dur = 60;
                break;
            case VOTE:
                dur = -1;
                break;
            case FIGHT:
                dur = -1;
                break;
            case NIGHT:
                dur = -1;
                break;
            case FIHGT_SPEECH:
                dur = 60;
                break;
            case FIGHT_LAST_SPEECH:
                dur = 0;
                break;
            default:
                throw new IllegalArgumentException("Phase not found!");
        }
        return dur;
    }

}
