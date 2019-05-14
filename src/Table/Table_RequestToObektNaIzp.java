package Table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.RequestDAO;
import Aplication.Request_To_ObektNaIzpitvaneRequestDAO;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Request;
import DBase_Class.Request_To_ObektNaIzpitvaneRequest;
import DBase_Class.Users;
import WindowView.ChoiceFromListWithPlusAndMinus;
import WindowView.RequestMiniFrame;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class Table_RequestToObektNaIzp  extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private static List<Integer> listRowUpdateObject = new ArrayList<Integer>();
	private static List<String> bsic_listObektNaIzpit = Obekt_na_izpitvane_requestDAO.getListStringAllValueObekt_na_izpitvane();
	private static int tbl_Colum = 3;
	private static int request_Code_Colum = 0;
	private static int obektNaIzp_Colum = 1;
	private static int id_Request_Colum = 2;

	public Table_RequestToObektNaIzp(JFrame parent, TranscluentWindow round, Users user) {
		super(parent, "",true);
		String[] columnNames = getTabHeader();
		@SuppressWarnings("rawtypes")
		Class[] types = getTypes();
		Object[][] dataTable = getDataTable();
		int counRow = dataTable.length;
		
		setTitle("Списък на Вътрешни Клиенти");
		setBounds(100, 100, 650, counRow*25+50);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	

		final JTable table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {
				if (table.getSelectedColumn() == request_Code_Colum && getSelectedModelRow(table) != -1) {
					table.rowAtPoint(e.getPoint());
					table.columnAtPoint(e.getPoint());
					DefaultTableModel model =(DefaultTableModel) table.getModel();
					String reqCodeStr = model.getValueAt(getSelectedModelRow(table), request_Code_Colum).toString();
					Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
					new RequestMiniFrame(new JFrame(), choiseRequest);

				}
				if (table.getSelectedColumn() == obektNaIzp_Colum && user != null && user.getIsAdmin()
						&& getSelectedModelRow(table) != -1) {
					int rowObektNaIzp = table.rowAtPoint(e.getPoint());
					System.out.println(rowObektNaIzp);
					EditRequestObektIzpit(table, rowObektNaIzp);

					AddInUpdateList(rowObektNaIzp, dataTable);
				}
				
			}
		});

		new TableFilterHeader(table, AutoChoices.ENABLED);

		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		setLocationRelativeTo(null);

		round.StopWindow();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(dataTable, columnNames) {

					private static final long serialVersionUID = 1L;
					@SuppressWarnings("rawtypes")
					private Class[] types2 = types;

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
							AddInUpdateList(row, dataTable);
						}
					}

				};
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				
				table.getColumnModel().getColumn(id_Request_Colum ).setWidth(0);
				table.getColumnModel().getColumn(id_Request_Colum ).setMinWidth(0);
				table.getColumnModel().getColumn(id_Request_Colum ).setMaxWidth(0);
				table.getColumnModel().getColumn(id_Request_Colum ).setPreferredWidth(0);
				

				JPanel panel_Btn = new JPanel();
				panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
				getContentPane().add(panel_Btn, BorderLayout.SOUTH);
				panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				JButton btnSave = new JButton("Запис");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						TranscluentWindow round = new TranscluentWindow();
						final Thread thread = new Thread(new Runnable() {
							@Override
							public void run() {
								updateData(table, listRowUpdateObject, round);
							}
						});
						thread.start();
					}
				});
				
				btnSave.setHorizontalAlignment(SwingConstants.RIGHT);
				btnSave.setAlignmentX(Component.RIGHT_ALIGNMENT);
				if (user != null && user.getIsAdmin()) {
					panel_Btn.add(btnSave);
				}
				
				JButton btnCancel = new JButton("Изход");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				panel_Btn.add(btnCancel);
			}
		});
		setVisible(true);
		repaint();
	}
	
	private static void EditRequestObektIzpit  (JTable table, int row) {
		String []  list = ReadListObektNaIzpitInCell(table, row);
		JFrame f = new JFrame();
		new ChoiceFromListWithPlusAndMinus(f, list, bsic_listObektNaIzpit, "Обект на изпитване");
		table.setValueAt(CreateStringObektNaIzpit(), row, obektNaIzp_Colum);
		
	}
	
	private static String CreateStringObektNaIzpit() {
		String str = "";
		for(String stringObektNaIzpit :ChoiceFromListWithPlusAndMinus.createMasiveStringFromChoice()){
			str = str + stringObektNaIzpit+"; ";
			}
		return str.substring(0, str.length()-2);
			
	}

	private static String[] ReadListObektNaIzpitInCell(JTable table, int row) {
		List<String> list = new ArrayList<String>();
		DefaultTableModel model =(DefaultTableModel) table.getModel();
		String strObektIzpit = model.getValueAt(row, obektNaIzp_Colum).toString().trim();
		String str = "";
		
		while (!strObektIzpit.isEmpty()) {
			if(strObektIzpit.indexOf(";")>=0){
			str = strObektIzpit.substring(0, strObektIzpit.indexOf(";") + 1);
			}else{
				str = strObektIzpit;	
			}
			list.add(str.replaceAll(";", "").trim());
			strObektIzpit = strObektIzpit.replaceFirst(str, "");
			System.out.println(strObektIzpit);
		}
		String [] masive = new String[list.size()];
		int i=0;
		for (String strList: list) {
			masive[i] = strList;
			i++;
		}
		return masive;
	}
	
	private Object[][] getDataTable() {
		List<Request> listAllIntApplic = RequestDAO.getInListAllValueRequest();
		Object[][] table = new Object[listAllIntApplic.size()][tbl_Colum];
		int i = 0;
		for (Request request : listAllIntApplic) {
			try {
				table[i][request_Code_Colum] = request.getRecuest_code();
				table[i][obektNaIzp_Colum] = createStringListObektNaIzp(request);
				i++;
			} catch (NumberFormatException e) {
				JOptionPane.showInputDialog("Грешни данни за резултат:", JOptionPane.ERROR_MESSAGE);
			}
		}
		return table;
	}

	private String createStringListObektNaIzp(Request request) {
		String str = "";
	for(Request_To_ObektNaIzpitvaneRequest requestToObIzp: Request_To_ObektNaIzpitvaneRequestDAO.getRequest_To_ObektNaIzpitvaneRequestByRequest(request)){
		str = str+requestToObIzp.getObektNaIzp().getName_obekt_na_izpitvane()+"; ";
	}
		return str.substring(0, str.length()-2);
	}

	
	
	@SuppressWarnings("rawtypes")
	private Class[] getTypes() {
		Class[] types = { String.class, String.class, Integer.class };
		return types;
	}

	private String[] getTabHeader() {
		String[] tableHeader = { "Код на заявка", "Обект на изпитване", "Id" };
		return tableHeader;
	}

	
	private static void updateData(JTable table, List<Integer> listIdUpdateObject, TranscluentWindow round) {
		
		for (int rowUpdateObject : listIdUpdateObject) {
			Request request = RequestDAO.getRequestFromColumnByVolume("recuest_code", table.getValueAt(rowUpdateObject, request_Code_Colum));
			List<Obekt_na_izpitvane_request> listObektIzpit_requet = new ArrayList<Obekt_na_izpitvane_request>();
			for (String strNameObektIzpit : ReadListObektNaIzpitInCell(table, rowUpdateObject)){
				listObektIzpit_requet.add(Obekt_na_izpitvane_requestDAO.
						getValueObekt_na_izpitvane_requestByName(strNameObektIzpit));
			}
			updateRequestToObIzpObject(request,listObektIzpit_requet);
		}
		round.StopWindow();
	}

	private static void updateRequestToObIzpObject(Request request, List<Obekt_na_izpitvane_request> listObektIzpit_requet ) {
List<Request_To_ObektNaIzpitvaneRequest> listRequestInBase = Request_To_ObektNaIzpitvaneRequestDAO.getRequest_To_ObektNaIzpitvaneRequestByRequest(request);
		
			int i =0;
			for (Request_To_ObektNaIzpitvaneRequest request_To_ObektNaIzpitvaneRequest : listRequestInBase) {
				request_To_ObektNaIzpitvaneRequest.setObektNaIzp(listObektIzpit_requet.get(i));
				Request_To_ObektNaIzpitvaneRequestDAO.updateValueRequest_To_ObektNaIzpitvaneRequest(request_To_ObektNaIzpitvaneRequest);
				i++;
			}
			if(listRequestInBase.size()<listObektIzpit_requet.size()){
			for (int j = i; j < listObektIzpit_requet.size(); j++) {
				Request_To_ObektNaIzpitvaneRequest reqToObIzp = new Request_To_ObektNaIzpitvaneRequest(request,listObektIzpit_requet.get(j));
				Request_To_ObektNaIzpitvaneRequestDAO.setValueRequest_To_ObektNaIzpitvaneRequest(reqToObIzp);
			}
		}else{
			
		}
		
	}

	private static void AddInUpdateList(int row, Object[][] dataTable) {

		if (listRowUpdateObject.isEmpty()) {
			listRowUpdateObject.add(row);
		} else {
			if (!listRowUpdateObject.equals(row)) {
				listRowUpdateObject.add(row);
			}
		}
	}

	private int getSelectedModelRow(JTable table) {
		return  table.convertRowIndexToModel(table.getSelectedRow());
		}

}
