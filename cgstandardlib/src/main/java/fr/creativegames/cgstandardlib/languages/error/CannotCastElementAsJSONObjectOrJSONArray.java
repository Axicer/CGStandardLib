package fr.creativegames.cgstandardlib.languages.error;

import fr.creativegames.cgstandardlib.exceptions.errors.AbstractError;
import fr.creativegames.cgstandardlib.exceptions.errors.ErrorLevel;

public class CannotCastElementAsJSONObjectOrJSONArray extends AbstractError {

	private String elementName;
	
	public CannotCastElementAsJSONObjectOrJSONArray(String elementName) {
		this.elementName = elementName;
	}
	
	@Override
	public String getDescription() {
		return "the element \""+elementName+"\" is not a JSONObject or a JSONArray !";
	}

	@Override
	public ErrorLevel getLevel() {
		return ErrorLevel.WARNING;
	}

}
