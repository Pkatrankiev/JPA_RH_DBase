package ExcelFilesFunction;

public class Destruct_Result {

	private String metod;
	private String nuclide;
	private String result;
	private String uncert;
	private String mda;
	private String tsi;
	private String quantity;
	private String cod;
	private String dimencion;

	public Destruct_Result() {
	}

	public Destruct_Result(String cod, String metod, String nuclide, String result, String uncert, String mda, String tsi, String quantity, String dimencion) {
		this.cod = cod;
		this.metod = metod;
		this.nuclide = nuclide;
		this.result = result;
		this.uncert = uncert;
		this.mda = mda;
		this.tsi = tsi;
		this.quantity = quantity;
		this.dimencion = dimencion;
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

}