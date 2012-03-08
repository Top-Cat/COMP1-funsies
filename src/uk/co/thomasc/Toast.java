package uk.co.thomasc;

public class Toast {
	
	private int x = 0;
	private int y = 0;
	private int width = Main.screenWidth - 100;
	private int height;
	
	private String text = "";
	
	public boolean visible = false;
	
	private int spriteX;
	private int spriteY;
	private int spriteSize;
	
	public Toast() {
		setWidth(width);
	}
	
	public void setWidth(int newWidth) {
		width = newWidth;
		
		width = Math.round((width - 60) / 36) * 36 + 60;
		x = Main.screenWidth / 2 - width / 2;
		height = (width * 284) / 492;
		height = Math.round((height - 60) / 36) * 36 + 60;
		y = Main.screenHeight / 2 - height / 2;
	}
	
	public void setData(String text, int x, int y, int size) {
		this.text = text;
		spriteX = x;
		spriteY = y;
		spriteSize = size;
	}
	
	public void show() {
		visible = true;
	}
	
	public void draw(Screen screen) {
		if (visible) {
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
			
			int size = (int) Math.pow(2, Math.floor(Math.log(height - 90) / Math.log(2)));
			screen.drawTexture(spriteX, spriteY, spriteSize, spriteSize, x + width / 2 - size / 2, y + 50, size, size);
			screen.drawText(text, 4, x + width / 2 - screen.getTextWidth(text, 2), y + height - 50, 0x7C532E);
		}
	}
}
