package jfsmw.config;

import jfsmw.LogMarker;
import jfsmw.config.application.AppConfig;
import jfsmw.config.project.ProjectConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.nio.file.Path;

/**
 * Created by Christoph Graupner on 2018-02-04.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@Service
public class ConfigService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ProjectConfig readProjectConfig(final Path pathToConfig) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(ProjectConfig.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            final ProjectConfig ret = (ProjectConfig) jaxbUnmarshaller.unmarshal(pathToConfig.toFile());
            logger.info("Read project config from " + pathToConfig.toAbsolutePath().normalize().toString());
            return ret;
        } catch (JAXBException e) {
            logger.error(LogMarker.APPLICATION,
                         "Could not read project config from "
                         + pathToConfig.toAbsolutePath().normalize().toString()
                         + " as of invalid file format. Created an empty one.",
                         e
            );

            return new ProjectConfig();
        }
    }

    public void saveProjectConfig(final ProjectConfig appConfig, final Path pathToConfig) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProjectConfig.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(appConfig, pathToConfig.toFile());
            logger.info("Saved project config to " + pathToConfig.toAbsolutePath().normalize().toString());
        } catch (JAXBException e) {
            logger.error(LogMarker.EXCEPTION,
                         "Something went wrong saving project config to "
                         + pathToConfig.toAbsolutePath().normalize().toString(),
                         e
            );
        }
    }

    public AppConfig readAppConfig(final Path pathToConfig) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AppConfig.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            final AppConfig ret = (AppConfig) jaxbUnmarshaller.unmarshal(pathToConfig.toFile());
            logger.info("Read application config from " + pathToConfig.toAbsolutePath().normalize().toString());
            return ret;
        } catch (JAXBException e) {
//            if (e.getMessage().contains("unexpected element")) {
//            }
            logger.warn(LogMarker.APPLICATION,
                        "Could not read app-config from "
                        + pathToConfig.toAbsolutePath().normalize().toString()
                        + " as of invalid file format. Created an empty one.",
                        e
            );

            return new AppConfig();
        }
    }

    public void saveAppConfig(final AppConfig appConfig, final Path pathToConfig) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AppConfig.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(appConfig, pathToConfig.toFile());
            logger.info("Saved application config to " + pathToConfig.toAbsolutePath().normalize().toString());
        } catch (JAXBException e) {
            logger.error(LogMarker.EXCEPTION,
                         "Something went wrong saving application config to "
                         + pathToConfig.toAbsolutePath().normalize().toString(),
                         e
            );
        }
    }
}

