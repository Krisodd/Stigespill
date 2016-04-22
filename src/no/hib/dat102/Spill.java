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
		start();
	}
	
	
	public Spiller flyttBrikke(Spiller spiller) {
		System.out.println("Trykk enter for å trille terningen.");
		s.nextLine();
		int trill=dice.trill();
		System.out.println("Du trillet: " + trill);
		int currentPlace = spiller.getPlassering();
		if(currentPlace+trill>100) {
			System.out.println("Du trillet over 100!");
			int temp_index = ((currentPlace+trill)-100);
			System.out.println("Du må gå tilbake " + temp_index + " steg fra mål.");
			temp_index = 100-temp_index;
			System.out.println(spiller.getNavn() + " rykket tilbake til rute " + temp_index);
			spiller.setPlassering(temp_index);
		} else if((currentPlace+trill)==100) {
			System.out.println("Royal Kebab Pizza til" + spiller.getNavn() + " her!");
			return spiller;
		}
		System.out.println(spiller.getNavn() + " flytter seg fra rute " + currentPlace + ", til rute " + (currentPlace+trill));
		currentPlace += trill;
		if (currentPlace<100 &&brett.isSpecialTile(currentPlace)) {
			brett.specialMove(currentPlace);
		}
		spiller.setPlassering(currentPlace);
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
			flyttBrikke(spillere[currentTur]);
			nesteSinTur();
		}
		return -1;
	}

}
