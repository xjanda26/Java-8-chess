package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field.Color;

public abstract class AbstractFigure {

	
	protected int col;
	protected int row;
    protected Color c;
	
    
	public void setCoords(int col, int row) {
		this.col=col;
		this.row=row;
		
	}

	public Color getColor() {
		return this.c;
	}

	public int getCol() {
		return this.col;
	}

	public int getRow() {
		return this.row;
	}
	
}
