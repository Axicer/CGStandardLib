package fr.creativegames.cgstandardlib.languages.error;

import fr.creativegames.cgstandardlib.exceptions.errors.AbstractError;
import fr.creativegames.cgstandardlib.exceptions.errors.ErrorLevel;

public class LanguageAlreadyRegisteredError extends AbstractError {

	@Override
	public String getDescription() {
		return "The lang is already owning an ID !";
	}

	@Override
	public ErrorLevel getLevel() {
		return ErrorLevel.WARNING;
	}

}
