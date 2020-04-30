package GlobalVariable;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GlobalVariableForSQL_DBase {

	static Boolean isRemoteDBase = true;

	private static Map<String, String> globalTextVariableMap = ReadFileWithGlobalTextVariable
			.getGlobalTextVariableMap();
	static String dataBaseName = globalTextVariableMap.get("dataBaseName");
	static String noConectionInDBseMesage = globalTextVariableMap.get("noConectionInDBseMesage");

	private static Map<String, String> createLokalDataBase() {

		Map<String, String> persistenceMap = new HashMap<String, String>();
		if (isRemoteDBase) {
			persistenceMap = ReadFileWithGlobalTextVariable.getGlobalDBasePersisRemoteMap();
			
		} else {
			persistenceMap = ReadFileWithGlobalTextVariable.getGlobalDBasePersisLokalMap();

		}
		return persistenceMap;
	}

	public static EntityManagerFactory getDBase() {
		try {

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(dataBaseName,
					createLokalDataBase());
			emfactory.createEntityManager();
		} catch (PersistenceException e) {
			JFrame jf = new JFrame();
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, noConectionInDBseMesage);
			System.exit(0);
		}
		return Persistence.createEntityManagerFactory(dataBaseName, createLokalDataBase());
	}

}
