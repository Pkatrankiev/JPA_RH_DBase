package Reference;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import AddResultViewFunction.AddResultViewMetods;
import ExcelFilesFunction.CreateExcelFile;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import GlobalVariable.ResourceLoader;
import WindowView.TranscluentWindow;
import javax.swing.border.LineBorder;

public class MounthlyReferenceForCNRDWater_Table extends JDialog {

	private static final long serialVersionUID = 1L;
	private static Object[][] valueDataTable;
	private static  List<String> listSimbolNuclide;
	private static JButton  btnReCalculateButton;
	
	static JLabel lblVolumeNuclide ;
	static JLabel lblVolumeIzhvarlenaActivyty ;
	static JLabel lblVolumeKombNeopred ;
	static JLabel lblVolumeMDA ;
	static JLabel lblValueSumActivyty ;
	static JLabel lblValueIznverlenObshtObem ;
	static JLabel lblVolumeSummObemnaActivyty ;
	static JLabel lblVolumePercentKontrolnoNivo ;
	static JLabel lblVolumeMaxObemnaActivyty ;
	static DecimalFormat df ;
	static DecimalFormat kk ;
	static JPanel top_panel;
	static int[] columnWith =  {143,90,96,70,95,85,80,95,110};
	
	public MounthlyReferenceForCNRDWater_Table(JFrame parent, TranscluentWindow round, String frame_name, String mount_name,
			int godina, Object[][] masiveValueDataTable, String[] columnNames, List<String> listNuclideSimbol) {
		super(parent, frame_name, true);

		JTable table = null;
		valueDataTable = masiveValueDataTable;
		listSimbolNuclide = listNuclideSimbol;
		df = new DecimalFormat("0.00E00");
		
		
		kk = new DecimalFormat("0.00E00");
		kk.setRoundingMode(RoundingMode.HALF_UP);
		
		boolean NoReport = false;
		int countRow = valueDataTable.length;
		int widshRow = 20;

		String labelNuclide = "";
		String labelIzhvarlenaActyvity = "";
		String labelKombinNeopr = "";
		String labelMDA = "";
		String labelSumActivyty = "";
		String labelIzhvarlenObshtObem = "";
		String labelSumObemnaActivyty = "";
		String labelPercentKontrolnoNivo = "";
		String labelMaxObemnaActivyty = "";

		
		Object[][] DataValue = ceateDataValue(valueDataTable, listNuclideSimbol);
		
//		DataValue = convertValueToString(DataValue, 1);

		valueDataTable = convertValueToString(valueDataTable, 4);
		String[] columnNameDataValue = new String[9];
		
		if (countRow < 2) {
			labelNuclide = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_ReportString");
			NoReport = true;
		} else {
			
			labelNuclide = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWaterTable_Nuclide");
			columnNameDataValue[0] = labelNuclide.replace("<html>", " ").replace("</html>", " ")
					.replace("<br>", " ").replace("<center>","").replace("</center>","");
			
			labelIzhvarlenaActyvity = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWaterTable_IzhvActivnost");
			columnNameDataValue[1] = labelIzhvarlenaActyvity.replace("<html>", " ").replace("</html>", " ")
					.replace("<br>", " ").replace("<center>","").replace("</center>","");
			
			labelKombinNeopr = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWaterTable_KombiniranaNeopred");
			columnNameDataValue[2] = labelKombinNeopr.replace("<html>", " ").replace("</html>", " ").replace("<br>",
					" ").replace("<center>","").replace("</center>","");
			
			labelMDA = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWaterTable_MDA");
			columnNameDataValue[3] = labelMDA.replace("<html>", " ").replace("</html>", " ").replace("<br>", " ")
					.replace("<center>","").replace("</center>","");
			
			labelSumActivyty = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWaterTable_IzhvSumarnaActivnost");
			columnNameDataValue[4] = labelSumActivyty.replace("<html>", " ").replace("</html>", " ").replace("<br>",
					" ").replace("<center>","").replace("</center>","");
			
			labelIzhvarlenObshtObem = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWaterTable_ObshtObem");
			columnNameDataValue[5] = labelIzhvarlenObshtObem.replace("<html>", " ").replace("</html>", " ")
					.replace("<br>", " ").replace("<center>","").replace("</center>","");
			
			labelSumObemnaActivyty = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWaterTable_SumarnaObemnaActivnost");
			columnNameDataValue[6] = labelSumObemnaActivyty.replace("<html>", " ").replace("</html>", " ")
					.replace("<br>", " ").replace("<center>","").replace("</center>","");
		
			labelMaxObemnaActivyty = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWaterTable_MaxObemnaActivnostZaMeseca");
			columnNameDataValue[7] = labelMaxObemnaActivyty.replace("<html>", " ").replace("</html>", " ")
					.replace("<br>", " ").replace("<center>","").replace("</center>","");

			labelPercentKontrolnoNivo = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWaterTable_PercentKontrolnoNivo");
			columnNameDataValue[8] = labelPercentKontrolnoNivo.replace("<html>", " ").replace("</html>", " ")
					.replace("<br>", " ").replace("<center>","").replace("</center>","");

		

			table = CreateDefaultTable(valueDataTable, columnNames);

		}

		top_panel = new JPanel();
		top_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		top_panel.setSize(new Dimension(2, 0));
		getContentPane().add(top_panel, BorderLayout.NORTH);
		GridBagLayout gbl_top_panel = new GridBagLayout();
		LineBorder.createBlackLineBorder();
		
	
		
		gbl_top_panel.columnWidths = new int[] { 53, 71, 63, 66, 60, 74, 0, 0, 0, 0 };
		gbl_top_panel.rowHeights = new int[] { 19, 24, 0 };
		gbl_top_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_top_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		top_panel.setLayout(gbl_top_panel);

		JLabel LabelNuclide = new JLabel(labelNuclide);
		LabelNuclide.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_LabelNuclide = new GridBagConstraints();
		gbc_LabelNuclide.insets = new Insets(0, 0, 5, 5);
		gbc_LabelNuclide.gridx = 0;
		gbc_LabelNuclide.gridy = 0;
		top_panel.add(LabelNuclide, gbc_LabelNuclide);
		if (!NoReport) {
			JLabel LabelIzhvarlenaActyvity = new JLabel(labelIzhvarlenaActyvity);
			LabelIzhvarlenaActyvity.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_LabelIzhvarlenaActyvity = new GridBagConstraints();
			gbc_LabelIzhvarlenaActyvity.insets = new Insets(0, 0, 5, 5);
			gbc_LabelIzhvarlenaActyvity.gridx = 1;
			gbc_LabelIzhvarlenaActyvity.gridy = 0;
			top_panel.add(LabelIzhvarlenaActyvity, gbc_LabelIzhvarlenaActyvity);

			JLabel LabelKombinNeopr = new JLabel(labelKombinNeopr);
			LabelKombinNeopr.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_LabelKombinNeopr = new GridBagConstraints();
			gbc_LabelKombinNeopr.insets = new Insets(0, 0, 5, 5);
			gbc_LabelKombinNeopr.gridx = 2;
			gbc_LabelKombinNeopr.gridy = 0;
			top_panel.add(LabelKombinNeopr, gbc_LabelKombinNeopr);

			JLabel LabelMDA = new JLabel(labelMDA);
			LabelMDA.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_LabelMDA = new GridBagConstraints();
			gbc_LabelMDA.insets = new Insets(0, 0, 5, 5);
			gbc_LabelMDA.gridx = 3;
			gbc_LabelMDA.gridy = 0;
			top_panel.add(LabelMDA, gbc_LabelMDA);

			JLabel LabelSumActivyty = new JLabel(labelSumActivyty);
			LabelSumActivyty.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_LabelSumActivyty = new GridBagConstraints();
			gbc_LabelSumActivyty.insets = new Insets(0, 0, 5, 5);
			gbc_LabelSumActivyty.gridx = 4;
			gbc_LabelSumActivyty.gridy = 0;
			top_panel.add(LabelSumActivyty, gbc_LabelSumActivyty);

			JLabel LabelIzhvarlenObshtObem = new JLabel(labelIzhvarlenObshtObem);
			GridBagConstraints gbc_LabelIzhvarlenObshtObem = new GridBagConstraints();
			gbc_LabelIzhvarlenObshtObem.insets = new Insets(0, 0, 5, 5);
			gbc_LabelIzhvarlenObshtObem.gridx = 5;
			gbc_LabelIzhvarlenObshtObem.gridy = 0;
			top_panel.add(LabelIzhvarlenObshtObem, gbc_LabelIzhvarlenObshtObem);

			JLabel LabelSumObemnaActivyty = new JLabel(labelSumObemnaActivyty);
			GridBagConstraints gbc_LabelSumObemnaActivyty = new GridBagConstraints();
			gbc_LabelSumObemnaActivyty.insets = new Insets(0, 0, 5, 5);
			gbc_LabelSumObemnaActivyty.gridx = 6;
			gbc_LabelSumObemnaActivyty.gridy = 0;
			top_panel.add(LabelSumObemnaActivyty, gbc_LabelSumObemnaActivyty);

			

			JLabel LabelMaxObemnaActivyty = new JLabel(labelMaxObemnaActivyty);
			GridBagConstraints gbc_LabelMaxObemnaActivyty = new GridBagConstraints();
			gbc_LabelMaxObemnaActivyty.insets = new Insets(0, 0, 5, 0);
			gbc_LabelMaxObemnaActivyty.gridx = 7;
			gbc_LabelMaxObemnaActivyty.gridy = 0;
			top_panel.add(LabelMaxObemnaActivyty, gbc_LabelMaxObemnaActivyty);
			
			JLabel LabelPercentKontrolnoNivo = new JLabel(labelPercentKontrolnoNivo);
			GridBagConstraints gbc_LabelPercentKontrolnoNivo = new GridBagConstraints();
			gbc_LabelPercentKontrolnoNivo.insets = new Insets(0, 0, 5, 5);
			gbc_LabelPercentKontrolnoNivo.gridx = 8;
			gbc_LabelPercentKontrolnoNivo.gridy = 0;
			top_panel.add(LabelPercentKontrolnoNivo, gbc_LabelPercentKontrolnoNivo);

			lblVolumeNuclide = new JLabel();
			GridBagConstraints gbc_lblVolumeNuclide = new GridBagConstraints();
			gbc_lblVolumeNuclide.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumeNuclide.gridx = 0;
			gbc_lblVolumeNuclide.gridy = 1;
			top_panel.add(lblVolumeNuclide, gbc_lblVolumeNuclide);

			lblVolumeIzhvarlenaActivyty = new JLabel();
			GridBagConstraints gbc_lblVolumeIzhvarlenaActivyty = new GridBagConstraints();
			gbc_lblVolumeIzhvarlenaActivyty.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumeIzhvarlenaActivyty.gridx = 1;
			gbc_lblVolumeIzhvarlenaActivyty.gridy = 1;
			top_panel.add(lblVolumeIzhvarlenaActivyty, gbc_lblVolumeIzhvarlenaActivyty);

			lblVolumeKombNeopred = new JLabel();
			GridBagConstraints gbc_lblVolumeKombNeopred = new GridBagConstraints();
			gbc_lblVolumeKombNeopred.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumeKombNeopred.gridx = 2;
			gbc_lblVolumeKombNeopred.gridy = 1;
			top_panel.add(lblVolumeKombNeopred, gbc_lblVolumeKombNeopred);

			lblVolumeMDA = new JLabel();
			GridBagConstraints gbc_lblVolumeMDA = new GridBagConstraints();
			gbc_lblVolumeMDA.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumeMDA.gridx = 3;
			gbc_lblVolumeMDA.gridy = 1;
			top_panel.add(lblVolumeMDA, gbc_lblVolumeMDA);

			lblValueSumActivyty = new JLabel();
			GridBagConstraints gbc_lblValueSumActivyty = new GridBagConstraints();
			gbc_lblValueSumActivyty.insets = new Insets(0, 0, 0, 5);
			gbc_lblValueSumActivyty.gridx = 4;
			gbc_lblValueSumActivyty.gridy = 1;
			top_panel.add(lblValueSumActivyty, gbc_lblValueSumActivyty);

			lblValueIznverlenObshtObem = new JLabel();
			GridBagConstraints gbc_lblValueIznverlenObshtObem = new GridBagConstraints();
			gbc_lblValueIznverlenObshtObem.insets = new Insets(0, 0, 0, 5);
			gbc_lblValueIznverlenObshtObem.gridx = 5;
			gbc_lblValueIznverlenObshtObem.gridy = 1;
			top_panel.add(lblValueIznverlenObshtObem, gbc_lblValueIznverlenObshtObem);

			lblVolumeSummObemnaActivyty = new JLabel();
			GridBagConstraints gbc_lblVolumeSummObemnaActivyty = new GridBagConstraints();
			gbc_lblVolumeSummObemnaActivyty.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumeSummObemnaActivyty.gridx = 6;
			gbc_lblVolumeSummObemnaActivyty.gridy = 1;
			top_panel.add(lblVolumeSummObemnaActivyty, gbc_lblVolumeSummObemnaActivyty);
			
			
			lblVolumeMaxObemnaActivyty = new JLabel();
			GridBagConstraints gbc_lblVolumeMaxObemnaActivyty = new GridBagConstraints();
			gbc_lblVolumeMaxObemnaActivyty.gridx = 7;
			gbc_lblVolumeMaxObemnaActivyty.gridy = 1;
			top_panel.add(lblVolumeMaxObemnaActivyty, gbc_lblVolumeMaxObemnaActivyty);

			lblVolumePercentKontrolnoNivo = new JLabel();
			GridBagConstraints gbc_lblVolumePercentKontrolnoNivo = new GridBagConstraints();
			gbc_lblVolumePercentKontrolnoNivo.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumePercentKontrolnoNivo.gridx = 8;
			gbc_lblVolumePercentKontrolnoNivo.gridy = 1;
			top_panel.add(lblVolumePercentKontrolnoNivo, gbc_lblVolumePercentKontrolnoNivo);

			

			
			
			JScrollPane scrollPane = new JScrollPane(table);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			
			viewLblVolume ( DataValue);
		}
		setSize(900, 220 + countRow * widshRow);
		setLocationRelativeTo(null);

		round.StopWindow();

		JPanel panel_Btn = new JPanel();
		panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		getContentPane().add(panel_Btn, BorderLayout.SOUTH);
		panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnReCalculateButton = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_CheckKorektExcelFile"));
		btnReCalcListener(btnReCalculateButton,  parent,  frame_name,
				 masiveValueDataTable,  columnNames,  listNuclideSimbol, mount_name, godina) ;
		
		if (!NoReport) {
			JButton btnExportButton = new JButton("export");
			panel_Btn.add(btnExportButton);
			
			btnExportListener(frame_name, btnExportButton, table, columnNameDataValue, listNuclideSimbol, mount_name, panel_Btn);

		panel_Btn.add(btnReCalculateButton);
		
		}
		
		
		
		JButton btnCancel = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"));
		panel_Btn.add(btnCancel);

		btnCancelListener(btnCancel);

		setVisible(true);
	}

	private static void viewLblVolume ( Object[][] DataValue){
		
		DataValue = convertValueToString(DataValue, 1);
		
	
		
		for (int i = 0; i < DataValue.length; i++) {
			if (i == 0) {
				DataValue[0][0] = "<html><center>" + DataValue[i][0] + "<br>";
				DataValue[0][1] = "<html><center>" + DataValue[i][1] + "<br>";
				DataValue[0][2] = "<html><center>" + DataValue[i][2] + "<br>";
				DataValue[0][3] = "<html><center>" + DataValue[i][3] + "<br>";
			} else {
				DataValue[0][0] = (String) DataValue[0][0] + DataValue[i][0] + "<br>";
				DataValue[0][1] = (String) DataValue[0][1] + DataValue[i][1] + "<br>";
				DataValue[0][2] = (String) DataValue[0][2] + DataValue[i][2] + "<br>";
				DataValue[0][3] = (String) DataValue[0][3] + DataValue[i][3] + "<br>";
			}

		}
		DataValue[0][0] = (String) DataValue[0][0] + "</center></html>";
		DataValue[0][1] = (String) DataValue[0][1] + "</center></html>";
		DataValue[0][2] = (String) DataValue[0][2] + "</center></html>";
		DataValue[0][3] = (String) DataValue[0][3] + "</center></html>";
		
		lblVolumeNuclide.setText(DataValue[0][0].toString());
		lblVolumeIzhvarlenaActivyty .setText(DataValue[0][1].toString());
		lblVolumeKombNeopred .setText(DataValue[0][2].toString());
		lblVolumeMDA.setText(DataValue[0][3].toString());
		lblValueSumActivyty.setText(DataValue[0][4].toString());
		lblValueIznverlenObshtObem.setText(DataValue[0][5].toString());
		lblVolumeSummObemnaActivyty.setText(DataValue[0][6].toString());
		lblVolumeMaxObemnaActivyty.setText(DataValue[0][7].toString());
		lblVolumePercentKontrolnoNivo.setText(DataValue[0][8].toString());
		
		top_panel.repaint();
	}
	
	
	private static Object[][] ceateDataValue(Object[][] DataTableValue, List<String> listNuclideSimbol) {
		Object[][] DataValue = new Object[listNuclideSimbol.size()][9];

		for (int i = 0; i < DataValue.length; i++) {
			for (int j = 0; j < 9; j++) {
				if (j ==0) {
					DataValue[i][j] = "";
				} else {
					DataValue[i][j] = 0.0;
				}
			}

		}
		int kontrolnoNivo = 11100;
		Double k = 0.0;
		Double maxSpecifActivyty = 0.0, sumActivyty = 0.0,  valActivyty = 0.0;
		String previosProtokil = "";
		for (int row = 0; row < DataTableValue.length; row++) {
			if(!DataTableValue[row][2].toString().isEmpty()){
			DataValue[0][5] = (Double) DataValue[0][5] +
					Double.parseDouble(DataTableValue[row][2].toString());
			}
			if(DataTableValue[row][4].toString().isEmpty()){
				valActivyty = 0.0;
			}else{
				valActivyty = Double.parseDouble(DataTableValue[row][4].toString());
				
			}
			if (! DataTableValue[row][0].toString().isEmpty() && previosProtokil.isEmpty()) {
				
				sumActivyty = valActivyty;
				
			}else{
				sumActivyty = sumActivyty + valActivyty;
			
				}
				previosProtokil = DataTableValue[row][0].toString();
				if (maxSpecifActivyty < sumActivyty) {
					maxSpecifActivyty = sumActivyty;
				}

			for (int i = 0; i < listNuclideSimbol.size(); i++) {

				if (DataTableValue[row][3].toString().equals(listNuclideSimbol.get(i))) {
					if(!DataTableValue[row][4].toString().isEmpty()){
					DataValue[i][1] = (Double) DataValue[i][1] + Double.parseDouble(DataTableValue[row][7].toString());
					DataValue[0][4] = (Double) DataValue[0][4] + Double.parseDouble(DataTableValue[row][7].toString());
					DataValue[0][6] = (Double) DataValue[0][6] + Double.parseDouble(DataTableValue[row][4].toString());
					if(!DataTableValue[row][7].toString().isEmpty()){
					k = Double.parseDouble(DataTableValue[row][8].toString()) / 2;
					DataValue[i][2] = Double.parseDouble(DataValue[i][2].toString()) + (k * k);
					}
					}
					System.out.println(Double.parseDouble(DataValue[i][3].toString()) +"  //  "+
					Double.parseDouble( DataTableValue[row][6].toString()));
					if (Double.parseDouble(DataValue[i][3].toString()) < Double.parseDouble( DataTableValue[row][6].toString())) {
						DataValue[i][3] = Double.parseDouble(DataTableValue[row][6].toString());
					}
					
				}
			}

		}
		for (int i = 0; i < DataValue.length; i++) {
			if (i == 0) {
				
				DataValue[i][7] = maxSpecifActivyty/1000;
				DataValue[i][8] = ((Double) maxSpecifActivyty * 100) / kontrolnoNivo;
			}
			DataValue[i][0] = listNuclideSimbol.get(i);
			DataValue[i][2] = (Math.sqrt((Double) DataValue[i][2]))*100 / (Double) DataValue[i][1];
		}
		
		 System.out.println("  *****************************************");
		return DataValue;
	}

	
	
	private static Object[][] convertValueToString(Object[][] DataValue, int indexStartNumber) {
		
		for (int i = 0; i < DataValue.length; i++) {
			for (int j = 0; j < 9; j++) {
				if (j >= indexStartNumber) {
					
					DataValue[i][j] = convertToStr(DataValue[i][j]);
					
				}
			
			}

		}
		return DataValue;
	}

	private static Object convertToStr(Object obj) {
		System.out.println(obj);
//		try {
		if(( (Double) obj).isNaN() ){
			obj = "";
		}
			try {
			if (Double.parseDouble(obj.toString()) == 0.0 ) {
//				
				obj = "";
			} else {

				obj = kk.format(obj).replace(",", ".");
				
			}
		} catch (IllegalArgumentException e) {
			obj = "";

		}
//	} catch (ClassCastException e) {
//		obj = "";
//
//	}
		return obj;
	}

	


	private void btnCancelListener(JButton btnCancel) {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			}
		});
	}

	
	private void btnReCalcListener(JButton btnReCalc, JFrame parent, String frame_name,
			Object[][] masiveValueDataTable,  String[] columnNameDataValue, List<String> listNuclideSimbol, String mount_name, int godina) {
		
		btnReCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				JFrame f = new JFrame();
				
				String frameName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWater_OpenExcelFileFrameName");
						new getExcelFileIzhvarlianiaBAK45(f, frameName, mount_name, godina,
								getValueFromLabel(columnNameDataValue, listNuclideSimbol));
						
			}

					
					});

	}
	
	
	
	private void btnExportListener(String frame_name, JButton btnExportButton, JTable table,
			 String[] columnNameDataValue, List<String> listNuclideSimbol, String mount_name, JPanel panel_Btn) {
		btnExportButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						AddResultViewMetods.setWaitCursor(panel_Btn);
						String headerText = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MounthlyReferenceForCNRDWater_TableLabel")+" ì. "+ mount_name;
						CreateExcelFile.toExcel(createMasiveTableTypeColumn(table), table, frame_name,
								getValueFromLabel(columnNameDataValue, listNuclideSimbol), columnWith, headerText);
						AddResultViewMetods.setDefaultCursor(panel_Btn);
					}

				
				});
				thread.start();

			}

			private String[] createMasiveTableTypeColumn(JTable table) {

				String tableTypeColumn[] = new String[table.getColumnCount()];
				for (int i = 0; i < table.getColumnCount(); i++) {

					tableTypeColumn[i] = "String";

				}

				return tableTypeColumn;
			}
		});
	}

	
	private String[][] getValueFromLabel(String[] columnNameDataValue, List<String> listNuclideSimbol) {
		int count = listNuclideSimbol.size()+1;
		String[][] dataValue = new String[count][9];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < 9; j++) {
				if(i==0){
					dataValue[i][j] = columnNameDataValue[j];
				}else{
				dataValue[i][j] = "";
				}
			}
			
		}
		String[][] value = new String[4][listNuclideSimbol.size()];
		
		value[0] =  splitLabelToValue( lblVolumeNuclide.getText(), count-1) ;
		value[1]  =  splitLabelToValue( lblVolumeIzhvarlenaActivyty .getText(), count-1) ;
		value[2]  =  splitLabelToValue(lblVolumeKombNeopred .getText(), count-1) ;
		value[3]  =  splitLabelToValue(lblVolumeMDA.getText(), count-1) ;
		
		
		
		for (int i = 1; i < count; i++) {
		dataValue[i][0] =  value[0][i-1] ;
		dataValue[i][1] =  value[1][i-1] ;
		dataValue[i][2] =  value[2][i-1] ;
		dataValue[i][3] =  value[3][i-1] ;
		}
		dataValue[1][4] =  lblValueSumActivyty.getText() ;
		dataValue[1][5] =  lblValueIznverlenObshtObem .getText() ;
		dataValue[1][6] =  lblVolumeSummObemnaActivyty.getText() ;
		dataValue[1][7] =  lblVolumeMaxObemnaActivyty.getText() ;
		dataValue[1][8] =  lblVolumePercentKontrolnoNivo.getText() ;
		
	for (int i = 0; i < dataValue.length; i++) {
		for (int j = 0; j < dataValue[0].length; j++) {
			System.out.print( dataValue[i][j]+" - ");
		}
		System.out.println();
	}
		
		return  dataValue;
	}
	
	public static String[] splitLabelToValue(String str, int count){
		System.out.println("-->> "+str);
		String[] dataValue = new String[count];
		String obj = "";
		for (int i = 0; i < count; i++) {
			obj = str.substring(0, str.indexOf("<br>"));
			System.out.println(obj);
			dataValue[i] = obj.substring(obj.lastIndexOf(">")+1);
			System.out.println(dataValue[i]);
			str =str.substring( str.indexOf("<br>")+2);
			System.out.println(str);
			
		}
		for (int i = 0; i < count; i++) {
			System.out.println(dataValue[i]);
		}
		
		return dataValue;
		
	}
	
	
	public static JTable CreateDefaultTable(Object[][] masiveDataTable, String[] columnNames) {
		JTable table = new JTable();
		
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				
//				viewLblVolume ( ceateDataValue(masiveDataTable, listSimbolNuclide));		
				

			}
		});
		
		table.addKeyListener(new KeyListener() {
			
			

			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
//				viewLblVolume ( ceateDataValue(masiveDataTable, listSimbolNuclide));	
			}
		});

//		new TableFilterHeader(table, AutoChoices.ENABLED);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(masiveDataTable, columnNames) {

					private static final long serialVersionUID = 1L;
										
					
					public Object getValueAt(int row, int col) {
						return masiveDataTable[row][col];
					}
					
									
					public boolean isCellEditable(int row, int column) {
						if (column == 2) {
							return true;
						}
						return false;
					}

					public void setValueAt(Object value, int row, int col) {
						try {
							@SuppressWarnings("unused")
							Double k = 	Double.valueOf((String) value);	
					if (!masiveDataTable[row][col].equals(value) && !masiveDataTable[row][col].equals("")) {
							masiveDataTable[row][col] = value;
							recalculateRow(row,  table, masiveDataTable);
						
							viewLblVolume ( ceateDataValue(masiveDataTable, listSimbolNuclide));		
							
								
						}
						} catch (Exception e) {
							ResourceLoader.appendToFile(e);	
						}
					}


				

				};
				
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				table.getTableHeader().setPreferredSize(
					      new Dimension(table.getColumnModel().getTotalColumnWidth(), 52));
//				table.getTableHeader (). GetDefaultRenderer (). SetHorizontalAlignment (SwingConstants.CENTER);
				
				((JLabel) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
			
				initColumnSizes(table);
			}

		});
		return table;
	}
	
	
	private static void recalculateRow(int origRow, JTable table, Object[][] masiveDataTable) {
	
		boolean fl = true;
		String previosProtokil = "1";
	
		for (int row = origRow; row < table.getRowCount(); row++) {
		
			if (! masiveDataTable[row][0].toString().isEmpty() && previosProtokil.isEmpty()) {
				System.out.println(row);
				fl = false;
			}
			if(fl){
				if(!masiveDataTable[row][4].toString().isEmpty()){
				System.out.println(row+"  "+masiveDataTable[row][6]);
				Double k2= Double.parseDouble(masiveDataTable[origRow][2].toString());
				Double k4= Double.parseDouble(masiveDataTable[row][4].toString());
//				Double k7= Double.parseDouble(masiveDataTable[origRow][7].toString());
				Double k5= Double.parseDouble(masiveDataTable[row][5].toString());
				masiveDataTable[row][7] =  k2*k4;
				masiveDataTable[row][8] = (Double) masiveDataTable[row][7]  * k5  / 100;
				 
				masiveDataTable[row][7] = convertToStr(masiveDataTable[row][7]);
				masiveDataTable[row][8] = convertToStr(masiveDataTable[row][8]);
				
				((AbstractTableModel) table.getModel()). fireTableCellUpdated(row, 2);
				((AbstractTableModel) table.getModel()). fireTableCellUpdated(row, 7);
				((AbstractTableModel) table.getModel()). fireTableCellUpdated(row, 8);
				 System.out.println(masiveDataTable[row][4]+"  "+masiveDataTable[row][7]+"  "+masiveDataTable[row][8]);
			}
			}
			previosProtokil=masiveDataTable[row][0].toString();
	}	
		
		
		
	}
	
		
	private static void initColumnSizes(JTable table) {
		   	
//	    	DefaultTableModel model =(DefaultTableModel) table.getModel();
	        TableColumn column = null;
	      
	        Component comp = null;
	        int headerWidth = 0;
	        int cellWidth = 0;
//	       Object[] longValues = getlong();
	       
	        TableCellRenderer headerRenderer =
	            table.getTableHeader().getDefaultRenderer();

	        for (int i = 0; i < table.getColumnCount(); i++) {
	            column = table.getColumnModel().getColumn(i);

	            comp = headerRenderer.getTableCellRendererComponent(
	                                 null, column.getHeaderValue(),
	                                 false, false, 0, 0);
	            headerWidth = comp.getPreferredSize().width;

//	            comp = table.getDefaultRenderer(model.getColumnClass(i)).
//	                             getTableCellRendererComponent(
//	                                 table, longValues[i],
//	                                 false, false, 0, i);
//	            cellWidth = comp.getPreferredSize().width;

	           

	            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
//	            column.sizeWidthToFit(); //or simple
	        }
	    }



	
	}
