package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field.Color;
import ija.ija2018.homework2.common.Figure;

public class Bishop extends AbstractFigure implements Figure {

	public Bishop(Color c) {
		this.c=c;
		col = row = 0;
	}
	
	@Override
	public String getState() {
		return this.c.toString()+" Bishop";
	}

}
