package math;

public class Matrix {
	//The 2d array that represents the matrix
	private float[][] set;
	
	/**
	 * Creates a new Matrix with the specified 2d array of values
	 * 
	 * @param set the 2d array of values that will be the matrix
	 */
	public Matrix(float[][] set) {
		this.set = set;
	}
	
	/**
	 * Multiplies the first matrix by the second.
	 * 
	 * @param a the first matrix
	 * @param b the second matrix
	 * @return the matrix that is the product of the two
	 */
	public static boolean sameOrder(Matrix a, Matrix b) {
		if(a.getOrder().equals(b.getOrder())) {
			return true;
		} else return false;
	}
	
	/**
	 * Sums the two matrices together
	 * 
	 * @param a the first matrix
	 * @param b the second matrix
	 * @return the matrix that is the sum of the two
	 */
	public static Matrix add(Matrix a, Matrix b) {
		//the set that is the sum
		float[][] set = new float[a.getNumRows()][a.getNumColumns()];
		
		//iterate through each row
		for(int row = 0; row < a.getNumRows(); row++) {
			//iterate through each column
			for(int column = 0; column < a.getNumColumns(); column++) {
				//sum the elements of each matrix at that location
				set[row][column] = a.getValue(row, column) + b.getValue(row, column);
			}
		}
		return new Matrix(set);
	}
	
	/**
	 * Multiplies the two matrices together
	 * 
	 * @param a the first matrix
	 * @param b the second matrix
	 * @return the matrix that is the product of the two
	 */
	public static Matrix multiply(Matrix a, Matrix b) {
		//the set that is the product
		float[][] set = new float[a.getNumRows()][b.getNumColumns()];
		
		//iterate through the rows of the first matrix
		for(int matrix1row = 0; matrix1row < a.getNumRows(); matrix1row++) {
			//iterate through the columns of the second matrix
			for(int matrix2column = 0; matrix2column < b.getNumColumns(); matrix2column++) {
				//sum begins at 0
				float sum = 0;
				
				//multiply the respective elements of each array by each other and add them together
				for(int iterator = 0; iterator < b.getNumColumns(); iterator++) {
					sum = sum + a.getValue(matrix1row, iterator)*b.getValue(iterator, matrix2column);
				}
				
				//set the desired element of the array to the obtained value
				set[matrix1row][matrix2column] = sum;
			}
		}
		return new Matrix(set);
	}
	
	/**
	 * Gets the order of the matrix.
	 * 
	 * @return the order of the matrix(rows by columns)
	 */
	public int[][] getOrder() {
		return new int[this.getNumRows()][this.getNumColumns()];
	}
	
	/**
	 * Gets the value of the matrix at the specified location
	 * 
	 * @param row the desired row
	 * @param column the desired column
	 * @return the value at (row, column) of the matrix
	 */
	public float getValue(int row, int column) {
		return set[row][column];
	}
	
	/**
	 * Gets the number of rows of the matrix
	 * 
	 * @return the number of rows
	 */
	public int getNumRows() {
		return set.length;
	}
	
	/**
	 * Gets the number of columns of the matrix
	 * 
	 * @return the number of columns
	 */
	public int getNumColumns() {
		return set[0].length;
	}
	
	/**
	 * Scales each element of the array by the specified scalar
	 * 
	 * @param scalar the scalar by which the elements of the matrix are multiplied
	 */
	public Matrix scale(float scalar) {
		//the set that is the scaled result
		float[][] set = new float[this.getNumRows()][this.getNumColumns()];
		
		//iterate through each row
		for(int i = 0; i < set.length; i++) {
			//iterate through each column
			for(int j = 0; j < set[0].length; j++) {
				//scale each element of the matrix by the scalar
				set[i][j] = this.set[i][j] * scalar;
			}
		}
		return new Matrix(set);
	}
	
	@Override
	/**
	 * Returns a string specifying the number of rows and columns of this matrix
	 */
	public String toString() {
		return set.length + "rows by " + set[0].length + "columns";
	}
}
