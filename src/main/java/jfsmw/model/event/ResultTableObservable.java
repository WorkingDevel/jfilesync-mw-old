package jfsmw.model.event;

/**
 * Created by Christoph Graupner on 2018-01-15.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public interface ResultTableObservable {

    ResultTableChangeListener addChangeListener(ResultTableChangeListener listener);

    ResultTableChangeListener removeChangeListener(ResultTableChangeListener listener);
}
