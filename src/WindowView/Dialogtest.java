package WindowView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import WindowViewAplication.RequestViewAplication;

public class Dialogtest extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPanel panel;




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Dialogtest dialog = new Dialogtest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dialogtest() {
		//--------------------------------
		
		String[] massiveO_I_R = RequestViewAplication.getStringMassiveO_I_R();
			
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				panel = new JPanel();
				scrollPane.setViewportView(panel);
			
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				{
					JLabel lblNewLabel = new JLabel("New label");
					panel.add(lblNewLabel);
				}
				
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
					panel.removeAll();
					if(textField.getText().length()>3){
					 ArrayList<String> list =  getStrMas( textField.getText(),  massiveO_I_R);
					 System.out.println("---"+list.size());
					 JLabel[] lblNewLabel2 = new JLabel[list.size()];
					int i=0;
					for (String string : list) {
						final int selection = i;
							lblNewLabel2[i] =  new JLabel(string);
							Color col = lblNewLabel2[i].getForeground();
							lblNewLabel2[i].addMouseListener(new MouseAdapter() {
								
								@Override
								public void mouseEntered(MouseEvent e) {
									lblNewLabel2[selection].setForeground(Color.RED);
								}

								@Override
								public void mouseExited(MouseEvent e) {
									lblNewLabel2[selection].setForeground(col);
								}
								
								public void mousePressed(MouseEvent e) {
									textField.setText(lblNewLabel2[selection].getText());
									
								}
									
								
							});
							
							
							
							
							panel.add(lblNewLabel2[i]);
							i++;
						}
					
					}
					panel.repaint();
					panel.revalidate();
					
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
		}

	}
	public ArrayList<String> getStrMas(String str, String[] massiveO_I_R){
		
		ArrayList<String> masStr = new ArrayList<String>() ;
		System.out.println(massiveO_I_R.length+" 55555555 "+str);
		for (String string : massiveO_I_R) {
		
			int strLength = string.length();
//			if(string.replaceFirst(str, "").length()!=strLength)
//			if(string.contentEquals(str))
			if(string.indexOf(str)>=0)
			{
				System.out.println(string+" - "+str);
				masStr.add(string);
			}
			
		}
		
		return masStr;
		
	}
	
}
