package OldClases;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBAutoBackupController {
	
	public void schedule() {

		System.out.println("Backup Started at " + new Date());
		
		Date backupDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String backupDateStr = format.format(backupDate);
		String dbNameList = "rhdbase";

		String fileName = "Daily_DB_Backup"; // default file name
		String folderPath = "D:\\home";
		File f1 = new File(folderPath);
		f1.mkdir(); // create folder if not exist

		String saveFileName = fileName + "_" + backupDateStr + ".sql";
		String savePath = folderPath + File.separator + saveFileName;

		String executeCmd = "mysqldump -u " + "root" + " -p" + "root" + "  --databases " + dbNameList
				+ " -r " + savePath; 

		Process runtimeProcess = null;
		try {
			runtimeProcess = Runtime.getRuntime().exec(executeCmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int processComplete = 0;
		try {
			processComplete = runtimeProcess.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (processComplete == 0) {
			System.out.println("Backup Complete at " + new Date());
		} else {
			System.out.println("Backup Failure");
		}
	}
}
