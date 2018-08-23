package WindowView;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import WindowViewAplication.RequestViewAplication;

public class DatePicker {

	// define variables
	int month = Calendar.getInstance().get(Calendar.MONTH);
	int year = Calendar.getInstance().get(Calendar.YEAR);
	int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	int minute = Calendar.getInstance().get(Calendar.MINUTE);
	int day1 = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	final Calendar cal_time = Calendar.getInstance();
	private Font font = new Font("Tahoma", Font.PLAIN, 14);

	// create object of JLabel with alignment
	JLabel l = new JLabel("", JLabel.CENTER);
	// define variable
	String day = "";
	// declaration
	JDialog d;
	// create object of JButton
	JButton[] button = new JButton[49];

	JLabel lab = new JLabel();

	public DatePicker(JFrame parent, Boolean inTime)// create constructor
	{
		// create object
		d = new JDialog();
		// set modal true
		d.setModal(true);
		// define string
		String[] header = { "Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Нд" };
		// create JPanel object and set layout
		JPanel p1 = new JPanel(new GridLayout(7, 7));
		// set size
		p1.setPreferredSize(new Dimension(430, 120));
		// for loop condition
		for (int x = 0; x < button.length; x++) {
			// define variable
			final int selection = x;
			// create object of JButton
			button[x] = new JButton();
			button[x].setFont(font);
			// set focus painted false
			button[x].setFocusPainted(false);
			// set background colour
			button[x].setBackground(Color.white);
			// if loop condition
			if (x > 6) {

				// add action listener
				button[x].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						if (!button[selection].getText().equals("")) {
							day = button[selection].getActionCommand();
							// call dispose() method
							d.dispose();
						}
					}
				});

			}
			if (x < 7)// if loop condition
			{
				button[x].setText(header[x]);
				// set fore ground colour
				button[x].setForeground(Color.red);
			}

			p1.add(button[x]);// add button

		}
		// create JPanel object with grid layout
		JPanel p2 = new JPanel(new GridLayout(1, 3));

		// create object of button for previous month
		JButton data_previous = new JButton("<< Назад");
		data_previous.setFont(font);
		// add action command
		data_previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// decrement month by 1
				month--;
				// call method
				displayDate();
			}
		});
		p2.add(data_previous);// add button
		p2.add(l);// add label
		// create object of button for next month
		JButton data_next = new JButton("Напред >>");
		data_next.setFont(font);
		// add action command
		data_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// increment month by 1
				month++;
				// call method
				displayDate();
			}
		});
		p2.add(data_next);// add next button

		JPanel panel = new JPanel();
		d.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 3, 0, 0));

		if (inTime)
			getTimeModule(panel);

		// set border alignment
		d.getContentPane().add(p1, BorderLayout.CENTER);
		d.getContentPane().add(p2, BorderLayout.SOUTH);
		d.pack();
		// set location
		d.setLocationRelativeTo(parent);
		// call method
		displayDate();
		// set visible true
		d.setVisible(true);
	}

	private void getTimeModule(JPanel panel) {
		Date data_time = null;
		final SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm");
		final String time = hour + ":00";
		final JLabel label = new JLabel(time, SwingConstants.CENTER);

		try {
			data_time = sdf_time.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cal_time.setTime(data_time);

		JButton previous_time_btn = new JButton("<< T Назад");
		previous_time_btn.setFont(font);
		previous_time_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// decrement time by 10
				cal_time.add(Calendar.MINUTE, -10);
				String new_time = sdf_time.format(cal_time.getTime());
				label.setText(new_time);

			}
		});

		panel.add(previous_time_btn);

		label.setText(time);

		panel.add(label);

		JButton next_time_btn = new JButton("Напред T >>");
		next_time_btn.setFont(font);
		next_time_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// increment time by 10
				cal_time.add(Calendar.MINUTE, 10);
				String new_time = sdf_time.format(cal_time.getTime());
				label.setText(new_time);

			}
		});
		panel.add(next_time_btn);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public void displayDate() {

		for (int x = 7; x < button.length; x++) {// for loop
			button[x].setText("");// set text
			button[x].setBackground(Color.white);
		}
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM-yyyy");
		// create object of SimpleDateFormat
		Calendar cal = Calendar.getInstance();
		Calendar calDay = Calendar.getInstance();
		// create object of java.util.Calendar
		cal.set(year, month, 1); // set year, month and date
		// define variables
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// condition
		if (dayOfWeek == 1) {
			dayOfWeek = 7;
		} else
			dayOfWeek = dayOfWeek - 1;
		SimpleDateFormat sdfDay = new SimpleDateFormat("dd-MM-yyyy");
		for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) { // set
																				// text
			button[x].setText("" + day);
			calDay.set(year, month, day);
			if (calDay.get(Calendar.DAY_OF_WEEK) == 7 || calDay.get(Calendar.DAY_OF_WEEK) == 1)
				button[x].setForeground(Color.red);

			if (RequestViewAplication.DateNaw(false).equals(sdfDay.format(calDay.getTime()))) {
				System.out.println(RequestViewAplication.DateNaw(false) + " " + sdfDay.format(calDay.getTime()));
				button[x].setBackground(Color.CYAN);
			}
		}
		for (int x = 0; x < button.length; x++) {
			if (button[x].getText().equals("")) {
				button[x].setEnabled(false);
//				button[x].setBorder(null);
			} else {
				button[x].setEnabled(true);
			}
		}
		l.setText(sdf.format(cal.getTime()));
		// set title
		d.setTitle("Дата");

	}

	public String setPickedDate(Boolean inTime) {
		// if condition
		if (day.equals(""))
			return day;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Calendar cal = Calendar.getInstance();
		if (inTime) {
			cal.set(year, month, Integer.parseInt(day), cal_time.get(Calendar.HOUR_OF_DAY),
					cal_time.get(Calendar.MINUTE));
		} else {
			sdf = new SimpleDateFormat("dd-MM-yyyy");
			cal.set(year, month, Integer.parseInt(day));
		}
		return sdf.format(cal.getTime());
	}

	private static long getSecondDuration(LocalDateTime t) {
		long h = t.getHour();
		long m = t.getMinute();
		long s = t.getSecond();
		return (h * 3600) + (m * 60) + s;
	}

	public static String getReferenceDate(String startStrDate, String endStrDate) {
		String meanStrPeriod = "";
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

		if (!startStrDate.equals("")) {
			if (endStrDate.equals("")) {
				meanStrPeriod = startStrDate;
			} else {
				// sredata na perioda
				LocalDateTime locStartDate = null;
				LocalDateTime locEndDate = null;
				LocalDateTime refDate = null;
				try {
					locStartDate = LocalDateTime.parse(startStrDate, sdf);
					locEndDate = LocalDateTime.parse(endStrDate, sdf);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return meanStrPeriod = "Некоректни дати";

				}
				Duration duration = Duration.between(locStartDate, locEndDate);

				long startDateInSec = getSecondDuration(locStartDate);
				long refTimeInSec = duration.getSeconds() / 2;
				long qw = startDateInSec + refTimeInSec;
				refDate = locStartDate.plusSeconds(refTimeInSec);
				if (refTimeInSec < 0) {
					meanStrPeriod = "Некоректен период";
				} else
					meanStrPeriod = refDate.format(sdf);

			}
		} else {
			meanStrPeriod = "";
		}

		return meanStrPeriod;
	}

	public static Boolean incorrectDate(String date, Boolean inTime) {
		Boolean corDate = false;
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String origDate = date;
		if (inTime) {
			sdf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
			try {
				origDate = date.substring(0, 10);
			} catch (StringIndexOutOfBoundsException e) {

				JOptionPane.showMessageDialog(null, "Грешна дата!", "Грешни данни", JOptionPane.ERROR_MESSAGE);

			}

		}
		DateTimeFormatter sdf1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		try {

			LocalDate data_time = LocalDate.parse(date, sdf);
			if (!data_time.format(sdf1).equals(origDate))
				return true;
			System.out.println("Data = " + data_time.format(sdf1) + " originData = " + origDate);

		} catch (DateTimeParseException e) {
			System.out.println(" EroriginData = " + origDate);
			return corDate = true;
		}
		return corDate;
	}

}
