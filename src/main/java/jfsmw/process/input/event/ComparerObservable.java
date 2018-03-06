package jfsmw.process.input.event;

/**
 * Created by Christoph Graupner on 2018-01-13.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public interface ComparerObservable {
    void registerComparerListener(ComparerListener listener);
}
