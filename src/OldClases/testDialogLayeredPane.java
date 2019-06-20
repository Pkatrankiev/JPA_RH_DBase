package OldClases;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import GlobalVariable.GlobalPathForIcons;

import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class testDialogLayeredPane extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			testDialogLayeredPane dialog = new testDialogLayeredPane();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public testDialogLayeredPane() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		
			

			
			
				JPanel panel = new JPanel();
		panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new BorderLayout(0, 0));
				
					JLayeredPane layeredPane = new JLayeredPane();
					panel_1.add(layeredPane);
											ImageIcon pic = new ImageIcon(GlobalPathForIcons.get_destination_ajaxLoader());
						JLabel lblNewLabel = new JLabel(pic);
						
						lblNewLabel.setBounds(0, 0, 432, 218);
						layeredPane.add(lblNewLabel);
					
						JPanel panel_2 = new JPanel();
						panel_2.setBounds(0, 0, 432, 218);
						layeredPane.add(panel_2);
						panel_2.setLayout(new BorderLayout(0, 0));
						
							JScrollPane scrollPane = new JScrollPane();
							panel_2.add(scrollPane);
						
					
			
				
				
			
				
				
			
		}
		
	
		
		
			

		
		
	
				
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	
}
