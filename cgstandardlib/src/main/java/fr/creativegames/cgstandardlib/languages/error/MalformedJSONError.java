package fr.creativegames.cgstandardlib.languages.error;

import fr.creativegames.cgstandardlib.exceptions.errors.AbstractError;
import fr.creativegames.cgstandardlib.exceptions.errors.ErrorLevel;

public class MalformedJSONError extends AbstractError {

	@Override
	public String getDescription() {
		return "the given JSON Object does not contains \"lang\" attribute as root attribute !";
	}

	@Override
	public ErrorLevel getLevel() {
		return ErrorLevel.SEVERE;
	}

}
