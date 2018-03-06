package jfsmw.process.input.impl;

import jfsmw.exception.ValidationException;
import jfsmw.model.ResultElement;
import jfsmw.model.SourceId;
import jfsmw.model.impl.ConcurrentMapResultMatrix;
import jfsmw.process.input.DirectoryScanner;
import jfsmw.test.integration.TestFixture;
import jfsmw.test.integration.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Christoph Graupner on 2018-01-09.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
class LocalDirectoryScannerTest {

    private TestFixture fixture;

    @BeforeEach
    void setUp() {
        fixture = TestUtil.copyFixtureToNewDir("dirs");
    }

    @AfterEach
    void tearDown() {
        fixture.destroy();
    }

    @Test
    void scan() throws IOException, ValidationException {
        final Path rootPath = fixture.path().resolve("dirs/source");
        LocalDirectoryScanner sut = new LocalDirectoryScanner(
                new SourceId(rootPath), rootPath
        );
        final ConcurrentMapResultMatrix sutResult = new ConcurrentMapResultMatrix();
        final DirectoryScanner sutRetValue = sut.setResultTable(sutResult).scan();

        assertThat(sutRetValue, is(notNullValue()));
//        assertThat(sutResult, notNullValue()));
        assertThat(sutResult.size(), is(1 + 8 + 7 + 5 + 3));

        final Path relativize = rootPath.relativize(rootPath);
        final Map<SourceId, ResultElement<? extends ResultElement>> rootElementMap = sutResult.get(
                relativize);
        assertThat(rootElementMap, is(notNullValue()));
        assertThat(rootElementMap.keySet(), hasSize(1));
        assertThat(rootElementMap, hasKey(sut.getSourceId()));

        Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                final Path relativizedPath = rootPath.relativize(file);
                final Map<SourceId, ResultElement<? extends ResultElement>> actual = sutResult.get(
                        relativizedPath);
                assertThat(relativizedPath.toString(), actual, is(notNullValue()));
                assertThat(relativizedPath.toString(), actual, hasKey(sut.getSourceId()));
                assertThat(relativizedPath.toString(),
                           actual.get(sut.getSourceId()).getPath(),
                           is(equalTo(file))
                );

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
//                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }

        });
    }

}
