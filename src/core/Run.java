package core;

import graphics.Screen;
import graphics.Window;

public class Run {
	
	public static Screen screen;
	public static Window window;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t = new Thread(new Updater());
		t.start();
	}
	
	public static void update(){
		window.Update();
	}
}
