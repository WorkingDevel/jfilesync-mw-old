package jfsmw.model.impl;

import jfsmw.model.ResultElement;
import jfsmw.model.ResultFactory;
import jfsmw.model.ResultMatrix;
import jfsmw.model.SourceId;
import jfsmw.model.event.ResultTableChangeListener;
import jfsmw.model.event.ResultTableObservable;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Christoph Graupner on 2018-01-13.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class ConcurrentMapResultMatrix implements ResultMatrix {

    private ConcurrentMap<Path, ConcurrentMap<SourceId, ResultElement<? extends ResultElement>>> contents = new ConcurrentHashMap<>();
    private List<SourceId> sourceIds = new Vector<>();
    private ResultTableEventHandler eventHandler = new ResultTableEventHandler();

    @Override
    public List<SourceId> getSourceIds() {
        return sourceIds;
    }

    @Override
    public Map<SourceId, ResultElement<? extends ResultElement>> get(final Path path) {
        return contents.get(path);
    }

    @Override
    public void add(final Path path, final ResultElement<? extends ResultElement> resultElement) {
        if (!sourceIds.contains(resultElement.getSourceId())) {
            sourceIds.add(resultElement.getSourceId());
        }
        ConcurrentMap<SourceId, ResultElement<? extends ResultElement>> el;
        if (!contents.containsKey(path)) {
            el = new ConcurrentHashMap<>();
            contents.put(path, el);
        } else {
            el = contents.get(path);
        }
        el.put(resultElement.getSourceId(), resultElement);
    }

    @Override
    public int size() {
        return contents.size();
    }

    @Override
    public ResultTableObservable events() {
        return eventHandler;
    }

    @Override
    public ResultFactory factory() {
        return jfsmw.model.impl.ResultFactory.getInstance();
    }

    /**
     * Created by Christoph Graupner on 2018-01-15.
     *
     * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
     */
    protected static class ResultTableEventHandler implements ResultTableObservable {
        ResultTableEventHandler() {

        }

        @Override
        public ResultTableChangeListener addChangeListener(final ResultTableChangeListener listener) {
            return null;
        }

        @Override
        public ResultTableChangeListener removeChangeListener(final ResultTableChangeListener listener) {
            return null;
        }
    }

}
