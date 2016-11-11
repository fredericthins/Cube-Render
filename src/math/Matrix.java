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
	 * Checks to see if the two matrices have the same order
	 * 
	 * @param a the first matrix
	 * @param b the second matrix
	 * @return whether or not the two matrices have the same order
	 */
	public static boolean sameOrder(Matrix a, Matrix b) {
		if(a.getOrder()[0] == b.getOrder()[0] && a.getOrder()[1] == b.getOrder()[1]) {
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
		
		if(!Matrix.sameOrder(a, b)) {
			System.err.println("Order mismatch, cannot add.");
			return null;
		} 
		
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
		
		if(a.getNumColumns() != b.getNumRows()) {
			System.err.println("Order mismatch, cannot multiply");
			return null;
		}
		
		//iterate through the rows of the first matrix
		for(int matrix1row = 0; matrix1row < a.getNumRows(); matrix1row++) {
			//iterate through the columns of the second matrix
			for(int matrix2column = 0; matrix2column < b.getNumColumns(); matrix2column++) {
				//sum begins at 0
				float sum = 0;
				
				//multiply the respective elements of each array by each other and add them together
				for(int iterator = 0; iterator < a.getNumColumns(); iterator++) {
					sum = sum + a.getValue(matrix1row, iterator)*b.getValue(iterator, matrix2column);
				}
				
				//set the desired element of the array to the obtained value
				set[matrix1row][matrix2column] = sum;
			}
		}
			
		return new Matrix(set);
	}
	
	/**
	 * Converts this Matrix, if a sqaure, to a MatrixS
	 * 
	 * @return this Matrix as a MatrixS
	 */
	public MatrixS toSquare() {
		if(this.getNumRows() != this.getNumColumns()) {
			System.err.println("Not a square matrix!");
		}
		
		return new MatrixS(set);
	}
	
	/**
	 * Transposes this matrix, meaning that rows and columns are swapped.
	 * 
	 * @return the transpose of this matrix
	 */
	public Matrix transpose() {
		//the transpose set
		float[][] set = new float[this.getNumColumns()][this.getNumRows()];
		
		//iterate through each element of the matrix, and set the respective element of the transpose set
		for(int i = 0; i < this.getNumRows(); i++) {
			for(int j = 0; j < this.getNumColumns(); j++) {
				set[j][i] = this.getValue(i, j);
			}
		}
		return new Matrix(set);
	}
	
	/**
	 * Gets the order of the matrix.
	 * 
	 * @return the order of the matrix(rows by columns)
	 */
	public int[] getOrder() {
		return new int[] {this.getNumRows(), this.getNumColumns()};
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
	 * Returns the matrix in string form
	 */
	public String toString() {
		String string = "{";
		for(int i = 0; i < this.getNumRows(); i++) {
			string = string + "\n";
			for(int j = 0; j < this.getNumColumns(); j++) {
				string = string + this.getValue(i, j) + " ";
			}
		}
		string = string + "\n" + "}";
		return string;
	}
}