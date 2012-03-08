package uk.co.thomasc;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public abstract class Screen {
	
	protected Texture texture;
	private String chars = 	"ABCDEFGHIJKLM" +
							"NOPQRSTUVWXY" +
							"Z0123456789'.";
	private int[] charWidth = {5, 5,  5,  5,  5,  5,  5,  5,  1,  4,  5,  4,  7, 5, 5, 5,  5,   5,  5,  5,  5,  5,  7,  5,  5, 5, 5,  2,  5,  5,  5,  5,  5,  4,  5,  5,  1,  1};
	private int[] charX =     {0, 5, 10, 15, 20, 25, 30, 35, 40, 41, 45, 50, 54, 0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 52, 57, 0, 5, 10, 12, 17, 22, 27, 32, 37, 41, 46, 51, 52};
	
	public Screen() {
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/tile.png"));
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		init();
		
		Main.currentScreen = this;
	}
	
	public void drawTexture(float sx, float sy, float sw, float sh, int dx, int dy, int dw, int dh) {
		float Csx = sx / texture.getTextureWidth();
		float Csy = sy / texture.getTextureHeight();
		float Csw = sw / texture.getTextureWidth();
		float Csh = sh / texture.getTextureHeight();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(Csx, Csy);
		GL11.glVertex2f(dx, dy);
		GL11.glTexCoord2f(Csx+Csw, Csy);
		GL11.glVertex2f(dx+dw, dy);
		GL11.glTexCoord2f(Csx+Csw, Csy+Csh);
		GL11.glVertex2f(dx+dw, dy+dh);
		GL11.glTexCoord2f(Csx, Csy+Csh);
		GL11.glVertex2f(dx, dy+dh);
		GL11.glEnd();
	}
	
	public void drawText(String text, int size, int x, int y, int color) {
		int pos;
		for (char letter : text.toUpperCase().toCharArray()) {
			if ((pos = chars.indexOf(letter)) >= 0) {
				int row = pos > 24 ? 2 : pos > 12 ? 1 : 0;
				GL11.glColor3f((color >> 16) / 255f, (color >> 8 & 0xFF) / 255f, (color & 0xFF) / 255f);
				drawTexture(224 + charX[pos], row * 6, charWidth[pos], 6, x, y, charWidth[pos] * size, 6 * size);
				GL11.glColor3f(1, 1, 1);
				x += (charWidth[pos] + 1) * size;
			} else {
				x += size * 5;
			}
		}
	}
	
	public int getTextWidth(String text, int size) {
		int width = 0;
		int pos;
		for (char letter : text.toUpperCase().toCharArray()) {
			if ((pos = chars.indexOf(letter)) >= 0) {
				width += (charWidth[pos] + 1) * size;
			} else {
				width += size * 5;
			}
		}
		return width - size;
	}
	
	public abstract void draw();
	
	public abstract void init();
	
}