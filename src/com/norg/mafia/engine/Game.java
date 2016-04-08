package com.norg.mafia.engine;

import java.util.LinkedList;
import java.util.List;

/**
 * Class represents current game
 * Created by pavel.krizhanovskiy on 08.04.2016.
 */
public final class Game {

    private final GamePhase phase;
    private final List<Player> players;

    public Game(GamePhase phase, List<Player> players) {
        this.phase = phase;
        this.players = new LinkedList<Player>() {
            {
                addAll(players);
            }
        };
    }

    public Game withNewPlayer(Player player) {
        return new Game(
                phase, new LinkedList<Player>() {
            {
                addAll(players);
                add(player);
            }
        }
        );
    }

    public Game withPlayer(int num, Player player) {
        return new Game(
                phase, new LinkedList<Player>() {
            {
                addAll(players);
                set(num, player);
            }}
        );
    }

    public Game withPhase(GamePhase phase) {
        return new Game(phase, players);
    }
}
