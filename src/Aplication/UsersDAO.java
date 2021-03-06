package Aplication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Post;
import DBase_Class.Users;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class UsersDAO {

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	

	public static void setBasicValueUsers(){
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		
		
		setValueUsers("��������", "��������", "123", "123", false, PostDAO.getValuePostById(1));	
	setValueUsers("������", "�������", "mbalachev", "123", false, PostDAO.getValuePostById(2));
	setValueUsers("�����", "����������","pkatrankiev", "123", false, PostDAO.getValuePostById(2));
	setValueUsers("������", "�����", "miliev", "123", true, PostDAO.getValuePostById(4));
}
//	Users
	public static void setValueUsers(String name, String family, String nikName, String pass, Boolean isAdmin, Post post) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
			entitymanager.getTransaction().begin();

		Users valueEnt = new Users();
		valueEnt.setName_users(name);
		valueEnt.setFamily_users(family);
		valueEnt.setNikName_users(nikName);
		valueEnt.setPass_users(pass);
		valueEnt.setIsAdmin(isAdmin);
		valueEnt.setPost(post);

		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	@SuppressWarnings("unchecked")
	public static List<Users> getInListAllValueUsers() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Users e ORDER BY e.name ASC");
		List<Users> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
	
		return list;
	}
	
	public static String[] getMasiveStringAllName_FamilyUsers() {
		List<Users> list = getInListAllValueUsers();
	String[] values = new String[list.size()];
	int i = 0;
	for (Users user : list) {
		values[i] = user.getName_users()+" "+user.getFamily_users();
		i++;
	}
	return values;
	}
	
	@GET
	@QueryParam("{id}")
	public static Users getValueUsersById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		Users users = (Users) entitymanager.find(Users.class, id);

		entitymanager.close();
		emfactory.close();

		return users;
	}

	@GET
	public static Users getValueUsersByName(String name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Users e WHERE e.name = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
	
		Users list = (Users) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	@GET
	public static Users getValueUsersByFamily(String name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Users e WHERE e.family = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		Users user;
		@SuppressWarnings("unchecked")
		List<Users> list =  query.getResultList();
		if(list.size()>0){
		user = list.get(0);
		}else{
			user =getValueUsersById(1);
		}
		entitymanager.close();
		emfactory.close();

		return user;
	}
	
	@GET
	public static Users getValueUsersByNicName(String nic_name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Users e WHERE e.nikName = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", nic_name);
	
		Users list = (Users) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	@SuppressWarnings("unchecked")
	public static List<Users> getListUsersFromColumnByVolume(String column_name, Object volume_check) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Users e WHERE e." + column_name + " = :text ORDER BY e.name ASC";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", volume_check);

		List<Users> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	public static List<String> getListStringAllName_FamilyUsersByPost(Post post) {
		List<Users> list = new ArrayList<>();
		if(post==null){
			 list = getInListAllValueUsers();
		}else{
		 list = getListUsersFromColumnByVolume("post", post);
		}
		List<String> values = new ArrayList<>();
		for (Users user : list) {
			values. add(getStringName_FamilyUser(user));
		}
		return values;
	}
	
	public static String getStringName_FamilyUser(Users user){
		return user.getName_users() + " " + user.getFamily_users();
	}
}
