package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Zabelejki;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class ZabelejkiDAO {

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	

	public static void setBasicValueZabelejki() {
		setValueZabelejki("Към неопределеността е добавена 10% систематична грешка.");
		setValueZabelejki(": Ако е необходимо, протоколът от изпитване може да включва"
				+ " мнения и интерпретации за определени изпитвания (заключения не се допускат) само"
				+ " в съответствие с изискванията на т. 5.10.5 от БДС EN ISO/IEC 17025.");
		setValueZabelejki("Резултатите от изпитването се отнасят само за изпитваните проби."
				+ "Протоколът от изпитване не може да бъде възпроизвеждан, освен с писменото разрешение"
				+ "на лабораторията и само изцяло.");
	}

	// Zabelevki
	public static void setValueZabelejki(String value) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Zabelejki valueEnt = new Zabelejki();
		valueEnt.setName_zabelejki(value);
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	@SuppressWarnings("unchecked")
	public static List<Zabelejki> getInListAllValueZabelejki() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Zabelejki e");
		List<Zabelejki> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static Zabelejki getValueZabelejkiById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Zabelejki zabelejki = entitymanager.find(Zabelejki.class, id);

		entitymanager.close();
		emfactory.close();

		return zabelejki;
	}

	public static String[] getMasiveStringAllValueZabelejki(){
		List<Zabelejki> list =  getInListAllValueZabelejki();
		String[] values = new String[list.size()];
		int i = 0;
		for (Zabelejki pokazatel : list) {
			values[i] = pokazatel.getName_zabelejki();
			i++;
		}
		return values;
	}

	public static Zabelejki getValueZabelejkiByName(String name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Zabelejki e WHERE e.name = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		@SuppressWarnings("unchecked")
		List<Zabelejki> list = query.getResultList();
		Zabelejki zab= null;
		if (!list.isEmpty()){
			zab = list.get(0);
		}else{
			setValueZabelejki(name);
			zab = getValueZabelejkiByName(name);
		}
		entitymanager.close();
		emfactory.close();

		return zab;
	}

	
}
