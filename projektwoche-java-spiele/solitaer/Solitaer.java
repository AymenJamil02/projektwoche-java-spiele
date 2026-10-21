import java.util.Scanner;

/**
 * Solitaer auf einem quadratischen Feld (Standard 5 x 5).
 * Ein Pin (#) springt ueber einen Nachbarn in ein leeres Feld (O),
 * der uebersprungene Pin wird entfernt.
 */
public class Solitaer {

    public static final int GROESSE = 5;   // Spielfeldgroesse bleibt variabel

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Spielfeld feld = new Spielfeld(GROESSE);
        feld.starteSpiel();

        while (feld.zugMoeglich()) {
            feld.ausgabeSpielfeld();
            feld.zug(sc);
        }

        feld.ausgabeSpielfeld();
        int rest = feld.anzahlPins();
        if (rest == 1) System.out.println("Gewonnen! Nur noch ein Pin uebrig.");
        else System.out.println("Kein Zug mehr moeglich. Uebrige Pins: " + rest);
        sc.close();
    }
}
