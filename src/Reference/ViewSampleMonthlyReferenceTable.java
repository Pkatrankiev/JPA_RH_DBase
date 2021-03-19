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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import ExcelFilesFunction.CreateExcelFile;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

@SuppressWarnings("serial")
public class ViewSampleMonthlyReferenceTable extends JDialog {
	
	public ViewSampleMonthlyReferenceTable(JFrame parent, TranscluentWindow round, String frame_name, String infoString, String[][] masiveValueDataTable, String[] columnNames) {
		super(parent, frame_name, true);

		JTable table = null;

		

		boolean NoReport = false;
		int countRow = masiveValueDataTable.length;
		int widshRow = 20;
		String reportString = "";
		
		if (countRow < 2) {
			reportString = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_ReportString");
			NoReport = true;
		} else {
			table = CreateDefaultTable( masiveValueDataTable, columnNames);

		}

		JPanel top_panel = new JPanel();
		top_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		top_panel.setSize(new Dimension(2, 0));
		getContentPane().add(top_panel, BorderLayout.NORTH);
		GridBagLayout gbl_top_panel = new GridBagLayout();
		gbl_top_panel.columnWidths = new int[] { 22, 323, 63, 66, 60, 74, 0 };
		gbl_top_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_top_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_top_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		top_panel.setLayout(gbl_top_panel);

		JLabel lblIzpitProdukt = new JLabel(infoString);
		GridBagConstraints gbc_lblIzpitProdukt = new GridBagConstraints();
		gbc_lblIzpitProdukt.gridwidth = 2;
		gbc_lblIzpitProdukt.anchor = GridBagConstraints.WEST;
		gbc_lblIzpitProdukt.insets = new Insets(0, 0, 5, 5);
		gbc_lblIzpitProdukt.gridx = 1;
		gbc_lblIzpitProdukt.gridy = 0;
		top_panel.add(lblIzpitProdukt, gbc_lblIzpitProdukt);

		JLabel lblReport = new JLabel(reportString);
		GridBagConstraints gbc_lblReport = new GridBagConstraints();
		gbc_lblReport.anchor = GridBagConstraints.EAST;
		gbc_lblReport.insets = new Insets(0, 0, 0, 5);
		gbc_lblReport.gridx = 1;
		gbc_lblReport.gridy = 1;
		top_panel.add(lblReport, gbc_lblReport);

		if (!NoReport) {
			JScrollPane scrollPane = new JScrollPane(table);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
		}
		setSize(800, 200 + countRow * widshRow);
		setLocationRelativeTo(null);
	
		String[][] masiveExtendLamels = createExtendLabels(infoString);

		round.StopWindow();

		JPanel panel_Btn = new JPanel();
		panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		getContentPane().add(panel_Btn, BorderLayout.SOUTH);
		panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		if (!NoReport) {
			JButton btnExportButton = new JButton("export");
			panel_Btn.add(btnExportButton);
			btnExportListener(frame_name, btnExportButton, table, masiveExtendLamels);
		
		}
		JButton btnCancel = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"));
		panel_Btn.add(btnCancel);

		btnCancelListener(btnCancel);

		setVisible(true);
	}
	
	private String[][] createExtendLabels(String infoString) {
		
		infoString = infoString.replace("<html>", "").replace("</html>", "");
		int indexBr = 0;
		List<String> listString = new ArrayList<>();
		while (infoString.length()>0) {
			indexBr = infoString.indexOf("<br>");
			if(indexBr>0){
				listString.add(infoString.substring(0, indexBr));
				infoString = infoString.substring( indexBr+4);	
			}else{
				listString.add(infoString);
				infoString = "";
			}
		}
		
		String[][] masiveExtendLamels = new String[listString.size()][1];
		for (int i = 0; i < listString.size(); i++) {
			System.out.println("--        "+listString.get(i));
			masiveExtendLamels[i][0] = listString.get(i);
		}
		
		
		return masiveExtendLamels;
	}

	private void btnCancelListener(JButton btnCancel) {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				String frame_Text = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("List_Table_DialogSave_ChoiceColumn_frame_Text");
//				String info_Text = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Request_List_Table_DialogForCloseTable");
//				if(DialogOption_Yes_No.DialogOption_YesNo(frame_Text, info_Text)){
//					viewTableList.setVisible(false);
//					startViewSampleTableList( reqCodeStr);
//				}
				setVisible(false);
			}
		});
	}

	private void btnExportListener(String frame_name, JButton btnExportButton, JTable table, String[][] masiveExtendLamels) {
		btnExportButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
					
						CreateExcelFile.toExcel(createMasiveTableTypeColumn(table), table, frame_name, masiveExtendLamels);
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

	public static JTable CreateDefaultTable(String[][] masiveDataTable, String[] columnNames) {
		JTable table = new JTable();

		new TableFilterHeader(table, AutoChoices.ENABLED);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(masiveDataTable, columnNames);
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
			}

		});
		return table;
	}

}
