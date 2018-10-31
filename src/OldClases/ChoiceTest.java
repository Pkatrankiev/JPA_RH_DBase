package OldClases;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DBase_Class.AplicantDAO;
import WindowView.AddInChoice;
import WindowView.AddInChoiceNameFamily;

import java.awt.Choice;
import java.awt.Dimension;

public class ChoiceTest extends JDialog {

	private final JPanel contentPanel = new JPanel();
private ArrayList<String> array_AplicantNameFamily;
private Choice choice;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChoiceTest dialog = new ChoiceTest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChoiceTest() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			choice = new Choice();
			choice.setSize(new Dimension(5, 0));
			choice.setPreferredSize(new Dimension(184, 4));
			choice.setMinimumSize(new Dimension(0, 4));
			contentPanel.add(choice);
		}
		{
			 String[] masive_AplicantNameFamily = AplicantDAO.getMasiveStringAllName_FamilyAplicant();
				array_AplicantNameFamily = new ArrayList<String>();
				for (String string : masive_AplicantNameFamily) {
					choice.add(string);
					array_AplicantNameFamily.add(string);
				}
				}
			JButton btnNewButton = new JButton("New button");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					Boolean fl_Aplicant = ChoiceFrame(array_AplicantNameFamily, choice);
							
				}
			});
			contentPanel.add(btnNewButton);
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
		private Boolean ChoiceFrame(ArrayList<String> array_list, Choice choice_obekt) {
			Boolean fl= false;
			final JFrame f = new JFrame();
			new AddInChoiceNameFamily(f, array_list, choice_obekt.getSelectedItem());
			String str = AddInChoiceNameFamily.getChoice();
			for (String string : array_list) {
				if (str.equals(string))
					fl = true;
			}
			if (!fl) {
				array_list.add(str);
				choice_obekt.add(str);
			}
			choice_obekt.select(str);
			return fl;
		}

	}

