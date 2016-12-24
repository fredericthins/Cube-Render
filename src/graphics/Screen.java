package graphics;

import java.awt.Graphics;

import javax.swing.JComponent;

import gameObject.Camera;
import gameObject.Cube;
import gameObject.GameObject;
import math.Quaternion;
import math.Vector3;

public class Screen extends JComponent{
	
	GameObject[] objects;
	Camera c;

	//Temp things
	Quaternion r;
	
	public Screen() {
		c = new Camera();
		c.transform.position = new Vector3(0,0,5);
		
		objects = new GameObject[]{new Cube(1)};
//		objects[0].transform.rotation = Quaternion.fromTo(new Vector3(-1,-1,0), new Vector3(0,0,-1));
		
		r = Quaternion.fromTo(new Vector3(0,0, -1), new Vector3(0,2f,0));
		r.setW(r.getW()*0.005f);
	//	r=r.scale(1/r.getX());
		
		System.out.println(r.toString());
		
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		for (GameObject t: objects) {
			t.updateMesh(c);
			t.draw(g);
		}
	}
	
	/**
	 *  Update the screen
	 */
	public void update(){
		objects[0].transform.rotation = Quaternion.Multiply(objects[0].transform.rotation, r);
		if(objects[0].transform.rotation.getX()!=0)objects[0].transform.rotation = objects[0].transform.rotation.scale(1/objects[0].transform.rotation.getX());
		else objects[0].transform.rotation = objects[0].transform.rotation.scale(1/objects[0].transform.rotation.getY());
		System.out.println(objects[0].transform.rotation);
		repaint();
	}
}
