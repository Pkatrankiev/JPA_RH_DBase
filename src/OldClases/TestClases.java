package OldClases;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.math.RoundingMode;
import java.text.AttributedString;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.hpsf.Decimal;

import AddResultViewFunction.AddResultViewMetods;
import Aplication.DobivDAO;
import Aplication.EjectionDAO;
import Aplication.EmitionDAO;
import Aplication.Ind_num_docDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.MetodyDAO;
import Aplication.NuclideDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.PeriodDAO;
import Aplication.RequestDAO;
import Aplication.Request_To_ObektNaIzpitvaneRequestDAO;
import Aplication.ResultsDAO;
import Aplication.TSI_DAO;
import Aplication.UsersDAO;
import CreateWordDocProtocol.FunctionForGenerateWordDocFile;
import DBase_Class.Dobiv;
import DBase_Class.Emition;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.Metody;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Period;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.TSI;
import DBase_Class.Users;
import DefaultTableList.ViewTableList;
import GlobalVariable.GlobalFormatDate;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import Reference.MounthlyReferenceForCNRDWater;
import Reference.MounthlyReferenceForMenuEjectionVolums;
import Reference.PeriodicReference;
import Table.Table_Sample_List;
import TableBeisicClassDBase.ViewTableBeisicClassDBase;
import Table_Request.Table_RequestToObektNaIzp;
import Table_Results.DialogView_DobivFromResultTableList;
import WindowView.AddDobivView;
import WindowView.AddResultsView;
import WindowView.ChoiceFromListWithPlusAndMinus;
import WindowView.DateChoice_period;
import WindowView.FrameChoiceRequestByCode;
import WindowView.MainWindow;
import WindowView.RequestMiniFrame;
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

		DateChoice_period date_time_reference = new DateChoice_period(f, "", str_date_period_reception, withTime,
				forDateReception, fromTable);
		date_time_reference.setVisible(true);
		System.out.println(DateChoice_period.get_date_time_reference());
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

	public static void testSetNewSimbolInDescriptionSampleGrupe_Request(String str) {
		
			
			String one = "";
			String tue = "";
			String new_str = "";
			boolean fl = true;
			if(!str.isEmpty()||str.indexOf("˲")<0){
			int i = str.indexOf("за ");
			if(i>0){
				one = str.substring(0, i).trim();
				tue = str.substring(i).trim();
				new_str = one+"˲ "+tue;
				fl=false;
			}
			i = str.indexOf("м. ");
			if(fl && i>0){
				one = str.substring(0, i).trim();
				tue = str.substring(i).trim();
				new_str = one+"˲ "+tue;
				fl=false;
			}
			
			i = str.indexOf("за период");
			if(i>0){
				one = str.substring(0, i).trim();
				tue = str.substring(i).trim();
				new_str = one+"˲ "+tue;
				fl=false;
			}
			i = str.indexOf("период");
			if(fl && i>0){
				one = str.substring(0, i).trim();
				tue = str.substring(i).trim();
				new_str = one+"˲ "+tue;
				fl=false;
			}
			i = str.indexOf("за 3-то");
			if(fl && i>0){
				one = str.substring(0, i).trim();
				tue = str.substring(i).trim();
				new_str = one+"˲ "+tue;
				fl=false;
			}
			i = str.indexOf("за 1-во");
			if(fl && i>0){
				one = str.substring(0, i).trim();
				tue = str.substring(i).trim();
				new_str = one+"˲ "+tue;
				fl=false;
			}
			i = str.indexOf("за 2-ро");
			if(fl && i>0){
				one = str.substring(0, i).trim();
				tue = str.substring(i).trim();
				new_str = one+"˲ "+tue;
				fl=false;
			}
			i = str.indexOf("за 4-то");
			if(fl && i>0){
				one = str.substring(0, i).trim();
				tue = str.substring(i).trim();
				new_str = one+"˲ "+tue;
				fl=false;
			}
			if(fl){
				
				new_str = str.trim()+"˲";
			}
			}
			System.out.println("- "+new_str);
			
		
			
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
				new PeriodicReference(f, round);

			}
		});
		thread.start();
	}

	
	public static void testNewResultFormat(int firstRequest, int numberRequest) {
		List<String> listString = new ArrayList<>();
		
		for (int i = firstRequest; i < firstRequest+numberRequest; i++) {
			Request request = RequestDAO.getListRequestFromColumnByVolume("recuest_code", i+"").get(0);
			List<Results> listResults = ResultsDAO. getListResultsFromColumnByVolume("request",request);
			for (Results results : listResults) {
				double val = results.getValue_result();
				double uncer =  results.getUncertainty();
				if(val!=0){
				String str = FunctionForGenerateWordDocFile.StoinostAndNeopredelenost(val, uncer);
			
				
				listString.add(FunctionForGenerateWordDocFile.formatter(val)+"  "+
						FunctionForGenerateWordDocFile.formatter(uncer)+"  "+str+"  "+FunctionForGenerateWordDocFile.transformEXPto10(str));
			}
			}
		}
		
		for (String string : listString) {
			System.out.println(string);
		}
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
	public
	static void ChoiceListIzpPokazatel() {
		
		
		JFrame f = new JFrame();
//		List<String> bsic_list = Obekt_na_izpitvane_requestDAO.getListStringAllValueObekt_na_izpitvane();
		String label = "Изберете Обект на изпитване";
		new MounthlyReferenceForMenuEjectionVolums(f, label);
	}

	public static void testNewRequestVew() {
		JFrame f = new JFrame();
		Users user = UsersDAO.getValueUsersById(3);
		Request request = RequestDAO.getRequestFromColumnByVolume("recuest_code", "4671");
		TranscluentWindow round = new TranscluentWindow();

		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				
				new RequestView(f, user, request, round, false);
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
				 new AddDobivView(f,round, UsersDAO.getValueUsersById(3));
//				new AddResultsView(f, round, UsersDAO.getValueUsersById(i));

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

	
	public static void MounthlyReferenceForCNRDWater_test() {
		JFrame f = new JFrame();
	new MounthlyReferenceForCNRDWater(f,"");

	}
	
		
	public static void Change_In_Dobiv_Nuclide() {

		List<Dobiv> listNameDobivs = DobivDAO.getListDobivByNuclide(NuclideDAO.getValueNuclideById(75));
		for (Dobiv dobiv : listNameDobivs) {
			String code = dobiv.getCode_Standart().replaceAll("[^\\d]", "").replaceAll("-", "").trim();
			// System.out.println(code);
			int kod = Integer.valueOf(code.substring(0, 4));
			if (kod > 4220) {
				System.out.println(kod);
				dobiv.setNuclide(NuclideDAO.getValueNuclideById(55));
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
	
	public static void Table_BeisicClasses() {
		TranscluentWindow round = new TranscluentWindow();

		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				JFrame f = new JFrame();
				
				Users user = UsersDAO.getValueUsersById(4);
				String titleTable = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("DBSTCN_Ind_num_doc_TableTitle");
				String[] columnNames = Ind_num_docDAO.getCulumnName_Ind_num_doc_ForTable();
				@SuppressWarnings("rawtypes")
				Class[] classTypes = Ind_num_docDAO.getCulumnClass_Ind_num_doc_ForTable();
				Object[][] data_Table = Ind_num_docDAO.getAll_Ind_num_doc_ForTable();
				int[] columnSize = Ind_num_docDAO.getCulumnSize_Ind_num_doc_ForTable();
				String key = "Ind_num_doc";
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				

				
				dim.getWidth();
				new ViewTableBeisicClassDBase(f, round, user, titleTable, columnNames, classTypes, data_Table,	columnSize, key, null);
				
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

	public static void TestRequestMiniFrame(String code) {
		new RequestMiniFrame(new JFrame(), RequestDAO.getRequestFromColumnByVolume("recuest_code", code));
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

		List<Integer> listPeriod = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
		List<Integer> kperiod = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

		for (int period : listPeriod) {

			for (Iterator<Integer> it = kperiod.iterator(); it.hasNext();) {
				int sample = it.next();
				System.out.println(period + "  " + sample);
				if (period == sample) {
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

	public static void changeStringDateMeasurInResults(String oldDate, String korektDate) {
		List<Results> listResults = ResultsDAO.getListResultsFromColumnByVolume("date_measur", oldDate);
		System.out.println(listResults.size());
		for (Results results : listResults) {
			results.setDate_measur(korektDate);
			ResultsDAO.updateResults(results) ;
		}
	}

	@SuppressWarnings({ "null", "unused" })

	public static boolean tbBackup() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.forLanguageTag("bg"));
		java.util.Date currentDate = new java.util.Date();
		Process p = null;
		p.getInputStream();

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
	
	@SuppressWarnings( "unused" )
	public static void testReferenceSample() {
		String start_Date = "01.03.2022";
		String end_Date = "31.03.2022";
		String FORMAT_DATE = GlobalFormatDate.getFORMAT_DATE();
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FORMAT_DATE);

		LocalDate locStartDate = LocalDate.parse(start_Date, sdf);
		LocalDate locEndDate = LocalDate.parse(end_Date, sdf);
		LocalDate localDate = null;
		List<Results> listAllResults = ResultsDAO.getInListAllValueResults();
		List<Sample> listSamle = new ArrayList<>();
		List<Emition> listEmition = EmitionDAO.getListAllEmition();
		System.out.println("0-   " + listAllResults.size());
		// for (Sample sample : listSamle) {
		// List<Results> listResults =
		// ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
		// listAllResults.addAll(listResults);
		// }
		List<List<Request>> listFromListResults = new ArrayList<>();
		for (Emition emition : listEmition) {
			List<Request> listResultsByEmition = new ArrayList<>();
			List<Metody> listMetodyByEmition = MetodyDAO.getList_MetodyByEmitionAndArrangement(emition);
			for (Iterator<Results> it = listAllResults.iterator(); it.hasNext();) {
				Results results = it.next();
				localDate = LocalDate.parse(results.getDate_measur(), sdf);
				if (localDate.isAfter(locStartDate) && localDate.isBefore(locEndDate) && results.getInProtokol()) {
					for (Metody metody : listMetodyByEmition) {
						if (results.getMetody().getId_metody() == metody.getId_metody()) {
							listResultsByEmition.add(results.getRequest());
							it.remove();
						}
					}
				} else {
					it.remove();
				}
			}

			listFromListResults.add(removeDuplicates(listResultsByEmition));
		}

		for (int i = 0; i < listFromListResults.size(); i++) {
			System.out.println(listEmition.get(i).getEmition_name()+" - "+listFromListResults.get(i).size());
			for (Request samp : listFromListResults.get(i)) {
//				System.out	.println(samp.getRecuest_code() + " - " + samp.getCounts_samples()+" - "+samp.get);
			}
		}

	}

	

	public static <T> List<T> removeDuplicates(List<T> list) {
		System.out.println("1   " + list.size());
		// Create a new LinkedHashSet
		Set<T> set = new LinkedHashSet<>();

		// Add the elements to set
		set.addAll(list);

		// Clear the list
		list.clear();

		// add the elements of set
		// with no duplicates to the list
		list.addAll(set);
		System.out.println("2   " + list.size());
		
		// return the list
		return list;
	}
	@SuppressWarnings("unused")
	public static void restoreDB_From_RemoteServer() {

		String PathToMySqlDumpFile = "c:\\xampp\\mysql\\bin\\";
		String HOSTIP = "192.168.21.27";
		String PORT = "3306";
		String USER = "someuser";
		String PASS = "123";
		String database = "rhdbase";
		String path = "l:/Петър/";
		String pathDump = "D:/DB_Backup.sql";
		
		String path1 = "c:\\xampp\\mysql\\bin\\mysqldump";
		String path2 = "c:\\xampp\\mysql\\bin\\mysql";

				String command= "cmd.exe /c "
				                    + "\"\""+path1+"\"  "
				                    + " --user="+USER
				                    + " --password="+PASS
				                    + " --host="+HOSTIP
				                    + " --protocol=tcp "
				                    + " --port="+PORT
				                    + " --default-character-set=utf8 "
				                    + " --single-transaction=TRUE "
				                    + " --routines "
				                    + " --events "
				                    + "\""+database
				                    +"\" "
				                    + ">"
				                    + " \""
				                    + "D:\\DB_Backup.sql"
				                    + "\""
				                    + " \"";

			
				
						
				
				
		
				
				String executeCmd1 = PathToMySqlDumpFile + "mysqldump -h " + HOSTIP + " -P " + PORT + " -u " + USER + " -p"
						+ PASS + " " + database + " -r " + path;
				String restoreCmd = PathToMySqlDumpFile + "mysql -h " + HOSTIP + " -P " + PORT + " -u " + USER + " -p"
						+ PASS + "-e " + path + "DB_backup_02-09-20_1418.sql";
		
		
		try {
			

			
			
			
			Process runtimeProcess = Runtime.getRuntime().exec(command);
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
