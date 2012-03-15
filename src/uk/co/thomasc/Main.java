package uk.co.thomasc;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import uk.co.thomasc.menu.MenuScreen;

public class Main {
	
	public static int xLen = 7;
	public static int yLen = 5;
	public static int tileSize = 64;
	public static int padding = 20;
	
	public static int screenWidth;
	public static int screenHeight;
	
	public static Screen currentScreen;
	
	private static GameState gamestate;
	
	public static void main(String[] args) {
		try {
			screenWidth = xLen * tileSize + padding*2;
			screenHeight = yLen * tileSize + padding*2;
			Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
			Display.setTitle("Monster");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GLinit();
		
		new MenuScreen();
		
		while (!Display.isCloseRequested()) {
			currentScreen.draw();
			Display.update();
			Display.sync(30);
		}
		
		Display.destroy();
	}
	
	public static void setGamestate(GameState gamestate) {
		Main.gamestate = gamestate;
	}
	
	public static GameState getGamestate() {
		return gamestate;
	}
	
	private static void GLinit() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
        
        GL11.glViewport(0, 0, screenWidth, screenHeight);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, screenWidth, screenHeight, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
}