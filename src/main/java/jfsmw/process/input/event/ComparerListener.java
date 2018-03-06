package jfsmw.process.input.event;

import jfsmw.model.ResultElement;

/**
 * Created by Christoph Graupner on 2018-01-13.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public interface ComparerListener {
    void started();

    void stopped();

    void scanFailure(ResultElement<?> resultElement);

    void scanned(ResultElement<?> resultElement);
}
