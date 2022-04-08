package GlobalVariable;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.persistence.exceptions.DatabaseException;


public class GlobalVariableForSQL_DBase {

	static Boolean isRemoteDBase =false;

	private static Map<String, String> globalTextVariableMap = ReadFileWithGlobalTextVariable
			.getGlobalTextVariableMap();
	static String dataBaseName = globalTextVariableMap.get("dataBaseName");
	static String noConectionInDBseMesage = globalTextVariableMap.get("noConectionInDBseMesage_textMesage");

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
		EntityManagerFactory emfactory = null;
		try {

		emfactory = Persistence.createEntityManagerFactory(dataBaseName,
					createLokalDataBase());
//			emfactory.createEntityManager();
		} catch (PersistenceException| DatabaseException e) {
			NoConectionInDBaseDialog();
		}
		return emfactory;
	}
	
	public static EntityManager getEntityManagerDBase(EntityManagerFactory emfactory) {
		EntityManager entitymanager = null;
		try {
			entitymanager = emfactory.createEntityManager();
			
//			emfactory.createEntityManager();
		} catch (Exception e) {
			NoConectionInDBaseDialog();
		}
//		if(UsersDAO.getInListAllValueUsers().isEmpty()){
//			NoConectionInDBaseDialog();
//		}
		return entitymanager;
	}

	public static void NoConectionInDBaseDialog() {
		JFrame jf = new JFrame();
		jf.setAlwaysOnTop(true);
		
        

        String[] options = {globalTextVariableMap.get("noConectionInDBaseDialog_BTN_Exit"), 
				globalTextVariableMap.get("noConectionInDBaseDialog_BTN_Âaiting")};
		 
        int x = JOptionPane.showOptionDialog(jf, noConectionInDBseMesage,"",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        System.out.println(x);
       if(x==0 || x==-1){
		System.exit(0);
       }
        
//		JOptionPane.showMessageDialog(jf, noConectionInDBseMesage);
//		System.exit(0);
	}

}
