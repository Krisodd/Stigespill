package no.hib.dat102;
import java.util.Scanner;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity(name="spill")
@Table(schema="stigespill")
public class Spill {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	transient int currentTur;
	transient Spiller[] spillere;
	transient Scanner s = new Scanner(System.in);
	transient Terning dice = new Terning();
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="brett")
	Brett brett;
	transient DB_logic db = new DB_logic();
	transient Logg logg;
	transient boolean uses_db;
	@
	Spiller spiller;
	
	boolean ferdig = false;
	public Integer getID() {
		return id;
	}
	public Spill(Spiller[] spillere, boolean online){
		uses_db=online;
		if(uses_db){
		brett = new Brett();
		}
		currentTur=0;
		this.spillere = spillere;
		System.out.println("Dere startet et nytt spill.");

		int vinner = start();
		System.out.println("Spillet er ferdig, vinneren er " + spillere[vinner].getNavn());
	}
	
	
	public Spiller flyttBrikke(Spiller spiller) { // Usually returns null, returns the Spiller object if the current player wins the game
		boolean tilbake = false;
		int six_counter = 0;
		int trill;
		do {
		System.out.println("Trykk enter for å trille terningen.");
		s.nextLine();
		trill=dice.trill();
		if(spiller.isStuck()&&trill!=6) {
			System.out.println("Du må trille 6 for å fortsette spillet! :c");
			logg = new Logg(spiller, trill, id);
			logg.log();
			return null;
		}
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
			logg = new Logg(spiller, trill, id);
			logg.log();
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
		if(trill == 6) {
			System.out.println("Du trilte 6! Trill igjen :D");
			six_counter++;
		}
		} while(trill==6&&six_counter<4); // Roll the die again if you get a 6
		if(six_counter==3) {
			System.out.println("Du trillet 6 tre ganger! Gå tilbake til start. Du må trille 6 igjen for å starte");
			spiller.setPlassering(1);
			spiller.setStuck(true);
		}
		logg = new Logg(spiller, trill, getID());
		logg.log();
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
