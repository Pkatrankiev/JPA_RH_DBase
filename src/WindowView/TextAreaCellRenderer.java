package WindowView;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

class TextAreaCellRenderer implements TableCellRenderer {

    private JTextArea textArea = new JTextArea();

    public TextAreaCellRenderer() {
        super();
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        if (isSelected) {

            textArea.setForeground(table.getSelectionForeground());
            textArea.setBackground(table.getSelectionBackground());
        } else {
        
            textArea.setForeground(table.getForeground());
            textArea.setBackground(table.getBackground());
        }
   
        textArea.setFont(table.getFont());
        textArea.setText((value == null) ? "" : value.toString());
        return textArea;
    }
    public void updateRowHeights(int column, int width, JTable table){
        for (int row = 0; row < table.getRowCount(); row++) {
            int rowHeight = table.getRowHeight();
            Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
            Dimension d = comp.getPreferredSize();
            comp.setSize(new Dimension(width, d.height));
            d = comp.getPreferredSize();
            rowHeight = Math.max(rowHeight, d.height);
            table.setRowHeight(row, rowHeight);
            System.out.println("  rowHeight= "+rowHeight);
        }
    }
	
}
