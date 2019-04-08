package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery(name="getListAllMetody_to_NiclideForDobive", query="SELECT e FROM Metody_to_NiclideForDobive e ORDER BY e.metody ASC")
@NamedQuery(name="findMetody_to_NiclideForDobiveByMetody", query="SELECT e FROM Metody_to_NiclideForDobive e WHERE e.metody = :text")
@NamedQuery(name="findMetody_to_NiclideForDobiveByNuclide", query="SELECT e FROM Metody_to_NiclideForDobive e WHERE e.nuclide = :text")
public class Metody_to_NiclideForDobive implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private  int Id_Metody_to_NiclideForDobive;

	@ManyToOne
	private  Nuclide nuclide;
	
	@ManyToOne
	private  Metody metody;

	public Metody_to_NiclideForDobive(Integer id, Nuclide nuclide, Metody metody) {
		super();

		this.nuclide = nuclide;
		this.metody = metody;
	}
	
	public Metody_to_NiclideForDobive() {
		super();
	}

	public Nuclide getNuclide() {
		return nuclide;
	}

	public void setNuclide(Nuclide nuclide) {
		this.nuclide = nuclide;
	}

	public  Metody getMetody() {
		return metody;
	}

	public void setMetody(Metody metody) {
		this.metody = metody;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId_Metody_to_NiclideForDobive() {
		return Id_Metody_to_NiclideForDobive;
	}
	
	
}
