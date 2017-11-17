package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Obekt_na_izpitvane implements Serializable {
	
	private static final long serialVersionUID = 1L;

		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 private int Id_obekt_na_izpitvane;

		 private String name;
		
		 public Obekt_na_izpitvane(Integer id, String name) {
		     super( );
		     		     
		     this.name = name;
		    		  }
		 
		 public Obekt_na_izpitvane(){
		 super( );
		 }
		 
		 public int getId_obekt_na_izpitvane() {
		 return Id_obekt_na_izpitvane;
		 }

		
		 public String getName_obekt_na_izpitvane() {
		 return name;
		 }

		 public void setName_obekt_na_izpitvane(String name) {
		 this.name = name;
		 }

		

}