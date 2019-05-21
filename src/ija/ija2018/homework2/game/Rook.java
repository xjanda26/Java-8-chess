/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field.Color;
import ija.ija2018.homework2.common.Figure;

/**
 *
 * @author xramos00
 */
public class Rook extends AbstractFigure implements Figure{
    
    public Rook(Color color){
        this.c = color;
        col = row = 0;
    }
    
    @Override
    public String getState() {
    	return this.c.toString()+" Rook";
    }

    
}
