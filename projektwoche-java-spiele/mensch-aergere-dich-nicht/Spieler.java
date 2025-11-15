package menschaergeredichnicht;

public class Spieler {

	private String name;
	private int meineNummer;  // Spielernummer im Bereich 0 ... 5, wird in Buchstaben A ... F umgesetzt
	private Figur[] figuren;
	private Spielfeld sf;
	

	Spieler(String n, int m, Spielfeld sf){
		name = n;
		meineNummer = m;
		this.sf = sf;
		
		figuren = new Figur[3];
		for (int i=1; i<=3; i++)
			figuren[i-1] = new Figur(this, i);
	}
	
	public String getName() {
		return name;
	}
	
	public Spielfeld getSpielfeld() {
		return sf;
	}
	
	
	// liefert eine Zeile, die den Namen, die außerhalb des Spielfelds befindlichen Figuren 
	// und die bereits heimgekehrten Figuren auflistet.
	public String toString() {
		String res = name + "\t Nicht im Spiel: ";
		for (int i = 0; i < 3; i++ ) 
			if (!figuren[i].istImSpiel) res += (figuren[i] + ", ");
		res +="\t Runde bereits beendet: ";
		for (int i = 0; i < 3; i++ ) 
			if (figuren[i].istZuHause) res += (figuren[i] + ", ");
		res +="\n";
		return res;
	}
	
	public Figur getFigur(int i) {
		return figuren[i];
	}
	
	public int getNr() {
		return meineNummer;
	}
	
	public boolean hatBeendet() {
		return figuren[0].istZuHause && figuren[1].istZuHause && figuren[2].istZuHause;
	}
}
