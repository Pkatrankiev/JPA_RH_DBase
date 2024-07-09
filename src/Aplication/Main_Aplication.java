package Aplication;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JFrame;

import org.apache.commons.io.FileUtils;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.MainWindow;
import WindowView.RequestView;
import WindowView.TranscluentWindow;

public class Main_Aplication {
	 
	public static void main(String[] args) {
	
		ReadFileWithGlobalTextVariable.CreadMasiveFromReadFile();
		
		final TranscluentWindow round = new TranscluentWindow();
		
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	
		    		 String version = "100624";
		    		version = compareByDateJarFileAndInputVersion(version);
		    	 new MainWindow(round, version);
	    		 
		    		
		     }
		    });
		    thread.start();
		
	}
	
	@SuppressWarnings("rawtypes")
	public static String  compareByDateJarFileAndInputVersion(String Mainversion) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		Date jarVersion = null, datMainVersion = null;
		File file = null;
		String path = "";
		try {
			datMainVersion = sdf.parse(Mainversion);
			 boolean recursive = true;
			 String fileName = "RH_DBase.jar";
			 File f = new File(".");
		 if(f.exists()) {
				 File root = new File(f.getAbsolutePath());
	            Collection files = FileUtils.listFiles(root, null, recursive);

	            for (Iterator iterator = files.iterator(); iterator.hasNext();) {
	            	 
	                file = (File) iterator.next();
//	                System.out.println(file.getName());
	                if (file.getName().equals(fileName)) {
	                	path = file.getAbsolutePath().replace(".\\","");
	                    System.out.println(path);
	                }
	            }
		}
			
		File fis = new File(path);
		long lastModifiedFile = fis.lastModified();
		String stringday = sdf.format(lastModifiedFile);
		jarVersion = sdf.parse(stringday);
		if(jarVersion.after(datMainVersion)) {
			return stringday;
		}

		} catch (ParseException e) {
		e.printStackTrace();
			return Mainversion;
		}
		return Mainversion;
		
	}
		
	
}



	


