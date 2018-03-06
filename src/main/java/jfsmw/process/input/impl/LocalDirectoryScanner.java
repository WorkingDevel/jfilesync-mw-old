package jfsmw.process.input.impl;

import jfsmw.LogMarker;
import jfsmw.exception.ValidationException;
import jfsmw.model.ResultElement;
import jfsmw.model.ResultMatrix;
import jfsmw.model.SourceId;
import jfsmw.process.input.DirectoryScanner;
import lombok.Getter;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Christoph Graupner on 2018-01-08.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class LocalDirectoryScanner implements DirectoryScanner {
    @Getter
    final         SourceId     sourceId;
    private final Logger       logger = LoggerFactory.getLogger(getClass());
    private final Path         rootPath;
    private       ResultMatrix resultMatrix;

    public LocalDirectoryScanner(final SourceId sourceId, Path path) {
        this.sourceId = sourceId;
        rootPath = path;
    }

    @Override
    public DirectoryScanner scan() throws IOException, ValidationException {
        scanDir(rootPath);
        return this;
    }

    private void scanDir(@NonNull final Path startPath) throws IOException, ValidationException {
        validateIsDir(startPath);
        resultMatrix.add(
                rootPath.relativize(startPath),
                resultMatrix.factory().createElement(startPath, ResultElement.Type.DIRECTORY, getSourceId())
        );
        for (Path path : Files.newDirectoryStream(startPath)) {
            if (path.toFile().isDirectory()) {
                //directory -> go recursive
                try {
                    scanDir(path);
                } catch (IOException e) {
                    logger.error(LogMarker.EXCEPTION, e.getLocalizedMessage(), e);
                }
            } else {
                //file
                resultMatrix.add(rootPath.relativize(path),
                                 resultMatrix.factory().createElement(path, ResultElement.Type.FILE, getSourceId())
                );
            }
        }
    }

    private void validateIsDir(final Path path) throws ValidationException {
        if (!path.toFile().isDirectory()) {
            throw new ValidationException("Path is not a directory: " + path);
        }
    }

    @Override
    public DirectoryScanner setResultTable(final ResultMatrix resultMatrix) {
        this.resultMatrix = resultMatrix;
        return this;
    }

}
