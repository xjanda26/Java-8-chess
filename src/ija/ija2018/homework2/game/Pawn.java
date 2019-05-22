/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Figure;
import ija.ija2018.homework2.common.Field.Color;

/**
 *
 * @author xramos00
 */
public class Pawn extends AbstractFigure implements Figure{
    
	boolean firstMove=false;
	
    Pawn(Color color){
        this.c = color;
        col = row = 0;
    }
    

    @Override
    public String getState() {
    	return this.c.toString()+" Pawn";
    }

    public void moved() {
    	this.firstMove=true;
    }
    
    public boolean wasMoved() {
    	return firstMove;
    }
    
    public void movedBack() {
    	this.firstMove=false;
    }
}
