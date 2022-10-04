package QuanLiKhoaHoc.GUI.CourseManage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class MainEditCourseOnline extends Application {
    public static Scene scene;
    public void main(String[] args){launch(args);}
    @Override
    public void start(Stage stage) throws Exception {
        setRoot("EditCourseOnlineGUI");
        setStylesheets("../Main/main");
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("Sửa khóa học online");
        stage.initStyle(StageStyle.TRANSPARENT);

//        primaryStage.setMaximized(true);
        stage.show();
    }
    public static void setRoot(String name) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainAddCourse.class.getResource(name + ".fxml")));
        scene = new Scene(root);
    }

    public static void setStylesheets(String name) throws IOException {
        scene.getStylesheets().add(Objects.requireNonNull(MainAddCourse.class.getResource(name + ".css")).toExternalForm());
    }
}
