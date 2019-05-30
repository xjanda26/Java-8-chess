/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2018.homework2.common;

import ija.ija2018.homework2.common.Figure;
import ija.ija2018.homework2.game.WrongMoveException;

import java.io.File;
import java.io.IOException;

import ija.ija2018.homework2.common.Field;

/**
 *
 * @author xramos00
 */
public interface Game {
    
    public boolean move(Figure figure, Field field);
    
    void undo();

	public boolean loadgame(String filename) throws IOException, WrongMoveException;
	//public boolean loadgame(File inputFile) throws IOException, WrongMoveException;

	void redo();
	
	public String getHistory();
}
