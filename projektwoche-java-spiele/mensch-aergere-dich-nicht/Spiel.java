package menschaergeredichnicht;

import java.util.Random;
import java.util.Scanner;

public class Spiel {

	private static Spieler[] spieler;
	private static Spielfeld sf;
	private static Scanner sc;
	private static Random rd;
	
	// wie funktioniert die spiellogik?
	// do - while - Schleife über die Spieler
	// Spieler macht Zug, nach Zug Test auf gewonnen des Spielers
	
	
	public static void main(String[] args) {
		
		// Initialisiere die Hilfsklassen
		sc = new Scanner(System.in);
		rd = new Random();

		// Initialisiere das Array der Spieler
		int anzahlSpieler = frageAnzahlSpieler();
		sf = new Spielfeld(anzahlSpieler);
		spieler = new Spieler[anzahlSpieler];		
		for (int i=0; i < anzahlSpieler; i++)
			spieler[i] = new Spieler(frageName(i+1), i, sf);
		
			
		int aktuellerSpielerNr = -1; // Nummer des aktuellen Spielers
		Spieler aktuellerSpieler; // aktueller Spieler
		
		do {
			aktuellerSpielerNr = ++aktuellerSpielerNr % anzahlSpieler;
			aktuellerSpieler = spieler[aktuellerSpielerNr];
			System.out.println("Das Spielfeld sieht so aus:");
			for (int i=0; i<spieler.length; i++) System.out.println(spieler[i]);
			System.out.println(sf);
			System.out.println(aktuellerSpieler.getName() + " ist jetzt an der Reihe.");
			
			int figNr = frageFigur(aktuellerSpieler) - 1; // -1, weil die technische Figurnummer eins kleiner ist als die angezeigte Figurnummer
			Figur aktuelleFigur = aktuellerSpieler.getFigur(figNr);
			if (aktuelleFigur.istZuHause) {
				System.out.println("Die gewählte Figur ist bereits zu Hause. Der nächste Spieler ist an der Reihe.");
			} else if (aktuelleFigur.istImSpiel) {
				int augen = rd.nextInt(6)+1;
				System.out.println("Es wurde eine " + augen + "geworfen.");
				aktuelleFigur.ziehe(augen);
			} else { // Figur muss noch herausgewürfelt werden.
				if (rauswuerfeln()) aktuelleFigur.geht_raus();
			}
		} while (!aktuellerSpieler.hatBeendet());
		
		System.out.println(aktuellerSpieler.getName() + "hat gewonnen - herzlichen Glückwunsch!");	
	}
	
	private static int frageFigur(Spieler sp) {
		System.out.println(sp.getName() + ", welche deiner Figuren willst du bewegen?\nGib eine Zahl von 1 - 3 ein.");
		int eingabe = sc.nextInt();
		while(!(1 <= eingabe && eingabe <=3 )) {
			System.out.println("Eingabe falsch. Gib eine Zahl von 1 - 3 ein.");
			eingabe = sc.nextInt();
		}
		return eingabe;
	}
	
	private static String frageName(int n) {
		System.out.print("Bitte gib den Namen für Spieler Nr. " + n + " ein: ");
		return sc.next();
	}
	
	// Die Methode begrüßt die Programmnutzer, fragt nach der Anzahl der Spieler
	// und gibt eine natürliche Zahl zwischen 2 und 6 (einschließlich) zurück
	private static int frageAnzahlSpieler() {
		String ausgabe1 = "Herzlich willkommen zu 'Mensch ärgere dich nicht!'";
		String ausgabe2 = "Es können 2 - 6 Menschen mitspielen.";
		String ausgabe3 = "Wie viele Personen wollen teilnehmen? ";
		
		int eingabe;
		System.out.println(ausgabe1);
		do {
			System.out.println(ausgabe2);
			System.out.print(ausgabe3);
			eingabe = sc.nextInt();
		} while (eingabe < 2 || eingabe > 6);
		return eingabe;
	}
	
	// Der Spieler darf bis zu 3 mal eine Augenzahl raten.
	// Der Spieler wird solange aufgefordert, eine Zahl einzugeben, bis eine gültige Zahl eingegeben wird.
	// Die geratene Zahl wird mit der gewuerfelten Zufallszahl verglichen.
	// Falls beide gleich sind, wird ihr Wert zurück gegeben.
	// Rät der Spieler 3 mal falsch, wird 0 zurückgegeben.
	private static boolean rauswuerfeln() {
		int geraten;
		int gewuerfelt;
		int versuch = 1;
		
		while (versuch < 4) {
			System.out.print(versuch + ". Versuch: ");
			do {
				System.out.print("Bitte rate eine Zahl zwischen 1 und 6:");
				geraten = sc.nextInt();
			} while (! (1 <= geraten && geraten <= 6));
			gewuerfelt = rd.nextInt(6)+1;
			if (geraten == gewuerfelt) {
				System.out.println("Richtig geraten!");
				return true;
			} else {
				versuch++;
				System.out.println("Leider falsch geraten - du hast eine "+gewuerfelt+" gewürfelt.");
			}
		}
		System.out.println("3 Versuche sind verbraucht - diesmal hat es leider nicht geklappt.");
		return false;
	}
}
