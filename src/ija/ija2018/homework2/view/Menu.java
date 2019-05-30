package ija.ija2018.homework2.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ija.ija2018.homework2.game.WrongMoveException;

/**
 *
 * @author Adam Janda <xjanda26@stud.fit.vutbr.cz>
 * */

public class Menu implements Initializable {

    @FXML private Button btnNewGame;
    @FXML private Button btnLoadGame;

    @FXML private TabPane tabPane;
    @FXML private Tab tabNewGame;
    @FXML private AnchorPane guiWindow;

    private SingleSelectionModel<Tab> selectionModel;
    private int gameCounter = 0;
    private File selectedFile = null;
    private File file;

    @Override
    public void initialize (URL location, ResourceBundle recources){
        //btnNewGame.setOnMouseClicked(addTabNewGame);

        selectionModel = tabPane.getSelectionModel();
    }

    @FXML public void newGame (ActionEvent event) {
            tabNewGame = new Tab();

            try {

                tabNewGame.setText("Hra " + ++gameCounter);
                tabNewGame.setClosable(true);
                tabNewGame.setId("newGame");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ija/ija2018/homework2/view/newGameTab.fxml"));
                Parent root = (Parent) loader.load();
                NewGameTab newTab = loader.getController();

                if ( selectedFile == null) {

                    String path = "lib/newgame" + gameCounter + ".txt";
                    file = new File(path);
                    file.getParentFile().mkdirs();
                    file.createNewFile();

                    newTab.setFile(file);

                    tabNewGame.setContent(root);

                    tabNewGame.setOnCloseRequest(e -> {
                        gameCounter--;
                        file.delete();
                    });
                }
                else {
                	if(newTab.getGame().loadgame(selectedFile))
                		System.out.println("podarilo sa nacitat hru");
                	else 
                		System.err.println("nepodarilo sa nacitat hru");
                	newTab.resetFigures();
                	newTab.setFiguresOnBoard();
                    newTab.setFile(selectedFile);
                    selectedFile = null;

                    tabNewGame.setContent(root);

                    tabNewGame.setOnCloseRequest(e -> {
                        gameCounter--;
                        selectedFile = null;
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongMoveException e1) {
				// TODO Pridat hlasku o zle zapisanej hre
				e1.printStackTrace();
			}

            tabPane.getTabs().add(tabNewGame);
            NewGameTab.tabIndex = tabPane.getTabs().indexOf(tabNewGame);
            selectionModel.selectLast();
        }

    @FXML public void loadGame(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("lib"));
        fc.setTitle("Náčítať hru");
        Stage stage = (Stage)  guiWindow.getScene().getWindow();
        selectedFile = fc.showOpenDialog(stage);

        if (selectedFile == null) {
            return;
        }
        newGame(event);
    }
}