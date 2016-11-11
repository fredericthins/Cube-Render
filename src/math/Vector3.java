package math;
public class Vector3{
	float x; 
	float y;
	float z;
	public Vector3(Matrix m){    //Vector3 constructor using a Matrix
		float lastValue = m.getValue(0, 3);
		this.x = m.getValue(0,0) / lastValue;
		this.y = m.getValue(0,1) / lastValue;
		this.z = m.getValue(0,2) / lastValue;
	}
	public Vector3(float x, float y, float z){ //Vector3 constructor using three float coordinates
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Vector3 clone(){
		Vector3 newVector = new Vector3(getX(),getY(),getZ());
		return newVector;
	}
	public String toString(){
		String ans = new String("X = " + getX() + ", Y = " + getY() + ", Z = " + getZ());
		return ans;
	}
	public float getMagn(){//Gets magnitude of the vector
		return (float) Math.sqrt(Math.pow(getX(),2) + Math.pow((getY()),2) + Math.pow(getZ(),2));
	}
	public Vector3 scale(float scalar){//Scale a vector
		this.setX(getX()*scalar);
		this.setY(getY()*scalar);
		this.setZ(getZ()*scalar);
		return this;
	}
	public Vector3 normalize(){
		return scale(1/Math.abs(getMagn()));
	}
	public Matrix homogeneous(){
		float[][] tempMatrix = {{this.getX()},{this.getY()},{this.getZ()}};
		Matrix homoVec = new Matrix(tempMatrix);
		return homoVec;
	}
//	public Vector3 rotate(Quaterion){
//		return 
//	}
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
public static Vector3 Add(Vector3 v1, Vector3 v2){
	Vector3 newVector = new Vector3(v1.getX() + v2.getX(),v1.getY() + v2.getY(),v1.getZ()+v2.getZ());
	return newVector;
}
public static float Dot(Vector3 v1, Vector3 v2){
	return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
}
public static Vector3 cross(Vector3 v1, Vector3 v2){
	Vector3 newVector = new Vector3(v1.getY()*v2.getZ() - v1.getZ()*v2.getY(),v1.getZ()*v2.getX() - v1.getX()*v2.getZ(),v1.getX()*v2.getY() - v1.getY()*v2.getX());
	return newVector;
}
public static Vector3 Project(Vector3 v1, Vector3 v2){	//v2 is being projected onto v1
	Vector3 projection = v1.scale(Dot(v1,v2)/v1.getMagn());
	return projection;
}
}