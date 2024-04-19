package window;

import java.awt.Graphics;

@SuppressWarnings("unused")
public class GameLoop implements Runnable {
	
	private GameFrame frame;
	private GamePanel panel;
	private Thread thread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1.5f; 
	public final static int TILES_WIDTH = 27; //default: 26
	public final static int TILES_HEIGH = 15; //default: 14
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_WIDTH;
	public final static int GAME_HEIGH = TILES_SIZE * TILES_HEIGH;
	
	public GameLoop() {
		
		panel = new GamePanel();
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		frame = new GameFrame(panel);
		startLoop();
		
	}
	
	private void startLoop() {
		
		thread = new Thread(this);
		thread.start();
		
	}
	
	public void update() {
		panel.updateGame();
	}

	/**
	 * This method implements the game loop for running the game.
	 * It continuously updates and renders the game while maintaining a target FPS (frames per second)
	 * and UPS (updates per second) rate.
	 * 
	 * The game loop consists of updating the game logic and rendering the graphics.
	 * 
	 * @see #update() This method is called to update the game logic.
	 * @see javax.swing.JPanel#repaint() This method is called to repaint the game panel.
	 * 
	 * The method calculates the time elapsed since the last update and frame rendering
	 * and adjusts the update and render frequencies accordingly to maintain a consistent game speed.
	 * It also includes a FPS (frames per second) counter to monitor the rendering performance
	 * and a UPS (updates per second) counter to monitor the game logic performance.
	 */
	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
		
		long lastTime = System.nanoTime();
		int frames = 0;
		int updates = 0;
		long lastRevision = System.currentTimeMillis();
		
		double deltaF = 0;
		double deltaU = 0;
		
		// Main game loop
		while(true) {
			long currentTime = System.nanoTime();
			
			// Calculate delta time for frame rendering and update
			deltaF += (currentTime - lastTime) / timePerFrame;
			deltaU += (currentTime - lastTime) / timePerUpdate;
			lastTime = currentTime;
			
			// Update the game logic if it's time for an update
			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			
			// Render the frame if it's time for rendering
			if(deltaF >= 1) {
				panel.repaint();
				frames++;
				deltaF--;
			}
			
			// Update the FPS and UPS counters every second
			if(System.currentTimeMillis() - lastRevision >= 1000) {
				lastRevision = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | " + "UPS: " + updates);
				frames = 0;
				updates = 0;
			}
			
		}
		
	}

}
