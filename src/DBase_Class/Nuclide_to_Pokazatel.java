package DBase_Class;



	import java.io.Serializable;

	import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
	import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

	@Entity
	@Table
	@NamedQuery(name="findNuclideToPokazatelByPokazatel", query="SELECT e FROM Nuclide_to_Pokazatel e WHERE e.pokazatel = :text ORDER BY e.nuclide ASC")
	@NamedQuery(name="findNuclide_to_PokazatelByNuclide", query="SELECT e FROM Nuclide_to_Pokazatel e WHERE e.nuclide = :text ORDER BY e.nuclide ASC")
	@NamedQuery(name="getInListAllNuclide_to_Pokazatel", query="SELECT e FROM Nuclide_to_Pokazatel e")
	public class Nuclide_to_Pokazatel implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		private int Id_Nuclide_to_Pokazatel;

		@ManyToOne
		private List_izpitvan_pokazatel pokazatel;
		
		@ManyToOne
		private Nuclide nuclide;

		public Nuclide_to_Pokazatel(List_izpitvan_pokazatel pokazatel, Nuclide nuclide) {
			super();

			this.pokazatel = pokazatel;
			this.nuclide = nuclide;
		}
		
		public Nuclide_to_Pokazatel() {
			super();
		}

		public int getId_Nuclide_to_Pokazatel() {
			return Id_Nuclide_to_Pokazatel;
		}

	
		public List_izpitvan_pokazatel getPokazatel() {
			return pokazatel;
		}

		public void setPokazatel(List_izpitvan_pokazatel pokazatel) {
			this.pokazatel = pokazatel;
		}

		public Nuclide getNuclide() {
			return nuclide;
		}

		public void setNuclide(Nuclide nuclide) {
			this.nuclide = nuclide;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	}
