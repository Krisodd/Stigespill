package no.hib.dat102;

import java.util.Scanner;

public class Klient {

	public void Start(){
		System.out.println("Hei og velkommen til stigespill");
		System.out.println("Hvor mange spillere er dere?(2-4)");
		Scanner tast = new Scanner(System.in);
		int antall = tast.nextInt();
		if(antall >= 2 && antall <= 4){
			for(int i = 0; i<=antall)
		}
	}
}
