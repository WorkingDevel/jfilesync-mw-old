package jfsmw.ui.javafx.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import jfsmw.ui.javafx.model.ProjectConfigViewModel;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Christoph Graupner on 2018-01-22.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@Component
public class ProjectConfigController {
  @FXML              Button                         btnOk;
  //    @FXML BorderPane compareConfig;
  @FXML              ProjectCompareConfigController compareConfigController;
  @FXML              TreeView                       treeViewDirectories;
  @FXML              TextField                      txtName;
  @Autowired private ApplicationContext             applicationContext;
  @FXML private      Button                         btnCancel;
  private            ProjectConfigViewModel         projectConfigViewModel;
  private            TreeItem<String>               root;

  @FXML
  public void initialize() {
    root = new TreeItem<>("Compare Tasks");

    treeViewDirectories.setRoot(root);
    treeViewDirectories.setShowRoot(true);
  }

  public void setProjectConfigViewModel(@NonNull final ProjectConfigViewModel projectConfigViewModel) {
    this.projectConfigViewModel = projectConfigViewModel;
    txtName.textProperty().bindBidirectional(projectConfigViewModel.getNameProperty());
  }

  @FXML
  void submitAction(final ActionEvent actionEvent) {

  }

  @FXML
  void cancelAction(final ActionEvent event) {
    ((Stage) btnCancel.getScene().getWindow()).close();
  }

  @FXML
  void compareAddAction(final ActionEvent event) {
    TextInputDialog dlg = new TextInputDialog("compare");
    dlg.setTitle("Add comparison");
    dlg.getDialogPane().setContentText("Describing name for comparision:");
    dlg.setHeaderText("Add new dir for comparison");
    dlg.showAndWait()
       .ifPresent(compareName -> {
         final TreeItem<String> compareItem = new TreeItem<>(compareName);
         root.getChildren().add(compareItem);
       });
  }
}
