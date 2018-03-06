package DBase_Class;
import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import com.mysql.fabric.xmlrpc.base.Data;

@Entity
@Table
@NamedQuery(name="sampleList", query="SELECT r FROM Sample r")
public class Sample implements Serializable {



	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_sample;
	private String sample_code;
	private String description_sample;
	private String date_time_reference;
	private int godina_period;
	
	@ManyToOne
	private Request request;
	@ManyToOne
	private Obekt_na_izpitvane_sample obekt_na_izpitvane;
	@ManyToOne
	private Period period;
	
	

	public Sample( 
			String sample_code,
			String description_sample, 
			String date_time_reference,
			Request request,
			Obekt_na_izpitvane_sample obekt_na_izpitvane,
			Period period,
			int godina_period
			) {
		
		super();
		
		this.sample_code = sample_code;
		this.description_sample = description_sample;
		this.date_time_reference = date_time_reference;
		this.request = request;
		this.obekt_na_izpitvane = obekt_na_izpitvane;
		this.period = period;
		this.godina_period = godina_period;
		
	}

	 public Sample(){
		 super( );
		 }
	 
	
	public int getId_sample() {
		return Id_sample;
	}

	public void setId_sample(int id_sample) {
		Id_sample = id_sample;
	}

	public String getSample_code() {
		return sample_code;
	}

	public void setSample_code(String sample_code) {
		this.sample_code = sample_code;
	}

	public String getDescription_sample() {
		return description_sample;
	}

	public void setDescription_sample(String description_sample) {
		this.description_sample = description_sample;
	}

	public String getDate_time_reference() {
		return date_time_reference;
	}

	public void setDate_time_reference(String date_time_reference) {
		this.date_time_reference = date_time_reference;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Obekt_na_izpitvane_sample getObekt_na_izpitvane() {
		return obekt_na_izpitvane;
	}

	public void setObekt_na_izpitvane(Obekt_na_izpitvane_sample obekt_na_izpitvane) {
		this.obekt_na_izpitvane = obekt_na_izpitvane;
	}

		
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public int getGodina_period() {
		return godina_period;
	}

	public void setGodina_period(int godina_period) {
		this.godina_period = godina_period;
	}

	
	
}

