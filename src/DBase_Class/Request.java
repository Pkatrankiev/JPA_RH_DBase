package DBase_Class;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table
@NamedQuery(name="requestList", query="SELECT r FROM Request r")
public class Request implements Serializable {



	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_recuest;

	private String recuest_code;
	private String date_request;
	private Boolean accreditation;
	private Boolean section;
	
	private int counts_samples;
	private String description_sample_group;
	private String date_reception;
	private String date_execution;
	private String text_obekt_na_izpitvane_request;

	@ManyToOne
	private Extra_module extra_module;
	
	@ManyToOne
	private Ind_num_doc ind_num_doc;
	@ManyToOne
	private Izpitvan_produkt izpitvan_produkt;
	@ManyToOne
	private Razmernosti razmernosti;
	@ManyToOne
	private Zabelejki zabelejki;
	@ManyToOne
	private Users users;
	
	@ManyToOne
	private Obekt_na_izpitvane_request obekt_na_izpitvane_request;

	public Request( 
			String recuest_code, 
			String date_request, 
			Boolean accreditation,
			Boolean section,
			Extra_module xtra_module,
						
			int counts_samples, 
			String description_sample_group, 
			String date_reception,
			String date_execution,
			String text_obekt_na_izpitvane_request,
			Ind_num_doc ind_num_doc,
			Izpitvan_produkt izpitvan_produkt, 
			Razmernosti razmernosti, 
			Zabelejki zabelejki, 
			Users users,
			Obekt_na_izpitvane_request obekt_na_izpitvane_request) {
		
		super();
		
		this.recuest_code = recuest_code;
		this.date_request = date_request;
		this.accreditation = accreditation;
		this.section = section;
		this.extra_module = xtra_module;
		this.setText_obekt_na_izpitvane_request(text_obekt_na_izpitvane_request);	
		this.counts_samples = counts_samples;
		this.description_sample_group = description_sample_group;
		this.date_reception = date_reception;
		this.date_execution = date_execution;
		this.ind_num_doc = ind_num_doc;
		this.izpitvan_produkt = izpitvan_produkt;
		this.razmernosti = razmernosti;
		this.zabelejki = zabelejki;
		this.users = users;
		this.obekt_na_izpitvane_request = obekt_na_izpitvane_request;
	}

	 public Request(){
		 super( );
		 }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId_recuest() {
		return Id_recuest;
	}

	public String getRecuest_code() {
		return recuest_code;
	}

	public String getDate_request() {
		return date_request;
	}

	public Boolean getAccreditation() {
		return accreditation;
	}

	public Boolean getSection() {
		return section;
	}

	public int getCounts_samples() {
		return counts_samples;
	}

	public String getDescription_sample_group() {
		return description_sample_group;
	}

	public String getDate_reception() {
		return date_reception;
	}

	public String getDate_execution() {
		return date_execution;
	}

	public Extra_module getExtra_module() {
		return extra_module;
	}

	public Ind_num_doc getInd_num_doc() {
		return ind_num_doc;
	}

	public Izpitvan_produkt getIzpitvan_produkt() {
		return izpitvan_produkt;
	}

	public Razmernosti getRazmernosti() {
		return razmernosti;
	}

	public Zabelejki getZabelejki() {
		return zabelejki;
	}

	public Users getUsers() {
		return users;
	}

	public Obekt_na_izpitvane_request getObekt_na_izpitvane_request() {
		return obekt_na_izpitvane_request;
	}

	public void setId_recuest(int id_recuest) {
		Id_recuest = id_recuest;
	}

	public void setRecuest_code(String recuest_code) {
		this.recuest_code = recuest_code;
	}

	public void setDate_request(String date_request) {
		this.date_request = date_request;
	}

	public void setAccreditation(Boolean accreditation) {
		this.accreditation = accreditation;
	}

	public void setSection(Boolean section) {
		this.section = section;
	}

	public void setCounts_samples(int counts_samples) {
		this.counts_samples = counts_samples;
	}

	public void setDescription_sample_group(String description_sample_group) {
		this.description_sample_group = description_sample_group;
	}

	public void setDate_reception(String date_reception) {
		this.date_reception = date_reception;
	}

	public void setDate_execution(String date_execution) {
		this.date_execution = date_execution;
	}

	public void setExtra_module(Extra_module extra_module) {
		this.extra_module = extra_module;
	}

	public void setInd_num_doc(Ind_num_doc ind_num_doc) {
		this.ind_num_doc = ind_num_doc;
	}

	public void setIzpitvan_produkt(Izpitvan_produkt izpitvan_produkt) {
		this.izpitvan_produkt = izpitvan_produkt;
	}

	public void setRazmernosti(Razmernosti razmernosti) {
		this.razmernosti = razmernosti;
	}

	public void setZabelejki(Zabelejki zabelejki) {
		this.zabelejki = zabelejki;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setObekt_na_izpitvane_request(Obekt_na_izpitvane_request obekt_na_izpitvane_request) {
		this.obekt_na_izpitvane_request = obekt_na_izpitvane_request;
	}

	public String getText_obekt_na_izpitvane_request() {
		return text_obekt_na_izpitvane_request;
	}

	public void setText_obekt_na_izpitvane_request(String text_obekt_na_izpitvane_request) {
		this.text_obekt_na_izpitvane_request = text_obekt_na_izpitvane_request;
	}

			
			
	 
	
}
