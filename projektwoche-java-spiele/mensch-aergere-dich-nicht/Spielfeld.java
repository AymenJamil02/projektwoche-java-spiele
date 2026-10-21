package menschaergeredichnicht;

public class Spielfeld {

	private Figur[] feld; // null oder figur
	
	public Spielfeld(int n){
		feld = new Figur[15*n];
		for (int i=0; i < feld.length; i++)
			feld[i] = null;
	}
	
	/* public int getPos(int pos) {
		return feld[pos];
	} */

	// setzt fig auf die dort gespeicherte Position und liefert eine ggf. dort befindliche Figur zurück
	public void setzeAufPos(Figur fig) {
		Figur alt = feld[fig.position];
		if (alt != null) alt.wirdGeschlagen();
		feld[fig.position] = fig;
	}
	
	public void nimmVomFeld(Figur fig) {
		feld[fig.position] = null;
	}
	
	public int anzahlFelder() {
		return feld.length;
	}
	
	public String toString() {
		String res = "";
		
		for (int i=0; i < feld.length; i++) {
			res += (feld[i]==null?" _ ":feld[i].toString());
		}
		
		res += "\n";
		return res;
	}
	
	

	
}
