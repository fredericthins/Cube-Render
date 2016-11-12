package gameObject;

import math.Quaternion;
import math.Vector3;

public class Transform {
	public Vector3 position = new Vector3(0,0,0);
	public Quaternion rotation = new Quaternion (0,0,0,1);
	public Vector3 forward = new Vector3(0,0,1); 
}
