package jfsmw.exception;

/**
 * Created by Christoph Graupner on 2018-01-16.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class ValidationException extends JfsException {
    public ValidationException(final String message) {
        super(message);
    }

    ValidationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    ValidationException(final Throwable cause) {
        super(cause);
    }

    ValidationException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
