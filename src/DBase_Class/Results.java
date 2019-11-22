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
@NamedQuery(name="resultsList", query="SELECT r FROM Results r")
public class Results implements Serializable {



	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_results;
	private Double value_result;
	private int sigma;
	private Double uncertainty;
	private Double mda;
	private Double quantity;
	private String date_chim_oper;
	private String date_measur;
	private String date_redac;
	private String basic_value;
	private Boolean inProtokol;

	@ManyToOne
	private Nuclide nuclide;
	@ManyToOne
	private List_izpitvan_pokazatel pokazatel;
	@ManyToOne
	private Metody metody;
	@ManyToOne
	private Sample sample;
	@ManyToOne
	private Razmernosti rtazmernosti;
	@ManyToOne
	private Zabelejki zabelejki;
	@ManyToOne
	private Users user_chim_oper;
	@ManyToOne
	private Users user_measur;
	@ManyToOne
	private Users user_redac;
	@ManyToOne
	private Dimension dimension;
	@ManyToOne
	private TSI tsi;
	@ManyToOne
	private Dobiv dobiv;

	public Results( 
			Nuclide nuclide, 
			List_izpitvan_pokazatel pokazatel,
			Metody metody,
			Sample sample,
			Razmernosti rtazmernosti,
			String basic_value,
			Double value_result,
			int sigma,
			Double uncertainty,
			Double mda,
			Zabelejki zabelejki,
			Users user_chim_oper,
			String date_chim_oper,
			Users user_measur,
			String date_measur,
			Users user_redac,
			String date_redac,
			Boolean inProtokol,
			Double quantity,
			Dimension dimension,
			TSI tsi,
			Dobiv dobiv
			) {
		
		super();
		
		this.nuclide = nuclide;
		this.pokazatel = pokazatel;
		this.metody = metody;
		this.sample = sample;
		this.rtazmernosti = rtazmernosti;
		this.basic_value = basic_value;
		this.value_result = value_result;
		this.sigma = sigma;
		this.uncertainty = uncertainty;
		this.mda = mda;
		this.zabelejki = zabelejki;
		this.user_chim_oper = user_chim_oper;
		this.date_chim_oper = date_chim_oper;
		this.user_measur = user_measur;
		this.date_measur = date_measur;
		this.user_redac = user_redac;
		this.date_redac = date_redac;
		this.inProtokol = inProtokol;
		this.dimension = dimension;
		this.quantity = quantity;
		this.tsi = tsi;
		this.dobiv = dobiv;
		
		
	}

	 public Results(){
		 super( );
		 }
	 
	

	public int getId_results() {
		return Id_results;
	}

	public void setId_results(int id_results) {
		Id_results = id_results;
	}

	public Boolean getInProtokol() {
		return inProtokol;
	}

	public void setInProtokol(Boolean inProtokol) {
		this.inProtokol = inProtokol;
	}

	public Double getValue_result() {
		return value_result;
	}

	public void setValue_result(Double value_result) {
		this.value_result = value_result;
	}

	public int getSigma() {
		return sigma;
	}

	public void setSigma(int sigma) {
		this.sigma = sigma;
	}

	public Double getUncertainty() {
		return uncertainty;
	}

	public void setUncertainty(Double uncertainty) {
		this.uncertainty = uncertainty;
	}

	public Double getMda() {
		return mda;
	}

	public void setMda(Double mda) {
		this.mda = mda;
	}

	public String getDate_chim_oper() {
		return date_chim_oper;
	}

	public void setDate_chim_oper(String date_chim_oper) {
		this.date_chim_oper = date_chim_oper;
	}

	public String getDate_measur() {
		return date_measur;
	}

	public void setDate_measur(String date_measur) {
		this.date_measur = date_measur;
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

	public Nuclide getNuclide() {
		return nuclide;
	}

	public void setNuclide(Nuclide nuclide) {
		this.nuclide = nuclide;
	}


	public List_izpitvan_pokazatel getPokazatel() {
		return pokazatel;
	}

	public void setPokazatel(List_izpitvan_pokazatel pokazatel) {
		this.pokazatel = pokazatel;
	}

	public Razmernosti getRazmernosti() {
		return rtazmernosti;
	}

	public void setRazmernosti(Razmernosti rtazmernosti) {
		this.rtazmernosti = rtazmernosti;
	}

	public Zabelejki getZabelejki() {
		return zabelejki;
	}

	public void setZabelejki(Zabelejki zabelejki) {
		this.zabelejki = zabelejki;
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
	
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

	public Metody getMetody() {
		return metody;
	}

	public void setMetody(Metody metody) {
		this.metody = metody;
	}

	public TSI getTsi() {
		return tsi;
	}

	public void setTsi(TSI tsi) {
		this.tsi = tsi;
	}

	public Dobiv getDobiv() {
		return dobiv;
	}

	public void setDobiv(Dobiv dobiv) {
		this.dobiv = dobiv;
	}

	
}

