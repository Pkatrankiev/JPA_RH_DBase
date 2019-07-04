package Table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import sun.nio.cs.ext.MSISO2022JP;

public class Table_RequestToObektNaIzp  extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
//	private static List<Integer> listRowUpdateObject = new ArrayList<Integer>();
	private static List<String> bsic_listObektNaIzpit = Obekt_na_izpitvane_requestDAO.getListStringAllValueObekt_na_izpitvane();
	private static Map<Integer, List<String>> mapListForStrObektNaIzp = new HashMap<Integer, List<String>>();

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
		
		setTitle("Таблица на изпитваните обекти към заявка");
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
				if (getSelectedModelRow(table) != -1){
				table.rowAtPoint(e.getPoint());
				table.columnAtPoint(e.getPoint());
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				String reqCodeStr = model.getValueAt(getSelectedModelRow(table), request_Code_Colum).toString();
				Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
				if (table.getSelectedColumn() == request_Code_Colum) {
					new RequestMiniFrame(new JFrame(), choiseRequest);
				}
				if (table.getSelectedColumn() == obektNaIzp_Colum && user != null && user.getIsAdmin()) {
					int rowObektNaIzp = table.rowAtPoint(e.getPoint());
					if(EditRequestObektIzpit(table, rowObektNaIzp, choiseRequest, mapListForStrObektNaIzp, bsic_listObektNaIzpit)){
					List<String> listFromChoiceObektNaIzp = ChoiceFromListWithPlusAndMinus.getMasiveStringFromChoice();
					table.setValueAt(createStringListObektNaIzp(listFromChoiceObektNaIzp, false), rowObektNaIzp, obektNaIzp_Colum);
					mapListForStrObektNaIzp.put(rowObektNaIzp, listFromChoiceObektNaIzp);
//					AddInUpdateList(rowObektNaIzp, listFromChoiceObektNaIzp);
					}
					
				}
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
//							AddInUpdateList(row, dataTable);
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
								updateData(table, mapListForStrObektNaIzp, round);
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
	
	static Boolean EditRequestObektIzpit  (JTable table, int row, Request request, Map<Integer, List<String>> mapListForStrObektNaIzp,
			List<String> bsic_listObektNaIzpit) {
	
		List<String>  OriginalListObektNaIzp = getListStringOfRequest_To_ObektNaIzpitvaneRequest(request);
//		List<String>  IncominglListObektNaIzp = new ArrayList<String>();
		List<String>  IncominglListObektNaIzp = mapListForStrObektNaIzp.get(row);
		if(IncominglListObektNaIzp!=null){
			OriginalListObektNaIzp = IncominglListObektNaIzp;
		}
		
		JFrame f = new JFrame();
		new ChoiceFromListWithPlusAndMinus(f, OriginalListObektNaIzp, bsic_listObektNaIzpit, "Обект на изпитване");
		List<String> listFromChoiceObektNaIzp = ChoiceFromListWithPlusAndMinus.getMasiveStringFromChoice();
			
		return checkForChange(listFromChoiceObektNaIzp, OriginalListObektNaIzp);
	}
	
	private static Boolean checkForChange(List<String> listFromChoiceObektNaIzp, List<String>  OriginalListObektNaIzp){
		Boolean check = false;
		if(listFromChoiceObektNaIzp.size() == OriginalListObektNaIzp.size()){
			int index=0;
			for (String strObektNaIzp : OriginalListObektNaIzp) {
				if(!listFromChoiceObektNaIzp.get(index).equals(strObektNaIzp)){
					return true;
				}
				index++;
			}
		}else{
			return true;	
		}
		return check;
	}


	private Object[][] getDataTable() {
		List<Request> listAllIntApplic = RequestDAO.getInListAllValueRequest();
		Object[][] table = new Object[listAllIntApplic.size()][tbl_Colum];
		int i = 0;
		for (Request request : listAllIntApplic) {
			try {
				table[i][request_Code_Colum] = request.getRecuest_code();
				table[i][obektNaIzp_Colum] = createStringListObektNaIzp(getListStringOfRequest_To_ObektNaIzpitvaneRequest(request), false);
				i++;
			} catch (NumberFormatException e) {
				JOptionPane.showInputDialog("Грешни данни за резултат:", JOptionPane.ERROR_MESSAGE);
			}
		}
		return table;
	}

		
	public static String createStringListObektNaIzp(List<String> list, Boolean forRequestVew) {
//		List<String> list;
//		if(request==null){
//			list = ChoiceFromListWithPlusAndMinus.getMasiveStringFromChoice();
//		}else{
//			list = getListStringOfRequest_To_ObektNaIzpitvaneRequest(request);
//		}
		String str = "", endLine="";
		
		int endIndex = list.size();
		for (int i = 0; i < list.size(); i++) {
			if(i==endIndex-1 && str.length()>0){
				str = str.substring(0, str.length()-2);
				str = str+" и "	;
			}
			if(forRequestVew && (str+list.get(i)).length()>70){
				endLine = "\n";
			}
			str = str+endLine+list.get(i)+"; ";
			
		}

	if(str.length()==0){
		return str;
	}
		return str.substring(0, str.length()-2);
	}

	public static List<String> getListStringOfRequest_To_ObektNaIzpitvaneRequest(Request request){
		List<String> listStr_Request_To_ObektNaIzpitvaneRequest = new ArrayList<String>();
		List<Request_To_ObektNaIzpitvaneRequest> list = 
				Request_To_ObektNaIzpitvaneRequestDAO.getRequest_To_ObektNaIzpitvaneRequestByRequest(request);
		for (Request_To_ObektNaIzpitvaneRequest request_To_ObektNaIzpitvaneRequest : list) {
			listStr_Request_To_ObektNaIzpitvaneRequest.add(request_To_ObektNaIzpitvaneRequest.getObektNaIzp().getName_obekt_na_izpitvane());
		}
		return listStr_Request_To_ObektNaIzpitvaneRequest;
		
	}
	
	
	
	public static String[] getMasiveStringOfRequest_To_ObektNaIzpitvaneRequest(Request request){
		List<Request_To_ObektNaIzpitvaneRequest> list = 
				Request_To_ObektNaIzpitvaneRequestDAO.getRequest_To_ObektNaIzpitvaneRequestByRequest(request);
		String[] masive = new String[list.size()];
		int i=0;
		for (Request_To_ObektNaIzpitvaneRequest request_To_ObektNaIzpitvaneRequest : list) {
			masive[i]=request_To_ObektNaIzpitvaneRequest.getObektNaIzp().getName_obekt_na_izpitvane();
			i++;
		}
		return masive;
			}
	
	public static Request_To_ObektNaIzpitvaneRequest getRequest_To_ObektNaIzpitvaneRequest(Request request, String ObektNaIzpitvaneRequest){
		List<Request_To_ObektNaIzpitvaneRequest> list = 
				Request_To_ObektNaIzpitvaneRequestDAO.getRequest_To_ObektNaIzpitvaneRequestByRequest(request);
		for (Request_To_ObektNaIzpitvaneRequest request_To_ObektNaIzpitvaneRequest : list) {
			if(request_To_ObektNaIzpitvaneRequest.getObektNaIzp().getName_obekt_na_izpitvane().equals(ObektNaIzpitvaneRequest))
			return request_To_ObektNaIzpitvaneRequest;
				}
		return null;
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

	
	private static void updateData(JTable table, Map<Integer, List<String>> mapListForStrObektNaIzp, TranscluentWindow round) {
		// create list of keys and values 
        List<Integer> ListofKeys = null; 
       
  
        // convert hashmap to list of keys and values 
        ListofKeys = mapListForStrObektNaIzp.keySet().stream().collect(Collectors.toCollection(ArrayList::new)); 
		for (int rowUpdateObject : ListofKeys) {
			Request request = RequestDAO.getRequestFromColumnByVolume("recuest_code", table.getValueAt(rowUpdateObject, request_Code_Colum));
			List<Obekt_na_izpitvane_request> listObektIzpit_request = creadListStrFromMap(mapListForStrObektNaIzp,
					rowUpdateObject);
			updateRequestToObIzpObject(request,listObektIzpit_request);
		}
		round.StopWindow();
	}

	static List<Obekt_na_izpitvane_request> creadListStrFromMap(
			Map<Integer, List<String>> mapListForStrObektNaIzp, int rowUpdateObject) {
		List<Obekt_na_izpitvane_request> listObektIzpit_request = new ArrayList<Obekt_na_izpitvane_request>();
		for (String strNameObektIzpit : mapListForStrObektNaIzp.get(rowUpdateObject)){
			listObektIzpit_request.add(Obekt_na_izpitvane_requestDAO.
					getValueObekt_na_izpitvane_requestByName(strNameObektIzpit));
		}
		return listObektIzpit_request;
	}

	static List<String> creatListStringfromListObekt_na_izpitvane_request(
			List<Obekt_na_izpitvane_request> listObektIzpit_request) {
	List<String> list = new ArrayList<String>();
		for (Obekt_na_izpitvane_request obekt_na_izpitvane_request : listObektIzpit_request) {
			list.add(obekt_na_izpitvane_request.getName_obekt_na_izpitvane());
		}
		return list;
	}
	
	static void updateRequestToObIzpObject(Request request, List<Obekt_na_izpitvane_request> listObektIzpit_requet ) {
		List<Request_To_ObektNaIzpitvaneRequest> listRequestInBase = 
				Request_To_ObektNaIzpitvaneRequestDAO.getRequest_To_ObektNaIzpitvaneRequestByRequest(request);
		
			int i =0;
			for (Request_To_ObektNaIzpitvaneRequest request_To_ObektNaIzpitvaneRequest : listRequestInBase) {
				if(i<listObektIzpit_requet.size()){
				request_To_ObektNaIzpitvaneRequest.setObektNaIzp(listObektIzpit_requet.get(i));
				Request_To_ObektNaIzpitvaneRequestDAO.updateValueRequest_To_ObektNaIzpitvaneRequest(request_To_ObektNaIzpitvaneRequest);
				i++;
				}else{
					Request_To_ObektNaIzpitvaneRequestDAO.deleteRequest_To_ObektNaIzpitvaneRequest(request_To_ObektNaIzpitvaneRequest);	
				}
			}
			if(listRequestInBase.size()<listObektIzpit_requet.size()){
			for (int j = i; j < listObektIzpit_requet.size(); j++) {
				Request_To_ObektNaIzpitvaneRequest reqToObIzp = new Request_To_ObektNaIzpitvaneRequest(request,listObektIzpit_requet.get(j));
				Request_To_ObektNaIzpitvaneRequestDAO.setValueRequest_To_ObektNaIzpitvaneRequest(reqToObIzp);
			}
			}

		}
	

	private int getSelectedModelRow(JTable table) {
		return  table.convertRowIndexToModel(table.getSelectedRow());
		}

}
