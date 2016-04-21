package no.hib.dat102;

public class Spill {
	int currentTur;
	Spiller[] spillere;
	
	
	public Spill(Spiller[] spillere){
		Brett brett = new Brett();
		currentTur=0;
		this.spillere = spillere;
	}
	public void flyttBrikke(Spiller spiller) {
		
	}
	public int hvemSinTur(){
		return currentTur;
	}
	public int nesteSinTur() {
		if(currentTur>=spillere.length){
			currentTur=0;
		} else {
			currentTur++;
		}
		return currentTur;
	}

}
