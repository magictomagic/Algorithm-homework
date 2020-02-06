package algorithmAssignments;

/**
 * first, generate Matrix that test required.	Use twice random methods too accomplish it.
 * 		the most stupid method:	consider the solution space. the number of vector various from 1 to [n/2], and the combination various from C1,n to C[n/2],n.
 * consider we should use required methods to solve it, a subset tree is created. Solution space is a n level tree subset 
 * tree, choose whether should choose the vector.
 */

public class matrixMutualExclusion {	
	public static int randomSpecifiedScopeInteger(int min, int max) {
		int num = min + (int)(Math.random() * (max-min+1));
		return num;
	}	
	public static float randomSpecifiedScopeFloat(float min, float max) {
		float num = min + (float)(Math.random() * (max-min));
		return num;
	}	
	public static Boolean[][] generateBooleanMatrix(int row, int column, float minColumnPercent, float maxColumnPercent){
		Boolean[][] BooleanMatrix = new Boolean[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				BooleanMatrix[i][j] = false;
			}
		}
		// attention each column's true quantities are different
		for (int c = 0; c < column; c++) {
			int times = (int)(randomSpecifiedScopeFloat(minColumnPercent, maxColumnPercent) * row);
			System.out.print(c + ":" + times + "	");
			for (int i = 0; i < times; i++) {	//	attention, no repetition
				int rIndex = randomSpecifiedScopeInteger(0, row - 1);
				if(BooleanMatrix[rIndex][c] == true) {
					i--;
				}
				else {
					BooleanMatrix[rIndex][c] = true;
				}
			}
		}
		return BooleanMatrix;
	}
	
	/*
	 * Combination of column vectors
	 */
	public static Boolean[] Vmethod(Boolean[][] testMatrix, Boolean[] oneX) {
		Boolean[] temp;
		temp = new Boolean[testMatrix.length];
		for (int i = 0; i < testMatrix.length; i++) {
			temp[i] = false;
		}
		
		for (int i = 0; i < oneX.length; i++) {
			for (int j = 0; j < testMatrix.length; j++) {
				if(oneX[i]) {
					temp[j] = testMatrix[j][i] | temp[j];
				}
			}		
		}
		return temp;
	}
	
	/*
	 * get B through A(oneX), B may return all false value.
	 */
	public static Boolean[] getTheOtherVerctor(Boolean[][] testMatrix, Boolean[] oneX){
		Boolean[] OneAggregate =  Vmethod(testMatrix, oneX);
		Boolean[] temp;
		temp = new Boolean[testMatrix[0].length];
		for (int i = 0; i < testMatrix[0].length; i++) {
			temp[i] = true;
		}
		for (int c = 0; c < testMatrix[0].length; c++) {
			for (int r = 0; r < testMatrix.length; r++) {
				if(OneAggregate[r] & testMatrix[r][c]) {
					temp[c] = false;
				}
			}	
		}
		return temp;
	}
	
	/*
	 * bound function
	 */
	public static Boolean bound(Boolean[] A) {
		Boolean[] B = getTheOtherVerctor(Matrix, A);
		Boolean tmpB = false;
		Boolean tmpA = false;
		for (int i = 0; i < B.length; i++) {
			tmpB = tmpB | B[i];
		}
		for (int i = 0; i < A.length; i++) {
			tmpA = tmpA | A[i];
		}
		if(!tmpB | !tmpA) {
			return false;
		}
		
		int numA = 0;
		int numB = 0;
		for (int i = 0; i < A.length; i++) {
			if(A[i]) numA++;
		}
		for (int i = 0; i < B.length; i++) {
			if(B[i]) numB++;
		}
		if(numA + numB > largestSum) {
			largestSum = numA + numB;
			return true;
		}
		else return false;
	}
	
	public static void output(Boolean[] x) {
		System.out.println("");
		if(count == 0) {
			System.out.println("solution space A:");
		}
		if(count == 1) {
			System.out.println("solution space B:");
		}
		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i] + "	");
		}
		if(count == 1) {
			System.out.println("\nlargestSum:	" + largestSum);
		}
		
		count++;
	}
	
	/*
	 * Backtracking search
	 */
	public static void backTrack(int t) {	//Boolean[][] Matrix, Boolean[] A
		if(t >= colMatrix) {
			
			if(bound(x)) {
				flag = true;
				for (int i = 0; i < colMatrix; i++) {
					bestx[i] = x[i];
				}
			}
			return;
		}
		else {
			progress++;
//			System.out.println(progress++);
			if((progress/progressBar)*50 > milepost) {
				System.out.print("#");
				milepost++;
			}
			
			x[t] = true;
			backTrack(t + 1);
			x[t] = false;
			backTrack(t + 1);
		}
	}
	
	public static Boolean flag = false;
	public static int count = 0;
	public static int rowMatrix = 1000;
	public static int colMatrix = 20;
	public static float minPercent = 0.0f;
	public static float maxPercent = 0.1f;
	
	public static double milepost = 0;
	public static double progressBar = (long)Math.pow(2, colMatrix);
	public static double progress = 0;
	
	public static Boolean[] x; 	// dynamic solution space
	public static Boolean[] bestx;
	public static Boolean[][] Matrix = generateBooleanMatrix(rowMatrix, colMatrix, minPercent, maxPercent);
	
	static int largestSum = 0;	//	MAX(A.length + B.length)
	
	public static void main(String[] args) {
		//initialize solution space []x
		x = new Boolean[colMatrix];
		bestx = new Boolean[colMatrix];
		for (int i = 0; i < colMatrix; i++) {
			x[i] = false;
		}
		
		Boolean[][] t = Matrix;
		for (int r = 0; r < rowMatrix; r++) {
			System.out.println("");
			for (int c = 0; c < colMatrix; c++) {
				System.out.print(t[r][c] + "	");
			}	
		}
		
		//Attention A & B at least have one element
		//add no solution, B
		System.out.println("\n##################################################" + "---initialize	complete");
		backTrack(0);
		System.out.println("---backtrack	complete");
		if(flag) {
			output(bestx);
			output(getTheOtherVerctor(Matrix, bestx));
		}
		else {
			System.out.println("\nNo solution");
		}
		
//		System.out.println("\nsolution space");
//		for (int i = 0; i < bestx.length; i++) {
//			System.out.print(bestx[i] + "	");
//		}
//		
//		Boolean[] b = getTheOtherVerctor(t, x);
//		
//		System.out.println("\nVvector");	//A
//		Boolean[] v = Vmethod(t, x);
//		for (int i = 0; i < t.length; i++) {
//			System.out.print(v[i] + "	");
//		}
//		System.out.println("\nother");	//B
//		for (int i = 0; i < b.length; i++) {
//			System.out.print(b[i] + "	");
//		}
		
	}
	
	
	
	
	
}
