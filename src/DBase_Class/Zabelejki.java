package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Zabelejki implements Serializable {
	
	private static final long serialVersionUID = 1L;

		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 private int Id_zabelejki;

		 private String name;
		 private String protokol_name;
		
		 public Zabelejki(Integer id, String name, String protokol_name) {
		     super( );
		     		     
		     this.name = name;
		     this.setProtokol_name(protokol_name);
		    		  }
		 
		 public Zabelejki(){
		 super( );
		 }
		 
		 public int getId_zabelejki() {
		 return Id_zabelejki;
		 }

		
		 public String getName_zabelejki() {
		 return name;
		 }

		 public void setName_zabelejki(String name) {
		 this.name = name;
		 }

		public String getProtokol_name() {
			return protokol_name;
		}

		public void setProtokol_name(String protokol_name) {
			this.protokol_name = protokol_name;
		}



}
