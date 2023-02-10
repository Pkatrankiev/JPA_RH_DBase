package Aplication;


import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import DBase_Class.Aplicant;
import GlobalVariable.GlobalPathForDocFile;
import GlobalVariable.GlobalVariableForSQL_DBase;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import GlobalVariable.ResourceLoader;
import WindowView.TranscluentWindow;

public class AplicantDAO {

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	
	static String errorOfData = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("attention");
	
	public static void setValueAplicant(String name, String family) {
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		Aplicant valueEnt = new Aplicant();
		valueEnt.setName_aplicant(name);
		valueEnt.setFamily_aplicant(family);

		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static void saveValueAplicantWitchCheck(String nameAndFamily) {
		String[] masive_AplicantNameFamily = getMasiveStringAllName_FamilyAplicant();
		Boolean fl_Aplicant = false;
		for (String string : masive_AplicantNameFamily) {
			if (string.equals(nameAndFamily)) {
				fl_Aplicant = true;
			}
		}
		if(!fl_Aplicant){
		setValueAplicant(nameAndFamily.substring(0, nameAndFamily.indexOf(" ")), nameAndFamily.substring(nameAndFamily.indexOf(" ")+1));
		}
	}
	
	@SuppressWarnings("unchecked")
	@GET
	public static List<Aplicant> getInListAllValueAplicant() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Aplicant e ORDER BY e.name ASC");
		List<Aplicant> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static String[] getMasiveStringAllName_FamilyAplicant() {
		List<Aplicant> list = getInListAllValueAplicant();
		String[] values = new String[list.size()];
		int i = 0;
		for (Aplicant user : list) {
			values[i] = user.getName_aplicant() + " " + user.getFamily_aplicant();
			i++;
		}
		return values;
	}

	@GET
	@QueryParam("{id}")
	public static Aplicant getValueAplicantById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		Aplicant aplicant = (Aplicant) entitymanager.find(Aplicant.class, id);

		entitymanager.close();
		emfactory.close();

		return aplicant;
	}

	@SuppressWarnings("unchecked")
	@GET
	public static List<Aplicant> getValueAplicantByName(String name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Aplicant e WHERE e.name = :text ORDER BY e.name ASC";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);

		List<Aplicant> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	@SuppressWarnings("unchecked")
	@GET
	public static List<Aplicant> getValueAplicantByFamily(String family) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Aplicant e WHERE e.family = :text ORDER BY e.family ASC";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", family);

		List<Aplicant> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static Aplicant getAplicantByNameFamily(String nameFamily) {
		for (Aplicant aplicant : getInListAllValueAplicant()) {
			if ((aplicant.getName_aplicant() + " " + aplicant.getFamily_aplicant()).equals(nameFamily)) {
				return aplicant;
			}
		}
		return null;
	}
	
	public static void backupDB_From_RemoteServer(TranscluentWindow round) {
		String text = "";
		Date backupDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy_HHmm");
		String backupDateStr = format.format(backupDate);

//		String PathToMySqlDumpFile = "c:\\xampp\\mysql\\bin\\";
		String PathToMySqlDumpFile = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("TEMPLATE_DIRECTORY_ROOT");
		String remoteDBase = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("remoteDBase");
		
		String HOSTIP = "192.168.21.27";
		String USER = "someuser";
		String PASS = "123";
		
		if(remoteDBase.equals("0")) {
			 HOSTIP = "localhost";
			 USER = "root";
			 PASS = "root";
		}
		String PORT = "3306";
		String database = "rhdbase";
		String path = "DB_backup_" + backupDateStr + ".sql";

		try {
			String executeCmd = PathToMySqlDumpFile + "mysqldump -h " + HOSTIP + " -P " + PORT + " -u " + USER + " -p"
					+ PASS + " " + database + " -r " + path;
			Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();
			if (processComplete == 0) {
				text = "<html>Архивирането зашърши успешно.<br>Името на архива е: "+path;
			} else {
				text = "Не можа да се направи резервно копие";
			}
			round.StopWindow();
			JOptionPane.showMessageDialog(null, text, errorOfData, JOptionPane.WARNING_MESSAGE);

		} catch (Exception e) {
			ResourceLoader.appendToFile(e);
			round.StopWindow();
			text = "Възникна проблем при архивирането";
			JOptionPane.showMessageDialog(null, text, errorOfData, JOptionPane.WARNING_MESSAGE);
			
			e.printStackTrace();
		}

	}
	
	public static void Restoredbfromsql() {
        try {
            /*NOTE: String s is the mysql file name including the .sql in its name*/
            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
//            CodeSource codeSource = YourImplementingClass.class.getProtectionDomain().getCodeSource();
//            File jarFile = new File(codeSource.getLocation().toURI().getPath());
//            String jarDir = jarFile.getParentFile().getPath();
//
//            /*NOTE: Creating Database Constraints*/
//             String dbName = "YourDBName";
//             String dbUser = "YourUserName";
//             String dbPass = "YourUserPassword";

        	
        	String PathToMySqlDumpFile = "TEMPLATES_DIRECTORY\\";
    		String remoteDBase = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("remoteDBase");
    		
    		String HOSTIP = "192.168.21.27";
    		String USER = "someuser";
    		String PASS = "123";
    		
    		if(remoteDBase.equals("0")) {
    			 HOSTIP = "localhost";
    			 USER = "root";
    			 PASS = "root1";
    		}
    		String PORT = "3306";
    		String database = "rhdbase";
    		String path = "DB_backup_21-08-22_.sql";
        	
        	System.out.println("1111111111111111111111111");
            /*NOTE: Creating Path Constraints for restoring*/
//            String restorePath = jarDir + "\\backup" + "\\" + s;

            /*NOTE: Used to create a cmd command*/
            /*NOTE: Do not create a single large string, this will cause buffer locking, use string array*/
//            String[] executeCmd = new String[]{"mysql", path, "-u" + USER, "-p" + PASS, "-e", " source " + restorePath};
//            String executeCmd =  "TEMPLATES_DIRECTORY\\mysql"+ " --user="  + USER + " --password="
//					+ PASS + " "+ database + " < "  + path;
            
            
        	String[] executeCmd = new String[]{"TEMPLATES_DIRECTORY\\mysql", "--user=root", " --password=root", " rhdbase <  ",
            		"D:\\JavaProjectEclipce\\JPA_RH_DBase\\TEMPLATES_DIRECTORY\\DB_backup_21-08-22_1539.sql"};
            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            System.out.println("22222222222222222222222222");
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            System.out.println("000000000000000000000");
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            System.out.println("333333333333333333333333333333");
            if (processComplete == 0) {
            	System.out.println("4444444444444444444444444444");
                JOptionPane.showMessageDialog(null, "Successfully restored from SQL : " );
            } else {
            	System.out.println("555555555555555555555555");
                JOptionPane.showMessageDialog(null, "Error at restoring");
            }


        } catch ( IOException | InterruptedException | HeadlessException ex) {
        	System.out.println("666666666666666666666666");
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
        }

    }
	
}
