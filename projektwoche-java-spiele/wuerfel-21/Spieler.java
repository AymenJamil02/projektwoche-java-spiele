/**
 * Spieler mit Name, Gesamtpunktestand und Kennzeichen, ob er vom Computer gesteuert wird.
 */
public class Spieler {

    private String name;
    private int gesamt;
    private boolean computer;

    public Spieler(String name, boolean computer) {
        this.name = name;
        this.computer = computer;
    }

    public String getName() { return name; }
    public int getGesamt() { return gesamt; }
    public boolean istComputer() { return computer; }

    public void addPunkte(int p) {
        gesamt += p;
    }

    public String toString() {
        return name + " " + gesamt;
    }
}
