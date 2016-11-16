package gameObject;

import math.Matrix;
import math.MatrixS;
import math.Quaternion;
import math.Vector3;

public class Camera {
	public Transform transform;
	
	//Camera transform values
	private float znear = 1;
	private float zfar = 100;
	private float x = 0.4f;
	private float y = 0.4f;
	
	//Ideal facing direction for projection onto x,y
	private final Vector3 worldDir = new Vector3(0,0,-1);
	
	/**
	 * Initializes a Camera object
	 */
	public Camera() {
		transform = new Transform();
		this.transform.forward = new Vector3(0,0,-1);
	}
	
	/**
	 * Calculates new forward direction to look at a new point
	 * 
	 * @param p new view point
	 */
	public void lookAt(Vector3 p){
		this.transform.forward = Vector3.Add(p, this.transform.position.scale(-1)).normalize();
	}
	
	/**
	 * @return Quaternion to rotate towards the worldDir
	 */
	public Quaternion rotCenterQ(){
		return Quaternion.fromTo(this.transform.forward, this.worldDir);
		
	}
	
	/**
	 * @return perspective transformation matrix
	 */
	public MatrixS perspectiveView(){
		return new MatrixS(new float [] [] {
			{1/x, 0, 0, 0},
			{0, 1/y, 0, 0},
			{0, 0, -2/(zfar-znear), -2*(znear+zfar)/(zfar-znear)},
			{0, 0, 1, 0}
		});
	}
	
	/**
	 * @return displacement matrix to move camera to origin
	 */
	public MatrixS displace(){
		return new MatrixS(new float[][]{
			{1,0,0, +this.transform.position.getX()},
			{0,1,0, +this.transform.position.getY()},
			{0,0,1, -this.transform.position.getZ()},
			{0,0,0,1}
		});
	}
	
	/**
	 * @return Rotation towards worldDir matrix
	 */
	public MatrixS rotCenterM(){
		return this.rotCenterQ().matRot();
	}
	
	/**
	 * @return vector3 post camera transformation
	 */
	public Vector3 CameraT(Vector3 pos){
		//Move with camera to center
		Vector3 p = new Vector3(Matrix.multiply(this.displace(), pos.homogeneous()));
		
		//Rotate with camera to worldDir
		p = p.rotate(this.rotCenterQ());
		
		//Apply perspective transformation
		p = new Vector3(Matrix.multiply(this.perspectiveView(), p.homogeneous()));
		
		return p;
	}
}
