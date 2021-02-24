package WindowView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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

public class AddInChoice extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTextField textField;
	private JPanel panel;

	public AddInChoice(JFrame parent, String nameFrame, List<String> massiveO_I_R, String selectItem) {
		super(parent, nameFrame, true);
		
		final JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

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
			textField = new JTextField();
			textField.setAlignmentY(Component.TOP_ALIGNMENT);
			textField.setAlignmentX(Component.LEFT_ALIGNMENT);
			contentPanel.add(textField, BorderLayout.NORTH);
			textField.setColumns(10);
			textField.setText(selectItem);
			textField.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent event) {
				}

				@Override
				public void keyReleased(KeyEvent event) {
					panel.removeAll();
					if (textField.getText().length() >= 3) {
						List<String> list = getStrMas(textField.getText(), massiveO_I_R);
						System.out.println("---" + list.size());
						JLabel[] lblNewLabel2 = new JLabel[list.size()];
						int i = 0;
						for (String string : list) {
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
				textField.setText(selectItem);
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		setVisible(true);
	}

	public List<String> getStrMas(String str, List<String> massiveO_I_R) {
		
		List<String> masStr = new ArrayList<String>();
		for (String string : massiveO_I_R) {
			if (string.toLowerCase().contains(str.toLowerCase())) {
				masStr.add(string);
			}

		}

		return masStr;

	}

	public static String getChoice() {
		String arr = textField.getText();
		return arr;
	}
}
