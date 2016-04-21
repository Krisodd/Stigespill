package no.hib.dat102;
import java.util.Random;

public class Terning {
	public static int trill() {
		Random r = new Random();
		return r.nextInt(6)+1;
	}
}
