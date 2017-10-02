package fr.creativegames.cgstandardlib.exceptions.errors;

/**
 * Represent an abstract error root of all errors
 * @author Axicer
 *
 */
public abstract class AbstractError {
	/**
	 * Return the error's name
	 * @return {@link String} error's name
	 */
	public String getName(){
		return getClass().getSimpleName();
	}
	/**
	 * Return the error's description
	 * @return {@link String} error's description
	 */
	public abstract String getDescription();
	/**
	 * Return the error's level
	 * @return {@link ErrorLevel} error's level
	 */
	public abstract ErrorLevel getLevel();
}
