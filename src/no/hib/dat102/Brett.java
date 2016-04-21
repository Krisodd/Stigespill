package no.hib.dat102;

public class Brett {
	final int ANTALL_RUTER = 100;
	final int ANTALL_SLANGER = 5;
	final int ANTALL_STIGER = 5;
	
	// The mappings must be sorted by the element index (the first element in each sub-array)
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
	
	
	Rute[] ruter = new Rute[ANTALL_RUTER];
	Stige[] stiger = new Stige[ANTALL_STIGER];
	Slange[] slanger = new Slange[ANTALL_SLANGER];
	
	public Brett() { 
		int antallStiger = 0;
		int antallSlanger = 0;
		for(int i=1;i<=ANTALL_RUTER;i++) { // populate board
			
			if(STIGE_MAPPING[antallStiger][0]==i) {
				ruter[i] = new Stige(i, STIGE_MAPPING[antallStiger][1]);
				antallStiger++;
			} else if (SLANGE_MAPPING[antallSlanger][0]==i) {
				ruter[i] = new Slange(i, SLANGE_MAPPING[antallSlanger][1]);
				antallSlanger++;
			} else {
				ruter[i] = new Rute(i);
			}
		}
	}
}
