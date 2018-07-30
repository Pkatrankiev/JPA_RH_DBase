package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class IzpitvanPokazatel implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_pokazatel;

	@ManyToOne
	private List_izpitvan_pokazatel pokazatel;

	@ManyToOne
	private Request request;
	
	@ManyToOne
	private Metody metody;

	public IzpitvanPokazatel(List_izpitvan_pokazatel pokazatel, Request request, Metody metody) {
		super();

		this.pokazatel = pokazatel;
		this.request = request;
		this.metody = metody;
	}

	public IzpitvanPokazatel() {
		super();
	}

	public int getId_pokazatel() {
		return Id_pokazatel;
	}

	public void setId_pokazatel(int id_pokazatel) {
		Id_pokazatel = id_pokazatel;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
	
	public Metody getMetody() {
		return metody;
	}

	public void setMetody(Metody metody) {
		this.metody = metody;
	}

	public List_izpitvan_pokazatel getPokazatel() {
		return pokazatel;
	}

	public void setPokazatel(List_izpitvan_pokazatel pokazatel) {
		this.pokazatel = pokazatel;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
