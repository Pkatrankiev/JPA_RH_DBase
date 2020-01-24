package AddResultViewFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DBase_Class.Nuclide;
import DBase_Class.Nuclide_to_Pokazatel;
import DBase_Class.Results;

public class SortListObjectByField {
	
	public static 	List<Results> sortListResultsByCodedNuclide(List<?> listResults){
		 Set<Entry<Integer, Integer>> st = createMap(listResults);    
		 List<Results>  newList = new ArrayList<Results>();
	       for (Map.Entry<Integer, Integer> me:st) 
	        newList.add((Results) getResultObjectFromListResultsByID(listResults, me.getKey())); 
         return newList;
	}
	
	private static Set<Entry<Integer, Integer>> createMap(List<?> listResults) {
		Map<Integer, Integer> map = createMapIdResults_MassNumberNuclide( listResults);
		 Map<Integer, Integer> map2 =sortByValue( map);
		
		 Set<Entry<Integer, Integer>> st = map2.entrySet();
		return st;
	}

	private static Map<Integer, Integer> createMapIdResults_MassNumberNuclide(List<?> likstResults){
		 HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Object results : likstResults) {
			
			 map.put(((Results) results).getId_results(), getMassNumberFromNuclideCode(((Results) results).getNuclide().getSymbol_nuclide()));
		}
		return map;
				
	}
	
	private static Object getResultObjectFromListResultsByID(List<?> listResults, int id){
		for (Object results : listResults) {
			if(((Results) results).getId_results()==id){
				return results;
			}
		}
		
		return null;
		
	}
		

	
	
	
	public static 	List<Nuclide> sortListNuclideByCodedNuclide(List<?> listResults){
		 Set<Entry<Integer, Integer>> st = createNuclideMap(listResults);    
		 List<Nuclide>  newList = new ArrayList<Nuclide>();
	       for (Map.Entry<Integer, Integer> me:st) 
	       newList.add((Nuclide) getNuclidelObjectFromListResultsByID(listResults, me.getKey())); 
	   return newList;
	}
	
	private static Set<Entry<Integer, Integer>> createNuclideMap(List<?> listResults) {
		Map<Integer, Integer> map = createMapIdNuclide_MassNumberNuclide( listResults);
		 Map<Integer, Integer> map2 =sortByValue( map);
		
		 Set<Entry<Integer, Integer>> st = map2.entrySet();
		return st;
	}
	
	private static Object getNuclidelObjectFromListResultsByID(List<?> listResults, int id){
		for (Object results : listResults) {
			if(((Nuclide) results).getId_nuclide()==id){
				return results;
			}
		}
		
		return null;
		
	}
	
	private static Map<Integer, Integer> createMapIdNuclide_MassNumberNuclide(List<?> likstResults){
		 HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Object results : likstResults) {
			
			 map.put(((Nuclide) results).getId_nuclide(), getMassNumberFromNuclideCode(((Nuclide) results).getSymbol_nuclide()));
		}
		return map;
				
	}
	
		
	
	
	public static 	List<Nuclide_to_Pokazatel> sortListNuclide_to_PokazatelByCodedNuclide(List<?> listResults){
		Set<Entry<Integer, Integer>> st = createNuclide_to_PokazatelMap(listResults);    
		 List<Nuclide_to_Pokazatel>  newList = new ArrayList<Nuclide_to_Pokazatel>();
	       for (Map.Entry<Integer, Integer> me:st) 
	        newList.add((Nuclide_to_Pokazatel) getNuclide_to_PokazatelObjectFromListResultsByID(listResults, me.getKey())); 
	    return newList;
	}
	
	private static Set<Entry<Integer, Integer>> createNuclide_to_PokazatelMap(List<?> listResults) {
		Map<Integer, Integer> map = createMapIdNuclide_to_Pokazatel_MassNumberNuclide( listResults);
		 Map<Integer, Integer> map2 =sortByValue( map);
		
		 Set<Entry<Integer, Integer>> st = map2.entrySet();
		return st;
	}
		
	private static Object getNuclide_to_PokazatelObjectFromListResultsByID(List<?> listResults, int id){
		for (Object results : listResults) {
			if(((Nuclide_to_Pokazatel) results).getId_Nuclide_to_Pokazatel()==id){
				return results;
			}
		}
		
		return null;
		
	}
	
	private static Map<Integer, Integer> createMapIdNuclide_to_Pokazatel_MassNumberNuclide(List<?> likstResults){
		 HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Object results : likstResults) {
			
			 map.put(((Nuclide_to_Pokazatel) results).getId_Nuclide_to_Pokazatel(), getMassNumberFromNuclideCode(((Nuclide_to_Pokazatel) results).getNuclide().getSymbol_nuclide()));
		}
		return map;
				
	}
	
	
	
	
	private static int getMassNumberFromNuclideCode(String nuclideCode){
		int cod = 0;
	 Pattern p = Pattern.compile("\\d+");
    Matcher m = p.matcher(nuclideCode);
    if (m.find()) {
       return Integer.valueOf(m.group(0));
    }
	return cod;
    
	}
	
	private static <K, V> Map<K, V> sortByValue(Map<K, V> map) {
	    List<Entry<K, V>> list = new LinkedList<>(map.entrySet());
	    Collections.sort(list, new Comparator<Object>() {
	        @SuppressWarnings("unchecked")
	        public int compare(Object o1, Object o2) {
	            return ((Comparable<V>) ((Map.Entry<K, V>) (o1)).getValue()).compareTo(((Map.Entry<K, V>) (o2)).getValue());
	        }
	    });

	    Map<K, V> result = new LinkedHashMap<>();
	    for (Iterator<Entry<K, V>> it = list.iterator(); it.hasNext();) {
	        Map.Entry<K, V> entry = (Map.Entry<K, V>) it.next();
	        result.put(entry.getKey(), entry.getValue());
	    }

	    return result;
	}
	
	
	
}
