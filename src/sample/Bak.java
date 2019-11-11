package sample;

import com.sun.org.apache.xpath.internal.objects.XString;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bak extends Application {

    public void start (Stage stage) {
        ;
        Image image = new Image("file:download.jpeg");
        ImageView mv = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(mv);

    Scene scene = new Scene(root, 500, 300);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
    }

}
