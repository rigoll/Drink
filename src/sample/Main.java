package sample;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;;
import static java.lang.Thread.sleep;

public class Main extends Application {





    //Bak bakObj = new Bak();
    //bakObj.start(Stage.stage);

    Button button1;
    Button button2;
    Button button3;
    Button button4;


    Button buttonx;

    public static void main(String[] args) {
    Application.launch(Bak.class, args);    



    }

    @Override
    public void start(Stage primaryStage) {


        primaryStage.setTitle("Title of Window");
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalOutput pin01 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Pump", PinState.LOW);
        final GpioPinDigitalOutput pin02 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Pump2", PinState.LOW);
        pin01.setShutdownOptions(true, PinState.LOW);
        pin02.setShutdownOptions(true, PinState.LOW);
        button1 = new Button("Vin (Rött)");
        button2 = new Button("Vin (Vitt)");
        button3 = new Button("Whiskey)");
        buttonx = new Button("Avsluta");


        button1.setOnTouchPressed(new EventHandler<TouchEvent>() {
            @Override public void handle(TouchEvent event ) {
                pin01.high();
            }
        });
        button1.setOnTouchReleased(new EventHandler<TouchEvent> () {
            @Override public void handle(TouchEvent event ) {
                pin01.low();
            }

        });


        button2.setOnTouchPressed(new EventHandler<TouchEvent>() {
            @Override public void handle(TouchEvent event ) {
                pin02.high();
            }
        });
        button2.setOnTouchReleased(new EventHandler<TouchEvent> () {
            @Override public void handle(TouchEvent event ) {
                pin02.low();
            }

            });


        // Using Button x to close
        buttonx.setOnAction(e -> primaryStage.close());

        //Button layout
        StackPane layout = new StackPane();
        layout.getChildren().add(button1);
        button1.setTranslateX(-200);
        button1.setTranslateY(20);
        button1.setMaxSize(100, 100);
        layout.getChildren().add(button2);
        button2.setTranslateX(50);
        button2.setTranslateY(20);
        button2.setMaxSize(100, 100);
        layout.getChildren().add(buttonx);
        buttonx.setTranslateX(300);
        buttonx.setTranslateY(20);
        buttonx.setMaxSize(100, 100);
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


