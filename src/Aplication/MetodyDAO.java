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
				"M.��-��-03 �������� 02/2014");
		setValueMetody("���������� �� ������������ �� ������", "�.��-��-03, �������� 03/2017");
		setValueMetody("����������� �� ������������������� ������������", "�.��-�� � 04 �������� 03");
		setValueMetody("����������� �� ����-��������������� �������", "�.��-�� � 05 �������� 02");
		setValueMetody("���������� �� ������������ �� ������ ��� ����", "�.��-�� � 03 �������� 02");
		setValueMetody("���������� �� ������������ �� 241Pu ���� ����-�������������", "�.��-�� � 07 �������� 02");
		setValueMetody("���������� �� ������������ �� ����-��������� ������������ � �������� �������",
				"M.��-��-08 �������� 02/2014");
		setValueMetody(
				"���������� �� ������������� �� ������������ �� �������������� 89SR, 90SR � 90Y ��� ��������� ������",
				"�.��-�� � 09 �������� 02");
		setValueMetody("���������� �� ������������ �� ����-��������� ������������", "�.��-��-10 �������� 02/2014");
		setValueMetody("����������� �� ����-��������������� �������", "�.��-�� � 11 �������� 02");
		setValueMetody("���������� �� ������������ �� 129I ���� �������������� ����-�������������",
				"�.��-��-12, �������� 01/2013");
		setValueMetody("���������� �� ������������ �� 232Th � 237Np ��� ����", "�.��-�� � 13 �������� 01");
		setValueMetody("���������� �� ������������ �� 99�� � �������� �������", "�.��-�� � 14 �������� 03");
		setValueMetody("���������� �� ������������ �� 14C � �������� �������", "�.��-�� � 15 �������� 02");
		setValueMetody("���������� �� ������������ �� 14C � �������� �������", "�.��-��-15, �������� 03/2017");
		setValueMetody("���������� �� ������������ �� 55Fe, 63Ni, 89/90Sr � 241Pu � �������� �������",
				"M.��-��-16 �������� 03/2014");
		setValueMetody("����������� �� ����-��������������� ������� CANBERRA ALPHA ANALYST",
				"�.��-�� � 21 �������� 01");

	}

	// Metody

	public static void setValueMetody(String name, String code) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Metody valueEnt = new Metody();
		valueEnt.setName_metody(name);
		valueEnt.setCode_metody(code);
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Metody> getInListAllValueMetody() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Metody e");
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
		if (query.getResultList().isEmpty()) {
			setValueMetody("uknou", name);
		}
		Metody list = (Metody) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}


}
