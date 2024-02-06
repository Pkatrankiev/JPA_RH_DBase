package ManagementBasicClassTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import Aplication.DimensionDAO;
import Aplication.EmitionDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.Metody_to_NiclideForDobiveDAO;
import Aplication.Metody_to_PokazatelDAO;
import Aplication.NuclideDAO;
import Aplication.PostDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import Aplication.UsersDAO;
import DBase_Class.Emition;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Metody_to_NiclideForDobive;
import DBase_Class.Metody_to_Pokazatel;
import DBase_Class.Nuclide;
import DBase_Class.Post;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;
import ExcelFilesFunction.CreateExcelFile;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import GlobalVariable.ResourceLoader;
import OldClases.ChoiceNuclideForDobiveWithPlusAndMinus;
import WindowView.RequestMiniFrame;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class ManagementUsersClass extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static String[] values_Post;
	
	private static List<Integer> listWhithChangeRow;
	private static Object[][] dataTable;

	
	private static int tbl_Colum = 9;
	private static int users_Name_Colum = 0;
	private static int users_Family_Colum = 1;
	private static int users_NikName_Colum = 2;
	private static int users_Pass_Colum = 3;
	private static int users_Post_Colum = 4;
	private static int users_isActing_Colum = 5;
	private static int users_isAdmin_Colum = 6;
	private static int users_ID_Colum = 7;
	private static int users_Hite_Pass_Colum = 8;
	
	private static int removeID_Users = 0;
	
	private static String clickToChoice = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("clickToChoice");
//	private static String dialogText = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_DialogText");
	private static Color L_BLUE = new Color(51,204,255);

	public ManagementUsersClass(JFrame parent, TranscluentWindow round, Users user, Object[][] dataTableWithNewRow, List<Integer> listChange) {
		super(parent, "", true);
		
		String[] columnNames = getTabHeader();
		@SuppressWarnings("rawtypes")
		Class[] types = getTypes();
		if(dataTableWithNewRow!=null) {
			dataTable = dataTableWithNewRow;
		}else {
			dataTable = getDataTable();	
		}
		
		if(listChange!=null) {
			listWhithChangeRow = listChange ;
		}else {
			listWhithChangeRow = new ArrayList<Integer>() ;
		}
		String fTit = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Users_UsersManagement");
		if (user != null && user.getIsAdmin()) {
			fTit +=  " "	+ ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MainWindow_Title_work") + " "
				+ user.getName_users() + " " + user.getFamily_users();
		}
		
		String title = fTit;
		
		setTitle(title);
		
		int countRow = dataTable.length;
		values_Post = getMasiveStringAllValueEmition();
		
		
	
//		final JTable table = new JTable();// new DefaultTableModel(rowData,
		
		
		JPanel panel_Btn = new JPanel();
		panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		getContentPane().add(panel_Btn, BorderLayout.SOUTH);
		panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton btnHelpButton = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_Help"));
		panel_Btn.add(btnHelpButton);
		
		
		JButton btnNewRow = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_NewRow"));
		btnNewRow.setEnabled(false);
		if (user != null && user.getIsAdmin()) {
		panel_Btn.add(btnNewRow);
		}
		
		JButton btnRemoveNewRow = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_RemoveRow"));
		btnRemoveNewRow.setEnabled(false);
		if (user != null && user.getIsAdmin()) {
		panel_Btn.add(btnRemoveNewRow);
		}
		
		JButton btnExportButton = new JButton("export");
		panel_Btn.add(btnExportButton);
		
		
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
		
		
		
		
		DefaultTableModel model =new DefaultTableModel(dataTable, columnNames);									// columnNames));
		  JTable table = new JTable(model){
			  
			private static final long serialVersionUID = 1L;

			public Component prepareRenderer
			  (TableCellRenderer renderer,int Index_row, int Index_col) {
			  Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
			  int id_Metod = (int)getValueAt(Index_row, users_ID_Colum);	
			  //even index, selected or not selected
			 
			  if (Index_row % 2 == 0) {
				  comp.setBackground(Color.lightGray);
				  }else {
				  comp.setBackground(Color.white);
				  }
			
			 if(listWhithChangeRow.size() > 0 ) {
			 for (int changeIdMetody : listWhithChangeRow) {
					
				    if (id_Metod == changeIdMetody ) {
				    	 comp.setBackground(L_BLUE);  
				  } 
			 }
			}
//			 else {
//				 comp.setForeground(Color.black);	
//			}
			 
			 if (isCellSelected(Index_row, Index_col)) {
				
					  comp.setBackground(Color.yellow);
					  btnNewRow.setEnabled(true);
					   
					   if(id_Metod>5000) {
						   btnRemoveNewRow.setEnabled(true);
						   removeID_Users = id_Metod;
					   }else {
						   btnRemoveNewRow.setEnabled(false);  
					   }
				} 
			 
			 
			 
			 if (id_Metod > 5000) {
				  comp.setForeground(Color.red);  
			  } else {
				  comp.setForeground(Color.black); 
				  }
		
			  return comp;
			  }
			  };
		
		

		

		
//	/////////////////////////////////////////////////////////////////////////////////////////////////	
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(dataTable, columnNames) {

					
					private static final long serialVersionUID = 1L;
					@SuppressWarnings("rawtypes")
					private Class[] types2 = types;

					@SuppressWarnings({})
					@Override
					public Class<?> getColumnClass(int columnIndex) {
						return this.types2[columnIndex];
					}

					public Object getValueAt(int row, int col) {
						return dataTable[row][col];
					}
					
					@Override
					public boolean isCellEditable(int row, int column) {
						if (user != null && user.getIsAdmin() && column < 5) {
						return true;
						} else {
							return false;
						}
									}
					
					
					
					public void setValueAt(Object value, int row, int col) {

						boolean fl = true;
						if (!dataTable[row][col].equals(value)) {
							
							if(col == users_NikName_Colum && checkUnikateNikName(table, (String)value, row)) {
								fl =false;
						}
							if(fl) {
							dataTable[row][col] = value;
							fireTableCellUpdated(row, col);
							AddInUpdateList(row);	
						}
						}
					}

					

					public int getColumnCount() {
						return columnNames.length;
					}

					public int getRowCount() {
						return dataTable.length;
					}

				};
		
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				tableMouseListener( table, user);
				new TableFilterHeader(table, AutoChoices.ENABLED);
				
				setUp_Post(table, table.getColumnModel().getColumn(users_Post_Colum));
				
				hiteColumn(table, users_ID_Colum);
				hiteColumn(table, users_Hite_Pass_Colum);
				
				 initColumnSizes(table);
				 btnSaveActionListener(parent, user, btnSave, table);
				 btnNewRowActionListener(parent,  user, table, btnNewRow);
				 btnRemoveNewRowActionListener(parent,  user, table, btnRemoveNewRow);
				 btnExportActionListener(title, btnExportButton,table);
				 btnHelpActionListener( btnHelpButton);
				 
			}

			

		
		
		});
	
//		/////////////////////////////////////////////////////////////////////////////////////////////////	
		
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setSize(800, 100+countRow*18);
		setLocationRelativeTo(null);
		round.StopWindow();

		
		validate();
		repaint();
		setVisible(true);
	}

	private void btnRemoveNewRowActionListener(JFrame parent,  Users user, JTable table,
			JButton btnRemoveNewRow) {
		
		btnRemoveNewRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TranscluentWindow round = new TranscluentWindow();
				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
							Object[][]  dataTableWithNewRow = new Object[dataTable.length-1][9];
						int k = 0;
						for (int i = 0; i < dataTable.length; i++) {
							if((int)dataTable[i][users_ID_Colum] != removeID_Users) {
							dataTableWithNewRow[k] = dataTable[i];
							k++;
							}
						}
						
						setVisible(false);
						new ManagementUsersClass(parent, round,  user, dataTableWithNewRow, listWhithChangeRow);
				
					}
				});
				thread.start();

			}
		});
		
	}
	
	private void btnHelpActionListener( JButton btnHelpButton) {
		btnHelpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame f = new JFrame();
				String label = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Users_HelpUsersManagement");
				String respath = "TEMPLATES_DIRECTORY\\Help_ManagementUsers.txt";
				String MetodText = ReadFailHelpWindow. CreadMasiveFromReadFile(respath);
				new HelpWindow(f, label, MetodText);
		
			}
		});
	}
	
	private void btnExportActionListener(String title, JButton btnExportButton, JTable table) {
		btnExportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {

							table.getRowSorter();
					
							CreateExcelFile.toExcel(getStringTypeColumn() , table, title, null, null, "");
										
				
					}
				});
				thread.start();

			}
		});
	}

	private void btnSaveActionListener(JFrame parent,  Users user, JButton btnSave, JTable table) {
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				TranscluentWindow round = new TranscluentWindow();
				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						updateData(table);
						setVisible(false);
						new ManagementUsersClass(parent, round,  user, null, null);
					}
				});
				thread.start();

			}
		});
	}

	private void btnNewRowActionListener(JFrame parent, Users user, JTable table,
			JButton btnNewRow) {
		btnNewRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TranscluentWindow round = new TranscluentWindow();
				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						DefaultTableModel model =(DefaultTableModel) table.getModel();
						Object[] tableMetody = new Object[tbl_Colum] ;
						tableMetody[users_Name_Colum] =  model.getValueAt(getSelectedModelRow(table), users_Name_Colum );
						tableMetody[users_NikName_Colum] =  model.getValueAt(getSelectedModelRow(table), users_NikName_Colum );
						tableMetody[users_Family_Colum] =  model.getValueAt(getSelectedModelRow(table), users_Family_Colum );
						tableMetody[users_Pass_Colum] = model.getValueAt(getSelectedModelRow(table), users_Pass_Colum );
						tableMetody[users_Post_Colum] =  model.getValueAt(getSelectedModelRow(table), users_Post_Colum );
						tableMetody[users_isActing_Colum] =  model.getValueAt(getSelectedModelRow(table), users_isActing_Colum );
						tableMetody[users_isAdmin_Colum] = model.getValueAt(getSelectedModelRow(table), users_isAdmin_Colum );
						tableMetody[users_ID_Colum] = (int) model.getValueAt(getSelectedModelRow(table), users_ID_Colum )+5000;
						tableMetody[users_Hite_Pass_Colum] = model.getValueAt(getSelectedModelRow(table), users_Hite_Pass_Colum );
						
						Object[][]  dataTableWithNewRow = new Object[dataTable.length+1][9];
						for (int i = 0; i < dataTable.length; i++) {
							dataTableWithNewRow[i] = dataTable[i];
						}
						dataTableWithNewRow[dataTable.length] = tableMetody;
						setVisible(false);
						new ManagementUsersClass(parent, round,  user, dataTableWithNewRow, listWhithChangeRow);
				
					}
				});
				thread.start();

			}
		});
	}

	private void tableMouseListener( JTable table, Users user) {
		
		TableModel model = table.getModel() ;
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {
				if (getSelectedModelRow(table) != -1) {
				int selectRow = getSelectedModelRow(table);
				
				System.out.println(selectRow+" "+selectRow);
			
		
				
				
//				***************************************************************************************
				if (user != null && user.getIsAdmin()) {
				if (e.getClickCount() == 2 && table.getSelectedColumn() == users_Pass_Colum) {
					
					int selectedRow = getSelectedModelRow(table);
					String stringUsersPass = (String) model.getValueAt(selectRow, users_Hite_Pass_Colum );
					

					String newPass = JOptionPane.showInputDialog(null,
							ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Users_Chenge_Pass"),stringUsersPass);
					
					System.out.println(newPass);
					if(newPass != null) {
					model.setValueAt(newPass, selectedRow, users_Hite_Pass_Colum);
					}

				}
				}
				
//				***************************************************************************************
				if (user != null && user.getIsAdmin()) {
				if (e.getClickCount() == 2 && table.getSelectedColumn() == users_isActing_Colum) {
					int selectedRow = getSelectedModelRow(table);
					boolean fl = (boolean) model.getValueAt(selectRow, users_isActing_Colum );
					model.setValueAt(!fl, selectedRow, users_isActing_Colum);
				}
				}
				
//				***************************************************************************************			
				if (user != null && user.getIsAdmin()) {
				if (e.getClickCount() == 2 && table.getSelectedColumn() == users_isAdmin_Colum) {
					int selectedRow = getSelectedModelRow(table);
					boolean fl = (boolean) model.getValueAt(selectRow, users_isAdmin_Colum );
					model.setValueAt(!fl, selectedRow, users_isAdmin_Colum);
				}
				}

				
							}
		}
		
		});
	}

	
	protected static boolean checkUnikateNikName(JTable table, String newTextNikname, int row) {
		TableModel model = table.getModel() ;
//		System.out.println("****************************** "+newTextNikname);
		String newPass ="";
		String Users_Dublikate_NikName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Users_Dublikate_NikName");
		String errorOfData = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("errorOfData");
		Users_Dublikate_NikName = "<html>"+Users_Dublikate_NikName+"<br>"+newTextNikname+"</html>";
		boolean fl = false;
		for (int i = 0; i < model.getRowCount(); i++) {
			if(i != row && newTextNikname.equals(model.getValueAt(i, users_NikName_Colum))) {
				JOptionPane.showMessageDialog(null, Users_Dublikate_NikName,errorOfData, JOptionPane.WARNING_MESSAGE);
				fl = true;
				i= dataTable.length;
			}
		}
		if(fl) {
		checkUnikateNikName(table, newPass, row);
		}
		return fl;
	}

	
	private Object[][] getDataTable() {
		List<Users> listAllUsers = UsersDAO.getInListAllValueUsers();
	
		Object[][] tableUsers = new Object[listAllUsers.size()][tbl_Colum];
		int i = 0;
		
		for (Users users : listAllUsers) {

						try {
							tableUsers[i][users_Name_Colum] = users.getName_users();
							tableUsers[i][users_Family_Colum] = users.getFamily_users();
							tableUsers[i][users_NikName_Colum] = users.getNikName_users();
							tableUsers[i][users_Pass_Colum] = "***";
							tableUsers[i][users_Post_Colum] = users.getPost().getName_post();
							tableUsers[i][users_isActing_Colum] = users.getActing();
							tableUsers[i][users_isAdmin_Colum] = users.getIsAdmin();
							tableUsers[i][users_ID_Colum] = users.getId_users();
							tableUsers[i][users_Hite_Pass_Colum] = users.getPass_users();
							
							i++;
							
						
						} catch (NullPointerException | NumberFormatException e) {
							ResourceLoader.appendToFile(e);
							JOptionPane.showInputDialog(
									ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("errorDataForResult_Text"), JOptionPane.ERROR_MESSAGE);
							} 
					}
			
		
		
	

		return tableUsers;
	}


	private static void AddInUpdateList(int row) {
		
		
		
		int index = (int) dataTable[row][users_ID_Colum];
		if(index < 5000) {
	
		Users user = UsersDAO.getValueUsersById(index);
		
		if(dataTable[row][users_Name_Colum].equals( user.getName_users())
		&& dataTable[row][users_Family_Colum].equals(  user.getFamily_users())
		&& dataTable[row][users_NikName_Colum ] .equals(  user.getNikName_users())
//		&& dataTable[row][users_Pass_Colum] .equals(  user.getPass_users())
		&& dataTable[row][users_Post_Colum] .equals(  user.getPost().getName_post())
		&& dataTable[row][users_isAdmin_Colum] .equals( user.getIsAdmin())
		&& dataTable[row][users_isActing_Colum] .equals( user.getActing())
		&& dataTable[row][users_Hite_Pass_Colum] .equals(  user.getPass_users())
		&& listWhithChangeRow.contains(user.getId_users())) {
			Iterator<Integer> itr = listWhithChangeRow.iterator();
			while (itr.hasNext()) {
			int obg = itr.next();
			if(obg==user.getId_users()) {
				itr.remove();
			}
			}
		}else {
		
		if (listWhithChangeRow.isEmpty()) {
			listWhithChangeRow.add(user.getId_users());
		} else {
			if (!listWhithChangeRow.contains(user.getId_users())) {
				listWhithChangeRow.add(user.getId_users());
			}
		}
		}
		}
	}
	
	private static void updateData(JTable table) {
		int tableIdUsers;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		for (int row = 0; row < dataTable.length; row++) {
			tableIdUsers = (int) model.getValueAt(row, users_ID_Colum);
			System.out.println(tableIdUsers+"+++++++++++++++++++++");
			if(tableIdUsers >= 5000) {
			saveNewUsers(table, row);
			}
		for (int changeIdUsers : listWhithChangeRow) {
			if(tableIdUsers == changeIdUsers) {
			Users metod = UsersDAO.getValueUsersById(tableIdUsers);
			udateUsers(table, row, metod);
				}
			}
		}
	
	}
	
	private static void saveNewUsers(JTable table, int row) {
		Users user = new Users();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		user.setName_users((String) model.getValueAt(row, users_Name_Colum));
		user.setFamily_users((String) model.getValueAt(row, users_Family_Colum));
		user.setNikName_users((String) model.getValueAt(row, users_NikName_Colum));
		user.setPass_users((String) model.getValueAt(row, users_Hite_Pass_Colum));
		user.setPost(PostDAO.getValuePostByName((String) model.getValueAt(row, users_Post_Colum)));
		user.setActing((Boolean) model.getValueAt(row, users_isActing_Colum));
		user.setIsAdmin((Boolean) model.getValueAt(row, users_isAdmin_Colum));
		
		UsersDAO.setValueUsers(user);
		
	}
	
	
	private static void udateUsers(JTable table, int row, Users user) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		user.setName_users((String) model.getValueAt(row, users_Name_Colum));
		user.setFamily_users((String) model.getValueAt(row, users_Family_Colum));
		user.setNikName_users((String) model.getValueAt(row, users_NikName_Colum));
		user.setPass_users((String) model.getValueAt(row, users_Hite_Pass_Colum));
		user.setPost(PostDAO.getValuePostByName((String) model.getValueAt(row, users_Post_Colum)));
		user.setActing((Boolean) model.getValueAt(row, users_isActing_Colum));
		user.setIsAdmin((Boolean) model.getValueAt(row, users_isAdmin_Colum));

		UsersDAO.updateUsers(user);
	}

	public static void setUp_Post(JTable table, TableColumn Users_Column) {
		JComboBox<?> comboBox = new JComboBox<Object>(values_Post);
		Users_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText(clickToChoice);
		Users_Column.setCellRenderer(renderer);
	}
	
	
	@SuppressWarnings("rawtypes")
	private Class[] getTypes() {

		Class[] types = {String.class, String.class, String.class, String.class, String.class, Boolean.class, Boolean.class,  Integer.class, String.class};

		return types;
	}
	
	private String[] getStringTypeColumn() {

		String[] types = { "String", "String", "String", "String", "String", "Boolean_Check", "Boolean_Check", "Integer", "String"};

		return types;
	}
	
	private Object[] getlong() {

		@SuppressWarnings("removal")
		Object[] types = {  "12345678901234", "12345678901234", 
				"12345678901234", "12345", "12345678",
				Boolean.TRUE, Boolean.TRUE, new Integer(20), "12345" };

		return types;
	}
	
	private void hiteColumn(JTable table, int users_ID_Colum) {
		table.getColumnModel().getColumn(users_ID_Colum).setWidth(0);
		table.getColumnModel().getColumn(users_ID_Colum).setMinWidth(0);
		table.getColumnModel().getColumn(users_ID_Colum).setMaxWidth(0);
		table.getColumnModel().getColumn(users_ID_Colum).setPreferredWidth(0);
		
	}
	
	
	private String[] getTabHeader() {
		String[] tableHeader = {
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Users_Name"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Users_Family"), 
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Users_NikName"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Users_Pass"), 
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Users_Post"), 
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Users_isActing"), 
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Users_isAdmin"),
				"id",""
				};
		return tableHeader;
	}

    private void initColumnSizes(JTable table) {
	   	
    	DefaultTableModel model =(DefaultTableModel) table.getModel();
        TableColumn column = null;
      
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
       Object[] longValues = getlong();
       
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < tbl_Colum; i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                                 null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width+15;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                             getTableCellRendererComponent(
                                 table, longValues[i],
                                 false, false, 0, i);
            cellWidth = comp.getPreferredSize().width+15;

           

            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
            column.sizeWidthToFit(); //or simple
        }
    }
	
    public static String[] getMasiveStringAllValueEmition(){
    	List<Post> list = PostDAO.getInListAllValuePost();
		String[] values = new String[list.size()];
		int i = 0;
		for (Post object : list) {
			values[i] = object.getName_post();
			i++;
		}
		return values;
	}
    	
	
	
    
    private int getSelectedModelRow(JTable table) {
		return  table.convertRowIndexToModel(table.getSelectedRow());
		}
}
