package OldClases;


import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.RequestDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.Request;
import WindowView.Login;
import WindowView.RequestView;
import WindowView.RequestViewAplication;
//import WindowView.TablePrintDemo.MyTableModel;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

public class TableRequestListNew {
//	private static Request choiseRequest;
//	private static String[] values_I_P;
//	private static String[] values_O_I_R;
//
//	public static void TableRequestList(String[] columnNames, Object[][] data, Class[] types) {
//		JFrame frame = new JFrame();
//		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		final JTable table = new JTable();// new DefaultTableModel(rowData,
//											// columnNames));
//
//		table.addMouseListener(new MouseAdapter() {
//
//			public void mousePressed(MouseEvent e) {
//				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
//
//					int row = table.getSelectedRow();
//					int col = table.getSelectedColumn();
//					String reqCodeStr = table.getValueAt(table.getSelectedRow(), 0).toString();
//
//					if (reqCodeStr.startsWith("templ")) {
//						choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
//						RequestView reqView = new RequestView(Login.getCurentUser(), choiseRequest);
//						frame.setVisible(false);
//
//					} else {
//						RequestViewAplication.OpenRequestInWordDokTamplate(reqCodeStr);
//					}
//				}
//			}
//		});
//
//		TableFilterHeader tfh = new TableFilterHeader(table, AutoChoices.ENABLED);
//
//		JScrollPane scrollPane = new JScrollPane(table);
//		frame.add(scrollPane, BorderLayout.CENTER);
//		frame.setSize(1200, 800);
//		frame.setVisible(true);
//
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {
//
//					private Class[] types2 = types;
//
//					@Override
//					public Class getColumnClass(int columnIndex) {
//						return this.types2[columnIndex];
//					}
//
//					@Override
//					public boolean isCellEditable(int row, int column) {
//						if (column >= 20) {
//							return false;
//						} else {
//							return true;
//						}
//					}
//
//				};
//				table.setModel(dtm);
//
//			}
//		});
//	}
//
//	public static void TableRequestListEditable(String[] columnNames, Object[][] data, Class[] types) {
//		JFrame frame = new JFrame();
//		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		values_I_P = Izpitvan_produktDAO.getMasiveStringAllValueIzpitvan_produkt();
//		values_O_I_R = Obekt_na_izpitvane_requestDAO.getMasiveStringAllValueObekt_na_izpitvane();
//		final JTable table = new JTable();// new DefaultTableModel(rowData,
//											// columnNames));
//
//		table.addMouseListener(new MouseAdapter() {
//
//			public void mousePressed(MouseEvent e) {
//				if (e.getClickCount() == 4 && table.getSelectedRow() != -1) {
//
//					int row = table.getSelectedRow();
//					int col = table.getSelectedColumn();
//					System.out.println("selekt row:" + row + " col: " + col);
//					String reqCodeStr = table.getValueAt(table.getSelectedRow(), 0).toString();
//
//					if (reqCodeStr.startsWith("templ")) {
//						choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
//						RequestView reqView = new RequestView(Login.getCurentUser(), choiseRequest);
//						frame.setVisible(false);
//
//					} else {
//						RequestViewAplication.OpenRequestInWordDokTamplate(reqCodeStr);
//					}
//				}
//			}
//		});
//
//		TableFilterHeader tfh = new TableFilterHeader(table, AutoChoices.ENABLED);
//
//		JScrollPane scrollPane = new JScrollPane(table);
//		frame.add(scrollPane, BorderLayout.CENTER);
//		frame.setSize(1200, 800);
//		frame.setVisible(true);
//
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {
//
//					private Class[] types2 = types;
//
//					@Override
//					public Class getColumnClass(int columnIndex) {
//						return this.types2[columnIndex];
//					}
//
//					@Override
//					public boolean isCellEditable(int row, int column) {
//						if (column == 2 || column == 3) {
//							return true;
//						} else {
//							return false;
//						}
//					}
//
//				};
//				table.setModel(dtm);
//				table.setFillsViewportHeight(true);
//				setUp_I_P_Column(table, table.getColumnModel().getColumn(2));
//				setUp_O_I_R_Column(table, table.getColumnModel().getColumn(3));
//
//			}
//		});
//	}
//
//	public static void setUp_I_P_Column(JTable table, TableColumn I_P_Column) {
//		// Set up the editor for the sport cells.
//		JComboBox comboBox = new JComboBox(values_I_P);
//
//		comboBox.addItem("None of the above");
//		I_P_Column.setCellEditor(new DefaultCellEditor(comboBox));
//
//		// Set up tool tips for the sport cells.
//		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//		renderer.setToolTipText("Click for combo box");
//		I_P_Column.setCellRenderer(renderer);
//	}
//
//	public static void setUp_O_I_R_Column(JTable table, TableColumn O_I_R_Column) {
//		// Set up the editor for the sport cells.
//		JComboBox comboBox = new JComboBox(values_O_I_R);
//
//		comboBox.addItem("None of the above");
//		O_I_R_Column.setCellEditor(new DefaultCellEditor(comboBox));
//
//		// Set up tool tips for the sport cells.
//		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//		renderer.setToolTipText("Click for combo box");
//		O_I_R_Column.setCellRenderer(renderer);
//	}
//
//	public static Request getChoiceRequest() {
//		return choiseRequest;
//	}

}
