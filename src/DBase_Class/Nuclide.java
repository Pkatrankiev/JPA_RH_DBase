package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Nuclide implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_nuclide;

	private String name_bg;
	private String name_en;
	private String symbol;
	private Double half_life;
	private Character genesis;
	private Boolean favorite;
	private int ejection_key;

	public Nuclide (String name_bg, String name_en, String symbol, Double half_life, Character genesis, Boolean favorite, int ejection_key) {
		super();
		this.name_bg = name_bg;
		this.name_en = name_en;
		this.symbol = symbol;
		this.half_life = half_life;
		this.genesis = genesis;
		this.favorite = favorite;
		this.ejection_key = ejection_key;
	}

	public Nuclide () {
		super();
	}

	public int getId_nuclide() {
		return Id_nuclide;
	}

	public String getName_bg_nuclide() {
		return name_bg;
	}

	public void setName_bg_nuclide(String name) {
		this.name_bg = name;
	}
	
	public String getName_en_nuclide() {
		return name_en;
	}

	public void setName_en_nuclide(String name) {
		this.name_en = name;
	}

	public String getSymbol_nuclide() {
		return symbol;
	}

	public void setSymbol_nuclide(String symbol) {
		this.symbol = symbol;
	}

	public Double getHalf_life_nuclide() {
		return half_life;
	}

	public void setHalf_life_nuclide(Double half_life) {
		this.half_life = half_life;
	}

	public Character getGenesis_nuclide() {
		return genesis;
	}

	public void setGenesis_nuclide(Character genesis) {
		this.genesis = genesis;
	}
	
	public Boolean getFavorite_nuclide() {
		return favorite;
	}

	public void setFavorite_nuclide(Boolean favorite) {
		this.favorite = favorite;
	}

	public int getEjection_key() {
		return ejection_key;
	}

	public void setEjection_key(int ejection_key) {
		this.ejection_key = ejection_key;
	}

}
