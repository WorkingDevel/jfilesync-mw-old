package jfsmw.test.integration;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by Christoph Graupner on 2018-01-09.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class TestTempFixture implements TestFixture {
    final Path path;

    public TestTempFixture(Path path) {
        this.path = path;
    }


    public void destroy() {
        if (isAlive()) {
            try {
                Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }

                });
                Files.deleteIfExists(path);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public boolean isAlive() {
        return path.toFile().exists();
    }

    @Override
    public Path path() {
        return path;
    }
}
