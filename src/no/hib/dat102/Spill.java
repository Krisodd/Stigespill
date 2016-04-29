package no.hib.dat102;
import java.util.Scanner;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;
@Entity(name="spill")
@Table(schema="stigespill")
public class Spill {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	Integer id;
//	@OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL},mappedBy="spill", targetEntity=Spiller.class)
//	List<Spiller> spillerene;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="brett")
	Brett brett;
	
	
	transient DB_logic db = new DB_logic();
	transient boolean uses_db;
	transient Scanner s = new Scanner(System.in);
	transient int currentTur;
	transient Spiller[] spillere;

	
	boolean ferdig = false;
	public Integer getID() {
		return id;
	}
	public Spill() {
	}
	public Spill(Spiller[] spillere, boolean online, int brettid){
//		spillerene = Arrays.asList(spillere);
		uses_db=online;
		brett = online ? Brett.getBrettFromDb(brettid) : new Brett(online);
		if(online) {
		brett.initialize(online);
		}
		currentTur=0;
		this.spillere = spillere;
		System.out.println("Dere startet et nytt spill.");

		int vinner = start();
		System.out.println("Spillet er ferdig, vinneren er " + spillere[vinner].getNavn());
	}
	
//	public static Logg getLogg(Integer til, Integer fra, Spiller spiller, Integer trill) {
//		Logg templogg = new Logg();
//		templogg.setFra(fra);
//		templogg.setTil(til);
//		templogg.setSpiller(spiller);
//		templogg.setTrill(trill);
//		return templogg;
//	}
	public Spiller flyttBrikke(Spiller spiller) { // Usually returns null, returns the Spiller object if the current player wins the game
		Logg logEntry = new Logg();
		logEntry.setSpiller(spiller); // Spiller
		boolean tilbake = false;
		int six_counter = 0;
		int trill;
		do {
		System.out.println("Trykk enter for å trille terningen.");
		s.nextLine();
		trill=Terning.trill();
		logEntry.setTrill(trill); // Trill
		if(spiller.isStuck()&&trill!=6) {
			System.out.println("Du må trille 6 for å fortsette spillet! :c");
			// Logg
			if(uses_db){logEntry(logEntry);};
			return null;
		}
		System.out.println(spiller.getNavn() + " trillet: " + trill);
		
		int currentPlace = spiller.getPlassering();
		logEntry.setFra(currentPlace); //Fra
		if(currentPlace+trill>100) {
			System.out.println("Du trillet over 100!");
			int temp_index = ((currentPlace+trill)-100);
			System.out.println("Du må gå tilbake " + temp_index + " steg fra mål.");
			temp_index = 100-temp_index;
			System.out.println(spiller.getNavn() + " rykket tilbake til rute " + temp_index);
			spiller.setPlassering(temp_index);
			logEntry.setTil(temp_index);
			tilbake = true;
		} else if((currentPlace+trill)==100) {
			System.out.println("Royal Kebab Pizza til " + spiller.getNavn() + " her!");
			// TODO lag en loggTrekk() metode
//			logg = new Logg(spiller, trill, id);
//			logg.log();
			logEntry.setTil(100);
			if(uses_db){logEntry(logEntry);};
			return spiller;
		}
		if(!tilbake){
		System.out.println(spiller.getNavn() + " flytter seg fra rute " + currentPlace + ", til rute " + (currentPlace+trill));
		currentPlace += trill;
		logEntry.setTil(currentPlace); // Til
		}
		if (currentPlace<100 &&brett.isSpecialTile(currentPlace)) {
			spiller.setPlassering(brett.specialMove(currentPlace));
		} else if(!tilbake) {
		spiller.setPlassering(currentPlace);
		logEntry.setTil(currentPlace); // Til
		}
		System.out.println();
		if(trill == 6) {
			System.out.println("Du trilte 6! Trill igjen :D");
			six_counter++;
		}
		} while(trill==6&&six_counter<4); // Roll the die again if you get a 6
		if(six_counter==3) {
			System.out.println("Du trillet 6 tre ganger! Gå tilbake til start. Du må trille 6 igjen for å starte");
			spiller.setPlassering(1);
			logEntry.setTil(1);
			spiller.setStuck(true);
		}

		if(uses_db){logEntry(logEntry);};
		return null;
		
	}
	
	
	
	public void logEntry(Logg loggObjekt) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(loggObjekt);
		em.getTransaction().commit();
		em.close();
	}
	public int hvemSinTur(){
		System.out.println(spillere[currentTur].getNavn() +" sin tur.");
		return currentTur;
	}
	public int nesteSinTur() {
		if(currentTur>=spillere.length-1){
			currentTur=0;
		} else {
			currentTur++;
		}
		System.out.println(spillere[currentTur].getNavn() +" sin tur.");
		return currentTur;
	}
	
	public int start() { // returns the winner!
		hvemSinTur();
		while(!ferdig) {
			if(flyttBrikke(spillere[currentTur])!=null) {
				return currentTur;
			}
			
			nesteSinTur();
		}
		return -1;
	}
	

}
