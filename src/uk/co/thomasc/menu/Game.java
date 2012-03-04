package uk.co.thomasc.menu;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import uk.co.thomasc.Main;
import uk.co.thomasc.Screen;
import uk.co.thomasc.Toast;
import uk.co.thomasc.entity.Entity;
import uk.co.thomasc.entity.Flask;
import uk.co.thomasc.entity.Monster;
import uk.co.thomasc.entity.Player;
import uk.co.thomasc.entity.Trap;

public class Game extends Screen {

	private int trapCount;
	public Player player;
	private Monster monster;
	public List<Entity> entities;
	private static Game instance;
	private Toast toast;
	
	public static Game getInstance() {
		return instance;
	}
	
	@Override
	public void init() {
		instance = this;
		toast = new Toast();
		trapCount = 10;
		entities = new ArrayList<Entity>();
		player = new Player();
		entities.add(player);
		monster = new Monster();
		entities.add(monster);
		newGrid();
	}
	
	@Override
	public void draw() {
		texture.bind();
		
		//Center
		for (int x = 0; x < Main.xLen; x += 1) {
			for (int y = 0; y < Main.yLen; y += 1) {
				drawTexture(0, 0, 32, 32, Main.padding + x * Main.tileSize, Main.padding + y * Main.tileSize, Main.tileSize, Main.tileSize);
			}
		}
		//Edges
		for (int x = 0; x < Main.xLen * Main.tileSize; x += 24) {
			drawTexture(42, 0, 12, 10, x + Main.padding, 0, 24, 20);
			drawTexture(42, 22, 12, 10, x + Main.padding, Main.tileSize * Main.yLen + Main.padding, 24, 20);
		}
		for (int y = 0; y < Main.yLen * Main.tileSize; y += 24) {
			drawTexture(32, 10, 10, 12, 0, y + Main.padding, 20, 24);
			drawTexture(54, 10, 10, 12, Main.tileSize * Main.xLen + Main.padding, y + Main.padding, 20, 24);
		}
		//Corners
		drawTexture(32, 0, 10, 10, 0, 0, 20, 20);
		drawTexture(32, 22, 10, 10, 0, Main.tileSize * Main.yLen + Main.padding, 20, 20);
		drawTexture(54, 0, 10, 10, Main.tileSize * Main.xLen + Main.padding, 0, 20, 20);
		drawTexture(54, 22, 10, 10, Main.tileSize * Main.xLen + Main.padding, Main.tileSize * Main.yLen + Main.padding, 20, 20);

		for (Entity entity : entities) {
			entity.draw(this);
		}
		
		toast.draw(this);
		
		pollInput();
	}
	
	private void pollInput() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					player.move(-1, 0);
				} else if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					player.move(1, 0);
				} else if (Keyboard.getEventKey() == Keyboard.KEY_W) {
					player.move(0, -1);
				} else if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					player.move(0, 1);
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
				if (entity instanceof Trap && !((Trap) entity).sprung) {
					monster.awake = true;
					((Trap) entity).sprung = true;
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
	
}