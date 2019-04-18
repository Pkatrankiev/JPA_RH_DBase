package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Post;

public class PostDAO {

	static String name_DBase = "JPA_RH_DBase";

	public static void setBasikValuePost() {
		setValuePost("--");
		setValuePost("Œ»–");
		setValuePost("Œ–’Œ");
		setValuePost("ŒÃŒ");

	}

	// Pokazatel
	public static void setValuePost(String value) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Post post = new Post();
		post.setName_post(value);
		entitymanager.persist(post);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	@SuppressWarnings("unchecked")
	public static List<Post> getInListAllValuePost() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Post e");
		List<Post> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		for (Post e : list) {
			System.out.println("Num:" + ((Post) e).getId_post() + "  NAME :" + ((Post) e).getName_post());
		}
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static Post getValuePostById(@QueryParam("id") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Post post = (Post) entitymanager.find(Post.class, id);

		entitymanager.close();
		emfactory.close();

		return post;
	}

	@GET
	public static Post getValuePostByName(String name) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Post e WHERE e.name = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		if (query.getResultList().isEmpty()){
			setValuePost(name);	
		}
		Post list = (Post) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}
}