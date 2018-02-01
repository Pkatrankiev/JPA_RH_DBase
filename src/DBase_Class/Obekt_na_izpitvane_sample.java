package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Obekt_na_izpitvane_sample implements Serializable {
	
	private static final long serialVersionUID = 1L;

		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 private int Id_obekt_na_izpitvane;

		 private String name;
		 
		 @ManyToOne
			private Obekt_na_izpitvane_request obect_izpitvane_request; 
		
		 public Obekt_na_izpitvane_sample(Integer id, String name, Obekt_na_izpitvane_request obect_izpitvane_request) {
		     super( );
		     		     
		     this.name = name;
		     this. obect_izpitvane_request = obect_izpitvane_request;
		    		  }
		 
		 public Obekt_na_izpitvane_sample(){
		 super( );
		 }
		 
		 public int getId_obekt_na_izpitvane() {
		 return Id_obekt_na_izpitvane;
		 }

		
		 public Obekt_na_izpitvane_request getObect_izpitvane_request() {
			return obect_izpitvane_request;
		}

		public void setObect_izpitvane_request(Obekt_na_izpitvane_request obect_izpitvane_request) {
			this.obect_izpitvane_request = obect_izpitvane_request;
		}

		public String getName_obekt_na_izpitvane() {
		 return name;
		 }

		 public void setName_obekt_na_izpitvane(String name) {
		 this.name = name;
		 }

		

}
