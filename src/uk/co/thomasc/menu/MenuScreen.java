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
		
		drawTexture(0, 64, 200, 42, Main.screenWidth / 2 - 200, 30, 400, 84);
		
		//Menu options
		int i = 0;
		for (Menu menu : Menu.values()) {
			if (i == selected) {
				drawTexture(192, 0, 11, 14, Main.screenWidth / 2 - getTextWidth(menu.getText(), 4) / 2 - 20, Main.screenHeight / 2 + (i * 35) - 38, 11, 14);
			}
			drawText(menu.getText(), 4, Main.screenWidth / 2 - getTextWidth(menu.getText(), 4) / 2, Main.screenHeight / 2 + (i++ * 35) - 40, (menu.requiresGS() && Main.getGamestate() == null) ? 0xA06B3D : 0x7C532E);
		}
		
		pollInput();
	}
	
	private void pollInput() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_W) {
					for (int i = selected - 1; i >= 0; i--) {
						if (!Menu.values()[i].requiresGS() || Main.getGamestate() != null) {
							selected = i;
							break;
						}
					}
				} else if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					for (int i = selected + 1; i < Menu.values().length; i++) {
						if (!Menu.values()[i].requiresGS() || Main.getGamestate() != null) {
							selected = i;
							break;
						}
					}
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