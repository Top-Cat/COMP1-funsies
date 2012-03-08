package uk.co.thomasc.entity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import uk.co.thomasc.Main;
import uk.co.thomasc.RenderPriority;
import uk.co.thomasc.Screen;

public abstract class Entity {
	
	private int x;
	private int y;
	private Random random = new Random();
	
	public void move(int x, int y) {
		this.x = Math.min(Math.max(0, this.x + x), Main.xLen - 1);
		this.y = Math.min(Math.max(0, this.y + y), Main.yLen - 1);
	}
	
	public void randomisePosition() {
		Map<RenderPriority, Set<Entity>> entities = Main.getGamestate().getEntities();
		
		x = 0;
		y = 0;
		boolean found = true;
		while (found) {
			found = false;
			x = random.nextInt(Main.xLen);
			y = random.nextInt(Main.yLen);
			for (int i = 0; i <= 4; i++) {
				if (entities.containsKey(RenderPriority.getRenderPriorityFromId(i))) {
					for (Entity entity : entities.get(RenderPriority.getRenderPriorityFromId(i))) {
						if (!entity.equals(this) && entity.getX() == x && entity.getY() == y) {
							found = true;
						}
					}
				}
			}
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public abstract void draw(Screen screen);

	public void save(BufferedWriter out) throws IOException {
		out.write("@" + this.getClass().getCanonicalName() + "\n");
		out.write(x + "\n");
		out.write(y + "\n");
		Map<RenderPriority, Set<Entity>> entities = Main.getGamestate().getEntities();
		RenderPriority renderPriority = RenderPriority.Normal;
		for (int i = 0; i <= 4; i++) {
			if (entities.containsKey(RenderPriority.getRenderPriorityFromId(i))) {
				if (entities.get(RenderPriority.getRenderPriorityFromId(i)).contains(this)) {
					renderPriority = RenderPriority.getRenderPriorityFromId(i);
				}
			}
		}
		out.write(renderPriority.getId() + "\n");
	}

	public void restore(List<String> in) {
		x = Integer.parseInt(in.remove(0));
		y = Integer.parseInt(in.remove(0));
		Main.getGamestate().registerForRender(RenderPriority.getRenderPriorityFromId(Integer.parseInt(in.remove(0))), this);
	}
	
}
