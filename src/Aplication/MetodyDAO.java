package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Izpitvan_produkt;
import DBase_Class.Metody;
import DBase_Class.Sample;

public class MetodyDAO {

	static String name_DBase = "JPA_RH_DBase";

	public static void setBasikValueMetody() {
		
		setValueMetody(
				"���������� �� ������������ �� ����-��������� ������������ � 89/90Sr ��� ���� � ������ ���������",
				"M.��-��-03 �������� 02/2014",true);
		setValueMetody("���������� �� ������������ �� ������", "�.��-��-03, �������� 03/2017",true);
		setValueMetody("����������� �� ������������������� ������������", "�.��-�� � 04 �������� 03",true);
		setValueMetody("����������� �� ����-��������������� �������", "�.��-�� � 05 �������� 02",true);
		setValueMetody("���������� �� ������������ �� ������ ��� ����", "�.��-�� � 03 �������� 02",true);
		setValueMetody("���������� �� ������������ �� 241Pu ���� ����-�������������", "�.��-�� � 07 �������� 02",true);
		
	}
	// Metody

	public static void setValueMetody(String name, String code, Boolean inAcredit) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Metody valueEnt = new Metody();
		valueEnt.setName_metody(name);
		valueEnt.setCode_metody(code);
		valueEnt.setInAcredit(inAcredit);
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Metody> getInListAllValueMetody() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Metody e ORDER BY e.code ASC");
		List<Metody> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static Metody getValueMetodyById(@QueryParam("id") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Metody metody = (Metody) entitymanager.find(Metody.class, id);

		entitymanager.close();
		emfactory.close();

		return metody;
	}

	@GET
	public static Metody getValueList_MetodyByName(String name) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Metody e WHERE e.code = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
	
		List<Metody> list = query.getResultList();
		
		entitymanager.close();
		emfactory.close();

		return list.get(0);
	}

	@GET
	public static Metody getList_MetodyByInAcredit(Boolean inAcredit) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Metody e WHERE e.inAcredit = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", inAcredit);
		
		Metody list = (Metody) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	public static String[] getMasiveStringAllValueMetody(){
		 List<Metody> list = getInListAllValueMetody();
		String[] values = new String[list.size()];
		int i = 0;
		for (Metody izpitvan_produkt : list) {
			values[i] = izpitvan_produkt.getCode_metody();
			i++;
		}
		return values;
	}

}
