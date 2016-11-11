package graphics;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Window extends JFrame{
	
	JComponent screen;
	
	public void init(){
		Color back = new Color(0x484446);
		this.setBackground(back);
		this.setSize(800, 800);
		this.repaint();
	}
	
	public Window(JComponent screen){
		init();
		this.screen = screen;
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
}
