package WindowView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Aplication.List_izpitvan_pokazatelDAO;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.TableColumn;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import Table.RequestTableList_Functions;
import Table.RequestTableList_OverallVariables;
import Table_Default_Structors.TableList_Functions;
import Table_Results.ResultsTableList_Functions;
import Table_Results.ResultsTableList_OverallVariables;

import javax.swing.JScrollPane;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ChoiceL_I_P extends JDialog {

	
	private static final long serialVersionUID = 1L;
	static ArrayList<String> bsic_list;
	static List<String> list_incommingObject;
	static int countL_I_P ;

	Panel[] check_panel ;
	static JLabel[] label ;
	static JCheckBox[] checkBox ;

	@SuppressWarnings("static-access")
	public ChoiceL_I_P(JFrame parent, List<String> incomming_list, Boolean fromTamplate, String name_Frame, 
			ResultsTableList_OverallVariables objectTableList_OverallVariables) {
		super(parent, name_Frame, true);
		
		if(name_Frame.contains(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().
				get("DefauiltTableMouseListener_EditColumnPokazatel"))) {
		bsic_list = RequestViewAplication.getStringListLIP();
		
		}

		if(name_Frame.contains(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().
				get("Request_List_Table_LabelText_ChoiceColumn"))){
			List<TableColumn> list_TableColumn = objectTableList_OverallVariables.getList_TableColumn();
			bsic_list = TableList_Functions.getListString_NameColumn(list_TableColumn);
		}
		
		
			
		list_incommingObject  = incomming_list;
		countL_I_P = bsic_list.size();
		
		
		label = new JLabel[countL_I_P];
		check_panel = new Panel[countL_I_P];
		checkBox = new JCheckBox[countL_I_P];
		
		final JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		final JPanel contentPanel = new JPanel();

		// Panel[] check_panel = new Panel[countL_I_P];
		// JLabel[] label = new JLabel[countL_I_P];
		// JCheckBox[] checkBox = new JCheckBox[countL_I_P];

		setBounds(100, 100, 400, 100 + (countL_I_P * 25));
		// setBounds(100, 100, 400, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		contentPanel.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_1 = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("LabelText_Select"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel_1);

		int i = 0;
		for (String string : bsic_list) {

			check_panel[i] = new Panel();
			panel.add(check_panel[i]);
			check_panel[i].setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			check_panel[i].setLayout(new BoxLayout(check_panel[i], BoxLayout.X_AXIS));
			int k = i;
			check_panel[i].addMouseListener(new MouseAdapter() {
			
				@Override
				public void mouseEntered(MouseEvent e) {

				}
				
				public void mousePressed(MouseEvent e) {
					
					if (checkBox[k].isSelected()) {
						checkBox[k].setSelected(false);
					} else {
						checkBox[k].setSelected(true);
					}

				}
			});
			checkBox[i] = new JCheckBox();
			check_panel[i].add(checkBox[i]);

			if (!list_incommingObject.isEmpty()) {

				for (String str : list_incommingObject) {
					if (str.equals(string))
						checkBox[i].setSelected(true);
				}
			}

			label[i] = new JLabel(string);
			check_panel[i].add(label[i]);
			i++;
		}

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("OK_Btn_Text"));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getChoiceL_P();
					removeAll();
					dispose();
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}

		JButton cancelButton = new JButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("exitBtn_Text"));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("  +++ " + list_incommingObject.size());
				cancelFunction();
				
				dispose();
			}

		
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				cancelFunction();
				 dispose();
			}
		});

		if (fromTamplate) {
			setVisible(false);
		} else
			setVisible(true);
	}

	
	private void cancelFunction() {
		for (int j = 0; j < bsic_list.size(); j++)
			checkBox[j].setSelected(false);
		for (String string : list_incommingObject) {
			for (int j = 0; j < bsic_list.size(); j++) {
				if (label[j].getText().equals(string)) {

					checkBox[j].setSelected(true);
				}
			}

		}
	}
	
	
	
	
	public static ArrayList<String> getChoiceL_P() {
		ArrayList<String> arr = new ArrayList<String>();
		for (int i = 0; i < countL_I_P; i++) {
			if (checkBox[i].isSelected()){
				arr.add(label[i].getText());
			System.out.println("+++ "+label[i].getText());
			}
		}
		return arr;
	}
	
	public static ArrayList<String> getNotChoiceL_P() {
		ArrayList<String> arr = new ArrayList<String>();
		for (int i = 0; i < countL_I_P; i++) {
			if (!checkBox[i].isSelected()){
				arr.add(label[i].getText());
			System.out.println("+++ "+label[i].getText());
			}
		}
		return arr;
	}
	public static String[] getMasiveNotChoiceL_P() {
		 ArrayList<String> list = getNotChoiceL_P();
		String[] arr = new String[list.size()];
		int i =0;
		for (String str:list) {
			arr[i] = str;
			i++;
		}
		return arr;
	}

	public static ArrayList<List_izpitvan_pokazatel> getListI_PFormChoiceL_P() {
		ArrayList<List_izpitvan_pokazatel> arr = new ArrayList<List_izpitvan_pokazatel>();
		List_izpitvan_pokazatel l_I_P = null;
		for (int i = 0; i < countL_I_P; i++) {
			if (checkBox[i].isSelected()) {
				l_I_P = List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(label[i].getText());
				arr.add(l_I_P);
			}
		}
		return arr;
	}
}
