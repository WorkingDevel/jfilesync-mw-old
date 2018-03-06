package jfsmw.model.impl;

import jfsmw.model.CompareRequest;
import jfsmw.model.JfsResult;
import jfsmw.model.ResultMatrix;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Christoph Graupner on 2018-01-21.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class SimpleJfsResult implements JfsResult {
    private ConcurrentMap<CompareRequest, ResultMatrix> results = new ConcurrentHashMap<>();

    @Override
    public ResultMatrix getResultMatrix(final CompareRequest compareRequest) {
        return results.getOrDefault(compareRequest, null);
    }

    @Override
    public Collection<ResultMatrix> getAllResultMatrices() {
        return results.values();
    }

    @Override
    public JfsResult addResultMatrix(final CompareRequest compareRequest, final ResultMatrix resultMatrix) {
        if (results.containsKey(compareRequest)) {
            throw new IllegalStateException("There is already a ResultMatrix for "+compareRequest);
        }
        results.put(compareRequest,resultMatrix);
        return this;
    }

}
