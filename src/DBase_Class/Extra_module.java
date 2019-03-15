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
public class Extra_module implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_extra_module;
	
	private String additional_requirements;
	private String additional_arrangements;
	private Boolean return_samples;
	
	@ManyToOne
	private Aplicant aplicant;
	@ManyToOne
	private External_applicant external_applicant;
	@ManyToOne
	private Internal_applicant internal_applicant;
	@ManyToOne
	private DopalnIziskv doplIzis;
	
	
	public Extra_module(External_applicant external_applicant, 
			Internal_applicant internal_applicant,
			Aplicant aplicant,
			DopalnIziskv doplIzisk,
			Boolean return_samples,
			String additional_requirements,
			String additional_arrangements){
		super();
		
		this.external_applicant = external_applicant;
		this.internal_applicant = internal_applicant;
		this.aplicant = aplicant;
		this.doplIzis = doplIzisk;
		this.return_samples = return_samples;
		this.additional_requirements = additional_requirements;
		this.additional_arrangements = additional_arrangements;
	}
	public Extra_module(){
		 super( );
		 }
	
	public int getId_extra_module() {
		return Id_extra_module;
	}
	public void setId_extra_module(int id_extra_module) {
		Id_extra_module = id_extra_module;
	}

	public String getAdditional_requirements() {
		return additional_requirements;
	}
	public void setAdditional_requirements(String additional_requirements) {
		this.additional_requirements = additional_requirements;
	}
	public String getAdditional_arrangements() {
		return additional_arrangements;
	}
	public void setAdditional_arrangements(String additional_arrangements) {
		this.additional_arrangements = additional_arrangements;
	}
	public Boolean getReturn_samples() {
		return return_samples;
	}
	public void setReturn_samples(Boolean return_samples) {
		this.return_samples = return_samples;
	}
	public External_applicant getExternal_applicant() {
		return external_applicant;
	}
	public void setExternal_applicant(External_applicant external_applicant) {
		this.external_applicant = external_applicant;
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
	
	public DopalnIziskv getDoplIzisk() {
		return doplIzis;
	}
	public void setDoplIzisk(DopalnIziskv doplIzisk) {
		this.doplIzis = doplIzisk;
	}
	public Aplicant getAplicant() {
		return aplicant;
	}
	public void setAplicant(Aplicant aplicant) {
		this.aplicant = aplicant;
	}
	
	
}
