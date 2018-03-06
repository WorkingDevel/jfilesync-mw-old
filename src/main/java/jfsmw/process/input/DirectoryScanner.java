package jfsmw.process.input;

import jfsmw.exception.ValidationException;
import jfsmw.model.ResultMatrix;

import java.io.IOException;

/**
 * Created by Christoph Graupner on 2018-01-08.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public interface DirectoryScanner {
    /**
     * Thread: blocking behavior
     *
     * @return this
     */
    DirectoryScanner scan() throws IOException, ValidationException;

    DirectoryScanner setResultTable(ResultMatrix resultMatrix);
}
