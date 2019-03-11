package Aplication;

public class GlobalVariable {
	
	
	private static String separator = ".";
	
	private static String DB_FORMAT_DATE = "dd-MM-yyyy";
	private static String DB_FORMAT_DATE_TIME ="dd-MM-yyyy HH:mm";
	private static String FORMAT_DATE = "dd-MM-yyyy";
	private static String FORMAT_DATE_TIME ="dd-MM-yyyy HH:mm";
	private static String TAB_FORMAT_DATE = "yyyy-MM-dd";
	private static String TAB_FORMAT_DATE_TIME ="yyyy-MM-dd HH:mm";
	private static String DOC_FORMAT_DATE_TIME ="dd.MM.yyyy / HH:mm";
	
	public static String getFORMAT_DATE() {
		return FORMAT_DATE.replaceAll("-", separator);
	}
	public static String getFORMAT_DATE_TIME() {
		return FORMAT_DATE_TIME.replaceAll("-", separator);
	}
	public static String getTAB_FORMAT_DATE() {
		return TAB_FORMAT_DATE.replaceAll("-", separator);
	}
	public static String getTAB_FORMAT_DATE_TIME() {
		return TAB_FORMAT_DATE_TIME.replaceAll("-", separator);
	}
	public static String getDB_FORMAT_DATE() {
		return DB_FORMAT_DATE;
	}
	public static String getDOC_FORMAT_DATE_TIME() {
		return DOC_FORMAT_DATE_TIME;
	}
	public static String getDB_FORMAT_DATE_TIME() {
		return DB_FORMAT_DATE_TIME;
	}
	
	public static String getSeparator() {
		return separator;
	}
	
	
}
