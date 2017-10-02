package fr.creativegames.cgstandardlib.languages;

import java.util.HashMap;
import java.util.Map;

import fr.creativegames.cgstandardlib.exceptions.ErrorHandler;
import fr.creativegames.cgstandardlib.languages.error.LanguageAlreadyRegisteredError;

public class LanguageManager {

	private static Map<String, AbstractLanguage> langs;
	
	static{
		langs = new HashMap<String, AbstractLanguage>();
	}
	
	public static void registerLanguage(AbstractLanguage lang, String ID){
		if(langs.containsKey(ID)){
			ErrorHandler.throwError(new LanguageAlreadyRegisteredError());
			return;
		}
		langs.put(ID, lang);
	}
	
	public static void unregisterLanguage(String ID){
		
	}
}
