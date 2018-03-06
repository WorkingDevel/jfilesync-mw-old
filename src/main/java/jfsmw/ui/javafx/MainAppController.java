package jfsmw.ui.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jfsmw.JfsMwApplication;
import jfsmw.config.project.ProjectConfig;
import jfsmw.ui.javafx.model.ProjectConfigViewModel;
import jfsmw.ui.javafx.project.ProjectConfigDialog;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * Created by Christoph Graupner on 2018-01-21.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@Component
public class MainAppController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @FXML
    Button btnNew;
    @FXML
    Button btnSave;
    @FXML
    Button btnSearch;
    @FXML
    Button btnRescan;
    @FXML
    Button btnSync;
    ObjectProperty<ProjectConfig> projectConfigObjectProperty = new SimpleObjectProperty<>();
    @Autowired
    private JfsMwApplication   application;
    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    public void initialize() {
        GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");
        btnNew.setGraphic(
                fontAwesome.create(FontAwesome.Glyph.FILE_ALT).color(Color.CYAN).size(24).useGradientEffect()
        );
        btnSave.setGraphic(fontAwesome.create(FontAwesome.Glyph.SAVE).color(Color.ORANGE).size(24).useGradientEffect());

        btnSave.disableProperty().bind(projectConfigObjectProperty.isNull());

        btnSearch.setGraphic(
                fontAwesome.create(FontAwesome.Glyph.SEARCH).color(Color.GREEN).size(24).useGradientEffect()
        );
        btnSearch.disableProperty().bind(projectConfigObjectProperty.isNull());

        btnRescan.setGraphic(
                fontAwesome.create(FontAwesome.Glyph.REPEAT).color(Color.AQUA).size(24).useGradientEffect()
        );
        btnRescan.disableProperty().bind(projectConfigObjectProperty.isNull());

        btnSync.setGraphic(
                fontAwesome.create(FontAwesome.Glyph.CHEVRON_CIRCLE_RIGHT)
                           .color(Color.ORANGERED)
                           .size(24)
                           .useGradientEffect()
        );
        btnSync.disableProperty().bind(projectConfigObjectProperty.isNull());

        projectConfigObjectProperty.setValue(application.getCurrentProject());
    }

    @FXML
    void newProjectDialogAction(final ActionEvent actionEvent) throws IOException {
        ProjectConfigDialog configDialog = new ProjectConfigDialog(applicationContext);
        configDialog.setProjectConfigViewModel(new ProjectConfigViewModel(new ProjectConfig()));
        configDialog.setHeaderText("New Project");
        configDialog.setTitle("JFileSync MW - New Project");

        configDialog.showAndWait().ifPresent(response -> {
            application.setCurrentProject(response.getProjectConfig());
            projectConfigObjectProperty.setValue(application.getCurrentProject());
        });
    }

    @FXML
    void exitAction(final ActionEvent actionEvent) {
        Stage stage = (Stage) btnNew.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    ProjectConfig getProjectConfig() {
        return projectConfigObjectProperty.getValue();
    }
}
