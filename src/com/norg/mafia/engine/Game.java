package com.norg.mafia.engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class represents current game
 * Created by pavel.krizhanovskiy on 08.04.2016.
 */
public final class Game {

    private final GamePhase phase;
    private final List<Player> players;
    private final Player player;

    public Game(GamePhase phase, List<Player> players, int player) {
        this.phase = phase;
        this.player = players.get(player);
        this.players = new LinkedList<Player>() {
            {
                addAll(players);
            }
        };
    }

    public Game(List<Player> players) {
        this(GamePhase.ZERO_NIGHT, players, 0);
    }

    public Game withNewPlayer(Player player) {
        return new Game(
                phase, new LinkedList<Player>() {{
                addAll(players);
                add(player);
            }
        }, this.player.number()
        );
    }

    public Game withPlayer(int num, Player player) {
        return new Game(
                phase, new LinkedList<Player>() {{
                addAll(players);
                set(num, player);
            }}, this.player.number()
        );
    }

    public Game withPlayer(int player) {
        return new Game(phase, players, player);
    }

    public Game withoutPlayer(int player) {
        return new Game(
                phase, new LinkedList<Player>() {{
                addAll(players);
                remove(player);
            }}, this.player.number()
        );
    }

    public Game withPhase(GamePhase phase) {
        return new Game(phase, players, player.number());
    }

    public GamePhase phase() {
        return phase;
    }

    public Player currentPlayer() {
        return player;
    }

    public Player nextPlayer() {
        return getNextPlayer();
    }

    private Player getNextPlayer() {
        return null;
    }
}
