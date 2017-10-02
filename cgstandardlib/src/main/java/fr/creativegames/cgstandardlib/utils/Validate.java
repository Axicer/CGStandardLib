package fr.creativegames.cgstandardlib.utils;

public class Validate {
	
	public static boolean notNull(Object o){
		return o != null;
	}
	
	public static boolean notEmpty(String s){
		return !s.equals("");
	}
}
