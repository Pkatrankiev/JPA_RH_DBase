package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery(name="getListAllEmition", query="SELECT e FROM Emition e ORDER BY e.emition_name ASC")
@NamedQuery(name="getListEmitionByEmition_name", query="SELECT e FROM Emition e WHERE e.emition_name = :text")
public class Emition implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_emition;
	private String emition_name;



	public Emition (int Id_emition, String value) {
		super();
		this.Id_emition = Id_emition;
		this.setEmition_name(emition_name);
		
	}

	public Emition () {
		super();
	}
	
	public int getId_emition() {
		return Id_emition;
	}

	public void setId_emition(int id_emition) {
		Id_emition = id_emition;
	}
	public String getEmition_name() {
		return emition_name;
	}

	public void setEmition_name(String value) {
		this.emition_name = value;
	}

}
