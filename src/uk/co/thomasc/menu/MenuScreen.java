package uk.co.thomasc.menu;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import uk.co.thomasc.Main;
import uk.co.thomasc.Screen;

public class MenuScreen extends Screen {
	
	private int selected;
	
	@Override
	public void draw() {
		texture.bind();
		
		GL11.glClearColor(216 / 255f, 148 / 255f, 80 / 255f, 1f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		drawTexture(0, 64, 200, 42, Main.screenWidth / 2 - 200, 50, 400, 84);
		
		//Menu options
		int i = 0;
		for (Menu menu : Menu.values()) {
			if (i == selected) {
				drawTexture(224, 0, 11, 14, Main.screenWidth / 2 - menu.getWidth() * 2 - 20, (5 + i) * 50 + 2, 11, 14);
			}
			drawTexture(192, i * 5, menu.getWidth(), 5, Main.screenWidth / 2 - menu.getWidth() * 2, (5 + i++) * 50, menu.getWidth() * 4, 20);
		}
		
		pollInput();
	}
	
	private void pollInput() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_W) {
					selected = Math.max(selected - 1, 0);
				} else if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					selected = Math.min(selected + 1, Menu.values().length - 1);
				} else if (Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
					Menu.values()[selected].createNew();
				}
			}
		}
	}
	
	@Override
	public void init() {
		
	}
	
}