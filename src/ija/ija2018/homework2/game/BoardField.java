/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2018.homework2.game;

import java.util.HashMap;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;
/**
 *
 * @author xramos00
 */
public class BoardField implements Field{
    
    private Figure figure;
    private Color color;
    public int col,row;
    private HashMap<Direction,Field> neighbors;
    public BoardField(int col, int row, Color c) {
        this.color = c;
        this.col = col;
        this.row = row;
        this.figure=null;
        this.neighbors = new HashMap<Direction,Field>();
    }
    
    @Override
    public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if(!(o instanceof BoardField)) {
			return false;
		}
		BoardField b = (BoardField)o;
    	if ((this.row == b.getRow()) && (this.col == b.getCol())) {
    		return true;
    	}
    	return false;
    }
    
    @Override
    public Figure get(){
        return figure;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFigure(Figure f){
        this.figure = f;
    }

    @Override
    public boolean isEmpty() {
        return figure==null;
    }
    
    public Color getColor(){
        return this.color;
    }


    public void removeFigure(){this.figure = null;}

	@Override
	public int getCol() {
		return this.col;
	}



	@Override
	public int getRow() {
		return this.row;
	}



	@Override
	public void addNextField(Direction dir, Field f) {
		this.neighbors.put(dir, f);
	}
	
	public Field getField(Direction dir) {
		return this.neighbors.get(dir);
	}
	
	public HashMap<Direction,Field> getFieldsMap(){
		return this.neighbors;
	}
    
}
