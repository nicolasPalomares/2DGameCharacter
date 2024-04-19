package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import domain.Character;
import input.Keyboard;

import static window.GameLoop.GAME_WIDTH;
import static window.GameLoop.GAME_HEIGH;

/**
 * This class manages the panel, the keyboard inputs, animates and updates the character sprites. 
 */

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private Keyboard keyboard;
	private Character character;
	
	public GamePanel() {
		
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		character = new Character(keyboard);
		setFocusable(true);
		
		this.setBackground(Color.gray);
		setPanelSize();
		
	}
	
	public void updateGame() {
		character.animCharacter();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		character.drawCharacter(g);
		
	}
	
	public void setPanelSize() {
		
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGH);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		System.out.println(GAME_WIDTH + "x" + GAME_HEIGH);
		
	}
	
	public void resetMovement() {
		character.resetDir();
	}
	
}
