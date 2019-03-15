package OldClases;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JEditorPane;

public class testScrol extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			testScrol dialog = new testScrol();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public testScrol() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane);
			{
				JPanel panel = new JPanel();
				scrollPane.setViewportView(panel);
				panel.setLayout(new BorderLayout(0, 0));
				{
					JEditorPane editorPane = new JEditorPane();
					panel.add(editorPane, BorderLayout.CENTER);
				}
			}
		}
	}

}
