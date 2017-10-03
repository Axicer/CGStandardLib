package fr.creativegames.cgstandardlib.languages.error;

import fr.creativegames.cgstandardlib.exceptions.errors.AbstractError;
import fr.creativegames.cgstandardlib.exceptions.errors.ErrorLevel;

public class LanguageNotRegisteredError extends AbstractError{

    @Override
    public ErrorLevel getLevel() {
        return ErrorLevel.WARNING;
    }

    @Override
    public String getDescription() {
        return "The language you try to unregister is not registered !";
    }
}
