package thread;

public class MatrixMultiplication {

	public static void main(String[] args) {
		int[][] m1 = {
				{1, 2},
				{2, 1}
		};
		int[][] m2 = {
				{1, 2, 3},
				{3, 2, 1}
		};

		int[][] p = multiply(m1, m2);
		
		//print p
		for(int i=0; i<p.length; i++) {
			for(int j=0; j<p[0].length; j++) {
				System.out.print(p[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static int[][] multiply(int[][] m1, int[][] m2) {
		int row1 = m1.length;
		int col1 = m1[0].length;
		
		int row2 = m2.length;
		int col2 = m2[0].length;
		
		if(col1 != row2) {
			System.out.println("Multiplication not possible");
			return null;
		}

		int[][] p = new int[row1][col2];
		
		for(int i=0; i<row1;i++) {
			for(int j=0; j<col2; j++) {
				for(int k=0; k<row2; k++) {
					p[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}
		
		return p;
	}

}
