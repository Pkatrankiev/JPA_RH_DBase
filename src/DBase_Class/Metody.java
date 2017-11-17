package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Metody implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_metody;

	private String name;
	private String code;

	public Metody(Integer id, String name, String code) {
		super();
		this.code = code;
		this.name = name;
	}

	public Metody() {
		super();
	}

	public int getId_metody() {
		return Id_metody;
	}

	public String getName_metody() {
		return name;
	}

	public void setName_metody(String name) {
		this.name = name;
	}

	public String getCode_metody() {
		return code;
	}

	public void setCode_metody(String code) {
		this.code = code;
	}

}
