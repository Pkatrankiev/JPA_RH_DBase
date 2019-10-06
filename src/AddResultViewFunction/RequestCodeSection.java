package AddResultViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import DBase_Class.Sample;
import WindowView.AddResultsView;
import WindowView.RequestViewFunction;

public class RequestCodeSection {
	

	public static void txtRqstCodeListener(AddResultsView addResultsViewWithTable, JLabel lblError,JTextField txtRqstCode,
			 	Choice choiceSmplCode) {

		txtRqstCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				txtRqstCode.setBackground(Color.WHITE);

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {

			}

		});

		txtRqstCode.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {

				txtRqstCode.setText(RequestViewFunction.checkFormatString(txtRqstCode.getText()));
				if (!RequestDAO.checkRequestCode(txtRqstCode.getText())) {
					txtRqstCode.setForeground(Color.red);
					lblError.setVisible(true);
					lblError.setText("Заявка с този номер не съществува");
					addResultsViewWithTable.validate();
					addResultsViewWithTable.repaint();

				} else {
					txtRqstCode.setForeground(Color.BLACK);
					txtRqstCode.setBorder(new LineBorder(Color.BLACK));
					OverallVariablesAddResults.setChoiseRequest ( RequestDAO.getRequestFromColumnByVolume("recuest_code", txtRqstCode.getText()));
					lblError.setVisible(false);
					OverallVariablesAddResults.setListPokazatel( IzpitvanPokazatelDAO.getValueIzpitvan_pokazatelByRequest(OverallVariablesAddResults.getChoiseRequest()));
					txtRqstCode.setEditable(false);
					if (OverallVariablesAddResults.getChoiseRequest() != null) {
						if (OverallVariablesAddResults.getListSample().isEmpty()) {
							OverallVariablesAddResults.setListSample (SampleDAO.getListSampleFromColumnByVolume("request", OverallVariablesAddResults.getChoiseRequest()));
								
						for (Sample samp : OverallVariablesAddResults.getListSample()) {
							System.out.println(	samp.getSample_code()+"  //////////////////");
								choiceSmplCode.add(samp.getSample_code());
							}
						}

					}
					choiceSmplCode.setEnabled(true);
					addResultsViewWithTable.validate();
					addResultsViewWithTable.repaint();

				}
			}

			@Override
			public void keyPressed(KeyEvent event) {

			}
		});
	}

	
	
	
	
	
	
}
