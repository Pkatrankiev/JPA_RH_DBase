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
@NamedQuery(name="findRequest_To_ObektNaIzpitvaneRequestByRequest", query="SELECT e FROM Request_To_ObektNaIzpitvaneRequest e WHERE e.request = :text")
@NamedQuery(name="findRequest_To_ObektNaIzpitvaneRequestByObektNaIzp", query="SELECT e FROM Request_To_ObektNaIzpitvaneRequest e WHERE e.obektNaIzp = :text")
@NamedQuery(name="ListAllRequest_To_ObektNaIzpitvaneRequest", query="SELECT e FROM Request_To_ObektNaIzpitvaneRequest e")
@NamedQuery(name="ListAllRequest_To_ObektNaIzpitvaneRequestByRequestAndObektNaIzp", query="SELECT e FROM Request_To_ObektNaIzpitvaneRequest e WHERE e.obektNaIzp = :obIzp AND e.request = :request")

public class Request_To_ObektNaIzpitvaneRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Request request;
	
	@ManyToOne
	private Obekt_na_izpitvane_request obektNaIzp;
	
	public Request_To_ObektNaIzpitvaneRequest(Request request, Obekt_na_izpitvane_request obektNaIzp){
		super();
		this.request = request;
		this.obektNaIzp = obektNaIzp;
	}
	
	public Request_To_ObektNaIzpitvaneRequest(){
		super();
	}

	public  int getId_Request_To_ObektNaIzpitvaneRequest() {
		return id;
	}
	

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Obekt_na_izpitvane_request getObektNaIzp() {
		return obektNaIzp;
	}

	public void setObektNaIzp(Obekt_na_izpitvane_request obektNaIzp) {
		this.obektNaIzp = obektNaIzp;
	}
	
	
}
