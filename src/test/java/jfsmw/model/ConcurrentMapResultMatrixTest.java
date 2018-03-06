package jfsmw.model;

import jfsmw.model.impl.ConcurrentMapResultMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Christoph Graupner on 2018-01-13.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
class ConcurrentMapResultMatrixTest {

    @Test
    void addGet() {
        final SourceId sourceId1 = new SourceId("1");
        final SourceId sourceId2 = new SourceId("2");
        final ResultElement<?> fixture1 = ResultElement.factory.createElement(
                Paths.get("./"),
                ResultElement.Type.DIRECTORY,
                sourceId1
        );
        final ResultElement<?> fixture2 = ResultElement.factory.createElement(
                Paths.get("./"),
                ResultElement.Type.DIRECTORY,
                sourceId2
        );
        final ResultElement<?> fixture3 = ResultElement.factory.createElement(
                Paths.get("./test"),
                ResultElement.Type.FILE,
                sourceId1
        );

        final ConcurrentMapResultMatrix sut = new ConcurrentMapResultMatrix();
        assertThat(sut.size(), is(0));
        assertThat(sut.get(Paths.get("./")), is(nullValue()));
        assertThat(sut.getSourceIds(), is(empty()));

        sut.add(Paths.get("./"), fixture1);
        assertThat(sut.size(), is(1));
        Map<SourceId, ResultElement<? extends ResultElement>> actual = sut.get(Paths.get("./"));
        assertThat(actual, is(notNullValue()));
        assertThat(actual.size(), is(1));

        assertThat(sut.getSourceIds(), hasSize(1));
        assertThat(sut.getSourceIds(), hasItem(fixture1.getSourceId()));

        // override with same ResultElement should just not do anything
        sut.add(Paths.get("./"), fixture1);
        assertThat(sut.size(), is(1));
        actual = sut.get(Paths.get("./"));
        assertThat(actual, is(notNullValue()));
        assertThat(actual.size(), is(1));

        Assertions.assertFalse(true, "not fully implemented yet");
    }

    @Test
    @Disabled
    void merge() {
        Assertions.assertFalse(true, "not implemented yet");
    }
}
