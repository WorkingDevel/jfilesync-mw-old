package jfsmw.model.impl;

import jfsmw.model.ChangableResultElement;
import jfsmw.model.ResultElement;
import jfsmw.model.SourceId;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Christoph Graupner on 2018-01-13.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
class LocalPathResultElement<OTHER extends ResultElement> implements ChangableResultElement<OTHER> {

    @Getter
    private final Path origin;

    private final SourceId sourceId;
    private final Type type;
    private Map<String, Object> additionalProperties = new HashMap<>();
    private BasicFileAttributes fileAttributes = null;

    LocalPathResultElement(final Path origin, final Type type, final SourceId sourceId) {
        this.origin = origin;
        this.type = type;
        this.sourceId = sourceId;
    }

    @Override
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    @Override
    public <T> void setAdditionalProperty(final String propertyName, final T value) {
        additionalProperties.put(propertyName, value);
    }

    @Override
    public SourceId getSourceId() {
        return sourceId;
    }

    @Override
    public <T> T getAdditionalProperty(final String propertyName) {
        return hasAdditionalProperty(propertyName) ? (T) additionalProperties.get(propertyName) : null;
    }

    @Override
    public boolean hasAdditionalProperties() {
        return additionalProperties != null && additionalProperties.size() > 0;
    }

    @Override
    public boolean hasAdditionalProperty(final String propertyName) {
        return additionalProperties.containsKey(propertyName);
    }

    @Override
    public boolean exists() {
        return getPath().toFile().exists();
    }

    @Override
    public String getName() {
        return getPath().getFileName() != null ? getPath().getFileName().toString() : "";
    }

    @Override
    public Path getPath() {
        return origin;
    }

    @Override
    public long getSize() throws IOException {
        if (!readFileAttributes()) {
            return SIZE_NOT_EXISTS;
        }
        return fileAttributes.size();
    }

    @Override
    public Instant getModificationTime() throws IOException {
        if (!readFileAttributes()) {
            return null;
        }
        return fileAttributes.lastModifiedTime().toInstant();
    }

    /**
     * @return true, if file exists and file attributes could be read
     *
     * @throws java.io.IOException
     */
    private boolean readFileAttributes() throws java.io.IOException {
        if (!exists()) {
            return false;
        }
        if (fileAttributes == null) {
            fileAttributes = Files.readAttributes(getOrigin(), BasicFileAttributes.class);
        }
        return fileAttributes != null;
    }

    @Override
    public Instant getCreationTime() throws IOException {
        if (!readFileAttributes()) {
            return null;
        }
        return fileAttributes.creationTime().toInstant();
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public List<ResultElement<?>> children() {
        return null;
    }

    @Override
    public int compareTo(final OTHER o) {
        return 0;
    }
}
