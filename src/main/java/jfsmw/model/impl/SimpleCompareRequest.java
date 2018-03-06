package jfsmw.model.impl;

import jfsmw.model.CompareRequest;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Christoph Graupner on 2018-01-21.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
class SimpleCompareRequest implements CompareRequest {
    private final List<Path> compareRoots;

    SimpleCompareRequest(final List<Path> compareRoots) {
        this.compareRoots = new ArrayList<>(compareRoots);
    }

    @Override
    public List<Path> getCompareRoots() {
        return compareRoots;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleCompareRequest)) {
            return false;
        }
        final SimpleCompareRequest that = (SimpleCompareRequest) o;
        return Objects.equals(getCompareRoots(), that.getCompareRoots());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCompareRoots());
    }
}
