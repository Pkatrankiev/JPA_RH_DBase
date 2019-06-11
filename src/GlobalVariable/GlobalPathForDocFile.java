package GlobalVariable;

public class GlobalPathForDocFile {
	
	private static final String TEMPLATE_DIRECTORY_ROOT = "TEMPLATES_DIRECTORY/";
	private static final String destinationDir = "DIRECTORY/";
	private static final String destinationDir_Protocols = "l:/ЛИ-РХ/Протоколи/";
	private static final String name_tamplate_Protocols = "Protokol.docx";
	private static final String name_tamplate_Request = "Zaqvka_new.docx";
	private static final String name_tamplate_RazpredForm = "Razpred.docx";
	
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
}
