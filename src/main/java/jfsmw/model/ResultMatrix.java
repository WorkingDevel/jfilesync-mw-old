package jfsmw.model;

import jfsmw.model.event.ResultTableObservable;
import lombok.NonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * Created by Christoph Graupner on 2018-01-09.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public interface ResultMatrix {

    List<SourceId> getSourceIds();

    /**
     * returns a list, there the index stands for the source and the value at
     *
     * @param path
     *
     * @return
     */
    Map<SourceId, ResultElement<? extends ResultElement>> get(@NonNull Path path);

    /**
     * Adds <code>resultElement#getSourceId()</code> to the list of SourceIds return by <code>#getSourceIds()</code>
     *
     * @param path          Relative <code>Path</code> to the compare roots.
     * @param resultElement
     */
    void add(@NonNull Path path, @NonNull ResultElement<? extends ResultElement> resultElement);

    int size();

    ResultTableObservable events();

    ResultFactory factory();
}
