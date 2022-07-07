package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table
public class Ejection implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_ejection;
	
	private Double volum;
	private int godina;
	
	@ManyToOne
	private Izpitvan_produkt produkt;
	@ManyToOne
	private Obekt_na_izpitvane_request obect;
	@ManyToOne
	private Period mesec;
	
	
	public Ejection(
			Izpitvan_produkt produkt, 
			Obekt_na_izpitvane_request obect,
			Period mesec,
			int godina,
			Double volum){
		super();
		
		this.produkt = produkt;
		this.obect = obect;
		this.mesec = mesec;
		this.godina = godina;
		this.volum = volum;
	}
	
	public Ejection(){
		 super( );
	}

	public int getId_ejection() {
		return Id_ejection;
	}

	public void setId_ejection(int id_ejection) {
		Id_ejection = id_ejection;
	}

	public Double getVolum() {
		return volum;
	}

	public void setVolum(Double volum2) {
		this.volum = volum2;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public Izpitvan_produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Izpitvan_produkt produkt) {
		this.produkt = produkt;
	}

	public Obekt_na_izpitvane_request getObect() {
		return obect;
	}

	public void setObect(Obekt_na_izpitvane_request obect) {
		this.obect = obect;
	}

	public Period getMesec() {
		return mesec;
	}

	public void setMesec(Period mesec) {
		this.mesec = mesec;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
