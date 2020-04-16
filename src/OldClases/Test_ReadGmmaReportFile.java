package OldClases;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DBase_Class.Izpitvan_produkt;
import DBase_Class.Metody;
import DBase_Class.Nuclide;
import DBase_Class.TSI;
import DBase_Class.Users;
import WindowView.ReadGamaFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class Test_ReadGmmaReportFile extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	

	public Test_ReadGmmaReportFile(JFrame parent, String nameFrame) {
		super(parent, nameFrame, true);
		GetVisibleLAF(this);

		setBounds(100, 100, 200, 100);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JFileChooser fileChooser = new JFileChooser("c:/GENIE2K/REPFILES");
		String path = "D:/CallGamma.xls";
		JButton btnReadfile = new JButton("Активност по линии");

		btnReadfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showOpenDialog(null);
				if (fileChooser.getSelectedFile() != null) {

					ReadGammaReportFile(fileChooser);

					if(saveToExcelFile(getListNuklide(), path)){
					openExcelFile(path);
					}
				}
			}

		});
		contentPane.add(btnReadfile, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("Ефективности по енергя");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showOpenDialog(null);
				if (fileChooser.getSelectedFile() != null) {

					ReadGammaReportFile(fileChooser);
					if(saveToExcelFile(getListEffiency(), path)){
					openExcelFile(path);
					}
				}
			}
		});
		contentPane.add(btnNewButton, BorderLayout.EAST);
		
		setVisible(true);
	}

	private void ReadGammaReportFile(JFileChooser fileChooser) {
		String pathfileName = fileChooser.getSelectedFile().toString();
		ReadGamaFile.getReadGamaFile(pathfileName);
	}

	private void GetVisibleLAF(Test_ReadGmmaReportFile win) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(win);
			this.pack();
		} catch (Exception ex) {
			Logger.getLogger(JFileChooser.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void saveToExcel(String path, List<nuklide> listNuk)
			throws UnsupportedEncodingException, FileNotFoundException {
		// File file = new File("Some name.xls");

		
	}

	private void openExcelFile(String path) {
		try {
			Desktop.getDesktop().open(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean saveToExcelFile(List<nuklide> listNuk, String path) {
boolean CheckingEnabledFile = true;
		try {
			Writer excel = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "cp1251" + "" + ""));

			try {

				// FileWriter excel = new FileWriter(out);
				excel.write("Енергия" + "\t");
				if(listNuk.get(1).getNeopredelenost()!=null){
				excel.write("Ефективност" + "\t");
				}else{
					excel.write("Активност" + "\t");
				}
				excel.write("Неопределеност" + "\t");
				excel.write("\n");
				for (nuklide nuk : listNuk) {
					excel.write(nuk.getEnergy().toString() + "\t");
					excel.write(nuk.getActivity().toString() + "\t");
					excel.write(nuk.getNeopredelenost()!=null?nuk.getNeopredelenost().toString() + "\t":"-");
					excel.write("\n");
				}

				excel.close();

			} catch (IOException e) {
				System.out.println(e);
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Файла "+path+" е отворен!", "Грешка в данните",
					JOptionPane.ERROR_MESSAGE);
			CheckingEnabledFile = false;
			e.printStackTrace();
		}
		return CheckingEnabledFile;

	}

	private List<nuklide> getListEffiency() {
		List<nuklide> listNuk = new ArrayList<nuklide>();
		String[] masiveFromGammaRepFileLines = ReadGamaFile.getStringArray();
		System.out.println(masiveFromGammaRepFileLines.length);
		for (int i = 0; i < masiveFromGammaRepFileLines.length; i++) {
			System.out.println(i+" / ");
			if (masiveFromGammaRepFileLines[i].contains("Efficiency Triplets")) {
				i = i + 5;
				System.out.println(i+" - "+masiveFromGammaRepFileLines[i]);
				while (!masiveFromGammaRepFileLines[i+1].contains("DUAL Efficiency Calibration Equation")) {
					nuklide nuk = new nuklide(Double.valueOf(masiveFromGammaRepFileLines[i].substring(18, 28).trim()),
							Double.valueOf(masiveFromGammaRepFileLines[i].substring(33, 48).trim()),
							Double.valueOf(masiveFromGammaRepFileLines[i].substring(50).trim()));
					listNuk.add(nuk);
					System.out.println(nuk.getActivity()+" --  "+nuk.getNeopredelenost());
					i++;
				}
			}
		}
		
		for (nuklide nuk : listNuk) {
			System.out.println(nuk.getActivity()+"   "+nuk.getNeopredelenost());
		}
		return listNuk;
	}

	public static List<nuklide> getListNuklide() {
		List<String> listStringMDA = ReadGamaFile.getListStringMDA();
		List<nuklide> listNuk = new ArrayList<nuklide>();
		for (int i = 0; i < listStringMDA.size(); i++) {

			nuklide nuk = new nuklide(Double.valueOf(listStringMDA.get(i).substring(15, 25).trim()),
					Double.valueOf(listStringMDA.get(i).substring(68, 79).trim()), null);
			listNuk.add(nuk);
		}

		listNuk.sort(new EnergySorter());

		return listNuk;

	}
}

class nuklide {
	private Double energy;
	private Double stoinost;
	private Double neopredelenost;

	public nuklide(Double energy, Double stoinost, Double neopredelenost) {
		this.setEnergy(energy);
		this.setActivity(stoinost);
		this.setNeopredelenost(neopredelenost);
	}

	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = energy;
	}

	public Double getActivity() {
		return stoinost;
	}

	public void setActivity(Double activity) {
		this.stoinost = activity;
	}

	public Double getNeopredelenost() {
		return neopredelenost;
	}

	public void setNeopredelenost(Double neopredelenost) {
		this.neopredelenost = neopredelenost;
	}

}

class EnergySorter implements Comparator<nuklide> {
	@Override
	public int compare(nuklide o1, nuklide o2) {
		return o1.getEnergy().compareTo(o2.getEnergy());
	}
}
