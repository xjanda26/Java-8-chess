package ija.ija2018.homework2.view;

import ija.ija2018.homework2.common.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Adam Janda <xjanda26@stud.fit.vutbr.cz>
 * */



public class ChooseBoxController implements Initializable {
    @FXML  Button btnWRook;
    @FXML  Button btnWKnight;
    @FXML  Button btnWBishop;
    @FXML  Button btnWQueen;

    @FXML ImageView imageWRook;
    @FXML ImageView imageWKnight;
    @FXML ImageView imageWBishop;
    @FXML ImageView imageWQueen;

    @FXML  Button btnBRook;
    @FXML  Button btnBKnight;
    @FXML  Button btnBBishop;
    @FXML  Button btnBQueen;

    @FXML ImageView imageBRook;
    @FXML ImageView imageBKnight;
    @FXML ImageView imageBBishop;
    @FXML ImageView imageBQueen;

    @FXML  Button btnConfirm;

    private Game game;
    private String changedFigure;
    private int col;
    private int row;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageWRook.setImage(new Image("file:lib/white-rook.png"));
        imageWKnight.setImage(new Image("file:lib/white-knight.png"));
        imageWBishop.setImage(new Image("file:lib/white-bishop.png"));
        imageWQueen.setImage(new Image("file:lib/white-queen.png"));


        imageBRook.setImage(new Image("file:lib/black-rook.png"));
        imageBKnight.setImage(new Image("file:lib/black-knight.png"));
        imageBBishop.setImage(new Image("file:lib/black-bishop.png"));
        imageBQueen.setImage(new Image("file:lib/black-queen.png"));
    }

    @FXML public void actionWRook (ActionEvent event){

    }

    @FXML public void actionWKnight (ActionEvent event){

    }

    @FXML public void actionWBishop (ActionEvent event){

    }

    @FXML public void actionWQueen (ActionEvent event){

    }

    @FXML public void actionBRook (ActionEvent event){

    }

    @FXML public void actionBKnight (ActionEvent event){

    }

    @FXML public void actionBBishop (ActionEvent event){

    }

    @FXML public void actionBQueen (ActionEvent event){

    }

    public void setChangeFigure (Game game, String state, int col, int row){
        this.game = game;
        this.changedFigure = state;
        this.col = col;
        this.row = row;
    }
}
