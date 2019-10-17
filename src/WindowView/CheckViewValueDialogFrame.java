package WindowView;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Panel;
import java.util.List;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Component;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;

import Aplication.DobivDAO;

import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class CheckViewValueDialogFrame extends JDialog {
	private JPointGraph2D chart_1;
	private JPanel panelGraphic;
	private int  maxViewVolume = 15;

	public CheckViewValueDialogFrame(JFrame parent,
			List<CheckResultClass> listCheckResultObject, Double check_actv_value, Double check_mda,  TranscluentWindow round) {
		super(parent, "Проверка на резултати", true);

		int maxCountList = listCheckResultObject.size();
		if(maxViewVolume < maxCountList){
			listCheckResultObject = listCheckResultObject.subList(maxCountList-maxViewVolume, maxCountList);
		}
		
		setSize(500, 400);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setFocusable(true);
		Panel[] panel_Value = new Panel[listCheckResultObject.size()+2];
		JLabel[] lblReuqestCode = new JLabel[listCheckResultObject.size()+2];
		JLabel[] lblString_MDA = new JLabel[listCheckResultObject.size()+2];
		JLabel[] lblString_Value = new JLabel[listCheckResultObject.size()+2];

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton cancellButton = new JButton("Отказ");
		cancellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				setVisible(false);
			}
		});

		buttonPane.add(cancellButton);
//		getRootPane().setDefaultButton(cancellButton);
		
		JButton okButton = new JButton("Покажи");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
//		getRootPane().setDefaultButton(okButton);
		JLabel l = new JLabel();
				buttonPane.add(l);
//		getRootPane().setDefaultButton(l);
	
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel PanelValue = new JPanel();
		PanelValue.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setRowHeaderView(PanelValue);
		PanelValue.setLayout(new BoxLayout(PanelValue, BoxLayout.Y_AXIS));

		JPanel panel_ValueLabel = new JPanel();
		panel_ValueLabel.setPreferredSize(new Dimension(210, 20));
		PanelValue.add(panel_ValueLabel);
		panel_ValueLabel.setLayout(new BoxLayout(panel_ValueLabel, BoxLayout.X_AXIS));
		JLabel lblStrReuqestCode = new JLabel("№ заявка");
		if(check_mda==null){
		lblStrReuqestCode.setText("Стандарт");	
		}
		
		lblStrReuqestCode.setMaximumSize(new Dimension(70, 16));
		lblStrReuqestCode.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_ValueLabel.add(lblStrReuqestCode);
		lblStrReuqestCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblStrReuqestCode.setPreferredSize(new Dimension(70, 20));

		
		JLabel lblStringValue = new JLabel("Value");
		lblStringValue.setMaximumSize(new Dimension(70, 16));
		lblStringValue.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_ValueLabel.add(lblStringValue);
		lblStringValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblStringValue.setPreferredSize(new Dimension(70, 20));

		if(check_mda!=null){
		JLabel lblStringMDA = new JLabel("MDA");
		lblStringMDA.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblStringMDA.setMaximumSize(new Dimension(70, 16));
		panel_ValueLabel.add(lblStringMDA);
		lblStringMDA.setHorizontalAlignment(SwingConstants.CENTER);
		lblStringMDA.setPreferredSize(new Dimension(70, 20));
		
		}
		
		NumberFormat frm = new DecimalFormat("0.00E00");

		double[] yData_Value = new double[listCheckResultObject.size()+1];
		double[] yData_MDA = new double[listCheckResultObject.size()+1];
		CheckResultClass checkResultClass;
		Double value = null;
		Double mda = null;
		
		Color col = Color.BLACK;
		for (int i = 0; i < listCheckResultObject.size()+2; i++) {
			String str_value = "", str_mda = "", str_code = "";
			if(i<listCheckResultObject.size())	{
		  checkResultClass = listCheckResultObject .get(i);
		  value = checkResultClass.getValue();
		  mda = checkResultClass.getMda();
		  yData_Value[i] =value;
		  str_code = checkResultClass.getRequestCode()+"";
		  if(check_mda!=null){
		  yData_MDA[i] = mda;
		  str_mda = frm.format(mda);
		  
		  }else{
			  str_code = DobivDAO.getDobivById(checkResultClass.getRequestCode()).getCode_Standart();  
		  }
		  
		 
		  str_value = frm.format(value);
		 
			}else {
				if(i==listCheckResultObject.size()+1) {
					 str_value = frm.format(check_actv_value);
					 if(check_mda!=null){
					  str_mda = frm.format(check_mda);	
					 }
					  col = Color.RED;
				}
			}
		
			panel_Value[i] = new Panel();
			panel_Value[i].setPreferredSize(new Dimension(210, 20));
			PanelValue.add(panel_Value[i]);
			panel_Value[i].setLayout(new BoxLayout(panel_Value[i], BoxLayout.X_AXIS));

			lblReuqestCode[i] = new JLabel(str_code);
			lblReuqestCode[i].setToolTipText(str_code);
			lblReuqestCode[i].setForeground(col);
			lblReuqestCode[i].setMaximumSize(new Dimension(70, 16));
			lblReuqestCode[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			panel_Value[i].add(lblReuqestCode[i]);
			lblReuqestCode[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblReuqestCode[i].setPreferredSize(new Dimension(70, 20));
			int k =i;
			lblReuqestCode[i].addMouseListener(new MouseAdapter() {
				@Override
			public void mouseExited(MouseEvent e) {
					lblReuqestCode[k].setForeground(Color.black);
//					setSize(500, 400);
//					paintGraph(check_mda, scrollPane, yData_Value, yData_MDA, listCheckResultObject.size());
//					revalidate();
//					repaint();
			}
				@Override
			public void mouseEntered(MouseEvent e) {
				 panelGraphic = paintGraph(check_mda, scrollPane, yData_Value, yData_MDA, k);
//					setSize(500, 400);
//					revalidate();
//					repaint();	
				lblReuqestCode[k].setForeground(Color.red);
				
				
				
				
			}
				@Override
				public void mouseReleased(MouseEvent e) {}

				public void mousePressed(MouseEvent e) {
//					 paintGraph(check_mda, scrollPane, yData_Value, yData_MDA, k);
//					setSize(500, 400);
					scrollPane.setViewportView(panelGraphic);
					revalidate();
					repaint();
					
					
				}
			});
			
			
			lblString_Value[i] = new JLabel(str_value);
			lblString_Value[i].setForeground(col);
			lblString_Value[i].setMaximumSize(new Dimension(70, 16));
			lblString_Value[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			panel_Value[i].add(lblString_Value[i]);
			lblString_Value[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblString_Value[i].setPreferredSize(new Dimension(70, 20));

			if(check_mda!=null){
			lblString_MDA[i] = new JLabel(str_mda);
			lblString_MDA[i].setForeground(col);
			lblString_MDA[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			lblString_MDA[i].setMaximumSize(new Dimension(70, 16));
			panel_Value[i].add(lblString_MDA[i]);
			lblString_MDA[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblString_MDA[i].setPreferredSize(new Dimension(70, 20));
			}
			
		}
		  yData_Value[listCheckResultObject.size()] = check_actv_value;
		  if(check_mda!=null){
		  yData_MDA[listCheckResultObject.size()] = check_mda;
		  }
		  
		  
		panelGraphic = paintGraph(check_mda, scrollPane, yData_Value, yData_MDA, yData_Value.length);
//		JButton falseButton = new JButton();
//		falseButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				panelGraphic.revalidate();
//				panelGraphic.repaint();
//			}
//		});
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				setVisible(false);	
				revalidate();
				repaint();
			}
		});
	
		setSize(500, 401);
		scrollPane.setViewportView(panelGraphic);
		panelGraphic.revalidate();
		panelGraphic.repaint();
		round.StopWindow();
		setVisible(true);
		setSize(501, 401);
		revalidate();
		repaint();
//		SwingUtilities.getWindowAncestor( this ).repaint(); 
//		okButton.getModel().setPressed(true);
//		falseButton.doClick();	
		
	}

	private JPanel paintGraph(Double check_mda, JScrollPane scrollPane, double[] yData_Value, double[] yData_MDA, int choice) {
		JPanel panelGraphic = new JPanel();
//		scrollPane.setViewportView(panelGraphic);
		panelGraphic.setLayout(new BoxLayout(panelGraphic, BoxLayout.Y_AXIS));

		JPanel panel_Graph_Value = new JPanel();
		panel_Graph_Value.setLayout(new BoxLayout(panel_Graph_Value, BoxLayout.X_AXIS));
		JPointGraph2D chart ;
		if(check_mda!=null){
			chart = new JPointGraph2D(yData_Value, "Активност", choice);
		}else{
				chart = new JPointGraph2D(yData_Value, "Добив", choice);	
			}
		chart.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), Color.GRAY, null, null));
		chart.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		chart.setLineColor(new Color(0, 28, 28));
		panel_Graph_Value.add(chart);
		panelGraphic.add(panel_Graph_Value);
		if(check_mda!=null){
		JPanel panel_Graph_MDA = new JPanel();
		panel_Graph_MDA.setLayout(new BoxLayout(panel_Graph_MDA, BoxLayout.X_AXIS));
		chart_1 = new JPointGraph2D(yData_MDA, "MDA", choice);
		chart_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		chart_1.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		chart_1.setLineColor(new Color(0, 28, 28));
		panel_Graph_MDA.add(chart_1);
		panelGraphic.add(panel_Graph_MDA);
		}
		return panelGraphic;
	}

	
}
