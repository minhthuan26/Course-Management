package QuanLiKhoaHoc.GUI.Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane wrapPane;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private AnchorPane windowPane;

    private Stage primaryStage;

    @FXML
    private Button minimizeWindowBtn;

    @FXML
    private Button resizeWindowBtn;

    @FXML
    private Button closeWindowBtn;

    @FXML
    private Button teacherManageBtn;

    @FXML
    private Button studentManageBtn;

    @FXML
    private Button assignmentManageBtn;

    @FXML
    private Button courseManageBtn;

    @FXML
    private Button resultManageBtn;

    private static double xOffset = 0;
    private static double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Handle();
    }

    public void Handle(){
        closeWindowBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage = (Stage) wrapPane.getScene().getWindow();
                primaryStage.close();
            }
        });

        minimizeWindowBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage = (Stage) wrapPane.getScene().getWindow();
                primaryStage.setIconified(true);
            }
        });

        resizeWindowBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage = (Stage) wrapPane.getScene().getWindow();
                if(primaryStage.isMaximized())
                    primaryStage.setMaximized(false);
                else
                    primaryStage.setMaximized(true);
            }
        });

        studentManageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    contentPane.getChildren().clear();
                    contentPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../StudentManage/studentManage.fxml"))));
                    AnchorPane content = (AnchorPane) contentPane.getChildren().get(0);
                    AnchorPane.setBottomAnchor(content, 0.0);
                    AnchorPane.setTopAnchor(content, 0.0);
                    AnchorPane.setLeftAnchor(content, 0.0);
                    AnchorPane.setRightAnchor(content, 0.0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        teacherManageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    contentPane.getChildren().clear();
                    contentPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../TeacherManage/teacherManage.fxml"))));
                    AnchorPane content = (AnchorPane) contentPane.getChildren().get(0);
                    AnchorPane.setBottomAnchor(content, 0.0);
                    AnchorPane.setTopAnchor(content, 0.0);
                    AnchorPane.setLeftAnchor(content, 0.0);
                    AnchorPane.setRightAnchor(content, 0.0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        resultManageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    contentPane.getChildren().clear();
                    contentPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../ResultManage/resultManage.fxml"))));
                    AnchorPane content = (AnchorPane) contentPane.getChildren().get(0);
                    AnchorPane.setBottomAnchor(content, 0.0);
                    AnchorPane.setTopAnchor(content, 0.0);
                    AnchorPane.setLeftAnchor(content, 0.0);
                    AnchorPane.setRightAnchor(content, 0.0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        courseManageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    contentPane.getChildren().clear();
                    contentPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../CourseManage/courseManage.fxml"))));
                    AnchorPane content = (AnchorPane) contentPane.getChildren().get(0);
                    AnchorPane.setBottomAnchor(content, 0.0);
                    AnchorPane.setTopAnchor(content, 0.0);
                    AnchorPane.setLeftAnchor(content, 0.0);
                    AnchorPane.setRightAnchor(content, 0.0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        assignmentManageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    contentPane.getChildren().clear();
                    contentPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../AssignmentManage/assignmentManage.fxml"))));
                    AnchorPane content = (AnchorPane) contentPane.getChildren().get(0);
                    AnchorPane.setBottomAnchor(content, 0.0);
                    AnchorPane.setTopAnchor(content, 0.0);
                    AnchorPane.setLeftAnchor(content, 0.0);
                    AnchorPane.setRightAnchor(content, 0.0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        windowPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage = (Stage) wrapPane.getScene().getWindow();
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });

        windowPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage = (Stage) wrapPane.getScene().getWindow();
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });
    }
}
