package jfsmw;

import jfsmw.config.ConfigService;
import jfsmw.config.application.AppConfig;
import jfsmw.config.project.ProjectConfig;
import jfsmw.ui.javafx.MainApp;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * Created by Christoph Graupner on 2018-01-21.
 *
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@SpringBootApplication
public class JfsMwApplication implements ApplicationRunner {
  @Configuration
  class AppConfiguration {
    @Bean
    public Path applicationPath() {
      return Paths.get(".", JfsMwConsts.JFSMW_HOMEFOLDER_NAME);
    }

    @Bean
    public AppConfig beanAppConfig(@Autowired Path applicationPath, ConfigService configService) {
      final File file = applicationPath.toFile();
      AppConfig  appConfig;
      if (!file.exists()) {
        if (!file.mkdirs()) {
          throw new IllegalStateException(file.getAbsolutePath() + " can't be created.");
        }
      }
      final File fileAppConfig = new File(file, JfsMwConsts.APP_JMWFS_APP_CONFIG_XML);
      if (fileAppConfig.exists()) {
        appConfig = configService.readAppConfig(fileAppConfig.toPath());
      } else {
        appConfig = new AppConfig();
      }
      return appConfig;
    }

    @Bean
    ResourceBundle versionBundle() {
      return ResourceBundle.getBundle("JfsMwVersion");
    }

    @Bean
    FileSystemManager fileSystemManager() throws FileSystemException {
      return VFS.getManager();
    }
  }

  private static JfsMwApplication instance = null;
  @Getter
  @Autowired
  AppConfig     appConfig;
  @Autowired
  Path          applicationPath;
  @Getter
  @Setter
  ProjectConfig currentProject;
  @Autowired
  private ConfigService                  configService;
  @Autowired
  private ConfigurableApplicationContext springContext;

  public static JfsMwApplication getInstance() {
    return instance;
  }

  public static void main(String[] args) {
    SpringApplication.run(JfsMwApplication.class, args);
  }

  public ConfigurableApplicationContext getSpringContext() {
    return springContext;
  }

  @Override
  public void run(final ApplicationArguments args) throws Exception {
    instance = this;
    MainApp.main(args.getSourceArgs());
  }

  @PreDestroy
  public void shutdown() {
    configService.saveAppConfig(getAppConfig(), getApplicationPath().resolve(JfsMwConsts.APP_JMWFS_APP_CONFIG_XML));
  }

  private Path getApplicationPath() {
    //System.getProperty("user.home")
    return applicationPath;
  }
}
