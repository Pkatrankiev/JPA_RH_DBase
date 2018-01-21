package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_Metody;
import DBase_Class.Sample;

public class List_MetodyDAO {

	static String name_DBase = "JPA_RH_DBase";

	public static void setBasikValueMetody(){
		setValueMetody("���������� �� ������������ �� ����-��������� ������������ � 89/90Sr ��� ���� � ������ ���������", "�.��-�� � 01 �������� 02");
		setValueMetody("���������� �� ������������ �� ������", "�.��-�� � 03 �������� 03");
		setValueMetody("����������� �� ������������������� ������������", "�.��-�� � 04 �������� 03");
		setValueMetody("����������� �� ����-��������������� �������", "�.��-�� � 05 �������� 02");
		setValueMetody("���������� �� ������������ �� ������ ��� ����", "�.��-�� � 03 �������� 02");
		setValueMetody("���������� �� ������������ �� 241Pu ���� ����-�������������", "�.��-�� � 07 �������� 02");
		setValueMetody("���������� �� ������������ �� ����-��������� ������������ � �������� �������", "�.��-�� � 08 �������� 02");
		setValueMetody("���������� �� ������������� �� ������������ �� �������������� 89SR, 90SR � 90Y ��� ��������� ������", "�.��-�� � 09 �������� 02");
		setValueMetody("���������� �� ������������ �� ����-��������� ������������", "�.��-�� � 10 �������� 02");
		setValueMetody("����������� �� ����-��������������� �������", "�.��-�� � 11 �������� 02");
		setValueMetody("���������� �� ������������ �� 129I ���� �������������� ����-�������������", "�.��-�� � 12 �������� 01");
		setValueMetody("���������� �� ������������ �� 232Th � 237Np ��� ����", "�.��-�� � 13 �������� 01");
		setValueMetody("���������� �� ������������ �� 99�� � �������� �������", "�.��-�� � 14 �������� 03");
		setValueMetody("���������� �� ������������ �� 14C � �������� �������", "�.��-�� � 15 �������� 02");
		setValueMetody("���������� �� ������������ �� 14C � �������� �������", "�.��-�� � 15 �������� 03");
		setValueMetody("���������� �� ������������ �� 55Fe, 63Ni, 89/90Sr � 241Pu � �������� �������", "�.��-�� � 16 �������� 03");
		setValueMetody("����������� �� ����-��������������� ������� CANBERRA ALPHA ANALYST", "�.��-�� � 21 �������� 01");

		
	}
	
//	Metody
	
	public static void setValueMetody(String value, String code) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		List_Metody valueEnt = new List_Metody();
		valueEnt.setName_metody(value);
		valueEnt.setCode_metody(code);
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<List_Metody> getInListAllValueMetody() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Metody e");
		List<List_Metody> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

//		for (Metody e : list) {
//			System.out.println("setValueMetody(\"" + ((Metody) e).getName_metody()
//					+"\", \"" + ((Metody) e).getCode_metody()+"\");");
//		}
		
		
//		for (Metody e : list) {
//			System.out.println("Num:" + ((Metody) e).getId_metody() + "  NAME :" + ((Metody) e).getName_metody()
//					+ "  Kode :" + ((Metody) e).getCode_metody());
//		}
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static List_Metody getValueMetodyById(@QueryParam("id") int id) {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
	EntityManager entitymanager = emfactory.createEntityManager();
	entitymanager.getTransaction().begin();
	List_Metody  metody = (List_Metody) entitymanager.find(List_Metody.class, id);
	
	entitymanager.close();
	emfactory.close();

	return metody;
}
	
	@GET
	public static List_Metody getValueList_MetodyByName(String name) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM List_Metody e WHERE e.code = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		if (query.getResultList().isEmpty()){
			setValueMetody("uknou", name);	
		}
		List_Metody list = (List_Metody) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}	
}
