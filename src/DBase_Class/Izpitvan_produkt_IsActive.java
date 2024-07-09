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
public class Izpitvan_produkt_IsActive implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Izpitvan_produkt_IsActive_ID;
		
	@ManyToOne
	private Izpitvan_produkt izpitvanProdukt;
	
	private boolean active;

	public Izpitvan_produkt_IsActive(Izpitvan_produkt izpitvanProdukt, boolean active) {
		
	
		super();
		
		this.izpitvanProdukt = izpitvanProdukt;
		this.setActive(active);
}

	public Izpitvan_produkt_IsActive() {
		super();
	}

	public Izpitvan_produkt getIzpitvanProdukt() {
		return izpitvanProdukt;
	}


	public void setIzpitvanProdukt(Izpitvan_produkt izpitvanProdukt) {
		this.izpitvanProdukt = izpitvanProdukt;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getIzpitvan_produkt_IsActive_ID() {
		return Izpitvan_produkt_IsActive_ID;
	}

	public void setIzpitvan_produkt_IsActive_ID(int izpitvan_produkt_IsActive_ID) {
		Izpitvan_produkt_IsActive_ID = izpitvan_produkt_IsActive_ID;
	}

}