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
		String[] values = new String[list_All_I_P.size()];
		int i = 0;
		for (Izpitvan_produkt izpitvan_produkt : list_All_I_P) {
			values[i] = izpitvan_produkt.getName_zpitvan_produkt();
			i++;
		}
		System.out.println(list_All_I_P.size() + " /*//////////////////////////////////");
		final JTable table = new JTable();// new DefaultTableModel(rowData,
											// columnNames));

		table.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 4 && table.getSelectedRow() != -1) {

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
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(1200, 800);
		frame.setVisible(true);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {

					private Class[] types2 = types;

					public void setComboBoxColumn(JTable table, List<Izpitvan_produkt> list_All_I_P) {
						// Set up the editor for the sport cells.
						JComboBox comboBox = new JComboBox();
						for (Izpitvan_produkt izpitvan_produkt : list_All_I_P) {
							comboBox.addItem(izpitvan_produkt.getName_zpitvan_produkt());
						}
						TableColumn comboBoxColumn = table.getColumnModel().getColumn(2);
						comboBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));

						// Set up tool tips for the sport cells.
						DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
						renderer.setToolTipText("Click for combo box");
						comboBoxColumn.setCellRenderer(renderer);
					}

					@Override
					public Class getColumnClass(int columnIndex) {
						return this.types2[columnIndex];
					}

					@Override
					public boolean isCellEditable(int row, int column) {
						if (column != 2) {
			                return false;
			            } else {
			                return true;
			            }
					}

				};
				table.setModel(dtm);

			}
		});
	}

	private class myComboBoxEditor extends DefaultCellEditor {
		myComboBoxEditor(String[] items) {
			super(new JComboBox(items));
		}
	}

	private class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {
		public MyComboBoxRenderer(String[] items) {
			super(items);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				super.setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(table.getBackground());
			}
			setSelectedItem(value);
			return this;
		}
	}

	public static Request getChoiceRequest() {
		return choiseRequest;
	}

}
