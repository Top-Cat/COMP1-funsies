package uk.co.thomasc.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import uk.co.thomasc.Main;
import uk.co.thomasc.entity.Entity;
import uk.co.thomasc.entity.Flask;
import uk.co.thomasc.entity.Monster;
import uk.co.thomasc.entity.Player;
import uk.co.thomasc.entity.Trap;

public class Game implements MenuChoice {

	private int trapCount = 10;
		
	public Player player = new Player();
	private Monster monster = new Monster();
	public List<Entity> entities = new ArrayList<Entity>();
	private Texture texture;
	
	private static Game instance;
	
	public static Game getInstance() {
		return instance;
	}
	
	public Game() {
		instance = this;
		
		newGrid();
		
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/tile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (!Display.isCloseRequested()) {
			texture.bind();
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
			
			//Center
			for (int x = 0; x < Main.xLen; x += 1) {
				for (int y = 0; y < Main.yLen; y += 1) {
					drawTexture(texture, 0, 0, 64, 64, Main.padding + x * Main.tileSize, Main.padding + y * Main.tileSize, Main.tileSize, Main.tileSize);
				}
			}
			//Edges
			for (int x = 0; x < Main.xLen * Main.tileSize; x += 24) {
				drawTexture(texture, 84, 0, 24, 20, x + Main.padding, 0, 24, 20);
				drawTexture(texture, 84, 44, 24, 20, x + Main.padding, Main.tileSize * Main.yLen + Main.padding, 24, 20);
			}
			for (int y = 0; y < Main.yLen * Main.tileSize; y += 24) {
				drawTexture(texture, 64, 20, 20, 24, 0, y + Main.padding, 20, 24);
				drawTexture(texture, 108, 20, 20, 24, Main.tileSize * Main.xLen + Main.padding, y + Main.padding, 20, 24);
			}
			//Corners
			drawTexture(texture, 64, 0, 20, 20, 0, 0, 20, 20);
			drawTexture(texture, 64, 44, 20, 20, 0, Main.tileSize * Main.yLen + Main.padding, 20, 20);
			drawTexture(texture, 108, 0, 20, 20, Main.tileSize * Main.xLen + Main.padding, 0, 20, 20);
			drawTexture(texture, 108, 44, 20, 20, Main.tileSize * Main.xLen + Main.padding, Main.tileSize * Main.yLen + Main.padding, 20, 20);
			
			drawTexture(texture, player.sprite * 64, 128, 64, 64, Main.padding + player.getX() * Main.tileSize, Main.padding + player.getY() * Main.tileSize, 64, 64);
			if (monster.awake) {
				drawTexture(texture, 64, 64, 64, 64, Main.padding + monster.getX() * Main.tileSize, Main.padding + monster.getY() * Main.tileSize, 64, 64);
			}
			
			pollInput();
			
			Display.update();
		}
	}
	
	private void pollInput() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					player.move(-1, 0);
					player.sprite = 1;
				} else if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					player.move(1, 0);
					player.sprite = 2;
				} else if (Keyboard.getEventKey() == Keyboard.KEY_W) {
					player.move(0, -1);
					player.sprite = 3;
				} else if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					player.move(0, 1);
					player.sprite = 0;
				} else {
					return;
				}
				checkNewPos();
			}
		}
	}
	
	private void checkNewPos() {
		if (monster.awake) {
			monster.move(player);
		}
		for (Entity entity : entities) {
			if (entity.getX() == player.getX() && entity.getY() == player.getY()) {
				if (entity instanceof Trap) {
					monster.awake = true;
					System.out.println("awake");
				} else if (entity instanceof Flask) {
					System.out.println("win!");
				}
			}
		}
	}
	
	private void newGrid() {
		trapCount = Math.min((Main.xLen * Main.yLen) - 2, trapCount);
		
		entities.add(monster);
		monster.randomisePosition(entities);
		
		entities.add(new Flask(entities));
		for (int i = 0; i < trapCount; i++) {
			entities.add(new Trap(entities));
		}
	}
	
	public void drawTexture(Texture texture, float sx, float sy, float sw, float sh, int dx, int dy, int dw, int dh) {
		float Csx = sx / texture.getImageWidth();
		float Csy = sy / texture.getImageHeight();
		float Csw = sw / texture.getImageWidth();
		float Csh = sh / texture.getImageHeight();
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
	
}