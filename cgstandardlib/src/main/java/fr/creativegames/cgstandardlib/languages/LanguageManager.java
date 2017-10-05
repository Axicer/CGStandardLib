package fr.creativegames.cgstandardlib.languages;

import java.util.HashMap;
import java.util.Map;

import fr.creativegames.cgstandardlib.exceptions.ErrorHandler;
import fr.creativegames.cgstandardlib.languages.error.LanguageAlreadyRegisteredError;
import fr.creativegames.cgstandardlib.languages.error.LanguageNotRegisteredError;

/**
 *  Main language manager
 * @author Axicer
 */
public class LanguageManager {

	private static Map<String, AbstractLanguage> langs;
	
	static{
		langs = new HashMap<String, AbstractLanguage>();
	}

    /**
     * Register a language and bind an ID
     * @param lang {@link AbstractLanguage} to register
     * @param ID {@link String} id to bind
     */
	public static void registerLanguage(AbstractLanguage lang, String ID){
		if(langs.containsKey(ID)){
			ErrorHandler.throwError(new LanguageAlreadyRegisteredError());
			return;
		}
		langs.put(ID, lang);
	}

    /**
     * Unregister a language by the ID
     * @param ID {@link String} language's ID
     */
	public static void unregisterLanguage(String ID){
		if(!langs.containsKey(ID)){
			ErrorHandler.throwError(new LanguageNotRegisteredError());
			return;
		}
		langs.remove(ID);
	}

    /**
     * Return a language by the ID
     * @param ID {@link String} the language's ID
     * @return AbstractLanguage involved
     */
	public AbstractLanguage getLanguage(String ID){
	    return langs.get(ID);
    }
}
