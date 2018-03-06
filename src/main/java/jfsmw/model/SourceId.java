package jfsmw.model;

import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Identifier of the source scanner
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class SourceId {
    @Getter
    final Path rootPath;

    public SourceId(final String id) {
        this.rootPath = Paths.get(id);
    }

    public SourceId(final Path rootPath) {
        this.rootPath = rootPath.toAbsolutePath().normalize();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SourceId)) {
            return false;
        }
        final SourceId sourceId = (SourceId) o;
        return Objects.equals(getRootPath(), sourceId.getRootPath());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getRootPath());
    }
}
