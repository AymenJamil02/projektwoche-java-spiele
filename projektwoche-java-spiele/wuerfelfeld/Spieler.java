/**
 * Spieler mit Name und eigenem Spielfeld.
 */
public class Spieler {

    private String name;
    private Spielfeld feld;

    public Spieler(String name, int n) {
        this.name = name;
        this.feld = new Spielfeld(n);
    }

    public String getName() { return name; }
    public Spielfeld getFeld() { return feld; }
}
