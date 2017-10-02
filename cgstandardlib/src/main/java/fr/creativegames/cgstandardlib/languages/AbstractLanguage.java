package fr.creativegames.cgstandardlib.languages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.creativegames.cgstandardlib.exceptions.ErrorHandler;
import fr.creativegames.cgstandardlib.languages.error.CannotCastElementAsJSONObjectOrJSONArray;
import fr.creativegames.cgstandardlib.languages.error.MalformedJSONError;
import fr.creativegames.cgstandardlib.languages.error.NullOrEmptyPathError;
import fr.creativegames.cgstandardlib.utils.Validate;

public class AbstractLanguage {
	
	private JSONObject langObject;
	
	public AbstractLanguage(JSONObject object) {
		this.langObject = object;
		try {
			if(!Validate.notNull(object.getJSONObject("lang"))){
				ErrorHandler.throwError(new MalformedJSONError());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String path, T defaultValue){
		if(!Validate.notNull(path) || !Validate.notEmpty(path)){
			ErrorHandler.throwError(new NullOrEmptyPathError());
			return defaultValue;
		}
		String[] nodes = path.split("\\.");
		try {
			Object actualNode = langObject.get("lang");
			for(int i = 0 ; i < nodes.length ; i++){
				String node = nodes[i];
				if(actualNode instanceof JSONObject){
					actualNode = ((JSONObject)actualNode).get(node);
				}else if(actualNode instanceof JSONArray){
					actualNode = ((JSONArray)actualNode).get(Integer.parseInt(node));
				}else{
					ErrorHandler.throwError(new CannotCastElementAsJSONObjectOrJSONArray(node));
					return defaultValue;
				}
			}
			if(actualNode instanceof JSONObject){
				return (T) ((JSONObject)actualNode).get(nodes[nodes.length-1]);
			}else if(actualNode instanceof JSONArray){
				return (T) ((JSONArray)actualNode).get(Integer.parseInt(nodes[nodes.length-1]));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return defaultValue;
		}
		return defaultValue;
	}
}
