package LeftPanelInMainWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;


public class CreateLeftPanelInfo {
		
   
	public static void creatLeftPanel(JPanel under_panel_Left,  JLabel lblNewLabel) {

		List<List<LeftPanelStartWindowClass>> listleftPanelStartWindow = VariableFromLeftPanel.getListLeftPanelStartWindow();
		
		String month = CreateListLeftPanelStartWindowClass.getPreviousMesec(1);
		lblNewLabel.setText("Проби от програма периодичен мониторинг за м." + month);

		
		String inLabel = "";
		for (List<LeftPanelStartWindowClass> groupList : listleftPanelStartWindow) {
			System.out.println(groupList.get(0).getMonitoringGroup());
			String string = groupList.get(0).getMonitoringGroup();
			JPanel panel2 = new JPanel();
			panel2.setMaximumSize(new Dimension(32767, 20));
			under_panel_Left.add(panel2);
			panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			if(string.equals("Вода")){
				inLabel = " за м." + CreateListLeftPanelStartWindowClass.getPreviousMesec(2); ;
				
			}
			JLabel lbl_Grup_ = new JLabel(string + inLabel);
			panel2.add(lbl_Grup_);

			for (LeftPanelStartWindowClass list : groupList) {
				if (!list.getLblLabel_Code().isEmpty()) {
					createRowLeftPanel(under_panel_Left, list);
				}
			}
		}
	}

	
	private static JPanel createRowLeftPanel(JPanel under_panel_Left, LeftPanelStartWindowClass leftPanelStartWindow) {
		JPanel panel = new JPanel();
		under_panel_Left.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 1));
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.GRAY));
		panel.setMaximumSize(new Dimension(440, 16));
		panel.setAutoscrolls(true);

		JLabel lblLabel_Code = new JLabel(leftPanelStartWindow.getLblLabel_Code());
		lblLabel_Code.setPreferredSize(new Dimension(30, 14));
		panel.add(lblLabel_Code);

		JLabel lblLabel_Obect = new JLabel(leftPanelStartWindow.getLblLabel_Obect());
		lblLabel_Obect.setPreferredSize(new Dimension(200, 14));
		panel.add(lblLabel_Obect);

		JLabel lblLabel_Sample = new JLabel(leftPanelStartWindow.getLblLabel_Sample());
		lblLabel_Sample.setPreferredSize(new Dimension(40, 14));
		panel.add(lblLabel_Sample);

		JLabel lblLabel_Protokol = new JLabel(leftPanelStartWindow.getLblLabel_Protokol());
		lblLabel_Protokol.setPreferredSize(new Dimension(140, 14));
		panel.add(lblLabel_Protokol);

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}
		});

		return panel;
	}

	

	


	
	
}
