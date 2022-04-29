package OldClases;

import java.io.IOException;

public class extractDBAse {
	
	public static void extract_DBAse() {
	
	/******************************************************/
	//Database Properties
	/******************************************************/
	String dbName ="JPA_RH_DBase";
	String dbUser = "root";
	String dbPass = "root";

	/***********************************************************/
	// Execute Shell Command
	/***********************************************************/
	String executeCmd = "";
	executeCmd = "mysqldump -u "+dbUser+" -p"+dbPass+" "+dbName+" -r backup.sql";
	
	
	try {
		Process runtimeProcess;
		runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	
	int processComplete = runtimeProcess.waitFor();
	if(processComplete == 0){

	System.out.println("Backup taken successfully");

	} else {

		System.out.println("Could not take mysql backup");

	}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	
	
}
