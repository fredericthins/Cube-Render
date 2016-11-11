package math;

public class MatrixS extends Matrix {
	private float[][] set;
	private int order;
	
	public MatrixS(float[][] set) {
		super(set);
	}
	
	public MatrixS(float[][] set, int order) {
		super(set);
		if(set.length != order || set[0].length != order) {
			System.err.println("Specified set is not a " + order + " by " + order + " matrix!");
		}
		
	}
	
}
