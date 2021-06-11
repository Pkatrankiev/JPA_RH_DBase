package Reference;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.attribute.standard.Finishings;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import DBase_Class.Obekt_na_izpitvane_sample;
import DBase_Class.Period;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import ExcelFilesFunction.CreateExcelFile;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import InfoPanelInMainWindow.CreateListLeftPanelStartWindowClass;
import WindowView.RequestMiniFrame;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class MounthlyReferenceForCNRDWater_Table extends JDialog {

	private static final long serialVersionUID = 1L;
	private static boolean changeObem = false;
	private static Object[][] valueDataTable;
	
	private static JButton  btnReCalculateButton;
	
	public MounthlyReferenceForCNRDWater_Table(JFrame parent, TranscluentWindow round, String frame_name,
			Object[][] masiveValueDataTable, String[] columnNames, List<String> listNuclideSimbol) {
		super(parent, frame_name, true);

		JTable table = null;
		valueDataTable = masiveValueDataTable;
		
		
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

		String[][] masiveExtendLamels = null;

		Object[][] DataValue = ceateDataValue(valueDataTable, listNuclideSimbol);
		
		DataValue = reformatNumberValue(DataValue, 1);

		valueDataTable = reformatNumberValue(valueDataTable, 4);

		if (countRow < 2) {
			labelNuclide = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_ReportString");
			NoReport = true;
		} else {
			String[] columnNameDataValue = new String[9];
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
			
			labelPercentKontrolnoNivo = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWaterTable_PercentKontrolnoNivo");
			columnNameDataValue[7] = labelPercentKontrolnoNivo.replace("<html>", " ").replace("</html>", " ")
					.replace("<br>", " ").replace("<center>","").replace("</center>","");
			
			labelMaxObemnaActivyty = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWaterTable_MaxObemnaActivnostZaMeseca");
			columnNameDataValue[8] = labelMaxObemnaActivyty.replace("<html>", " ").replace("</html>", " ")
					.replace("<br>", " ").replace("<center>","").replace("</center>","");

			masiveExtendLamels = createMasiveObjectToString(DataValue, columnNameDataValue);

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

			table = CreateDefaultTable(valueDataTable, columnNames);

		}

		JPanel top_panel = new JPanel();
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

			JLabel LabelPercentKontrolnoNivo = new JLabel(labelPercentKontrolnoNivo);
			GridBagConstraints gbc_LabelPercentKontrolnoNivo = new GridBagConstraints();
			gbc_LabelPercentKontrolnoNivo.insets = new Insets(0, 0, 5, 5);
			gbc_LabelPercentKontrolnoNivo.gridx = 7;
			gbc_LabelPercentKontrolnoNivo.gridy = 0;
			top_panel.add(LabelPercentKontrolnoNivo, gbc_LabelPercentKontrolnoNivo);

			JLabel LabelMaxObemnaActivyty = new JLabel(labelMaxObemnaActivyty);
			GridBagConstraints gbc_LabelMaxObemnaActivyty = new GridBagConstraints();
			gbc_LabelMaxObemnaActivyty.insets = new Insets(0, 0, 5, 0);
			gbc_LabelMaxObemnaActivyty.gridx = 8;
			gbc_LabelMaxObemnaActivyty.gridy = 0;
			top_panel.add(LabelMaxObemnaActivyty, gbc_LabelMaxObemnaActivyty);

			JLabel lblVolumeNuclide = new JLabel(DataValue[0][0].toString());
			GridBagConstraints gbc_lblVolumeNuclide = new GridBagConstraints();
			gbc_lblVolumeNuclide.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumeNuclide.gridx = 0;
			gbc_lblVolumeNuclide.gridy = 1;
			top_panel.add(lblVolumeNuclide, gbc_lblVolumeNuclide);

			JLabel lblVolumeIzhvarlenaActivyty = new JLabel(DataValue[0][1].toString());
			GridBagConstraints gbc_lblVolumeIzhvarlenaActivyty = new GridBagConstraints();
			gbc_lblVolumeIzhvarlenaActivyty.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumeIzhvarlenaActivyty.gridx = 1;
			gbc_lblVolumeIzhvarlenaActivyty.gridy = 1;
			top_panel.add(lblVolumeIzhvarlenaActivyty, gbc_lblVolumeIzhvarlenaActivyty);

			JLabel lblVolumeKombNeopred = new JLabel(DataValue[0][2].toString());
			GridBagConstraints gbc_lblVolumeKombNeopred = new GridBagConstraints();
			gbc_lblVolumeKombNeopred.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumeKombNeopred.gridx = 2;
			gbc_lblVolumeKombNeopred.gridy = 1;
			top_panel.add(lblVolumeKombNeopred, gbc_lblVolumeKombNeopred);

			JLabel lblVolumeMDA = new JLabel(DataValue[0][3].toString());
			GridBagConstraints gbc_lblVolumeMDA = new GridBagConstraints();
			gbc_lblVolumeMDA.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumeMDA.gridx = 3;
			gbc_lblVolumeMDA.gridy = 1;
			top_panel.add(lblVolumeMDA, gbc_lblVolumeMDA);

			JLabel lblValueSumActivyty = new JLabel(DataValue[0][4].toString());
			GridBagConstraints gbc_lblValueSumActivyty = new GridBagConstraints();
			gbc_lblValueSumActivyty.insets = new Insets(0, 0, 0, 5);
			gbc_lblValueSumActivyty.gridx = 4;
			gbc_lblValueSumActivyty.gridy = 1;
			top_panel.add(lblValueSumActivyty, gbc_lblValueSumActivyty);

			JLabel lblValueIznverlenObshtObem = new JLabel(DataValue[0][5].toString());
			GridBagConstraints gbc_lblValueIznverlenObshtObem = new GridBagConstraints();
			gbc_lblValueIznverlenObshtObem.insets = new Insets(0, 0, 0, 5);
			gbc_lblValueIznverlenObshtObem.gridx = 5;
			gbc_lblValueIznverlenObshtObem.gridy = 1;
			top_panel.add(lblValueIznverlenObshtObem, gbc_lblValueIznverlenObshtObem);

			JLabel lblVolumeSummObemnaActivyty = new JLabel(DataValue[0][6].toString());
			GridBagConstraints gbc_lblVolumeSummObemnaActivyty = new GridBagConstraints();
			gbc_lblVolumeSummObemnaActivyty.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumeSummObemnaActivyty.gridx = 6;
			gbc_lblVolumeSummObemnaActivyty.gridy = 1;
			top_panel.add(lblVolumeSummObemnaActivyty, gbc_lblVolumeSummObemnaActivyty);

			JLabel lblVolumePercentKontrolnoNivo = new JLabel(DataValue[0][7].toString());
			GridBagConstraints gbc_lblVolumePercentKontrolnoNivo = new GridBagConstraints();
			gbc_lblVolumePercentKontrolnoNivo.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumePercentKontrolnoNivo.gridx = 7;
			gbc_lblVolumePercentKontrolnoNivo.gridy = 1;
			top_panel.add(lblVolumePercentKontrolnoNivo, gbc_lblVolumePercentKontrolnoNivo);

			JLabel lblVolumeMaxObemnaActivyty = new JLabel(DataValue[0][8].toString());
			GridBagConstraints gbc_lblVolumeMaxObemnaActivyty = new GridBagConstraints();
			gbc_lblVolumeMaxObemnaActivyty.gridx = 8;
			gbc_lblVolumeMaxObemnaActivyty.gridy = 1;
			top_panel.add(lblVolumeMaxObemnaActivyty, gbc_lblVolumeMaxObemnaActivyty);

			JScrollPane scrollPane = new JScrollPane(table);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
		}
		setSize(900, 220 + countRow * widshRow);
		setLocationRelativeTo(null);

		round.StopWindow();

		JPanel panel_Btn = new JPanel();
		panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		getContentPane().add(panel_Btn, BorderLayout.SOUTH);
		panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnReCalculateButton = new JButton("ReCalc");
		btnReCalcListener(btnReCalculateButton,  parent,  frame_name,
				 masiveValueDataTable,  columnNames,  listNuclideSimbol) ;
		
		if (!NoReport) {
			JButton btnExportButton = new JButton("export");
			panel_Btn.add(btnExportButton);
			btnExportListener(frame_name, btnExportButton, table, masiveExtendLamels);

		panel_Btn.add(btnReCalculateButton);
		btnReCalculateButton.setEnabled(changeObem);
		}
		
		
		
		JButton btnCancel = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"));
		panel_Btn.add(btnCancel);

		btnCancelListener(btnCancel);

		setVisible(true);
	}

	
	
	private String[][] createMasiveObjectToString(Object[][] dataValue, String[] columnNameDataValue) {
		String[][] newMasive = new String[dataValue.length + 1][dataValue[0].length];
		for (int i = 0; i < dataValue.length + 1; i++) {
			for (int j = 0; j < dataValue[0].length; j++) {
				if (i == 0) {
					newMasive[i][j] = columnNameDataValue[j];
				} else {
					newMasive[i][j] = dataValue[i - 1][j].toString();
				}
			}
		}
		return newMasive;
	}

	private Object[][] ceateDataValue(Object[][] DataTableValue, List<String> listNuclideSimbol) {
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
		int kntrolnoNivo = 11100;
		Double k = 0.0;
		Double maxSpecifActivyty = 0.0, sumActivyty = 0.0;
		String previosProtokil = "";
		for (int row = 0; row < DataTableValue.length; row++) {
			if(!DataTableValue[row][2].toString().isEmpty()){
			DataValue[0][5] = (Double) DataValue[0][5] +
					Double.parseDouble(DataTableValue[row][2].toString());
			}
			
			if (! DataTableValue[row][0].toString().isEmpty() && previosProtokil.isEmpty()) {
				
				sumActivyty = (Double) DataTableValue[row][4];
				
			}else{
				sumActivyty = sumActivyty + (Double) DataTableValue[row][4];
			
				}
				previosProtokil = DataTableValue[row][0].toString();
				if (maxSpecifActivyty < sumActivyty) {
					maxSpecifActivyty = sumActivyty;
				}

			for (int i = 0; i < listNuclideSimbol.size(); i++) {

				if (DataTableValue[row][3].toString().equals(listNuclideSimbol.get(i))) {
					DataValue[i][1] = (Double) DataValue[i][1] + Double.parseDouble(DataTableValue[row][7].toString());
					DataValue[0][4] = (Double) DataValue[0][4] + (Double) DataTableValue[row][7];
					DataValue[0][6] = (Double) DataValue[0][6] + (Double) DataTableValue[row][4];
					if(!DataTableValue[row][7].toString().isEmpty()){
					k = ((Double) DataTableValue[row][8]) / 2;
					DataValue[i][2] = Double.parseDouble(DataValue[i][2].toString()) + (k * k);
					}
					if ((Double) DataValue[i][3] < (Double) DataTableValue[row][6]) {
						DataValue[i][3] = (Double) DataTableValue[row][6];
					}
				}
			}

		}
		for (int i = 0; i < DataValue.length; i++) {
			if (i == 0) {
			
				DataValue[i][7] = ((Double) DataValue[i][6] * 100) / kntrolnoNivo;
				DataValue[i][8] = maxSpecifActivyty/1000;
			}
			DataValue[i][0] = listNuclideSimbol.get(i);
			DataValue[i][2] = (Math.sqrt((Double) DataValue[i][2]))*100 / (Double) DataValue[i][1];
		}
		return DataValue;
	}

	
	
	private Object[][] reformatNumberValue(Object[][] DataValue, int indexStartNumber) {
		DecimalFormat df = new DecimalFormat("0.00E00");
		for (int i = 0; i < DataValue.length; i++) {
			for (int j = 0; j < 9; j++) {
				if (j >= indexStartNumber) {
					try {
						if ((Double) DataValue[i][j] == 0.0 || ((Double) DataValue[i][j]).isNaN()) {
							DataValue[i][j] = "";
						} else {

							DataValue[i][j] = df.format(DataValue[i][j]);
						}
					} catch (IllegalArgumentException e) {
						DataValue[i][j] = "";

					}

				}
				DataValue[i][j] = DataValue[i][j].toString().replace(",", ".");
			}

		}
		return DataValue;
	}

	


	private void btnCancelListener(JButton btnCancel) {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			}
		});
	}

	
	private void btnReCalcListener(JButton btnReCalc, JFrame parent, String frame_name,
			Object[][] masiveValueDataTable, String[] columnNames, List<String> listNuclideSimbol) {
		
		btnReCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TranscluentWindow round = new TranscluentWindow();

				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
			
						Object[][]	newValueDataTable =	getNullInEmptyCells(masiveValueDataTable);			
						new MounthlyReferenceForCNRDWater_Table(parent, round, frame_name, newValueDataTable, columnNames, listNuclideSimbol);
					}

					
				});
				thread.start();
			}
		});

	}
	
	private Object[][] getNullInEmptyCells(Object[][] masiveValueDataTable) {
		for (int i = 0; i < masiveValueDataTable.length; i++) {
			for (int j = 0; j < 9; j++) {
				try {
					masiveValueDataTable[i][j] = Double.parseDouble(masiveValueDataTable[i][j].toString());
						
				} catch (Exception e) {
					if (masiveValueDataTable[i][j].toString().isEmpty()) {
						masiveValueDataTable[i][j] = 0.0;
					}
				}
				
			}
		}
		return masiveValueDataTable;
	}
	
	private void btnExportListener(String frame_name, JButton btnExportButton, JTable table,
			String[][] masiveExtendLamels) {
		btnExportButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {

						CreateExcelFile.toExcel(createMasiveTableTypeColumn(table), table, frame_name,
								masiveExtendLamels);
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

	public static JTable CreateDefaultTable(Object[][] masiveDataTable, String[] columnNames) {
		JTable table = new JTable();
		
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				
				

			}
		});
		
		table.addKeyListener(new KeyListener() {
			
			

			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
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
							fireTableCellUpdated(row, col);
													
						}
						} catch (Exception e) {
							
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
