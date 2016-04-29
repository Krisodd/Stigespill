package no.hib.dat102;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity(name="brett")
@Table(name="brett",schema = "stigespill")
public class Brett {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column(name="antallstiger")
	Integer antallStiger;
	@Column(name="antallslanger")
	Integer antallSlanger;
	transient final int ANTALL_RUTER = 101; // Add one to keep Arrays happy
	transient Rute[] ruter = new Rute[ANTALL_RUTER];
	transient int[] plassering = new int[4];
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Special tiles mapping																							//
	//																													//
	// The mappings must be sorted by the element index (the first element in each sub-array)							//
	// Formatted as a pair of integers, where [0] represents the tile index and [1] represents the destination index	//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	transient int[][] STIGE_MAPPING = { 
			{14, 23}, 
			{24, 28}, 
			{36, 47}, 
			{69, 78}, 
			{83, 91}};
	
	transient int[][] SLANGE_MAPPING = {
			{32, 23}, 
			{49, 28}, 
			{77, 47}, 
			{88, 78}, 
			{99, 91}};
//	@OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL},mappedBy="brett", targetEntity=Slange.class)
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="brett")
	List<Slange> slanger = new ArrayList<Slange>();
//	@OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL},mappedBy="brett", targetEntity=Stige.class)
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="brett")
	List<Stige> stiger = new ArrayList<Stige>();
	
	public Brett() {
	}
	public Brett(Boolean uses_db) { 
		antallStiger = 0;
		antallSlanger = 0;
		if(uses_db) {
		for(int i=1;i<ANTALL_RUTER;i++) { // populate board with tiles offline
			if(antallStiger<STIGE_MAPPING.length&&STIGE_MAPPING[antallStiger][0]==i) {
				ruter[i] = new Stige(i, STIGE_MAPPING[antallStiger][1], this);
				stiger.add((Stige)ruter[i]);
				antallStiger++;
				System.out.println("Added stige: " + ruter[i].getRuteIndex() + " -> " + ruter[i].getDestinationIndex());
			} else if (antallSlanger<SLANGE_MAPPING.length&&SLANGE_MAPPING[antallSlanger][0]==i) {
				ruter[i] = new Slange(i, SLANGE_MAPPING[antallSlanger][1], this);
				antallSlanger++;
				slanger.add((Slange)ruter[i]);
				System.out.println("Added slange: " + ruter[i].getRuteIndex() + " -> " + ruter[i].getDestinationIndex());
			} else {
				ruter[i] = new Rute(i);
			}
		}
		} else{
			
		}
	}
	public Rute[] getRuter() {
		return ruter;
	}
	public Rute getRute(int index) {
		return ruter[index];
	}
	public int specialMove(int index) {
		return ruter[index].move();
	}
	public int tileDestination(int index) { // If true, returns an integer with the destination tile of the special tile
		return ruter[index].getDestinationIndex();
	}
	public boolean isSpecialTile(int index) {
		if (index>100){
			return false;
		}
		return ruter[index].isSpecialTile();
	}
	public void goTo(Spiller spiller, int index){
		spiller.setPlassering(index);
		if(ruter[index] instanceof Stige) {
			ruter[index].move();
		}
	}
	public void persistBrett() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(this);
		em.getTransaction().commit();
		em.close();
	}
	
	public void getBrettFromDb() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = emf.createEntityManager();
	}
	
}
