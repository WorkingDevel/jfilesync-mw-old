package jfsmw.ui.javafx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jfsmw.config.project.ProjectConfig;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christoph Graupner on 2018-01-23.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class ProjectConfigViewModel {

  final ProjectConfig projectConfig;
  List<CompareConfigViewModel> compareConfigs = new ArrayList<>();
  @Getter
  StringProperty nameProperty = new SimpleStringProperty();

  public ProjectConfigViewModel(final ProjectConfig projectConfig) {
    this.projectConfig = projectConfig;
    projectConfig.getCompareConfigs()
                 .forEach(
                     compareConfig1 -> compareConfigs.add(new CompareConfigViewModel(compareConfig1))
                 );
  }

  public ProjectConfig getProjectConfig() {
    return projectConfig;
  }
}
