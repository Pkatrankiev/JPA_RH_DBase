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

import DBase_Class.Request;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class RequestMiniFrame extends JDialog {

	
	private static final long serialVersionUID = 1L;
	public RequestMiniFrame(JFrame parent, Request request) {
			super(parent, "", true);
			
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width = gd.getDisplayMode().getWidth();
			int height = gd.getDisplayMode().getHeight();
			
			PointerInfo a = MouseInfo.getPointerInfo();
			Point b = a.getLocation();
			int x = (int) b.getX();
			int y = (int) b.getY();
			
			if((x+450)>width)
				x=x-450;
			if((y+200)>height)
				y=y-200;
			
			setBounds(x, y, 450, 200);
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {}

				public void mousePressed(MouseEvent e) {
					dispose();
				}
			});
				
				String codeRequest = request.getRecuest_code();
				String dateRequest = request.getDate_request();
				
				String i_P_Request = request.getIzpitvan_produkt().getName_zpitvan_produkt();
				String obekt_I_Request = request.getText_obekt_na_izpitvane_request();
				String countSample = request.getCounts_samples()+"";
				String applicant = "";
				if (request.getExtra_module()==null) {
					applicant = "�� \"���\"";	
				}else{
					if(request.getExtra_module().getInternal_applicant()==null&& request.getExtra_module().getInternal_applicant()==null){
						applicant = "�� \"���\"";
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
				
				JLabel requestCode_label = new JLabel("��� �� ��������");
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
				JLabel IdNumber_label = new JLabel("��. �����");
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
				}
						
				
				JLabel applicant_label = new JLabel("��������");
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
				
				JLabel I_P_R_label = new JLabel("�������� �������");
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
				
				JLabel O_I_R_label = new JLabel("����� �� ���������");
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
				
				JLabel countSample_label = new JLabel("���� �����");
				GridBagConstraints gbc_countSample_label = new GridBagConstraints();
				gbc_countSample_label.anchor = GridBagConstraints.WEST;
				gbc_countSample_label.insets = new Insets(0, 0, 5, 5);
				gbc_countSample_label.gridx = 1;
				gbc_countSample_label.gridy = 5;
				getContentPane().add(countSample_label, gbc_countSample_label);
				
				JLabel countSample_value = new JLabel(countSample);
				GridBagConstraints gbc_countSample_value = new GridBagConstraints();
				gbc_countSample_value.anchor = GridBagConstraints.WEST;
				gbc_countSample_value.insets = new Insets(0, 0, 5, 5);
				gbc_countSample_value.gridx = 2;
				gbc_countSample_value.gridy = 5;
				getContentPane().add(countSample_value, gbc_countSample_value);
				setVisible(true);
			

		}

}
