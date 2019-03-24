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
@NamedQuery(name="getListAllNaredbi", query="SELECT e FROM Naredbi e ORDER BY e.name_request ASC")
@NamedQuery(name="findNaredbiByName_request", query="SELECT e FROM Naredbi e WHERE e.name_request = :text")
@NamedQuery(name="findNaredbiByName_protokol", query="SELECT e FROM Naredbi e WHERE e.name_protokol = :text")
@NamedQuery(name="findNaredbiByRazmernost", query="SELECT e FROM Naredbi e WHERE e.razmernost = :text")

public class Naredbi implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_naredbi;
	
	private String name_request;
	private String name_protokol;

		
	@ManyToOne
	private Razmernosti razmernost;
	
	
	
	public Naredbi(Razmernosti razmernost, String name_request, String name_protokol){
		super();
		
		
		this.razmernost = razmernost;
		this.name_request = name_request;
		this.name_protokol =  name_protokol;
	}
	public Naredbi(){
		 super( );
		 }
	public String getName_request() {
		return name_request;
	}
	public void setName_request(String name_request) {
		this.name_request = name_request;
	}
	public String getName_protokol() {
		return name_protokol;
	}
	public void setName_protokol(String name_protokol) {
		this.name_protokol = name_protokol;
	}
	public Razmernosti getRazmernost() {
		return razmernost;
	}
	public void setRazmernost(Razmernosti razmernost) {
		this.razmernost = razmernost;
	}
	public int getId_naredbi() {
		return Id_naredbi;
	}
	
	
	
}
