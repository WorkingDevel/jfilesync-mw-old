package jfsmw.ui.javafx.project;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import jfsmw.LogMarker;
import jfsmw.config.project.DirectoryConfig;
import jfsmw.ui.javafx.model.DirectoryConfigViewModel;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created by Christoph Graupner on 2018-01-27.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@Component
public class ProjectConfigAddDirController {
  /**
   * Created by Christoph Graupner on 2018-01-27.
   *
   * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
   */
  public static class ProjectConfigAddDirDialog extends Dialog<DirectoryConfigViewModel> {
    private final ApplicationContext applicationContext;
    private final Logger             logger = LoggerFactory.getLogger(getClass());

    ProjectConfigAddDirDialog(final ApplicationContext applicationContext) {
      this.applicationContext = applicationContext;
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/project-config-add-dir.fxml"));
      fxmlLoader.setControllerFactory(this.applicationContext::getBean);

      try {
        final Parent                        projectScene            = fxmlLoader.load();
        final ProjectConfigAddDirController projectConfigController = fxmlLoader.getController();
        getDialogPane().setContent(projectScene);
        projectConfigController.setViewModel(new DirectoryConfigViewModel(new DirectoryConfig()));

        // Hide this current window (if this is what you want)
        //            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        setResultConverter(param -> {
          if (param.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            return projectConfigController.viewModel;
          } else {
            return null;
          }
        });
        getDialogPane().getButtonTypes().add(new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE));
        getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
      } catch (IOException e) {
        logger.error(LogMarker.EXCEPTION, e.getLocalizedMessage(), e);
        throw new IllegalStateException(e);
      }
    }
  }

  @FXML Button                          btnSelectDirectory;
  @FXML ChoiceBox<DirectoryConfig.Type> choiceType;
  @FXML TitledPane                      rootPane;
  @FXML TextField                       textDirectory;
  @Getter
  DirectoryConfigViewModel viewModel;

  void setViewModel(final DirectoryConfigViewModel pViewModel) {
    if (viewModel != null) {
      choiceType.valueProperty().unbindBidirectional(viewModel.directoryTypeProperty());
      textDirectory.textProperty().unbindBidirectional(viewModel.pathProperty());
      rootPane.textProperty().unbind();
    }
    this.viewModel = pViewModel;

    if (this.viewModel != null) {
      choiceType.valueProperty().bindBidirectional(viewModel.directoryTypeProperty());
      textDirectory.textProperty().bindBidirectional(viewModel.pathProperty());
      rootPane.textProperty().bind(javafx.beans.binding.Bindings.concat(
          choiceType.valueProperty(),
          ": ",
          textDirectory.textProperty()
      ));
    }
  }

  @FXML
  void initialize() {
    choiceType.setItems(FXCollections.observableArrayList(DirectoryConfig.Type.values()));
  }

  @FXML
  void openDirectoryAction(final ActionEvent actionEvent) {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setTitle("Choose Directory");
    String oldPath = textDirectory.getText();
    if (null != oldPath && !"".equals(oldPath)) {

    }
    File selectedFile = directoryChooser.showDialog(textDirectory.getScene().getWindow());
    if (selectedFile != null) {
      textDirectory.setText(selectedFile.getAbsolutePath());
    }
  }
}
