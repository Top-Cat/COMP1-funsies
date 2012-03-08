package uk.co.thomasc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.thomasc.entity.Entity;
import uk.co.thomasc.entity.Monster;
import uk.co.thomasc.entity.Player;

public class GameState {
	
	private Map<RenderPriority, Set<Entity>> entities = new HashMap<RenderPriority, Set<Entity>>();
	private Player player = new Player();
	private Monster monster = new Monster();
	
	public void registerForRender(RenderPriority priority, Entity object) {
		if (!entities.containsKey(priority)) {
			entities.put(priority, new HashSet<Entity>());
		}
		entities.get(priority).add(object);
	}

	public void unregisterForRender(Entity object) {
		for (RenderPriority priority : entities.keySet()) {
			entities.get(priority).remove(object);
		}
	}

	public Player getPlayer() {
		return player;
	}
	
	public Monster getMonster() {
		return monster;
	}

	public Map<RenderPriority, Set<Entity>> getEntities() {
		return entities;
	}
	
	public void save() {
		try {
			FileWriter fstream = new FileWriter("savefile");
			BufferedWriter out = new BufferedWriter(fstream);
			for (int i = 0; i <= 4; i++) {
				if (getEntities().containsKey(RenderPriority.getRenderPriorityFromId(i))) {
					for (Entity entity : getEntities().get(RenderPriority.getRenderPriorityFromId(i))) {
						entity.save(out);
					}
				}
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void restore() {
		entities.clear();
		try {
			FileReader fstream = new FileReader("savefile");
			BufferedReader in = new BufferedReader(fstream);
			String line;
			List<String> restore = new ArrayList<String>();
			Entity current = null;
			while ((line = in.readLine()) != null) {
				if (line.startsWith("@")) {
					if (current != null) {
						current.restore(restore);
						restore.clear();
					}
					Constructor<?> c = Class.forName(line.substring(1)).getDeclaredConstructor(new Class[] {});
					current = (Entity) c.newInstance();
					if (current instanceof Player) {
						player = (Player) current;
					} else if (current instanceof Monster) {
						monster = (Monster) current;
					}
				} else {
					restore.add(line);
				}
			}
			if (current != null) {
				current.restore(restore);
				restore.clear();
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
}