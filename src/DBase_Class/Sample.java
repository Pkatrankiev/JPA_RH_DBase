package DBase_Class;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table
@NamedQuery(name="sampleList", query="SELECT r FROM Sample r")
public class Sample  {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Request request;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_sample;
	private String sample_code;
	private String description_sample;
	private String date_time_reference;
	private int godina_period;
	
	
	
	
	@ManyToOne
	private Obekt_na_izpitvane_sample obekt_na_izpitvane_sample;
	@ManyToOne
	private Period period;
	@ManyToOne
	private Request_To_ObektNaIzpitvaneRequest request_to_obekt_na_izpitvane_request;
	
	

	public Sample( 
			String sample_code,
			String description_sample, 
			String date_time_reference,
			Request request,
			Obekt_na_izpitvane_sample obekt_na_izpitvane_sample,
			Request_To_ObektNaIzpitvaneRequest request_to_obekt_na_izpitvane_request,
			Period period,
			int godina_period
			) {
		
		super();
		
		this.sample_code = sample_code;
		this.description_sample = description_sample;
		this.date_time_reference = date_time_reference;
		this.request = request;
		this.obekt_na_izpitvane_sample = obekt_na_izpitvane_sample;
		this.request_to_obekt_na_izpitvane_request = request_to_obekt_na_izpitvane_request;
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

	public Obekt_na_izpitvane_sample getObekt_na_izpitvane_sample() {
		return obekt_na_izpitvane_sample;
	}

	public void setObekt_na_izpitvane_sample(Obekt_na_izpitvane_sample obekt_na_izpitvane_sample) {
		this.obekt_na_izpitvane_sample = obekt_na_izpitvane_sample;
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

	public Request_To_ObektNaIzpitvaneRequest getRequest_to_obekt_na_izpitvane_request() {
		return request_to_obekt_na_izpitvane_request;
	}

	public void setRequest_to_obekt_na_izpitvane_request(
			Request_To_ObektNaIzpitvaneRequest request_to_obekt_na_izpitvane_request) {
		this.request_to_obekt_na_izpitvane_request = request_to_obekt_na_izpitvane_request;
	}

	
	
}

