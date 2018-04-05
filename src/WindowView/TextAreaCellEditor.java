package WindowView;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;

abstract class TextAreaCellEditor implements TableCellEditor {

    private final JScrollPane scroll;
    private JTextArea textArea = new JTextArea();

    public TextAreaCellEditor() {
        scroll = new JScrollPane(textArea);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        //scroll.setViewportBorder(BorderFactory.createEmptyBorder());
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));
        KeyStroke enter = KeyStroke.getKeyStroke(
                KeyEvent.VK_ENTER, InputEvent.CTRL_MASK);
        textArea.getInputMap(JComponent.WHEN_FOCUSED).put(enter, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	        stopCellEditing();
            }
            
        });
    }

    @Override
    public Object getCellEditorValue() {
        return textArea.getText();
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {
    	
    	 updateRowHeights(column, table.getColumnModel().getColumn(column).getWidth(), table);
        System.out.println("2. getTableCellEditorComponent  rowHeight= ");
        textArea.setFont(table.getFont());
        textArea.setText((value != null) ? value.toString() : "");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                textArea.setCaretPosition(textArea.getText().length());
                textArea.requestFocusInWindow();
                System.out.println("4. invokeLater: getTableCellEditorComponent");
            }
        });
        return scroll;
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
	

	@Override
    public boolean isCellEditable(final EventObject e) {
        if (e instanceof MouseEvent) {
            return ((MouseEvent) e).getClickCount() >= 2;
        }
        System.out.println("1. isCellEditable");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (e instanceof KeyEvent) {
                    KeyEvent ke = (KeyEvent) e;
                    char kc = ke.getKeyChar();
                    if (Character.isUnicodeIdentifierStart(kc)) {
                        textArea.setText(textArea.getText() + kc);
                        System.out.println("3. invokeLater: isCellEditable");
                    }
                }
            }
        });
        return true;
    }

    //Copid from AbstractCellEditor
    protected EventListenerList listenerList = new EventListenerList();
    transient protected ChangeEvent changeEvent = null;

    @Override
    public boolean shouldSelectCell(EventObject e) {
    	
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        fireEditingStopped();
        return true;
    }

    @Override
    public void cancelCellEditing() {
        fireEditingCanceled();
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        listenerList.add(CellEditorListener.class, l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        listenerList.remove(CellEditorListener.class, l);
    }

    public CellEditorListener[] getCellEditorListeners() {
        return listenerList.getListeners(CellEditorListener.class);
    }

    protected void fireEditingStopped() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
// Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CellEditorListener.class) {
                // Lazily create the event:
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }
                ((CellEditorListener) listeners[i + 1]).editingStopped(changeEvent);
            }
        }
    }

    protected void fireEditingCanceled() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
// Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CellEditorListener.class) {
                // Lazily create the event:
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }
                ((CellEditorListener) listeners[i + 1]).editingCanceled(changeEvent);
            }
        }
    }
}