package jfsmw.test.integration;

import java.nio.file.Path;

/**
 * Created by Christoph Graupner on 2018-01-09.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public interface TestFixture {
    void destroy();

    boolean isAlive();

    Path path();
}
