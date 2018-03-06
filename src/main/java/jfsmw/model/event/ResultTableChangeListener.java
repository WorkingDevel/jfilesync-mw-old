package jfsmw.model.event;

import jfsmw.model.ResultElement;

/**
 * Created by Christoph Graupner on 2018-01-15.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@FunctionalInterface
public interface ResultTableChangeListener {
    void changed(ResultElement oldValue, ResultElement newValue);
}
