package jfsmw.test.integration;

import lombok.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Christoph Graupner on 2018-01-09.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class TestUtil {
    public static TestFixture unpackFixtureWithNewDir(String pathToFixture) {
        InputStream res = TestUtil.class.getResourceAsStream("/fixtures/" + pathToFixture);
        try {
            final File tempDir = com.google.common.io.Files.createTempDir();
            unzip(res, tempDir);
            return new TestTempFixture(tempDir.toPath());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static TestFixture copyFixtureToNewDir(String pathToFixture) {
        URI uri = null;
        final File tempDir = com.google.common.io.Files.createTempDir();
        try {
            uri = TestUtil.class.getResource("/fixtures/" + pathToFixture).toURI();
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
        Path targetRoot = tempDir.toPath().resolve(pathToFixture);

        try (FileSystem fileSystem = (uri.getScheme().equals("jar")
                                      ? FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap())
                                      : null)
        ) {
            Path myPath = Paths.get(uri);
            Files.walkFileTree(myPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    final Path target = targetRoot.resolve(myPath.relativize(file));
                    Files.createDirectories(target.getParent());
                    Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return new TestTempFixture(tempDir.toPath());
    }

    private static void unzip(@NonNull InputStream fis, @NonNull File dir) throws IOException {
        // create output directory if it doesn't exist
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry ze = zis.getNextEntry();
        while (ze != null) {
            String fileName = ze.getName();
            File newFile = new File(dir, fileName);
            if (ze.isDirectory()) {
                newFile.mkdirs();
            } else {
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            //close this ZipEntry
            zis.closeEntry();
            ze = zis.getNextEntry();
        }
        //close last ZipEntry
        zis.closeEntry();
        zis.close();
        fis.close();

    }
}
