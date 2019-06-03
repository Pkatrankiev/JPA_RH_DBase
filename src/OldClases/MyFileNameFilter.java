package OldClases;

import java.io.File;
import java.io.FilenameFilter;

public class MyFileNameFilter implements FilenameFilter {

  

    	private String name;

		public MyFileNameFilter(String name) {
		// TODO Auto-generated constructor stub
	}

		@Override
    	public boolean accept(File arg0, String arg1) {
    	    // TODO Auto-generated method stub
    	    boolean result =false;
    	    if(arg1.startsWith(name))
    	        result = true;
    	    return result;
    	}
    

}
