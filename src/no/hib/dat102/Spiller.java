package no.hib.dat102;

public class Spiller {
	
	int plassering;
	String spillerNavn;
	
	
	public Spiller(){
		plassering = 0;
		
	}
	
	public void setNavn(String navn){
		spillerNavn = navn;
	}
	
	public String getNavn(){
		return spillerNavn;
	}
	
	public void setPlassering(int plassering){
		this.plassering = plassering;
	}
	
	public int getPlassering(){
		return plassering;
	}
}