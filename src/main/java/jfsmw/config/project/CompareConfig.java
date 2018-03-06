package jfsmw.config.project;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by Christoph Graupner on 2018-01-23.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@XmlType
public class CompareConfig {
    enum Type {
        FIRST_INTO_OTHERS, OTHERS_INTO_FIRST, ALL_IN_ALL
    }

    @XmlAttribute(name = "name")
    @Getter
    @Setter
    String displayName;

    @XmlElement(name = "directory")
    @Getter
    @Setter
    List<DirectoryConfig> directories;

}
