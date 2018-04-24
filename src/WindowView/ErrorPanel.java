package WindowView;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.CardLayout;

public class ErrorPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ErrorPanel() {
		setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, "name_106032077559199");
		
		JLabel lblNewLabel = new JLabel("New label1");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, "name_106053152346939");
		
		JLabel lblNewLabel_1 = new JLabel("New label2");
		panel_1.add(lblNewLabel_1);

	}

}
