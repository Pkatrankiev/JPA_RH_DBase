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
		if (column == AddResultViewMetods.getDateAnaliz_Colum() || column == AddResultViewMetods.getDateHimObr_Colum()) {
			String date_choice = getDateFromDatePicker(table, column);
			for (int i = 0; i < OverallVariablesAddResults.getDataTable().length; i++) {
				table.setValueAt(date_choice, i, column);
			}
		}
		if (column == AddResultViewMetods.getQunt_Colum()) {
			String date_choice = getStringOfQuantyti(table, column);
			for (int i = 0; i < OverallVariablesAddResults.getDataTable().length; i++) {
				table.setValueAt(date_choice, i, column);
			}
		}

	}

	private String getDateFromDatePicker(JTable table, int col) {
		String date = "";
		if (col == AddResultViewMetods.getDateAnaliz_Colum()) {
			date = table.getValueAt(table.getSelectedRow(), AddResultViewMetods.getDateAnaliz_Colum()).toString();
		}
		if (col == AddResultViewMetods.getDateHimObr_Colum()) {
			date = table.getValueAt(table.getSelectedRow(), AddResultViewMetods.getDateHimObr_Colum()).toString();
		}
		final JFrame f = new JFrame();
		DatePicker dPicer = new DatePicker(f, false, date);
		String date_choice = dPicer.setPickedDate(false);
		return date_choice;
	}

	private String getStringOfQuantyti(JTable table, int col) {
		final JFrame f = new JFrame();
		String incertData = table.getValueAt(table.getSelectedRow(), col).toString();
		String string = (String) JOptionPane.showInputDialog(f, "Въведете стойност за всички редове", "",
				JOptionPane.PLAIN_MESSAGE, null, null, incertData);

		return string;
	}

}