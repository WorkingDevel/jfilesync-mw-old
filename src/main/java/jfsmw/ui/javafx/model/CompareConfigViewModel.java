package jfsmw.ui.javafx.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import jfsmw.config.project.CompareConfig;
import lombok.Getter;

/**
 * Created by Christoph Graupner on 2018-01-23.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class CompareConfigViewModel {
  private final CompareConfig compareConfig;
  @Getter
  ListProperty<String> directoryProperty = new SimpleListProperty<>();

  public CompareConfigViewModel(final CompareConfig compareConfig) {
    this.compareConfig = compareConfig;
  }
}
