package jfsmw.model;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by Christoph Graupner on 2018-01-21.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public interface CompareRequest {
    List<Path> getCompareRoots();

    boolean equals(final Object o);

    int hashCode();
}
