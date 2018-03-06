package jfsmw.ui.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfsmw.JfsMwApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ResourceBundle;

/**
 * Created by Christoph Graupner on 2018-01-21.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class MainApp extends Application {
    private Parent                         root;
    private ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = JfsMwApplication.getInstance().getSpringContext();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/main-app.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        root = fxmlLoader.load();
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        ResourceBundle bundle = (ResourceBundle) springContext.getBean("versionBundle");

        primaryStage.setTitle("JFileSync MW " + bundle.getString("jfsmw.version"));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }
}
