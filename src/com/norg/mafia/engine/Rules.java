package com.norg.mafia.engine;

/**
 * Controls rules
 * Created by pavel.krizhanovskiy on 08.04.2016.
 */
public interface Rules {
    GamePhase phase();
    boolean nextPhase();
}
