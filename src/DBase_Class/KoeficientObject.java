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
@NamedQuery(name="getListAllKoeficientObject", query="SELECT e FROM KoeficientObject e ORDER BY e.obect ASC")



public class KoeficientObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_metody;
	private Double koeficient;

	@ManyToOne
	private  Obekt_na_izpitvane_request obect;
	
	public KoeficientObject(Integer id, 
			Double koeficient, 
			Obekt_na_izpitvane_request emition) {
		super();
		
		this.setKoeficient(koeficient);
		this.obect = emition;
	}

	public KoeficientObject (){
		super();
	}

	public Double getKoeficient() {
		return koeficient;
	}

	public void setKoeficient(Double koeficient) {
		this.koeficient = koeficient;
	}

	public int getId_metody() {
		return Id_metody;
	}

	public void setId_metody(int id_metody) {
		Id_metody = id_metody;
	}

	public Obekt_na_izpitvane_request getObect() {
		return obect;
	}

	public void setObect(Obekt_na_izpitvane_request obect) {
		this.obect = obect;
	}

	
}
