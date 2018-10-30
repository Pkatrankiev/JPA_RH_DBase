package DBase_Class;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery(name="externalApplicantList", query="SELECT r FROM External_applicant r")
public class External_applicant {
	

//	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_external_applicant;
	
	private String external_applicant_name;
	private String external_applicant_address;
	private String external_applicant_telephone;
	private String external_applicant_contract_number;
	
	
	public External_applicant(String external_applicant_name, String external_applicant_address, String external_applicant_telephone,
			String external_applicant_contract_number) {
		super();
		this.external_applicant_name = external_applicant_name;
		this.external_applicant_address = external_applicant_address;
		this.external_applicant_telephone = external_applicant_telephone;
		this.external_applicant_contract_number = external_applicant_contract_number;
	}

	public External_applicant(){
		 super( );
		 }
	
	
	public int getId_external_applicant() {
		return Id_external_applicant;
	}


	public void setId_external_applicant(int id_external_applicant) {
		Id_external_applicant = id_external_applicant;
	}

	
	public String getExternal_applicant_name() {
		return external_applicant_name;
	}

	
	public void setExternal_applicant_name(String external_applicant_name) {
		this.external_applicant_name = external_applicant_name;
	}

	
	public String getExternal_applicant_address() {
		return external_applicant_address;
	}

	
	public void setExternal_applicant_address(String external_applicant_address) {
		this.external_applicant_address = external_applicant_address;
	}

	
	public String getExternal_applicant_telephone() {
		return external_applicant_telephone;
	}

	
	public void setExternal_applicant_telephone(String external_applicant_telephone) {
		this.external_applicant_telephone = external_applicant_telephone;
	}

	
	public String getExternal_applicant_contract_number() {
		return external_applicant_contract_number;
	}

	
	public void setExternal_applicant_contract_number(String external_applicant_contract_number) {
		this.external_applicant_contract_number = external_applicant_contract_number;
	}

}
