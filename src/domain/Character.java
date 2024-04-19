package domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import input.Keyboard;

/**
 * This is the main character class. It loads the sprites for every character animation using BufferedImage and ArrayList
 * and it manages the movement and collision of the character hitbox. 
 */

public class Character {
	
	private Keyboard keyboard;
	private MapCollision mapCollision;
	
	private int  speed;
	private float x, y;
	
	private String direction;
	private int aniTick, aniIndex, aniSpeed = 30;
	private boolean last;
	private BufferedImage spriteIdleR, spriteIdleL, spriteRunR, spriteRunL, img;
	
	// Idle animation sprites
	private ArrayList<BufferedImage> idleR = new ArrayList<>();
	private ArrayList<BufferedImage> idleL = new ArrayList<>();
	
	// Run animation sprites
	private ArrayList<BufferedImage> runR = new ArrayList<>();
	private ArrayList<BufferedImage> runL = new ArrayList<>();
	
	public Character(Keyboard keyboard) {
		
		importSprites();
		this.keyboard = keyboard;
		mapCollision = new MapCollision();
		setDefaultValues();
		
	}
	
	public void setDefaultValues() {
		
		x = 300;
		y = 468.9549f;
		speed = 2;
		direction = "";
		last = true;
		
	}
        
	public void importSprites() {
		
		try {
			spriteIdleR = ImageIO.read(getClass().getResourceAsStream("/resources/idleR.png"));
			spriteIdleL = ImageIO.read(getClass().getResourceAsStream("/resources/idleL.png"));
			spriteRunR = ImageIO.read(getClass().getResourceAsStream("/resources/runR.png"));
			spriteRunL = ImageIO.read(getClass().getResourceAsStream("/resources/runL.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void animCharacter() {
		
		loadAnimations();
		action();
		ArrayList<BufferedImage> ani = animate();
		updateAnimation(ani);
		
	}
	
	/**
	 * If you want to visualize the character hitbox, remove the commentary on 'drawHitbox(g)'
	 * method calling. 
	 */
	public void drawCharacter(Graphics g) {
		
		g.drawImage(img, (int) x, (int) y, 240, 167, null);
		//drawHitbox(g);
		mapCollision.drawMapHitbox(g);
		
	}
	
	/**
	 * Resets the direction of the character. 
	 */
	public void resetDir() {
		keyboard.aPress = false;
		keyboard.dPress = false;
	}
	
	/**
	 * This method manages the character movement and collision with the map.
	 */
	public void action() {
		
		// Movement
		if(keyboard.dPress) {
			direction = "runR";
			x += speed;
			last = true;
		}
		else if(keyboard.aPress) {
			direction = "runL";
			x -= speed;
			last = false;
		}
		else if(!keyboard.dPress && last) {
			direction = "idleR";
		}
		else if(!keyboard.aPress && !last) {
			direction = "idleL";
		}
		
		// Character collision with map bounds
		String col = mapCollision.setCollision(this);
		switch(col) {
		case "leftWall":
			x += speed;
			break;
		case "floor":
			y -= speed;
			break;
		case "rightWall":
			x -= speed;
			break;
		}
		
	}
	
	/**
	 * This method animates the "run left", "run right", "idle left" and "idle right"
	 * depending on the character actual position and user input. 
	 */
	public ArrayList<BufferedImage> animate() {
		
		ArrayList<BufferedImage> al = new ArrayList<>();
		
		switch(direction) {
		case "runR":
			al.addAll(runR);
			break;
		case "runL":
			al.addAll(runL);
			break;
		case "idleR":
			al.addAll(idleR);
			break;
		case "idleL":
			al.addAll(idleL);
			break;
		}
		
		return al;
		
	}
	
	/**
	 * This method updates the character animation, iterating through the ArrayList
	 * containing the sprites. The animation speed can be changed with the @param aniSpeed. 
	 */
	public void updateAnimation(ArrayList<BufferedImage> arr) {
		
		aniTick++;
		
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			
			if(aniIndex >= arr.size()) {
				aniIndex = 0;
			}
			
			img = arr.get(aniIndex);
		}
		
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Rectangle characterBounds() {
		return new Rectangle((int) x + 95, (int) y + 77, 50, 80);
	}

	public void drawHitbox(Graphics g) {
		
		g.setColor(Color.PINK);
		g.drawRect((int) x + 95, (int) y + 77, 50, 80);
		
	}
	
	/**
	 * This method loads the character animations.
	 * It cuts every sprite of the main .png image and adds them to the sprite array. 
	 */
	public void loadAnimations() {
		
		for(int i = 0; i < 8; i++) {
			idleR.add(spriteIdleR.getSubimage(i * 160, 0, 160, 111));
		}
		
		
		for(int i = 0; i < 8; i++) {
			idleL.add(spriteIdleL.getSubimage(1120 - (i * 160), 0, 160, 111));
		}
		
		
		for(int i = 0; i < 8; i++) {
			runR.add(spriteRunR.getSubimage(i * 160, 0, 160, 111));
		}
		
		
		for(int i = 0; i < 8; i++) {
			runL.add(spriteRunL.getSubimage(1120 - (i * 160), 0, 160, 111));
		}
		
	}

}
