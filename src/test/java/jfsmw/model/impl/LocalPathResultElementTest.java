package jfsmw.model.impl;

import jfsmw.model.ResultElement;
import jfsmw.model.SourceId;
import jfsmw.test.integration.TestFixture;
import jfsmw.test.integration.TestUtil;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by Christoph Graupner on 2018-01-19.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
class LocalPathResultElementTest {

    private static TestFixture fixture;

    @BeforeAll
    static void setUp() {
        fixture = TestUtil.unpackFixtureWithNewDir("dirs.zip");
    }

    @AfterAll
    static void tearDown() {
        fixture.destroy();
    }

    @AfterEach
    void tearEach() throws IOException {
        Files.deleteIfExists(fixture.path().resolve("times.test"));
    }

    @Test
    @Disabled
    void getAdditionalProperties() {
    }

    @Test
    void getSourceId() {
        LocalPathResultElement<LocalPathResultElement> sut = new LocalPathResultElement<>(
                fixture.path().resolve("dirs"),
                ResultElement.Type.DIRECTORY,
                new SourceId(fixture.path())
        );

        assertThat(sut.getSourceId(), is(equalTo(new SourceId(fixture.path()))));
    }

    @Test
    void exists() {
        LocalPathResultElement<LocalPathResultElement> sut = new LocalPathResultElement<>(
                fixture.path().resolve("dirs"),
                ResultElement.Type.DIRECTORY,
                new SourceId(fixture.path())
        );
        assertThat(sut.getPath().toString(), sut.exists(), is(true));

        sut = new LocalPathResultElement<>(
                fixture.path().resolve("dirs2"),
                ResultElement.Type.DIRECTORY,
                new SourceId(fixture.path())
        );
        assertThat(sut.getPath().toString(), sut.exists(), is(false));
    }

    @Test
    void getName() {
        LocalPathResultElement<LocalPathResultElement> sut = new LocalPathResultElement<>(
                fixture.path().resolve("dirs/source/c/f.txt"),
                ResultElement.Type.FILE,
                new SourceId(fixture.path())
        );
        assertThat(sut.getPath().toString(), sut.exists(), is(true));
        assertThat(sut.getName(), is(equalTo("f.txt")));

        sut = new LocalPathResultElement<>(
                fixture.path().resolve("dirs/source/c/noexist.txt"),
                ResultElement.Type.FILE,
                new SourceId(fixture.path())
        );
        assertThat(sut.getPath().toString(), sut.exists(), is(false));
        assertThat(sut.getName(), is(equalTo("noexist.txt")));

        //directories

        sut = new LocalPathResultElement<>(
                Paths.get("/"),
                ResultElement.Type.DIRECTORY,
                new SourceId(fixture.path())
        );
        assertThat(sut.getPath().toString(), sut.exists(), is(true));
        assertThat(sut.getName(), is(equalTo("")));

        sut = new LocalPathResultElement<>(
                Paths.get("noexist/source/c/"),
                ResultElement.Type.DIRECTORY,
                new SourceId(fixture.path())
        );
        assertThat(sut.getPath().toString(), sut.exists(), is(false));
        assertThat(sut.getName(), is(equalTo("c")));
    }

    @Test
    void getPath() {

    }


    @Test
    void getSize() throws IOException {
        LocalPathResultElement<LocalPathResultElement> sut = new LocalPathResultElement<>(
                fixture.path().resolve("dirs/source/c/f.txt"),
                ResultElement.Type.FILE,
                new SourceId(fixture.path())
        );
        assertThat(sut.getPath().toString(), sut.exists(), is(true));
        assertThat(sut.getSize(), is(12L));

        sut = new LocalPathResultElement<>(
                fixture.path().resolve("notexists/source/c/f.txt"),
                ResultElement.Type.FILE,
                new SourceId(fixture.path())
        );
        assertThat(sut.getPath().toString(), sut.exists(), is(false));
        assertThat(sut.getSize(), is(ResultElement.SIZE_NOT_EXISTS));
    }

    @Test
    void getModificationTime() throws IOException {
        final Path origin = fixture.path().resolve("times.test");
        final File fixtureFile = origin.toFile();

        assertThat(fixtureFile.createNewFile(), is(true));
        final Instant fixtureInstant = Instant.parse("2018-01-01T13:33:03Z");
        assertThat(fixtureFile.setLastModified(fixtureInstant.toEpochMilli()), is(true));

        LocalPathResultElement<LocalPathResultElement> sut = new LocalPathResultElement<>(
                fixture.path().resolve("times.test"),
                ResultElement.Type.FILE,
                new SourceId(fixture.path())
        );
        assertThat(sut.toString(), sut.exists(), is(true));
        assertThat(sut.toString(), sut.getModificationTime(), is(Instant.parse("2018-01-01T13:33:03Z")));

        assertThat(fixtureFile.delete(), is(true));

        sut = new LocalPathResultElement<>(
                fixture.path().resolve("notexists/times.test"),
                ResultElement.Type.FILE,
                new SourceId(fixture.path())
        );
        assertThat(sut.toString(), sut.exists(), is(false));
        assertThat(sut.toString(), sut.getModificationTime(), is(nullValue()));

    }

    @Test
    @Disabled
        //not working on linux file systems
    void getCreationTime() throws IOException {
        final Path origin = fixture.path().resolve("times.test");
        final File fixtureFile = origin.toFile();
        assertThat(fixtureFile.createNewFile(), is(true));
        BasicFileAttributes attr = Files.readAttributes(origin, BasicFileAttributes.class);
        final Instant fixtureInstant = attr.creationTime().toInstant();
        assertThat(fixtureFile.setLastModified(Instant.parse("2000-11-11T15:09:34Z").toEpochMilli()), is(true));
        LocalPathResultElement<LocalPathResultElement> sut = new LocalPathResultElement<>(
                fixture.path().resolve("times.test"),
                ResultElement.Type.FILE,
                new SourceId(fixture.path())
        );
        assertThat(sut.toString(), sut.getCreationTime(), is(fixtureInstant));
        assertThat(fixtureFile.delete(), is(true));
    }

    @Test
    void getType() {
        LocalPathResultElement<LocalPathResultElement> sut = new LocalPathResultElement<>(
                Paths.get("noexist/source/c/"),
                ResultElement.Type.DIRECTORY,
                new SourceId(fixture.path())
        );

        assertThat(sut.exists(), is(false));
        assertThat(sut.getType(), is(ResultElement.Type.DIRECTORY));

        sut = new LocalPathResultElement<>(
                fixture.path().resolve("dirs/source/c"),
                ResultElement.Type.FILE,
                new SourceId(fixture.path())
        );

        assertThat(sut.exists(), is(true));
        assertThat(sut.getType(), is(ResultElement.Type.FILE));
    }

    @Test
    @Disabled
    void children() {
    }

    @Test
    @Disabled
    void compareTo() {
    }

    @Test
    @Disabled
    void getOrigin() {
    }
}
