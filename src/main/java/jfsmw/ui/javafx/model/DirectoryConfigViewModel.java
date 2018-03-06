package jfsmw.ui.javafx.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jfsmw.config.project.DirectoryConfig;

/**
 * Created by Christoph Graupner on 2018-02-17.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class DirectoryConfigViewModel {
  private final ObjectProperty<DirectoryConfig.Type> directoryType;
  private final StringProperty                       path;
  private final DirectoryConfig                      wrapped;

  public DirectoryConfigViewModel(final DirectoryConfig wrapped) {
    this.wrapped = wrapped;
    directoryType = new SimpleObjectProperty<>(wrapped.getType());
    path = new SimpleStringProperty(wrapped.getPath());
  }

  public ObjectProperty<DirectoryConfig.Type> directoryTypeProperty() {
    return directoryType;
  }

  public StringProperty pathProperty() {
    return path;
  }
}
