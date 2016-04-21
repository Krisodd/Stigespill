package no.hib.dat102;

public class Spiller {
	
	int plassering;
	String brikkefarge;
	Brikke brikke;
	
	public Spiller(int spillernr){
		plassering = 0;
		brikke = new Brikke(spillernr);
		
	}
	
	public Brikke getBrikke(){
		return brikke;
	}
}