package Aplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbConnection {

	static String name_DBase = "JPA_RH_DBase";

	public static EntityManagerFactory DbEmfactory() {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		
		return emfactory;
	}

	
	public static EntityManager DbConnection() {

		EntityManager entitymanager = DbEmfactory().createEntityManager();
		entitymanager.getTransaction().begin();
		

		return entitymanager;
	}

}
