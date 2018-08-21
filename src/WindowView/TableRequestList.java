package WindowView;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.RequestDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.Request;
import WindowView.TablePrintDemo.MyTableModel;
import WindowViewAplication.RequestViewAplication;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;

public class TableRequestList {
	private static Request choiseRequest;

	public static void TableRequestList(String[] columnNames, Object[][] data, Class[] types) {
		JFrame frame = new JFrame();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		List<Izpitvan_produkt> list_All_I_P = Izpitvan_produktDAO.getInListAllValueIzpitvan_produkt();
		System.out.println(list_All_I_P.size()+" /*//////////////////////////////////");
		final JTable table = new JTable();// new DefaultTableModel(rowData,
											// columnNames));

	

		table.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {

					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
					System.out.println("selekt row:" + row + " col: " + col);
					String reqCodeStr = table.getValueAt(table.getSelectedRow(), 0).toString();

					if (reqCodeStr.startsWith("templ")) {
						choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
						RequestView reqView = new RequestView(Login.getCurentUser(), choiseRequest);
						frame.setVisible(false);

					} else {
						RequestViewAplication.OpenRequestInWordDokTamplate(reqCodeStr);
					}
				}
			}
		});

		TableFilterHeader tfh = new TableFilterHeader(table, AutoChoices.ENABLED);

		JScrollPane scrollPane = new JScrollPane(table);
		setComboBoxColumn(table, table.getColumnModel().getColumn(0), list_All_I_P);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(1200, 800);
		frame.setVisible(true);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {

					private Class[] types2 = types;

					@Override
					public Class getColumnClass(int columnIndex) {
						return this.types2[columnIndex];
					}

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}

				};
				table.setModel(dtm);

			}
		});
	}

	public static void setComboBoxColumn(JTable table, TableColumn comboBoxColumn,
			List<Izpitvan_produkt> list_All_I_P) {
		// Set up the editor for the sport cells.
		JComboBox comboBox = new JComboBox();
		for (Izpitvan_produkt izpitvan_produkt : list_All_I_P) {
			comboBox.addItem(izpitvan_produkt.getName_zpitvan_produkt());
		}
		comboBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));

		// Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for combo box");
		comboBoxColumn.setCellRenderer(renderer);
	}

	public static Request getChoiceRequest() {
		return choiseRequest;
	}

}
