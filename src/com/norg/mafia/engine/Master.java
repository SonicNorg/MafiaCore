package com.norg.mafia.engine;

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
}
