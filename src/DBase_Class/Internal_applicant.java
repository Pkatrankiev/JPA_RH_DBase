package DBase_Class;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table
@NamedQuery(name="internal_applicantList", query="SELECT r FROM Internal_applicant r")
public class Internal_applicant {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_internal_applicant;
	
	private String internal_applicant_organization;
	private String internal_applicant_address;
	private String internal_applicant_telephone;
	
	public Internal_applicant(
			
			String internal_applicant_organization,
			String internal_applicant_address, 
			String internal_applicant_telephone) {
		super();
		this.internal_applicant_organization = internal_applicant_organization;
		this.internal_applicant_address = internal_applicant_address;
		this.internal_applicant_telephone = internal_applicant_telephone;
		
		
	}
	
	public Internal_applicant(){
		super();
	}
	
	
	public int getId_internal_applicant() {
		return Id_internal_applicant;
	}
	public int setId_internal_applicant(int id_internal_applicant) {
		return Id_internal_applicant = id_internal_applicant;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getInternal_applicant_organization() {
		return internal_applicant_organization;
	}

	public void setInternal_applicant_organization(String internal_applicant_organization) {
		this.internal_applicant_organization = internal_applicant_organization;
	}

	public String getInternal_applicant_address() {
		return internal_applicant_address;
	}

	public void setInternal_applicant_address(String internal_applicant_address) {
		this.internal_applicant_address = internal_applicant_address;
	}

	public String getInternal_applicant_telephone() {
		return internal_applicant_telephone;
	}

	public void setInternal_applicant_telephone(String internal_applicant_telephone) {
		this.internal_applicant_telephone = internal_applicant_telephone;
	}


}
