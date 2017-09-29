package fr.creativegames.cgstandardlib.exceptions.errors;

/**
 * Represent all available error levels
 * @author Axicer
 *
 */
public enum ErrorLevel {
	/**
	 * Used only to warn about minor error
	 */
	WARNING(0),
	/**
	 * Used for important error but not critical for the application 
	 */
	SEVERE(1),
	/**
	 * Use for high level error which cause critical issues to the application
	 */
	CRITICAL(2);
	
	private int id;
	
	private ErrorLevel(int id) {
		this.id = id;
	}

	/**
	 * Get the {@link ErrorLevel} id
	 * @return {@link Integer} id of the {@link ErrorLevel}
	 */
	public int getID() {
		return id;
	}
}
