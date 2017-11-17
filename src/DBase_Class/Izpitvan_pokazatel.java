package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Izpitvan_pokazatel implements Serializable {
	
	private static final long serialVersionUID = 1L;

		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 private int Id_pokazatel;

		 private String name;
		
		 public Izpitvan_pokazatel(Integer id, String name) {
		     super( );
		     		     
		     this.name = name;
		    		  }
		 
		 public Izpitvan_pokazatel(){
		 super( );
		 }
		 
		 public int getId_pokazatel() {
		 return Id_pokazatel;
		 }

		
		 public String getName_pokazatel() {
		 return name;
		 }

		 public void setName_pokazatel(String name) {
		 this.name = name;
		 }



}
