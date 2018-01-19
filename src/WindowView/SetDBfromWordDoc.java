package WindowView;

import javax.swing.JTable;

public class SetDBfromWordDoc {

	public static void setVolume(String fileName) {
		
	
	String celsTranfer[][][] = ReaderWordDoc.readMyDocument(fileName);
	 System.out.println("broy tab "+celsTranfer.length);
	 System.out.println("Broy Raw "+celsTranfer[0].length);
	 System.out.println("Broy coll "+celsTranfer[0][0].length);
	 String cellVolume;
	 
	 
	 
	 for (int tab = 0; tab < celsTranfer.length; tab++) {
		 
		 for (int row = 0; row < celsTranfer[0].length; row++) {
			 
			 for (int coll = 0; coll < celsTranfer[0][0].length; coll++) {
				 
				 cellVolume = celsTranfer[tab][row][coll];
				
				 /** is RECUEST_CODE in RECUEST Class**/
				 cellVolume.replaceAll(" ", "");
				 String recuest_code = "";
				 if(cellVolume.startsWith("№")){
					
					recuest_code = cellVolume.substring(1,5);
				}
				 System.out.println("RECUEST_CODE "+ recuest_code);
				 
				 
				 /** is ACCREDITATION in RECUEST Class**/
				 System.out.println("cellVolume  "+cellVolume);
				 Boolean accreditation=false;
				 cellVolume.replaceAll(" ", "");
				 if(cellVolume.startsWith("Сертификат")){
					 accreditation=true;
						}
				 System.out.println("ACCREDITATION "+ accreditation);
			}
			
		}
		
	}
	
	}
}
