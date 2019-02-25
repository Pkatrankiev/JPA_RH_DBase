package WindowView;

import java.util.Comparator;

public class CheckResultClass {
	private Double mda;
	private Double value;
	private int requestCode;
	
	public CheckResultClass ( Double value,	Double mda,	int requestCode) {
		
		super();
		
		this.value = value;
		this.mda = mda;
		this.requestCode = requestCode;
	}
public CheckResultClass ( ) {
		
		super();
}
	public Double getMda() {
		return mda;
	}

	public void setMda(Double mda) {
		this.mda = mda;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public int getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}	
	
	public static Comparator<CheckResultClass> StuNameComparator = new Comparator<CheckResultClass>() {

		public int compare(CheckResultClass s1, CheckResultClass s2) {
		   Integer val1 = s1.getRequestCode();
		   Integer val2 = s2.getRequestCode();

		   return val1.compareTo(val2);

	    }};
}
