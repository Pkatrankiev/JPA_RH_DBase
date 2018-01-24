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
public class List_izpitvan_pokazatel implements Serializable {
	
	private static final long serialVersionUID = 1L;

			 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 private int Id_pokazatel;

		 private String name;
		 @ManyToOne
			private Metody metody;
		
		 public List_izpitvan_pokazatel(Integer id, String name,  Metody metody) {
		     super( );
		     		     
		     this.name = name;
		     this.metody = metody;
		    		  }
		 
		 public List_izpitvan_pokazatel(){
		 super( );
		 }
		 
		 public int getId_pokazatel() {
		 return Id_pokazatel;
		 }
		 public void setId_pokazatel(int id_pokazatel) {
				Id_pokazatel = id_pokazatel;
			}
		
		 public String getName_pokazatel() {
		 return name;
		 }

		 public void setName_pokazatel(String name) {
		 this.name = name;
		 }

		public Metody getMetody() {
			return metody;
		}

		public void setMetody(Metody metody) {
			this.metody = metody;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		



}
