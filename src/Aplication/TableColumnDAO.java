package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import DBase_Class.TableColumn;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class TableColumnDAO {

	public static void setValueTableColumn(String tipe_Table, String name_Column, String Class_Column,
			String tipe_Column, String keyMap, Boolean inVisible) {
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		TableColumn valueEnt = new TableColumn();
		valueEnt.setTipe_Table(tipe_Table);
		valueEnt.setKeyMap(keyMap);
		valueEnt.setName_Column(name_Column);
		valueEnt.setClass_Column(Class_Column);
		valueEnt.setTipe_Column(tipe_Column);
		valueEnt.setInVisible(inVisible);
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	@SuppressWarnings("unchecked")
	public static List<TableColumn> getListAllTableColumn() {
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createNamedQuery("getListAllTableColumn");
		List<TableColumn> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public static List<TableColumn> getListTableColumnByTipe_Table(String name) {
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createNamedQuery("getListTableColumnByTipe_Table");
		query.setParameter("text", name);

		List<TableColumn> list = query.getResultList();

		entitymanager.close();
		emfactory.close();

		return list;
	}

}
