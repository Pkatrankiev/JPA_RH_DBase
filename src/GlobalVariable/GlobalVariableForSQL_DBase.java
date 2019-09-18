package GlobalVariable;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import WindowView.RequestView;

public class GlobalVariableForSQL_DBase {

static Boolean isRemoteDBase = true;
		
		private static Map<String, String>  createLokalDataBase() {
		
		Map<String, String> persistenceMap = new HashMap<String, String>();
		if(isRemoteDBase){
		persistenceMap.put("javax.persistence.jdbc.url", "jdbc:mysql://192.168.21.27:3306/rhdbase?characterEncoding=UTF-8");
//		persistenceMap.put("javax.persistence.jdbc.url", "jdbc:mysql://192.168.21.75:3306/rhdbase?characterEncoding=UTF-8");
		persistenceMap.put("javax.persistence.jdbc.user", "someuser");
		persistenceMap.put("javax.persistence.jdbc.password", "123");
		}else{
			persistenceMap.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/rhdbase?characterEncoding=UTF-8");
			persistenceMap.put("javax.persistence.jdbc.user", "root");
			persistenceMap.put("javax.persistence.jdbc.password", "root");
		}
		return persistenceMap;
	}
	

		public static EntityManagerFactory getDBase(){
			try{
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA_RH_DBase", createLokalDataBase());
			emfactory.createEntityManager();
		} catch (PersistenceException e) {

			JOptionPane.showMessageDialog(null, "�� �� �������� � ������ �����:");
		}		
			return Persistence.createEntityManagerFactory("JPA_RH_DBase", createLokalDataBase());
		}
		
		

}
