package core;

import graphics.Screen;
import graphics.Window;

public class Run {
	
	public static Screen screen;//where drawing is done
	public static Window window;//jframe containing screen
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t = new Thread(new Updater());
		t.start();
	}
	
	/**
	 * Update window
	 */
	public static void update(){
		window.Update();
	}
}
