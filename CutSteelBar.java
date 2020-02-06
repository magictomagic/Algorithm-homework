package algorithmAssignments;


public class CutSteelBar {

//	public class cutBar{
//		int start = -1;
//		int end = -1;
//		public cutBar(int start, int end) {
//			this.start = start;
//			this.end = end;
//		}
//	}
	
	
	public static int calculateValue(int[] Bar, int s, int e) {
		if(e - s == 1) {
			return 0;			
		}
		else {
			return Bar[e] - Bar[s];
		}
	}
	
	public static void cutMethod(int[] Bar, int[][] value, int[][] s) {
		int lenth = Bar.length - 1;
		for(int i = 0; i < lenth; i++) value[i][i] = -1;	//meaningless, protect
		for(int i = 0; i < lenth - 1; i++) { 
			value[i][i + 1] = 0;
		}//boundary condition
		
		
		for(int r = 2; r < lenth; r++) {	//
			for(int i = 0; i + r <= lenth; i++) {	
				int  j = i + r;
				
				for(int k = i + 1; k < j; k++) { //get min value between i and j
					int temp = value[i][k] + value[k][j] + calculateValue(Bar, i, j);
					if(temp < value[i][j]) {
						value[i][j] = temp;
						s[i][j] = k;
					}
					System.out.println("j   "+ j + "   temp   "+ temp);

				}
				System.out.println("jj  "+ j);
			}
		}
		
		
		System.out.println("value");
		for(int i = 0; i < lenth; i++) {
			for(int j = 0; j < lenth; j++) {
				System.out.print(value[i][j] + " 	");
			}
			System.out.println("");
		}
		System.out.println("s");
		for(int i = 0; i < lenth; i++) {
			for(int j = 0; j < lenth; j++) {
				System.out.print(s[i][j] + " 	");
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) throws Exception {

//		int[] SteelBar = new int[] {0,5,10,12,13,14,18,19};
		int[] SteelBar = new int[] {0,1,2,3,44,45,46,47};	//95
		
		int[][] price = new int[SteelBar.length][SteelBar.length];
		int[][] sequence = new int[SteelBar.length][SteelBar.length];
		for(int i = 0; i < SteelBar.length; i++) {
			for(int j = 0; j < SteelBar.length; j++) {
//				price[i][j] = Integer.MAX_VALUE;
				price[i][j] = 1000;
			}
		}
//		for(int i = 0; i < 6; i++) {
//			for(int j = 0; j < 6; j++) {
//				System.out.print(price[i][j] + "  ");
//			}
//			System.out.println("");
//		}
		
		cutMethod(SteelBar, price, sequence);
		
	}
	
	
}
