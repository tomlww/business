import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Conditional;


public class Test {
	private static void test(int[]arr) {
        for (int i = 0; i < arr.length; i++) {
            try {
                if (arr[i] % 2 == 0) {
                    throw new NullPointerException();
                } else {
                    System.out.print(i);
                }
            } finally {
                System.out.print("e");
            }
        }
    }
 
    public static void main(String[]args) {
    	String str="you are my friend#";
    	String [] row = str.split("#");
    	List<String[]> list = new ArrayList<String[]>();
    	/*for(String r:row){
    		String [] single = r.split(" ");
    		list.add(single);
    	}
    	for(int i=0;i<list.size();i++){
    		String [] s = list.get(i);
    		System.out.println("Sample Output:"+s.length);
    	}*/
    	
    	 Map<String,String> map = new HashMap<String, String>();
    	Conditional cl = (Conditional) map.values();
    }
   

}
