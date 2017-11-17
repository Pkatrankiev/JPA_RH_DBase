package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Razmernosti implements Serializable {
	
	private static final long serialVersionUID = 1L;

		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 private int Id_razmernosti;

		 private String name;
		
		 public Razmernosti(Integer id, String name) {
		     super( );
		     		     
		     this.name = name;
		    		  }
		 
		 public Razmernosti(){
		 super( );
		 }
		 
		 public int getId_razmernosti() {
		 return Id_razmernosti;
		 }

		
		 public String getName_razmernosti() {
		 return name;
		 }

		 public void setName_razmernosti(String name) {
		 this.name = name;
		 }



}


