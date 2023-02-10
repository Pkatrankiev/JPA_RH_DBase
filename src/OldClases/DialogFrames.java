package OldClases;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DialogFrames {
	
	
	public static void MessageDialog() {
		Icon otherIcon = null;
		JFrame jf = new JFrame();
		jf.setAlwaysOnTop(true);
		/**	
		 * 	
		Component, Object
		Component, Object, String, int
		Component, Object, String,  int, Icon
		
		Component - frame or null;
		Object - object show in frame;
		String - name same frame; 
		int - JOptionPane types
								ERROR_MESSAGE 	
								INFORMATION_MESSAGE 
								WARNING_MESSAGE 
								QUESTION_MESSAGE 
								PLAIN_MESSAGE
		Icon - set other icon - not Default
		
		*/
		JOptionPane.showMessageDialog(jf, "text in frame","text frame", JOptionPane.PLAIN_MESSAGE, otherIcon);
		
	}
		
	
	public static void OptionDialog() {
		String[] options = {"abc", "def", "ghi", "jkl"};
        //Integer[] options = {1, 3, 5, 7, 9, 11};
        //Double[] options = {3.141, 1.618};
        //Character[] options = {'a', 'b', 'c', 'd'};
        int x = JOptionPane.showOptionDialog(null, "Returns the position of your choice on the array",
                "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        System.out.println(x);
        /**
         * select "abc" -> 0;
         * select "def" -> 1;
         * select "ghi" -> 2;
         * select "jkl" -> 3;
         */
	}

	public static void OptionDialog2() {
		JCheckBox check = new JCheckBox("Tick me");
        Object[] options = {'e', 2, 3.14, 4, 5, "TURTLES!", check};
        int x = JOptionPane.showOptionDialog(null, "So many options using Object[]",
                "Don't forget to Tick it!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);

        if (check.isSelected() && x != -1) {
            System.out.println("Your choice was " + options[x]);
        } else {
            System.out.println(":( no choice");
        }
	}

	
	public static void ConfirmDialog() {
			JFrame jf = new JFrame();
			jf.setAlwaysOnTop(true);
			/**	
			Component, Object
 			Component, Object, String, int
 			Component, Object, String, int, int
 			Component, Object, String, int, int, Icon
			
			Component - frame or null;
			Object - object show in frame;
			String - name same frame; 
			int - JOptionPane types	DEFAULT_OPTION
									YES_NO_OPTION
									YES_NO_CANCEL_OPTION
									OK_CANCEL_OPTION
			int - Message type  ERROR_MESSAGE
								INFORMATION_MESSAGE
								WARNING_MESSAGE
								QUESTION_MESSAGE
								PLAIN_MESSAGE					
									
			Icon - set other icon - not Default
			*/
			 int input = JOptionPane.showConfirmDialog(jf, "text in frame","text frame",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			// 0=yes, 1=no, 2=cancel
			 System.out.println(input);
		}

	public static void InputDialog() {
		JFrame jf = new JFrame();
		jf.setAlwaysOnTop(true);
		/**	
		 * 	
		Object (returns String) – Shows a question-message dialog requesting input from the user.
		Object, Object (returns String) – Shows a question-message dialog requesting 
										input from the user with the input value initialized.
		Component, Object (returns String) – Shows a question-message dialog requesting 
											input from the user. Returns the input as String. 
											The Component determines the Frame in which the dialog 
											is displayed; if null, or if the parentComponent has no 
											Frame, a default Frame is used.
		Component, Object, Object (returns String) – Same as above. The only difference is that the 
											input field has an initial value set through the last 
											Object parameter.
		Component, Object, String, int (returns String) – Shows a dialog requesting 	
											input from the user. The dialog has a title 
											set through the String parameter and a MessageType 
											set through the int parameter. The different MessageTypes 
											for JOptionPane, are:
																ERROR_MESSAGE
																INFORMATION_MESSAGE
																WARNING_MESSAGE
																QUESTION_MESSAGE
																PLAIN_MESSAGE
		Component, Object, String, int, Icon, Object[], Object (returns Object) – Prompts the user for 
												input in a blocking dialog where the initial selection, 
												possible selections, and all other options can be specified. 
												The Icon (if not null) is displayed inside the dialog and
												overrides the default MessageType icon.
		
		*/
		 String[] options = {"I adore turtles", "Yes", "Maybe", "Urm...", "No", "Hate them"};
	        ImageIcon icon = new ImageIcon("src/images/turtle32.png");
	        String n = (String)JOptionPane.showInputDialog(null, "Do you like turtles??",
	                "I like turtles", JOptionPane.QUESTION_MESSAGE, icon, options, options[2]);
	        System.out.println(n);
	}
	
	
}
