package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Nuclide;
import DBase_Class.Obekt_na_izpitvane_sample;
import DBase_Class.Period;

public class PeriodDAO {


	static String name_DBase = "JPA_RH_DBase";

//	Nuclide
	public static void setValuePeriod(int period, String value) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Period valueEnt = new Period();
		valueEnt.setId_period(period);
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

	public static String [] getMasiveStringAllValuePeriod() {
		List<Period> list = getInListAllValuePeriod();
		String[] values = new String[list.size()];
		int i = 0;
		for (Period e : list) { 
			values[i] = e.getValue();
			i++;
			}
		return values;
	}
	
	public static void setBasicValuePeriod() {
		
		setValuePeriod(1, "������");
		setValuePeriod(2, "��������");
		setValuePeriod(3, "����");
		setValuePeriod(4, "�����");
		setValuePeriod(5, "���");
		setValuePeriod(6, "���");
		setValuePeriod(7, "���");
		setValuePeriod(8, "������");
		setValuePeriod(9, "���������");
		setValuePeriod(10, "��������");
		setValuePeriod(11, "�������");
		setValuePeriod(12, "��������");
		setValuePeriod(31, "1-�� ����������");
		setValuePeriod(32, "2-�� ����������");
		setValuePeriod(33, "3-�� ����������");
		setValuePeriod(34, "4-�� ����������");
		setValuePeriod(61, "1-�� ���������");
		setValuePeriod(62, "2-�� ���������");
		setValuePeriod(99, "�������");
		
		
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

		String hql = "SELECT e FROM Period e WHERE e.value = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", period);
		
		List<Period> listPeriod =  query.getResultList();
		Period list = listPeriod.get(0);
		entitymanager.close();
		emfactory.close();

		return list;
	}
	


}
