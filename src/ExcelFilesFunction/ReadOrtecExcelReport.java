package ExcelFilesFunction;


public class ReadOrtecExcelReport {
	private static String cod_sample;
//	private static String user_Analize="";
	
	public static String getCod_sample() {
		return cod_sample;
	}


	public static String getStringDestruct_Result(Destruct_Result destruct_Result) {

		return destruct_Result.getResult() + " ; " + destruct_Result.getMda() + " ;  " + destruct_Result.getMetod()
				+ "  ; " + destruct_Result.getNuclide() + " ;  " + destruct_Result.getTsi() + " ;  "
				+ destruct_Result.getUncert();
	}

	
	
}
