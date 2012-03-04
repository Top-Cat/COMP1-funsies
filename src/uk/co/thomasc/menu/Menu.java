package uk.co.thomasc.menu;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import uk.co.thomasc.Screen;

public enum Menu {
	GAME(Game.class, 22),
	TRAIN(Game.class, 25),
	ABOUT(About.class, 29);
	
	private Class<? extends Screen> clazz;
	private int width;
	
	private Menu(Class<? extends Screen> clazz, int width) {
		this.clazz = clazz;
		this.width = width;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Screen createNew() {
		try {
			Constructor<? extends Screen> c = clazz.getDeclaredConstructor(new Class[] {});
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
	
}