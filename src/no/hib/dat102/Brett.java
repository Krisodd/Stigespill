package no.hib.dat102;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "stigespill")
public class Brett {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	final int ANTALL_RUTER = 101; // Add one to keep Arrays happy
	Rute[] ruter = new Rute[ANTALL_RUTER];
	int[] plassering = new int[4];
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Special tiles mapping																							//
	//																													//
	// The mappings must be sorted by the element index (the first element in each sub-array)							//
	// Formatted as a pair of integers, where [0] represents the tile index and [1] represents the destination index	//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	int[][] STIGE_MAPPING = { 
			{14, 23}, 
			{24, 28}, 
			{36, 47}, 
			{69, 78}, 
			{83, 91}};
	int[][] SLANGE_MAPPING = {
			{32, 23}, 
			{49, 28}, 
			{77, 47}, 
			{88, 78}, 
			{99, 91}};
	
	
	public Brett() { 
		int antallStiger = 0;
		int antallSlanger = 0;
		for(int i=1;i<ANTALL_RUTER;i++) { // populate board with tiles
			if(antallStiger<STIGE_MAPPING.length&&STIGE_MAPPING[antallStiger][0]==i) {
				ruter[i] = new Stige(i, STIGE_MAPPING[antallStiger][1]);
				antallStiger++;
				System.out.println("Added stige: " + ruter[i].getRuteIndex() + " -> " + ruter[i].getDestinationIndex());
			} else if (antallSlanger<SLANGE_MAPPING.length&&SLANGE_MAPPING[antallSlanger][0]==i) {
				ruter[i] = new Slange(i, SLANGE_MAPPING[antallSlanger][1]);
				antallSlanger++;
				System.out.println("Added slange: " + ruter[i].getRuteIndex() + " -> " + ruter[i].getDestinationIndex());
			} else {
				ruter[i] = new Rute(i);
			}
		}
	}
	public Rute[] getRuter() {
		return ruter;
	}
	public Rute getRute(int index) {
		return ruter[index];
	}
	public int specialMove(int index) {
		return ruter[index].move();
	}
	public int tileDestination(int index) { // If true, returns an integer with the destination tile of the special tile
		return ruter[index].getDestinationIndex();
	}
	public boolean isSpecialTile(int index) {
		if (index>100){
			return false;
		}
		return ruter[index].isSpecialTile();
	}
	public void goTo(Spiller spiller, int index){
		spiller.setPlassering(index);
		if(ruter[index] instanceof Stige) {
			ruter[index].move();
		}
	}
	
}
