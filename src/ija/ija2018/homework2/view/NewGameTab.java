package ija.ija2018.homework2.view;

import ija.ija2018.homework2.GameFactory;
import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;
import ija.ija2018.homework2.game.Board;
import ija.ija2018.homework2.common.Game;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.util.ArrayList;
import java.util.List;

import java.lang.*;
import java.util.Scanner;

/**
 *
 * @author Adam Janda <xjanda26@stud.fit.vutbr.cz>
 * */

public class NewGameTab implements Initializable {

    public static int tabIndex = 0;

    @FXML Rectangle pawnW0;
    @FXML Rectangle pawnW1;
    @FXML Rectangle pawnW2;
    @FXML Rectangle pawnW3;
    @FXML Rectangle pawnW4;
    @FXML Rectangle pawnW5;
    @FXML Rectangle pawnW6;
    @FXML Rectangle pawnW7;

    @FXML Rectangle pawnB0;
    @FXML Rectangle pawnB1;
    @FXML Rectangle pawnB2;
    @FXML Rectangle pawnB3;
    @FXML Rectangle pawnB4;
    @FXML Rectangle pawnB5;
    @FXML Rectangle pawnB6;
    @FXML Rectangle pawnB7;

    @FXML Rectangle rookW0;
    @FXML Rectangle rookW1;
    @FXML Rectangle bishopW0;
    @FXML Rectangle bishopW1;
    @FXML Rectangle knightW0;
    @FXML Rectangle knightW1;
    @FXML Rectangle queenW;
    @FXML Rectangle kingW;

    @FXML Rectangle rookB0;
    @FXML Rectangle rookB1;
    @FXML Rectangle bishopB0;
    @FXML Rectangle bishopB1;
    @FXML Rectangle knightB0;
    @FXML Rectangle knightB1;
    @FXML Rectangle queenB;
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

    private final List<Pane> panes = new ArrayList<>();
    private final List<Rectangle> pawnsB = new ArrayList<>();
    private final List<Rectangle> rooksB = new ArrayList<>();
    private final List<Rectangle> knightsB = new ArrayList<>();
    private final List<Rectangle> bishopsB = new ArrayList<>();

    private final List<Rectangle> pawnsW = new ArrayList<>();
    private final List<Rectangle> rooksW = new ArrayList<>();
    private final List<Rectangle> knightsW = new ArrayList<>();
    private final List<Rectangle> bishopsW = new ArrayList<>();
    private Point2D offset = new Point2D(0.0d,0.0d);

    private boolean movingWhite = true;
    private boolean movingBlack = false;
    private boolean movePass = true;
    private Game game;
    private Board board;
    private Figure movingFigure;
    private File selectedFile;
    private int boardSize;
    private int step = 0;

    private int pawnWCounter = 0;
    private int rookWCounter = 0;
    private int knightWCounter = 0;
    private int bishopWCounter = 0;

    private int pawnBCounter = 0;
    private int rookBCounter = 0;
    private int knightBCounter = 0;
    private int bishopBCounter = 0;

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

    private void setFiguresOnBoard(){

        pawnWCounter = 0;
        rookWCounter = 0;
        knightWCounter = 0;
        bishopWCounter = 0;

        pawnBCounter = 0;
        rookBCounter = 0;
        knightBCounter = 0;
        bishopBCounter = 0;

        for (int row = 1; row <= boardSize; row++){
            for (int column = 1; column <= boardSize; column++ ){
                if (! board.getField(column, row). isEmpty() ) {
                    switch (board.getField(column, row).get().getState()) {
                        case "W Pawn":
                            pawnsW.get(pawnWCounter).setLayoutX((column - 1) * 50 + 40);
                            pawnsW.get(pawnWCounter).setLayoutY(440 - 50* row);
                            pawnsW.get(pawnWCounter).setFill(new ImagePattern(whitePawn));
                            pawnsW.get(pawnWCounter).setOpacity(1.0d);
                            pawnWCounter++;
                            break;
                        case "W Rook":
                            rooksW.get(rookWCounter).setLayoutX((column - 1) * 50 + 40);
                            rooksW.get(rookWCounter).setLayoutY(440 - 50* row);
                            rooksW.get(rookWCounter).setFill(new ImagePattern(whiteRook));
                            rooksW.get(rookWCounter).setOpacity(1.0d);
                            rookWCounter++;
                            break;
                        case "W Knight":
                            knightsW.get(knightWCounter).setLayoutX((column - 1) * 50 + 40);
                            knightsW.get(knightWCounter).setLayoutY(440 - 50* row);
                            knightsW.get(knightWCounter).setFill(new ImagePattern(whiteKnight));
                            knightsW.get(knightWCounter).setOpacity(1.0d);
                            knightWCounter++;
                            break;
                        case "W Bishop":
                            bishopsW.get(bishopWCounter).setLayoutX((column - 1) * 50 + 40);
                            bishopsW.get(bishopWCounter).setLayoutY(440 - 50* row);
                            bishopsW.get(bishopWCounter).setFill(new ImagePattern(whiteBishop));
                            bishopsW.get(bishopWCounter).setOpacity(1.0d);
                            bishopWCounter++;
                            break;
                        case "W Queen":
                            queenW.setLayoutX((column - 1) * 50 + 40);
                            queenW.setLayoutY(440 - 50* row);
                            queenW.setFill(new ImagePattern(whiteQueen));
                            queenW.setOpacity(1.0d);
                            break;
                        case "W King":
                            kingW.setLayoutX((column - 1) * 50 + 40);
                            kingW.setLayoutY(440 - 50* row);
                            kingW.setFill(new ImagePattern(whiteKing));
                            kingW.setOpacity(1.0d);
                            break;
                        // Black figures
                        case "B Pawn":
                            pawnsB.get(pawnBCounter).setLayoutX((column - 1) * 50 + 40);
                            pawnsB.get(pawnBCounter).setLayoutY(440 - 50* row);
                            pawnsB.get(pawnBCounter).setFill(new ImagePattern(blackPawn));
                            pawnsB.get(pawnBCounter).setOpacity(1.0d);
                            pawnBCounter++;
                            break;
                        case "B Rook":
                            rooksB.get(rookBCounter).setLayoutX((column - 1) * 50 + 40);
                            rooksB.get(rookBCounter).setLayoutY(440 - 50* row);
                            rooksB.get(rookBCounter).setFill(new ImagePattern(blackRook));
                            rooksB.get(rookBCounter).setOpacity(1.0d);
                            rookBCounter++;
                            break;
                        case "B Knight":
                            knightsB.get(knightBCounter).setLayoutX((column - 1) * 50 + 40);
                            knightsB.get(knightBCounter).setLayoutY(440 - 50* row);
                            knightsB.get(knightBCounter).setFill(new ImagePattern(blackKnight));
                            knightsB.get(knightBCounter).setOpacity(1.0d);
                            knightBCounter++;
                            break;
                        case "B Bishop":
                            bishopsB.get(bishopBCounter).setLayoutX((column - 1) * 50 + 40);
                            bishopsB.get(bishopBCounter).setLayoutY(440 - 50* row);
                            bishopsB.get(bishopBCounter).setFill(new ImagePattern(blackBishop));
                            bishopsB.get(bishopBCounter).setOpacity(1.0d);
                            bishopBCounter++;
                            break;
                        case "B Queen":
                            queenB.setLayoutX((column - 1) * 50 + 40);
                            queenB.setLayoutY(440 - 50* row);
                            queenB.setFill(new ImagePattern(blackQueen));
                            queenB.setOpacity(1.0d);
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

    private void resetListFigures(List<Rectangle> list){
        for (Rectangle rec : list){
            rec.setOpacity(0.0d);
            rec.setLayoutX(0.0d);
            rec.setLayoutY(0.0d);
        }
    }

    private void resetFigures(){
        resetListFigures(pawnsW);
        resetListFigures(rooksW);
        resetListFigures(knightsW);
        resetListFigures(bishopsW);

        queenW.setLayoutX(0);
        queenW.setLayoutY(0);
        queenW.setOpacity(0.0d);

        kingW.setLayoutX(0);
        kingW.setLayoutY(0);
        kingW.setOpacity(0.0d);

        resetListFigures(pawnsB);
        resetListFigures(rooksB);
        resetListFigures(knightsB);
        resetListFigures(bishopsB);

        queenB.setLayoutX(0);
        queenB.setLayoutY(0);
        queenB.setOpacity(0.0d);

        kingB.setLayoutX(0);
        kingB.setLayoutY(0);
        kingB.setOpacity(0.0d);
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

        pawnsB.add(pawnB0);
        pawnsB.add(pawnB2);
        pawnsB.add(pawnB1);
        pawnsB.add(pawnB3);
        pawnsB.add(pawnB4);
        pawnsB.add(pawnB5);
        pawnsB.add(pawnB6);
        pawnsB.add(pawnB7);

        rooksB.add(rookB0);
        rooksB.add(rookB1);

        knightsB.add(knightB0);
        knightsB.add(knightB1);

        bishopsB.add(bishopB0);
        bishopsB.add(bishopB1);

        pawnsW.add(pawnW0);
        pawnsW.add(pawnW2);
        pawnsW.add(pawnW1);
        pawnsW.add(pawnW3);
        pawnsW.add(pawnW4);
        pawnsW.add(pawnW5);
        pawnsW.add(pawnW6);
        pawnsW.add(pawnW7);

        rooksW.add(rookW0);
        rooksW.add(rookW1);

        knightsW.add(knightW0);
        knightsW.add(knightW1);

        bishopsW.add(bishopW0);
        bishopsW.add(bishopW1);

        panes.add(row0);
        panes.add(row1);
        panes.add(row2);
        panes.add(row3);
        panes.add(row4);
        panes.add(row5);
        panes.add(row6);
        panes.add(row7);

        boardSize = board.getSize();

        setFiguresOnBoard();
    }

    @FXML public void actionUndo(){
        if (step > 0){
            step--;
            game.undo();

            boolean tmp;

            tmp = movingWhite;
            movingWhite = movingBlack;
            movingBlack = tmp;
            resetFigures();
            setFiguresOnBoard();
        }
    }

    @FXML public void actionRedo(){
        game.redo();

        boolean tmp;

        tmp = movingWhite;
        movingWhite = movingBlack;
        movingBlack = tmp;
        resetFigures();
        setFiguresOnBoard();
    }


    public void setFile(File file) throws IOException {
        this.selectedFile = file;

        printView();
    }

    public void printView () throws FileNotFoundException{
        Scanner scan = new Scanner(selectedFile);
        ArrayList<String> listS = new ArrayList<>();

        while (scan.hasNextLine()) {
            listS.add(scan.nextLine());
        }
        scan.close();

        for (String line: listS ) {
            listView.getItems().add(line);
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
            //final Timeline timeline = new Timeline();
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
     *  Po pustení tlačidla myši sa figúrka pokúsi dopadnúť na miesto, nad ktorým sa nachádza.
     *  Pri úspechu presunie figúrku na nové miesto podĺa pravidiel šachu.
     *  Pri neúspechu presunie metóda figúrku naspäť kde bola pred presunom.
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
                //System.out.println("Y: " + pickIdenxY( (int)(((Rectangle)(event.getSource())).getLayoutY())) + movingFigure.getState());
                if ( ( !(movingFigure.getState() == "W Pawn") &&  pickIdenxY( (int)(((Rectangle)(event.getSource())).getLayoutY())) >= 8 ) ||
                        ( !(movingFigure.getState() == "B Pawn") &&  pickIdenxY( (int)(((Rectangle)(event.getSource())).getLayoutY())) <= 1) ){
                    //System.out.println("Choose new figure"); //TODO
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseBox.fxml"));
                        Parent root = (Parent) loader.load();
                        Stage stage = new Stage();

                        stage.setTitle("Výber figúrky");
                        stage.setScene(new Scene(root));
                        stage.show();

                    } catch (Exception e){
                        e.printStackTrace();
                    }

                }

                if (movingFigure.getColor() == Field.Color.W){
                    movingWhite = false;
                    movingBlack = true;
                }
                else if (movingFigure.getColor() == Field.Color.B){
                    movingBlack = false;
                    movingWhite = true;
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
                return true;
            }
        }
        return false;
    }

    public void kickOutFigure(int x, int y){
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        if (movingFigure.getColor() == Field.Color.W){
            if (clearFigure(pawnsB, x, y)) return;

            if (clearFigure(rooksB, x, y)) return;

            if (clearFigure(knightsB, x, y)) return;

            if (clearFigure(bishopsB, x, y)) return;

            if (pickIdenxX((int)queenB.getLayoutX()) == x && pickIdenxY((int)queenB.getLayoutY()) == y) {
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.millis(100),
                                new KeyValue(queenB.layoutXProperty(), 0.0d),
                                new KeyValue(queenB.layoutYProperty(), 0.0d),
                                new KeyValue(queenB.opacityProperty(), 0.0d)
                        )
                );
                queenB.setOpacity(0.0d);
                timeline.play();
                return;
            }

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

            if (pickIdenxX((int)queenW.getLayoutX()) == x && pickIdenxY((int)queenW.getLayoutY()) == y) {
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.millis(100),
                                new KeyValue(queenW.layoutXProperty(), 0.0d),
                                new KeyValue(queenW.layoutYProperty(), 0.0d),
                                new KeyValue(queenW.opacityProperty(), 0.0d)
                        )
                );
                queenW.setOpacity(0.0d);
                timeline.play();
                return;
            }

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