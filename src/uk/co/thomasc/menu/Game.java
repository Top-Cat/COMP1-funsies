package uk.co.thomasc.menu;

import org.lwjgl.input.Keyboard;

import uk.co.thomasc.Main;
import uk.co.thomasc.RenderPriority;
import uk.co.thomasc.Screen;
import uk.co.thomasc.Toast;
import uk.co.thomasc.entity.Entity;
import uk.co.thomasc.entity.Flask;
import uk.co.thomasc.entity.Trap;

public class Game extends Screen {

	private static Game instance;
	public Toast toast;
	
	public boolean trainingMode;
	
	public static Game getInstance() {
		return instance;
	}
	
	@Override
	public void init() {
		instance = this;
		toast = new Toast();
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

		for (int i = 0; i <= 4; i++) {
			if (Main.getGamestate().getEntities().containsKey(RenderPriority.getRenderPriorityFromId(i))) {
				for (Entity obj : Main.getGamestate().getEntities().get(RenderPriority.getRenderPriorityFromId(i))) {
					obj.draw(this);
				}
			}
		}
		
		toast.draw(this);
		
		pollInput();
	}
	
	private void pollInput() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (!toast.visible) {
					if (Keyboard.getEventKey() == Keyboard.KEY_A) {
						Main.getGamestate().getPlayer().move(-1, 0);
					} else if (Keyboard.getEventKey() == Keyboard.KEY_D) {
						Main.getGamestate().getPlayer().move(1, 0);
					} else if (Keyboard.getEventKey() == Keyboard.KEY_W) {
						Main.getGamestate().getPlayer().move(0, -1);
					} else if (Keyboard.getEventKey() == Keyboard.KEY_S) {
						Main.getGamestate().getPlayer().move(0, 1);
					} else if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
						new MenuScreen();
						return;
					}
					checkNewPos();
				} else {
					if (Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
						Main.setGamestate(null);
						new MenuScreen();
					}
				}
			}
		}
	}
	
	private void checkNewPos() {
		if (Main.getGamestate().getMonster().awake) {
			Main.getGamestate().getMonster().move(Main.getGamestate().getPlayer());
		}
		for (int i = 0; i <= 4; i++) {
			if (Main.getGamestate().getEntities().containsKey(RenderPriority.getRenderPriorityFromId(i))) {
				for (Entity entity : Main.getGamestate().getEntities().get(RenderPriority.getRenderPriorityFromId(i))) {
					if (entity.getX() == Main.getGamestate().getPlayer().getX() && entity.getY() == Main.getGamestate().getPlayer().getY()) {
						if (entity instanceof Trap && !((Trap) entity).sprung) {
							Main.getGamestate().getMonster().awake = true;
							((Trap) entity).sprung = true;
						} else if (entity instanceof Flask) {
							Game.getInstance().toast.setData("You win!", 96, 0, 32);
							Game.getInstance().toast.show();
						}
					}
				}
			}
		}
	}
	
}