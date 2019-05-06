package GlobalVariable;

public class GlobalPathForIcons {
	
	private static final String destinationIcons = "ICONS/";
	private static final String modifiIcon = "Modify.gif";
	private static final String addIcon = "add-icon.gif";
	private static final String SaveIcon = "Save.gif";
	private static final String OpenIcon = "Open.gif";
	private static final String winIcon = "winIcon.png";
	private static final String ajaxLoader = "ajaxLoader.gif";
	
	public static String get_destination_addIcon() {
		return destinationIcons+ addIcon;
	}
	public static String get_destination_SaveIcon() {
		return destinationIcons+ SaveIcon;
	}
	
	public static String get_destination_OpenIcon() {
		return destinationIcons+ OpenIcon;
	}
	
	public static String get_destination_winIcon() {
		return destinationIcons+ winIcon;
	}
	public static String get_destination_ajaxLoader() {
		return destinationIcons+ ajaxLoader;
	}
	public static String get_destination_ModifyIcon() {
		return destinationIcons+ modifiIcon;
	}
}
