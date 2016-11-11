package math;
/* This program creates the basic methods and attributes for a 3d vector
 * 	Attributes:
 * 		float x = x coordinate
 * 		float y = y coordinate
 * 		float z = z coordinate
 * 	Insta. Methods:
 * 		clone()
 * 		toString()
 * 		getMagn()
 * 		scale(float)
 * 		normalize()
 * 		homogeneous()
 * 		Getters and Setters
 * 	Static Methods:
 * 		Add(Vector3, Vector3)
 * 		Dot(Vector3, Vector3)
 * 		Cross(Vector3, Vector3)
 * 		Project(Vector3, Vector3)
 */
public class Vector3{
	float x; 
	float y;
	float z;
	public Vector3(Matrix m){  						  //Vector3 constructor using a Matrix
		float lastValue = m.getValue(0, 3);
		this.x = m.getValue(0,0) / lastValue;
		this.y = m.getValue(0,1) / lastValue;
		this.z = m.getValue(0,2) / lastValue;
	}
	public Vector3(float x, float y, float z){		 //Vector3 constructor using three float coordinates
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Vector3 clone(){							//Copy the current vector and return the copy Vector3
		Vector3 newVector = new Vector3(getX(),getY(),getZ());
		return newVector;
	}
	public String toString(){						//Return a string that tells the components of Vector3
		String ans = new String("X = " + getX() + ", Y = " + getY() + ", Z = " + getZ());
		return ans;
	}
	public float getMagn(){							//Gets magnitude of the vector
		return (float) Math.sqrt(Math.pow(getX(),2) + Math.pow((getY()),2) + Math.pow(getZ(),2));
	}
	public Vector3 scale(float scalar){				//Scale a vector by a magnitude of scalar
		this.setX(getX()*scalar);
		this.setY(getY()*scalar);
		this.setZ(getZ()*scalar);
		return this;
	}
	public Vector3 normalize(){						//Return a normalized version of the current vector3
		return scale(1/Math.abs(getMagn()));
	}
	public Matrix homogeneous(){					//Return the homogeneous of the current vector [1][4] with the last value as 1
		float[][] tempMatrix = {{this.getX()},{this.getY()},{this.getZ()}};   
		Matrix homoVec = new Matrix(tempMatrix);
		return homoVec;
	}
	public Vector3 rotate(Quaterion){				//Rotating a vector
		Quaterion q1 =  new Quaterion(0, this);
		Quaterion q2 = q.norm();
		Quaterion q3 = new Quaterion(Matrix.multiply(q1.MatRot(), q2.Mat()));
		return 
	}
													//Getters and Setters
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
public static Vector3 Add(Vector3 v1, Vector3 v2){	//The addition of two vector3(v1,v2) returns the resulting vector
	Vector3 newVector = new Vector3(v1.getX() + v2.getX(),v1.getY() + v2.getY(),v1.getZ()+v2.getZ());
	return newVector;
}
public static float Dot(Vector3 v1, Vector3 v2){	//Returns the dot product of two vectors
	return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
}
public static Vector3 cross(Vector3 v1, Vector3 v2){//Returns the cross product of two vectors
	Vector3 newVector = new Vector3(v1.getY()*v2.getZ() - v1.getZ()*v2.getY(),v1.getZ()*v2.getX() - v1.getX()*v2.getZ(),v1.getX()*v2.getY() - v1.getY()*v2.getX());
	return newVector;
}
public static Vector3 Project(Vector3 v1, Vector3 v2){//Returns the projection of two vectors(v2 is being projected onto v1)
	Vector3 projection = v1.scale(Dot(v1,v2)/v1.getMagn());
	return projection;
}
}