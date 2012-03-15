package uk.co.thomasc.menu;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import uk.co.thomasc.Screen;

public enum Menu {
	GAME(NewGame.class, "Play", false),
	TRAIN(TrainingGame.class, "Train", false),
	RESUME(Game.class, "Resume", true),
	SAVE(Save.class, "Save", true),
	LOAD(Load.class, "Load", false),
	ABOUT(About.class, "About", false);
	
	private Class<? extends Screen> clazz;
	private String text;
	private boolean requiresGS;
	
	public static Menu[] values = values();
	
	private Menu(Class<? extends Screen> clazz, String text, boolean requiresGS) {
		this.clazz = clazz;
		this.text = text;
		this.requiresGS = requiresGS;
	}
	
	public boolean requiresGS() {
		return requiresGS;
	}
	
	public String getText() {
		return text;
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