package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery(name="getListAllNormi", query="SELECT e FROM Normi e ORDER BY e.nuclide ASC")
@NamedQuery(name="findNormiByNaredbi", query="SELECT e FROM Normi e WHERE e.naredbi = :text")
@NamedQuery(name="findNormiByNuclide", query="SELECT e FROM Normi e WHERE e.nuclide = :text")
@NamedQuery(name="findNormiByValue_norna", query="SELECT e FROM Normi e WHERE e.value_norma = :text")

public class Normi implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_normi;
		
	private Double value_norma;
	
	@ManyToOne
	private Naredbi naredbi;
	@ManyToOne
	private Nuclide nuclide;
	
	
	
	public Normi(Naredbi naredbi, Nuclide nuclide, Double value_norma){
		super();
		
		
		this.naredbi = naredbi;
		this.nuclide = nuclide;
		this.value_norma =  value_norma;
	}
	public Normi(){
		 super( );
		 }
	public Double getValue_norma() {
		return value_norma;
	}
	public void setValue_norma(Double value_norma) {
		this.value_norma = value_norma;
	}
	public Naredbi getNaredbi() {
		return naredbi;
	}
	public void setNaredbi(Naredbi naredbi) {
		this.naredbi = naredbi;
	}
	public Nuclide getNuclide() {
		return nuclide;
	}
	public void setNuclide(Nuclide nuclide) {
		this.nuclide = nuclide;
	}
	public int getId_normi() {
		return Id_normi;
	}
	
	
}
