package Table;

public class TableObject_Class {
	private int numberColum;
	private String columName_Header;
	private Class<?> classColumn;
	private String tipeColumn;
	private String[] masiveValueForChoice;


	public Class<?> getClassColumn() {
		return classColumn;
	}

	public void setClassColumn(Class<?> classColumn) {
		this.classColumn = classColumn;
	}

	public String getColumName_Header() {
		return columName_Header;
	}

	public void setColumName_Header(String columName_Header) {
		this.columName_Header = columName_Header;
	}

	public String[] getMasiveValueForChoice() {
		return masiveValueForChoice;
	}

	public void setMasiveValueForChoice(String[] masiveValueForChoice) {
		this.masiveValueForChoice = masiveValueForChoice;
	}

	public String getTipeColumn() {
		return tipeColumn;
	}

	public void setTipeColumn(String tipeColumn) {
		this.tipeColumn = tipeColumn;
	}

	public int getNumberColum() {
		return numberColum;
	}

	public void setNumberColum(int numberColum) {
		this.numberColum = numberColum;
	}
	
	
}
