package gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import math.Matrix;
import math.Vector3;
import model.Mesh;

public class GameObject {

	// GameObject attributes
	Transform transform = new Transform();
	Mesh mesh;
	Polygon[] polygons; 
	Vector3[] normals;
	
	public void init() {
		this.normals = this.mesh.normal;
	}

	public void updateMesh(Camera c) {
		int[][] faceSet = mesh.faceSet;
		float[] vertices = mesh.vertices;

		polygons = new Polygon[faceSet.length];
		for (int i = 0; i < faceSet.length; i++) {
			int[] xcoords = new int[3];
			int[] ycoords = new int[3];

			for (int j = 0; j < 3; j++) {
				Vector3 pos = new Vector3(vertices[faceSet[i][j] * 3], vertices[1 + faceSet[i][j] * 3],
						vertices[2 + faceSet[i][j] * 3]);
				pos = pos.rotate(this.transform.rotation);
				pos = Vector3.Add(pos, this.transform.position);
				pos = new Vector3(Matrix.multiply(c.CameraT(), pos.homogeneous()));
				pos = pos.scale(400);

				xcoords[j] = (int) (400 + pos.getX());
				ycoords[j] = (int) (400 - pos.getY());
			}

			polygons[i] = new Polygon(xcoords, ycoords, 3);
			
			Vector3[] temp_set = new Vector3[normals.length];
			for (int j = 0; j < normals.length; j++) {
				temp_set[i] = normals[i].rotate(this.transform.rotation).rotate(c.rotCenterQ());
				
			}
			
		}

	}

	public void draw(Graphics g) {
		final Vector3 facing_direction = new Vector3(0, 0, -1);
		float dot;
		
		for (int i = 0; i < polygons.length; i++) {
			dot = Vector3.Dot(normals[i], facing_direction);
			if (dot < 0) {
				g.setColor(Color.WHITE);
				g.fillPolygon(polygons[i]);
				g.setColor(Color.BLACK);
				g.drawPolygon(polygons[i]);
			}
		}
	}

}
