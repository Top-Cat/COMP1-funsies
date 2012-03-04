package uk.co.thomasc.menu;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import uk.co.thomasc.Main;
import uk.co.thomasc.Screen;

public class About extends Screen {

	@Override
	public void draw() {
		texture.bind();
		
		GL11.glClearColor(216 / 255f, 148 / 255f, 80 / 255f, 1f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		drawTexture(256, 0, 186, 13, 30, 30, 186, 13);
		
		drawTexture(0, 128, 442, 90, 40, 60, Main.screenWidth - 80, (Main.screenWidth - 80) * 45 / 221);
		
		drawTexture(224, 32, 278, 30, 20, Main.screenHeight - 150, Main.screenWidth - 40, (Main.screenWidth - 40) * 15 / 139);
		
		drawTexture(192, 15, 23, 5, Main.screenWidth / 2 - 46, Main.screenHeight - 50, 86, 20);
		drawTexture(224, 0, 11, 14, Main.screenWidth / 2 - 46 - 20, Main.screenHeight - 48, 11, 14);
		
		pollInput();
	}
	
	private void pollInput() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
					new MenuScreen();
				}
			}
		}
	}



	@Override
	public void init() {
		
	}
	
}