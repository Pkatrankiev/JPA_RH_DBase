package TableBeisicClassDBase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.compress.utils.Lists;

import Aplication.Ind_num_docDAO;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowViewAplication.AutoSuggestor;
import WindowViewAplication.SuggestionLabel;

public class ViewAddNewObject extends JDialog {
	private static final long serialVersionUID = 1L;

	Panel[] check_panel;
	static JLabel[] check_label;
	static JTextField[] check_TextFild;
	static JScrollPane[] check_ScrollPane;
	
	static JWindow popUpWindow ;
	static JPanel popUpPanel ;
	static FontMetrics metrics;

	@SuppressWarnings("rawtypes")
	public ViewAddNewObject(JFrame parent, String titleTable, Object[][] data_Table, String[] columnNames,
			Class[] types, String key, int lastRecord) {
		super(parent, titleTable, true);

		final JPanel contentPanel = new JPanel();
		List<String> listName =new ArrayList<>(Arrays.asList(columnNames)); 
		int row = columnNames.length;
		
	
		
		popUpWindow = new JWindow();
		popUpPanel = new JPanel();
		popUpWindow.getContentPane().add(popUpPanel);
		
		
		setBounds(100, 100, 400, 100 + (row * 25));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		contentPanel.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_1 = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("LabelText_Select"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel_1);

		 metrics = lblNewLabel_1.getFontMetrics(lblNewLabel_1.getFont());
		int maxLabelWidth = getMaxLabelWidth(listName, metrics) + 5;
		check_label = new JLabel[row];
		check_panel = new Panel[row];
		check_TextFild = new JTextField[row];
		
		int i = 0;
		for (String string : columnNames) {

			check_panel[i] = new Panel();
			panel.add(check_panel[i]);
			check_panel[i].setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			check_panel[i].setLayout(new BoxLayout(check_panel[i], BoxLayout.X_AXIS));

			check_label[i] = new JLabel(string);

			check_label[i].setPreferredSize(new Dimension(maxLabelWidth, 22));
			check_panel[i].add(check_label[i]);

			check_TextFild[i] = new JTextField();

			check_panel[i].add(check_TextFild[i]);
			
			check_TextFild_Listener(check_TextFild[i],getDictonariColum(i,data_Table),  maxLabelWidth);

			i++;
		}

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton(
					ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("OK_Btn_Text"));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
					addData(key);
					popUpWindow.setVisible(false);
					removeAll();
					dispose();
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}

		JButton cancelButton = new JButton(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				popUpWindow.setVisible(false);
				dispose();
			}

		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		setVisible(true);

	}

	

	private Object[] getDictonariColum(int i, Object[][] data_Table) {
		Object[] newMasive = new Object[data_Table.length];
		int row = data_Table.length;
		for (int j = 0; j < row; j++) {
			newMasive[j] = data_Table[j][i];
		}
		return newMasive;
	}



	private int getMaxLabelWidth(List<String> columnNames, FontMetrics metrics) {
		
	
		int max = 0;
		String maxStr = "";
		for (String string : columnNames) {
			if (max < string.length()) {
				max = string.length();
				maxStr = string;
			}
			
		}
		
		return metrics.stringWidth(maxStr);
	}

	private void addData(String key) {

		switch (key) {
		case "Ind_num_doc":
			System.out.println(check_TextFild[2].getText() + "  " + check_TextFild[1].getText());
			Ind_num_docDAO.setValueInd_num_doc(check_TextFild[2].getText(), check_TextFild[1].getText());
			break;

		default:
			break;
		}
	}

	public void componentMoved(ComponentEvent e) {
        
    }
	
	private void check_TextFild_Listener(JTextField textField, Object[] dictonary, int maxLabelWidth) {
		
		
		
		textField.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
//				popUpWindow(textField, dictonary, maxLabelWidth);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				popUpWindow(textField, dictonary, maxLabelWidth);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
//				popUpWindow(textField, dictonary, maxLabelWidth);
				
			}
		});
		
		textField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				popUpWindow(textField, dictonary, maxLabelWidth);
				
			}
		});
		
		
	textField.addKeyListener(new KeyListener() {
		
		
		@Override
		public void keyTyped(KeyEvent event) {
		}

		@Override
		public void keyReleased(KeyEvent event) {
			popUpWindow(textField, dictonary, maxLabelWidth);
		}

		@Override
		public void keyPressed(KeyEvent event) {
		       if (event.getKeyCode() == KeyEvent.VK_TAB) {
               System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
                	   transferFocus();
                  
               }
           

		}
	});
	
}
	
	private void popUpWindow(JTextField textField, Object[] dictonary, int maxLabelWidth) {
		float opacit = 0.86f;
		
		
		
		int xx = getLocation().x;
		int yy = getLocation().y;
		getSize();
		int height = getSize().height;
		
		popUpWindow.setVisible(false);
		popUpPanel.removeAll();
		
		if (textField.getText().length() >= 1) {
			List<String> list = getStrMas(textField.getText(), dictonary);
			int countList = list.size();
			
			int windowWidth = getMaxLabelWidth(list, metrics);
			int winwowHeight = countList*22;
			
			for (String object : list) {
				System.out.println("->" + object);
			}
			System.out.println("---" + list.size());
			JLabel lblNewLabel2 = new JLabel();
			
			for (String string : list) {
				lblNewLabel2 = new JLabel(string);
				popUpPanel.add(lblNewLabel2);
			
			}
			popUpPanel.revalidate();
			int x= textField.getX();
			int y= textField.getY();
			if(countList>0 && textField.isFocusable()){
							
				popUpWindow.setVisible(true);
				popUpWindow.setLocation(xx, yy+height);
				popUpWindow.setSize(windowWidth, winwowHeight);
				popUpWindow.setAlwaysOnTop(true);
				
				popUpWindow.setOpacity(opacit);
				System.out.println("windowWidth "+windowWidth+"  winwowHeight "+winwowHeight+"  x "+x+"  y "+y+"  xx "+xx+"  yy "+yy);
//				popUpPanel.repaint();
//				popUpPanel.revalidate();
				popUpWindow.repaint();
				popUpWindow.revalidate();
			}else{
				popUpWindow.setVisible(false);
//				popUpPanel.removeAll();
			}
		}else{
			popUpWindow.setVisible(false);
//			popUpPanel.removeAll();
		}
	
		
	}


	
	public List<String> getStrMas(String str, Object[] massiveO_I_R) {
		
		List<String> masStr = new ArrayList<String>();
		for (Object object : massiveO_I_R) {
			if (object.toString().toLowerCase().contains(str.toLowerCase())) {
				if(object instanceof Integer){
					masStr.add(Integer.toString((int)object));
				}else{
				masStr.add((String) object);
				}
			}

		}

		return masStr;

	}
	
	

	
}
