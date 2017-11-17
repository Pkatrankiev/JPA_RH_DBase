package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Ind_num_doc implements Serializable {
	
	private static final long serialVersionUID = 1L;


	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int Id_ind_num_doc;

	 private String name;
	

	 public Ind_num_doc(Integer id, String name) {
	     super( );
	     
	     
	     this.name = name;
	    
	  }
	 
	 public Ind_num_doc(){
	 super( );
	 }
	 
	 public int getId_ind_num_doc() {
	 return Id_ind_num_doc;
	 }

	
	 public String getName() {
	 return name;
	 }

	 public void setName(String name) {
	 this.name = name;
	 }

	
	}
