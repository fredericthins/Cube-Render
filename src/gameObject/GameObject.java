package gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import math.Matrix;
import math.Vector3;
import model.Mesh;

public class GameObject {

	// GameObject attributes
	public Transform transform = new Transform();
	Mesh mesh;
	Polygon[] polygons; 		//Set of polygons for java to render (triangles only)
	Vector3[] normals;
	Vector3[] facecenters;
	
	Vector3 facingdir;
	
	public void init() {
		//Initialize normal and facing direction
		this.normals = this.mesh.normal.clone();
		facingdir = new Vector3(0,0,0);
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
		facecenters = new Vector3[faceSet.length];
		for (int i = 0; i < faceSet.length; i++) {
			//Do clipping check
			if(this.backfaceCull(c, i)){
				//coordinates of the points of this polygon
				int[] xcoords = new int[3];
				int[] ycoords = new int[3];
				int[] zcoords = new int[3];
				
				//Center of this polygon face
				facecenters[i] = new Vector3(0,0,0);
	
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
					pos = c.CameraT(pos);
					
					//Viewspace transformations
					pos = pos.scale(400);
					
					//Sum up points of this polygon
					facecenters[i] = Vector3.Add(facecenters[i], pos);
					
					//Convert position to pixel data
					xcoords[j] = (int) (400 + pos.getX());
					ycoords[j] = (int) (400 - pos.getY());
					zcoords[j] = (int) (pos.getZ());
				}
	
				//Create new triangle given coordinate points
				polygons[i] = new Polygon(xcoords, ycoords, 3);
				
				//Divide by 3 to find average of points
				facecenters[i] = facecenters[i].scale(1/3f);
			}
		}
	}
	
	/**
	 * Computes backface culling calculation
	 * <ol>
	 * 	<li>Compute the facing direction:</li>
	 * 		<ul><li>Object position - Camera position</li></ul>
	 * 	<li>Calculate normal vector of this face: </li>
	 * 		<ul><li>Rotate this vector by object rotation, then camera center rotation</li></ul>
	 * 	<li>Calculate dot product of facing direction and normal vector</li>
	 * 	<li>Compute boolean result:</li>
	 * 		<ul><li>If the dot product is <0, it means the face is facing towards us, so render it.<br>
	 * 		If the dot product is >0, it means the face is facing away from us, so don't render it</li></ul>
	 * </ol>
	 * 
	 * @param c Camera object
	 * @param i Index of face in faceset
	 * @return if this face passed the cull (if it can render)
	 */
	boolean backfaceCull(Camera c, int i){
		//Calculate facing direction
		facingdir = Vector3.Add(this.transform.position, c.transform.position.scale(-1));
		
		//Calculate normal
		Vector3 nrm = this.mesh.normal[i].rotate(this.transform.rotation).rotate(c.rotCenterQ());
		normals[i] = nrm.scale(50);
		
		//Return comparison of dot product to 0
		return Vector3.Dot(nrm, facingdir) < 0;
	}

	/**
	 * Draws this gameobject
	 * 
	 * @param g Java's graphics object
	 */
	public void draw(Graphics g) {
		for (int i = 0; i < polygons.length; i++) {
			if(polygons[i]!=null){
				//Face and normal point calculation
				int facex = (int)facecenters[i].getX()+400;
				int facey = 400-(int)facecenters[i].getY();
				int normalx = facex - (int)this.normals[i].getX();
				int normaly = facey + (int)this.normals[i].getY();
//				int normalx = 400+(int)this.normals[i].getX();
//				int normaly = 400-(int)this.normals[i].getY();
				
				//Draw polygon fill and outline
				g.setColor(Color.WHITE);
				g.fillPolygon(polygons[i]);
				g.setColor(Color.BLACK);
				g.drawPolygon(polygons[i]);
				
				//Draw normal line
				g.setColor(Color.BLUE);
				g.drawLine(facex, facey, normalx, normaly);
	
				//Draw normal start and end points
				g.setColor(Color.GREEN);
				g.fillOval(facex, facey, 10,10);
				g.setColor(Color.RED);
				g.fillOval(normalx, normaly, 10,10);
			}
		}
	}

}
