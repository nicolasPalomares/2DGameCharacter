package main;

import window.GameLoop;

/**
 * This program recreates a character movement in a 2D videogame. The sprites for the character were taken from:
 * @see https://itch.io/game-assets/free
 * 
 * This is the main class; here, an instance of the game loop is created and executed.
 * 
 * The window runs through a game loop that renders and update the frames and images - {@link window.GameLoop#run() Game Loop} 
 * The character moves left and right (with 'A' and 'D') through keyboard inputs - {@link input.Keyboard Keyboard Inputs}
 * The program works with a panel {@link window.GamePanel Game panel} and a frame {@link window.GameFrame Game frame}
 * The character and map hitboxes were programmed with rectangles and collision. The character hitbox "bounces" whith the walls and the floor.
 * 
 * A Jar file is included in the "/jar" folder inside project's directory.
 * 
 * JDK Version: 1.8
 */

public class Game {
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GameLoop game = new GameLoop();
	}

}
