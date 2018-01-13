package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({ 
	// get all posts
	@NamedQuery(name = "getAllValueIzpitvan_produkt", query = "SELECT p FROM Izpitvan_produkt p"), 
	// get posts by author
	@NamedQuery(name = "getValueIzpitvan_produktById", query = "SELECT p FROM Izpitvan_produkt p WHERE p.Id_izpitvan_produkt=:Id_izpitvan_produkt"),
		})
public class Izpitvan_produkt implements Serializable {
	
	private static final long serialVersionUID = 1L;

		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 private int Id_izpitvan_produkt;

		 private String name;
		

		 public Izpitvan_produkt(String name) {
		     super( );
		     		     
		     this.name = name;
		    
		  }
		 
		 public Izpitvan_produkt(){
		 super( );
		 }
		 
		 public int getId_zpitvan_produkt() {
		 return Id_izpitvan_produkt;
		 }

		
		 public String getName_zpitvan_produkt() {
		 return name;
		 }

		 public void setName_zpitvan_produkt(String name) {
		 this.name = name;
		 }


}
