package math;
/* This program creates the basic methods and attributes for a 3d vector
 * 	Attributes:
 * 		float x = x coordinate
 * 		float y = y coordinate
 * 		float z = z coordinate
 * 	Instance Methods:
 * 		clone()
 * 		toString()
 * 		getMagn()
 * 		scale(float)
 * 		normalize()
 * 		homogeneous()
 * 		rotate()
 * 		Getters and Setters
 * 	Static Methods:
 * 		Add(Vector3, Vector3)
 * 		Dot(Vector3, Vector3)
 * 		Cross(Vector3, Vector3)
 * 		Project(Vector3, Vector3)
 */
public class Vector3{
	float x; //X coordinate
	float y; //Y coordinate
	float z; //Z coordinate
	/* Creates a new 3D Vector with a given Matrix as the coordinates
	 * 
	 * @param Matrix the Matrix of coordinates and scalar
	 */
	public Vector3(Matrix m){  						
		float lastValue = m.getValue(3, 0);
		this.x = m.getValue(0,0) / lastValue;
		this.y = m.getValue(1,0) / lastValue;
		this.z = m.getValue(2,0) / lastValue;
	}
	/* Creates a new 3D Vector given the coordinates
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
	/* Creates a copy of the 3D vector
	 * 
	 * @return newVector the copy of the current vector
	 */
	public Vector3 clone(){
		Vector3 newVector = new Vector3(getX(),getY(),getZ()); //Creates the copy of Vector
		return newVector;									//Returns the copy version of Vector
	}
	/* Return a string that shows attributes of a vector
	 * 
	 * @return str the string that shows the attributes of the vector
	 */
	public String toString(){						
		String str = new String("X = " + getX() + ", Y = " + getY() + ", Z = " + getZ());//Creating a string that shows the attributes of a vector
		return str;// Return the string
	}
	/* Returns the magnitude of a vector
	 * 
	 * @return float that represents the magnitude of the vector
	 */
	public float getMagn(){
		return (float) Math.sqrt(x*x + y*y + z*z); //Return the magnitude
	}
	/* Returns a vector that is scaled by a value of scalar
	 * 
	 * @param scalar the float that represents the scale
	 * 
	 * @return the vector that is scaled by value scalar
	 */
	public Vector3 scale(float scalar){
		float nx = getX()*scalar; //Scaling the x component by value scalar
		float ny = getY()*scalar; //Scaling the y component by value scalar
		float nz = getZ()*scalar; //Scaling the z component by value scalar
		return new Vector3(nx,ny,nz); //Return the Vector3 scaled to value scalar
	}
	/*Return a nomarlized version of the current Vector3
	 * 
	 * @return normalized version of the current vector
	 */
	public Vector3 normalize(){	
		return scale(1/getMagn());
	}
	/*Return homogeneous version of the vector
	 * 
	 * @return homoVec the Matrix[1][4] that gives the coordinates of the vector and scalar
	 */
	public Matrix homogeneous(){					
		float[][] tempMatrix = {{this.getX()},{this.getY()},{this.getZ()},{1}}; //Creating a matrix with the coordinates of vector  
		Matrix homoVec = new Matrix(tempMatrix);//Translate into a matrix
		return homoVec;//Return the matrix
	}
	/*Returns a vector that is rotated by quaternion q4
	 * 
	 * @param q4 Quaternion that represents the rotation
	 * 
	 * @returns tempV the vector that has been rotated by q4
	 */
	public Vector3 rotate(Quaternion q4){
		Quaternion q1 =  new Quaternion(0, this); //Quaternion representation of the current vector
		Quaternion q2 = q4.norm(); //normalized version of quaternion q4
		Quaternion q3 = new Quaternion(Matrix.multiply(q1.matRot(), q2.mat()));//Rotation of the vector by q4
		Vector3 tempV = new Vector3(q3.getX(),q3.getY(),q3.getZ()); //the resulting vector rotated
		return tempV;//returns the vector rotated
	}
	//Getters and Setters
	/* Returns the value of x coordinate
	 * 
	 * @returns x the value of x coordinate
	 */
	public float getX() {
		return x;
	}
	/* Set a new value for the x coordinate
	 * 
	 * @param x float that represent the new value wanted
	 */
	public void setX(float x) {
		this.x = x;
	}
	/* Returns the value of y coordinate
	 * 
	 * @returns y the value of y coordinate
	 */
	public float getY() {
		return y;
	}
	/* Set a new value for the y coordinate
	 * 
	 * @param y float that represent the new value wanted
	 */
	public void setY(float y) {
		this.y = y;
	}
	/* Returns the value of z coordinate
	 * 
	 * @returns z the value of z coordinate
	 */
	public float getZ() {
		return z;
	}
	/* Set a new value for the z coordinate
	 * 
	 * @param z float that represent the new value wanted
	 */
	public void setZ(float z) {
		this.z = z;
	}
/* Return the resulting Vector of the addition of two vectors
 * 
 * @param v1 the first Vector
 * @param v2 the second Vector
 * 
 * @return newVector the resulting vector from the addition of v1 and v2
 */
public static Vector3 Add(Vector3 v1, Vector3 v2){
	Vector3 newVector = new Vector3(v1.getX() + v2.getX(),v1.getY() + v2.getY(),v1.getZ()+v2.getZ());
	return newVector;
}
/* Return the resulting Vector of the dot product of two vectors
 * 
 * @param v1 the first Vector
 * @param v2 the second Vector
 * 
 * @return newVector the resulting vector from the dot product of v1 and v2
 */
public static float Dot(Vector3 v1, Vector3 v2){
	return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
}
/* Return the resulting Vector of the cross product of two vectors
 * 
 * @param v1 the first Vector
 * @param v2 the second Vector
 * 
 * @return newVector the resulting vector from the cross product of v1 and v2
 */
public static Vector3 cross(Vector3 v1, Vector3 v2){
	Vector3 newVector = new Vector3(v1.getY()*v2.getZ() - v1.getZ()*v2.getY(),v1.getZ()*v2.getX() - v1.getX()*v2.getZ(),v1.getX()*v2.getY() - v1.getY()*v2.getX());
	return newVector;
}
/* Return the resulting Vector of the projection of one vector onto another
 * 
 * @param v1 the first Vector
 * @param v2 the second Vector
 * 
 * @return projection the resulting vector that is the projection of v2 on v1
 */
public static Vector3 Project(Vector3 v1, Vector3 v2){
	Vector3 projection = v1.scale(Dot(v1,v2)/v1.getMagn());
	return projection;
}
}