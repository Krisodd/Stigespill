package no.hib.dat102;

public class Brett {
	final int ANTALL_RUTER = 101; // Add one to keep Arrays happy

	Rute[] ruter = new Rute[ANTALL_RUTER];
	
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
				System.out.println("Added stige: " + ruter[i].getRuteIndex());
			} else if (antallSlanger<SLANGE_MAPPING.length&&SLANGE_MAPPING[antallSlanger][0]==i) {
				ruter[i] = new Slange(i, SLANGE_MAPPING[antallSlanger][1]);
				antallSlanger++;
			} else {
				ruter[i] = new Rute(i);
			}
		}
	}
}
