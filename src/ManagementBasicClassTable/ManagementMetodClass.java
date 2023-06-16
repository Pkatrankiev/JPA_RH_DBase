package ManagementBasicClassTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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

public class ManagementMetodClass extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static String[] values_Emition;
	
	private static List<Integer> listWhithChangeRow;
	private static Object[][] dataTable;

	
	private static int tbl_Colum = 9;
	private static int metody_code_Colum = 0;
	private static int metody_descript_Colum = 1;
	private static int metody_InAcredit_Colum = 2;
	private static int metody_Acting_Colum = 3;
	private static int metody_Arrangement_Colum = 4;
	private static int metody_Emition_Colum = 5;
	private static int metody_NiclideForDobiv_Colum = 6;
	private static int metody_ToPokazatel_Colum = 7;
	private static int metody_ID_Colum = 8;
	
	
	private static String clickToChoice = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("clickToChoice");
	private static String dialogText = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_DialogText");
	

	public ManagementMetodClass(JFrame parent, TranscluentWindow round, Users user, Object[][] dataTableWithNewRow) {
		super(parent, "", true);

		String[] columnNames = getTabHeader();
		@SuppressWarnings("rawtypes")
		Class[] types = getTypes();
		if(dataTableWithNewRow!=null) {
			dataTable = dataTableWithNewRow;
		}else {
			dataTable = getDataTable();	
		}
		String title = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_TitleName");
		setTitle(title);
		
		
		values_Emition = getMasiveStringAllValueEmition();
		
		
		listWhithChangeRow = new ArrayList<Integer>() ;
//		final JTable table = new JTable();// new DefaultTableModel(rowData,
		
		
		JPanel panel_Btn = new JPanel();
		panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		getContentPane().add(panel_Btn, BorderLayout.SOUTH);
		panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton btnNewRow = new JButton("new Row");
		panel_Btn.add(btnNewRow);
		
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
			  //even index, selected or not selected
			  if (Index_row % 2 == 0) {
			  comp.setBackground(Color.lightGray);
			  }else {
			  comp.setBackground(Color.white);
			  }
			  if (isCellSelected(Index_row, Index_col)) {
				  comp.setBackground(Color.yellow);
			}
						  
			  if ((int)getValueAt(Index_row, metody_ID_Colum)>5000) {
				  comp.setForeground(Color.red);  
			  } else {
				  comp.setForeground(Color.black); 
				  }
			 if(listWhithChangeRow.size()>0) {
			 for (int changeRow : listWhithChangeRow) {
				    if (Index_row == changeRow) {
					  comp.setForeground(Color.blue);  
				  } else {
					  comp.setForeground(Color.black); 
					  } 
			 }
			}else {
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
						if (user != null && user.getIsAdmin()) {
							return true;
						} else {
							return false;
						}
									}
					
					
					
					public void setValueAt(Object value, int row, int col) {

						if (!dataTable[row][col].equals(value)) {
							dataTable[row][col] = value;
							fireTableCellUpdated(row, col);
							AddInUpdateList(row);
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
				tableMouseListener( table);
				new TableFilterHeader(table, AutoChoices.ENABLED);
				
				setUp_Emition(table, table.getColumnModel().getColumn(metody_Emition_Colum));
				
				table.getColumnModel().getColumn(metody_ID_Colum).setWidth(0);
				table.getColumnModel().getColumn(metody_ID_Colum).setMinWidth(0);
				table.getColumnModel().getColumn(metody_ID_Colum).setMaxWidth(0);
				table.getColumnModel().getColumn(metody_ID_Colum).setPreferredWidth(0);
				
				 initColumnSizes(table);
				 btnSaveActinListener(btnSave, table);
				 btnNewRowActionListener(parent, round, user, table, btnNewRow);
				 btnExportActionListener(title, btnExportButton,table);
				 
				 
			}

		
		});
	
//		/////////////////////////////////////////////////////////////////////////////////////////////////	
		
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setSize(1200, 800);
		setLocationRelativeTo(null);
		round.StopWindow();

		
		validate();
		repaint();
		setVisible(true);
	}

	private void btnExportActionListener(String title, JButton btnExportButton, JTable table) {
		btnExportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

//				TranscluentWindow p = new TranscluentWindow();

				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {

							table.getRowSorter();
//						List<Integer> listRow = new ArrayList<Integer>();
//						for (int i = 0; i < model.getRowCount(); i++) {
//							listRow.add(i);
//						}								
							CreateExcelFile.toExcel(getStringTypeColumn() , table, title, null, null, "");
										
				
					}
				});
				thread.start();

			}
		});
	}

	private void btnSaveActinListener(JButton btnSave, JTable table) {
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				TranscluentWindow round = new TranscluentWindow();
				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						updateData(table, round);
					}
				});
				thread.start();

			}
		});
	}

	private void btnNewRowActionListener(JFrame parent, TranscluentWindow round, Users user, JTable table,
			JButton btnNewRow) {
		btnNewRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						DefaultTableModel model =(DefaultTableModel) table.getModel();
						
						System.out.println( model.getValueAt(getSelectedModelRow(table), metody_InAcredit_Colum ));
						Object[] tableMetody = new Object[9] ;
						tableMetody[metody_code_Colum] =  model.getValueAt(getSelectedModelRow(table), metody_code_Colum );
						tableMetody[metody_descript_Colum] =  model.getValueAt(getSelectedModelRow(table), metody_descript_Colum );
						tableMetody[metody_InAcredit_Colum] =  model.getValueAt(getSelectedModelRow(table), metody_InAcredit_Colum );
						tableMetody[metody_Acting_Colum] = model.getValueAt(getSelectedModelRow(table), metody_Acting_Colum );
						tableMetody[metody_Arrangement_Colum] =  model.getValueAt(getSelectedModelRow(table), metody_Arrangement_Colum );
						tableMetody[metody_Emition_Colum] = model.getValueAt(getSelectedModelRow(table), metody_Emition_Colum );
						tableMetody[metody_NiclideForDobiv_Colum] =  model.getValueAt(getSelectedModelRow(table), metody_NiclideForDobiv_Colum );
						tableMetody[metody_ToPokazatel_Colum] =  model.getValueAt(getSelectedModelRow(table), metody_ToPokazatel_Colum );
						tableMetody[metody_ID_Colum] = (int) model.getValueAt(getSelectedModelRow(table), metody_ID_Colum )+5000;
						
						Object[][]  dataTableWithNewRow = new Object[dataTable.length+1][9];
						for (int i = 0; i < dataTable.length; i++) {
							dataTableWithNewRow[i] = dataTable[i];
						}
						dataTableWithNewRow[dataTable.length] = tableMetody;
						setVisible(false);
						new ManagementMetodClass(parent, round,  user, dataTableWithNewRow);
				
					}
				});
				thread.start();

			}
		});
	}



	private void tableMouseListener( JTable table) {
		
		TableModel model = table.getModel() ;
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {
				System.out.println(table.getSelectedColumn());
							
				
				if (table.getSelectedColumn() == metody_code_Colum ) {
					table.rowAtPoint(e.getPoint());
					table.columnAtPoint(e.getPoint());
					
					int selectedRow = getSelectedModelRow(table);
					
					
					String AllTextCodeMetody = (String) model.getValueAt(getSelectedModelRow(table), metody_code_Colum );
					int begin = AllTextCodeMetody.indexOf(" ");
					String code = AllTextCodeMetody.substring(0, begin);
					String textCodeMetody = AllTextCodeMetody.substring(begin+1);
					String newTextCodeMetody ;
					try {
						newTextCodeMetody = code + " "+ JOptionPane.showInputDialog(null, dialogText, textCodeMetody).trim();
					} catch (NullPointerException e2) {
						newTextCodeMetody = AllTextCodeMetody;
					} 
					model.setValueAt(newTextCodeMetody, selectedRow, metody_code_Colum);
					if (!newTextCodeMetody.equals(AllTextCodeMetody)) {
						AddInUpdateList(selectedRow);
					}

				}
				if (table.getSelectedColumn() == metody_NiclideForDobiv_Colum ) {
					table.rowAtPoint(e.getPoint());
					table.columnAtPoint(e.getPoint());
					int selectedRow = getSelectedModelRow(table);
					String stringNuclideDobiv = (String) model.getValueAt(getSelectedModelRow(table), metody_NiclideForDobiv_Colum );
					int idSelectedMetod = (Integer) model.getValueAt(getSelectedModelRow(table), metody_ID_Colum );
					System.out.println(idSelectedMetod);
					String title = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_NuclideDobiv");
					String codeMetod = (String) model.getValueAt(getSelectedModelRow(table), metody_code_Colum );
					List<String> listStringNuklideFromMetod = generateListStrFromStrNiclideForDobivData(stringNuclideDobiv);
					
					JFrame f = new JFrame();
					new ChoiceNuclideForDobiveWithPlusAndMinus(f,  title, getListStrAllNuclide(), listStringNuklideFromMetod,codeMetod);

					List<String> listChoiceNuclideForDobive = ChoiceNuclideForDobiveWithPlusAndMinus.getMasiveStringFromChoice();
					for (String class1 : listChoiceNuclideForDobive) {
						System.out.println(class1);
					}
					
					String newStringNuclideDobiv = getStringNuclideForDobive(listChoiceNuclideForDobive);
					
					model.setValueAt(newStringNuclideDobiv, selectedRow, metody_NiclideForDobiv_Colum);
					if (!newStringNuclideDobiv.equals(stringNuclideDobiv)) {
						AddInUpdateList(selectedRow);
					}
				}
			
				if (table.getSelectedColumn() == metody_ToPokazatel_Colum ) {
					table.rowAtPoint(e.getPoint());
					table.columnAtPoint(e.getPoint());
					int selectedRow = getSelectedModelRow(table);
					String stringToPokazatel = (String) model.getValueAt(getSelectedModelRow(table), metody_ToPokazatel_Colum );
					int idSelectedMetod = (Integer) model.getValueAt(getSelectedModelRow(table), metody_ID_Colum );
					System.out.println(idSelectedMetod);
					String title = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_Pokazatel");
					String codeMetod = (String) model.getValueAt(getSelectedModelRow(table), metody_code_Colum );
					List<String> listStringNuklideFromMetod = generateListStrFromStrNiclideForDobivData(stringToPokazatel);
					JFrame f = new JFrame();
					new ChoiceNuclideForDobiveWithPlusAndMinus(f,  title, getListStrAllPokazatel(),listStringNuklideFromMetod,codeMetod);

					List<String> listChoiceNuclideForDobive = ChoiceNuclideForDobiveWithPlusAndMinus.getMasiveStringFromChoice();
					for (String class1 : listChoiceNuclideForDobive) {
						System.out.println(class1);
					}
					
					String newStringNuclideDobiv = getStringNuclideForDobive(listChoiceNuclideForDobive);
					
					model.setValueAt(newStringNuclideDobiv, selectedRow, metody_ToPokazatel_Colum);
					System.out.println(newStringNuclideDobiv+""+stringToPokazatel);
					if (!newStringNuclideDobiv.equals(stringToPokazatel)) {
						AddInUpdateList(selectedRow);
					}
				}
				
							}

		
		});
	}

	

	private List<String> generateListStrFromStrNiclideForDobivData(String stringNuclideDobiv) {
		List<String> listChoiceNuclideForDobive = new ArrayList<>();
		String[] list = stringNuclideDobiv.split(",");
		for (String string : list) {
			listChoiceNuclideForDobive.add(string.trim());
		}
		
		return listChoiceNuclideForDobive;
	}
	
	private List<String> getListStrAllNuclide(){
	List<Nuclide> listNuclide = NuclideDAO.getInListAllValueNuclide();
	List<String>  listStringAllNuklide = new ArrayList<>();
	for (Nuclide nuclide : listNuclide) {
		listStringAllNuklide.add(nuclide.getSymbol_nuclide());
	}
	return listStringAllNuklide;
	}
	
	protected List<String> getListStrAllPokazatel() {
		List<List_izpitvan_pokazatel> listAllPokazatel = List_izpitvan_pokazatelDAO.getInListAllValuePokazatel();
		List<String>  listStringAllPokazatel = new ArrayList<>();
		for (List_izpitvan_pokazatel pokazatel : listAllPokazatel) {
			listStringAllPokazatel.add(pokazatel.getName_pokazatel());
		}
		return listStringAllPokazatel;
	}
	
	
	private Object[][] getDataTable() {
		List<Metody> listAllMetody = MetodyDAO.getInListAllValueMetody();
	
		Object[][] tableMetody = new Object[listAllMetody.size()][tbl_Colum];
		int i = 0;
		
		for (Metody metod : listAllMetody) {

						try {
												
								
							tableMetody[i][metody_code_Colum] = metod.getCode_metody();
							tableMetody[i][metody_descript_Colum] = metod.getName();
							tableMetody[i][metody_InAcredit_Colum] = metod.getInAcredit();
							tableMetody[i][metody_Acting_Colum] = metod.getActing_metody();
							tableMetody[i][metody_Arrangement_Colum] = metod.getArrangement();
							tableMetody[i][metody_Emition_Colum] = metod.getEmition().getEmition_name();
							tableMetody[i][metody_NiclideForDobiv_Colum] = getStringNuclideForDobive(metod);
							tableMetody[i][metody_ToPokazatel_Colum] = getStringToPokazatel(metod);
							tableMetody[i][metody_ID_Colum] = metod.getId_metody();
							
							i++;
							
						
						} catch (NullPointerException | NumberFormatException e) {
							ResourceLoader.appendToFile(e);
							JOptionPane.showInputDialog(
									ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("errorDataForResult_Text"), JOptionPane.ERROR_MESSAGE);
							} 
					}
			
		
		
	

		return tableMetody;
	}

	private String getStringNuclideForDobive(List<String> list) {
		String str = "";
		
		for (String nuclide : list) {
			str += nuclide+", ";
		}
		
		if(str.length()>0) {
			str = str.substring(0, str.length()-2);
		}
		return str;
	}
	
		
	
	private static Object getStringNuclideForDobive(Metody metod) {
		String str = "";
		List<Metody_to_NiclideForDobive> list = Metody_to_NiclideForDobiveDAO.getListMetody_to_NiclideForDobiveByMetody(metod);
		for (Metody_to_NiclideForDobive metody_to_NiclideForDobive : list) {
			str += metody_to_NiclideForDobive.getNuclide().getSymbol_nuclide()+", ";
		}
		
		if(str.length()>0) {
			str = str.substring(0, str.length()-2);
		}
		return str;
	}

	private static Object getStringToPokazatel(Metody metod) {
		String str = "";
		List<Metody_to_Pokazatel> list = Metody_to_PokazatelDAO.getListMetody_to_PokazatelByMetody(metod);
		for (Metody_to_Pokazatel metody_to_Pokazatel : list) {
			str += metody_to_Pokazatel.getPokazatel().getName_pokazatel()+", ";
		}
		if(str.length()>0) {
			str = str.substring(0, str.length()-2);
		}
		return str;
		
	}

	private static void AddInUpdateList(int row) {
		int index = (int) dataTable[row][metody_ID_Colum];
		Metody metod = MetodyDAO.getValueMetodyById(index);
		
		if(dataTable[row][metody_code_Colum].equals( metod.getCode_metody())
		&& dataTable[row][metody_descript_Colum].equals(  metod.getName())
		&& dataTable[row][metody_InAcredit_Colum] .equals(  metod.getInAcredit())
		&& dataTable[row][metody_Acting_Colum] .equals(  metod.getActing_metody())
		&& dataTable[row][metody_Arrangement_Colum] .equals(  metod.getArrangement())
		&& dataTable[row][metody_Emition_Colum] .equals( metod.getEmition().getEmition_name())
		&& dataTable[row][metody_NiclideForDobiv_Colum] .equals( getStringNuclideForDobive(metod))
		&& dataTable[row][metody_ToPokazatel_Colum] .equals( getStringToPokazatel(metod))
		&& listWhithChangeRow.contains(row)) {
			System.out.println(row+"++++++++++++++++++++++++++++++++++++++++++++++++++++");
			Iterator<Integer> itr = listWhithChangeRow.iterator();
			while (itr.hasNext()) {
			int obg = itr.next();
			if(obg==row) {
				System.out.println(row+"******************************************");	
				itr.remove();
			}
			}
		}
		
		if (listWhithChangeRow.isEmpty()) {
			listWhithChangeRow.add(row);
		} else {
			if (!listWhithChangeRow.contains(row)) {
				listWhithChangeRow.add(row);
			}
		}
	
	}
	
	private static void updateData(JTable table, TranscluentWindow round) {
		int index;
		for (int changeRoe : listWhithChangeRow) {
			index = (int) dataTable[changeRoe][metody_ID_Colum];
			if(index >= 5000) {
				saveNewMetod(table, changeRoe);
			}else {
			Metody metod = MetodyDAO.getValueMetodyById(index);
			udateMetod(table, changeRoe, metod);
			}
		}
		round.StopWindow();
	}
	
	private static void saveNewMetod(JTable table, int row) {
		Metody metod = new Metody();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		metod.setCode((String) model.getValueAt(row, metody_code_Colum));
		metod.setName((String) model.getValueAt(row, metody_descript_Colum));
		metod.setInAcredit((Boolean) model.getValueAt(row, metody_InAcredit_Colum));
		metod.setActing_metody((Boolean) model.getValueAt(row, metody_Acting_Colum));
		metod.setArrangement((Integer) model.getValueAt(row, metody_Arrangement_Colum));
		metod.setEmition(EmitionDAO.getEmitionByEmition_name((String) model.getValueAt(row, metody_Emition_Colum)));
		
		MetodyDAO.setValueMetody(metod);
	}
	
	private static void udateMetod(JTable table, int row, Metody metod) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		metod.setCode((String) model.getValueAt(row, metody_code_Colum));
		metod.setName((String) model.getValueAt(row, metody_descript_Colum));
		metod.setInAcredit((Boolean) model.getValueAt(row, metody_InAcredit_Colum));
		metod.setActing_metody((Boolean) model.getValueAt(row, metody_Acting_Colum));
		metod.setArrangement((Integer) model.getValueAt(row, metody_Arrangement_Colum));
		metod.setEmition(EmitionDAO.getEmitionByEmition_name((String) model.getValueAt(row, metody_Emition_Colum)));
		

		MetodyDAO.updateMetody(metod);
	}

	public static void setUp_Emition(JTable table, TableColumn Metody_Column) {
		JComboBox<?> comboBox = new JComboBox<Object>(values_Emition);
		Metody_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText(clickToChoice);
		Metody_Column.setCellRenderer(renderer);
	}
	
	
	@SuppressWarnings("rawtypes")
	private Class[] getTypes() {

		Class[] types = { String.class, String.class, Boolean.class, Boolean.class, Integer.class, String.class, String.class, String.class, Integer.class};

		return types;
	}
	
	private String[] getStringTypeColumn() {

		String[] types = { "String", "String","Boolean_Check", "Boolean_Check", "Integer", "String", "String", "String", "Integer"};

		return types;
	}
	
	private Object[] getlong() {

		Object[] types = {  "123456789012345678901234567890", "123456789012345678901234567890123456789012345678901234567890", 
				Boolean.TRUE, Boolean.TRUE, new Integer(20), "12345678901234567890", 
				"123456789012345678901234567890123456789012345678901234567890", 
				"123456789012345678901234567890123456789012345678901234567890", new Integer(2000) };

		return types;
	}
	
	
	private String[] getTabHeader() {
		String[] tableHeader = {
//				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"), 
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_Code"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_Deskript"), 
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_InAcreidt"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_Acting"), 
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_Arangement"), 
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_Emition"), 
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_NuclideDobiv"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_Pokazatel"),
				"id"
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
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                             getTableCellRendererComponent(
                                 table, longValues[i],
                                 false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;

           

            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
//            column.sizeWidthToFit(); //or simple
        }
    }
	
    public static String[] getMasiveStringAllValueEmition(){
    	List<Emition> list = EmitionDAO.getListAllEmition();
		String[] values = new String[list.size()];
		int i = 0;
		for (Emition object : list) {
			values[i] = object.getEmition_name();
			i++;
		}
		return values;
	}
    	
	private static void AddInUpdateList1(int row) {

		if (listWhithChangeRow.isEmpty()) {
			listWhithChangeRow.add(row);
		} else {
			if (!listWhithChangeRow.contains(row)) {
				listWhithChangeRow.add(row);
			}
		}
	
	}
    
	
	
    
    private int getSelectedModelRow(JTable table) {
		return  table.convertRowIndexToModel(table.getSelectedRow());
		}
}
