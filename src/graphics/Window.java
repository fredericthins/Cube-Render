package graphics;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	Screen screen;
	
	/**
	 * initialize window, add screen, make window visible
	 * @param screen-screen object to display in window
	 */
	public Window(Screen screen){
		init();
		this.screen = screen;
		this.add(this.screen, BorderLayout.CENTER);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	
	}
	
	/**
	 * initialize the window with certain parameter
	 */
	private void init(){
		Color back = new Color(0x484446);
		this.setBackground(back);
		this.setSize(800, 800);
		this.repaint();
		
	}
	
	/**
	 * Run every 16 ms, will tick the changes and update the screen
	 */
	public void Update(){
		screen.update();
	}
}
