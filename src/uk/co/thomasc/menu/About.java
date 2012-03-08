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
		
		drawText("Monster Game", 3, 30, 30, 0x724D2B);
		
		drawText("Based on the concept of the prerelease", 2, Main.screenWidth / 2 - getTextWidth("Based on the concept of the prerelease", 1), 60, 0x91633C);
		drawText("by AQA for COMP1 2012. I'm not taking the", 2, Main.screenWidth / 2 - getTextWidth("by AQA for COMP1 2012. I'm not taking the", 1), 75, 0x91633C);
		drawText("exam but I thought it would be fun to make", 2, Main.screenWidth / 2 - getTextWidth("exam but I thought it would be fun to make", 1), 90, 0x91633C);
		drawText("this into something that is actually", 2, Main.screenWidth / 2 - getTextWidth("this into something that is actually", 1), 105, 0x91633C);
		drawText("playable.", 2, Main.screenWidth / 2 - getTextWidth("playable.", 1), 120, 0x91633C);
		
		drawText("Check out spout.org to see", 3, Main.screenWidth / 2 - getTextWidth("Check out spout.org to see", 3) / 2, Main.screenHeight - 150, 0x91633C);
		drawText("what I normally work on", 3, Main.screenWidth / 2 - getTextWidth("what I normally work on", 3) / 2, Main.screenHeight - 130, 0x91633C);
		
		drawText("back", 4, Main.screenWidth / 2 - getTextWidth("back", 2), Main.screenHeight - 50, 0x7C532E);
		drawTexture(192, 0, 11, 14, Main.screenWidth / 2 - 46 - 20, Main.screenHeight - 48, 11, 14);
		
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