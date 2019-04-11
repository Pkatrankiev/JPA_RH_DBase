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
@NamedQuery(name="getListAllDobiv", query="SELECT e FROM Dobiv e ORDER BY e.code_Standart ASC")
@NamedQuery(name="findNDobivByCode_Standart", query="SELECT e FROM Dobiv e WHERE e.code_Standart = :text")
@NamedQuery(name="findDobivByMetody", query="SELECT e FROM Dobiv e WHERE e.metody = :text ORDER BY e.Id_dobiv DESC")
@NamedQuery(name="findDobivByIzpitvan_produkt", query="SELECT e FROM Dobiv e WHERE e.izpitvan_produkt = :text")
@NamedQuery(name="findDobivByNuclide", query="SELECT e FROM Dobiv e WHERE e.nuclide = :text")
public class Dobiv implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_dobiv;
	private String code_Standart;
	private String description;
	private Double value_result;
	private Double uncertainty;
	private String date_measur;
	private String date_chim_oper;
	private String date_redac;
	private String basic_value;
	
	@ManyToOne
	private Metody metody;
	@ManyToOne
	private Izpitvan_produkt izpitvan_produkt;
	@ManyToOne
	private Nuclide nuclide;
	@ManyToOne
	private TSI tsi;
	@ManyToOne
	private Users user_chim_oper;
	@ManyToOne
	private Users user_measur;
	@ManyToOne
	private Users user_redac;
	
	
	public Dobiv( 
			String code_Standart,
			Metody metody,
			Izpitvan_produkt izpitvan_produkt,
			String description,
			Nuclide nuclide,
			Double value_result,
			Double uncertainty,
			TSI tsi,
			Users user_chim_oper,
			String date_chim_oper,
			Users user_measur,
			String date_measur,
			Users user_redac,
			String date_redac,
			String basic_value
			
			) {
		
		super();
		
		this.code_Standart = code_Standart;
		this.metody = metody;
		this.izpitvan_produkt = izpitvan_produkt;
		this.description = description;
		this.nuclide = nuclide;
		this.value_result = value_result;
		this.uncertainty = uncertainty;
		this.tsi = tsi;
		this.user_chim_oper = user_chim_oper;
		this.date_chim_oper = date_chim_oper;
		this.user_measur = user_measur;
		this.date_measur = date_measur;
		this.user_redac = user_redac;
		this.date_redac = date_redac;
		this.basic_value = basic_value;
		
	}

	 public Dobiv(){
		 super( );
		 }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId_dobiv() {
		return Id_dobiv;
	}

	public String getCode_Standart() {
		return code_Standart;
	}

	public void setCode_Standart(String code_Standart) {
		this.code_Standart = code_Standart;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getValue_result() {
		return value_result;
	}

	public void setValue_result(Double value_result) {
		this.value_result = value_result;
	}

	public Double getUncertainty() {
		return uncertainty;
	}

	public void setUncertainty(Double uncertainty) {
		this.uncertainty = uncertainty;
	}

	public String getDate_measur() {
		return date_measur;
	}

	public void setDate_measur(String date_measur) {
		this.date_measur = date_measur;
	}

	public String getDate_chim_oper() {
		return date_chim_oper;
	}

	public void setDate_chim_oper(String date_chim_oper) {
		this.date_chim_oper = date_chim_oper;
	}

	public String getDate_redac() {
		return date_redac;
	}

	public void setDate_redac(String date_redac) {
		this.date_redac = date_redac;
	}

	public String getBasic_value() {
		return basic_value;
	}

	public void setBasic_value(String basic_value) {
		this.basic_value = basic_value;
	}

	public Metody getMetody() {
		return metody;
	}

	public void setMetody(Metody metody) {
		this.metody = metody;
	}

	public Izpitvan_produkt getIzpitvan_produkt() {
		return izpitvan_produkt;
	}

	public void setIzpitvan_produkt(Izpitvan_produkt izpitvan_produkt) {
		this.izpitvan_produkt = izpitvan_produkt;
	}

	public Nuclide getNuclide() {
		return nuclide;
	}

	public void setNuclide(Nuclide nuclide) {
		this.nuclide = nuclide;
	}

	public TSI getTsi() {
		return tsi;
	}

	public void setTsi(TSI tsi) {
		this.tsi = tsi;
	}

	public Users getUser_chim_oper() {
		return user_chim_oper;
	}

	public void setUser_chim_oper(Users user_chim_oper) {
		this.user_chim_oper = user_chim_oper;
	}

	public Users getUser_measur() {
		return user_measur;
	}

	public void setUser_measur(Users user_measur) {
		this.user_measur = user_measur;
	}

	public Users getUser_redac() {
		return user_redac;
	}

	public void setUser_redac(Users user_redac) {
		this.user_redac = user_redac;
	}
	 
}
