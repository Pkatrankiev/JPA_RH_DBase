package WindowView;

import javax.swing.JTable;

public class SetDBfromWordDoc {

	public static void setVolume(String fileName) {
		
	
	String celsTranfer[][][] = ReaderWordDoc.readMyDocument(fileName);
	 System.out.println("broy tab "+celsTranfer.length);
	 System.out.println("Broy Raw "+celsTranfer[0].length);
	 System.out.println("Broy coll "+celsTranfer[0][0].length);
	 String cellVolume;
	 
	 String[] columnNames = null; 
	 Object[][] data;
	 
	 for (int tab = 0; tab < celsTranfer.length; tab++) {
		 
		 for (int row = 0; row < celsTranfer[0].length; row++) {
			 
			 for (int coll = 0; coll < celsTranfer[0][0].length; coll++) {
				 
				 cellVolume = celsTranfer[tab][row][coll];
				if (row==0){
					 columnNames[coll] = cellVolume;
				} data
				 
				 
				 /** is RECUEST_CODE in RECUEST Class**/
//				 Search_RECUEST_CODE(cellVolume);
				 
				 
				 /** is ACCREDITATION in RECUEST Class**/
//				 Search_ACCREDITATION(cellVolume);
			}
			
		}
		
	}
	
	}

	private static void Search_ACCREDITATION(String cellVolume) {
		System.out.println("cellVolume  "+cellVolume);
		 Boolean accreditation=false;
		 cellVolume.replaceAll(" ", "");
		 if(cellVolume.startsWith("Сертификат")){
			 accreditation=true;
				}
		 System.out.println("ACCREDITATION "+ accreditation);
	}

	private static void Search_RECUEST_CODE(String cellVolume) {
		cellVolume.replaceAll(" ", "");
		 String recuest_code = "";
		 if(cellVolume.startsWith("№")){
			
			recuest_code = cellVolume.substring(1,5);
		}
		 System.out.println("RECUEST_CODE "+ recuest_code);
	}
}
