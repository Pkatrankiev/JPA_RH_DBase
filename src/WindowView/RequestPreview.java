package WindowView;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DBase_Class.Request;

import javax.swing.JScrollPane;
import javax.swing.RootPaneContainer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.LineBorder;

import org.eclipse.persistence.internal.sessions.remote.SequencingFunctionCall.WhenShouldAcquireValueForAll;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;

public class RequestPreview extends JFrame {

private int d_width=650, d_height=920;
	public RequestPreview(Request request, String list_izpitvan_pokazatel, String[][] sample_description) {
		super("ѕреглед на за€вка");
		getContentPane().setBackground(Color.ORANGE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, d_width+20, d_height+50);
		getContentPane().setLayout(null);
	
		Font font = new Font("Magnolia Script", Font.ITALIC, 12);
		Color col = Color.BLUE;

		JLabel request_code = new JLabel(request.getRecuest_code());
		request_code.setBounds(280, 155, 62, 20);
		getContentPane().add(request_code);
		request_code.setVerticalTextPosition(SwingConstants.BOTTOM);
		request_code.setHorizontalTextPosition(SwingConstants.CENTER);
		request_code.setHorizontalAlignment(SwingConstants.RIGHT);
		request_code.setFont(font);
		request_code.setVerticalAlignment(SwingConstants.BOTTOM);
		request_code.setForeground(col);

		JLabel request_date = new JLabel(request.getDate_request());
		request_date.setBounds(349, 155, 98, 20);
		getContentPane().add(request_date);
		request_date.setVerticalTextPosition(SwingConstants.BOTTOM);
		request_date.setVerticalAlignment(SwingConstants.BOTTOM);
		request_date.setHorizontalTextPosition(SwingConstants.CENTER);
		request_date.setHorizontalAlignment(SwingConstants.LEFT);
		request_date.setForeground(col);
		request_date.setFont(font);

		String str = "";
		if (request.getInd_num_doc() != null)
			str = request.getInd_num_doc().getName();

		JLabel ind_num_doc = new JLabel(str);
		ind_num_doc.setBounds(357, 240, 245, 20);
		getContentPane().add(ind_num_doc);
		ind_num_doc.setVerticalTextPosition(SwingConstants.BOTTOM);
		ind_num_doc.setVerticalAlignment(SwingConstants.BOTTOM);
		ind_num_doc.setHorizontalTextPosition(SwingConstants.CENTER);
		ind_num_doc.setHorizontalAlignment(SwingConstants.LEFT);
		ind_num_doc.setForeground(col);
		ind_num_doc.setFont(font);

		JLabel izpitvan_produkt = new JLabel(request.getIzpitvan_produkt().getName_zpitvan_produkt());
		izpitvan_produkt.setBounds(188, 282, 414, 20);
		getContentPane().add(izpitvan_produkt);
		izpitvan_produkt.setVerticalTextPosition(SwingConstants.BOTTOM);
		izpitvan_produkt.setVerticalAlignment(SwingConstants.BOTTOM);
		izpitvan_produkt.setHorizontalTextPosition(SwingConstants.CENTER);
		izpitvan_produkt.setHorizontalAlignment(SwingConstants.LEFT);
		izpitvan_produkt.setForeground(col);
		izpitvan_produkt.setFont(font);

		String strObect = request.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();
		String str1 = strObect, str2 = "";
		System.out.println("strObect " + strObect.length());
		int max = 34;
		if (strObect.length() >= max) {
			int i = max;
			while (i > 0) {
				if (strObect.substring(i - 1, i).equals(" ")) {
					str1 = strObect.substring(0, i);
					str2 = strObect.substring(i, strObect.length());
					i = 0;
				}
				i--;
			}
		}

		JLabel obekt_na_izpitvane_1 = new JLabel(str1);
		obekt_na_izpitvane_1.setBounds(280, 315, 322, 20);
		getContentPane().add(obekt_na_izpitvane_1);
		obekt_na_izpitvane_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		obekt_na_izpitvane_1.setVerticalAlignment(SwingConstants.BOTTOM);
		obekt_na_izpitvane_1.setHorizontalTextPosition(SwingConstants.CENTER);
		obekt_na_izpitvane_1.setHorizontalAlignment(SwingConstants.LEFT);
		obekt_na_izpitvane_1.setForeground(col);
		obekt_na_izpitvane_1.setFont(font);

		JLabel obekt_na_izpitvane_2 = new JLabel(str2);
		obekt_na_izpitvane_2.setBounds(83, 330, 519, 20);
		getContentPane().add(obekt_na_izpitvane_2);
		obekt_na_izpitvane_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		obekt_na_izpitvane_2.setVerticalAlignment(SwingConstants.BOTTOM);
		obekt_na_izpitvane_2.setHorizontalTextPosition(SwingConstants.CENTER);
		obekt_na_izpitvane_2.setHorizontalAlignment(SwingConstants.LEFT);
		obekt_na_izpitvane_2.setForeground(col);
		obekt_na_izpitvane_2.setFont(font);

		str1 = "";
		str2 = "";
		String izpit_pokaz = list_izpitvan_pokazatel.replaceAll("\n", " ");
		izpit_pokaz = izpit_pokaz + " / " + request.getRazmernosti().getName_razmernosti();
		max = 34;
		String txt = izpit_pokaz;
		if (txt.length() >= max) {
			int i = max;
			while (i > 0) {
				if (txt.substring(i - 1, i).equals(" ")) {
					str1 = txt.substring(0, i);
					str2 = txt.substring(i, txt.length());
					i = 0;
				}
				i--;
			}
		}
		JLabel pokazatel_razmernost_1 = new JLabel(str1);
		pokazatel_razmernost_1.setBounds(280, 355, 322, 20);
		getContentPane().add(pokazatel_razmernost_1);
		pokazatel_razmernost_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		pokazatel_razmernost_1.setVerticalAlignment(SwingConstants.BOTTOM);
		pokazatel_razmernost_1.setHorizontalTextPosition(SwingConstants.CENTER);
		pokazatel_razmernost_1.setHorizontalAlignment(SwingConstants.LEFT);
		pokazatel_razmernost_1.setForeground(col);
		pokazatel_razmernost_1.setFont(font);

		JLabel pokazatel_razmernost_2 = new JLabel(str2);
		pokazatel_razmernost_2.setBounds(83, 375, 519, 20);
		getContentPane().add(pokazatel_razmernost_2);
		pokazatel_razmernost_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		pokazatel_razmernost_2.setVerticalAlignment(SwingConstants.BOTTOM);
		pokazatel_razmernost_2.setHorizontalTextPosition(SwingConstants.CENTER);
		pokazatel_razmernost_2.setHorizontalAlignment(SwingConstants.LEFT);
		pokazatel_razmernost_2.setForeground(col);
		pokazatel_razmernost_2.setFont(font);

		str2 = "";
		int count = request.getCounts_samples();
		String descrip_sam_gr_str = request.getDescription_sample_group().replaceAll("\n", " ");
		descrip_sam_gr_str = count + getWordOFNumber(count) + "проби, " + descrip_sam_gr_str;

		
		Boolean ref_date_fl=false, period_fl =false;
		for (int k = 0; k < count; k++) {
		String checkref_date_str = sample_description[k][3];
		String check_period_str = sample_description[k][4];
    	for (int i = 0; i < count; i++) {
    		if(!sample_description[i][3].equals(checkref_date_str))
    			ref_date_fl=true;
    		if(!sample_description[i][4].equals(check_period_str))
    			period_fl=true;
		}
		}
		String samp_str = "";
				
		for (int i = 0; i < count; i++) {
			samp_str =samp_str+ sample_description[i][0]+" / ";
			samp_str =samp_str+ sample_description[i][1]+", ";
			samp_str =samp_str+ sample_description[i][2]+", ";
			if(ref_date_fl)
				samp_str =samp_str+ "реф. дата: "+sample_description[i][3]+", ";
			if(period_fl)
				samp_str =samp_str+ "за "+sample_description[i][4]+" на "+sample_description[i][5]+"г.";
		}
		
		
		max = 34;
		txt = descrip_sam_gr_str+" "+ samp_str;
		if (txt.length() >= max) {
			int i = max;
			while (i > 0) {
				if (txt.substring(i - 1, i).equals(" ")) {
					str1 = txt.substring(0, i);
					str2 = txt.substring(i, txt.length());
					i = 0;
				}
				i--;
			}
		}
		

		max = 68;
		String counts_sample_str = str1;
		String[] desk_samp_str = new String[3];

		 desk_samp_str[0] = str2;
		desk_samp_str[1] = "";
		desk_samp_str[2] = "";
		for (int k = 0; k < 3; k++) {
			if (desk_samp_str[k].length() >= max) {
				int i = max;
				while (i > 0) {

					if (desk_samp_str[k].substring(i - 1, i).equals(" ")) {
						String ss = desk_samp_str[k];
						desk_samp_str[k] = ss.substring(0, i);
						if (k < 2)
							desk_samp_str[k + 1] = ss.substring(i, ss.length());
						i = 0;
					}
					i--;
				}
			}
		}
		JLabel counts_sample = new JLabel(counts_sample_str);
		counts_sample.setBounds(306, 403, 296, 20);
		getContentPane().add(counts_sample);
		counts_sample.setVerticalTextPosition(SwingConstants.BOTTOM);
		counts_sample.setVerticalAlignment(SwingConstants.BOTTOM);
		counts_sample.setHorizontalTextPosition(SwingConstants.CENTER);
		counts_sample.setHorizontalAlignment(SwingConstants.LEFT);
		counts_sample.setForeground(col);
		counts_sample.setFont(font);

		JLabel description_sample_group = new JLabel(desk_samp_str[0]);
		description_sample_group.setBounds(83, 427, 519, 20);
		getContentPane().add(description_sample_group);
		description_sample_group.setVerticalTextPosition(SwingConstants.BOTTOM);
		description_sample_group.setVerticalAlignment(SwingConstants.BOTTOM);
		description_sample_group.setHorizontalTextPosition(SwingConstants.CENTER);
		description_sample_group.setHorizontalAlignment(SwingConstants.LEFT);
		description_sample_group.setForeground(col);
		description_sample_group.setFont(font);

		JLabel description_sample_1 = new JLabel(desk_samp_str[1]);
		description_sample_1.setBounds(83, 448, 519, 20);
		getContentPane().add(description_sample_1);
		description_sample_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		description_sample_1.setVerticalAlignment(SwingConstants.BOTTOM);
		description_sample_1.setHorizontalTextPosition(SwingConstants.CENTER);
		description_sample_1.setHorizontalAlignment(SwingConstants.LEFT);
		description_sample_1.setForeground(col);
		description_sample_1.setFont(font);

		JLabel description_sample_2 = new JLabel(desk_samp_str[2]);
		description_sample_2.setBounds(83, 472, 519, 20);
		getContentPane().add(description_sample_2);
		description_sample_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		description_sample_2.setVerticalAlignment(SwingConstants.BOTTOM);
		description_sample_2.setHorizontalTextPosition(SwingConstants.CENTER);
		description_sample_2.setHorizontalAlignment(SwingConstants.LEFT);
		description_sample_2.setForeground(col);
		description_sample_2.setFont(font);

		JLabel date_time_reception = new JLabel(request.getDate_time_reception());
		date_time_reception.setBounds(180, 505, 422, 20);
		getContentPane().add(date_time_reception);
		date_time_reception.setVerticalTextPosition(SwingConstants.BOTTOM);
		date_time_reception.setVerticalAlignment(SwingConstants.BOTTOM);
		date_time_reception.setHorizontalTextPosition(SwingConstants.CENTER);
		date_time_reception.setHorizontalAlignment(SwingConstants.LEFT);
		date_time_reception.setForeground(col);
		date_time_reception.setFont(font);

		JLabel date_execution = new JLabel(request.getDate_execution());
		date_execution.setBounds(202, 540, 400, 20);
		getContentPane().add(date_execution);
		date_execution.setVerticalTextPosition(SwingConstants.BOTTOM);
		date_execution.setVerticalAlignment(SwingConstants.BOTTOM);
		date_execution.setHorizontalTextPosition(SwingConstants.CENTER);
		date_execution.setHorizontalAlignment(SwingConstants.LEFT);
		date_execution.setForeground(col);
		date_execution.setFont(font);

		JLabel date_time_request = new JLabel(request.getDate_request());
		date_time_request.setBounds(221, 595, 381, 20);
		getContentPane().add(date_time_request);
		date_time_request.setVerticalTextPosition(SwingConstants.BOTTOM);
		date_time_request.setVerticalAlignment(SwingConstants.BOTTOM);
		date_time_request.setHorizontalTextPosition(SwingConstants.CENTER);
		date_time_request.setHorizontalAlignment(SwingConstants.LEFT);
		date_time_request.setForeground(col);
		date_time_request.setFont(font);

		JLabel user = new JLabel(request.getUsers().getName_users() + " " + request.getUsers().getFamily_users());
		user.setBounds(125, 618, 228, 20);
		getContentPane().add(user);
		user.setVerticalTextPosition(SwingConstants.BOTTOM);
		user.setVerticalAlignment(SwingConstants.BOTTOM);
		user.setHorizontalTextPosition(SwingConstants.CENTER);
		user.setHorizontalAlignment(SwingConstants.LEFT);
		user.setForeground(col);
		user.setFont(font);

		String zabel_str = request.getZabelejki().getName_zabelejki();
		str2 = "";

		max = 50;
		txt = zabel_str;
		if (txt.length() >= max) {
			int i = max;
			while (i > 0) {
				if (txt.substring(i - 1, i).equals(" ")) {
					str1 = txt.substring(0, i);
					str2 = txt.substring(i, txt.length());
					i = 0;
				}
				i--;
			}
		}

		JLabel zabelejki_1 = new JLabel(str1);
		zabelejki_1.setBounds(147, 671, 455, 20);
		getContentPane().add(zabelejki_1);
		zabelejki_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		zabelejki_1.setVerticalAlignment(SwingConstants.BOTTOM);
		zabelejki_1.setHorizontalTextPosition(SwingConstants.CENTER);
		zabelejki_1.setHorizontalAlignment(SwingConstants.LEFT);
		zabelejki_1.setForeground(col);
		zabelejki_1.setFont(font);

		JLabel zabelejki_2 = new JLabel(str2);
		zabelejki_2.setBounds(83, 694, 519, 20);
		getContentPane().add(zabelejki_2);
		zabelejki_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		zabelejki_2.setVerticalAlignment(SwingConstants.BOTTOM);
		zabelejki_2.setHorizontalTextPosition(SwingConstants.CENTER);
		zabelejki_2.setHorizontalAlignment(SwingConstants.LEFT);
		zabelejki_2.setForeground(col);
		zabelejki_2.setFont(font);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, d_width, d_height);
		lblNewLabel.setAlignmentY(TOP_ALIGNMENT);
		getContentPane().add(lblNewLabel);
		ImageIcon pic = new ImageIcon("Zaqvka.png");
		lblNewLabel.setIcon(new ImageIcon("E:\\JAVA Project Petar\\JPA_RH_DBase\\Zaqvka.png"));

//		lblNewLabel.setIcon(new ImageIcon("C:\\Soft\\JAVA Project\\JPA_RH_DBase\\Zaqvka.png"));
		

		setVisible(true);
	
	}


		


	public String getWordOFNumber(int num) {
		String str = "";
		String str2 = "";
		if (num > 12) {
			num = num - 10;
			str2 = "надесет";
		}

		switch (num) {
		case 1:
			str = "един";
			break;

		case 2:
			str = "двa";
			break;

		case 3:
			str = "три";
			break;

		case 4:
			str = "четири";
			break;

		case 5:
			str = "пет";
			break;

		case 6:
			str = "шест";
			break;

		case 7:
			str = "седем";
			break;

		case 8:
			str = "осем";
			break;

		case 9:
			str = "девет";
			break;

		case 10:
			str = "десет";
			break;

		case 11:
			str = "единадесет";
			break;

		case 12:
			str = "дванадесет";
			break;
		}
		if (num == 1) {
			return "(" + str2 + ") брой ";
		} else
			return "(" + str + str2 + ") бро€ ";
	}

  
    
}
