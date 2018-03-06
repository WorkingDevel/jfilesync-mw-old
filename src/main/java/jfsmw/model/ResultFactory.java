package jfsmw.model;

import java.nio.file.Path;

/**
 * Created by Christoph Graupner on 2018-01-13.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public interface ResultFactory {
    ResultElement<?> createElement(final Path path, final ResultElement.Type type, final SourceId sourceId);

    ResultMatrix createResultMatrix();

    CompareRequest createCompareRequest(Path... sourceRoots);
}
