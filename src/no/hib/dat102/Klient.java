package no.hib.dat102;

import java.util.Scanner;

public class Klient {

	public void Start(){
		
		Spill spill = new Spill();
		System.out.println("Hei og velkommen til stigespill");
		System.out.println("Hvor mange spillere er dere?(2-4)");
		Scanner tast = new Scanner(System.in);
		int antall = tast.nextInt();
		Spiller[] spillere = new Spiller[antall];
		boolean valg = false;
		while(!valg){
			if(antall >= 2 && antall <= 4){
				spillere = new Spiller[antall];
				for(int i = 0; i<antall; i++){
					spillere[i] = new Spiller(i+1);
				}
				valg = true;
			}else{
				System.out.println("Ugylid valg av antall spillere, vennligst prøv igjen");
				antall = tast.nextInt();
			}
		}
		System.out.println("Dere har valgt "+antall+" antall spillere");
		
		
		tast.close();
	}
}
