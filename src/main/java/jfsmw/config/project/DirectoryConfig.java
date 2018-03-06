package jfsmw.config.project;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Christoph Graupner on 2018-02-04.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@XmlType
public class DirectoryConfig {
  public enum Type {
    LOCAL("file"), SSH(null), FTP(null), SFTP("sftp");

    private final String vfsScheme;

    Type(final String pVfsScheme) {
      vfsScheme = pVfsScheme;
    }

    public static Type fromVfsScheme(String vfsScheme) {
      switch (vfsScheme) {
        case "file":
          return LOCAL;
        case "sftp":
          return SFTP;
      }
      return null;
    }

    public String toVfsScheme() {
      return vfsScheme;
    }
  }

  @Getter
  @Setter
  String path;

  @XmlAttribute
  @Getter
  @Setter
  Type type;
}
