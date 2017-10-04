package fr.creativegames.cgstandardlib.languages;

import org.json.JSONException;
import org.json.JSONObject;

import fr.creativegames.cgstandardlib.exceptions.ErrorHandler;
import fr.creativegames.cgstandardlib.languages.error.CannotCastElementAsJSONObjectError;
import fr.creativegames.cgstandardlib.languages.error.MalformedJSONError;
import fr.creativegames.cgstandardlib.languages.error.NullOrEmptyPathError;
import fr.creativegames.cgstandardlib.utils.Validate;

/**
 * Represent a Abstract language
 * @author Axicer
 */
public class AbstractLanguage {
	
	private JSONObject langJSON;

    /**
     * Main Constructor of an {@link AbstractLanguage}
     * @param object the {@link JSONObject} main element of this language
     */
	public AbstractLanguage(JSONObject object) {
		this.langJSON = object;
		try {
			if(!Validate.notNull(object.getJSONObject("lang"))){
				ErrorHandler.throwError(new MalformedJSONError());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

    /**
     * Get the content of the given path and if not found give the default value
     * *NOTE* the returned element is as same type as the default value
     * @param path {@link String} the path of the needed value
     * @param defaultValue default value needed
     * @param <T> all types of elements that a JSON file can store
     * @return the given element or the default value if not found
     */
	@SuppressWarnings("unchecked")
	public <T> T get(String path, T defaultValue){
		if(!Validate.notNull(path) || !Validate.notEmpty(path)){
			ErrorHandler.throwError(new NullOrEmptyPathError());
			return defaultValue;
		}
		String[] nodes = path.split("\\.");
		try {
			JSONObject actualNode = langJSON.getJSONObject("lang");
			for(int i = 0 ; i < nodes.length-1 ; i++){
				String node = nodes[i];
				if(actualNode instanceof JSONObject){
					actualNode = actualNode.getJSONObject(node);
				}else{
					ErrorHandler.throwError(new CannotCastElementAsJSONObjectError(node));
					return defaultValue;
				}
			}
			if(actualNode instanceof JSONObject){
				return (T) actualNode.get(nodes[nodes.length-1]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return defaultValue;
		}
		return defaultValue;
	}

    /**
     * Return the raw JSON language
     * @return JSONObject element
     */
    public JSONObject getLangJSON() {
        return langJSON;
    }
}
