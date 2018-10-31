package DBase_Class;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

public class AplicantDAO {

	static String name_DBase = "JPA_RH_DBase";

//	Aplicant
	public static void setValueAplicant(String name, String family) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Aplicant valueEnt = new Aplicant();
		valueEnt.setName_aplicant(name);
		valueEnt.setFamily_aplicant(family);
		
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	@SuppressWarnings("unchecked")
	@GET
	public static List<Aplicant> getInListAllValueAplicant() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Aplicant e");
		List<Aplicant> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
	
		return list;
	}
	
	public static String[] getMasiveStringAllName_FamilyAplicant() {
		List<Aplicant> list = getInListAllValueAplicant();
	String[] values = new String[list.size()];
	int i = 0;
	for (Aplicant user : list) {
		values[i] = user.getName_aplicant()+" "+user.getFamily_aplicant();
		i++;
	}
	return values;
	}
	
	@GET
	@QueryParam("{id}")
	public static Aplicant getValueAplicantById(@QueryParam("id") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Aplicant aplicant = (Aplicant) entitymanager.find(Aplicant.class, id);

		entitymanager.close();
		emfactory.close();

		return aplicant;
	}

	@SuppressWarnings("unchecked")
	@GET
	public static List<Aplicant> getValueAplicantByName(String name) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Aplicant e WHERE e.name = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
	
		List<Aplicant> list =  query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	public static  List<Aplicant> getValueAplicantByFamily(String family) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Aplicant e WHERE e.family = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", family);
	
		 List<Aplicant> list =  query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}
}
