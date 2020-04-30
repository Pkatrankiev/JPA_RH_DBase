package GlobalVariable;

import java.util.Map;

public class GlobalPathForDocFile {
	
	private static 	Map<String, String> globalTextVariableMap = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap();
	private static 	Map<String, String> globalIntVariableMap = ReadFileWithGlobalTextVariable.getGlobalIntVariableMap();
	
	private static final String TEMPLATE_DIRECTORY_ROOT = globalTextVariableMap.get("TEMPLATE_DIRECTORY_ROOT");
	private static final String destinationDir = globalTextVariableMap.get("destinationDir");
	private static final String destinationDir_Protocols = globalTextVariableMap.get("destinationDir_Protocols");
	
	public static final int maxRowInOneTableOnePage =  Integer.parseInt(globalIntVariableMap.get("maxRowInOneTableOnePage"));
	public static final int maxRowInFullTableOnePage = Integer.parseInt(globalIntVariableMap.get("maxRowInFullTableOnePage"));
	private static final Integer[] masiveMergeColumn = {Integer.parseInt(globalIntVariableMap.get("masiveMergeColumn-1")),
														Integer.parseInt(globalIntVariableMap.get("masiveMergeColumn-2")),
														Integer.parseInt(globalIntVariableMap.get("masiveMergeColumn-3"))
														};
	private static final String name_tamplate_Protocols = globalTextVariableMap.get("name_tamplate_Protocols");
	private static final String text_dopalneniq_izklucheniq = globalTextVariableMap.get("text_dopalneniq_izklucheniq");
	private static final String name_tamplate_Request = globalTextVariableMap.get("name_tamplate_Request");
	private static final String name_tamplate_RazpredForm = globalTextVariableMap.get("name_tamplate_RazpredForm");
	
	

	
	
	
	
//	private static final String TEMPLATE_DIRECTORY_ROOT = "TEMPLATES_DIRECTORY/";
//	private static final String destinationDir = "DIRECTORY/";
//	private static final String destinationDir_Protocols = "l:/ЛИ-РХ/Протоколи/";
//	public static final int maxRowInOneTableOnePage = 24;
//	public static final int maxRowInFullTableOnePage = 32;
//	private static final Integer[] masiveMergeColumn = {0,1,4};
//	private static final String name_tamplate_Protocols = "Protokol_NEW.docx";
//	private static final String text_dopalneniq_izklucheniq = "____________________";
//	private static final String name_tamplate_Request = "Zaqvka_new.docx";
//	private static final String name_tamplate_RazpredForm = "Razpred.docx";
	
//	public static int maxRowInOneTableOnePage = 13;
//	public static int maxRowInFullTableOnePage = 25;
//	private static final Integer[] masiveMergeColumn = {0,1,3};
//	private static final String name_tamplate_Protocols = "Protokol.docx";
	
	public static String get_TEMPLATE_DIRECTORY_ROOT() {
		return TEMPLATE_DIRECTORY_ROOT;
	}

	public static String get_destinationDir() {
		return destinationDir;
	}

	public static String get_destinationDir_Protocols() {
		return destinationDir_Protocols;
	}
	
	public static String get_NameTamplate_Protokol() {
		return name_tamplate_Protocols;
	}
	
	public static String get_NameTamplate_Request() {
		return name_tamplate_Request;
	}
	
	public static String get_NameTamplate_RazpredForm() {
		return name_tamplate_RazpredForm;
	}

	public static int getMaxRowInOneTableOnePage() {
		return maxRowInOneTableOnePage;
	}

	public static int getMaxRowInFullTableOnePage() {
		return maxRowInFullTableOnePage;
	}

	public static Integer[] getMasivemergecolumn() {
		return masiveMergeColumn;
	}

	public static String getTextDopalneniqIzklucheniq() {
		return text_dopalneniq_izklucheniq;
	}
	
	
}
