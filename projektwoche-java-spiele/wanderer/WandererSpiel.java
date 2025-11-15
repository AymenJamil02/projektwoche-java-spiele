import java.util.Scanner;

/**
 * Wanderer / Bruecke und Taschenlampe (Projektwoche Mai 2025).
 * Ein- und Ausgaben finden nur in dieser Klasse statt.
 */
public class WandererSpiel {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean nochmal = true;
        while (nochmal) {
            Bruecke b = new Bruecke();

            while (!b.istGeloest() && b.getZeit() <= 60) {
                System.out.println("Links: " + b.seite(true) + " | Rechts: " + b.seite(false)
                        + " | Zeit: " + b.getZeit() + " Minuten");
                if (b.lampeLinks()) {
                    System.out.println("Zwei Wanderer gehen nach rechts.");
                    char w1 = leseWanderer(b, true);
                    char w2 = leseWanderer(b, true);
                    while (w2 == w1) {
                        System.out.println("Bitte einen anderen Wanderer waehlen.");
                        w2 = leseWanderer(b, true);
                    }
                    b.gehe(w1, w2);
                } else {
                    System.out.println("Ein Wanderer geht mit der Lampe zurueck nach links.");
                    char w = leseWanderer(b, false);
                    b.gehe(w, ' ');
                }
            }

            if (b.istGeloest()) {
                System.out.println("Glueckwunsch! Geloest in " + b.getZeit() + " Minuten.");
                nochmal = frageJaNein("Nochmal spielen? (j/n)");
            } else {
                System.out.println("Die Lampe ist aus (" + b.getZeit() + " Minuten). Leider nicht geloest.");
                nochmal = frageJaNein("Erneut versuchen? (j/n)");
            }
        }
    }

    /** Liest einen Buchstaben A-D ein, der auf der angegebenen Seite steht. */
    private static char leseWanderer(Bruecke b, boolean links) {
        while (true) {
            System.out.println("Wanderer (A-D):");
            String eingabe = sc.next();
            if (eingabe.length() == 1) {
                char c = eingabe.charAt(0);
                if (c >= 'A' && c <= 'D' && b.istLinks(c) == links) return c;
            }
            System.out.println("Ungueltige Eingabe.");
        }
    }

    private static boolean frageJaNein(String frage) {
        while (true) {
            System.out.println(frage);
            String e = sc.next();
            if (e.equals("j")) return true;
            if (e.equals("n")) return false;
        }
    }
}
