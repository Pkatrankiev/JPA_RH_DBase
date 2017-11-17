package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Users implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id_users;

	private String name;
	private String family;
	private String pass;
	private Integer priority;

	public Users(Integer id, String name, String family, String pass, Integer priority) {
		super();
		this.name = name;
		this.family = family;
		this.pass = pass;
		this.priority = priority;
	}

	public Users() {
		super();
	}

	public int getId_users() {
		return Id_users;
	}

	public String getName_users() {
		return name;
	}

	public void setName_users(String name) {
		this.name = name;
	}

	public String getFamily_users() {
		return family;
	}

	public void setFamily_users(String family) {
		this.family = family;
	}

	public String getPass_users() {
		return pass;
	}

	public void setPass_users(String pass) {
		this.pass = pass;
	}

	public Integer getPriority_users() {
		return priority;
	}

	public void setPriority_users(Integer priority) {
		this.priority = priority;
	}

}
