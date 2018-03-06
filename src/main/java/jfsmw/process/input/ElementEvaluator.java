package jfsmw.process.input;

import jfsmw.model.ResultElement;

import java.util.function.Function;

/**
 * Created by Christoph Graupner on 2018-01-08.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@FunctionalInterface
public interface ElementEvaluator extends Function<ResultElement<?>, ResultElement<?>> {

}
