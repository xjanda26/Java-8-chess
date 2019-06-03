package ija.ija2018.homework2.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Adam Janda <xjanda26@stud.fit.vutbr.cz>
 * */


/**
 * Trieda predstavuje okno výzvy, pri zatvaraní záložiek a okna aplikácie.
 * Obsahuje metódu display, ktorá vytvorí nové okno so správou a možnosťou odpovedať áno alebo nie.
 * Vytvorené okno sa dá zatvoriť jedine odpovedaním.
 */
public class ConfirmBox {
    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        Label label = new Label();
        label.setText(message);

        //Vytvorenie dvoch tlacitok
        Button yesButton = new Button("Áno");
        Button noButton = new Button("Nie");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        } );

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        } );

        HBox layout = new HBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
