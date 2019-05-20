/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2018.homework2;

import ija.ija2018.homework2.common.Game;
import ija.ija2018.homework2.game.Board;
import ija.ija2018.homework2.game.ChessGame;

/**
 *
 * @author xramos00
 */

public abstract class GameFactory {

    public GameFactory() {
    }

    
    public static Game createChessGame(Board board){
        return new ChessGame(board);
    }
    
}
