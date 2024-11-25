package it.uniroma2.ispw;

import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.enums.TypesOfUIs;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.CampiVuotiExeption;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.view.cli.CliController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class    Main extends Application {


    public static TypesOfPersistenceLayer getPersistenceLayer() {
        return Conf.getConf().getTypesOfPersistenceLayer();
    }

    public static void main(String[] args) throws SystemException, IOException, LoginException, ItemNotFoundException, SQLException, CampiVuotiExeption {
        setPersistenceLayerAndUi();
        if (Conf.getConf().getTypesOfUIs().equals(TypesOfUIs.JAVAFX))
            launch();
        else
            new CliController().start();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        ChangePage istanza = ChangePage.getChangePage();
        istanza.setStage(stage);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void setPersistenceLayerAndUi() {
        Conf conf = Conf.getConf();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            //persistence layer
            if (properties.getProperty("persistence.layer").equals("FileSystem")) {
                conf.setPersistenceLayer(TypesOfPersistenceLayer.FILE_SYSTEM);
            } else {
                conf.setPersistenceLayer(TypesOfPersistenceLayer.JDBC);
            }

            //user interface
            if (properties.getProperty("ui").equals("javafx")) {
                conf.setTypesOfUIs(TypesOfUIs.JAVAFX);
            } else {
                conf.setTypesOfUIs(TypesOfUIs.CLI);
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}

