package ija.ija2018.homework2.view;

import ija.ija2018.homework2.GameFactory;
import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;
import ija.ija2018.homework2.game.Board;
import ija.ija2018.homework2.common.Game;

import ija.ija2018.homework2.game.WrongMoveException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;

import java.util.*;

import java.lang.*;

/**
 *
 * @author Adam Janda <xjanda26@stud.fit.vutbr.cz>
 * */

public class NewGameTab implements Initializable {

    public static int tabIndex = 0;

    @FXML public TableView<HistoryTable> tableView;

    @FXML public TableColumn<HistoryTable,String> colTurn;
    @FXML public TableColumn<HistoryTable,String> colWhite;
    @FXML public TableColumn<HistoryTable,String> colBlack;

    @FXML Rectangle kingW;

    @FXML Rectangle kingB;

    /**
     *
     * Vrchný riadok hracej dosky(riadok 8) + políčka na tomto riadku
     */
    @FXML Pane row0;
    @FXML Rectangle field0x0;
    @FXML Rectangle field1x0;
    @FXML Rectangle field2x0;
    @FXML Rectangle field3x0;
    @FXML Rectangle field4x0;
    @FXML Rectangle field5x0;
    @FXML Rectangle field6x0;
    @FXML Rectangle field7x0;

    /**
     *
     * riadok 7 + políčka na tomto riadku
     */
    @FXML Pane row1;
    @FXML Rectangle field0x1;
    @FXML Rectangle field1x1;
    @FXML Rectangle field2x1;
    @FXML Rectangle field3x1;
    @FXML Rectangle field4x1;
    @FXML Rectangle field5x1;
    @FXML Rectangle field6x1;
    @FXML Rectangle field7x1;

    /**
     *
     * riadok 6 + políčka na tomto riadku
     */
    @FXML Pane row2;
    @FXML Rectangle field0x2;
    @FXML Rectangle field1x2;
    @FXML Rectangle field2x2;
    @FXML Rectangle field3x2;
    @FXML Rectangle field4x2;
    @FXML Rectangle field5x2;
    @FXML Rectangle field6x2;
    @FXML Rectangle field7x2;

    /**
     *
     * riadok 5 + políčka na tomto riadku
     */
    @FXML Pane row3;
    @FXML Rectangle field0x3;
    @FXML Rectangle field1x3;
    @FXML Rectangle field2x3;
    @FXML Rectangle field3x3;
    @FXML Rectangle field4x3;
    @FXML Rectangle field5x3;
    @FXML Rectangle field6x3;
    @FXML Rectangle field7x3;

    /**
     *
     * riadok 4 + políčka na tomto riadku
     */
    @FXML Pane row4;
    @FXML Rectangle field0x4;
    @FXML Rectangle field1x4;
    @FXML Rectangle field2x4;
    @FXML Rectangle field3x4;
    @FXML Rectangle field4x4;
    @FXML Rectangle field5x4;
    @FXML Rectangle field6x4;
    @FXML Rectangle field7x4;

    /**
     *
     * riadok 3 + políčka na tomto riadku
     */
    @FXML Pane row5;
    @FXML Rectangle field0x5;
    @FXML Rectangle field1x5;
    @FXML Rectangle field2x5;
    @FXML Rectangle field3x5;
    @FXML Rectangle field4x5;
    @FXML Rectangle field5x5;
    @FXML Rectangle field6x5;
    @FXML Rectangle field7x5;

    /**
     *
     * riadok 2 + políčka na tomto riadku
     */
    @FXML Pane row6;
    @FXML Rectangle field0x6;
    @FXML Rectangle field1x6;
    @FXML Rectangle field2x6;
    @FXML Rectangle field3x6;
    @FXML Rectangle field4x6;
    @FXML Rectangle field5x6;
    @FXML Rectangle field6x6;
    @FXML Rectangle field7x6;

    /**
     *
     * Spodný riadok (riadok 1) + políčka na tomto riadku
     * */
    @FXML Pane row7;
    @FXML Rectangle field0x7;
    @FXML Rectangle field1x7;
    @FXML Rectangle field2x7;
    @FXML Rectangle field3x7;
    @FXML Rectangle field4x7;
    @FXML Rectangle field5x7;
    @FXML Rectangle field6x7;
    @FXML Rectangle field7x7;

    @FXML VBox vbox;
    @FXML Pane boardPane;

    @FXML Rectangle previousPlace;
    @FXML ListView listView;

    @FXML Button btnUndo;
    @FXML Button btnRedo;
    @FXML Button btnSwitch;

    @FXML Button btnStepBegin;
    @FXML Button btnStepBack;
    @FXML Button btnStepForward;
    @FXML Button btnStepEnd;
    @FXML Slider slider;

    private final List<Pane> panes = new ArrayList<>();
    private final List<Rectangle> pawnsB = new ArrayList<>();
    private final List<Rectangle> rooksB = new ArrayList<>();
    private final List<Rectangle> knightsB = new ArrayList<>();
    private final List<Rectangle> bishopsB = new ArrayList<>();
    private final List<Rectangle> queensB = new ArrayList<>();

    private final List<Rectangle> pawnsW = new ArrayList<>();
    private final List<Rectangle> rooksW = new ArrayList<>();
    private final List<Rectangle> knightsW = new ArrayList<>();
    private final List<Rectangle> bishopsW = new ArrayList<>();
    private final List<Rectangle> queensW = new ArrayList<>();
    private Point2D offset = new Point2D(0.0d,0.0d);

    private boolean movingWhite = true;
    private boolean movingBlack = false;
    private boolean movePass = true;
    private boolean historyPlay = false;
    private boolean autoPlay = true;

    private Game game;
    private Board board;

    private Figure movingFigure;
    private File selectedFile;

    private int boardSize;
    private int step = 0;
    private int stepPlay = 0;


    private int pawnWCounter = 0;
    private int rookWCounter = 0;
    private int knightWCounter = 0;
    private int bishopWCounter = 0;
    private int queenWCounter = 0;

    private int pawnBCounter = 0;
    private int rookBCounter = 0;
    private int knightBCounter = 0;
    private int bishopBCounter = 0;
    private int queenBCounter = 0;

    private Image whitePawn ;
    private Image whiteRook ;
    private Image whiteKnight ;
    private Image whiteBishop;
    private Image whiteQueen ;
    private Image whiteKing;

    private Image blackPawn ;
    private Image blackRook ;
    private Image blackKnight;
    private Image blackBishop ;
    private Image blackQueen;
    private Image blackKing ;

    @FXML private Rectangle frame;

    /**
     *
     * Metóda vykreslí a nastaví figúrky podľa objektu 'board'.
     * */
    private void setFiguresOnBoard(){

        pawnWCounter = 0;
        rookWCounter = 0;
        knightWCounter = 0;
        bishopWCounter = 0;
        queenWCounter = 0;

        pawnBCounter = 0;
        rookBCounter = 0;
        knightBCounter = 0;
        bishopBCounter = 0;
        queenBCounter = 0;

        for (int row = 1; row <= boardSize; row++){
            for (int column = 1; column <= boardSize; column++ ){
                if (! board.getField(column, row). isEmpty() ) {
                    Rectangle rect = new Rectangle();
                    rect.setOnMouseDragged(this::figureMoves);
                    rect.setOnMousePressed(this::figureStarts);
                    rect.setOnMouseReleased(this::figureLands);
                    rect.setCursor(Cursor.CLOSED_HAND);
                    rect.setFill(Color.web("#ffffff00"));
                    rect.setHeight(50.0d);
                    rect.setWidth(50.0d);
                    rect.setStroke(Color.TRANSPARENT);
                    rect.setStrokeType(StrokeType.INSIDE);
                    rect.setArcHeight(5.0);
                    rect.setArcWidth(5.0);
                    switch (board.getField(column, row).get().getState()) {
                        case "W Pawn":
                            pawnsW.add(rect);
                            boardPane.getChildren().add(rect);
                            pawnsW.get(pawnWCounter).setLayoutX((column - 1) * 50 + 40);
                            pawnsW.get(pawnWCounter).setLayoutY(440 - 50* row);
                            pawnsW.get(pawnWCounter).setFill(new ImagePattern(whitePawn));
                            pawnsW.get(pawnWCounter).setOpacity(1.0d);
                            pawnWCounter++;
                            break;
                        case "W Rook":
                            rooksW.add(rect);
                            boardPane.getChildren().add(rect);
                            rooksW.get(rookWCounter).setLayoutX((column - 1) * 50 + 40);
                            rooksW.get(rookWCounter).setLayoutY(440 - 50* row);
                            rooksW.get(rookWCounter).setFill(new ImagePattern(whiteRook));
                            rooksW.get(rookWCounter).setOpacity(1.0d);
                            rookWCounter++;
                            break;
                        case "W Knight":
                            knightsW.add(rect);
                            boardPane.getChildren().add(rect);
                            knightsW.get(knightWCounter).setLayoutX((column - 1) * 50 + 40);
                            knightsW.get(knightWCounter).setLayoutY(440 - 50* row);
                            knightsW.get(knightWCounter).setFill(new ImagePattern(whiteKnight));
                            knightsW.get(knightWCounter).setOpacity(1.0d);
                            knightWCounter++;
                            break;
                        case "W Bishop":
                            bishopsW.add(rect);
                            boardPane.getChildren().add(rect);
                            bishopsW.get(bishopWCounter).setLayoutX((column - 1) * 50 + 40);
                            bishopsW.get(bishopWCounter).setLayoutY(440 - 50* row);
                            bishopsW.get(bishopWCounter).setFill(new ImagePattern(whiteBishop));
                            bishopsW.get(bishopWCounter).setOpacity(1.0d);
                            bishopWCounter++;
                            break;
                        case "W Queen":
                            queensW.add(rect);
                            boardPane.getChildren().add(rect);
                            queensW.get(queenWCounter).setLayoutX((column - 1) * 50 + 40);
                            queensW.get(queenWCounter).setLayoutY(440 - 50* row);
                            queensW.get(queenWCounter).setFill(new ImagePattern(whiteQueen));
                            queensW.get(queenWCounter).setOpacity(1.0d);
                            queenWCounter++;
                            break;
                        case "W King":
                            kingW.setLayoutX((column - 1) * 50 + 40);
                            kingW.setLayoutY(440 - 50* row);
                            kingW.setFill(new ImagePattern(whiteKing));
                            kingW.setOpacity(1.0d);
                            break;
                        // Black figures
                        case "B Pawn":
                            pawnsB.add(rect);
                            boardPane.getChildren().add(rect);
                            pawnsB.get(pawnBCounter).setLayoutX((column - 1) * 50 + 40);
                            pawnsB.get(pawnBCounter).setLayoutY(440 - 50* row);
                            pawnsB.get(pawnBCounter).setFill(new ImagePattern(blackPawn));
                            pawnsB.get(pawnBCounter).setOpacity(1.0d);
                            pawnBCounter++;
                            break;
                        case "B Rook":
                            rooksB.add(rect);
                            boardPane.getChildren().add(rect);
                            rooksB.get(rookBCounter).setLayoutX((column - 1) * 50 + 40);
                            rooksB.get(rookBCounter).setLayoutY(440 - 50* row);
                            rooksB.get(rookBCounter).setFill(new ImagePattern(blackRook));
                            rooksB.get(rookBCounter).setOpacity(1.0d);
                            rookBCounter++;
                            break;
                        case "B Knight":
                            knightsB.add(rect);
                            boardPane.getChildren().add(rect);
                            knightsB.get(knightBCounter).setLayoutX((column - 1) * 50 + 40);
                            knightsB.get(knightBCounter).setLayoutY(440 - 50* row);
                            knightsB.get(knightBCounter).setFill(new ImagePattern(blackKnight));
                            knightsB.get(knightBCounter).setOpacity(1.0d);
                            knightBCounter++;
                            break;
                        case "B Bishop":
                            bishopsB.add(rect);
                            boardPane.getChildren().add(rect);
                            bishopsB.get(bishopBCounter).setLayoutX((column - 1) * 50 + 40);
                            bishopsB.get(bishopBCounter).setLayoutY(440 - 50* row);
                            bishopsB.get(bishopBCounter).setFill(new ImagePattern(blackBishop));
                            bishopsB.get(bishopBCounter).setOpacity(1.0d);
                            bishopBCounter++;
                            break;
                        case "B Queen":
                            queensB.add(rect);
                            boardPane.getChildren().add(rect);
                            queensB.get(queenBCounter).setLayoutX((column - 1) * 50 + 40);
                            queensB.get(queenBCounter).setLayoutY(440 - 50* row);
                            queensB.get(queenBCounter).setFill(new ImagePattern(blackQueen));
                            queensB.get(queenBCounter).setOpacity(1.0d);
                            queenBCounter++;
                            break;
                        case "B King":
                            kingB.setLayoutX((column - 1) * 50 + 40);
                            kingB.setLayoutY(440 - 50* row);
                            kingB.setFill(new ImagePattern(blackKing));
                            kingB.setOpacity(1.0d);
                            break;
                    }
                }
            }
        }
        previousPlace.setOpacity(0.0d);
    }

    /**
     *
     * Metóda nastaví listu štvorcov súradnice 0,0 a priehľadnosť.
     * Použivané v metóde 'resetFigures'
     *
     * @param list List štvrocv, ktorého elementom sa nastavia súradnice na 0,0 a priehľadnosť.
     * */
    private void resetListFigures(List<Rectangle> list){
        for (Rectangle rec : list){
            rec.setOpacity(0.0d);
            rec.setLayoutX(0.0d);
            rec.setLayoutY(0.0d);
        }
        list.clear();
    }

    /**
     *
     * Metóda nastaví všetkým figúrkam na hracej ploche súradnice 0,0 a priehľadnosť.
     * Využíva metódu 'resetListFigures'
     * */
    private void resetFigures(){
        resetListFigures(pawnsW);
        resetListFigures(rooksW);
        resetListFigures(knightsW);
        resetListFigures(bishopsW);

        resetListFigures(queensW);

        kingW.setLayoutX(0);
        kingW.setLayoutY(0);
        kingW.setOpacity(0.0d);

        resetListFigures(pawnsB);
        resetListFigures(rooksB);
        resetListFigures(knightsB);
        resetListFigures(bishopsB);

        resetListFigures(queensB);

        kingB.setLayoutX(0);
        kingB.setLayoutY(0);
        kingB.setOpacity(0.0d);
    }

    /**
     *
     * Metóda nastavuje pravidlo, ktorá farba je na rade. Nastavuje hodnotu krokov, podľa záznamov
     * v zdrojovom súbore.
     * @throws IOException
     */
    private void turnRule() throws IOException{
        BufferedReader input = new BufferedReader(new FileReader(selectedFile));
        String last = null, line;

        this.step = 0;

        while ((line = input.readLine()) != null) {
            last = line;
            this.step++;
        }

        String trim = last.trim();
        int number = trim.split("\\s+").length;

        if (last != null){
            if (number % 2 == 0) {
                step = (step - 1) * 2 + 1;
            } else {
                step *= 2;
            }
        }
        else step = 0;

            movingWhite = true;
            movingBlack = false;
    }

    public void resetAndSet(){
        resetFigures();
        setFiguresOnBoard();
    }

    /**
     *
     * Metóda inicializuje zákldné rozloženie hracej dosky
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        whitePawn = new Image("file:lib/white-pawn.png");
        whiteRook = new Image("file:lib/white-rook.png");
        whiteKnight = new Image("file:lib/white-knight.png");
        whiteBishop = new Image("file:lib/white-bishop.png");
        whiteQueen = new Image("file:lib/white-queen.png");
        whiteKing = new Image("file:lib/white-king.png");

        blackPawn = new Image("file:lib/black-pawn.png");
        blackRook = new Image("file:lib/black-rook.png");
        blackKnight = new Image("file:lib/black-knight.png");
        blackBishop = new Image("file:lib/black-bishop.png");
        blackQueen = new Image("file:lib/black-queen.png");
        blackKing = new Image("file:lib/black-king.png");

        board = new Board(8);
        game = GameFactory.createChessGame(board);

        panes.add(row0);
        panes.add(row1);
        panes.add(row2);
        panes.add(row3);
        panes.add(row4);
        panes.add(row5);
        panes.add(row6);
        panes.add(row7);

        boardSize = board.getSize();

        colTurn.setCellValueFactory(new PropertyValueFactory<>("Turn"));
        colWhite.setCellValueFactory(new PropertyValueFactory<>("HistoryWhite"));
        colBlack.setCellValueFactory(new PropertyValueFactory<>("HistoryBlack"));

        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.getSelectionModel().setCellSelectionEnabled(true);

        setFiguresOnBoard();
        actionAutoBtn();

        frame.setOpacity(0.0d);
    }

    /**
     * Getter hry
     * @return Game
     */
    public Game getGame(){
        return this.game;
    }

    /**
     * Getter zdrojového súboru ťahov
     * @return File
     */
    public File getSelectedFile(){
        return this.selectedFile;
    }

    /**
     * Nastavuje koordinácie orámovania, ktoré ukazuje na aktuálny krok, v ktorom sa nachádza vykreslená hra.
     * @param column
     * @param row
     */
    public void setFrame (int column, int row){
        frame.setOpacity(1.0d);
        this.frame.setLayoutX(column);
        this.frame.setLayoutY(row);
    }

    /**
     * Metóda implementuje udalosť pri kliknutí na bunku v tabuľke vypísaných ťahov.
     * @param event
     */

    @FXML public void tableOnClick(MouseEvent event){
        TablePosition tablePosition = tableView.getSelectionModel().getSelectedCells().get(0);
        int row = tablePosition.getRow();
        int col = tablePosition.getColumn();

        if (!historyPlay){
            historyPlay = true;
            stepPlay=step;
        }

        int rowCounter = row*2 + col;

        int difference = stepPlay - rowCounter;

        if (col > 0) {
            if (difference > 0){
                for (int i = 0;i < difference; i++){
                    stepPlay--;
                    this.game.dryUndo();
                }
                resetAndSet();
                setFrame(75 * col, 25 * row + 25);
            }else if (difference < 0){
                difference = rowCounter - stepPlay;
                for (int i = 0; i < difference; i++){
                    stepPlay++;
                    this.game.redo();
                }
                resetAndSet();
                setFrame(75 * col, 25 * row + 25);
            }
        }

    }

    /**
     * Vymaže obsah súboru a uloží celý obsah premennej, ktorá obsahuje dáta o histórii ťahov.
     * @throws IOException
     */
    private void writeIntoFile() throws IOException{
        PrintWriter writer = new PrintWriter(this.selectedFile);
        writer.print("");
        writer.close();

        writer = new PrintWriter(this.selectedFile);
        writer.print(this.game.getHistory());
        writer.close();

        tableView.getItems().clear();
        printView();
    }

    /**
     *
     * Metóda implementuje tlačitko Undo. Po použití resetuje hraciu plochu a vykreslí
     * ju podľa objektu 'board', ktorý bol zmenení.
     * Prenastaví pravidlo, ktorá farba je na ťahu.
     *
     * Ak nebol spravený žiadny krok alebo hra sa vrátila do východzieho bodu, nič nerobí.
     * @throws IOException
     * */
    @FXML public void actionUndo() throws IOException{
        if (!historyPlay){
            if (step > 0){

                game.undo();

                boolean tmp;

                tmp = movingWhite;
                movingWhite = movingBlack;
                movingBlack = tmp;
                resetFigures();
                setFiguresOnBoard();

                writeIntoFile();
                step--;
                if (step == 0) frame.setOpacity(0.0d);
                else setFrame((((step-1)%2)+1) * 75, (((step-1)/2)+1) * 25);
            }
            else frame.setOpacity(0.0d);
        }
    }

    @FXML public void actionRedo() throws IOException{
        if (!historyPlay){
            this.game.redo();

            boolean tmp;
            tmp = movingWhite;
            movingWhite = movingBlack;
            movingBlack = tmp;

            step ++;
            resetFigures();
            setFiguresOnBoard();

            writeIntoFile();
        }
    }

    /**
     * Vykreslí hraciu plochu do počiatočnej podoby. Ak je zapnuté automatické prehrávanie, prekresluje hraciu
     * dosku od najnovšieho ťahu k najstaršiemu.
     */
    @FXML public void actionBegin() {
        if (!historyPlay){
            historyPlay = true;
            stepPlay=step;
        }

        if (stepPlay > 0){
            if (autoPlay){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = stepPlay; i > 0; i--) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    game.dryUndo();
                                    resetAndSet();
                                    setFrame((((stepPlay-1)%2)+1) * 75, (((stepPlay-1)/2)+1) * 25);

                                }
                            });
                            try {
                                Thread.sleep(500 - (int)slider.getValue());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            stepPlay--;
                            if (stepPlay == 0) frame.setOpacity(0.0d);
                        }
                    }
                }).start();
            } else {
                for (int i = stepPlay; i > 0; i--){
                    stepPlay--;
                    this.game.dryUndo();
                }
                resetAndSet();
                if (stepPlay == 0) frame.setOpacity(0.0d);
                else setFrame((((stepPlay-1)%2)+1) * 75, (((stepPlay-1)/2)+1) * 25);
            }
        }
    }

    /**
     * Spraví krok späť a vykreslí hraciu plochu. Ak je na začiatku prehrávania, nerobí nič.
     * Pri kroku späť, upraví pravidlo ťahu farieb.
     */
    @FXML public void actionStepBack() {
        if (!historyPlay){
            historyPlay = true;
            stepPlay = step;
        }

        if (step > 0){
            if (stepPlay == 0) frame.setOpacity(0.0d);
            else {
                this.game.dryUndo();
                stepPlay--;
                resetFigures();
                setFiguresOnBoard();

                boolean tmp;
                tmp = movingWhite;
                movingWhite = movingBlack;
                movingBlack = tmp;

                if (stepPlay == 0) frame.setOpacity(0.0d);
                else setFrame((((stepPlay-1)%2)+1) * 75, (((stepPlay-1)/2)+1) * 25);
            }
        }
    }

    /**
     * Spraví krok vpred a vykreslí hraciu plochu. Ak je na konci prehrávania, ukončí prehrávanie.
     * Pri kroku vpred, upraví pravidlo ťahu farieb.
     */
    @FXML public void actionStepForward() {
        if (historyPlay) {
            this.game.redo();
            stepPlay++;
            resetFigures();
            setFiguresOnBoard();

            boolean tmp;
            tmp = movingWhite;
            movingWhite = movingBlack;
            movingBlack = tmp;

            setFrame((((stepPlay-1)%2)+1) * 75, (((stepPlay-1)/2)+1) * 25);
            frame.setOpacity(1.0d);

            if (stepPlay == step){
                historyPlay = false;
            }
        }
    }

    /**
     * Vykreslí hraciu plochu do poslednej podoby, ktorá je zaznamenaná. Ak je zapnuté automatické
     * prehrávanie, prekresluje hraciu dosku od miesta prehrávanie do konca prehrávania.
     *
     * Po vykreslení ukončí prehrávanie.
     */
    @FXML public void actionStepEnd() {
        if (historyPlay){
            historyPlay = false;

            if (autoPlay){
                frame.setOpacity(1.0d);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = stepPlay; i <= step; i++) {
                            if (stepPlay >= 0) {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        game.redo();
                                        resetAndSet();
                                        setFrame((((stepPlay-1)%2)+1) * 75, (((stepPlay-1)/2)+1) * 25);
                                    }
                                });

                                try {
                                    Thread.sleep(500 - (int)slider.getValue());
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            stepPlay++;
                        }
                    }
                }).start();
            } else {
                while (stepPlay < step) {
                    stepPlay++;
                    this.game.redo();
                }
                resetAndSet();
                setFrame((((stepPlay-1)%2)+1) * 75, (((stepPlay-1)/2)+1) * 25);
            }
        }
    }

    /**
     * Nastavuje možnosť automatického prehrávania.
     */
    @FXML public void actionAutoBtn(){
        if (autoPlay){
            btnSwitch.setText("Automatic Off");
            btnSwitch.setStyle("-fx-background-color: red");
            this.autoPlay = false;
        } else {
           btnSwitch.setText("Automatic On");
           btnSwitch.setStyle("-fx-background-color: green");
           this.autoPlay = true;
        }
    }

    /**
     *
     * Metóda nastavuje súbor, ktorý bude slúžiť pre zápis/čítanie histórie krokov.
     *
     * @param file  Súbor, ktorý sa má pracovať.
     * */
    public void setFile(File file) throws IOException, WrongMoveException {
        this.selectedFile = file;
        if (selectedFile.length() != 0) {
            game.loadgame(this.selectedFile);
            resetFigures();
            setFiguresOnBoard();
            turnRule();
            this.stepPlay = 0;
            historyPlay = true;
            setFrame((((step-1)%2)+1) * 75, (((step-1)/2)+1) * 25);

        }
        printView();
        frame.setOpacity(0.0d);
    }

    /**
     *
     * Metóda pre vypísanie histórie krokov do ListView v GUI aplikácie.
     * */
    private void printView () throws FileNotFoundException{
        Scanner scan = new Scanner(selectedFile);
        ArrayList<String> listS = new ArrayList<>();

        while (scan.hasNextLine()) {
            listS.add(scan.nextLine());
        }
        scan.close();

        for (String line : listS){
            String words[] = line.split("\\s+");
            if (words.length == 2){
                HistoryTable newRow = new HistoryTable(words[0], words[1],"");
                tableView.getItems().add(newRow);
            }
            else if (words.length == 3) {
                HistoryTable newRow = new HistoryTable(words[0], words[1],words[2]);
                tableView.getItems().add(newRow);
            }
            else return;

            if (!historyPlay) {
                setFrame((((step-1)%2)+1) * 75, (((step-1)/2)+1) * 25);
            }

        }
    }

    /**
     *
     * Metóda implementuje nastavenia vybranej figúrky pre presun tým, že
     * jej nastaví nižšiu nepriehľadnosť a na jej staré miesto na hracej doske
     * vytvorí značku (orámuje políčko, z ktorého sa začinalo).
     *
     * @param event     Udalosť myši, ktorá spúšťa metódu
     * */
    @FXML
    public void figureStarts(MouseEvent event) {
        movingFigure = board.getField(pickIdenxX((int)(((Rectangle)(event.getSource())).getLayoutX())), pickIdenxY((int)(((Rectangle)(event.getSource())).getLayoutY()))).get();
        if (movingFigure.getColor() == Field.Color.W && movingWhite){
            ((Rectangle)(event.getSource())).setOpacity(0.4d);
            offset = new Point2D(event.getX(), event.getY());

            previousPlace.setOpacity(1.0d);
            previousPlace.setLayoutX( ((Rectangle)(event.getSource())).getLayoutX() );
            previousPlace.setLayoutY( ((Rectangle)(event.getSource())).getLayoutY() );
            movePass = true;
        }
        else if (movingFigure.getColor() == Field.Color.B && movingBlack){
            ((Rectangle)(event.getSource())).setOpacity(0.4d);
            offset = new Point2D(event.getX(), event.getY());

            previousPlace.setOpacity(1.0d);
            previousPlace.setLayoutX( ((Rectangle)(event.getSource())).getLayoutX() );
            previousPlace.setLayoutY( ((Rectangle)(event.getSource())).getLayoutY() );
            movePass = true;
        }
        else {
            movePass = false;
            event.consume();
        }
    }

    /**
     * Metóda implementujúca výber novej polohy pre figúrku pri držaní tlačidla myši.
     * Ak figúrka vyjde z hracej dosky, figúrka sa vráti na počiatočné pole odkiaľ
     * bola prenesená.
     *
     * @param event     Udalosť myši, ktorá spúšťa metódu
     * */
    @FXML
    public void figureMoves(MouseEvent event) {
        if ( movePass ){
            Point2D mousePoint = new Point2D(event.getX(), event.getY());

            if ( pickIdenxX((int)(((Rectangle)(event.getSource())).getLayoutX()))== 0 || pickIdenxY((int)(((Rectangle)(event.getSource())).getLayoutY())) == 0){
                final Timeline timeline = new Timeline();

                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.millis(100),
                                new KeyValue(((Rectangle)(event).getSource()).layoutXProperty(), previousPlace.getLayoutX()),
                                new KeyValue(((Rectangle)(event).getSource()).layoutYProperty(), previousPlace.getLayoutY()),
                                new KeyValue(((Rectangle)(event).getSource()).opacityProperty(), 1.0d)
                        )
                );
                previousPlace.setOpacity(0.0d);
                timeline.play();
                event.consume();
            }

            Point2D mousePoint_p = ((Rectangle)(event.getSource())).localToParent(mousePoint);
            ((Rectangle)(event.getSource())).relocate(mousePoint_p.getX()-offset.getX(), mousePoint_p.getY()-offset.getY());
        }
    }

    /**
     *  Po uvoľnení tlačidla myši sa figúrka pokúsi dopadnúť na miesto, nad ktorým sa nachádza.
     *  Pri úspechu presunie figúrku na nové miesto podĺa pravidiel šachu.
     *  Pri neúspechu presunie metóda figúrku naspäť kde bola pred presunom.
     *
     *  Pri dosiahnutí pešiaka posledného riadku, ktorý je mu naproti v smere pohybu pešiaka,
     *  povolí hráčovi si vymeniť figúrku v novom okne.
     *
     * @param event     Udalosť myši, ktorá spúšťa metódu
     * */
    @FXML
    public void figureLands (MouseEvent event) {

        offset = new Point2D(0.0d, 0.0d);

        Point2D mousePoint = new Point2D(event.getX(), event.getY());
        Point2D mousePointScene = ((Rectangle)(event.getSource())).localToScene(mousePoint);

        Rectangle r = pickRectangle( mousePointScene.getX(), mousePointScene.getY() );

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        if (movePass){
            if ( pickIdenxX( (int)(((Rectangle)(event.getSource())).getLayoutX()))== 0 || pickIdenxY( (int)(((Rectangle)(event.getSource())).getLayoutY())) == 0 ){
                //Pohyb figúrky mimo hraciu plochu
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.millis(100),
                                new KeyValue(previousPlace.layoutXProperty(),0.0d),
                                new KeyValue(previousPlace.layoutYProperty(),0.0d),
                                new KeyValue(((Rectangle)(event.getSource())).opacityProperty(), 1.0d)
                        )
                );
                previousPlace.setOpacity(0.0d);
            }
            else if ( ! game.move(movingFigure,board.getField(pickIdenxX(( (int) ( ((Rectangle)(event.getSource())).getLayoutX() ))), pickIdenxY(( (int) (((Rectangle)(event.getSource())).getLayoutY())))  ))  ){
                //Pohyb figúrky porušil pravidlá posunu

                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.millis(100),
                                new KeyValue(((Rectangle)(event).getSource()).layoutXProperty(), previousPlace.getLayoutX()),
                                new KeyValue(((Rectangle)(event).getSource()).layoutYProperty(), previousPlace.getLayoutY()),
                                new KeyValue(previousPlace.layoutXProperty(),0.0d),
                                new KeyValue(previousPlace.layoutYProperty(),0.0d),
                                new KeyValue(((Rectangle)(event).getSource()).opacityProperty(), 1.0d)
                        )
                );
                previousPlace.setOpacity(0.0d);
            }
            else if( r != null ) { //Figúrka na hracej doske
                Point2D rectScene =r.localToScene(r.getX(), r.getY());
                Point2D parent = boardPane.sceneToLocal(rectScene.getX(), rectScene.getY());

                if (historyPlay){
                    int dif = step - stepPlay;
                    if (dif > 0){
                        System.out.println("menim zapis hry dif: " + dif);
                        historyPlay = false;
                        for (int i = 0; i < dif; i++){System.out.println("loop: " + i);
                            this.game.redo();
                        }

                        for (int i = 0; i < dif; i++){
                            this.game.undo();
                        }

                        step = stepPlay;

                        try {
                            writeIntoFile();
                        } catch (IOException e){
                            e.printStackTrace();
                        }

                    } else System.out.println("lands rozdiel je mensi ako ");
                }

                kickOutFigure( pickIdenxX( (int)(((Rectangle)(event.getSource())).getLayoutX())), pickIdenxY( (int)(((Rectangle)(event.getSource())).getLayoutY())) );

                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.millis(100),
                                new KeyValue(previousPlace.layoutXProperty(),0.0d),
                                new KeyValue(previousPlace.layoutYProperty(),0.0d),
                                new KeyValue(((Rectangle)(event.getSource())).layoutXProperty(), parent.getX()),
                                new KeyValue(((Rectangle)(event.getSource())).layoutYProperty(), parent.getY()),
                                new KeyValue(((Rectangle)(event.getSource())).opacityProperty(), 1.0d)
                        )
                );
                previousPlace.setOpacity(0.0d);
                step++;

                //Pesiak na druhom konci hracej dosky si moze zmenit figurku podla vlastneho vyberu
                if ( ( movingFigure.getState().equals("W Pawn") &&  pickIdenxY( (int)(((Rectangle)(event.getSource())).getLayoutY())) >= 8 ) ||
                        ( movingFigure.getState().equals("B Pawn") &&  pickIdenxY( (int)(((Rectangle)(event.getSource())).getLayoutY())) <= 1) ){
                    try{

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseBox.fxml"));
                        Parent root = (Parent) loader.load();
                        ChooseBoxController cbc = loader.getController();
                        //Vytvorenie nového okna pre výber figúrok
                        Stage stage = new Stage();

                        cbc.setChangeFigure( this.board, this.movingFigure, pickIdenxX((int) ( ((Rectangle)(event.getSource())).getLayoutX() )), pickIdenxY(( (int) (((Rectangle)(event.getSource())).getLayoutY()))));
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setTitle("Výber figúrky");
                        stage.setScene(new Scene(root));
                        stage.show();

                        stage.setOnHidden(e -> {
                            resetFigures();
                            setFiguresOnBoard();
                        });
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

                //Nastavenie povolenia ďalšieho kroku
                if (movingFigure.getColor() == Field.Color.W){
                    movingWhite = false;
                    movingBlack = true;
                }
                else if (movingFigure.getColor() == Field.Color.B){
                    movingBlack = false;
                    movingWhite = true;
                }

                try {
                    writeIntoFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else { //Figúrka mimo hraciu dosku
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.millis(100),
                                new KeyValue(previousPlace.layoutXProperty(),0.0d),
                                new KeyValue(previousPlace.layoutYProperty(),0.0d),
                                new KeyValue(((Rectangle)(event.getSource())).opacityProperty(), 1.0d)
                        )
                );
                previousPlace.setOpacity(0.0d);
            }
            ((Rectangle)(event.getSource())).setOpacity(1.0d);
            timeline.play();

        }
    }

    /**
     * Metóda odstráni figúrku, ktorá sa nachádza na súradniciah x a y.
     *
     * Pri odstránení vráti hodnotu true, inak false.
     *
     * @param list List figúrok rovnakého typu a farby
     * @param x     X-ová súradnica cieľového poľa
     * @param y     Y-ová súradnica cieľového poľa
     * @return true/false
     * @used clearFigure
     */
    private boolean clearFigure(List<Rectangle> list, int x, int y){
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        for (Rectangle rec:list) {
            if (pickIdenxX((int)rec.getLayoutX()) == x && pickIdenxY((int)rec.getLayoutY()) == y){
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.millis(100),
                                new KeyValue(rec.layoutXProperty(), 0.0d),
                                new KeyValue(rec.layoutYProperty(), 0.0d),
                                new KeyValue(rec.opacityProperty(), 0.0d)
                        )
                );
                rec.setOpacity(0.0d);
                timeline.play();

                this.board.getField(pickIdenxX( (int)previousPlace.getLayoutX()) , pickIdenxY( (int)previousPlace.getLayoutY()) ).removeFigure();

                return true;
            }
        }
        return false;
    }

    /**
     * Prechádza všetky figúrky a hľadá figúrku so súradnicami x a y.
     * @param x
     * @param y
     */
    public void kickOutFigure(int x, int y){
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        if (movingFigure.getColor() == Field.Color.W){
            if (clearFigure(pawnsB, x, y)) return;

            if (clearFigure(rooksB, x, y)) return;

            if (clearFigure(knightsB, x, y)) return;

            if (clearFigure(bishopsB, x, y)) return;

            if (clearFigure(queensB, x, y)) return;

            if (pickIdenxX((int)kingB.getLayoutX()) == x && pickIdenxY((int)kingB.getLayoutY()) == y) {
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.millis(100),
                                new KeyValue(kingB.layoutXProperty(), 0.0d),
                                new KeyValue(kingB.layoutYProperty(), 0.0d),
                                new KeyValue(kingB.opacityProperty(), 0.0d)
                        )
                );
                kingB.setOpacity(0.0d);
                timeline.play();
                return;
            }
        }
        else if (movingFigure.getColor() == Field.Color.B){
            if (clearFigure(pawnsW, x, y)) return;

            if (clearFigure(rooksW, x, y)) return;

            if (clearFigure(knightsW, x, y)) return;

            if (clearFigure(bishopsW, x, y)) return;

            if (clearFigure(queensW, x, y)) return;

            if (pickIdenxX((int)kingW.getLayoutX()) == x && pickIdenxY((int)kingW.getLayoutY())== y) {
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.millis(100),
                                new KeyValue(kingW.layoutXProperty(), 0.0d),
                                new KeyValue(kingW.layoutYProperty(), 0.0d),
                                new KeyValue(kingW.opacityProperty(), 0.0d)
                        )
                );
                kingW.setOpacity(0.0d);
                timeline.play();
                return;
            }
        }
    }

    /**
     * Vyberanie indexu X(stĺpcu) pre manipuláciu s hash mapou hracej dosky
     *
     * @param value   Hodnota X súradnice
     * @return integer  Celočíselná hodnota, ktorá určuje stĺpec na hracej doske
     * */
    private int pickIdenxX(int value){
        if (20 <= value && 70 > value ){
            return 1;
        }
        else if (70 <= value && 120 > value){
            return 2;
        }
        else if (120 <= value && 170 > value){
            return 3;
        }
        else if (170 <= value && 220 > value){
            return 4;
        }
        else if (220 <= value && 270 > value){
            return 5;
        }
        else if (270 <= value && 320 > value){
            return 6;
        }
        else if (320 <= value && 370 > value){
            return 7;
        }
        else if (370 <= value && 420 > value){
            return 8;
        }
        else return 0;
    }

    /**
     * Vyberanie indexu Y(riadku) pre manipuláciu s hash mapou hracej dosky
     *
     * @param value   Hodnota Y súradnice
     * @return integer  Celočíselná hodnota, ktorá určuje riadok na hracej doske
     * */
    private int pickIdenxY(int value){
        if (20 <= value && 70 > value ){
            return 8;
        }
        else if (70 <= value && 120 > value){
            return 7;
        }
        else if (120 <= value && 170 > value){
            return 6;
        }
        else if (170 <= value && 220 > value){
            return 5;
        }
        else if (220 <= value && 270 > value){
            return 4;
        }
        else if (270 <= value && 320 > value){
            return 3;
        }
        else if (320 <= value && 370 > value){
            return 2;
        }
        else if (370 <= value && 420 > value){
            return 1;
        }
        else return 0;
    }

    /**
     * Vyberá políčka z hracej dosky, na ktoré sa postaví figúrka.
     * Ak sa súradnice figúrky nenáchadzajú v rozsahu súradníc hracej dosky,
     * funkcia vráti hodnotu NULL.
     *
     * @param sceneX    X-ová súradnica figúrky, ktorá bola presunutá
     * @param sceneY    Y-ová súradnica figúrky, ktorá bola presunutá
     *
     * @return pickedrectangle  Políčko hracej dosky, nad ktorým sa nachádza presúvaná figúrka
     * */
    private Rectangle pickRectangle(double sceneX, double sceneY) {
        Rectangle pickedRectangle = null;
        for( Pane row : panes ) {

            Point2D mousePoint = new Point2D(sceneX, sceneY);
            Point2D mpLocal = row.sceneToLocal(mousePoint);

            if( row.contains(mpLocal) ) {
                for( Node cell : row.getChildrenUnmodifiable() ) {
                    Point2D mpLocalCell = cell.sceneToLocal(mousePoint);
                    if( cell.contains(mpLocalCell) ) {
                        pickedRectangle = (Rectangle)cell;
                        break;
                    }
                }
                break;
            }
        }
        return pickedRectangle;
    }
}