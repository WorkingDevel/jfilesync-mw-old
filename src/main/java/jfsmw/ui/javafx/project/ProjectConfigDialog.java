package jfsmw.ui.javafx.project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import jfsmw.ui.javafx.model.ProjectConfigViewModel;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

/**
 * Created by Christoph Graupner on 2018-01-23.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class ProjectConfigDialog extends Dialog<ProjectConfigViewModel> {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ApplicationContext applicationContext;
    private ProjectConfigController projectConfigController;
    private ProjectConfigViewModel projectConfigViewModel;

    public ProjectConfigDialog(final ApplicationContext applicationContext) throws IOException {
        this.applicationContext = applicationContext;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/project-config.fxml"));
        fxmlLoader.setControllerFactory(this.applicationContext::getBean);
        final Parent projectScene = fxmlLoader.load();
        projectConfigController = fxmlLoader.getController();
        getDialogPane().setContent(projectScene);

        // Hide this current window (if this is what you want)
//            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        setResultConverter(param -> {
            if (param.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return getProjectConfigViewModel();
            } else {
                return null;
            }
        });
        getDialogPane().getButtonTypes().add(new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE));
        getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
    }

    ProjectConfigViewModel getProjectConfigViewModel() {
        return projectConfigViewModel;
    }

    public void setProjectConfigViewModel(@NonNull final ProjectConfigViewModel projectConfigViewModel) {
        this.projectConfigViewModel = projectConfigViewModel;
        projectConfigController.setProjectConfigViewModel(this.projectConfigViewModel);
    }
}
