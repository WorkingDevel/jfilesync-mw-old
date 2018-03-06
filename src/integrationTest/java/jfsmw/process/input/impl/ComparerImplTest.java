package jfsmw.process.input.impl;

import jfsmw.test.integration.TestFixture;
import jfsmw.test.integration.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Christoph Graupner on 2018-01-08.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
class ComparerImplTest {
    private TestFixture fixture;

    @BeforeEach
    void setUp() {
        fixture = TestUtil.unpackFixtureWithNewDir("dirs.zip");
    }

    @AfterEach
    void tearDown() {
        fixture.destroy();
    }

    @Test
    void addSourceScanner() throws Exception {
        throw new Exception("hh");
//        ComparerImpl sut = new ComparerImpl();
//        DirectoryScanner r1 = sut.getSourceScanner(0);
//        ResultMatrix result = new ConcurrentMapResultMatrix();
//
//        final ResultFactory resultFactory = ;
//        sut.addSourceScanner(
//                new LocalDirectoryScanner(
//                        new SourceId(fixture.path()), fixture.path().resolve("dirs/source"),
//                        resultFactory
//                )
//        )
//           .addSourceScanner(
//                   new LocalDirectoryScanner(
//                           new SourceId(fixture.path()), fixture.path().resolve("dirs/target"),
//                                             resultFactory
//                   )
//           );
//        sut.evaluateWith(element -> element);
    }
}
