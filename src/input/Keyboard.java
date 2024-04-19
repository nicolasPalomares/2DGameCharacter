package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class manages character movement though user keyboard inputs.
 * @param aPress - true if user presses 'A' key, false if user relases the key. 
 * @param dPress - true if user presses 'D' key, false if user relases the key. 
 */

public class Keyboard implements KeyListener {
	
	public boolean aPress, dPress;
	
	/**
	 * Reads keyboard input.
	 * If user presses 'A' key, the character moves left.
	 * If user presses 'D' key, the character moves right. 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_A) {
			aPress = true;
		}
		else if(code == KeyEvent.VK_D) {
			dPress = true;
		}
		
	}
	
	/**
	 * Released key event. 
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_A) {
			aPress = false;
		}
		else if(code == KeyEvent.VK_D) {
			dPress = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}	

}
