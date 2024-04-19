package domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import window.GameLoop;

/**
 * This class takes care of the map bounds and hitbox. 
 */

public class MapCollision {

	private static final int xLeftWall = GameLoop.TILES_SIZE,
							 yLeftWall = GameLoop.TILES_SIZE * 2,
							 wLeftWall = GameLoop.TILES_SIZE,
							 hLeftWall = GameLoop.TILES_SIZE * 11;

	private static final int xFloor = GameLoop.TILES_SIZE * 2,
			 			     yFloor = 624,
			 			     wFloor = GameLoop.TILES_SIZE * 23,
							 hFloor = GameLoop.TILES_SIZE;

	private static final int xRightWall = GameLoop.TILES_SIZE * 25,
							 yRightWall = GameLoop.TILES_SIZE * 2,
							 wRightWall = GameLoop.TILES_SIZE,
							 hRightWall = GameLoop.TILES_SIZE * 11;

	public void drawMapHitbox(Graphics g) {

		leftWallHitbox(g);
		floorHitbox(g);
		rightWallHitbox(g);

	}

	public static void leftWallHitbox(Graphics g) {
		g.setColor(Color.PINK);
		g.drawRect(xLeftWall, yLeftWall, wLeftWall, hLeftWall);
	}

	public static void floorHitbox(Graphics g) {
		g.setColor(Color.PINK);
		g.drawRect(xFloor, yFloor, wFloor, hFloor);
	}

	public static void rightWallHitbox(Graphics g) {
		g.setColor(Color.PINK);
		g.drawRect(xRightWall, yRightWall, wRightWall, hRightWall);
	}

	public Rectangle leftWallBounds() {
		return new Rectangle(xLeftWall, yLeftWall, wLeftWall, hLeftWall);
	}

	public Rectangle floorBounds() {
		return new Rectangle(xFloor, yFloor, wFloor, hFloor);
	}

	public Rectangle rightWallBounds() {
		return new Rectangle(xRightWall, yRightWall, wRightWall, hRightWall);
	}
	
	public String setCollision(Character character) {
		
		String s = "";
		
		if( character.characterBounds().intersects(leftWallBounds()) ) {
			s = "leftWall";
		}
		else if ( character.characterBounds().intersects(floorBounds()) ) {
			s = "floor";
		}
		else if ( character.characterBounds().intersects(rightWallBounds()) ) {
			s = "rightWall";
		}
		
		return s;
		
	}

}
