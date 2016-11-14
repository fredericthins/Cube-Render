package gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import math.Matrix;
import math.MatrixS;
import math.Vector3;
import model.Mesh;

public class GameObject {

	// GameObject attributes
	public Transform transform = new Transform();
	Mesh mesh;
	Polygon[] polygons; 		//Set of polygons for java to render (triangles only)
	Vector3[] normals;
	
	public void init() {
		this.normals = this.mesh.normal;
	}

	/**
	 * Updates points of this mesh to viewspace
	 * 
	 * @param c The Camera object that is looking at this gameobject
	 */
	public void updateMesh(Camera c) {
		//Initialize variables from the mesh
		int[][] faceSet = mesh.faceSet;
		float[] vertices = mesh.vertices;

		//Initialize polygon set
		polygons = new Polygon[faceSet.length];
		for (int i = 0; i < faceSet.length; i++) {
			//coordinates of the points of this polygon
			int[] xcoords = new int[3];
			int[] ycoords = new int[3];

			//loop through each point
			for (int j = 0; j < 3; j++) {
				//Initialize point as a Vector3
				Vector3 pos = new Vector3(vertices[faceSet[i][j] * 3], vertices[1 + faceSet[i][j] * 3],
						vertices[2 + faceSet[i][j] * 3]);
				
				//Model space transformations
				pos = pos.rotate(this.transform.rotation);
				
				//World space transformations
				pos = Vector3.Add(pos, this.transform.position);
				
				//Camera space transformations
				pos = new Vector3(Matrix.multiply(c.CameraT(),pos.homogeneous()));
				
				//Viewspace transformations
				pos = pos.scale(400);
				xcoords[j] = (int) (400 + pos.getX());
				ycoords[j] = (int) (400 - pos.getY());
			}

			//Create new triangle given coordinate points
			polygons[i] = new Polygon(xcoords, ycoords, 3);
		}

		
		//Calculate normals
		for (int j = 0; j < normals.length; j++) {
			normals[j] = this.mesh.normal[j].rotate(this.transform.rotation).rotate(c.rotCenterQ());
			normals[j] = new Vector3(Matrix.multiply(c.perspectiveView(), normals[j].homogeneous()));
		}
	}

	public void draw(Graphics g) {
		final Vector3 facing_direction = new Vector3(0, 0, -1);
		
		for (int i = 0; i < polygons.length; i++) {
			float dot = Vector3.Dot(normals[i], facing_direction);
			if (dot < 0) {
				g.setColor(Color.WHITE);
				g.fillPolygon(polygons[i]);
				g.setColor(Color.BLACK);
				g.drawPolygon(polygons[i]);
			}
		}
	}

}
