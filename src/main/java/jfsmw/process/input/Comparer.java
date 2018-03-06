package jfsmw.process.input;

import jfsmw.model.ResultMatrix;
import jfsmw.process.input.event.ComparerObservable;

/**
 * Created by Christoph Graupner on 2018-01-08.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public interface Comparer extends ComparerObservable {
    Comparer addSourceScanner(DirectoryScanner scanner);

    Comparer evaluateWith(ElementEvaluator evaluator);

    ResultMatrix run();

    DirectoryScanner getSourceScanner(int position);
}
