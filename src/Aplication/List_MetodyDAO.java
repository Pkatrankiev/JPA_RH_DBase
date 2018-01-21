package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_Metody;
import DBase_Class.Sample;

public class List_MetodyDAO {

	static String name_DBase = "JPA_RH_DBase";

	public static void setBasikValueMetody(){
		setValueMetody("нопедекъме мю яздзпфюмхерн мю юктю-хгкзвбюых пюдхнмсйкхдх х 89/90Sr бзб бндх х цнпхлх люрепхюкх", "л.кх-пу √ 01 педюйжхъ 02");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю рпхрхи", "л.кх-пу √ 03 педюйжхъ 03");
		setValueMetody("йюкхапхпюме мю ревмняжхмрхкюжхнмем яоейрпнлерзп", "л.кх-пу √ 04 педюйжхъ 03");
		setValueMetody("йюкхапхпюме мю юктю-яоейрпнлерпхвмю яхярелю", "л.кх-пу √ 05 педюйжхъ 02");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю рпхрхи бзб бндх", "л.кх-пу √ 03 педюйжхъ 02");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю 241Pu якед юктю-яоейрпнлерпхъ", "л.кх-пу √ 07 педюйжхъ 02");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю юктю-хгкзвбюых пюдхнмсйкхдх б пюгкхвмх люрпхжх", "л.кх-пу √ 08 педюйжхъ 02");
		setValueMetody("нопедекъме мю етейрхбмняррю мю пецхярпхпюме мю пюдхнмсйкхдхре 89SR, 90SR х 90Y опх вепемйнбн апнеме", "л.кх-пу √ 09 педюйжхъ 02");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю цюлю-хгкзвбюых пюдхнмсйкхдх", "л.кх-пу √ 10 педюйжхъ 02");
		setValueMetody("йюкхапхпюме мю цюлю-яоейрпнлерпхвмю яхярелю", "л.кх-пу √ 11 педюйжхъ 02");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю 129I впег мхяйнемепцхимю цюлю-яоейрпнлерпхъ", "л.кх-пу √ 12 педюйжхъ 01");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю 232Th х 237Np бзб бндх", "л.кх-пу √ 13 педюйжхъ 01");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю 99рЯ б пюгкхвмх люрпхжх", "л.кх-пу √ 14 педюйжхъ 03");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю 14C б пюгкхвмх люрпхжх", "л.кх-пу √ 15 педюйжхъ 02");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю 14C б пюгкхвмх люрпхжх", "л.кх-пу √ 15 педюйжхъ 03");
		setValueMetody("нопедекъме мю яздзпфюмхерн мю 55Fe, 63Ni, 89/90Sr х 241Pu б пюгкхвмх люрпхжх", "л.кх-пу √ 16 педюйжхъ 03");
		setValueMetody("йюкхапхпюме мю юктю-яоейрпнлерпхвмю яхярелю CANBERRA ALPHA ANALYST", "л.кх-пу √ 21 педюйжхъ 01");

		
	}
	
//	Metody
	
	public static void setValueMetody(String value, String code) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		List_Metody valueEnt = new List_Metody();
		valueEnt.setName_metody(value);
		valueEnt.setCode_metody(code);
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<List_Metody> getInListAllValueMetody() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Metody e");
		List<List_Metody> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

//		for (Metody e : list) {
//			System.out.println("setValueMetody(\"" + ((Metody) e).getName_metody()
//					+"\", \"" + ((Metody) e).getCode_metody()+"\");");
//		}
		
		
//		for (Metody e : list) {
//			System.out.println("Num:" + ((Metody) e).getId_metody() + "  NAME :" + ((Metody) e).getName_metody()
//					+ "  Kode :" + ((Metody) e).getCode_metody());
//		}
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static List_Metody getValueMetodyById(@QueryParam("id") int id) {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
	EntityManager entitymanager = emfactory.createEntityManager();
	entitymanager.getTransaction().begin();
	List_Metody  metody = (List_Metody) entitymanager.find(List_Metody.class, id);
	
	entitymanager.close();
	emfactory.close();

	return metody;
}
	
	@GET
	public static List_Metody getValueList_MetodyByName(String name) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM List_Metody e WHERE e.code = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		if (query.getResultList().isEmpty()){
			setValueMetody("uknou", name);	
		}
		List_Metody list = (List_Metody) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}	
}
