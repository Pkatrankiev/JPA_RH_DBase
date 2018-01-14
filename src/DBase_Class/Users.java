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
	private String nikName;
	private String pass;
	private Boolean isAdmin;
	private Post post;

	public Users(Integer id, 
			String name, 
			String family, 
			String nikName, 
			String pass, 
			Boolean isAdmin, 
			Post post) {
		super();
		this.name = name;
		this.family = family;
		this.nikName = nikName;
		this.pass = pass;
		this.isAdmin = isAdmin;
		this.post = post;
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
	
	public String getNikName_users() {
		return nikName;
	}

	public void setNikName_users(String nikName) {
		this.nikName = nikName;
	}

	public String getPass_users() {
		return pass;
	}

	public void setPass_users(String pass) {
		this.pass = pass;
	}

	public Boolean  getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
}
