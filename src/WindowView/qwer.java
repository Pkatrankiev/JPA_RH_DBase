package WindowView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

public class qwer extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			qwer dialog = new qwer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public qwer() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				JPanel panel = new JPanel();
				
				panel.setMaximumSize(new Dimension(300, 100));
				panel.setMinimumSize(new Dimension(200, 10));
				scrollPane.setViewportView(panel);
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
				{
					JPanel panel_1 = new JPanel();
					panel_1.setBackground(Color.CYAN);
					panel_1.setMaximumSize(new Dimension(400, 8));
					panel_1.setAutoscrolls(true);
					panel.add(panel_1);
					
					{
						JLabel label = new JLabel("New label");
						label.setBorder(new LineBorder(new Color(0, 0, 0)));
						label.setBackground(Color.RED);
						label.setSize(new Dimension(40,10));
						panel_1.add(label);
					}
					{
						JPanel panel_2 = new JPanel();
						panel_2.setPreferredSize(new Dimension(55, 20));
						panel_2.setMinimumSize(new Dimension(25, 20));
						panel_1.add(panel_2);
					}
					{
						textField_4 = new JTextField();
						textField_4.setColumns(10);
						textField_4.setSize(new Dimension(40,10));
						panel_1.add(textField_4);
					}
					{
						JComboBox comboBox = new JComboBox();
						comboBox.setPreferredSize(new Dimension(78, 20));
						comboBox.setEditable(true);
						comboBox.setSize(new Dimension(280, 23));
						comboBox.setMaximumRowCount(10);
						panel_1.add(comboBox);
					}
					{
						textField_5 = new JTextField();
						textField_5.setColumns(10);
						panel_1.add(textField_5);
					}
					{
						JLabel label = new JLabel("New label");
						panel_1.add(label);
					}
				}
				{
					JPanel panel_1 = new JPanel();
					panel_1.setMinimumSize(new Dimension(50, 25));
					panel_1.setPreferredSize(new Dimension(50, 25));
					panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
					panel.add(panel_1);
					panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					{
						JLabel label = new JLabel("New label");
						panel_1.add(label);
					}
					{
						textField_2 = new JTextField();
						textField_2.setColumns(10);
						panel_1.add(textField_2);
					}
					{
						textField_3 = new JTextField();
						textField_3.setColumns(10);
						panel_1.add(textField_3);
					}
					{
						JLabel label = new JLabel("New label");
						panel_1.add(label);
					}
				}
				{
					JPanel panel_1 = new JPanel();
					panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
					panel.add(panel_1);
					panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					{
						JLabel lblNewLabel = new JLabel("New label");
						panel_1.add(lblNewLabel);
					}
					{
						textField_1 = new JTextField();
						panel_1.add(textField_1);
						textField_1.setColumns(10);
					}
					{
						textField = new JTextField();
						panel_1.add(textField);
						textField.setColumns(10);
					}
					{
						JLabel lblNewLabel_1 = new JLabel("New label");
						panel_1.add(lblNewLabel_1);
					}
				}
			}
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

}
