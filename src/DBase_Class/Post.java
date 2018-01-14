package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Post implements Serializable {
	
	private static final long serialVersionUID = 1L;

			 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 private int Id_post;

		 private String name;
		
		 public Post(Integer id, String name) {
		     super( );
		     		     
		     this.name = name;
		    		  }
		 
		 public Post(){
		 super( );
		 }
		 
		 public int getId_post() {
		 return Id_post;
		 }

		
		 public String getName_post() {
		 return name;
		 }

		 public void setName_post(String name) {
		 this.name = name;
		 }



}

