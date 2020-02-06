package algorithmAssignments;

/**
 * notice every time Median Algorithm put the smaller value to the front of RandomSelectedValue, put the bigger to the behind of
 * RandomSelectedValue: to arrcord with average situation, select Median Algorithm. Attention: change the sequence of array.
 * 
 * sum up the value of each of two part,if one is bigger than 1/2, select Median value from which, again, get two parts.
 * 
 * input in the form of reading file
 */

import java.io.*;
public class weightedMedian {
	public static int randomSpecifiedScopeInteger(int min, int max) {
		int num = min + (int)(Math.random() * (max-min+1));
		return num;
	}	
	public static int[] swapArrayValue(int[] array, int i, int j) {
		int temp;
		temp = array[j];
		array[j] = array[i];
		array[i] = temp;
		return array;	
	}	
	public static int[] MedianAlgorithm(int[] array, int startFrom, int endFrom) {
		if(count == 0) {
			length = endFrom - startFrom;
			start = startFrom;
			end = endFrom;
		}
		count++;
		int boundary = randomSpecifiedScopeInteger(startFrom, endFrom);	//index
		int boundaryvalue = array[boundary];
		int s = startFrom, e = endFrom;
		while (s < e) {
			if(array[e] == boundaryvalue) {
				if(array[s] < boundaryvalue) {
					s++;
					continue;
				}
				if(array[s] > boundaryvalue) {
					swapArrayValue(array, e, s);
					continue;
				}
			}
			if(array[s] == boundaryvalue) {
				if(array[e] > boundaryvalue) {
					e--;
					continue;
				}
				if(array[e] < boundaryvalue) {
					swapArrayValue(array, e, s);
					continue;
				}
			}
			if(array[s] > boundaryvalue) {
				swapArrayValue(array, s, e);
				e--;
			}
			if(array[s] < boundaryvalue) {
				s++;
			}	
		}
		if(s < (int) (start + end) / 2) {
			return MedianAlgorithm(array, s, endFrom);
		}
		if(s > (int) (start + end) / 2) {
			return MedianAlgorithm(array, startFrom, e);
		}
		else {
			medianIndex = s;
			medianNumber = array[medianIndex];
			count = 0;
			end = -1;
			start = -1;
			return array;
		}
	}
	public static int[] WeightedMedian(int[] array, int startFrom, int endFrom) {
		float leftWeight = 0;
		float rightWeight = 0;
		x = MedianAlgorithm(array, startFrom, endFrom);
		for (int i = 0; i < medianIndex; i++) {
			leftWeight = leftWeight + w[x[i]];
		}
		for (int i = x.length - 1; i > medianIndex; i--) {
			rightWeight = rightWeight + w[x[i]];
		}		
		if (leftWeight > 0.5) {
			return WeightedMedian(x, startFrom, medianIndex);
		}
		if (rightWeight > 0.5){
			return WeightedMedian(x, medianIndex, endFrom);
		}
		else {	//	 (leftWeight < 0.5 && rightWeight < 0.5) 
			System.out.println("Index: " + medianIndex);
			System.out.println("x[i]: " + x[medianIndex] + "	w[x[i]]: " + w[x[medianIndex]]);
			return x;
		}
	}
	static int start = -1;
	static int end = -1;
	static int medianIndex = -1;
	static int medianNumber = -1;
	static int length = -1;
    static int count = 0; 	//	MedianAlgorithm
    static int times = 0;	// 	WeightedMedian
	static String line = null; 
    static String[] sp; 
    static int[] x = null;
    static float[] w = null;
    static int[] t = null;
    static int lineNumber = 0;
    static float judge = 0;
	public static void main(String[] args) throws Exception {
		FileReader fr=new FileReader("D:\\default\\testJavaInput\\first\\2.txt");
		BufferedReader br=new BufferedReader(fr);
	    while((line=br.readLine())!=null){ 
	    	lineNumber++;    	
	    	weightedMedian.sp = line.split("	"); 
		    if(lineNumber%3 == 1) {
		    	weightedMedian.x = new int[weightedMedian.sp.length];
			    for(int i = 0, l = weightedMedian.sp.length; i < l; ++i){ 
			    	weightedMedian.x[i] = Integer.parseInt(weightedMedian.sp[i]); 
		        }
		    }
		    if(lineNumber%3 == 2) {
		    	weightedMedian.w = new float[sp.length];
		    	for(int i = 0; i < sp.length; ++i) {
			    	w[x[i]] = Float.parseFloat(sp[i]);
			    	judge = judge + w[x[i]];
		    	}	
		    }
	    } 
	    br.close();
	    if(judge != 1) {
	    	System.out.println("input weight is not 1");
	    	return;
	    }
	    WeightedMedian(x, 0, x.length - 1);
	}

}
