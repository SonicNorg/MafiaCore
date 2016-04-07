import org.jetbrains.annotations.NotNull;

/**
 * Class for Mafia player
 * Created by Norg on 07.04.2016.
 */
public final class Player {
    private final boolean sex; //false is female
    private final int number;
    private final PlayerState state;
    private final String name;
    private final Role role;
    private final int fault;

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

    public Player withNewState(@NotNull PlayerState state) {
        return new Player(number, name, sex, role, state, fault);
    }

    public Player addFault() {
        return new Player(number, name, sex, role, state, fault+1);
    }

    public int faults() {
        return fault;
    }

    public PlayerState state() {
        return state;
    }
}
