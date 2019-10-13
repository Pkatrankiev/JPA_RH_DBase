package Table;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;




public class Add_TableHeaderMouseListener {

	public static void Add_TableHeaderMouseListener_(JTable table, String[] masiveTipeHeader, int countTableRow){
	
		JTableHeader header = table.getTableHeader();
	header.setReorderingAllowed(false);
	header.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent event) {
			Point point = event.getPoint();
			int col = table.columnAtPoint(point);
			String date_choice="";
			switch (masiveTipeHeader[col]) {
				
				case "DatePicker":
					 date_choice = Add_TableMouseListener.getDateFromDatePicker(table, col);
					
					break;
				case "Double_All":
					date_choice = getStringOfQuantyti(table, col);
					
					break;	
				}
			if(date_choice.length()>0){
				for (int i = 0; i <countTableRow; i++) {
					table.setValueAt(date_choice, i, col);
				}
			}
	
		}
			});

}
	
	private static String getStringOfQuantyti(JTable table, int col) {
		final JFrame f = new JFrame();
		String incertData = table.getValueAt(table.getSelectedRow(), col).toString();
		String string = (String) JOptionPane.showInputDialog(f, "Въведете стойност за всички редове", "",
				JOptionPane.PLAIN_MESSAGE, null, null, incertData);

		return string;
	}

	
}

	
