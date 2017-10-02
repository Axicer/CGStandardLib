package fr.creativegames.cgstandardlib.languages.error;

import fr.creativegames.cgstandardlib.exceptions.errors.AbstractError;
import fr.creativegames.cgstandardlib.exceptions.errors.ErrorLevel;

public class NullOrEmptyPathError extends AbstractError {

	@Override
	public String getDescription() {
		return "given path is either null or empty !";
	}

	@Override
	public ErrorLevel getLevel() {
		return ErrorLevel.WARNING;
	}

}
