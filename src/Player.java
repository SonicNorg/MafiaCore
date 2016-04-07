/**
 * Created by Norg on 07.04.2016.
 */
public class Player {
    boolean sex; //false is female
    int number;
    PlayerState state;
    String name;
    Role role;
    int fault;

    void speech() {
        speech(60);
    }

    public void speech(int seconds) {
        System.out.println("Слово получает игрок №" + (number) + " - " + name);
    }

    public Player (int nmbr, String nm, boolean sx, Role rl, PlayerState stt, int flt) {
        this.number = nmbr;
        this.name = nm;
        this.sex = sx;
        this.role = rl;
        this.state = stt;
        this.fault = flt;
    }

    public Player (int nmbr, String nm, boolean sx, Role rl) {
        this(nmbr, nm, sx, rl, PlayerState.IN_GAME, 0);
    }

    public Player (int nmbr, boolean sx, Role rl) {
        this(nmbr, "Игрок", sx, rl, PlayerState.IN_GAME, 0);
    }
}
