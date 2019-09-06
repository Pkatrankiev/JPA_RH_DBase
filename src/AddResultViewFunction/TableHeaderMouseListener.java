package AddResultViewFunction;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import WindowView.DatePicker;

public class TableHeaderMouseListener extends MouseAdapter {

	private JTable table;

	public TableHeaderMouseListener(JTable table) {
		this.table = table;
	}

	public void mouseClicked(MouseEvent event) {
		Point point = event.getPoint();
		int column = table.columnAtPoint(point);
		if (column == AddresultViewMwetods.getDateAnaliz_Colum() || column == AddresultViewMwetods.getDateHimObr_Colum()) {
			String date_choice = getDateFromDatePicker(table, column);
			for (int i = 0; i < ÎverallVariables.getDataTable().length; i++) {
				table.setValueAt(date_choice, i, column);
			}
		}
		if (column == AddresultViewMwetods.getQunt_Colum()) {
			String date_choice = getStringOfQuantyti(table, column);
			for (int i = 0; i < ÎverallVariables.getDataTable().length; i++) {
				table.setValueAt(date_choice, i, column);
			}
		}

	}

	private String getDateFromDatePicker(JTable table, int col) {
		String date = "";
		if (col == AddresultViewMwetods.getDateAnaliz_Colum()) {
			date = table.getValueAt(table.getSelectedRow(), AddresultViewMwetods.getDateAnaliz_Colum()).toString();
		}
		if (col == AddresultViewMwetods.getDateHimObr_Colum()) {
			date = table.getValueAt(table.getSelectedRow(), AddresultViewMwetods.getDateHimObr_Colum()).toString();
		}
		final JFrame f = new JFrame();
		DatePicker dPicer = new DatePicker(f, false, date);
		String date_choice = dPicer.setPickedDate(false);
		return date_choice;
	}

	private String getStringOfQuantyti(JTable table, int col) {
		final JFrame f = new JFrame();
		String incertData = table.getValueAt(table.getSelectedRow(), col).toString();
		String string = (String) JOptionPane.showInputDialog(f, "Âúâåäåòå ñòîéíîñò çà âñè÷êè ðåäîâå", "",
				JOptionPane.PLAIN_MESSAGE, null, null, incertData);

		return string;
	}

}