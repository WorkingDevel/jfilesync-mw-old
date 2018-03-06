package jfsmw.model;

import java.util.Collection;

/**
 * Created by Christoph Graupner on 2018-01-21.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public interface JfsResult {

    ResultMatrix getResultMatrix(CompareRequest compareRequest);

    Collection<ResultMatrix> getAllResultMatrices();

    /**
     * @param compareRequest
     *
     * @return this
     *
     * @throws IllegalStateException if there is already a compareResult set
     */
    JfsResult addResultMatrix(CompareRequest compareRequest, ResultMatrix resultMatrix);
}
