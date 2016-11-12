package gameObject;

import math.Matrix;
import math.MatrixS;
import math.Quaternion;
import math.Vector3;

public class Camera {
	public Transform transform;
	private float znear = 1;
	private float zfar = 100;
	private float x = 0.4f;
	private float y = 0.4f;
	private final Vector3 worldDir = new Vector3(0,0,-1);
	
	public Camera() {
		transform = new Transform();
		this.transform.forward = new Vector3(0,0,-1);
	}
	
	/**
	 * 
	 * @param V new view point
	 */
	public void lookAt(Vector3 V){
		this.transform.forward = Vector3.Add(V, this.transform.position.scale(-1)).normalize();
	}
	
	/**
	 * 
	 * @return rotated Quaternion
	 */
	public Quaternion rotCenterQ(){
		return Quaternion.fromTo(this.transform.forward, this.worldDir);
		
	}
	
	/**
	 * 
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
	 * 
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
	 * 
	 * @return rotated matrix
	 */
	public MatrixS rotCenterM(){
		return this.rotCenterQ().matRot();
	}
	
	/**
	 * 
	 * @return transformation matrix for the camera
	 */
	public MatrixS CameraT(){
		return Matrix.multiply(this.rotCenterM(), this.displace()).toSquare();
//		return Matrix.multiply(Matrix.multiply(this.perspectiveView(), this.rotCenterM()), this.displace()).toSquare();
	}
}
