package Reference;

import java.text.DecimalFormat;

public class FormatDoubleNumber {
	
	
public static Double formatStringToDouble(String number, int crease) {
	DecimalFormat formatter = getExponentFormaterToCrease( crease);
	double db = Double.parseDouble(number);
	System.out.println("double db "+db);
	String fnumber = formatter.format(db);
	System.out.println("String fnumber "+fnumber);
	if (fnumber.contains("E")) {
		number = getExponentSubstringToCrease(fnumber, crease);
		System.out.println("number = "+number);
	}else{
	number = getSubstringToCrease(fnumber, crease);
	}
	db = Double.parseDouble(number);
	
		return db;
	}

public static String formatDoubleToString(Double number, int crease, boolean exponent) {
	
	DecimalFormat formatter = getExponentFormaterToCrease(crease);
	String fnumber = formatter.format(number);
	if (!fnumber.contains("E-")) {
	 formatter = getFormaterToCrease(crease);
	}
	if(exponent){
		formatter = getExponentFormaterToCrease(crease);
	}else{
		 formatter = getFormaterToCrease(crease);
	}
	String str = formatter.format(number).replace(",", ".").replace("E0", "E+0");
	
		return str;
	}

private static DecimalFormat getFormaterToCrease(int crease) {
	DecimalFormat formatter ;
	switch (crease) {
	case 0:
		formatter = new DecimalFormat("0");
		break;
	case 1:
		formatter = new DecimalFormat("0.0");
		break;
	case 2:
		formatter = new DecimalFormat("0.00");
		break;
	case 3:
		formatter = new DecimalFormat("0.000");
		break;
	default:
		formatter = new DecimalFormat("0.0000");
		break;
	}
	
	return formatter;
}
private static DecimalFormat getExponentFormaterToCrease(int crease) {
	DecimalFormat formatter ;
			
	switch (crease) {
	case 0:
		formatter = new DecimalFormat("0E00");
		break;
	case 1:
		formatter = new DecimalFormat("0.0E00");
		break;
	case 2:
		formatter = new DecimalFormat("0.00E00");
		break;
	case 3:
		formatter =  new DecimalFormat("0.000E00");
		break;
	case 4:
		formatter =  new DecimalFormat("0.0000E00");
		break;
	default:
		formatter = new DecimalFormat("0.00000E00");
		break;
	}
	
	return formatter;
}

private static String getSubstringToCrease(String number, int crease) {
	String str  = number;
	number = number.replace(",", ".");
	int beginIndex = number.indexOf(".");
	if(beginIndex>0){
	number = number +"00000";
	switch (crease) {
	case 0:
//		#.0
		str = number.substring(0,beginIndex+2);
		break;
	case 1:
//		#.00
		str = number.substring(0,beginIndex+3);
		break;
	case 2:
//		#.000
		str = number.substring(0,beginIndex+4);
		break;
	case 3:
//		#.0000
		str = number.substring(0,beginIndex+5);
		break;
	

	default:
//		#.00000
		str = number.substring(0,beginIndex+6);
		break;
	}
	}
	return str;
}

private static String getExponentSubstringToCrease(String numberWithExp, int crease) {
	String str  = numberWithExp;
	System.out.println("numberWithExp  "+numberWithExp );
	numberWithExp = numberWithExp.replace(",", ".").replace("Å", "E");
	int beginIndex = numberWithExp.indexOf(".");
	int exponIndex = numberWithExp.indexOf("E")+1;
	System.out.println("exponIndex  "+exponIndex );
	if(beginIndex>0){
	switch (crease) {
	case 0:
//		#.0 E00
		str = numberWithExp.substring(0,beginIndex+2)+
				numberWithExp.substring(exponIndex);
		break;
	case 1:
//		#.00 E00
		str = numberWithExp.substring(0,beginIndex+3)+
				numberWithExp.substring(exponIndex);
		break;
	case 2:
//		#.000
		str = numberWithExp.substring(0,beginIndex+4)+
				numberWithExp.substring(exponIndex);
		break;
	case 3:
//		#.0000
		str = numberWithExp.substring(0,beginIndex+5)+
				numberWithExp.substring(exponIndex);
		break;
	

	default:
//		#.00000
		str = numberWithExp.substring(0,beginIndex+6)+
				numberWithExp.substring(exponIndex);
		break;
	}
	}
	return str;
}
}
