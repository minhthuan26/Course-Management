package QuanLiKhoaHoc.GUI.TeacherManage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class teacherMain extends Application {
    public static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        setRoot("addTeacher");
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    public static void setRoot(String name) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(teacherMain.class.getResource(name + ".fxml")));
        scene = new Scene(root);
    }

    public static void setStylesheets(String name) throws IOException {
        scene.getStylesheets().add(Objects.requireNonNull(teacherMain.class.getResource(name + ".css")).toExternalForm());
    }

    public static void Test(String string){
        System.out.println(string);
    }
}
