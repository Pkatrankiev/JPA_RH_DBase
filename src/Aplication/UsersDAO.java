package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Users;
import DBase_Class.Zabelejki;

public class UsersDAO {

	static String name_DBase = "JPA_RH_DBase";

	public static void setBasicValueUsers(){
	setValueUsers("Михаил", "Балачев", "123", 1);
	setValueUsers("Петър", "Катранкиев", "123", 1);
	setValueUsers("Мартин", "Илиев", "123", 2);
}
//	Users
	public static void setValueUsers(String name, String family, String pass, Integer priority) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Users valueEnt = new Users();
		valueEnt.setName_users(name);
		valueEnt.setFamily_users(family);
		valueEnt.setPass_users(pass);
		valueEnt.setPriority_users(priority);

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
					+ "\", \"" + ((Users) e).getFamily_users() +"\", \"" + ((Users) e).getPass_users()
					+ "\", " + ((Users) e).getPriority_users()+");");
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
