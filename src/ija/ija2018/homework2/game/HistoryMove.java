package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;

/**
*
* @author xramos00
*/

public class HistoryMove {

	public Figure deleted=null;
	
	public Figure moved= null;
	
	public Figure changed = null;
	
	public Field deletedFromField, movedFromField, movedToField;
	
	public Board b;
	
	public boolean check = false;
	public boolean mate = false;
	
	public void setchange(Figure changed) {
		this.changed = changed;
	}
	
	public void check() {
		this.check=true;
	}
	
	public void mate() {
		this.mate=true;
	}
	
	public String toString() {
		String move = "";
		if (moved instanceof Rook) {
			move = "V";
		}
		if (moved instanceof Queen) {
			move = "D";
		}
		if(moved instanceof Knight) {
			move = "J";
		}
		if(moved instanceof King) {
			move = "K";
		}
		if(moved instanceof Bishop) {
			move = "S";
		}
		
		String tochange = "";
		if(changed != null) {
			if (changed instanceof Rook) {
				tochange = "V";
			}
			if (changed instanceof Queen) {
				tochange = "D";
			}
			if(changed instanceof Knight) {
				tochange = "J";
			}
			if(changed instanceof King) {
				tochange = "K";
			}
			if(changed instanceof Bishop) {
				tochange = "S";
			}
		}
		
		
		char fromCol = (char)(movedFromField.getCol()+96);
		char toCol = (char)(movedToField.getCol()+96);
		StringBuilder output = new StringBuilder(); 
		output.append(move).append(fromCol).append(Integer.toString(movedFromField.getRow())).append((deleted==null?"":"x")).append(toCol).append(Integer.toString(movedToField.getRow())).append(tochange).append((check?"+":"")).append((mate?"#":""));
	
		return output.toString();
	}
		
	
	public HistoryMove(Figure deleted, Field deletedFromField,Figure moved, Field movedFromField, Field movedToField, Board b) {
		
		this.deleted = deleted;
		this.moved = moved;
		this.deletedFromField = deletedFromField;
		this.movedFromField = movedFromField;
		this.movedToField = movedToField;
		this.b = b;
		
	}
	
}
