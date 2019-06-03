package ija.ija2018.homework2;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import ija.ija2018.homework2.view.ConfirmBox;

import java.io.IOException;

/**
 *
 * @author Adam Janda <xjanda26@stud.fit.vutbr.cz>
 * */

public class Main extends Application{

    private Stage window;

    @Override
    public void start(Stage primaryStage){
        try{
            window = primaryStage;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ija/ija2018/homework2/view/ija-app-gui.fxml"));
            Parent root;

            try{
                root = loader.load();
            }catch (IOException ioe){
                return;
            }

            Scene scene = new Scene(root, Color.TRANSPARENT);
            scene.getStylesheets().add("ija/ija2018/homework2/view/style.css");
            window.setScene(scene);
            window.setTitle("ija-app");

            window.setOnCloseRequest(e -> {
                e.consume();
                closeApplication();
            });

            window.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    public void closeApplication(){
        Boolean answer = ConfirmBox.display("Ukončiť aplikáciu","Naozaj chcete zatvoriť aplikáciu?");
        if(answer) this.window.close();
    }
}
