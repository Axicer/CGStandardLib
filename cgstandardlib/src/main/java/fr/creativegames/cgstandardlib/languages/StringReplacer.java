package fr.creativegames.cgstandardlib.languages;

import fr.creativegames.cgstandardlib.exceptions.ErrorHandler;
import fr.creativegames.cgstandardlib.languages.error.MalformedJSONError;
import fr.creativegames.cgstandardlib.utils.Validate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Main class for String replacement
 * @author Axicer
 *
 */
public class StringReplacer {

	/**
	 * Return the {@link AbstractLanguage} global vars 
	 * @param lang {@link AbstractLanguage} given language
	 * @return {@link Map}<{@link String}, {@link String}> of global variables's name and value
	 */
    public static Map<String, String> getGlobalVars(AbstractLanguage lang){
        Map<String, String> vars = new HashMap<String, String>();
        try {
            if(!Validate.notNull(lang.getLangJSON().getJSONObject("lang"))){
                ErrorHandler.throwError(new MalformedJSONError());
                return null;
            }
            JSONObject root = lang.getLangJSON().getJSONObject("lang");
            return child(lang, root);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return vars;
    }

    /**
     * Replace all syntaxes of global variables in the given text
     * @param text {@link String} givent text
     * @param lang {@link AbstractLanguage} to use
     * @return {@link String} the replaced text
     */
    public static String replaceGlobal(String text, AbstractLanguage lang){
        String[] elements = text.split("@");
        Map<String, String> map = getGlobalVars(lang);
        for(String key : map.keySet()){
            for(int i = 0 ; i < elements.length ; i++){
                String str = elements[i];
                if(str.startsWith(key)){
                    str = str.replace(key,map.get(key));
                }
                elements[i] = str;
            }
        }
        StringBuilder build = new StringBuilder();
        for(int i = 0 ; i < elements.length ; i++){
        	build.append(elements[i]);
        }
        return build.toString();
    }
    
    /**
     * Replace all syntaxes of variables given in the given text
     * @param text {@link String} given text
     * @param values {@link String}[] variables to replace
     * @return {@link String} the replaced text
     */
    public static String replaceVar(String text, String... values){
		for(int i = 0 ; i < values.length ; i++){
			if(text.contains("$"+String.valueOf(i)))
				text = text.replace("$"+String.valueOf(i), values[i]);
		}
		return text;
	}

    /**
     * Recursive function to get all global variables in a given language
     * @param lang {@link AbstractLanguage} given language
     * @param obj {@link JSONObject} root JSON object
     * @return {@link Map}<{@link String}, {@link String}> of global variables
     */
    @SuppressWarnings("unchecked")
	private static Map<String, String> child(AbstractLanguage lang, JSONObject obj){
        Map<String, String> val = new HashMap<String, String>();
        try{
            Iterator<String> it = obj.keys();
            while(it.hasNext()){
                String key = it.next();
                Object o = obj.get(key);
                if(o instanceof JSONObject){
                    val.putAll(child(lang, (JSONObject)o));
                }else{
                    if(o instanceof String && key.startsWith("@")){
                        val.put(key, (String)o);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
