/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;
/**
 *
 * @author xramos00
 */
public class Board {
    private Field[][] internalBoard;
	private int size;
	
	public Board(int i) {
		internalBoard = new BoardField[i][i];
		int rowCounter = i;
		int colCounter = 1;
		size = i;
		
		for (int j = 0; j < i; j++) {
			for (int k = 0; k < i; k++) {
                                Field.Color c = Field.Color.W;
                                if (i%2 == 0 && j%2 == 0){ 
                                    c = Field.Color.W;
                                }
                                if (i%2 == 0 && j%2 == 1){ 
                                    c = Field.Color.B;
                                }
                                if (i%2 == 1 && j%2 == 0){   
                                    c = Field.Color.B;
                                }
                                if (i%2 == 1 && j%2 == 1){   
                                    c = Field.Color.W;
                                }
				internalBoard[j][k] = new BoardField(colCounter,rowCounter,c);
				colCounter++;
			}
			colCounter=1;
			rowCounter--;
		}
		
		for (int j = 0; j < i; j++) {
			for (int k = 0; k < i; k++) {	
				if(j-1 >= 0) {
					internalBoard[j][k].addNextField(Field.Direction.U, internalBoard[j-1][k]);
				}
				if(j+1 < size) {
					internalBoard[j][k].addNextField(Field.Direction.D, internalBoard[j+1][k]);
				}
				if(k-1 >= 0) {
					internalBoard[j][k].addNextField(Field.Direction.L, internalBoard[j][k-1]);
				}
				if(k+1 < size) {
					internalBoard[j][k].addNextField(Field.Direction.R, internalBoard[j][k+1]);
				}
				if(j+1 < size && k+1 < size) {
					internalBoard[j][k].addNextField(Field.Direction.RD, internalBoard[j+1][k+1]);
				}
				if(j-1 >= 0 && k-1 >= 0) {
					internalBoard[j][k].addNextField(Field.Direction.LU, internalBoard[j-1][k-1]);
				}
				if(j+1 < size && k-1 >= 0) {
					internalBoard[j][k].addNextField(Field.Direction.LD, internalBoard[j+1][k-1]);
				}
				if(j-1 >= 0 && k+1 < size) {
					internalBoard[j][k].addNextField(Field.Direction.RU, internalBoard[j-1][k+1]);
				}
			}
		}
		
		size = i;
	}
	
	public Field getField(int col, int row) {
		try {
			row = size - row;
			col -= 1;
			Field desiredField = internalBoard[row][col];
			return desiredField;
			}
		catch (IndexOutOfBoundsException e) {
			return null;
		}	
	}
	
	public int getSize() {
		return size;
	}

	public Field[][] getFields(){
		return internalBoard;
	}
	
}
