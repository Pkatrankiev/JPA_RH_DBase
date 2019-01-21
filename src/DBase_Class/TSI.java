package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


	@Entity
	@Table
	public class TSI  implements Serializable {
		
		private static final long serialVersionUID = 1L;

				 @Id
			 @GeneratedValue(strategy = GenerationType.IDENTITY)
			 private int Id_tsi;

			 private String name;
			 private  String descript;
			
			 public TSI(Integer id, String name, String descript) {
			     super( );
			     		     
			     this.name = name;
			     this. descript = descript;
			    		  }
			 
			 public TSI(){
			 super( );
			 }

			public int getId_post() {
				return Id_tsi;
			}

			public String getName() {
				return name;
			}

			public String getDescript() {
				return descript;
			}

			
			public void setName(String name) {
				this.name = name;
			}

			public void setDescript(String descript) {
				this.descript = descript;
			}
			 
			 
	}
