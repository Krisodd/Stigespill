package no.hib.dat102;

import java.util.Scanner;

public class Klient {

	public void Start(){
		
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
					spillere[i] = new Spiller();
				}
				valg = true;
			}else{
				System.out.println("Ugylid valg av antall spillere, vennligst prøv igjen");
				antall = tast.nextInt();
			}
		}
		System.out.println("Dere har valgt "+antall+" antall spillere");
		
		for(int i = 0; i<antall; i++){
			System.out.println("Hva heter spiller "+(i+1)+"?");
			spillere[i].setNavn(tast.next());
		}
		
		Spill spill = new Spill(spillere);
		
		boolean vinner = false;
		while(!vinner){
			for(int i = 0; i < antall; i++){
				if(spillere[i].getPlassering() == 100){
					vinner = true;
					System.out.println((spillere[i].getNavn() + " har vunnet dette spillet!"));
				}
			}
		}
		tast.close();
	}
}
