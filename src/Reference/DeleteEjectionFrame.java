package Reference;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Aplication.EjectionDAO;
import Aplication.PeriodDAO;
import DBase_Class.Ejection;
import DBase_Class.Period;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;

public class DeleteEjectionFrame extends JDialog  {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;

	private List<Ejection> listReferenceEjection ;
	private JPanel[] panel_Ejection;
					
				
	public DeleteEjectionFrame(Frame basic_panel, String mesec, int godina) {
		
		super(basic_panel, "Изтриване записи на обеми от базата", true);
		final JDialog dialog = new JDialog();
		
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setSize(430, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		System.out.println(mesec);
		Period per = PeriodDAO.getValuePeriodByPeriod(mesec);
		listReferenceEjection = EjectionDAO.getListEjectionFromMesecANDGodina(per, godina);
		int countEjection = listReferenceEjection.size();
		if( countEjection > 0) {
			
			JPanel panell = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panell.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panell.setPreferredSize(new Dimension(10, 20));
			panell.setMaximumSize(new Dimension(32767, 20));
			
			String text = "С мишката изберете записа за изтриване.";
			JLabel lblNewLabel = new JLabel(text);
			panell.add(lblNewLabel);
			panel.add(panell);
		panel_Ejection = new JPanel[countEjection];
		int k=0;
		for (Ejection ejection : listReferenceEjection) {
			String info = k+1+" - "+ejection.getObect().getName_obekt_na_izpitvane()+" - "+
						FormatDoubleNumber.formatDoubleToString(ejection.getVolum(),2, true)+" m3";
			panel_Ejection[k] = createInfoPanell(info, basic_panel,  mesec,  godina);
			
			panel.add(panel_Ejection[k]);
			k++;
			}
		}else {
			JPanel panell = createInfoPanell("Няма записи", basic_panel,  mesec,  godina);
			
			panel.add(panell);
		}
		
		setVisible(true);
	}


	private JPanel createInfoPanell(String info, Frame basic_panel, String mesec, int godina) {
		JPanel panell = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panell.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panell.setPreferredSize(new Dimension(10, 20));
		panell.setMaximumSize(new Dimension(32767, 20));
		
		
		JLabel lblNewLabel = new JLabel(info);
		panell.add(lblNewLabel);
		
		Color col = lblNewLabel.getForeground();
		
		lblNewLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("exxxxxxxxxxxxxxxxxxxxxx");
				lblNewLabel.setForeground(col);
				panel.repaint();
				panel.revalidate();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("ennnnnnnnnnnnnnnnnnnnnnnnnnn");
				lblNewLabel.setForeground(Color.RED);
				panel.repaint();
				panel.revalidate();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = lblNewLabel.getText();
				int con = Integer.parseInt(text.substring(0, text.indexOf("-")).trim());
				Ejection eject = listReferenceEjection.get(con-1);
				if(OptionDialog(text)>0) {
				EjectionDAO.deleteEjection(eject);
				dispose();
				new DeleteEjectionFrame(basic_panel, mesec, godina);
				}
				
			}
		
			
			
			

		});
		
		
		return panell;
	}

	public static int OptionDialog(String info) {
		String[] options = {"Не", "Да"};
        int x = JOptionPane.showOptionDialog(null, "<html>Искате ли да изтриете записа:<br>"+info+"?</html>",
                "Изтриване на запис",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        System.out.println(x);
		return x;
       
	}
	
	
	
}
