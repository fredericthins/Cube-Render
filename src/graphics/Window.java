package graphics;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Window extends JFrame{
	
	Screen screen;
	
	/**
	 * initialize the window with certain parameter
	 */
	private void init(){
		Color back = new Color(0x484446);
		this.setBackground(back);
		this.setSize(800, 800);
		this.repaint();
		
	}
	
	public Window(Screen screen){
		init();
		this.screen = screen;
		this.add(this.screen, BorderLayout.CENTER);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	
	}
	public void Update(){
		screen.update();
	}
}
