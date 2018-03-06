package jfsmw.exception;

/**
 * Created by Christoph Graupner on 2018-01-16.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class JfsException extends Exception {
    public JfsException() {
        super();
    }

    public JfsException(final String message) {
        super(message);
    }

    public JfsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public JfsException(final Throwable cause) {
        super(cause);
    }

    protected JfsException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
