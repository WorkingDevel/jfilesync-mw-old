package jfsmw.process.input.impl;

import jfsmw.model.ResultMatrix;
import jfsmw.process.input.DirectoryScanner;
import jfsmw.process.input.ElementEvaluator;
import jfsmw.process.input.event.ComparerListener;
import jfsmw.process.input.Comparer;
import jfsmw.model.ResultMatrix;
import jfsmw.process.input.Comparer;

import java.util.List;

/**
 * Created by Christoph Graupner on 2018-01-08.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class ComparerImpl implements Comparer {
    List<DirectoryScanner> scanners;

    @Override
    public Comparer addSourceScanner(DirectoryScanner scanner) {
        return this;
    }

    @Override
    public Comparer evaluateWith(ElementEvaluator evaluator) {
        return null;
    }


    @Override
    public ResultMatrix run() {
        return null;
    }

    @Override
    public DirectoryScanner getSourceScanner(int position) {
        return scanners.get(position);
    }


    @Override
    public void registerComparerListener(final ComparerListener listener) {

    }
}
