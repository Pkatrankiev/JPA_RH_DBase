package OldClases;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class RemouveDublikateFromList {
	
	 public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
	    {
	  
	        // Create a new LinkedHashSet
	        Set<T> set = new LinkedHashSet<>();
	  
	        // Add the elements to set
	        set.addAll(list);
	  
	        // Clear the list
	        list.clear();
	  
	        // add the elements of set
	        // with no duplicates to the list
	        list.addAll(set);
	  
	        // return the list
	        return list;
	    }
}
