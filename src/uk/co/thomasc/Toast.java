package uk.co.thomasc;

public class Toast {
	
	int x = 0;
	int y = 0;
	
	int width = Main.screenWidth - 100;
	int height;
	
	int displaytimer = 0;
	
	public Toast() {
		width = Math.round((width - 60) / 36) * 36 + 60;
		x = Main.screenWidth / 2 - width / 2;
		height = (width * 284) / 492;
		height = Math.round((height - 60) / 36) * 36 + 60;
		y = Main.screenHeight / 2 - height / 2;
		
		displaytimer = 1000;
	}
	
	public void draw(Screen screen) {
		screen.drawTexture(74, 10, 12, 12, x + 30, y + 30, width - 60, height - 60);
		
		for (int x = 0; x < (width - 60) / 36; x++) {
			screen.drawTexture(74, 0, 12, 10, this.x + 30 + x * 36, y, 36, 30);
			screen.drawTexture(74, 22, 12, 10, this.x + 30 + x * 36, y + height - 30, 36, 30);
		}
		
		for (int y = 0; y < (height - 60) / 36; y++) {
			screen.drawTexture(64, 10, 10, 12, x, this.y + 30 + y * 36, 30, 36);
			screen.drawTexture(86, 10, 10, 12, x + width - 30, this.y + 30 + y * 36, 30, 36);
		}
		
		screen.drawTexture(64, 0, 10, 10, x, y, 30, 30);
		screen.drawTexture(86, 0, 10, 10, x + width - 30, y, 30, 30);
		screen.drawTexture(64, 22, 10, 10, x, y + height - 30, 30, 30);
		screen.drawTexture(86, 22, 10, 10, x + width - 30, y + height - 30, 30, 30);
		
		displaytimer--;
		if (displaytimer < 1) {
			//Hide me?
		}
	}
}