package ManagementBasicClassTable;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.*;

public class HelpWindow extends JDialog {
	
	private static final long serialVersionUID = 1L;

	public HelpWindow(JFrame parent, String labelString, String text) {
		
		super(parent, labelString, true);
				
		
    try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (Exception evt) {}
  
    final JDialog f = new JDialog();
    
    // Create the StyleContext, the document and the pane
    StyleContext sc = new StyleContext();
    final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
    final JTextPane pane = new JTextPane(doc);
    
    // Create and add the main document style
    Style defaultStyle = sc.getStyle(StyleContext.DEFAULT_STYLE);
    final Style mainStyle = sc.addStyle("MainStyle", defaultStyle);
    StyleConstants.setLeftIndent(mainStyle, 16);
    StyleConstants.setRightIndent(mainStyle, 16);
    StyleConstants.setFirstLineIndent(mainStyle, 16);
    StyleConstants.setFontFamily(mainStyle, "Times New Roman");
    StyleConstants.setFontSize(mainStyle, 16);

    // Create and add the constant width style
    final Style boldStyle = sc.addStyle("Heading2", null);
    StyleConstants.setForeground(boldStyle, Color.black);
    StyleConstants.setFontSize(boldStyle, 16);
    StyleConstants.setFontFamily(boldStyle, "Times New Roman");
    StyleConstants.setBold(boldStyle, true);
    StyleConstants.setLeftIndent(boldStyle, 8);
    StyleConstants.setFirstLineIndent(boldStyle, 0);
           
    
    Integer[][] masive = extracMasiveDubleForBoldSimbols(text);
    
    text = text.replace("#","").replace("&","").replace("$","\n");
    try {
            // Set the logical style
            doc.setLogicalStyle(0, mainStyle);

            // Add the text to the document
            doc.insertString(0, text, null);

            // Apply the character attributes
            for (int i = 0; i < masive.length; i++) {
			 doc.setCharacterAttributes(masive[i][0], masive[i][1], boldStyle, false);
            }

            // Set the foreground color and change the font
            pane.setForeground(Color.black);
            pane.setFont(new Font("Times New Roman", Font.PLAIN, 24));
          } catch (BadLocationException e) {
          }
    pane.setCaretPosition(0);
    

    getContentPane().add(new JScrollPane(pane));
    setSize(700, 300);
    setLocationRelativeTo(null);
    f.setLocationRelativeTo(parent);
    setVisible(true);


   
	}


	private Integer[][] extracMasiveDubleForBoldSimbols(String text) {
		ArrayList<String> list = new ArrayList<String>();
		int start, end;
		
   do {
		start = text.indexOf("#");
		if(start>0) {
		text = text.replaceFirst("#","");
		
		}
		end = text.indexOf("&");
		if(end>0) {
		text = text.replaceFirst("&","");
		
		}
		if(start>0 && end >0) {
			list.add(start+","+(end-start));
			
		}
   } while (end>0); 
		
  Integer[][]  masive = new Integer[list.size()][2];
   int k = 0;
  for (String strings : list) {
			String[] dub = strings.split(",");
			masive[k][0] = Integer.parseInt(dub[0]);
			masive[k][1] = Integer.parseInt(dub[1]);
			k++;
		}
		return masive;
	}


		

}
