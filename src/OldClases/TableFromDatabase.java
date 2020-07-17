package OldClases;

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableFromDatabase extends JFrame
{
    public TableFromDatabase()
    {
        Vector<Object> columnNames = new Vector<Object>();
        Vector<Object> data = new Vector<Object>();

        try
        {
            //  Connect to an Access Database

            String driver = "com.mysql.jdbc.Driver";
//            String url = "jdbc:odbc:???";  // if using ODBC Data Source name
            String url =
                "jdbc:mysql://localhost:3306/rhdbase?characterEncoding=UTF-8";
            String userid = "root";
            String password = "root";

            Class.forName( driver );
            Connection connection = DriverManager.getConnection( url, userid, password );

            //  Read data from a table

            String sql = "Select * from IzpitvanPokazatel";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names

            for (int i = 1; i <= columns; i++)
            {
            	
            	System.out.println("Column Type Name of "+i+"st column: "+md.getClass());
                columnNames.addElement( md.getColumnName(i) );
            }

            //  Get row data

            while (rs.next())
            {
                Vector<Object> row = new Vector<Object>(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.addElement( rs.getObject(i) );
                }

                data.addElement( row );
            }

            rs.close();
            stmt.close();
            connection.close();
        }
        catch(Exception e)
        {
            System.out.println( e );
        }

        //  Create table with database data

        DefaultTableModel model = new DefaultTableModel(data, columnNames)
        {
        	@Override
            public Class getColumnClass(int column)
            {
                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };

		JTable table = new JTable( model );
        JScrollPane scrollPane = new JScrollPane( table );
        getContentPane().add( scrollPane );

        JPanel buttonPanel = new JPanel();
        getContentPane().add( buttonPanel, BorderLayout.SOUTH );
        
       
		JButton save = new JButton("save");
		buttonPanel.add(save);
		
    }

    public static void main(String[] args)
    {
        TableFromDatabase frame = new TableFromDatabase();
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible(true);
    }
}
