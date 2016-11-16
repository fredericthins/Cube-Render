package math;

public class Vector3{
	
	float x; //X coordinate
	float y; //Y coordinate
	float z; //Z coordinate
	
	/**
	 * Creates a new 3D Vector with a given Matrix as the homogeneous form
	 * 
	 * @param m the Matrix of coordinates and scalar
	 */
	public Vector3(Matrix m){				
		float lastValue = m.getValue(3, 0);
		this.x = m.getValue(0,0) / lastValue;
		this.y = m.getValue(1,0) / lastValue;
		this.z = m.getValue(2,0) / lastValue;
	}
	
	/**
	 * Creates a new 3D Vector given the coordinates
	 * 
	 * @param x the float that represents the x coordinate
	 * @param y the float that represents the y coordinate
	 * @param z the float that represents the z coordinate
	 */
	public Vector3(float x, float y, float z){	
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**@return the copy of the current vector*/
	public Vector3 clone(){return new Vector3(getX(),getY(),getZ());}
	/**@return string representing this vector*/
	public String toString(){return "<"+x+", "+y+", "+z+">";}
	/**@return the magnitude of the vector*/
	public float abs(){return (float) Math.sqrt(x*x + y*y + z*z);}
	/**@return normalized form of the current vector*/
	public Vector3 normalize(){	return scale(1/abs());}
	
	/**
	 * Computes the scaling of this vector by a scalar
	 * 
	 * @param scalar the float that represents the scale
	 * @return the vector that is scaled by value scalar
	 */
	public Vector3 scale(float scalar){
		float nx = getX()*scalar; //Scaling the x component by value scalar
		float ny = getY()*scalar; //Scaling the y component by value scalar
		float nz = getZ()*scalar; //Scaling the z component by value scalar
		return new Vector3(nx,ny,nz); //Return the Vector3 scaled to value scalar
	}
	
	/**@return the homogeneous form of this vector*/
	public Matrix homogeneous(){
		return new Matrix(new float[][]{
			{this.getX()},
			{this.getY()},
			{this.getZ()},
			{1}});
	}
	
	/**
	 * Returns a vector that is rotated by quaternion q
	 * 
	 * @param q Quaternion that represents the rotation
	 * @returns the vector that has been rotated by q
	 */
	public Vector3 rotate(Quaternion q){
		Quaternion q1 =  new Quaternion(0, this); //Quaternion representation of the current vector
		Quaternion q2 = q.norm(); //normalized version of quaternion q4
		Quaternion q3 = new Quaternion(Matrix.multiply(q2.matRot(), q1.mat()));//Rotation of the vector by q4
		return new Vector3(q3.getX(),q3.getY(),q3.getZ());//returns the vector rotated
	}
	
	/**
	 * Return the resulting Vector of the addition of two vectors
	 * 
	 * @param v1 the first Vector
	 * @param v2 the second Vector
	 * @return the resulting vector from the addition of v1 and v2
	 */
	public static Vector3 Add(Vector3 v1, Vector3 v2){
		return new Vector3(v1.getX() + v2.getX(),v1.getY() + v2.getY(),v1.getZ()+v2.getZ());
	}
	
	/**
	 * Computes the dot product of two vectors
	 * 
	 * @param v1 the first Vector
	 * @param v2 the second Vector
	 * @return the dot product of v1 and v2
	 */
	public static float Dot(Vector3 v1, Vector3 v2){
		return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
	}
	
	/**
	 * Computes the cross product of two vectors
	 * 
	 * @param v1 the first Vector
	 * @param v2 the second Vector
	 * @return the resulting vector from the cross product of v1 and v2
	 */
	public static Vector3 cross(Vector3 v1, Vector3 v2){
		return new Vector3(
				v1.getY()*v2.getZ() - v1.getZ()*v2.getY(),
				v1.getZ()*v2.getX() - v1.getX()*v2.getZ(),
				v1.getX()*v2.getY() - v1.getY()*v2.getX()
				);
	}
	
	/**
	 * Return the resulting Vector of the projection of one vector onto another
	 * 
	 * @param v1 the first Vector
	 * @param v2 the second Vector
	 * @return the resulting vector that is the projection of v2 on v1
	 */
	public static Vector3 Project(Vector3 v1, Vector3 v2){
		return v1.scale(Dot(v1,v2)/v1.abs());
	}

	//Getters
	/**@returns x the value of x coordinate*/
	public float getX() {return x;}
	/**@returns y the value of y coordinate*/
	public float getY() {return y;}
	/**@returns z the value of z coordinate*/
	public float getZ() {return z;}
	
	//Setters
	/**
	 * Set a new value for the x coordinate
	 * @param x float that represent the new value wanted
	 */
	public void setX(float x) {this.x = x;}
	/**
	 * Set a new value for the y coordinate
	 * @param y float that represent the new value wanted
	 */
	public void setY(float y) {this.y = y;}
	/**
	 * Set a new value for the z coordinate
	 * @param z float that represent the new value wanted
	 */
	public void setZ(float z) {this.z = z;}
	
}