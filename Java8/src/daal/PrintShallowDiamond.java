package daal;

public class PrintShallowDiamond {

	public static void printShallowDiamond(int n) {
		char[][] m = new char[n][n];
		
		//print top-forward slid
		for(int i=n/2,j=0; (i>=0 && j<=n/2); i--, j++) {
			m[i][j] = '*';
		}
		
		//print top-backward slid
		for(int i=n/2,j=n-1; (i>=0 && j>n/2); i--, j--) {
			m[i][j] = '*';
		}
		
		//print bottom-backward slid
		for(int i=n/2+1,j=1; (i<n && j<=n/2); i++, j++) {
			m[i][j] = '*';
		}
		
		//print bottom-forward slid
		for(int i=n/2+1,j=n-2; (i<n && j>n/2); i++, j--) {
			m[i][j] = '*';
		}
		
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n;  j++) {
				System.out.print(m[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int n = 9;
		printShallowDiamond(n);

	}

}
