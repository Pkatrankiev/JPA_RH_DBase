package WindowView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GlobalVariable.ReadFileWithGlobalTextVariable;

import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddInChoiceNameFamily extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTextField textField_Name;
	private JPanel panel;
	private static JTextField textField_Family;

	public AddInChoiceNameFamily(JFrame parent, ArrayList<String> massiveO_I_R, String selectItem) {
		super(parent, "", true);

		final JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		String name = selectItem.substring(0, selectItem.indexOf(" "));
		String family = selectItem.substring(selectItem.indexOf(" ") + 1, selectItem.length());

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		{

			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel_1, BorderLayout.NORTH);

			JLabel lblNewLabel = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("name_Label_Text"));
			panel_1.add(lblNewLabel);
			textField_Name = new JTextField();
			panel_1.add(textField_Name);
			textField_Name.setAlignmentY(Component.TOP_ALIGNMENT);
			textField_Name.setAlignmentX(Component.LEFT_ALIGNMENT);
			textField_Name.setColumns(10);
			textField_Name.setText(name);

			JLabel lblNewLabel_1 = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("family_Label_Text"));
			panel_1.add(lblNewLabel_1);

			textField_Family = new JTextField();
			panel_1.add(textField_Family);
			textField_Family.setText(family);
			textField_Family.setColumns(10);
			textField_Family.setAlignmentY(Component.TOP_ALIGNMENT);
			textField_Family.setAlignmentX(Component.LEFT_ALIGNMENT);

			textField_Family.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent event) {
				}

				@Override
				public void keyReleased(KeyEvent event) {
					panel.removeAll();
					if (textField_Family.getText().length() >= 3) {
						ArrayList<String> listFamily = getStrMasByFamily(textField_Family.getText(), massiveO_I_R);

						JLabel[] lblNewLabel2 = new JLabel[listFamily.size()];

						int i = 0;
						for (String string : listFamily) {
							final int selection = i;
							lblNewLabel2[i] = new JLabel(string);
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
									String str = lblNewLabel2[selection].getText();
									textField_Name.setText(str.substring(0, str.indexOf(" ")));
									textField_Family.setText(str.substring(str.indexOf(" ") + 1, str.length()));
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

			textField_Name.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent event) {
				}

				@Override
				public void keyReleased(KeyEvent event) {
					panel.removeAll();
					if (textField_Name.getText().length() >= 3) {

						ArrayList<String> listName = getStrMasByName(textField_Name.getText(), massiveO_I_R);

						JLabel[] lblNewLabel2 = new JLabel[listName.size()];

						int i = 0;
						for (String string : listName) {
							final int selection = i;
							lblNewLabel2[i] = new JLabel(string);
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
									String str = lblNewLabel2[selection].getText();
									textField_Name.setText(str.substring(0, str.indexOf(" ")));
									textField_Family.setText(str.substring(str.indexOf(" ") + 1, str.length()));
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
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("OK_Btn_Text"));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getChoice();
				removeAll();
				dispose();

			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();

				textField_Name.setText(selectItem.substring(0, selectItem.indexOf(" ")));
				textField_Family.setText(selectItem.substring(selectItem.indexOf(" ") + 1, selectItem.length()));

				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		setVisible(true);
	}

	public ArrayList<String> getStrMasByName(String str, ArrayList<String> massiveO_I_R) {

		ArrayList<String> masStr = new ArrayList<String>();
		for (String string : massiveO_I_R) {
			if (string.indexOf(str) >= 0) {
				masStr.add(string);
			}

		}

		return masStr;

	}

	public ArrayList<String> getStrMasByFamily(String str, ArrayList<String> massiveO_I_R) {
		String stringF;
		ArrayList<String> masStr = new ArrayList<String>();
		for (String string : massiveO_I_R) {
			stringF = string.substring(string.indexOf(" ") + 1, string.length());
			if (stringF.indexOf(str) >= 0) {
				masStr.add(string);
			}

		}

		return masStr;

	}

	public static String getChoice() {
		String arr = textField_Name.getText() + " " + textField_Family.getText();

		return arr;
	}
}
