package jfsmw.ui.javafx.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import jfsmw.LogMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Christoph Graupner on 2018-02-04.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@Component
public class ProjectCompareConfigController {
  private final Logger logger = LoggerFactory.getLogger(getClass());
  @Autowired
  ApplicationContext applicationContext;
  @FXML ListView<TitledPane> listViewDirectories;

  @FXML
  void addDirectoryAction(final ActionEvent actionEvent) throws IOException {

    ProjectConfigAddDirController.ProjectConfigAddDirDialog dialog =
        new ProjectConfigAddDirController.ProjectConfigAddDirDialog(applicationContext);
    dialog.showAndWait()
          .ifPresent(
              directoryConfigViewModel -> {
                try {
                  FXMLLoader fxmlLoader =
                      new FXMLLoader(getClass().getResource("/ui/project-config-add-dir.fxml"));
                  fxmlLoader.setControllerFactory(applicationContext::getBean);
                  TitledPane titledPane = fxmlLoader.load();
                  ((ProjectConfigAddDirController) fxmlLoader.getController())
                      .setViewModel(directoryConfigViewModel);
                  listViewDirectories.getItems().add(titledPane);
                } catch (IOException e) {
                  logger.error(LogMarker.EXCEPTION, e.getLocalizedMessage(), e);
                  throw new IllegalStateException(e);
                }
              }
          );
  }
}
