package jfsmw.config.application;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Christoph Graupner on 2018-02-04.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@XmlRootElement(name = "jmwfilesync-app-config")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AppConfig {
    @XmlAttribute
    @Getter
    String version = "0.0.1";

    String lastProject;
}
