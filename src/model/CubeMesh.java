package model;

import math.Vector3;

public class CubeMesh extends Mesh{
	
	float s; //sidelength of cube
	
	/**
	 * initalize cube with vertices relative to center point and s sidelength
	 * @param sidelength-length of each side of cube
	 */
	public CubeMesh(float sidelength){
		this.s = sidelength;
		
		this.vertices = new float[]{
			s/2, -s/2, s/2,			//F-D-R , 0
			-s/2, -s/2, s/2,		//F-D-L , 1
			-s/2, s/2, s/2,			//F-T-L , 2
			s/2, s/2, s/2,			//F-T-R , 3
			-s/2, s/2, -s/2,		//B-T-L , 4
			s/2, s/2, -s/2,			//B-T-R , 5
			s/2, -s/2, -s/2,		//B-D-R , 6
			-s/2,-s/2, -s/2			//B-D-L , 7
		};
		
		this.faceSet = new int[][]{ 
			{1,0,2},	//Front, left 
			{0,3,2},	//Front, right
			{0,6,5},	//Right, right
			{0,5,3},	//Right, left
			{2,3,5},	//Top, right
			{2,5,4},	//Top, left
			{1,2,4},	//Left, right
			{7,1,4},	//Left, left
			{1,0,6},	//Bottom, right
			{1,6,7},	//Bottom, left
			{7,6,4},	//Back, left
			{6,5,4}		//Back, right
		};
		
		this.normal = new Vector3[] {
			new Vector3(0,0,1),	//Front, left
			new Vector3(0,0,1),	//Front, right
			new Vector3(1,0,0), //Right, right
			new Vector3(1,0,0), //Right, left
			new Vector3(0,1,0), //Top, right
			new Vector3(0,1,0), //Top, left
			new Vector3(-1,0,0), //Left, right
			new Vector3(-1,0,0), //Left, left
			new Vector3(0,-1,0), //Bottom, right
			new Vector3(0,-1,0), //Bottom, left
			new Vector3(0,0,-1), //Back, left
			new Vector3(0,0,-1)	//Back, right
		};
	}
}
