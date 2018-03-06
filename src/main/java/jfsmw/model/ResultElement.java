package jfsmw.model;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;

/**
 * Created by Christoph Graupner on 2018-01-08.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public interface ResultElement<OTHER extends ResultElement> extends Comparable<OTHER> {
    ResultFactory factory = jfsmw.model.impl.ResultFactory.getInstance();
    long SIZE_NOT_EXISTS = -1L;

    SourceId getSourceId();

    <T> T getAdditionalProperty(String propertyName);

    boolean hasAdditionalProperties();

    boolean hasAdditionalProperty(String propertyName);

    boolean exists();

    String getName();

    Path getPath();

    long getSize() throws IOException;

    /**
     * @return Time of last modification in UTC time.
     *
     * @throws IOException if something could not be read from file system
     */
    Instant getModificationTime() throws IOException;

    /**
     * Not really working on linux
     *
     * @return
     *
     * @throws IOException if something could not be read from file system
     */
    @Deprecated
    Instant getCreationTime() throws IOException;

    Type getType();

    List<ResultElement<?>> children();

    enum Type {
        DIRECTORY, FILE
    }
}
