package DBase_Class;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Otclonenie implements Serializable {
	
	private static final long serialVersionUID = 1L;

		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 private int Id_otclon;

		 private String name;
		
		 public Otclonenie(Integer id, String name) {
		     super( );
		     		     
		     this.name = name;
		    		  }
		 
		 public Otclonenie(){
		 super( );
		 }
		 
		 public int getId_otclon() {
		 return Id_otclon;
		 }

		
		 public String getName_otclon() {
		 return name;
		 }

		 public void setName_otclon(String name) {
		 this.name = name;
		 }



}
