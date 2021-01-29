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
@NamedQuery(name="getListAllMetody", query="SELECT e FROM Metody e ORDER BY e.code ASC")
@NamedQuery(name="findMetodyByCode", query="SELECT e FROM Metody e WHERE e.code = :text")
@NamedQuery(name="getList_MetodyByInAcredit", query="SELECT e FROM Metody e WHERE e.inAcredit = :text")
@NamedQuery(name="getList_MetodyByActing", query="SELECT e FROM Metody e WHERE e.acting = :text ORDER BY e.code ASC")
@NamedQuery(name="getList_MetodyByActingAndArrangement", query="SELECT e FROM Metody e WHERE e.acting = :text ORDER BY e.arrangement ASC")
@NamedQuery(name="getList_MetodyByEmitionAndArrangement", query="SELECT e FROM Metody e WHERE e.emition = :text ORDER BY e.arrangement ASC")


public class Metody implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_metody;
	
	private String name;
	private String code;
	private Boolean inAcredit;
	private Boolean acting;
	private int arrangement;
	@ManyToOne
	private  Emition emition;

	public Metody(Integer id, String name, String code, Boolean inAcredit, Boolean acting, int arrangement, Emition emition) {
		super();
		this.code = code;
		this.name = name;
		this.inAcredit = inAcredit;
		this.acting = acting;
		this.arrangement = arrangement;
		this.emition = emition;
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

	public int getArrangement() {
		return arrangement;
	}

	public void setArrangement(int arrangement) {
		this.arrangement = arrangement;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getActing() {
		return acting;
	}

	public void setActing(Boolean acting) {
		this.acting = acting;
	}

	public Emition getEmition() {
		return emition;
	}

	public void setEmition(Emition emition) {
		this.emition = emition;
	}

	
	
	
}
