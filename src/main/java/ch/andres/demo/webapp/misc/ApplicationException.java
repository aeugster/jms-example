package ch.andres.demo.webapp.misc;

/**
 * Fach- oder Businessfehler which is kind of expected
 * - does result in warning (errorhandler)
 * - can / should be shown to the user
 */
public class ApplicationException extends Exception {

	public ApplicationException(String message) {
		super(message);
	}
}
