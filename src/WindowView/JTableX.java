package WindowView;

import javax.swing.*;
import javax.swing.table.*;
import java.util.Hashtable;
import java.util.Vector;

 public class JTableX extends JTable
 {
     protected RowEditorModel rm;

     public JTableX()
     {
         super();
         rm = null;
     }

     public JTableX(TableModel tm)
     {
         super(tm);
         rm = null;
     }

     public JTableX(TableModel tm, TableColumnModel cm)
     {
         super(tm,cm);
         rm = null;
     }

     public JTableX(TableModel tm, TableColumnModel cm,
      ListSelectionModel sm)
     {
         super(tm,cm,sm);
         rm = null;
     }

     public JTableX(int rows, int cols)
     {
         super(rows,cols);
         rm = null;
     }

     public JTableX(final Vector rowData, final Vector columnNames)
     {
         super(rowData, columnNames);
         rm = null;
     }

     public JTableX(final Object[][] rowData, final Object[] colNames)
     {
         super(rowData, colNames);
         rm = null;
     }

     // new constructor
     public JTableX(TableModel tm, RowEditorModel rm)
     {
         super(tm,null,null);
         this.rm = rm;
     }

    public void setRowEditorModel(RowEditorModel rm)
     {
         this.rm = rm;
     }

     public RowEditorModel getRowEditorModel()
    {
         return rm;
     }
     public TableCellEditor getCellEditor(int row, int col)
     {
         TableCellEditor tmpEditor = null;
         if (rm!=null)
             tmpEditor = rm.getEditor(row);
         if (tmpEditor!=null)
             return tmpEditor;
         return super.getCellEditor(row,col);
     }
     
 	
 	
 	public class RowEditorModel
 	 {
 	      private Hashtable data;
 	      public RowEditorModel()
 	      {
 	          data = new Hashtable();
 	      }
 	     public void addEditorForRow(int row, TableCellEditor e )
 	     {
 	         data.put(new Integer(row), e);
 	     }
 	     public void removeEditorForRow(int row)
 	     {
 	         data.remove(new Integer(row));
 	     }
 	     public TableCellEditor getEditor(int row)
 	     {
 	         return (TableCellEditor)data.get(new Integer(row));
 	     }
 	 }
 }
