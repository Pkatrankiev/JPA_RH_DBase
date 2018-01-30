package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Post;
import DBase_Class.Users;
import DBase_Class.Zabelejki;

public class UsersDAO {

	static String name_DBase = "JPA_RH_DBase";

	public static void setBasicValueUsers(){
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		
		setValueUsers("Непознат", "Непознат", "123", "123", false, PostDAO.getValuePostById(1));	
	setValueUsers("Михаил", "Балачев", "mbalachev", "123", false, PostDAO.getValuePostById(2));
	setValueUsers("Петър", "Катранкиев","pkatrankiev", "123", false, PostDAO.getValuePostById(2));
	setValueUsers("Мартин", "Илиев", "miliev", "123", true, PostDAO.getValuePostById(4));
}
//	Users
	public static void setValueUsers(String name, String family, String nikName, String pass, Boolean isAdmin, Post post) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
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

	public static List<Users> getInListAllValueUsers() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Users e");
		List<Users> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		
		
		for (Users e : list) {
			
			System.out.println("setValueUsers(\"" + ((Users) e).getId_users() + "\", \"" + ((Users) e).getName_users()
					+ "\", \"" + ((Users) e).getFamily_users() +"\", \"" + ((Users) e).getNikName_users()+"\", \"" + ((Users) e).getPass_users()
					+ "\", " + ((Users) e).getIsAdmin()+");");
		}

//		for (Users e : list) {
//			System.out.println("Num:" + ((Users) e).getId_users() + "  NAME :" + ((Users) e).getName_users()
//					+ "  Family :" + ((Users) e).getFamily_users() + "  Pass :" + ((Users) e).getPass_users()
//					+ "  Prior :" + ((Users) e).getPriority_users());
//		}
		return list;
	}


	@GET
	@QueryParam("{id}")
	public static Users getValueUsersById(@QueryParam("id") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Users users = (Users) entitymanager.find(Users.class, id);

		entitymanager.close();
		emfactory.close();

		return users;
	}

}
