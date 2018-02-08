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
@NamedQuery(name="requestList", query="SELECT r FROM Request r")
public class Request implements Serializable {



	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_recuest;
	 @Column(name = "recuest_code", unique=true)
	 private String recuest_code;
	private String date_request;
	private Boolean accreditation;
	private Boolean section;
	private String applicant_name;
	private String applicant_family;
	private int counts_samples;
	private String description_sample_group;
	private String date_time_reception;
	private String date_execution;

	@ManyToOne
	private External_applicant external_applicant;
	@ManyToOne
	private Internal_applicant internal_applicant;
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
			External_applicant external_applicant,
			Internal_applicant internal_applicant,
			String applicant_name,
			String applicant_family,
			int counts_samples, 
			String description_sample_group, 
			String date_time_reception,
			String date_execution,
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
		this.external_applicant = external_applicant;
		this.internal_applicant = internal_applicant;
		this.applicant_name = applicant_name;
		this.applicant_family = applicant_family;
		this.counts_samples = counts_samples;
		this.description_sample_group = description_sample_group;
		this.date_time_reception = date_time_reception;
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
	 
			
		public String getApplicant_family() {
			return applicant_family;
		}

		
		public void setApplicant_family(String applicant_family) {
			this.applicant_family = applicant_family;
		}

		
	 
	public Obekt_na_izpitvane_request getObekt_na_izpitvane_request() {
			return obekt_na_izpitvane_request;
		}

		public void setObekt_na_izpitvane_request(Obekt_na_izpitvane_request obekt_na_izpitvane_request) {
			this.obekt_na_izpitvane_request = obekt_na_izpitvane_request;
		}

		public void setId_recuest(int id_recuest) {
			Id_recuest = id_recuest;
		}

	public int getId_recuest() {
		return Id_recuest;
	}

	
	public String getRecuest_code() {
		return recuest_code;
	}

	public void setRecuest_code(String recuest_code) {
		this.recuest_code = recuest_code;
	}

	public String getDate_request() {
		return date_request;
	}

	public void setDate_request(String date_request) {
		this.date_request = date_request;
	}

	public Boolean getAccreditation() {
		return accreditation;
	}

	public void setAccreditation(Boolean accreditation) {
		this.accreditation = accreditation;
	}
	
	
	public Boolean getSection() {
		return section;
	}

	public void setSection(Boolean section) {
		this.section = section;
	}

	
	public External_applicant getExternal_applicant() {
		return external_applicant;
	}

	public void setExternal_applicant(External_applicant external_applicant) {
		this.external_applicant = external_applicant;
	}

		
	public String getApplicant_name() {
		return applicant_name;
	}

	public void setApplicant_name(String applicant_name) {
		this.applicant_name = applicant_name;
	}

	public int getCounts_samples() {
		return counts_samples;
	}

	public void setCounts_samples(int number_samples) {
		this.counts_samples = number_samples;
	}

	public String getDescription_sample_group() {
		return description_sample_group;
	}

	public void setDescription_sample_group(String description_sample_group) {
		this.description_sample_group = description_sample_group;
	}

	public String getDate_time_reception() {
		return date_time_reception;
	}

	public void setDate_time_reception(String date_time_reception) {
		this.date_time_reception = date_time_reception;
	}

	public Ind_num_doc getInd_num_doc() {
		return ind_num_doc;
	}

	public void setInd_num_doc(Ind_num_doc ind_num_doc) {
		this.ind_num_doc = ind_num_doc;
	}

	public Izpitvan_produkt getIzpitvan_produkt() {
		return izpitvan_produkt;
	}

	public void setIzpitvan_produkt(Izpitvan_produkt izpitvan_produkt) {
		this.izpitvan_produkt = izpitvan_produkt;
	}

	public Razmernosti getRazmernosti() {
		return razmernosti;
	}

	public void setRazmernosti(Razmernosti razmernosti) {
		this.razmernosti = razmernosti;
	}

	public Zabelejki getZabelejki() {
		return zabelejki;
	}

	public void setZabelejki(Zabelejki zabelejki) {
		this.zabelejki = zabelejki;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Internal_applicant getInternal_applicant() {
		return internal_applicant;
	}

	public void setInternal_applicant(Internal_applicant internal_applicant) {
		this.internal_applicant = internal_applicant;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDate_execution() {
		return date_execution;
	}

	public void setDate_execution(String date_execution) {
		this.date_execution = date_execution;
	}
	
}
