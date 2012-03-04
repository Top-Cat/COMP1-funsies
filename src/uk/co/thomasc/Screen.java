package uk.co.thomasc;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public abstract class Screen {
	
	protected Texture texture;
	
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
	
	public abstract void draw();
	
	public abstract void init();
	
}