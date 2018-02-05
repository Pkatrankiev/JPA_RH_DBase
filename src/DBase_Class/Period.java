package DBase_Class;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Period implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_period;

	private String period;
	private String value;



	public Period (String period, String value) {
		super();
		this.period = period;
		this.value = value;
		
	}

	public Period () {
		super();
	}

	public int getId_period() {
		return Id_period;
	}

	public void setId_period(int id_period) {
		Id_period = id_period;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
