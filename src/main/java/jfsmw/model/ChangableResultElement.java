package jfsmw.model;

import java.util.Map;

/**
 * Created by Christoph Graupner on 2018-01-13.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public interface ChangableResultElement<OTHER extends ResultElement> extends ResultElement<OTHER> {
    <T> void setAdditionalProperty(String propertyName, T value);

    Map<String, Object> getAdditionalProperties();
}
