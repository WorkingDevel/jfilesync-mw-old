package jfsmw;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Created by Christoph Graupner on 2018-01-16.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class LogMarker {
    public static final Marker APPLICATION = MarkerFactory.getMarker("APPLICATION");
    public static final Marker CONFIG = MarkerFactory.getMarker("CONFIG");
    public static final Marker CONFIG_ERROR = MarkerFactory.getMarker("CONFIG_ERROR");
    public static final Marker EXCEPTION = MarkerFactory.getMarker("EXCEPTION");
    public static final Marker TEST = MarkerFactory.getMarker("TEST");
    public static final Marker TODO_EXCEPTION = MarkerFactory.getMarker("TODO");

    static {
        CONFIG_ERROR.add(EXCEPTION);
        CONFIG_ERROR.add(CONFIG);
        TODO_EXCEPTION.add(EXCEPTION);
    }

}
