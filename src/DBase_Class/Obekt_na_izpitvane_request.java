package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Obekt_na_izpitvane_request implements Serializable {
	
	private static final long serialVersionUID = 1L;

		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 private int Id_obekt_na_izpitvane;

		 private String name;
		 private String simple_Name;
		
		 public Obekt_na_izpitvane_request(Integer id, String name, String simple_Name) {
		     super( );
		     		     
		     this.name = name;
		     this.simple_Name = simple_Name;
		    		  }
		 
		 public Obekt_na_izpitvane_request(){
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

		public String getSimple_Name() {
			return simple_Name;
		}

		public void setSimple_Name(String simple_Name) {
			this.simple_Name = simple_Name;
		}

		

}