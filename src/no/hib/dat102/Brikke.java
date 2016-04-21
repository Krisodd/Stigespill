package no.hib.dat102;

public class Brikke {
	
	String brikke;
	
	public Brikke(int spiller){
		switch(spiller){
		case 1:
			brikke = "Rød";
			break;
		case 2:
			brikke = "Blå";
			break;
		case 3:
			brikke = "Gul";
			break;
		case 4:
			brikke = "Grønn";
			break;
		default:
			brikke = null;
		}
		
	}
	
	public String getFarge(){
		return brikke;
	}
	

	
	
}
