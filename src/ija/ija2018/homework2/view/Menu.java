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
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import ija.ija2018.homework2.game.WrongMoveException;

/**
 *
 * @author Adam Janda xjanda26@stud.fit.vutbr.cz
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

    /**
     * Inicializuje výber zo záložiek.
     * @param location Lokacia
     * @param recources Zdroje
     */
    @Override
    public void initialize (URL location, ResourceBundle recources){
          selectionModel = tabPane.getSelectionModel();
    }

    /**
     * Vytvorí novú hru po spustení. Nová hra sa vytvorí v novej záložke a pridelí sa dočasný súbor, kde sa budú
     * ukladať záznami ťahov.
     *
     * Pokiaľ hra má pridelený zdrojový súbor, súbor uź je pracovný.
     *
     * Po zatvorení záložky s hrou, vypíše upozornenie s možnosťou uloženia hry. Pokiaľ uźívateľ bude chcieť uložiť
     * postup hry, otvorí sa dialog a môže uložiť postup hry do súboru, na miesto kde si zvolí. V inakšom príapde je
     * pracovný súbor vymazaný.
     * @param event Udalost
     */
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
                    file = null;

                    tabNewGame.setContent(root);

                    tabNewGame.setOnCloseRequest(e -> {
                        gameCounter--;

                        Boolean answer = ConfirmBox.display("Ukončenie hry","Chcete uložiť hru?");
                        if(answer) {
                            FileChooser fileChooser = new FileChooser();

                            //Set extension filter
                            fileChooser.setInitialDirectory(new File("lib"));
                            FileChooser.ExtensionFilter extFilter =
                                    new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                            fileChooser.getExtensionFilters().add(extFilter);

                            //Show save file dialog
                            Stage stage = (Stage)  guiWindow.getScene().getWindow();
                            File file = fileChooser.showSaveDialog(stage);

                            if(file != null){
                                saveFile(newTab.getSelectedFile(), file);
                            }
                        }

                        newTab.getSelectedFile().delete();
                    });
                }
                else {

                	newTab.resetAndSet();
                    newTab.setFile(selectedFile);
                    selectedFile = null;

                    tabNewGame.setContent(root);

                    tabNewGame.setOnCloseRequest(e -> {
                        gameCounter--;

                        Boolean answer = ConfirmBox.display("Ukončenie hry","Chcete uložiť hru?");
                        if(answer) {
                            FileChooser fileChooser = new FileChooser();

                            //Set extension filter
                            fileChooser.setInitialDirectory(new File("lib"));
                            FileChooser.ExtensionFilter extFilter =
                                    new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                            fileChooser.getExtensionFilters().add(extFilter);

                            //Show save file dialog
                            Stage stage = (Stage)  guiWindow.getScene().getWindow();
                            File file = fileChooser.showSaveDialog(stage);

                            if(file != null){
                                saveFile(newTab.getSelectedFile(), file);
                            }
                        }

                        newTab.getSelectedFile().delete();

                        selectedFile = null;
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongMoveException e1) {

				e1.printStackTrace();
			}

            tabPane.getTabs().add(tabNewGame);
            NewGameTab.tabIndex = tabPane.getTabs().indexOf(tabNewGame);
            selectionModel.selectLast();
        }

    /**
     * Metóda vytvorí dialog, v ktorom si uživateľ vyberie záznam, ktorý bude chcieť nastaviť ako zdrojový.
     * Obsah z neho sa prekopíruje do nového súboru a k práci sa používa tento novovytvorený súbor.
     *
     * Po prekopírovaní obsahu je ukazovateľ na zdrojový súbor zrušený.
     *
     * @param event Udalost
     * @throws IOException IOException
     */
    @FXML public void loadGame(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("lib"));
        fc.setTitle("Náčítať hru");
        Stage stage = (Stage)  guiWindow.getScene().getWindow();
        selectedFile = fc.showOpenDialog(stage);

        if (selectedFile == null) {
            return;
        }

        String path = "lib/loadgame" + gameCounter + ".txt";
        file = new File(path);
        file.getParentFile().mkdirs();
        file.createNewFile();

        saveFile(selectedFile,file);

        selectedFile = file;

        newGame(event);
    }

    /**
     * Prekopíruje obsah zdrojového súboru do súboru cieľového.
     *
     * @param source Zdrojový súbor
     * @param destination Cieľový súbor
     */
    private void saveFile(File source, File destination){
        try {
            Scanner scan = new Scanner(source);
            ArrayList<String> listS = new ArrayList<>();

            while (scan.hasNextLine()) {
                listS.add(scan.nextLine());
            }
            scan.close();

            PrintWriter writer = new PrintWriter(destination);

            for (String str : listS){
                writer.print(str);
                writer.print("\n");
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}