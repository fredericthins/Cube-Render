package gameObject;

import model.CubeMesh;

public class Cube extends GameObject{
	
	float s;
	
	public Cube(float s) {
		this.s = s;
		this.mesh = new CubeMesh(s);
		this.init();
	}
	
}
