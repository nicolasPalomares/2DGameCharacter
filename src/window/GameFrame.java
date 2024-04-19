package window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

/**
 * Main frame class of the "game".
 */

public class GameFrame {
	
	private JFrame frame;
	
	public GameFrame(GamePanel panel) {
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		frame.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowGainedFocus(WindowEvent e) {
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				panel.resetMovement();
			} 
			
		});
	}

}
