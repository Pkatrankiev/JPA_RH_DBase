package WindowView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.ScrollPane;
import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;

public class RequestView extends JFrame {
	
	JScrollPane scrollpane;

	  public RequestView() {
	    super("JScrollPane Demonstration");
	    setSize(600, 400);
//	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    init();
	    setVisible(true);
	  }

	public void init() {
	  
	    JPanel p = new JPanel();
	    p.setSize(600, 400);
	    
	    scrollpane = new JScrollPane(p);
	    GridBagLayout gbl_p = new GridBagLayout();
	    gbl_p.columnWidths = new int[]{582, 0};
	    gbl_p.rowHeights = new int[]{104, 33, 23, 0};
	    gbl_p.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	    gbl_p.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	    p.setLayout(gbl_p);
//	    "<html>First line<br>Second line</html>"


String text = "<html>ƒ⁄–∆¿¬ÕŒ œ–≈ƒœ–»ﬂ“»≈ ì–¿ƒ»Œ¿ “»¬Õ» Œ“œ¿ƒ⁄÷»ì<br> À¿¡Œ–¿“Œ–»ﬂ «¿ »«œ»“¬¿Õ≈<br><br><br>"+
		"C≈ “Œ– –¿ƒ»Œ’»Ã»ﬂ<br>"+
		"À» ñ –’ <br>"+
		"„.  ÓÁÎÓ‰ÛÈ<br>"+
		"ÚÂÎ.: (0973) 7 24 01  e-mail: LI-RH_DPRAO@mail.bg</html>";
	    JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>" + text + "</div></html>");
	    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
	    lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	    gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
	    gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
	    gbc_lblNewLabel.gridx = 0;
	    gbc_lblNewLabel.gridy = 0;
	    p.add(lblNewLabel, gbc_lblNewLabel);
	    
	    JLabel lblNewLabel_1 = new JLabel("New label");
	    GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	    gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
	    gbc_lblNewLabel_1.gridx = 0;
	    gbc_lblNewLabel_1.gridy = 1;
	    p.add(lblNewLabel_1, gbc_lblNewLabel_1);
	    
	    JLabel lblNewLabel_2 = new JLabel("New label");
	    GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
	    gbc_lblNewLabel_2.gridx = 0;
	    gbc_lblNewLabel_2.gridy = 2;
	    p.add(lblNewLabel_2, gbc_lblNewLabel_2);
	    getContentPane().add(scrollpane, BorderLayout.CENTER);
	    
	    
	  }

}
