package ExcelFilesFunction;

public class Destruct_Result {

	private String metod;
	private String nuclide;
	private String result;
	private String dobiv;
	private String uncert;
	private String mda;
	private String tsi;
	private String quantity;
	private String cod;
	private String dimencion;
	private String date_Analize;
	private String user_Analize;

	public Destruct_Result() {
	}

	public Destruct_Result(String cod, String metod, String nuclide, String result, String dobiv, String uncert, 
			String mda, String tsi, String quantity, String dimencion, String date_Analize, String user_Analize) {
		this.cod = cod;
		this.metod = metod;
		this.nuclide = nuclide;
		this.result = result;
		this.dobiv=dobiv;
		this.uncert = uncert;
		this.mda = mda;
		this.tsi = tsi;
		this.quantity = quantity;
		this.dimencion = dimencion;
		this.date_Analize = date_Analize;
		this.user_Analize = user_Analize;
	}

	

	public String getDobiv() {
		return dobiv;
	}

	public void setDobiv(String dobiv) {
		this.dobiv = dobiv;
	}

	public void setDimencion(String dimencion) {
		this.dimencion = dimencion;
	}

	public String getDimencion() {
		return dimencion;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getMetod() {
		return metod;
	}

	public void setMetod(String metod) {
		this.metod = metod;
	}

	public String getNuclide() {
		return nuclide;
	}

	public void setNuclide(String nuclide) {
		this.nuclide = nuclide;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUncert() {
		return uncert;
	}

	public void setUncert(String uncert) {
		this.uncert = uncert;
	}

	public String getMda() {
		return mda;
	}

	public void setMda(String mda) {
		this.mda = mda;
	}

	public String getTsi() {
		return tsi;
	}

	public void setTsi(String tsi) {
		this.tsi = tsi;
	}

	public String getUser_Analize() {
		return user_Analize;
	}

	public void setUser_Analize(String user_Analize) {
		this.user_Analize = user_Analize;
	}

	public String getDate_Analize() {
		return date_Analize;
	}

	public void setDate_Analize(String date_Analize) {
		this.date_Analize = date_Analize;
	}

}