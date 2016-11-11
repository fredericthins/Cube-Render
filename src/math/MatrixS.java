package math;

public class MatrixS extends Matrix {
	//the 2d array that represents the square matrix
	private float[][] set;
	
	/**
	 * Creates a new square matrix with the specified 2d array of values
	 * 
	 * @param set the 2d array of values that will be the matrix
	 */
	public MatrixS(float[][] set) {
		super(set);
		this.set = set;
	}
	
	/**
	 * Creates a new square matrix with the specified 2d array of values, and checks to make sure the array matches the order given
	 * 
	 * @param set the 2d array of values that will be the matrix
	 * @param order the order of the square matrix
	 */
	public MatrixS(float[][] set, int order) {
		super(set);
		this.set = set;
		if(set.length != order || set[0].length != order) {
			System.err.println("Specified set is not a " + order + " by " + order + " matrix!");
		}
	}
	
	/**
	 * Squares this array by multiplying it with itself
	 * 
	 * @return the squared matrix
	 */
	public MatrixS square() {
		return Matrix.multiply(this, this).toSquare();
	}
	
	/**
	 * Gets the minor of the matrix by removing the specified row and column from the matrix
	 * 
	 * @param row the row to disregard
	 * @param column the column to disregard
	 * @return the minor of the matrix at the specified point
	 */
	public MatrixS getMinor(int row, int column) {
		float[][] set = new float[this.getNumRows()-1][this.getNumColumns()-1];
		
		//the top left corner
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				set[i][j] = this.set[i][j];
			}
		}
		
		//the bottom right corner
		for(int i = row+1; i < this.getNumRows(); i++) {
			for(int j = column+1; j < this.getNumColumns(); j++) {
				set[i-1][j-1] = this.set[i][j];
			}
		}
		
		//the bottom left corner
		for(int i = row+1; i < this.getNumRows(); i++) {
			for(int j = 0; j < column; j++) {
				set[i-1][j] = this.set[i][j];
			}
		}
		
		//the top right corner
		for(int i = 0; i < row; i++) {
			for(int j = column+1; j < this.getNumColumns(); j++) {
				set[i][j-1] = this.set[i][j];
			}
		}
		
		return new Matrix(set).toSquare();
	}
	
	/**
	 * The cofactored value of the value at the specified row and column
	 * 
	 * @param row the row of the value
	 * @param column the column of the value
	 * @return the cofactored value of the matrix
	 */
	public float coFactor(int row, int column) {
		if(row%2 == 0 && column%2 == 0) {
			return this.getValue(row, column);
		} else if(row%2 == 1 && column%2 == 1) {
			return this.getValue(row, column);
		} else return -1*this.getValue(row, column);
	}
	
	/**
	 * Calculates the determinant of this matrix recursively
	 * 
	 * @return the determinant
	 */
	public float determinant() {
		//If 2x2 matrix, return the calculated value
		if(this.orderS() == 2) {
			return this.getValue(0, 0)*this.getValue(1, 1) - this.getValue(1, 0)*this.getValue(0, 1);
		} 
		
		//If not 2x2, use cofactors and the determinants of the minors to recursively calculate the determinant of the whole thing
		else {
			//the sum of the minor determinants, which is the determinant of the original matrix
			float sum = 0;
			
			//iterate across the top row(every column in it)
			for(int i = 0; i < this.getNumColumns(); i++) {
				sum = sum + coFactor(0, i)*this.getMinor(0, i).determinant();
			}
			
			return sum;
		}
	}
	
	/**
	 * Calculates the inverse of the matrix through this method: https://www.mathsisfun.com/algebra/matrix-inverse-minors-cofactors-adjugate.html
	 * 
	 * @return the inverse of this matrix
	 */
	public MatrixS inverse() {
		//Calculate the determinant of the original matrix
		float determinant = this.determinant();
		
		//The set that will be the cofactored matrix
		float[][] set = new float[orderS()][orderS()];
		
		//Create the matrix of minors
		for(int i = 0; i < orderS(); i++) {
			for(int j = 0; j < orderS(); j++) {
				set[i][j] = this.getMinor(i, j).determinant();
			}
		}
		
		//Create the matrix of cofactors
		MatrixS matrix = new MatrixS(set);
		
		//Fill out the cofactor matrix set
		for(int i = 0; i < orderS(); i++) {
			for(int j = 0; j < orderS(); j++) {
				set[i][j] = matrix.coFactor(i, j);
			}
		}
		
		//Transpose the matrix of cofactors
		matrix = ((Matrix) matrix).transpose().toSquare();
		
		//Multiply by 1/determinant to obtain the inverse
		return matrix.scale(1/determinant).toSquare();
	}
	
	/**
	 * Returns the order of the square matrix as an integer(if 3x3, returns 3)
	 * 
	 * @return the side length of the square matrix
	 */
	public int orderS() {
		return set.length;
	}
}