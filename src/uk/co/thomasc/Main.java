package uk.co.thomasc;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import uk.co.thomasc.menu.Game;

public class Main {
	
	public static int xLen = 10;
	public static int yLen = 7;
	public static int tileSize = 64;
	public static int padding = 20;
	
	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(xLen * tileSize + padding*2, yLen * tileSize + padding*2));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GLinit();
		
		new Game();
		Display.destroy();
	}
	
	private static void GLinit() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
        
        GL11.glViewport(0, 0, xLen * tileSize + padding*2, yLen * tileSize + padding*2);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, xLen * tileSize + padding*2, yLen * tileSize + padding*2, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
}