package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Metody_to_Pokazatel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int Id_Metody_to_Pokazatel;

	@ManyToOne
	private List_izpitvan_pokazatel pokazatel;
	
	@ManyToOne
	private Metody metody;

	public Metody_to_Pokazatel(Integer id, List_izpitvan_pokazatel pokazatel, Metody metody) {
		super();

		this.pokazatel = pokazatel;
		this.metody = metody;
	}
	
	public Metody_to_Pokazatel() {
		super();
	}

	public int getId_Metody_to_Pokazatel() {
		return Id_Metody_to_Pokazatel;
	}

	public void setId_Metody_to_Pokazatel(int id_Metody_to_Pokazatel) {
		Id_Metody_to_Pokazatel = id_Metody_to_Pokazatel;
	}

	public List_izpitvan_pokazatel getPokazatel() {
		return pokazatel;
	}

	public void setPokazatel(List_izpitvan_pokazatel pokazatel) {
		this.pokazatel = pokazatel;
	}

	public Metody getMetody() {
		return metody;
	}

	public void setMetody(Metody metody) {
		this.metody = metody;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
