package no.hib.dat102;

import java.util.Scanner;

public class Klient {
	boolean online = false;
	boolean valid_input = false;
	Scanner tast = new Scanner(System.in);
	int brettid;
	public void Start(){
		
		System.out.println("Hei og velkommen til stigespill");
		
		do{
			System.out.println("Vil du koble spillet opp mot vår database? Y/N");
			String db_yn = tast.nextLine();
			if(db_yn.compareToIgnoreCase("y")==0) {
				online = true;
				valid_input=true;
				System.out.print("Tast inn ID på brettet du vil laste inn: " );
				brettid = tast.nextInt();
			} else if(db_yn.compareToIgnoreCase("n")==0) {
				online = false;
				valid_input=true;
			} 
		} while (!valid_input);
		
		
		
			
		System.out.println("Hvor mange spillere er dere?(2-4)");
		int antall = tast.nextInt();
		Spiller[] spillere = new Spiller[antall];
		
		boolean valg = false;
		while(!valg){
			if(antall >= 2 && antall <= 4){
				spillere = new Spiller[antall];
				for(int i = 0; i<antall; i++){
					spillere[i] = new Spiller();
				}
				valg = true;
			}else{
				System.out.println("Ugylid valg av antall spillere, vennligst pr�v igjen");
				antall = tast.nextInt();
			}
		}
		System.out.println("Dere har valgt "+antall+" antall spillere");
		
		for(int i = 0; i<antall; i++){
			System.out.println("Hva heter spiller "+(i+1)+"?");
			spillere[i].setNavn(tast.next());
			if(online){
				spillere[i].persistSpiller();
			}
		}
		
		@SuppressWarnings("unused") // All of the action is inside the constructor!
		Spill spill = online ? new Spill(spillere, online, brettid) : new Spill(spillere, online, -1);
		
		boolean vinner = false;
		while(!vinner){
			for(int i = 0; i < antall; i++){
				if(spillere[i].getPlassering() == 100){
					vinner = true;
					System.out.println(spillere[i].getNavn() + " har vunnet dette spillet!");
				}
			}
		}
		tast.close();
	}
	public int getBrettId() {
		return brettid;
	}
}
