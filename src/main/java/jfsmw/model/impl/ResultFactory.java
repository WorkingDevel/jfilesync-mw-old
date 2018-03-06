package jfsmw.model.impl;

import jfsmw.model.CompareRequest;
import jfsmw.model.ResultElement;
import jfsmw.model.ResultMatrix;
import jfsmw.model.SourceId;

import java.nio.file.Path;
import java.util.Arrays;

/**
 * Created by Christoph Graupner on 2018-01-13.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class ResultFactory implements jfsmw.model.ResultFactory {
    private static ResultFactory instance = null;

    public static ResultFactory getInstance() {
        if (instance == null) {
            instance = new ResultFactory();
        }
        return instance;
    }

    public ResultElement<?> createElement(final Path path, final ResultElement.Type type,
            final SourceId sourceId) {
        return new LocalPathResultElement<>(path, type, sourceId);
    }

    @Override
    public ResultMatrix createResultMatrix() {
        return new ConcurrentMapResultMatrix();
    }

    @Override
    public CompareRequest createCompareRequest(final Path... sourceRoots) {

        return new SimpleCompareRequest(Arrays.asList(sourceRoots));
    }

}
