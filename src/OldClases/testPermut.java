package OldClases;

	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Map;

	public class testPermut {

	    public static int permAlone(String str) {
	        int length = str.length();
	        int total = 0;
	        int invalid = 0;
	        int overlap = 0;
	        int tot_invalid = 0;
	        ArrayList<Integer> vals = new ArrayList<>();
	        Map<Character, Integer> chars = new HashMap<>();

	        // obtain individual characters and their frequencies from the string
	        for (int i = 0; i < length; i++) {
	            char key = str.charAt(i);
	            if (!chars.containsKey(key)) {
	                chars.put(key, 1);
	            }
	            else {
	                chars.put(key, chars.get(key) + 1);
	            }
	        }

	        // if one character repeated set total to 0
	        if (chars.size() == 1 && length > 1) {
	            total = 0;
	        }
	        // otherwise calculate total, invalid permutations and overlap
	        else {
	            // calculate total
	            total = factorial(length);
	            System.out.println("total = "+length+"! = "+total);
	            // calculate invalid permutations
	            for (char key : chars.keySet()) {
	                int len = 0;
	                int lenPerm = 0;
	                int charPerm = 0;
	                int val = chars.get(key);
	                int check = 1;
	                // if val > 0 there will be more invalid permutations to  calculate
	                if (val > 1) {
	                    check = val;
	                    vals.add(val); 
	                }
	                while (check > 1) {
	                    len = length - check + 1; 
	                    lenPerm = factorial(len);
	                    charPerm = factorial(check);
	                    System.out.println(len+"! * "+check+"! = "+(lenPerm*charPerm));
	                    invalid = lenPerm * charPerm;
	                    total -= invalid; 
	                    check--; 
	                    tot_invalid += invalid;
	                }  
	              
	            }
	            System.out.println("invalid = "+tot_invalid);
	            // calculate overlaps
	            if (vals.size() > 1) {
	            	 
	                overlap = factorial(chars.size());
	                System.out.println("overlaps = "+chars.size()+"! = "+overlap);
	                for (int val : vals) {
	                	 System.out.print(val+"! * ");
	                    overlap *= factorial(val);
	                }
	            }
	            System.out.println();
	            System.out.println("overlap = "+overlap);
	            total += overlap;
	        }
	        
	        System.out.println("total = "+(factorial(length)-tot_invalid+overlap));
	        return total;
	    }
	    

	    // helper function to calculate factorials - not recursive as I was running out of memory on the platform :?
	    private static int factorial(int num) {
	        int result = 1;
	        if (num == 0 || num == 1) {
	            result = num;
	        }
	        else {
	            for (int i = 2; i <= num; i++) {
	                result *= i;
	            }
	        }
	        
	        
	        return result;
	    }

	    public static void main(String[] args) {

	    	String str="aaassddff";
	        System.out.printf("For %s: %d\n\n",str, permAlone(str)); // expected 12
	    }
	}

