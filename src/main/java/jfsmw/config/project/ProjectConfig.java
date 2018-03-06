package jfsmw.config.project;

import lombok.Getter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christoph Graupner on 2018-01-23.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@XmlRootElement(name = "jmwfilesync-project")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProjectConfig {
    @XmlAttribute
    String version = "0.0.1";
    @Getter
    @XmlElement(name = "compare")
    List<CompareConfig> compareConfigs = new ArrayList<>();
}
