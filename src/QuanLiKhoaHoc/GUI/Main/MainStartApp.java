package QuanLiKhoaHoc.GUI.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class MainStartApp extends Application {

    public static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        setRoot("main");
        setStylesheets("main");
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quản lí khoá học");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    public static void setRoot(String name) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainStartApp.class.getResource(name + ".fxml")));
        scene = new Scene(root);
    }

    public static void setStylesheets(String name) throws IOException {
        scene.getStylesheets().add(Objects.requireNonNull(MainStartApp.class.getResource(name + ".css")).toExternalForm());
    }

    public static void Test(String string){
        System.out.println(string);
    }
}
