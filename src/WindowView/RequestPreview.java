package WindowView;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
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
import javax.swing.border.LineBorder;

import org.eclipse.persistence.internal.sessions.remote.SequencingFunctionCall.WhenShouldAcquireValueForAll;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class RequestPreview extends JFrame {

	private JPanel contentPane;


	public RequestPreview(Request request, String list_izpitvan_pokazatel) {
		super("Преглед на заявка");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 650,920);
		getContentPane().setLayout(null);
		
		Panel panel_front = new Panel();
		panel_front.setBounds(0, 0, 634, 882);
		panel_front.setBackground(new Color(255,255,255,255));
		getContentPane().add(panel_front);
		panel_front.setLayout(null);
		
		JLabel request_code = new JLabel(request.getRecuest_code());
		request_code.setVerticalTextPosition(SwingConstants.BOTTOM);
		request_code.setHorizontalTextPosition(SwingConstants.CENTER);
		request_code.setHorizontalAlignment(SwingConstants.RIGHT);
		request_code.setFont(new Font("Vivaldi", Font.BOLD, 16));
		request_code.setVerticalAlignment(SwingConstants.BOTTOM);
		request_code.setForeground(Color.BLACK);
		request_code.setBounds(265, 143, 62, 20);
		panel_front.add(request_code);
		
		JLabel request_date = new JLabel(request.getDate_request());
		request_date.setVerticalTextPosition(SwingConstants.BOTTOM);
		request_date.setVerticalAlignment(SwingConstants.BOTTOM);
		request_date.setHorizontalTextPosition(SwingConstants.CENTER);
		request_date.setHorizontalAlignment(SwingConstants.LEFT);
		request_date.setForeground(Color.BLACK);
		request_date.setFont(new Font("Vivaldi", Font.BOLD, 16));
		request_date.setBounds(347, 143, 98, 20);
		panel_front.add(request_date);
		
		String str = "";
		if(request.getInd_num_doc()!=null)
			str = request.getInd_num_doc().getName();
		JLabel ind_num_doc = new JLabel(str);
		ind_num_doc.setVerticalTextPosition(SwingConstants.BOTTOM);
		ind_num_doc.setVerticalAlignment(SwingConstants.BOTTOM);
		ind_num_doc.setHorizontalTextPosition(SwingConstants.CENTER);
		ind_num_doc.setHorizontalAlignment(SwingConstants.LEFT);
		ind_num_doc.setForeground(Color.BLACK);
		ind_num_doc.setFont(new Font("Vivaldi", Font.BOLD, 16));
		ind_num_doc.setBounds(347, 222, 228, 20);
		panel_front.add(ind_num_doc);
		
		JLabel izpitvan_produkt = new JLabel(request.getIzpitvan_produkt().getName_zpitvan_produkt());
		izpitvan_produkt.setVerticalTextPosition(SwingConstants.BOTTOM);
		izpitvan_produkt.setVerticalAlignment(SwingConstants.BOTTOM);
		izpitvan_produkt.setHorizontalTextPosition(SwingConstants.CENTER);
		izpitvan_produkt.setHorizontalAlignment(SwingConstants.LEFT);
		izpitvan_produkt.setForeground(Color.BLACK);
		izpitvan_produkt.setFont(new Font("Vivaldi", Font.BOLD, 16));
		izpitvan_produkt.setBounds(191, 262, 384, 20);
		panel_front.add(izpitvan_produkt);
		
		String strObect = request.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();
		String str1 = "",str2 = "";
		if(strObect.length()>=36)
			for (int i = 36; i > 0; i--) {
				if(strObect.substring(i,i).equals(" ")){
					str1 = strObect.substring(0, i);
					str2 = strObect.substring(i+1, strObect.length());
				}
			}
			
		
		JLabel obekt_na_izpitvane_1 = new JLabel(str1);
		obekt_na_izpitvane_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		obekt_na_izpitvane_1.setVerticalAlignment(SwingConstants.BOTTOM);
		obekt_na_izpitvane_1.setHorizontalTextPosition(SwingConstants.CENTER);
		obekt_na_izpitvane_1.setHorizontalAlignment(SwingConstants.LEFT);
		obekt_na_izpitvane_1.setForeground(Color.BLACK);
		obekt_na_izpitvane_1.setFont(new Font("Vivaldi", Font.BOLD, 16));
		obekt_na_izpitvane_1.setBounds(265, 293, 310, 20);
		panel_front.add(obekt_na_izpitvane_1);
		
		JLabel obekt_na_izpitvane_2 = new JLabel(str2);
		obekt_na_izpitvane_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		obekt_na_izpitvane_2.setVerticalAlignment(SwingConstants.BOTTOM);
		obekt_na_izpitvane_2.setHorizontalTextPosition(SwingConstants.CENTER);
		obekt_na_izpitvane_2.setHorizontalAlignment(SwingConstants.LEFT);
		obekt_na_izpitvane_2.setForeground(Color.BLACK);
		obekt_na_izpitvane_2.setFont(new Font("Vivaldi", Font.BOLD, 16));
		obekt_na_izpitvane_2.setBounds(91, 305, 484, 20);
		panel_front.add(obekt_na_izpitvane_2);
		
		
		str1 = ""; str2 = "";
		String izpit_pokaz = list_izpitvan_pokazatel.replaceAll("\n", " ");
		izpit_pokaz = izpit_pokaz+" "+request.getRazmernosti().getName_razmernosti();
		if(izpit_pokaz.length()>=36)
			for (int i = 36; i > 0; i--) {
				if(izpit_pokaz.substring(i,i).equals(" ")){
					str1 = izpit_pokaz.substring(0, i);
					str2 = izpit_pokaz.substring(i+1, izpit_pokaz.length());
				}
			}
		
		JLabel pokazatel_razmernost_1 = new JLabel(str1);
		pokazatel_razmernost_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		pokazatel_razmernost_1.setVerticalAlignment(SwingConstants.BOTTOM);
		pokazatel_razmernost_1.setHorizontalTextPosition(SwingConstants.CENTER);
		pokazatel_razmernost_1.setHorizontalAlignment(SwingConstants.LEFT);
		pokazatel_razmernost_1.setForeground(Color.BLACK);
		pokazatel_razmernost_1.setFont(new Font("Vivaldi", Font.BOLD, 16));
		pokazatel_razmernost_1.setBounds(265, 329, 310, 20);
		panel_front.add(pokazatel_razmernost_1);
		
		JLabel pokazatel_razmernost_2 = new JLabel(str2);
		pokazatel_razmernost_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		pokazatel_razmernost_2.setVerticalAlignment(SwingConstants.BOTTOM);
		pokazatel_razmernost_2.setHorizontalTextPosition(SwingConstants.CENTER);
		pokazatel_razmernost_2.setHorizontalAlignment(SwingConstants.LEFT);
		pokazatel_razmernost_2.setForeground(Color.BLACK);
		pokazatel_razmernost_2.setFont(new Font("Vivaldi", Font.BOLD, 16));
		pokazatel_razmernost_2.setBounds(91, 346, 484, 20);
		panel_front.add(pokazatel_razmernost_2);
		
		
		String counts_sample_str = ""; str2 = "";
		int count = request.getCounts_samples();
		String descrip_sam_gr_str = request.getDescription_sample_group().replaceAll("\n", " ");
		descrip_sam_gr_str = count+"("+getWordOFNumber(count)+") брои проби "+ descrip_sam_gr_str;
		
		if(descrip_sam_gr_str.length()>=32)
			for (int i = 32; i > 0; i--) {
				if(descrip_sam_gr_str.substring(i,i).equals(" ")){
					counts_sample_str = descrip_sam_gr_str.substring(0, i);
					str2 = descrip_sam_gr_str.substring(i+1, descrip_sam_gr_str.length());
				}
			}
		
		
		JLabel counts_sample = new JLabel(counts_sample_str);
		counts_sample.setVerticalTextPosition(SwingConstants.BOTTOM);
		counts_sample.setVerticalAlignment(SwingConstants.BOTTOM);
		counts_sample.setHorizontalTextPosition(SwingConstants.CENTER);
		counts_sample.setHorizontalAlignment(SwingConstants.LEFT);
		counts_sample.setForeground(Color.BLACK);
		counts_sample.setFont(new Font("Vivaldi", Font.BOLD, 16));
		counts_sample.setBounds(301, 373, 274, 20);
		panel_front.add(counts_sample);
		
		String description_sample_str = request.getDescription_sample_group().replaceAll("\n", " ");
		description_sample_str = str2 + description_sample_str;
		String[] desk_samp_str = new String[3];
		desk_samp_str[0] ="";
		desk_samp_str[1] ="";
		desk_samp_str[2] ="";
						
		desk_samp_str[0] = description_sample_str;
			for (int k = 0; k < 3; k++) {
				if(desk_samp_str[k].length()>=58){
					int i = 58;
					while (i > 0) {
//					for (int i = 58; i > 0; i--) {
				if(descrip_sam_gr_str.substring(i,i).equals(" ")){
					desk_samp_str[k] = desk_samp_str[k].substring(0, i);
					desk_samp_str[k+1] = desk_samp_str[k].substring(i+1, descrip_sam_gr_str.length());
					i=0;
				}
				i--;
			}
				}
			}
		
		
		JLabel description_sample_group = new JLabel(desk_samp_str[0]);
		description_sample_group.setVerticalTextPosition(SwingConstants.BOTTOM);
		description_sample_group.setVerticalAlignment(SwingConstants.BOTTOM);
		description_sample_group.setHorizontalTextPosition(SwingConstants.CENTER);
		description_sample_group.setHorizontalAlignment(SwingConstants.LEFT);
		description_sample_group.setForeground(Color.BLACK);
		description_sample_group.setFont(new Font("Vivaldi", Font.BOLD, 16));
		description_sample_group.setBounds(91, 393, 484, 20);
		panel_front.add(description_sample_group);
		
		JLabel description_sample_1 = new JLabel(desk_samp_str[1]);
		description_sample_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		description_sample_1.setVerticalAlignment(SwingConstants.BOTTOM);
		description_sample_1.setHorizontalTextPosition(SwingConstants.CENTER);
		description_sample_1.setHorizontalAlignment(SwingConstants.LEFT);
		description_sample_1.setForeground(Color.BLACK);
		description_sample_1.setFont(new Font("Vivaldi", Font.BOLD, 16));
		description_sample_1.setBounds(91, 413, 484, 20);
		panel_front.add(description_sample_1);
		
		JLabel description_sample_2 = new JLabel(desk_samp_str[2]);
		description_sample_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		description_sample_2.setVerticalAlignment(SwingConstants.BOTTOM);
		description_sample_2.setHorizontalTextPosition(SwingConstants.CENTER);
		description_sample_2.setHorizontalAlignment(SwingConstants.LEFT);
		description_sample_2.setForeground(Color.BLACK);
		description_sample_2.setFont(new Font("Vivaldi", Font.BOLD, 16));
		description_sample_2.setBounds(91, 435, 484, 20);
		panel_front.add(description_sample_2);
		
		JLabel date_time_reception = new JLabel(request.getDate_time_reception());
		date_time_reception.setVerticalTextPosition(SwingConstants.BOTTOM);
		date_time_reception.setVerticalAlignment(SwingConstants.BOTTOM);
		date_time_reception.setHorizontalTextPosition(SwingConstants.CENTER);
		date_time_reception.setHorizontalAlignment(SwingConstants.LEFT);
		date_time_reception.setForeground(Color.BLACK);
		date_time_reception.setFont(new Font("Vivaldi", Font.BOLD, 16));
		date_time_reception.setBounds(203, 466, 289, 20);
		panel_front.add(date_time_reception);
		
		JLabel date_execution = new JLabel(request.getDate_execution());
		date_execution.setVerticalTextPosition(SwingConstants.BOTTOM);
		date_execution.setVerticalAlignment(SwingConstants.BOTTOM);
		date_execution.setHorizontalTextPosition(SwingConstants.CENTER);
		date_execution.setHorizontalAlignment(SwingConstants.LEFT);
		date_execution.setForeground(Color.BLACK);
		date_execution.setFont(new Font("Vivaldi", Font.BOLD, 16));
		date_execution.setBounds(203, 497, 289, 20);
		panel_front.add(date_execution);
		
		JLabel date_time_request = new JLabel(request.getDate_request());
		date_time_request.setVerticalTextPosition(SwingConstants.BOTTOM);
		date_time_request.setVerticalAlignment(SwingConstants.BOTTOM);
		date_time_request.setHorizontalTextPosition(SwingConstants.CENTER);
		date_time_request.setHorizontalAlignment(SwingConstants.LEFT);
		date_time_request.setForeground(Color.BLACK);
		date_time_request.setFont(new Font("Vivaldi", Font.BOLD, 16));
		date_time_request.setBounds(234, 546, 274, 20);
		panel_front.add(date_time_request);
		
		JLabel user = new JLabel(request.getUsers().getName_users()+" "+request.getUsers().getFamily_users());
		user.setVerticalTextPosition(SwingConstants.BOTTOM);
		user.setVerticalAlignment(SwingConstants.BOTTOM);
		user.setHorizontalTextPosition(SwingConstants.CENTER);
		user.setHorizontalAlignment(SwingConstants.LEFT);
		user.setForeground(Color.BLACK);
		user.setFont(new Font("Vivaldi", Font.BOLD, 16));
		user.setBounds(129, 567, 228, 20);
		panel_front.add(user);
		
		String zabel_str = ""; str2 = "";
		zabel_str = request.getZabelejki().getName_zabelejki();
		
		
		if(zabel_str.length()>=50)
			for (int i = 50; i > 0; i--) {
				if(zabel_str.substring(i,i).equals(" ")){
					zabel_str = zabel_str.substring(0, i);
					str2 = zabel_str.substring(i+1, zabel_str.length());
				}
			}
		
		
		
		JLabel zabelejki_1 = new JLabel(zabel_str);
		zabelejki_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		zabelejki_1.setVerticalAlignment(SwingConstants.BOTTOM);
		zabelejki_1.setHorizontalTextPosition(SwingConstants.CENTER);
		zabelejki_1.setHorizontalAlignment(SwingConstants.LEFT);
		zabelejki_1.setForeground(Color.BLACK);
		zabelejki_1.setFont(new Font("Vivaldi", Font.BOLD, 16));
		zabelejki_1.setBounds(153, 617, 422, 20);
		panel_front.add(zabelejki_1);
		
		JLabel zabelejki_2 = new JLabel(str2);
		zabelejki_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		zabelejki_2.setVerticalAlignment(SwingConstants.BOTTOM);
		zabelejki_2.setHorizontalTextPosition(SwingConstants.CENTER);
		zabelejki_2.setHorizontalAlignment(SwingConstants.LEFT);
		zabelejki_2.setForeground(Color.BLACK);
		zabelejki_2.setFont(new Font("Vivaldi", Font.BOLD, 16));
		zabelejki_2.setBounds(91, 638, 484, 20);
		panel_front.add(zabelejki_2);
		
		Panel panel_background = new Panel();
		panel_background.setBounds(0, 0, 634, 882);
		getContentPane().add(panel_background);
		panel_background.setVisible(true);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Soft\\JAVA Project\\JPA_RH_DBase\\Zaqvka.png"));
		panel_background.add(lblNewLabel);
		

		
//		CirclePanel topPanel = new CirclePanel();
//        // drawing should be in blue
//        topPanel.setForeground(Color.blue);
//        // background should be black, except it's not opaque, so 
//        // background will not be drawn
//        topPanel.setBackground(Color.black);
//        // set opaque to false - background not drawn
//        topPanel.setOpaque(false);
//        topPanel.setBounds(50, 50, 100, 100);
//        // add topPanel - components paint in order added, 
//        // so add topPanel first
//        getContentPane().add(topPanel);
        
//		Panel panel = new Panel();
//		 BufferedImage img = null;
//		 try {
//		File f = new File("Zaqvka.png");
//        img = ImageIO.read(f);
//		 } catch (Exception e) {
//	            System.out.println("Cannot read file: " + e);
//	        }
//		BackgroundPanel background = new BackgroundPanel(img, BackgroundPanel.SCALED, 0.50f, 0.5f);
//        
//		 panel.setContentPane(background);
//		
			
		
		setVisible(true);
	}
public String getWordOFNumber(int num){
	String str="";
	String str2 = "";
	if(num>12){
		num= num-10;
		str2 = "надесет";
	}
	
	switch (num)
	{
		case 1 :
			str="една";
			break;

		case 2 :
			str="две";
			break;

		case 3 :
			str="три";
			break;

		case 4 :
			str="четири";
			break;
			
		case 5 :
			str="пет";
			break;
			
		case 6 :
			str="шест";
			break;
			
		case 7 :
			str="седем";
			break;
			
		case 8 :
			str="осем";
			break;
			
		case 9 :
			str="девет";
			break;
			
		case 10 :
			str="десет";
			break;
			
		case 11 :
			str="единадесет";
			break;
			
		case 12 :
			str="дванадесет";
			break;
	}
	
	
	return str+str2;
}
}

