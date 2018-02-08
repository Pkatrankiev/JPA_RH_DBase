package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Izpitvan_produkt;
import DBase_Class.Metody;
import DBase_Class.Sample;

public class MetodyDAO {

	static String name_DBase = "JPA_RH_DBase";

	public static void setBasikValueMetody() {
		
		setValueMetody(
				"нопедекъме мю яздзпфюмхерн мю юктю-хгкзвбюых пюдхнмсйкхдх х 89/90Sr бзб бндх х цнпхлх люрепхюкх",
				"M.кх-пу-03 пЕДЮЙЖХЪ 02/2014");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю рпхрхи", "л.кх-пу-03, пЕДЮЙЖХЪ 03/2017");
		setValueMetody("йюкхапхпюме мю ревмняжхмрхкюжхнмем яоейрпнлерзп", "л.кх-пу √ 04 педюйжхъ 03");
		setValueMetody("йюкхапхпюме мю юктю-яоейрпнлерпхвмю яхярелю", "л.кх-пу √ 05 педюйжхъ 02");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю рпхрхи бзб бндх", "л.кх-пу √ 03 педюйжхъ 02");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю 241Pu якед юктю-яоейрпнлерпхъ", "л.кх-пу √ 07 педюйжхъ 02");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю юктю-хгкзвбюых пюдхнмсйкхдх б пюгкхвмх люрпхжх",
				"M.кх-пу-08 пЕДЮЙЖХЪ 02/2014");
		setValueMetody(
				"нопедекъме мю етейрхбмняррю мю пецхярпхпюме мю пюдхнмсйкхдхре 89SR, 90SR х 90Y опх вепемйнбн апнеме",
				"л.кх-пу √ 09 педюйжхъ 02");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю цюлю-хгкзвбюых пюдхнмсйкхдх", "л.кх-пу-10 пЕДЮЙЖХЪ 02/2014");
		setValueMetody("йюкхапхпюме мю цюлю-яоейрпнлерпхвмю яхярелю", "л.кх-пу √ 11 педюйжхъ 02");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю 129I впег мхяйнемепцхимю цюлю-яоейрпнлерпхъ",
				"л.кх-пу-12, пЕДЮЙЖХЪ 01/2013");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю 232Th х 237Np бзб бндх", "л.кх-пу √ 13 педюйжхъ 01");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю 99рЯ б пюгкхвмх люрпхжх", "л.кх-пу √ 14 педюйжхъ 03");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю 14C б пюгкхвмх люрпхжх", "л.кх-пу √ 15 педюйжхъ 02");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю 14C б пюгкхвмх люрпхжх", "л.кх-пу-15, пЕДЮЙЖХЪ 03/2017");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю 55Fe, 63Ni, 89/90Sr х 241Pu б пюгкхвмх люрпхжх",
				"M.кх-пу-16 пЕДЮЙЖХЪ 03/2014");
		setValueMetody("йюкхапхпюме мю юктю-яоейрпнлерпхвмю яхярелю CANBERRA ALPHA ANALYST",
				"л.кх-пу √ 21 педюйжхъ 01");

	}

	// Metody

	public static void setValueMetody(String name, String code) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Metody valueEnt = new Metody();
		valueEnt.setName_metody(name);
		valueEnt.setCode_metody(code);
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Metody> getInListAllValueMetody() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Metody e");
		List<Metody> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static Metody getValueMetodyById(@QueryParam("id") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Metody metody = (Metody) entitymanager.find(Metody.class, id);

		entitymanager.close();
		emfactory.close();

		return metody;
	}

	@GET
	public static Metody getValueList_MetodyByName(String name) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Metody e WHERE e.code = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		if (query.getResultList().isEmpty()) {
			setValueMetody("uknou", name);
		}
		Metody list = (Metody) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}


}
