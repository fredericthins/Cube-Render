package math;

public class Quaternion {
	
	//The four dimensions
	private float w;
	private float x;
	private float y;
	private float z;
	
	/**
	 * Constructor given all 4 dimensions
	 * @param w
	 * @param x
	 * @param y
	 * @param z
	 */
	public Quaternion(float w, float x, float y, float z){
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Constructor given a 3D vector and a 4th dimension
	 * @param w
	 * @param vector
	 */
	public Quaternion(float w, Vector3 vector){
		this.w = w;
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	/**
	 * Constructor given a matrix
	 * @param mat
	 */
	public Quaternion(Matrix mat){
		this.w = mat.getValue(1, 1);
		this.x = mat.getValue(2, 1);
		this.y = mat.getValue(3, 1);
		this.z = mat.getValue(4, 1);
	}
	
	/*
	 * Converts a Quaternion into a String
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "["+w+", "+x+", "+y+", "+z+"]";
		
	}
	
	/**
	 * Scales the Quaternion by size
	 * @param size scale factor
	 * @return the scaled Quaternion
	 */
	public Quaternion scale(float size){
		return new Quaternion(this.w*size, this.x*size, this.y*size, this.z*size);
	}
	
	/**
	 * 
	 * @return the conjugated Quaternion
	 */
	public Quaternion conj(){
		return new Quaternion(this.w, this.x*-1, this.y*-1, this.z*-1);
	}
	
	/**
	 * 
	 * @return the magnitude of the Quaternion
	 */
	public float abs(){
		return (float) Math.sqrt(Quaternion.Multiply(this, this.conj()).w);
		
	}
	
	/**
	 * 
	 * @return the normalized form of the Quaternion
	 */
	public Quaternion norm(){
		return this.scale(1/this.abs());
	}
	
	/**
	 * 
	 * @return the inverse of the Quaternion
	 */
	public Quaternion inverse(){
		return this.conj().scale(1/(this.abs()*this.abs()));
	}
	
	/**
	 * 
	 * @return the matrix form of Quaternion
	 */
	public Matrix mat(){
		return new Matrix(new float[][]{
			{w},
			{x},
			{y},
			{z}
			
		});
	}
	
	/**
	 * For this quaternion Q mutlipied with P, gives matrix for Q*P multiplication
	 * @return matrix for Q*P
	 */
	public MatrixS matPrimary(){
		return new MatrixS(new float[][]{
			{w, -x, -y, -z},
			{x, w, -z, y},
			{y, z, w, -x},
			{z, -y, x, w}
			
		}, 4);
	}
	
	/**
	 * For this quaternion Q mutlipied with P, gives matrix for P*Q multiplication
	 * @return matrix for P*Q
	 */
	public MatrixS matSecondary(){
		return new MatrixS(new float[][]{
			{w, -x, -y, -z},
			{x, w, z, -y},
			{y, -z, w, x},
			{z, -y, -x, w}
			
		}, 4);
	}
	
	/**
	 *For this quaternion Q on vector v, give matrix for Q*v*(Q^-1)
	 * @return A Matrix that represents a quaternion rotation
	 */
	public MatrixS matRot(){
		return Matrix.multiply(this.matPrimary(), this.conj().matSecondary()).toSquare();
	}
	
	/**
	 * 
	 * @param vect1 initial vector
	 * @param vect2 final vector
	 * @return
	 */
	public static Quaternion fromTo(Vector3 vect1, Vector3 vect2){
		double m = Math.sqrt(2f + 2f * Vector3.Dot(vect1, vect2));
		return new Quaternion ((float)(0.5f * m), Vector3.cross(vect1,vect2).scale((float)(1f/m)));
	}
	
	/**
	 * 
	 * @param quat1
	 * @param quat2
	 * @return a new quaternion that is the sum of two quaternions
	 */
	public static Quaternion Add(Quaternion quat1, Quaternion quat2){
		
		return new Quaternion(quat1.w+ quat2.w , quat1.x+ quat2.x, quat1.y+ quat2.y, quat1.z+ quat2.z);
	}
	
	/**
	 * 
	 * @param quat1
	 * @param quat2
	 * @return
	 */
	public static Quaternion Multiply(Quaternion quat1, Quaternion quat2){
		
		return new Quaternion (Matrix.multiply(quat1.matPrimary(), quat2.mat()));
	}
	
	/**
	 * 
	 * @param quad1
	 * @param quat2
	 * @return a float which is the dot of all 4 dimensions
	 */
	public static float Dot(Quaternion quat1, Quaternion quat2){
		
		return quat1.w*quat2.w+quat1.x*quat2.x+quat1.y*quat2.y+quat1.z*quat2.z;
	}
}
