package DBase_Class;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class DopalnIziskv implements Serializable {
	
	private static final long serialVersionUID = 1L;

		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 private int Id_dopIzis;

		 private String name;
		
		 public DopalnIziskv(Integer id, String name) {
		     super( );
		     		     
		     this.name = name;
		    		  }
		 
		 public DopalnIziskv(){
		 super( );
		 }
		 
		 public int getId_dopIzis() {
		 return Id_dopIzis;
		 }

		
		 public String getName_dopIzis() {
		 return name;
		 }

		 public void setName_dopIzis(String name) {
		 this.name = name;
		 }



}
