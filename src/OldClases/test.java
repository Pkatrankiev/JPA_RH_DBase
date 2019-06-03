package OldClases;

import javax.swing.JPanel;


import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.util.logging.Logger;

import javax.swing.BoxLayout;

public class test extends JPanel {

	
	static Logger logger;
	private static final long serialVersionUID = 1L;
	public test() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel();
		panel.add(lblNewLabel);
		
	}
	public static JPanel creadJPanel (String[][] str){
		JPanel panel_Basic = new JPanel();
		panel_Basic.setLayout(new BoxLayout(panel_Basic, BoxLayout.Y_AXIS));
		for (int i = 0; i < str.length; i++) {
		JPanel panel = new JPanel();
		panel_Basic.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{60, 60, 60, 0};
		gbl_panel.rowHeights = new int[]{10, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
	
		JLabel lblNewLabel_1 = new JLabel(str[i][0]);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(str[i][1]);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 0;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(str[i][2]);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 0;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		return panel_Basic;
		}

//public static void convertDocx2pdf(String docxFilePath) {
//		
//		File docxFile = new File(docxFilePath);
//		String pdfFile = docxFilePath.substring(0, docxFilePath.lastIndexOf(".docx")) + ".pdf";
//
//		if (docxFile.exists()) {
//		    if (!docxFile.isDirectory()) { 
//		        ActiveXComponent app = null;
//
//		        long start = System.currentTimeMillis();
//		        try {
//		            ComThread.InitMTA(true); 
//		            app = new ActiveXComponent("Word.Application");
//		            Dispatch documents = app.getProperty("Documents").toDispatch();
//		            Dispatch document = Dispatch.call(documents, "Open", docxFilePath, false, true).toDispatch();
//		            File target = new File(pdfFile);
//		            if (target.exists()) {
//		                target.delete();
//		            }
//		            Dispatch.call(document, "SaveAs", pdfFile, 17);
//		            Dispatch.call(document, "Close", false);
//		            long end = System.currentTimeMillis();
//		            logger.info("============Convert Finished：" + (end - start) + "ms");
//		        } catch (Exception e) {
////		            logger.error(e.getLocalizedMessage(), e);
//		            throw new RuntimeException("pdf convert failed.");
//		        } finally {
//		            if (app != null) {
//		                app.invoke("Quit", new Variant[] {});
//		            }
//		            ComThread.Release();
//		        }
//		    }
//		}
//	}


}
