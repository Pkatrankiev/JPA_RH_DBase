package OldClases;

import java.awt.Frame;

import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.AttributedString;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.CallableStatement;

import AddResultViewFunction.AddResultViewMetods;
import Aplication.DobivDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.NuclideDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.RequestDAO;
import Aplication.Request_To_ObektNaIzpitvaneRequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import Aplication.TSI_DAO;
import Aplication.UsersDAO;
import DBase_Class.Dobiv;
import DBase_Class.DopalnIziskv;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Period;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.TSI;
import DBase_Class.Users;
import DefaultTableList.TableList_OverallVariables;
import DefaultTableList.TableObject_Class;
import DefaultTableList.ViewTableList;
import GlobalVariable.GlobalVariableForSQL_DBase;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import Reference.Reference;
import Table.Table_Sample_List;
import Table_Request.Table_RequestToObektNaIzp;
import Table_Results.DialogView_DobivFromResultTableList;
import WindowView.AddDobivView;
import WindowView.AddResultsView;
import WindowView.ChoiceFromListWithPlusAndMinus;
import WindowView.DateChoice_period;
import WindowView.FrameChoiceRequestByCode;
import WindowView.MainWindow;
import WindowView.RequestView;
import WindowView.TranscluentWindow;

public class TestClases {

	@SuppressWarnings("unused")
	private static void test() {
		TranscluentWindow round = new TranscluentWindow();
		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				int m = 0;
				for (int i = 0; i < 900000000; i++) {
					for (int k = 0; k < 900000000; k++) {
						m++;
					}
				}
			}
		});
		thread.start();
		round.StopWindow();
		// System.out.println(value);
	}

	public static void testTableSampleList() {
		JButton loginMenu = new JButton();
		Frame win = new Frame();
		MainWindow_Old.StartLoginMenu(win, loginMenu);
		TranscluentWindow round = new TranscluentWindow();

		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				JFrame f = new JFrame();
				new Table_Sample_List(f, round, null);
			}
		});
		thread.start();
	}

	public static void setDataTime_Referense(String str_date_period_reception) {
		final JFrame f = new JFrame();
		Boolean forDateReception = false;
		Boolean withTime = true;
		Boolean fromTable = true;
		
		DateChoice_period date_time_reference = new DateChoice_period(f, str_date_period_reception,
				withTime, forDateReception,	fromTable);
		date_time_reference.setVisible(true);
		System.out.println( DateChoice_period.get_date_time_reference());
	}
	
	public static void testSetText_Ob_na_Izp_Request() {
		List<Request> list_Requestv = RequestDAO.getInListAllValueRequest();
		for (Request request : list_Requestv) {
			String str_Ob_na_Izp = Table_RequestToObektNaIzp.createStringListObektNaIzp(
					Table_RequestToObektNaIzp.getListStringOfRequest_To_ObektNaIzpitvaneRequest(request), false);
			request.setText_obekt_na_izpitvane_request(str_Ob_na_Izp);
			RequestDAO.updateObjectRequest(request);
		}
	}

	public static void testAddDobivView() {
		TranscluentWindow round = new TranscluentWindow();

		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				JFrame f = new JFrame();
				new AddDobivView(f, round, UsersDAO.getValueUsersById(3));

			}
		});
		thread.start();
	}

	public static void testReferenceView() {
		TranscluentWindow round = new TranscluentWindow();

		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				JFrame f = new JFrame();
				new Reference(f, round);

			}
		});
		thread.start();
	}

	
	
	public static void test3() {
		List<String> list = new ArrayList<String>();
		String strObektIzpit = "Спецкорпус-1; Бак 4 и 5 (Изход 2)";
		String str = "";
		strObektIzpit = strObektIzpit.replaceAll("\\(", "#<").replaceAll("\\)", "#>");

		while (!strObektIzpit.isEmpty()) {
			if (strObektIzpit.indexOf(";") >= 0) {
				str = strObektIzpit.substring(0, strObektIzpit.indexOf(";") + 1);
			} else {
				str = strObektIzpit;
			}
			list.add(str.replaceAll(";", "").replaceAll("#<", "\\(").replaceAll("#>", "\\)").trim());
			strObektIzpit = strObektIzpit.replaceFirst(str, "");
		}
		// String [] masive = new String[list.size()];
		int i = 0;
		for (String strList : list) {
			System.out.println(i + "-" + strList);
			i++;
		}

	}

	public static void start_Table_List_Test(int i) {
		TranscluentWindow round = new TranscluentWindow();
		Users user = UsersDAO.getValueUsersById(i);
		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				JFrame f = new JFrame();
				new ViewTableList(f, round, user, "Results", "Списък на Резултатите", true, null);

			}
		});
		thread.start();
	}

	public static Boolean CompareDoubleUnits(Double dobivValue1, Double dobivValue2) {
		dobivValue1 = 99.999858999;
		dobivValue2 = 99.9998;

		// Double dobiveValueResult = reformatDoubleValue
		// (dobiv.getValue_result());
		int intdobivValue1 = (int) (AddResultViewMetods.reformatDoubleValue(dobivValue1) * 100000);
		int intdobivValue2 = (int) (AddResultViewMetods.reformatDoubleValue(dobivValue2) * 100000);
		int dobiveValuePlus = (int) ((AddResultViewMetods.reformatDoubleValue(dobivValue1 + 0.00005)) * 100000);
		int dobiveValueMinus = (int) ((AddResultViewMetods.reformatDoubleValue(dobivValue1 - 0.00005)) * 100000);

		System.out.println(intdobivValue1 + " " + intdobivValue2 + " " + dobiveValuePlus + " " + dobiveValueMinus);

		return (dobiveValuePlus >= intdobivValue2 && dobiveValueMinus <= intdobivValue2);

	}

	public static void test2() {
		TranscluentWindow round = new TranscluentWindow();
		Callable<Integer> worker = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				int m = metodTest();

				return m;
			}

		};

		Future<Integer> result = Executors.newSingleThreadExecutor().submit(worker);
		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		round.StopWindow();
	}

	private static int metodTest() throws InterruptedException {
		int m = 0;

		for (int k = 0; k < 900; k++) {
			Thread.sleep(10);
			m++;
		}
		return m;
	}

	@SuppressWarnings("unused")
	private static void ChoiceListIzpPokazatel() {
		JFrame f = new JFrame();
		List<String> bsic_list = Obekt_na_izpitvane_requestDAO.getListStringAllValueObekt_na_izpitvane();
		String label = "Изберете Обект на изпитване";
		new ChoiceFromListWithPlusAndMinus(f, null, bsic_list, label);
	}

	public static void testNewRequestVew() {
		JFrame f = new JFrame();
		Users user = UsersDAO.getValueUsersById(3);
		Request request = RequestDAO.getRequestFromColumnByVolume("recuest_code", "4069");
		TranscluentWindow round = new TranscluentWindow();

		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				new RequestView(f, user, request, round, true);
			}
		});
		thread.start();
	}

	@SuppressWarnings("unused")
	private static void updateUserRedac() {
		Users user = UsersDAO.getValueUsersById(3);
		List<Results> list = ResultsDAO.getInListAllValueResults();
		for (Results dobiv : list) {
			dobiv.setUser_redac(user);
			ResultsDAO.updateResults(dobiv);
		}
	}

	public static void AddResultsViewWithTable(int i) {
		TranscluentWindow round = new TranscluentWindow();

		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				JFrame f = new JFrame();
				// new AddDobivView(f,round, UsersDAO.getValueUsersById(3));
				new AddResultsView(f, round, UsersDAO.getValueUsersById(i));

				// new OldClases.AddResultsViewWithTable(f,round,
				// UsersDAO.getValueUsersById(3));

			}
		});
		thread.start();

	}

	public static void Table_Results_List_Test(int i, String table_Taipe) {
		TranscluentWindow round = new TranscluentWindow();

		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				JFrame f = new JFrame();
				// new AddDobivView(f,round, UsersDAO.getValueUsersById(3));

				// new Table_Request_List_Test2(f, round,
				// UsersDAO.getValueUsersById(i), "request",
				// ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("EnableRequestList_TitleName"),
				// true);

				// new Table.Table_Request_List(f, round,
				// UsersDAO.getValueUsersById(i), "request",
				// ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("EnableRequestList_TitleName"));

				new ViewTableList(f, round, UsersDAO.getValueUsersById(i), table_Taipe,
						ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("EnableResultsList_TitleName"),
						true, null);

			}
		});
		thread.start();

	}

	public static void StartMainWindow_Test() {
		TranscluentWindow round = new TranscluentWindow();

		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				new MainWindow(round);

			}
		});
		thread.start();

	}

	public static void DialogView_DobivFromResultTableList_test(int i) {
		Dobiv selectDobiv = DobivDAO.getDobivById(i);

		List<Dobiv> listNameDobivs = DobivDAO.getListDobivByNuclide(selectDobiv.getNuclide());
		DialogView_DobivFromResultTableList dobivFromResultTableList = new DialogView_DobivFromResultTableList(
				new JFrame(), listNameDobivs, selectDobiv);
		System.out.println(dobivFromResultTableList.getSelectDobiv().getId_dobiv());
	}

	public static void Change_In_Dobiv_Nuclide() {

		List<Dobiv> listNameDobivs = DobivDAO.getListDobivByNuclide(NuclideDAO.getValueSNuclideById(75));
		for (Dobiv dobiv : listNameDobivs) {
			String code = dobiv.getCode_Standart().replaceAll("[^\\d]", "").replaceAll("-", "").trim();
			// System.out.println(code);
			int kod = Integer.valueOf(code.substring(0, 4));
			if (kod > 4220) {
				System.out.println(kod);
				dobiv.setNuclide(NuclideDAO.getValueSNuclideById(55));
				DobivDAO.updateDobiv(dobiv);
			}
		}
	}

	public static void Table_RequestToObektNaIzp() {
		TranscluentWindow round = new TranscluentWindow();

		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				JFrame f = new JFrame();
				// new AddDobivViewWithTable(f,round,
				// UsersDAO.getValueUsersById(3));
				new Table_RequestToObektNaIzp(f, round, UsersDAO.getValueUsersById(4));
				// new AddResultsViewWithTable_Test(f,round,
				// UsersDAO.getValueUsersById(3));
			}
		});
		thread.start();

	}

	public static void startCreateProtokolDocx() {
		JFrame f = new JFrame();
		new FrameChoiceRequestByCode(f, "Генериране на Протокол");
		// String codeRequest = "3833";
		//
		// Request choiseRequest =
		// RequestDAO.getRequestFromColumnByVolume("recuest_code", codeRequest);
		// String date_time_reference =
		// RequestViewFunction.GenerateStringRefDateTimeFromRequest(choiseRequest);
		//
		// Map<String, String> substitutionData =
		// Generate_Map_For_Request_Word_Document.GenerateMapForRequestWordDocument(
		// choiseRequest,
		// RequestViewFunction.generateStringListIzpitvanPokazatelFromrequest(choiseRequest),
		// RequestViewFunction.generateMasiveSampleDescriptionFromRequest(choiseRequest),
		// date_time_reference);
		// TestSuperScript.GenerateProtokolWordDoc("test.docx");
		// StartGenerateDocTemplate.GenerateProtokolWordDoc("Protokol.docx",
		// choiseRequest, substitutionData);
	}

	@SuppressWarnings("unused")
	private static void testScript() {
		String as = "asd4 5/78Ajj";

		JOptionPane.showMessageDialog(null,
				"Грешни:" + superscript1(as) + " " + superscript2(as) + " " + superscript3(as) + " ");
	}

	public static String superscript1(String str) {

		AttributedString as = new AttributedString(str);

		as.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, 1, 5);

		// Font font = new Font("Tahoma", Font.PLAIN, 11);
		// Map<TextAttribute,?> attributes = font.getAttributes();
		// Map<TextAttribute,Object> newAttributes = new
		// HashMap<TextAttribute,Object>(attributes);
		// newAttributes.put(TextAttribute.SUPERSCRIPT,
		// TextAttribute.SUPERSCRIPT_SUPER);
		// font = font.deriveFont(newAttributes);
		//
		// str = str.replaceAll("0", "⁰");
		// str = str.replaceAll("1", "¹");
		// str = str.replaceAll("2", "²");
		// str = str.replaceAll("3", "³");
		// str = str.replaceAll("4", "⁴");
		// str = str.replaceAll("5", "⁵");
		// str = str.replaceAll("6", "⁶");
		// str = str.replaceAll("7", "⁷");
		// str = str.replaceAll("8", "⁸");
		// str = str.replaceAll("9", "⁹");
		// str = str.replaceAll("/", "⸍");
		return str;
	}

	public static String superscript2(String str) {
		str = str.replaceAll("0", "⁰");
		str = str.replaceAll("1", "¹");
		str = str.replaceAll("2", "²");
		str = str.replaceAll("3", "³");
		str = str.replaceAll("4", "⁴");
		str = str.replaceAll("5", "⁵");
		str = str.replaceAll("6", "⁶");
		str = str.replaceAll("7", "⁷");
		str = str.replaceAll("8", "⁸");
		str = str.replaceAll("9", "⁹");
		str = str.replaceAll("/", "⸍");
		return str;
	}

	public static String superscript3(String str) {
		str = str.replaceAll("0", "⁰");
		str = str.replaceAll("1", "¹");
		str = str.replaceAll("2", "²");
		str = str.replaceAll("3", "³");
		str = str.replaceAll("4", "⁴");
		str = str.replaceAll("5", "⁵");
		str = str.replaceAll("6", "⁶");
		str = str.replaceAll("7", "⁷");
		str = str.replaceAll("8", "⁸");
		str = str.replaceAll("9", "⁹");
		str = str.replaceAll("/", "⸍");
		return str;
	}

	public static void setAllValueToRequestToObektNaIzpit() {
		List<Request> list = RequestDAO.getInListAllValueRequest();
		for (Request request : list) {
			Request_To_ObektNaIzpitvaneRequestDAO.setValueRequest_To_ObektNaIzpitvaneRequest(request,
					request.getObekt_na_izpitvane_request());
		}
	}

	@SuppressWarnings("unused")
	private static void TestIzpitvanPokazatelClass() {
		List<IzpitvanPokazatel> list = IzpitvanPokazatelDAO.getInListAllValueIzpitvan_pokazatel();
		for (IzpitvanPokazatel izpitvanPokazatel : list) {
			System.out.println(izpitvanPokazatel.getMetody().getName_metody() + " / "
					+ izpitvanPokazatel.getPokazatel().getName_pokazatel());
		}
	}

	@SuppressWarnings("unused")
	private static void ChangeObjectsInClass() {

		List<Results> listResult = ResultsDAO.getInListAllValueResults();
		TSI obTSI = TSI_DAO.getValueTSIById(9);

		for (Results object : listResult) {
			object.setTsi(obTSI);
			ResultsDAO.updateResults(object);
		}

	}

	public static String ChangeStringToNumber(String num) {

		String head = num.substring(0, num.indexOf("."));
		System.out.println("head= " + head);
		if (Integer.parseInt(head) == 0) {
			head = "0.";
			String body = num.substring(num.indexOf(".") + 1);
			while (body.substring(0, 1).equals("0")) {
				head = head + "0";
				body = body.substring(1);
			}
			System.out.println("head= " + head);
			System.out.println("body= " + body);
			System.out.println("-------------------------------");

			String olt;
			int bodyInt = 0;
			double dob2 = 0;
			if (body.length() > 5) {
				body = body.substring(0, 5);
				System.out.println("body= " + body);
				bodyInt = Integer.parseInt(body);
				olt = "0." + body.substring(4);
				System.out.println("olt= " + olt);
				dob2 = Double.parseDouble(olt);

				if (dob2 + 0.5 >= 1) {
					bodyInt++;
				}
			}
			System.out.println("bodyInt= " + bodyInt);
			System.out.println("-------------------------------");

			num = head + bodyInt;
			System.out.println("num= " + num);
			System.out.println("-------------------------------");

			System.out.println(num + "  num.indexOf(body)= " + num.indexOf(body));
			num = num.substring(0, num.indexOf(body) + body.length());
		}
		System.out.println("num= " + num);
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(4);
		Double boubVal = Double.parseDouble(num);
		System.out.println(num + " -> " + nf.format(boubVal));
		//
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.HALF_UP);

		String a = df.format(boubVal);
		System.out.println(a);
		//
		double roundOff = Math.round(boubVal * 10000.0000) / 10000.0000;
		System.out.println(roundOff);
		System.out.println(num);

		return num;
	}

	public static void creatInDBaseRequestInResultsClass() {
		List<Results> listAllResults = ResultsDAO.getInListAllValueResults();
		for (Results results : listAllResults) {
			if (results.getRequest() == null) {
				// System.out.println(results.getSample().);

				Request request = results.getSample().getRequest();
				results.setRequest(request);
				ResultsDAO.updateResults(results);
			}
		}
	}

	public static void TestIterator() {
		
		@SuppressWarnings("rawtypes")
		List<Integer> listPeriod = new ArrayList(Arrays.asList(1,2,3,4,5,6));
		List<Integer> kperiod = new ArrayList(Arrays.asList(1,2,3,4,5,6,7));
		
	for (int period : listPeriod) {
		
		for (Iterator<Integer> it = kperiod.iterator(); it.hasNext();) {
		    int sample = it.next();
		    System.out.println(period+"  "+sample);
		    if(period==sample){
		        it.remove();
		    }
		}
	
	}
	}
	
	
	public static String NumberToMAXDigitAftrerZerro(String num) {
		int MAXDigit = 4;

		String head = num.substring(0, num.indexOf("."));
		if (Integer.parseInt(head) == 0) {
			head = "0.";
			String body = num.substring(num.indexOf(".") + 1);

			while (body.substring(0, 1).equals("0")) {
				head = head + "0";
				body = body.substring(1);
			}

			if (body.length() >= MAXDigit) {
				String olt;
				int bodyInt = 0;
				double dob2 = 0;
				body = body.substring(0, 5);
				bodyInt = Integer.parseInt(body);
				olt = "0." + body.substring(MAXDigit - 1);
				dob2 = Double.parseDouble(olt);

				if (dob2 + 0.5 >= 1) {
					bodyInt++;
				}

				num = head + bodyInt;
			}

		}

		return num;
	}

	public static String NumberFormatWithRounding(String num) {
		num = "Pu-325";
		;
		System.out.println(num.replaceAll("\\D+", "") + "-------------------------------");

		String numm1 = num;

		double dob2 = Double.parseDouble(num);

		DecimalFormat dfA1 = new DecimalFormat("0.0000E00");
		dfA1.setRoundingMode(RoundingMode.HALF_UP);
		String numA1 = dfA1.format(dob2);
		System.out.println("numA1 =" + numA1);
		System.out.println("-------------------------------");

		Double boubVal2 = Double.parseDouble(num);
		System.out.println(boubVal2);
		if (Double.parseDouble(num) == 0) {
			return num;
		}

		String formatNum;
		String head = num.substring(0, num.indexOf("."));
		if (Integer.parseInt(head) == 0) {
			String body = num.substring(num.indexOf(".") + 1);
			while (body.substring(0, 1).equals("0")) {
				body = body.substring(1);
			}
			if (body.length() > 5) {
				body = body.substring(0, 5);
			}
			num = num.substring(0, num.indexOf(body) + body.length());
			formatNum = num;
		} else {
			Double boubVal = Double.parseDouble(num);
			DecimalFormat df = new DecimalFormat("#.##############");
			df.setRoundingMode(RoundingMode.HALF_UP);
			formatNum = df.format(boubVal);

		}
		formatNum = formatNum.replaceAll(",", ".");

		ReformatDoubleTo4decimalExponet(formatNum);
		System.out.println("-------------------------------");
		ChangeStringToNumber(numm1);

		return formatNum.replaceAll(",", ".");
	}

	public static String ReformatDoubleTo4decimalExponet(String formatNum) {
		String stt = "";
		double dob2 = Double.parseDouble(formatNum);
		DecimalFormat df = new DecimalFormat("0.0000E00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		stt = df.format(dob2).replaceAll(",", ".");

		String expon = stt.substring(stt.indexOf("E") + 1);
		int kk = Integer.parseInt(expon);
		if (kk >= -4 && kk <= 0) {
			stt = NumberToMAXDigitAftrerZerro(formatNum);
		}

		if (kk > 1 && kk <= 4) {
			DecimalFormat df4 = new DecimalFormat("#.####");
			df4.setRoundingMode(RoundingMode.HALF_UP);
			stt = df4.format(dob2).replaceAll(",", ".");
		}
		System.out.println(stt);
		return stt;
	}


	public static void createProtocolWordDoc(String str) {
		JFrame f = new JFrame();
		new FrameChoiceRequestByCode(f, str);
	}

	

	public static boolean tbBackup() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.forLanguageTag("bg"));
		java.util.Date currentDate = new java.util.Date();
		Process p = null;

		Runtime runtime = Runtime.getRuntime();
		String bDump = "C:\\MySQL\\bin\\mysqldump.exe  --default-character-set=utf8 -uroot -p123 -c  -B shch2 -r "
				+ "D:/" + dateFormat.format(currentDate) + "_backup" + ".sql";
		// String executeCmd = LOCATION+" -u " + DBUSER + " --add-drop-database
		// -B " + DBNAME + " -r " + PATH + ""+FILENAME
		// Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);

		String executeCmd = "c:\\xampp\\mysql\\bin\\mysqldump.exe -u root -proot --add-drop-database -B rhdbase -r D:\\000\\1234.sql";
		String[] restoreCmd = new String[] { "c:\\xampp\\mysql\\bin\\mysql.exe ", "--user=root", "--password=root",
				"-e", "source " + "D:\\000\\1233.sql" };
		// String[] executeCmd = new
		// String[]{"c:\\xampp\\mysql\\bin\\mysqldump.exe ", " -u root", "
		// -proot",
		// "--add-drop-database", " -B rhdbase" + " -r D:\\000\\1238.sql"};

		String[] comm = new String[5];
		comm[0] = "c:\\xampp\\mysql\\bin\\mysqldump.exe";
		comm[1] = " -u root";
		comm[2] = " -proot";
		comm[3] = " rhdbase";
		comm[4] = " -r D:\\000\\1232.sql";

		// String executeCmd = "mysqldump -u " + dbUserName + " -p" + dbPassword
		// + " --add-drop-database -B " + dbName + " -r " + path;
		Process runtimeProcess;
		try {
			System.out.println(executeCmd);// this out put works in mysql shell
			runtimeProcess = Runtime.getRuntime().exec(restoreCmd);

			int processComplete = runtimeProcess.waitFor();

			if (processComplete == 0) {
				System.out.println("Backup created successfully");
				return true;
			} else {
				System.out.println("Could not create the backup");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}


	
	
	public static void backupDB_From_RemoteServer() {
		Date backupDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy_HHmm");
		String backupDateStr = format.format(backupDate);

		String PathToMySqlDumpFile = "c:\\xampp\\mysql\\bin\\";
		String HOSTIP = "192.168.21.27";
		String PORT = "3306";
		String USER = "someuser";
		String PASS = "123";
		String database = "rhdbase";
		String path = "l:/Петър/DB_backup_" + backupDateStr + ".sql";

		try {
			String executeCmd = PathToMySqlDumpFile + "mysqldump -h " + HOSTIP + " -P " + PORT + " -u " + USER + " -p"
					+ PASS + " " + database + " -r " + path;
			Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();
			if (processComplete == 0) {
				System.out.println("Backup taken successfully");
			} else {
				System.out.println("Could not take mysql backup");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public static void restoreDB_From_RemoteServer() {
		
		String PathToMySqlDumpFile = "c:\\xampp\\mysql\\bin\\";
		String HOSTIP = "192.168.21.27";
		String PORT = "3306";
		String USER = "someuser";
		String PASS = "123";
		String database = "rhdbase";
		String path = "l:/Петър/";
//		String fileName = "DB_backup_" + backupDateStr + ".sql";

		try {
			String executeCmd = PathToMySqlDumpFile + "mysqldump -h " + HOSTIP + " -P " + PORT + " -u " + USER + " -p"
					+ PASS + " " + database + " -r " + path;
			String restoreCmd = PathToMySqlDumpFile + "mysql -h " + HOSTIP + " -P " + PORT + " -u " + USER + " -p"
					+ PASS + "-e " + path+"DB_backup_02-09-20_1418.sql" ;	
			
			Process runtimeProcess = Runtime.getRuntime().exec(restoreCmd);
			int processComplete = runtimeProcess.waitFor();
			if (processComplete == 0) {
				System.out.println("Backup taken successfully");
			} else {
				System.out.println("Could not take mysql backup");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	public static void createRazprFormWordDoc() {
		JFrame f = new JFrame();
		new FrameChoiceRequestByCode(f, "Генериране на Разпределителен формуляр");
	}
}
