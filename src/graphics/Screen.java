package graphics;

import java.awt.Graphics;

import javax.swing.JComponent;

public class Screen extends JComponent{

	@Override
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		
	}
	
	/**
	 *  Update the screen
	 */
	public void update(){
		repaint();
	}
}
