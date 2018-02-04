package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Dimension implements Serializable {
	
	private static final long serialVersionUID = 1L;

		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 private int Id_dimension;

		 private String name;
		
		 public Dimension(Integer id, String name) {
		     super( );
		     		     
		     this.name = name;
		    		  }
		 
		 public Dimension(){
		 super( );
		 }
		 
		 public int getId_dimension() {
		 return Id_dimension;
		 }

		
		 public String getName_dimension() {
		 return name;
		 }

		 public void setName_dimension(String name) {
		 this.name = name;
		 }



}