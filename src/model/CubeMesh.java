package model;

public class CubeMesh extends Mesh{
	
	float s; //sidelength of cube
	
	public CubeMesh(float sidelength, float[] vertices){
		this.s = sidelength;
		
		this.vertices = new float[]{
			s/2, -s/2, s/2,			//F-D-R 
			-s/2, -s/2, s/2,		//F-D-L
			-s/2, s/2, s/2,			//F-T-L
			s/2, s/2, s/2,			//F-T-R
			-s/2, s/2, -s/2,		//B-T-L
			s/2, s/2, -s/2,			//B-T-R
			s/2, -s/2, -s/2,		//B-D-R
			-s/2,-s/2, -s/2			//B-D-L
		};
	}
}
