
package Table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import Aplication.TableColumnDAO;
import DBase_Class.TableColumn;
import DBase_Class.Users;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import OldClases.Table_Request_List_Test2;
import Table_Default_Structors.CreateTable;
import Table_Default_Structors.DefauiltTableMouseListener;
import WindowView.ChoiceL_I_P;
import WindowView.Login;
import WindowView.TranscluentWindow;
import javax.swing.JCheckBox;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridLayout;

public class Table_Request_List extends JDialog {

	private static final long serialVersionUID = 1L;
	private boolean changedVisibleColumn;
	private List<TableColumn> list_TableColumnFromDBase;
	private List<TableColumn> list_TableColumn;
	private List<String> listNameVisibleColumn = null;

	public Table_Request_List(JFrame parent, TranscluentWindow round, Users user, String tipe_Table,
			String frame_name, Boolean firstLoad) {
		super(parent, frame_name, true);

		final JTable table = createTable(tipe_Table, frame_name, user, firstLoad);
		
		if (firstLoad) {
			System.out.println("firstLoad " + firstLoad);
			firstLoad = false;
			list_TableColumnFromDBase = TableColumnDAO.getListTableColumnByTipe_Table(tipe_Table);
			RequestTableList_OverallVariables.setList_TableColumn(list_TableColumnFromDBase);
		}
			
		
		list_TableColumn = RequestTableList_OverallVariables.getList_TableColumn();
	
		for (TableColumn string : RequestTableList_OverallVariables.getList_TableColumn()) {
			System.out.println("first " + string.getName_Column()+" "+string.getInVisible());
		}
		
		
		changedVisibleColumn = RequestTableList_Functions.check_ChangedVisibleColumn2(listNameVisibleColumn);
				
		RequestTableList_OverallVariables.setMasiveIndexInvizible_Colum(getMasiveIndexInvisibleColumn(list_TableColumn));

		

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

		JLabel lblColumnChoice = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("Request_List_Table_LabelText_ChoiceColumn"));
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
		chckbxNewCheckBox.setEnabled(false);
		raide_panel.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setBorder(null);
		chckbxNewCheckBox.setMargin(new Insets(0, 2, 0, 2));

		chckbxNewCheckBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		chckbxNewCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
		if (user != null && user.getIsAdmin()) {
			chckbxNewCheckBox.setEnabled(true);
		}
		RequestTableList_OverallVariables.setChckbxNewCheckBox(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RequestTableList_OverallVariables.setChckbxNewCheckBox(chckbxNewCheckBox);
			}
		});

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
		if (user != null && user.getIsAdmin()) {
			panel_Btn.add(btnSave);
		}
		JButton btnCancel = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		panel_Btn.add(btnCancel);

		columnChoiceListener(this, scrollPane, tipe_Table, frame_name, user, lblColumnChoice);
		btnSaveListener(tipe_Table, table, btnSave);

		setVisible(true);
	}

	private void btnSaveListener(String tipe_Table, final JTable table, JButton btnSave) {
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				TranscluentWindow round = new TranscluentWindow();

				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {

						List<Integer> listRowForUpdate = RequestTableList_OverallVariables.getListRowForUpdate();

						switch (tipe_Table) {

						case "request":
							RequestTableList_Functions.updateData(table, listRowForUpdate, round);
							break;

						}

					}
				});
				thread.start();

			}
		});
	}

	private void columnChoiceListener(Table_Request_List table_Request_List, JScrollPane scrollPane,
			String tipe_Table, String frame_name, Users user, JLabel lblColumnChoice) {
		lblColumnChoice.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (changedVisibleColumn) {
					lblColumnChoice.setForeground(Color.RED);
				} else {
					lblColumnChoice.setForeground(Color.BLACK);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblColumnChoice.setForeground(Color.BLUE);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame f = new JFrame();
				listNameVisibleColumn = RequestTableList_Functions.getMasiveFromNameVISIBLEColumn(list_TableColumn);
			
								
				new ChoiceL_I_P(f, listNameVisibleColumn, false, ReadFileWithGlobalTextVariable
						.getGlobalTextVariableMap().get("Request_List_Table_LabelText_ChoiceColumn"));
				listNameVisibleColumn = ChoiceL_I_P.getChoiceL_P();
			
				
				

				if (RequestTableList_Functions.check_ChangedVisibleColumn2(listNameVisibleColumn)) {
					List<TableColumn> listTC = RequestTableList_Functions.ghangeInvisibleInTableColunmObject(
							listNameVisibleColumn, list_TableColumn);
				
					RequestTableList_OverallVariables.setList_TableColumn(listTC);
					
					list_TableColumn = RequestTableList_OverallVariables.getList_TableColumn();
								
					table_Request_List.dispose();
					TranscluentWindow round = new TranscluentWindow();

					final Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {

							JFrame f = new JFrame();
							new Table_Request_List(f, round, Login.getCurentUser(), tipe_Table, frame_name,false);

						}
					});
					thread.start();

				}
			}

			

		});
	}

	

	private int[] getMasiveIndexInvisibleColumn(List<TableColumn> list_TableColumn) {
		String[] masiveNameInvisibleColumn = RequestTableList_Functions
				.getMasiveFromNameInvisbleColumn(list_TableColumn);

		int[] masiveIndexInvisbleColumn = DefauiltTableMouseListener
				.getMasiveIndexColumnFromMasiveNameColumn(masiveNameInvisibleColumn);
		return masiveIndexInvisbleColumn;
	}

	
	private JTable createTable(String tipe_Table, String frame_name, Users user, Boolean firstLoad) {
		if(firstLoad){
			CreateColumnTapeForTable.CreateListColumnTapeForTable(tipe_Table);
		}
		switch (tipe_Table) {

		case "request":
			RequestTableList_Functions.OverallVariablesForRequestTable(frame_name, user, firstLoad);
			break;

		}
		final JTable table = CreateTable.CreateDefaultTable(tipe_Table);
		return table;
	}
	
}