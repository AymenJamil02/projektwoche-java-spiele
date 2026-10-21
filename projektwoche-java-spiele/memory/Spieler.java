/**
 * Ein Memory-Spieler mit Name und Punktestand.
 */
public class Spieler {

    private String name;
    private int punkte;

    public Spieler(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPunkte() {
        return punkte;
    }

    public void punkt() {
        punkte++;
    }

    public String toString() {
        return name + ": " + punkte + " Punkte";
    }
}
