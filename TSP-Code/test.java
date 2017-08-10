import java.util.*;

public class test{
	
	static int[] cityList = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
	

	public static void main(String[] args){
		inversionMutation();
		//System.out.println(cityList);
		
		for (int i = 0; i < cityList.length;i++){
			
			System.out.print(cityList[i] + " ");
		}
		System.out.println();

		
		
	}
	
	
	static void inversionMutation(){
    	
    	Random rand = new Random();
    	int start = rand.nextInt(cityList.length);
    	int end = rand.nextInt(cityList.length - start) + start;
    	System.out.println(start + " " + end);
    	int[] vals = new int[end-start];
    	
    	
    	System.arraycopy(cityList, start, vals, 0, end-start);
    	
    	for(int i = 0; i < vals.length / 2; i++)
    	{
    	    int temp = vals[i];
    	    vals[i] = vals[vals.length - i - 1];
    	    vals[vals.length - i - 1] = temp;
    	}
    	
    	for (int i = 0; i < vals.length;i++){
			
			System.out.print(vals[i] + " ");
		}
		System.out.println();
    	
    	for (int i = 0; i < vals.length; i++){
    		
			int value2 = vals[i];
			//setCity(i, value2);
			cityList[start + i] = value2;
    		
    	} 
	
	}
}