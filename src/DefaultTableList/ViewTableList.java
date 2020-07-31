package DefaultTableList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import Aplication.TableColumnDAO;
import DBase_Class.Request;
import DBase_Class.TableColumn;
import DBase_Class.Users;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import Table_Request.UpdateDataFor_RequestTalbeList;
import Table_Results.UpdateDataFor_ResultsTalbeList;
import WindowView.ChoiceL_I_P;
import WindowView.TranscluentWindow;

public class ViewTableList extends JDialog {

	private static final long serialVersionUID = 1L;


	@SuppressWarnings("static-access")
	public ViewTableList(JFrame parent, TranscluentWindow round, Users user, String tipe_Table, String frame_name,
			Boolean firstLoad, Request choisetRequest) {
		super(parent, frame_name, true);
		
		TableList_OverallVariables objectTableList_OverallVariables = new TableList_OverallVariables();
		objectTableList_OverallVariables.setTipe_Table(tipe_Table);
		objectTableList_OverallVariables.setFrame_name(frame_name);
		
		createVisibleTableColumn(objectTableList_OverallVariables, firstLoad);

		final JTable table = createTable( objectTableList_OverallVariables, user, firstLoad, choisetRequest);

		
		JPanel top_panel = new JPanel();
		top_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		top_panel.setSize(new Dimension(2, 0));
		getContentPane().add(top_panel, BorderLayout.NORTH);
		top_panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel left_panel = new JPanel();
		left_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		left_panel.setSize(new Dimension(2, 0));
		left_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		top_panel.add(left_panel, BorderLayout.NORTH);

		JLabel lblColumnChoice = new JLabel(createLblColumnChoiceString(tipe_Table));
		lblColumnChoice.setHorizontalTextPosition(SwingConstants.CENTER);
		lblColumnChoice.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		left_panel.add(lblColumnChoice);

		JPanel raide_panel = new JPanel();
		raide_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		raide_panel.setSize(new Dimension(2, 0));
		raide_panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		top_panel.add(raide_panel, BorderLayout.NORTH);

		JCheckBox chckbxNewCheckBox = new JCheckBox(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("Request_List_Table_LabelText_EditingData"));

		chckbxNewCheckBox.setEnabled(check_UserIsAdmin(user));
		raide_panel.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setBorder(null);
		chckbxNewCheckBox.setMargin(new Insets(0, 2, 0, 2));
		chckbxNewCheckBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		chckbxNewCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);

		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setSize(1200, 800);
		setLocationRelativeTo(null);

		JLabel ll = new JLabel();
		scrollPane.add(ll);
		round.StopWindow();
		JPanel panel_Btn = new JPanel();
		panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		getContentPane().add(panel_Btn, BorderLayout.SOUTH);
		panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton btnSave = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("saveBtn_Text"));
		btnSave.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSave.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_Btn.add(btnSave);
		btnSave.setVisible(false);
	
		
		JButton btnCancel = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"));
		panel_Btn.add(btnCancel);

		coloredLabelIfChangeSelectedColumn(tipe_Table, objectTableList_OverallVariables.getList_TableColumn(),
				lblColumnChoice);
		chckbxNewCheckBoxListener( objectTableList_OverallVariables, chckbxNewCheckBox);
		columnChoiceListener(this, user, objectTableList_OverallVariables, lblColumnChoice, choisetRequest);
		if (check_UserIsAdmin(user)) {
			btnSave.setVisible(true);
		}
		btnSaveListener(objectTableList_OverallVariables, tipe_Table, table, btnSave);
		btnCancelListener(btnCancel);

		setVisible(true);
	}

	private void btnCancelListener(JButton btnCancel) {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
	}

	private boolean check_UserIsAdmin(Users user) {
		return user != null && user.getIsAdmin();
	}

	@SuppressWarnings("static-access")
	private void createVisibleTableColumn(TableList_OverallVariables objectTableList_OverallVariables, Boolean firstLoad) {
		
		if (firstLoad) {
//			firstLoad = false;
			List<TableColumn> list_TableColumnFromDBase = TableColumnDAO.getListTableColumnByTipe_Table(objectTableList_OverallVariables.getTipe_Table());
			objectTableList_OverallVariables.setList_TableColumn(list_TableColumnFromDBase);

		}

		List<TableColumn> list_TableColumn = objectTableList_OverallVariables.getList_TableColumn();
		System.out.println("masiveLength "+objectTableList_OverallVariables.getList_TableColumn().size());
		objectTableList_OverallVariables
				.setMasive_Invizible_Colum(getMasiveIndexInvisibleColumnResults(objectTableList_OverallVariables, list_TableColumn));

	}

	private String createLblColumnChoiceString(String tipe_Table) {
		String lblColumnChoiceString = "";

		switch (tipe_Table) {

		case "request":

			lblColumnChoiceString = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("Request_List_Table_LabelText_ChoiceColumn");

			break;

		case "Results":

			lblColumnChoiceString = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("Results_List_Table_LabelText_ChoiceColumn");

			break;
		}
		return lblColumnChoiceString;
	}

	@SuppressWarnings("static-access")
	private void chckbxNewCheckBoxListener(TableList_OverallVariables objectTableList_OverallVariables, JCheckBox chckbxNewCheckBox) {

		objectTableList_OverallVariables.setChckbxNewCheckBox(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				objectTableList_OverallVariables.setChckbxNewCheckBox(chckbxNewCheckBox);
			}
		});
		

	}

	
	private int[] getMasiveIndexInvisibleColumnResults(TableList_OverallVariables objectTableList_OverallVariables, List<TableColumn> list_TableColumn) {
		String[] masiveNameInvisibleColumn = TableList_Functions
				.getMasiveFromNameInvisbleColumn(list_TableColumn);

		int[] masiveIndexInvisbleColumn = TableList_Functions
				.getMasiveIndexColumnFromMasiveNameColumn(objectTableList_OverallVariables, masiveNameInvisibleColumn);
		return masiveIndexInvisbleColumn;
	}

	private void btnSaveListener(TableList_OverallVariables objectTableList_OverallVariables, String tipe_Table, final JTable table, JButton btnSave) {
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				TranscluentWindow round = new TranscluentWindow();

				final Thread thread = new Thread(new Runnable() {
					
					
					@SuppressWarnings("static-access")
					public void run() {
						
						switch (tipe_Table) {

						case "request":
							UpdateDataFor_RequestTalbeList.updateDataFor_RequestTalbeList(objectTableList_OverallVariables, table, round);
							break;
						case "Results":
							UpdateDataFor_ResultsTalbeList.updateData(objectTableList_OverallVariables, table, round);
							break;

						}
						
						if (check_ChangedColumn(tipe_Table, objectTableList_OverallVariables.getList_TableColumn())) {
							if(OptionDialog()>0){
							updateTableColumn(objectTableList_OverallVariables.getList_TableColumn());	
							}
						}

					}

					private void updateTableColumn(List<TableColumn> list_TableColumn) {
						for (TableColumn tableColumn : list_TableColumn) {
							TableColumnDAO.updateObjectTableColumn(tableColumn);
						}
						
					}
				});
				thread.start();

			}
		});
	}

	public static int OptionDialog() {
		String[] options = {"Да", "Не"};
;
		ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Request_List_Table_LabelText_EditingData");
        int x = JOptionPane.showOptionDialog(null,
        		ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("List_Table_DialogSave_ChoiceColumn"),
                "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        System.out.println(x);
		return x;
       
	}
	
	
	@SuppressWarnings("static-access")
	private void columnChoiceListener(ViewTableList table_List,  Users user, TableList_OverallVariables objectTableList_OverallVariables, 
			JLabel lblColumnChoice, Request choisetRequest) {

		List<TableColumn> list_TableColumn = null;
		
		list_TableColumn = objectTableList_OverallVariables.getList_TableColumn();
		objectTableList_OverallVariables.setMasive_Invizible_Colum(getMasiveIndexInvisibleColumnResults(objectTableList_OverallVariables, list_TableColumn));
	
		lblColumnChoice.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				coloredLabelIfChangeSelectedColumn(objectTableList_OverallVariables.getTipe_Table(), objectTableList_OverallVariables.getList_TableColumn(),
						lblColumnChoice);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblColumnChoice.setForeground(Color.BLUE);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame f = new JFrame();
				List<String> listForNameVisibleColumn = null;
				
				listForNameVisibleColumn = TableList_Functions
						.getMasiveFromNameVISIBLEColumn(objectTableList_OverallVariables.getList_TableColumn());
				
//				switch (tipe_Table) {
//
//				case "request":
//					listForNameVisibleColumn = RequestTableList_Functions
//							.getMasiveFromNameVISIBLEColumn(RequestTableList_OverallVariables.getList_TableColumn());
//					break;
//				case "Results":
//					listForNameVisibleColumn = ResultsTableList_Functions
//							.getMasiveFromNameVISIBLEColumn(ResultsTableList_OverallVariables.getList_TableColumn());
//
//					break;
//				}

				new ChoiceL_I_P(f, listForNameVisibleColumn, false, lblColumnChoice.getText(), objectTableList_OverallVariables);
				listForNameVisibleColumn = ChoiceL_I_P.getChoiceL_P();

				if (isChangedVisibleColumn(objectTableList_OverallVariables.getTipe_Table(), listForNameVisibleColumn)) {

					objectTableList_OverallVariables.setList_TableColumn(
							TableList_Functions.ghangeInvisibleInTableColunmObject(listForNameVisibleColumn,
									objectTableList_OverallVariables.getList_TableColumn()));

					
//					switch (tipe_Table) {
//
//					case "request":
//						RequestTableList_OverallVariables.setList_TableColumn(
//								RequestTableList_Functions.ghangeInvisibleInTableColunmObject(listForNameVisibleColumn,
//										RequestTableList_OverallVariables.getList_TableColumn()));
//
//						break;
//					case "Results":
//						ResultsTableList_OverallVariables.setList_TableColumn(
//								ResultsTableList_Functions.ghangeInvisibleInTableColunmObject(listForNameVisibleColumn,
//										ResultsTableList_OverallVariables.getList_TableColumn()));
//
//						break;
//					}

					table_List.dispose();
					TranscluentWindow round = new TranscluentWindow();

					final Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {

							JFrame f = new JFrame();
							new ViewTableList(f, round, user, objectTableList_OverallVariables.getTipe_Table(),
									objectTableList_OverallVariables.getFrame_name(), false,  choisetRequest);

						}
					});
					thread.start();

				}
			}

			private Boolean isChangedVisibleColumn(String tipe_Table, List<String> listForNameVisibleColumn) {
				boolean changedVisibleColumn = false;
				
				changedVisibleColumn = TableList_Functions
						.check_ChangedVisibleColumn(listForNameVisibleColumn);
				
//				switch (tipe_Table) {
//				
//				
//				case "request":
//					changedVisibleColumn = TableList_Functions
//							.check_ChangedVisibleColumn2(listForNameVisibleColumn);
//					break;
//				case "Results":
//					changedVisibleColumn = TableList_Functions
//							.check_ChangedVisibleColumn(listForNameVisibleColumn);
//
//					break;
//				}
				return changedVisibleColumn;
			}

		});
	}

	private void coloredLabelIfChangeSelectedColumn(String tipe_Table, List<TableColumn> listTableColumn,
			JLabel lblColumnChoice) {
		if (check_ChangedColumn(tipe_Table, listTableColumn)) {
			lblColumnChoice.setForeground(Color.RED);
		} else {
			lblColumnChoice.setForeground(Color.BLACK);
		}
	}

	private boolean check_ChangedColumn(String tipe_Table, List<TableColumn> list_TableColumn) {
		List<TableColumn> list_TableColumnFromDBase = TableColumnDAO.getListTableColumnByTipe_Table(tipe_Table);
		return TableList_Functions.check_ChangedVisibleColumn(list_TableColumn, list_TableColumnFromDBase);

	}

	private JTable createTable(TableList_OverallVariables objectTableList_OverallVariables,  Users user, Boolean firstLoad, Request choisetRequest) {
		if (firstLoad) {
			CreateColumnTapeForTable.CreateListColumnTapeForTable_Test(objectTableList_OverallVariables);
		}
		TableList_Functions.SetDataInOverallVariablesForTable(objectTableList_OverallVariables, user, firstLoad, choisetRequest);
		
		return CreateTable.CreateDefaultTable(objectTableList_OverallVariables);
	}

}
