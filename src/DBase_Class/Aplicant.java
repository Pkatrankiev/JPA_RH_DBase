package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Aplicant implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_aplicant;

	private String name;
	private String family;
	

	public Aplicant(Integer id, 
			String name, 
			String family
			) {
		super();
		this.name = name;
		this.family = family;
		
	}

	public Aplicant() {
		super();
	}

	public int getId_aplicant() {
		return Id_aplicant;
	}

	

	public String getName_aplicant() {
		return name;
	}

	public void setName_aplicant(String name) {
		this.name = name;
	}

	public String getFamily_aplicant() {
		return family;
	}

	public void setFamily_aplicant(String family) {
		this.family = family;
	}
	
	
}

