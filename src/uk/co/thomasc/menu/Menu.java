package uk.co.thomasc.menu;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public enum Menu {
	GAME((byte) 49, "Start new game", Game.class);
	
	private String menuText;
	private Class<? extends MenuChoice> clazz;
	private byte id;
	
	private Menu(byte id, String menuText, Class<? extends MenuChoice> clazz) {
		this.menuText = menuText;
		this.clazz = clazz;
		this.id = id;
	}

	public String getText() {
		return menuText;
	}
	
	private byte getId() {
		return id;
	}
	
	public MenuChoice createNew() {
		try {
			Constructor<? extends MenuChoice> c = clazz.getDeclaredConstructor(new Class[] {});
			return c.newInstance();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
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
		return null;
	}
	
	private static HashMap<Byte, Menu> map = new HashMap<Byte, Menu>();

	static {
		for (Menu ch : values()) {
			map.put(ch.getId(), ch);
		}
	}

	public static Menu getFromId(byte id) {
		return map.get(id);
	}
	
}