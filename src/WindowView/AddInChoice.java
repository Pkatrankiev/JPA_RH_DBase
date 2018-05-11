package WindowView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Array;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import WindowViewAplication.RequestViewAplication;

import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class AddInChoice extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPanel panel;


	/**
	 * Create the dialog.
	 */
	public AddInChoice(JFrame parent) {
		 super(parent, "", true);
		 String[] massiveO_I_R = RequestViewAplication.getStringMassiveO_I_R();
		 
		 final JDialog dialog = new JDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				JPanel panel = new JPanel();
				scrollPane.setViewportView(panel);
			
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
			}
		}
		{
			textField = new JTextField();
			textField.setAlignmentY(Component.TOP_ALIGNMENT);
			textField.setAlignmentX(Component.LEFT_ALIGNMENT);
			contentPanel.add(textField, BorderLayout.NORTH);
			textField.setColumns(10);
			textField.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent event) {

				}

				@Override
				public void keyReleased(KeyEvent event) {
					 ArrayList<String> list =  getStrMas( textField.getText(),  massiveO_I_R);
						for (String string : list) {
							JLabel lblNewLabel = new JLabel(string);
							panel.add(lblNewLabel);
						}
				
				}

				@Override
				public void keyPressed(KeyEvent event) {

				}
			});
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
			setVisible(true);
		}
	}

	public ArrayList<String> getStrMas(String str, String[] massiveO_I_R){
		
		ArrayList<String> masStr = new ArrayList<String>() ;
		for (String string : massiveO_I_R) {
			if(string.contentEquals(str))
			{
				masStr.add(string);
			}
			
		}
		
		return masStr;
		
	}
}
