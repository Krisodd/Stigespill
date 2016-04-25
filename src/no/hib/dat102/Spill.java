package no.hib.dat102;
import java.util.Scanner;
public class Spill {
	int currentTur;
	Spiller[] spillere;
	Scanner s = new Scanner(System.in);
	Terning dice = new Terning();
	Brett brett;
	boolean ferdig = false;
	
	public Spill(Spiller[] spillere){
		brett = new Brett();
		currentTur=0;
		this.spillere = spillere;
		System.out.println("Dere startet et nytt spill.");
		int vinner = start();
		System.out.println("Spillet er ferdig, vinneren er " + spillere[vinner].getNavn());
	}
	
	
	public Spiller flyttBrikke(Spiller spiller) {
		boolean tilbake = false;
		System.out.println("Trykk enter for å trille terningen.");
		s.nextLine();
		int trill=dice.trill();
		System.out.println(spiller.getNavn() + " trillet: " + trill);
		int currentPlace = spiller.getPlassering();
		if(currentPlace+trill>100) {
			System.out.println("Du trillet over 100!");
			int temp_index = ((currentPlace+trill)-100);
			System.out.println("Du må gå tilbake " + temp_index + " steg fra mål.");
			temp_index = 100-temp_index;
			System.out.println(spiller.getNavn() + " rykket tilbake til rute " + temp_index);
			spiller.setPlassering(temp_index);
			tilbake = true;
		} else if((currentPlace+trill)==100) {
			System.out.println("Royal Kebab Pizza til " + spiller.getNavn() + " her!");
			return spiller;
		}
		if(!tilbake){
		System.out.println(spiller.getNavn() + " flytter seg fra rute " + currentPlace + ", til rute " + (currentPlace+trill));
		currentPlace += trill;
		}
		if (currentPlace<100 &&brett.isSpecialTile(currentPlace)) {
			spiller.setPlassering(brett.specialMove(currentPlace));
		} else if(!tilbake) {
		spiller.setPlassering(currentPlace);
		}
		System.out.println();
		
		return null;
		
	}
	
	
	
	public int hvemSinTur(){
		System.out.println(spillere[currentTur].getNavn() +" sin tur.");
		return currentTur;
	}
	public int nesteSinTur() {
		if(currentTur>=spillere.length-1){
			currentTur=0;
		} else {
			currentTur++;
		}
		System.out.println(spillere[currentTur].getNavn() +" sin tur.");
		return currentTur;
	}
	
	public int start() { // returns the winner!
		hvemSinTur();
		while(!ferdig) {
			if(flyttBrikke(spillere[currentTur])!=null) {
				return currentTur;
			}
			nesteSinTur();
		}
		return -1;
	}

}
