package sample;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class Main extends Application {

    Button button1;
    Button button2;


    Button buttonx;

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Title of Window");
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalOutput pin01 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Pump", PinState.LOW);
        final GpioPinDigitalOutput pin02 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Pump2", PinState.LOW);
        pin01.setShutdownOptions(true, PinState.LOW);
        pin02.setShutdownOptions(true, PinState.LOW);
        button1 = new Button("Vin");
        button2 = new Button("Whiskey");


        buttonx = new Button("Avsluta");


        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // Wine for 4 sec
                pin01.toggle();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                pin01.toggle();

            }
        });


        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                pin02.toggle();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                pin02.toggle();
            }



        });

        // Using Button x to close
        buttonx.setOnAction(e -> primaryStage.close());

        //Button layout
        StackPane layout = new StackPane();
        layout.getChildren().add(button1);
        button1.setTranslateX(-70);
        button1.setTranslateY(20);
        layout.getChildren().add(button2);
        button2.setTranslateX(50);
        button2.setTranslateY(20);
        layout.getChildren().add(buttonx);
        buttonx.setTranslateX(180);
        buttonx.setTranslateY(20);
        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.getScene().getAccelerators().put(
                KeyCombination.keyCombination ("CTRL+C"), new Runnable() {
                    @Override
                    public void run() {
                        Platform.exit();
                    }
                } );
    }




}


