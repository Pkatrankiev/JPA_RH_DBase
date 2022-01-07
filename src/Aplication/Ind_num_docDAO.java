package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Ind_num_doc;
import GlobalVariable.GlobalVariableForSQL_DBase;
import GlobalVariable.ReadFileWithGlobalTextVariable;

public class Ind_num_docDAO {

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	
	

	public static void setBasikValueInd_num_doc() {
		setValueInd_num_doc("няма информация", "няма информация");
		setValueInd_num_doc("ПП12-ИЕ.РНК.ПН.007/00", "Охарактеризиране на шламове и утайки в СП,,ИЕ 1÷4 блок");
		setValueInd_num_doc("ОП08-ИЕ.ФХК.ПР.002/00",
				"Проект 5f. Осигуряване проследимост на пробите от БНС и БВС на СК-1 и СК-2");
		setValueInd_num_doc("ОП01-ИЕ.РНК.УН.001/01", "Обем и организация на радиохимичния и технологичен контрол");

	}

	public static void setValueInd_num_doc(String value, String content) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Ind_num_doc valueEnt = new Ind_num_doc();
		valueEnt.setName(value);
		valueEnt.setContent(content);
		;
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	@SuppressWarnings("unchecked")
	public static List<Ind_num_doc> getInListAllValueInd_num_doc() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Ind_num_doc e ORDER BY e.name ASC");
		List<Ind_num_doc> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static Ind_num_doc getValueInd_num_docById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		Ind_num_doc ind_num_doc = (Ind_num_doc) entitymanager.find(Ind_num_doc.class, id);

		entitymanager.close();
		emfactory.close();

		return ind_num_doc;
	}

	@GET
	public static Ind_num_doc getValueIByName(String name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Ind_num_doc e WHERE e.name = :text";
		Ind_num_doc list;
		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		if (query.getResultList().isEmpty()) {
			list = (Ind_num_doc) getValueInd_num_docById(1);
		} else
			list = (Ind_num_doc) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static String[] getMasiveStringAllValueValueInd_num_doc() {
	
		List<Ind_num_doc> list = getInListAllValueInd_num_doc();
		
		String[] values = new String[list.size()];
		int i = 0;
		for (Ind_num_doc izpitvan_produkt : list) {
			values[i] = izpitvan_produkt.getName();
			i++;
		}
		return values;
	}

	public static void updateInd_num_doc(Ind_num_doc object) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
	
		entitymanager.find(Ind_num_doc.class, object.getId_ind_num_doc());
		entitymanager.merge(object);

		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null, "Прблем при обновяване на резултат: "+object.getName(), "Проблем с база данни:",
					JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}
	
	public static void DeleteInd_num_doc(Ind_num_doc valueEnt) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Ind_num_doc object = entitymanager.find(Ind_num_doc.class, valueEnt.getId_ind_num_doc());
		entitymanager.remove(object);
		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null,  "Прблем при изтриване на ред №: "+object.getId_ind_num_doc()+"Вероятно се използва в базата", "Проблем с база данни:",
					JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}
	
	@SuppressWarnings("unchecked")
	public static Object[][] getAll_Ind_num_doc_ForTable() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Ind_num_doc e ORDER BY e.Id_ind_num_doc ASC");
		List<Ind_num_doc> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		int col = 3;
		int row = list.size();
		Object[][] values = new Object[row][col];
		int i = 0;
		for (Ind_num_doc izpitvan_produkt : list) {
			values[i][0] = izpitvan_produkt.getId_ind_num_doc();
			values[i][1] = izpitvan_produkt.getContent();
			values[i][2] = izpitvan_produkt.getName();
			i++;
		}
			return values;
	}
	public static String[] getCulumnName_Ind_num_doc_ForTable() {
		int col = 3;
		String[] values = new String[col];
		values[0] = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("DBSTCN_Ind_num_doc_Id");
		values[2] = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("DBSTCN_Ind_num_doc_Name");
		values[1] = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("DBSTCN_Ind_num_doc_Content");
		return values;
	}
	public static int[] getCulumnSize_Ind_num_doc_ForTable() {
		int[] col = {40,300,160,500};
		return col;
	}
	@SuppressWarnings("rawtypes")
	public static Class[] getCulumnClass_Ind_num_doc_ForTable() {
			Class[] types = { Integer.class, String.class, String.class};
		return types;
	}

}
