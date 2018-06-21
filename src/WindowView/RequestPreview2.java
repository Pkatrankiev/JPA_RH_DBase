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
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class RequestPreview2 extends JFrame {

	private int d_width = 610, d_height = 865;

	public RequestPreview2(Request request, String list_izpitvan_pokazatel, String[][] sample_description) {
		super("ѕреглед на за€вка");

		Font font = new Font("Magnolia Script", Font.ITALIC, 12);
		Color col = Color.BLUE;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(630, 920);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setBackground(Color.GREEN);
		scrollpane.setName("");
		scrollpane.setBorder(null);

		// *****************************************

		// *********************************************

		getContentPane().add(scrollpane);

		JPanel main_panel = new JPanel();
		scrollpane.setViewportView(main_panel);
		GridBagLayout gbl_main_panel = new GridBagLayout();
		gbl_main_panel.columnWidths = new int[] { d_width };
		gbl_main_panel.rowHeights = new int[] { d_height };
		gbl_main_panel.columnWeights = new double[] { 1.0 };
		gbl_main_panel.rowWeights = new double[] { 1.0 };
		main_panel.setLayout(gbl_main_panel);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		main_panel.add(panel, gbc_panel);

		panel.setSize(d_width, d_height);
		panel.setBackground(Color.MAGENTA);

		JLabel request_code = new JLabel(request.getRecuest_code());
		request_code.setBounds(250, 146, 62, 20);
		panel.add(request_code);
		// request_code.setVerticalTextPosition(SwingConstants.BOTTOM);
		// request_code.setHorizontalTextPosition(SwingConstants.CENTER);
		request_code.setHorizontalAlignment(SwingConstants.RIGHT);
		request_code.setFont(font);
		request_code.setVerticalAlignment(SwingConstants.BOTTOM);
		request_code.setForeground(col);

		JLabel request_date = new JLabel(request.getDate_request());
		request_date.setBounds(320, 146, 98, 20);
		panel.add(request_date);
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
		ind_num_doc.setBounds(327, 215, 228, 20);
		panel.add(ind_num_doc);
		ind_num_doc.setVerticalTextPosition(SwingConstants.BOTTOM);
		ind_num_doc.setVerticalAlignment(SwingConstants.BOTTOM);
		ind_num_doc.setHorizontalTextPosition(SwingConstants.CENTER);
		ind_num_doc.setHorizontalAlignment(SwingConstants.LEFT);
		ind_num_doc.setForeground(col);
		ind_num_doc.setFont(font);

		JLabel izpitvan_produkt = new JLabel(request.getIzpitvan_produkt().getName_zpitvan_produkt());
		izpitvan_produkt.setBounds(171, 255, 384, 20);
		panel.add(izpitvan_produkt);
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
		obekt_na_izpitvane_1.setBounds(250, 284, 310, 20);
		panel.add(obekt_na_izpitvane_1);
		obekt_na_izpitvane_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		obekt_na_izpitvane_1.setVerticalAlignment(SwingConstants.BOTTOM);
		obekt_na_izpitvane_1.setHorizontalTextPosition(SwingConstants.CENTER);
		obekt_na_izpitvane_1.setHorizontalAlignment(SwingConstants.LEFT);
		obekt_na_izpitvane_1.setForeground(col);
		obekt_na_izpitvane_1.setFont(font);

		JLabel obekt_na_izpitvane_2 = new JLabel(str2);
		obekt_na_izpitvane_2.setBounds(71, 298, 484, 20);
		panel.add(obekt_na_izpitvane_2);
		obekt_na_izpitvane_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		obekt_na_izpitvane_2.setVerticalAlignment(SwingConstants.BOTTOM);
		obekt_na_izpitvane_2.setHorizontalTextPosition(SwingConstants.CENTER);
		obekt_na_izpitvane_2.setHorizontalAlignment(SwingConstants.LEFT);
		obekt_na_izpitvane_2.setForeground(col);
		obekt_na_izpitvane_2.setFont(font);
		JLabel pokazatel_razmernost_1 = new JLabel(str1);
		pokazatel_razmernost_1.setBounds(247, 321, 310, 20);
		panel.add(pokazatel_razmernost_1);
		pokazatel_razmernost_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		pokazatel_razmernost_1.setVerticalAlignment(SwingConstants.BOTTOM);
		pokazatel_razmernost_1.setHorizontalTextPosition(SwingConstants.CENTER);
		pokazatel_razmernost_1.setHorizontalAlignment(SwingConstants.LEFT);
		pokazatel_razmernost_1.setForeground(col);
		pokazatel_razmernost_1.setFont(font);

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

		JLabel pokazatel_razmernost_2 = new JLabel(str2);
		pokazatel_razmernost_2.setBounds(71, 341, 484, 20);
		panel.add(pokazatel_razmernost_2);
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

		Boolean ref_date_fl = false, period_fl = false;
		for (int k = 0; k < count; k++) {
			String checkref_date_str = sample_description[k][3];
			String check_period_str = sample_description[k][4];
			for (int i = 0; i < count; i++) {
				if (!sample_description[i][3].equals(checkref_date_str))
					ref_date_fl = true;
				if (!sample_description[i][4].equals(check_period_str))
					period_fl = true;
			}
		}
		String samp_str = "";

		for (int i = 0; i < count; i++) {
			samp_str = samp_str + sample_description[i][0] + " / ";
			samp_str = samp_str + sample_description[i][1] + ", ";
			samp_str = samp_str + sample_description[i][2] + ", ";
			if (ref_date_fl)
				samp_str = samp_str + "реф. дата: " + sample_description[i][3] + ", ";
			if (period_fl)
				samp_str = samp_str + "за " + sample_description[i][4] + " на " + sample_description[i][5] + "г.";
		}

		max = 34;
		txt = descrip_sam_gr_str + " " + samp_str;
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

		max = 58;
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
		counts_sample.setBounds(282, 366, 274, 20);
		panel.add(counts_sample);
		counts_sample.setVerticalTextPosition(SwingConstants.BOTTOM);
		counts_sample.setVerticalAlignment(SwingConstants.BOTTOM);
		counts_sample.setHorizontalTextPosition(SwingConstants.CENTER);
		counts_sample.setHorizontalAlignment(SwingConstants.LEFT);
		counts_sample.setForeground(col);
		counts_sample.setFont(font);

		JLabel description_sample_group = new JLabel(desk_samp_str[0]);
		description_sample_group.setBounds(72, 387, 484, 20);
		panel.add(description_sample_group);
		description_sample_group.setVerticalTextPosition(SwingConstants.BOTTOM);
		description_sample_group.setVerticalAlignment(SwingConstants.BOTTOM);
		description_sample_group.setHorizontalTextPosition(SwingConstants.CENTER);
		description_sample_group.setHorizontalAlignment(SwingConstants.LEFT);
		description_sample_group.setForeground(col);
		description_sample_group.setFont(font);

		JLabel description_sample_1 = new JLabel(desk_samp_str[1]);
		description_sample_1.setBounds(71, 408, 484, 20);
		panel.add(description_sample_1);
		description_sample_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		description_sample_1.setVerticalAlignment(SwingConstants.BOTTOM);
		description_sample_1.setHorizontalTextPosition(SwingConstants.CENTER);
		description_sample_1.setHorizontalAlignment(SwingConstants.LEFT);
		description_sample_1.setForeground(col);
		description_sample_1.setFont(font);

		JLabel description_sample_2 = new JLabel(desk_samp_str[2]);
		description_sample_2.setBounds(72, 428, 484, 20);
		panel.add(description_sample_2);
		description_sample_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		description_sample_2.setVerticalAlignment(SwingConstants.BOTTOM);
		description_sample_2.setHorizontalTextPosition(SwingConstants.CENTER);
		description_sample_2.setHorizontalAlignment(SwingConstants.LEFT);
		description_sample_2.setForeground(col);
		description_sample_2.setFont(font);

		JLabel date_time_reception = new JLabel(request.getDate_time_reception());
		date_time_reception.setBounds(165, 459, 289, 20);
		panel.add(date_time_reception);
		date_time_reception.setVerticalTextPosition(SwingConstants.BOTTOM);
		date_time_reception.setVerticalAlignment(SwingConstants.BOTTOM);
		date_time_reception.setHorizontalTextPosition(SwingConstants.CENTER);
		date_time_reception.setHorizontalAlignment(SwingConstants.LEFT);
		date_time_reception.setForeground(col);
		date_time_reception.setFont(font);

		JLabel date_execution = new JLabel(request.getDate_execution());
		date_execution.setBounds(188, 490, 289, 20);
		panel.add(date_execution);
		date_execution.setVerticalTextPosition(SwingConstants.BOTTOM);
		date_execution.setVerticalAlignment(SwingConstants.BOTTOM);
		date_execution.setHorizontalTextPosition(SwingConstants.CENTER);
		date_execution.setHorizontalAlignment(SwingConstants.LEFT);
		date_execution.setForeground(col);
		date_execution.setFont(font);

		JLabel date_time_request = new JLabel(request.getDate_request());
		date_time_request.setBounds(202, 540, 274, 20);
		panel.add(date_time_request);
		date_time_request.setVerticalTextPosition(SwingConstants.BOTTOM);
		date_time_request.setVerticalAlignment(SwingConstants.BOTTOM);
		date_time_request.setHorizontalTextPosition(SwingConstants.CENTER);
		date_time_request.setHorizontalAlignment(SwingConstants.LEFT);
		date_time_request.setForeground(col);
		date_time_request.setFont(font);

		JLabel user = new JLabel(request.getUsers().getName_users() + " " + request.getUsers().getFamily_users());
		user.setBounds(113, 560, 228, 20);
		panel.add(user);
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
		zabelejki_1.setBounds(133, 611, 422, 20);
		panel.add(zabelejki_1);
		zabelejki_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		zabelejki_1.setVerticalAlignment(SwingConstants.BOTTOM);
		zabelejki_1.setHorizontalTextPosition(SwingConstants.CENTER);
		zabelejki_1.setHorizontalAlignment(SwingConstants.LEFT);
		zabelejki_1.setForeground(col);
		zabelejki_1.setFont(font);

		JLabel zabelejki_2 = new JLabel(str2);
		zabelejki_2.setBounds(71, 631, 484, 20);
		panel.add(zabelejki_2);
		zabelejki_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		zabelejki_2.setVerticalAlignment(SwingConstants.BOTTOM);
		zabelejki_2.setHorizontalTextPosition(SwingConstants.CENTER);
		zabelejki_2.setHorizontalAlignment(SwingConstants.LEFT);
		zabelejki_2.setForeground(col);
		zabelejki_2.setFont(font);

		ImageIcon pic = new ImageIcon("Zaqvka.png");
		// lblNewLabel.setIcon(new ImageIcon("C:\\Soft\\JAVA
		// Project\\JPA_RH_DBase\\Zaqvka.png"));

		Dimension d = getSize();
		File source = new File("C:\\Soft\\JAVA Project\\JPA_RH_DBase\\Zaqvka.png");
		ImageIcon pic1 = rescaleImage(source, d.height, d.width);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, d_width, d_height);
		panel.add(lblNewLabel);
		lblNewLabel.setAlignmentY(TOP_ALIGNMENT);
		lblNewLabel.setIcon(new ImageIcon("C:\\Soft\\JAVA Project\\JPA_RH_DBase\\Zaqvka.png"));

		setVisible(true);

		panel.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent event) {
				Dimension dim_label = lblNewLabel.getSize();
				Dimension new_d = getSize();
				Dimension dim = rescaleComponent(new_d.height, new_d.width,  dim_label);
				
				d_height = (int) dim.getHeight();
				d_width = (int) dim.getWidth();
				gbl_main_panel.rowHeights = new int[] { d_height };
				panel.setSize(d_width, d_height);
				lblNewLabel.setBounds(0, 0, d_width, d_height);
				
				ImageIcon pic1 = rescaleImage(source, new_d.height, new_d.width);
				lblNewLabel.setIcon(pic1);
					

				System.out.println(d_width + " " + d_height);
				int w = (d_width - 610) / 2;
				int h = (d_height - 865) / 2;
				request_code.setBounds(250 + w, 146 + h, 62, 20);

			}
		});

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

	public ImageIcon rescaleImage(File source, int maxHeight, int maxWidth) {
		int newHeight = 0, newWidth = 0; // Variables for the new height and
											// width
		int priorHeight = 0, priorWidth = 0;
		BufferedImage image = null;
		ImageIcon sizeImage;

		try {
			image = ImageIO.read(source); // get the image
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Picture upload attempted & failed");
		}

		sizeImage = new ImageIcon(image);

		if (sizeImage != null) {
			priorHeight = sizeImage.getIconHeight();
			priorWidth = sizeImage.getIconWidth();
		}

		// Calculate the correct new height and width
		// if((float)priorHeight/(float)priorWidth >
		// (float)maxHeight/(float)maxWidth)
		// {
		// newHeight = maxHeight;
		// newWidth =
		// (int)(((float)priorWidth/(float)priorHeight)*(float)newHeight);
		// }
		// else
		{
			newWidth = maxWidth;
			newHeight = (int) (((float) priorHeight / (float) priorWidth) * (float) newWidth);
		}

		// Resize the image

		// 1. Create a new Buffered Image and Graphic2D object
		BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedImg.createGraphics();

		// 2. Use the Graphic object to draw a new image to the image in the
		// buffer
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(image, 0, 0, newWidth, newHeight, null);
		g2.dispose();

		// 3. Convert the buffered image into an ImageIcon for return
		return (new ImageIcon(resizedImg));
	}

	public Dimension rescaleComponent(int maxHeight, int maxWidth, Dimension dim_label) {
		int newHeight = 0, newWidth = 0; // Variables for the new height and
		// width
		int priorHeight = 0, priorWidth = 0;
					
			priorHeight = (int) dim_label.getHeight();
			priorWidth = (int) dim_label.getWidth();
				
			newWidth = maxWidth;
			newHeight = (int) (((float) priorHeight / (float) priorWidth) * (float) newWidth);
		
			Dimension d = new Dimension();
		 d.setSize(newWidth, newHeight);
		return d;
	}
}
