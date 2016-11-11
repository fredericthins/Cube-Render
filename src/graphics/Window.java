package graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Window extends JFrame{
	
	JComponent screen;
	
	public Window(JComponent screen){
		this.screen = screen;
		this.setVisible(true);
	}
}
