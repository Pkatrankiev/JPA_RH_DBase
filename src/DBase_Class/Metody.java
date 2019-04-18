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
@NamedQuery(name="getListAllMetody", query="SELECT e FROM Metody e ORDER BY e.code ASC")
@NamedQuery(name="findMetodyByCode", query="SELECT e FROM Metody e WHERE e.code = :text")
@NamedQuery(name="getList_MetodyByInAcredit", query="SELECT e FROM Metody e WHERE e.inAcredit = :text")
@NamedQuery(name="getList_MetodyByActing", query="SELECT e FROM Metody e WHERE e.acting = :text ORDER BY e.code ASC")

public class Metody implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_metody;
	
	private String name;
	private String code;
	private Boolean inAcredit;
	private Boolean acting;

	public Metody(Integer id, String name, String code, Boolean inAcredit, Boolean acting) {
		super();
		this.code = code;
		this.name = name;
		this.inAcredit = inAcredit;
		this.acting = acting;
	}

	public Metody (){
		super();
	}

	public Boolean getInAcredit() {
		return inAcredit;
	}

	public void setInAcredit(Boolean inAcredit) {
		this.inAcredit = inAcredit;
	}

	public int getId_metody() {
		return Id_metody;
	}

	public void setId_metody(int id_metody) {
		Id_metody = id_metody;
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

	public Boolean getActing_metody() {
		return acting;
	}

	public void setActing_metody(Boolean acting) {
		this.acting = acting;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
