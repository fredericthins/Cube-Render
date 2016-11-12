package graphics;

import java.awt.Graphics;

import javax.swing.JComponent;

import gameObject.Camera;
import gameObject.Cube;
import gameObject.GameObject;

public class Screen extends JComponent{
	
	GameObject[] objects;
	Camera c;

	public Screen() {
		c = new Camera();
		objects = new GameObject[]{new Cube(5)};
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
		repaint();
	}
}
