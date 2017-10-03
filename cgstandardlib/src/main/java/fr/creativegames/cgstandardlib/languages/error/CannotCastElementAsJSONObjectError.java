package fr.creativegames.cgstandardlib.languages.error;

import fr.creativegames.cgstandardlib.exceptions.errors.AbstractError;
import fr.creativegames.cgstandardlib.exceptions.errors.ErrorLevel;

public class CannotCastElementAsJSONObjectError extends AbstractError {

	private String elementName;
	
	public CannotCastElementAsJSONObjectError(String elementName) {
	    this.elementName = elementName;
	}
	
	@Override
	public String getDescription() {
		return "The element \""+elementName+"\" is not a JSONObject !";
	}

	@Override
	public ErrorLevel getLevel() {
		return ErrorLevel.WARNING;
	}

}
