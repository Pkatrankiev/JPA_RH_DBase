package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Nuclide;
import DBase_Class.Period;

public class PeriodDAO {


	static String name_DBase = "JPA_RH_DBase";

//	Nuclide
	public static void setValuePeriod(String period, String value) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Period valueEnt = new Period();
		valueEnt.setPeriod(period);
		valueEnt.setValue(value);
		
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Period> getInListAllValuePeriod() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Period e");
		List<Period> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static void setBasicValuePeriod() {
		setValuePeriod("���� ����������", "���� ����������");
		setValuePeriod("����������", "1 - 2017");
		setValuePeriod("����������", "2 - 2017");
		setValuePeriod("����������", "3 - 2017");
		setValuePeriod("����������", "4 - 2017");
		setValuePeriod("�����", "������ - 2017");
		setValuePeriod("�����", "�������� - 2017");
		setValuePeriod("�����", "���� - 2017");
		setValuePeriod("�����", "����� - 2017");
		setValuePeriod("�����", "��� - 2017");
		setValuePeriod("�����", "��� - 2017");
		setValuePeriod("�����", "��� - 2017");
		setValuePeriod("�����", "������ - 2017");
		setValuePeriod("�����", "��������� - 2017");
		setValuePeriod("�����", "�������� - 2017");
		setValuePeriod("�����", "������� - 2017");
		setValuePeriod("�����", "�������� - 2017");
		setValuePeriod("�������", "1- ������ - 2017");
		setValuePeriod("�������", "2- ������ - 2017");
		setValuePeriod("�������", "3- ������ - 2017");
		setValuePeriod("�������", "4- ������ - 2017");
		
		
		}
	
	@GET
	@QueryParam("{id}")
public static Period getPeriodById(@QueryParam("id") int id) {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
	EntityManager entitymanager = emfactory.createEntityManager();
	entitymanager.getTransaction().begin();
	Period  nuclide = (Period) entitymanager.find(Period.class, id);
	
	entitymanager.close();
	emfactory.close();

	return nuclide;
}
	
	@GET
	public static Period getValuePeriodByPeriod(String period) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Period e WHERE e.period = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", period);
		
		Period list = (Period) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	@GET
	public static Period getPeriodByValue(String value) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Period e WHERE e.value = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", value);
		
		Period list = (Period) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}

}
