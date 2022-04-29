package WindowView;

import javax.swing.JOptionPane;

import GlobalVariable.ReadFileWithGlobalTextVariable;

public class DialogOption_Yes_No {
	public static boolean DialogOption_YesNo (String frame_Text, String Info_Text) {
		String[] options = {ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("No_Btn_Text"), 
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Yes_Btn_Text")};
        int x = JOptionPane.showOptionDialog(null, Info_Text, frame_Text,
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
       return x==1;
       
	}
}
