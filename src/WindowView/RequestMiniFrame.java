package WindowView;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Aplication.IzpitvanPokazatelDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Request;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class RequestMiniFrame extends JDialog {

	
	private static final long serialVersionUID = 1L;
	public RequestMiniFrame(JFrame parent, Request request) {
			super(parent, "", true);
			
			
			
			
		
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {}

				public void mousePressed(MouseEvent e) {
					dispose();
				}
			});
			int maxWidth = 0;
				String codeRequest = request.getRecuest_code();
				
				
				String dateRequest = request.getDate_request();
				
				
				String i_P_Request = request.getIzpitvan_produkt().getName_zpitvan_produkt();
				
				String obekt_I_Request = request.getText_obekt_na_izpitvane_request();
				
				String countSample = request.getCounts_samples()+"";
			
				String pokazatel = generatePokazatel(request); 
				
				String applicant = "";
				if (request.getExtra_module()==null) {
					applicant = "ДП \"РАО\"";	
				}else{
					if(request.getExtra_module().getInternal_applicant()==null&& request.getExtra_module().getInternal_applicant()==null){
						applicant = "ДП \"РАО\"";
					}else{
					if (request.getExtra_module().getInternal_applicant()!=null) {
						applicant = request.getExtra_module().getInternal_applicant().getInternal_applicant_organization();
				}else{
					applicant = request.getExtra_module().getExternal_applicant().getExternal_applicant_name();
				}
					}
				}
				
				GridBagLayout gridBagLayout = new GridBagLayout();
				gridBagLayout.columnWidths = new int[]{5, 125, 44, 0, 0, 0};
				gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
				gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				getContentPane().setLayout(gridBagLayout);
				
				JLabel requestCode_label = new JLabel("Код на заявката");
				GridBagConstraints gbc_requestCode_label = new GridBagConstraints();
				gbc_requestCode_label.anchor = GridBagConstraints.WEST;
				gbc_requestCode_label.insets = new Insets(0, 0, 5, 5);
				gbc_requestCode_label.gridx = 1;
				gbc_requestCode_label.gridy = 0;
				getContentPane().add(requestCode_label, gbc_requestCode_label);
				
				JLabel requestCode_value = new JLabel(codeRequest);
				requestCode_value.setPreferredSize(new Dimension(30, 14));
				requestCode_value.setMinimumSize(new Dimension(30, 14));
				requestCode_value.setMaximumSize(new Dimension(30, 14));
				GridBagConstraints gbc_requestCode_value = new GridBagConstraints();
				gbc_requestCode_value.insets = new Insets(0, 0, 5, 5);
				gbc_requestCode_value.gridx = 2;
				gbc_requestCode_value.gridy = 0;
				getContentPane().add(requestCode_value, gbc_requestCode_value);
				
				maxWidth = getMaxWidth(maxWidth,requestCode_value);
				
				JLabel label = new JLabel("/");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 3;
				gbc_label.gridy = 0;
				getContentPane().add(label, gbc_label);
				
				JLabel requestDate_value = new JLabel(dateRequest);
				GridBagConstraints gbc_requestDate_value = new GridBagConstraints();
				gbc_requestDate_value.anchor = GridBagConstraints.WEST;
				gbc_requestDate_value.insets = new Insets(0, 0, 5, 0);
				gbc_requestDate_value.gridx = 4;
				gbc_requestDate_value.gridy = 0;
				getContentPane().add(requestDate_value, gbc_requestDate_value);
				
				
				if(request.getInd_num_doc()!=null){
					String IDNumberRequest = request.getInd_num_doc().getName();
				JLabel IdNumber_label = new JLabel("Ид. номер");
				GridBagConstraints gbc_IdNumber_label = new GridBagConstraints();
				gbc_IdNumber_label.anchor = GridBagConstraints.WEST;
				gbc_IdNumber_label.insets = new Insets(0, 0, 5, 5);
				gbc_IdNumber_label.gridx = 1;
				gbc_IdNumber_label.gridy = 1;
				getContentPane().add(IdNumber_label, gbc_IdNumber_label);
				
				JLabel IdNumber_Value = new JLabel(IDNumberRequest);
				GridBagConstraints gbc_IdNumber_Value = new GridBagConstraints();
				gbc_IdNumber_Value.anchor = GridBagConstraints.WEST;
				gbc_IdNumber_Value.gridwidth = 3;
				gbc_IdNumber_Value.insets = new Insets(0, 0, 5, 0);
				gbc_IdNumber_Value.gridx = 2;
				gbc_IdNumber_Value.gridy = 1;
				getContentPane().add(IdNumber_Value, gbc_IdNumber_Value);
				
				maxWidth = getMaxWidth(maxWidth, IdNumber_Value);
				}
					
				
				
				
				JLabel applicant_label = new JLabel("Заявител");
				GridBagConstraints gbc_applicant_label = new GridBagConstraints();
				gbc_applicant_label.anchor = GridBagConstraints.WEST;
				gbc_applicant_label.insets = new Insets(0, 0, 5, 5);
				gbc_applicant_label.gridx = 1;
				gbc_applicant_label.gridy = 2;
				getContentPane().add(applicant_label, gbc_applicant_label);
				
				JLabel applicant_value = new JLabel(applicant);
				GridBagConstraints gbc_applicant_value = new GridBagConstraints();
				gbc_applicant_value.anchor = GridBagConstraints.WEST;
				gbc_applicant_value.gridwidth = 3;
				gbc_applicant_value.insets = new Insets(0, 0, 5, 0);
				gbc_applicant_value.gridx = 2;
				gbc_applicant_value.gridy = 2;
				getContentPane().add(applicant_value, gbc_applicant_value);
				
				maxWidth = getMaxWidth(maxWidth, applicant_value);
				
				
				JLabel I_P_R_label = new JLabel("Изпитван продукт");
				GridBagConstraints gbc_I_P_R_label = new GridBagConstraints();
				gbc_I_P_R_label.anchor = GridBagConstraints.WEST;
				gbc_I_P_R_label.insets = new Insets(0, 0, 5, 5);
				gbc_I_P_R_label.gridx = 1;
				gbc_I_P_R_label.gridy = 3;
				getContentPane().add(I_P_R_label, gbc_I_P_R_label);
				
				JLabel I_P_R_Value = new JLabel(i_P_Request);
				GridBagConstraints gbc_I_P_R_Value = new GridBagConstraints();
				gbc_I_P_R_Value.anchor = GridBagConstraints.WEST;
				gbc_I_P_R_Value.gridwidth = 3;
				gbc_I_P_R_Value.insets = new Insets(0, 0, 5, 0);
				gbc_I_P_R_Value.gridx = 2;
				gbc_I_P_R_Value.gridy = 3;
				getContentPane().add(I_P_R_Value, gbc_I_P_R_Value);
				
				maxWidth = getMaxWidth(maxWidth, I_P_R_Value);
				
				
				JLabel O_I_R_label = new JLabel("Обект на изпитване");
				GridBagConstraints gbc_O_I_R_label = new GridBagConstraints();
				gbc_O_I_R_label.anchor = GridBagConstraints.WEST;
				gbc_O_I_R_label.insets = new Insets(0, 0, 5, 5);
				gbc_O_I_R_label.gridx = 1;
				gbc_O_I_R_label.gridy = 4;
				getContentPane().add(O_I_R_label, gbc_O_I_R_label);
				
				JLabel O_I_R_value = new JLabel(obekt_I_Request);
				GridBagConstraints gbc_O_I_R_value = new GridBagConstraints();
				gbc_O_I_R_value.anchor = GridBagConstraints.WEST;
				gbc_O_I_R_value.gridwidth = 3;
				gbc_O_I_R_value.insets = new Insets(0, 0, 5, 0);
				gbc_O_I_R_value.gridx = 2;
				gbc_O_I_R_value.gridy = 4;
				getContentPane().add(O_I_R_value, gbc_O_I_R_value);
				
				maxWidth = getMaxWidth(maxWidth, O_I_R_value);
				
				
				JLabel lblPokazatel = new JLabel("Изпитван показател");
				GridBagConstraints gbc_lblPokazatel = new GridBagConstraints();
				gbc_lblPokazatel.anchor = GridBagConstraints.WEST;
				gbc_lblPokazatel.insets = new Insets(0, 0, 5, 5);
				gbc_lblPokazatel.gridx = 1;
				gbc_lblPokazatel.gridy = 5;
				getContentPane().add(lblPokazatel, gbc_lblPokazatel);
				
				JLabel lblValuePokazatel = new JLabel(pokazatel);
				GridBagConstraints gbc_lblValuePokazatel = new GridBagConstraints();
				gbc_lblValuePokazatel.anchor = GridBagConstraints.WEST;
				gbc_lblValuePokazatel.gridwidth = 3;
				gbc_lblValuePokazatel.insets = new Insets(0, 0, 5, 5);
				gbc_lblValuePokazatel.gridx = 2;
				gbc_lblValuePokazatel.gridy = 5;
				getContentPane().add(lblValuePokazatel, gbc_lblValuePokazatel);
				
				maxWidth = getMaxWidth(maxWidth, lblValuePokazatel);
				
				
				
				JLabel countSample_label = new JLabel("Брой проби");
				GridBagConstraints gbc_countSample_label = new GridBagConstraints();
				gbc_countSample_label.anchor = GridBagConstraints.WEST;
				gbc_countSample_label.insets = new Insets(0, 0, 0, 5);
				gbc_countSample_label.gridx = 1;
				gbc_countSample_label.gridy = 6;
				getContentPane().add(countSample_label, gbc_countSample_label);
				
				JLabel countSample_value = new JLabel(countSample);
				GridBagConstraints gbc_countSample_value = new GridBagConstraints();
				gbc_countSample_value.anchor = GridBagConstraints.WEST;
				gbc_countSample_value.insets = new Insets(0, 0, 0, 5);
				gbc_countSample_value.gridx = 2;
				gbc_countSample_value.gridy = 6;
				getContentPane().add(countSample_value, gbc_countSample_value);
				
				int width = 155+maxWidth;
				int height	= 200;
				int[] koordinates = getCurentKoordinates(width, height);
				setBounds(koordinates[0], koordinates[1],width , height);
				
				System.out.println(width+" / "+height);
				
				setVisible(true);
			

		}
	public static int getMaxWidth(int maxWidth, JLabel label) {
		FontMetrics fm = label.getFontMetrics(label.getFont());
		int width = fm.stringWidth(label.getText());
		if(maxWidth < width){
			maxWidth =  width;
		}
		System.out.println(maxWidth+" ////////////////// "+width);
		return maxWidth;
	}
	private String generatePokazatel(Request request) {
		String list_IzpitPokazatel="";
		for (IzpitvanPokazatel izpitPokazatel : IzpitvanPokazatelDAO.getValueIzpitvan_pokazatelByRequest(request)) {
			list_IzpitPokazatel = list_IzpitPokazatel+izpitPokazatel.getPokazatel().getName_pokazatel()+"; ";
		}
		return list_IzpitPokazatel.substring(0, list_IzpitPokazatel.length()-2);
	}
	public static int[] getCurentKoordinates(int width, int height) {
		int[] koordinates = new int[2];
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int koordWidth = gd.getDisplayMode().getWidth();
		int koorHeight = gd.getDisplayMode().getHeight();
		
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();
		
		if((x+width)>koordWidth)
			x=x-width;
		if(x<0){
			x=0;
		}
		if((y+height)>koorHeight)
			y=y-height;
		if(y<0){
			y=0;
		}
		koordinates[0] = x;
		koordinates[1] = y;
		return koordinates;
	}

}
