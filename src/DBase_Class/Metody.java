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
@NamedQuery(name="metodyList", query="SELECT r FROM Metody r")

public class Metody implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_metody;
	
	@ManyToOne
	private Sample sample;
	@ManyToOne
	private List_Metody list_metody;

	@ManyToOne
	private Izpitvan_pokazatel pokazatel;

	
	public Metody(
			Sample sample,
			List_Metody list_metody,
			Izpitvan_pokazatel pokazatel) {
		
		super();
		
		this.sample = sample;
		this.list_metody = list_metody;
		this.pokazatel = pokazatel;
	}
	
	public Metody (){
		super();
	}

	public int getId_metody() {
		return Id_metody;
	}

	public void setId_metody(int id_metody) {
		Id_metody = id_metody;
	}

	
	public Izpitvan_pokazatel getPokazatel() {
		return pokazatel;
	}

	public void setPokazatel(Izpitvan_pokazatel pokazatel) {
		this.pokazatel = pokazatel;
	}

	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

	public List_Metody getList_metody() {
		return list_metody;
	}

	public void setList_metody(List_Metody list_metody) {
		this.list_metody = list_metody;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
