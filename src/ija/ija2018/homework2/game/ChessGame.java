/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2018.homework2.game;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.core.IsInstanceOf;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;
import ija.ija2018.homework2.common.Game;
import ija.ija2018.homework2.common.Field.Color;
import ija.ija2018.homework2.common.Field.DiagonalDirections;
import ija.ija2018.homework2.common.Field.Direction;
import ija.ija2018.homework2.game.Board;

/**
 *
 * @author xramos00
 */
public class ChessGame implements Game{

	Board board;
	
	Set<Direction> blackAllowedDirections = null;
    Set<Direction> whiteAllowedDirections = null;
    Set<Direction> rookAllowedDirections = null;
	
    Set<Rook> whiteRooks = null;
    Set<Rook> blackRooks = null;
    
    Set<Bishop> whiteBishops = null;
    Set<Bishop> blackBishops = null;
    
    Set<Knight> whiteKnights = null;
    Set<Knight> blackKnights = null;
    
    Set<Pawn> whitePawns = null;
    Set<Pawn> blackPawns = null;
    
    Figure whiteKing;
    Figure blackKing;
    
    Set<Queen> whiteQueens;
    Set<Queen> blackQueens;
    
    public int actualMove = -1; 
    
    private Figure toChange = new Rook(Color.W);
    //int fromX,fromY, toX, toY;
    //int deletedFromX,deletedFromY;
    //Figure deleted=null;
    
    public List<HistoryMove> moves;
    
    public ChessGame(Board b) {
    	whiteAllowedDirections = new HashSet<Direction>();
    	whiteAllowedDirections.add(Direction.U);
    	whiteAllowedDirections.add(Direction.LU);
    	whiteAllowedDirections.add(Direction.RU);
    	blackAllowedDirections = new HashSet<Direction>();
    	blackAllowedDirections.add(Direction.D);
    	blackAllowedDirections.add(Direction.LD);
    	blackAllowedDirections.add(Direction.RD);
    	rookAllowedDirections = new HashSet<Direction>();
    	rookAllowedDirections.add(Direction.D);
    	rookAllowedDirections.add(Direction.U);
    	rookAllowedDirections.add(Direction.L);
    	rookAllowedDirections.add(Direction.R);
    	
		this.moves = new ArrayList<HistoryMove>();
        this.board = b;
        whiteRooks = new HashSet<Rook>();
        blackRooks = new HashSet<Rook>();
        
        whiteBishops = new HashSet<Bishop>();
        blackBishops = new HashSet<Bishop>();
        
        whiteKnights = new HashSet<Knight>();
        blackKnights = new HashSet<Knight>();
        
        whitePawns = new HashSet<Pawn>();
        blackPawns = new HashSet<Pawn>();
        
        whiteQueens = new HashSet<Queen>();
        blackQueens = new HashSet<Queen>();
        /*for (int i = 0; i < b.getSize();i+=2){
            Field tmp = b.getField(i+1, 1);
            tmp.setFigure(new Pawn(Field.Color.W));
            tmp.get().setCoords(i+1, 1);
        }*/
        Figure tmp  = new Rook(Field.Color.W);
        whiteRooks.add((Rook)tmp);
        board.getField(1, 1).setFigure(tmp); //veza vlavo dole
        board.getField(1, 1).get().setCoords(1, 1);
        tmp = new Rook(Field.Color.W);
        whiteRooks.add((Rook)tmp);
        board.getField(8, 1).setFigure(tmp); //veza vpravo dole
        board.getField(8, 1).get().setCoords(8, 1);
        tmp = new Bishop(Field.Color.W);
        whiteBishops.add((Bishop)tmp);
        board.getField(3, 1).setFigure(tmp); //strelec vlavo dole
        board.getField(3, 1).get().setCoords(3, 1);
        tmp = new Bishop(Field.Color.W);
        whiteBishops.add((Bishop)tmp);
        board.getField(6, 1).setFigure(tmp); //strelec vpravo dole
        board.getField(6, 1).get().setCoords(6, 1);
        tmp = new Queen(Field.Color.W);
        whiteQueens.add((Queen)tmp);
        board.getField(4, 1).setFigure(tmp); //kralovna dole biela
        board.getField(4, 1).get().setCoords(4, 1);
        tmp = new King(Field.Color.W);
        whiteKing = tmp;
        board.getField(5, 1).setFigure(tmp); //kral dole biely
        board.getField(5, 1).get().setCoords(5, 1);
        tmp = new Knight(Field.Color.W);
        whiteKnights.add((Knight)tmp);
        board.getField(2, 1).setFigure(tmp); //jazdec dole vlavo
        board.getField(2, 1).get().setCoords(2, 1);
        tmp = new Knight(Field.Color.W);
        whiteKnights.add((Knight)tmp);
        board.getField(7, 1).setFigure(tmp); //jazdec dole vpravo
        board.getField(7, 1).get().setCoords(7, 1);
        
   
        for (int i = 1; i <= b.getSize();i+=1){			//pesiaci dole 
            Field tmp1 = b.getField(i, 2);
            Figure tmp_fig = new Pawn(Field.Color.W);
            whitePawns.add((Pawn) tmp_fig);
            tmp1.setFigure(tmp_fig);
            tmp1.get().setCoords(i, 2);
        }
        tmp  = new Rook(Field.Color.B);
        blackRooks.add((Rook)tmp);
        board.getField(1, 8).setFigure(tmp);
        board.getField(1, 8).get().setCoords(1, 8);
        tmp  = new Rook(Field.Color.B);
        blackRooks.add((Rook)tmp);
        board.getField(8, 8).setFigure(tmp);
        board.getField(8,8).get().setCoords(8, 8);
        tmp = new Bishop(Field.Color.B);
        blackBishops.add((Bishop)tmp);
        board.getField(3, 8).setFigure(tmp); //strelec vlavo hore
        board.getField(3, 8).get().setCoords(3, 8);
        tmp = new Bishop(Field.Color.B);
        blackBishops.add((Bishop)tmp);
        board.getField(6, 8).setFigure(tmp); //strelec vpravo hore
        board.getField(6, 8).get().setCoords(6, 8);
        tmp = new Queen(Field.Color.B);
        blackQueens.add((Queen)tmp);
        board.getField(4, 8).setFigure(tmp); //kralovna hore cierna
        board.getField(4, 8).get().setCoords(4, 8);
        tmp = new King(Field.Color.B);
        blackKing = tmp;
        board.getField(5, 8).setFigure(tmp); //kral hore cierny
        board.getField(5, 8).get().setCoords(5, 8);
        tmp = new Knight(Field.Color.B);
        blackKnights.add((Knight)tmp);
        board.getField(2, 8).setFigure(tmp); //jazdec hore vlavo
        board.getField(2, 8).get().setCoords(2, 8);
        tmp = new Knight(Field.Color.B);
        blackKnights.add((Knight)tmp);
        board.getField(7, 8).setFigure(tmp); //jazdec hore vpravo
        board.getField(7, 8).get().setCoords(7, 8);
        
        for (int i = 1; i <= b.getSize();i+=1){					//pesiaci hore
            Field tmp1 = b.getField(i,b.getSize()-1);
            Figure tmp_fig = new Pawn(Field.Color.B);
            blackPawns.add((Pawn)tmp_fig);
            tmp1.setFigure(tmp_fig);
            tmp1.get().setCoords(i,b.getSize()-1);
        }
    }
    
    public void setChange(Figure figure) {
    	if (figure instanceof Bishop) {
    		if (figure.getColor() == Color.B) {
    			blackBishops.add((Bishop)figure);
    		}
    		if (figure.getColor() == Color.W) {
    			whiteBishops.add((Bishop)figure);
    		}
    	}
    	if (figure instanceof Knight) {
    		if (figure.getColor() == Color.B) {
    			blackKnights.add((Knight)figure);
    		}
    		if (figure.getColor() == Color.W) {
    			whiteKnights.add((Knight)figure);
    		}
    	}
    	if (figure instanceof Pawn) {
    		if (figure.getColor() == Color.B) {
    			blackPawns.add((Pawn)figure);
    		}
    		if (figure.getColor() == Color.W) {
    			whitePawns.add((Pawn)figure);
    		}
    	}
    	if (figure instanceof Queen) {
    		if (figure.getColor() == Color.B) {
    			blackQueens.add((Queen)figure);
    		}
    		if (figure.getColor() == Color.W) {
    			whiteQueens.add((Queen)figure);
    		}
    	}
    	if (figure instanceof Rook) {
    		if (figure.getColor() == Color.B) {
    			blackRooks.add((Rook)figure);
    		}
    		if (figure.getColor() == Color.W) {
    			whiteRooks.add((Rook)figure);
    		}
    	}
    	this.toChange=figure;
    }

    public void removeStepsFromIndex(int index) {
    	int tmp = moves.size()-1;
    	for (int i = tmp; i> index;i-- ) {
    		moves.remove(i);
    	}
    }
    
    @Override
    public boolean move(Figure figure, Field field) {
    	boolean returnCode;
    	if (figure instanceof Pawn) {
    		returnCode = move((Pawn)figure, field);
    	}
    	else if(figure instanceof Rook) {
    		returnCode = move((Rook)figure, field);
    	}
    	else if(figure instanceof Knight){
    		returnCode = move((Knight)figure, field);
    	}
    	else if(figure instanceof Bishop){
    		returnCode = move((Bishop)figure, field);
    	}
    	else if(figure instanceof Queen){
    		returnCode = move((Queen)figure, field);
    	}
    	else {
    		returnCode = move((King)figure, field);
    	}
    	
    	if (returnCode) {
    		actualMove++;
    		if(figure.getColor() == Color.B) {
    			if (!blackInCheck()) {
    				undo();
    				return false;
    			}
    		}
    		else {
    			if (!whiteInCheck()) {
    				undo();
    				return false;
    			}
    		}
    	}
    	
    	return returnCode;
    	
    }
    
    private boolean move(Pawn figure, Field field) {
    	if ((field.getCol() == figure.getCol()) && (field.getRow() == figure.getRow())) {
    		return false;
    	}
    	if ((figure.c == Color.B && figure.getRow() < field.getRow()) || (figure.c == Color.W && figure.getRow() > field.getRow())) {
    		return false;
    	}
    	Field fieldWithFigure = board.getField(figure.getCol(), figure.getRow());
    	HashMap <Direction,Field> neighbors =  board.getField(figure.getCol(), figure.getRow()).getFieldsMap();
    	Set<Direction> allowedDirections = new HashSet<Direction>(figure.c == Color.B ? blackAllowedDirections:whiteAllowedDirections);
    	allowedDirections.retainAll(neighbors.keySet());
    	
    	// looking for closest neighbors
    	for (Direction key: allowedDirections) {
    		if (!figure.wasMoved() && (key == Direction.D || key==Direction.U)) {
    			if(neighbors.get(key).getFieldsMap().get(key) != null) {
    				if(neighbors.get(key).getFieldsMap().get(key).equals(field)) {
    					if (!neighbors.get(key).isEmpty()) {
    						return false;
    					}
    					figure.moved();
    	    			int fromX = fieldWithFigure.getCol();
    	    			int fromY = fieldWithFigure.getRow();
    	    			int toX = field.getCol();
    	    			int toY = field.getRow();
    	    			Figure deleted = null;
    	    			removeStepsFromIndex(actualMove);
    	    			moves.add(new HistoryMove(deleted,null,figure,board.getField(fromX, fromY),board.getField(toX, toY),board));
    	    			if (toChange !=null && (toY==1 || toY==8)) {
    	    				moves.get(moves.size()-1).changed=toChange;
    	    				field.setFigure(toChange);
    	    				toChange.setCoords(field.getCol(), field.getRow());
    	    				figure.setCoords(0, 0);
    	    				toChange = null;
    	    			}
    	    			else {
    	    				field.setFigure(figure);
    	    				figure.setCoords(field.getCol(), field.getRow());
    	    			}
    	        		fieldWithFigure.setFigure(null);
    	        		
    	        		figure.moved();
    					return true;
    				}
    			}
    		}
    		if (neighbors.get(key).equals(field)) {
	    		if (key == Direction.U || key == Direction.D) {
	    			if (!neighbors.get(key).isEmpty()) {
						return false;
					}
	    			int fromX = fieldWithFigure.getCol();
	    			int fromY = fieldWithFigure.getRow();
	    			int toX = field.getCol();
	    			int toY = field.getRow();
	    			Figure deleted = null;
	    			removeStepsFromIndex(actualMove);
	    			moves.add(new HistoryMove(deleted,null,figure,board.getField(fromX, fromY),board.getField(toX, toY),board));
	    			if (toChange !=null && (toY==1 || toY==8)) {
	    				moves.get(moves.size()-1).changed=toChange;
	    				field.setFigure(toChange);
	    				toChange.setCoords(field.getCol(), field.getRow());
	    				figure.setCoords(0, 0);
	    				toChange = null;
	    			}
	    			else {
	    				field.setFigure(figure);
	    				figure.setCoords(field.getCol(), field.getRow());
	    			}
	        		figure.moved();
					return true;
	    		}
	    		if (key == Direction.RU || key == Direction.LU || key == Direction.RD || key == Direction.LD) {
	    			if (neighbors.get(key).isEmpty()) {
	    				return false;
	    			}
	    			if (figure.c == neighbors.get(key).get().getColor()) {
	    				return false;
	    			}
	    			int fromX = fieldWithFigure.getCol();
	    			int fromY = fieldWithFigure.getRow();
	    			int toX = field.getCol();
	    			int toY = field.getRow();
	    			Figure deleted = neighbors.get(key).get();
	    			int deletedFromX = neighbors.get(key).get().getCol();
	    			int deletedFromY = neighbors.get(key).get().getRow();
	    			removeStepsFromIndex(actualMove);
	    			moves.add(new HistoryMove(deleted,board.getField(deletedFromX, deletedFromY),figure,board.getField(fromX, fromY),board.getField(toX, toY),board));
	    			// setting figure to new field
	    			if (toChange !=null && (toY==1 || toY==8)) {
	    				moves.get(moves.size()-1).changed=toChange;
	    				field.setFigure(toChange);
	    				toChange.setCoords(field.getCol(), field.getRow());
	    				figure.setCoords(0, 0);
	    				toChange = null;
	    			}
	    			else {
	    				field.setFigure(figure);
	    				figure.setCoords(field.getCol(), field.getRow());
	    			}
	        		// removing figure
	        		deleted.setCoords(0, 0);
	        		figure.moved();
	        		
					return true;
	    			
	    		}
    		}
    	}
    		
        return false;
    }

    private boolean move(Rook figure, Field field) {
    	
    	if ((figure.getCol() == field.getCol()) && (figure.getRow() == field.getRow())) {
    		// moving figure to the same place
    		return false;
    	}
    	
    	if ((figure.getCol() != field.getCol()) && (figure.getRow() != field.getRow())) {
    		// moving figure out of the allowed directions
    		return false;
    	}
    	
    	Set<Field> reachable = reachableFields(figure);
    	
    	if (!reachable.contains(field)) {
    		return false;
    	}
    	else {
    		if(field.isEmpty()) {
    			int fromX = figure.getCol();
    			int fromY = figure.getRow();
    			int toX = field.getCol();
    			int toY = field.getRow();
    			Figure deleted = null;
    			removeStepsFromIndex(actualMove);
    			moves.add(new HistoryMove(deleted,null,figure,board.getField(fromX, fromY),board.getField(toX, toY),board));
    			// setting figure to new field
				field.setFigure(figure);
				board.getField(figure.getCol(), figure.getRow()).setFigure(null);
				// setting new coordinates to the figure
        		figure.setCoords(field.getCol(), field.getRow());
				return true;
    		}
    		else {
    			int fromX = figure.getCol();
    			int fromY = figure.getRow();
    			int toX = field.getCol();
    			int toY = field.getRow();
    			Figure deleted = field.get();
    			removeStepsFromIndex(actualMove);
    			moves.add(new HistoryMove(deleted,board.getField(deleted.getCol(), deleted.getRow()),figure,board.getField(fromX, fromY),board.getField(toX, toY),board));
    			// setting figure to new field
				field.setFigure(figure);
				board.getField(figure.getCol(), figure.getRow()).setFigure(null);
				// setting new coordinates to the figure
        		figure.setCoords(field.getCol(), field.getRow());
        		// removing figure
        		deleted.setCoords(0, 0);
        		//board.getField(col, row).setFigure(null);
				return true;
    		}
    	}
    	
    }
    
    public boolean move ( Knight figure, Field field) {
    	if (figure.getRow() == field.getRow()) {
    		return false;
    	}
    		
    	if (figure.getCol() == field.getCol()) {
    		return false;   		
    	}
    	
    	int rozdielriadkov = Math.abs(figure.getRow() - field.getRow());
    	int rozdielstlpcov = Math.abs(figure.getCol() - field.getCol());
    	
    	if (!((rozdielriadkov == 2 && rozdielstlpcov == 1) || (rozdielriadkov == 1 && rozdielstlpcov == 2))) {
    		return false; 
    	}
    		
    	if (!field.isEmpty()) {
    		if (field.get() instanceof King) {
    			return false;
    		}
    		
    		if (figure.getColor() == field.get().getColor()) {
    			return false;
    		}
    		
			int fromX = figure.getCol();
			int fromY = figure.getRow();
			int toX = field.getCol();
			int toY = field.getRow();
			Figure deleted = field.get();
			removeStepsFromIndex(actualMove);
			moves.add(new HistoryMove(deleted,field,figure,board.getField(fromX, fromY),board.getField(toX, toY),board));
			// setting figure to new field
			field.setFigure(figure);
			board.getField(figure.getCol(), figure.getRow()).setFigure(null);
			// setting new coordinates to the figure
    		figure.setCoords(field.getCol(), field.getRow());
    		// removing figure
    		deleted.setCoords(0, 0);
			return true;
    	}
    	else {
    		int fromX = figure.getCol();
			int fromY = figure.getRow();
			int toX = field.getCol();
			int toY = field.getRow();
			Figure deleted = field.get();
			removeStepsFromIndex(actualMove);
			moves.add(new HistoryMove(deleted,null,figure,board.getField(fromX, fromY),board.getField(toX, toY),board));
			// setting figure to new field
			field.setFigure(figure);
			board.getField(figure.getCol(), figure.getRow()).setFigure(null);
			// setting new coordinates to the figure
    		figure.setCoords(field.getCol(), field.getRow());
			return true;
    	}    	
    }
    
    
    public boolean move ( Bishop figure, Field field) {
    	
    	int rozdielriadkov = Math.abs(figure.getRow() - field.getRow());
    	int rozdielstlpcov = Math.abs(figure.getCol() - field.getCol());
    	
    	if (!(rozdielriadkov == rozdielstlpcov)) {		//!nejde po diagonale
    		return false; 
    	}
    	
    	if (figure.getRow() == field.getRow() && figure.getCol() == field.getCol()) {	//rovnaka pozicia
    		return false;
    	}
    		
    	Direction smerpohybu = null;
    	
    	if (figure.getCol() > field.getCol()) {				//vlavo
    		if (figure.getRow() > field.getRow()) {			//dole
    			smerpohybu = Direction.LD;
    		}else {
    			smerpohybu = Direction.LU;					//hore
    		}
    		
    	}else {											//vpravo
    		if (figure.getRow() > field.getRow()) {			//dole
    			smerpohybu = Direction.RD;
    		}else {
    			smerpohybu = Direction.RU;				//hore
    		}
    	}
    		
    	Field pozicia = board.getField(figure.getCol(), figure.getRow());	//policko na ktorom je figurka
    	
    	while (pozicia.getField(smerpohybu) != null) {								//pohyb z pozicie figurky na cielovu poziciu
    		pozicia = pozicia.getField(smerpohybu);
    		if (!pozicia.equals(field)) {
    			if (!pozicia.isEmpty()) {
    				return false;											//pocas pohybu na cielovu poziciu bola v ceste ina figurka
    			}
    		}else {															//dostala som sa na cielovu poziciu
    			break;
    		}
    	}
    	
	    if (!field.isEmpty()) {
	    	
    		if (field.get() instanceof King) {
    			return false;
    		}
	    	
	    	if (figure.getColor() == field.get().getColor()) {		//ak na ciel. pozicii niekto, nesmie byt rovnakej farby
				return false;
			}
			
			int fromX = figure.getCol();
			int fromY = figure.getRow();
			int toX = field.getCol();
			int toY = field.getRow();
			Figure deleted = field.get();
			removeStepsFromIndex(actualMove);
			moves.add(new HistoryMove(deleted,field,figure,board.getField(fromX, fromY),board.getField(toX, toY),board));
			// setting figure to new field
			field.setFigure(figure);
			board.getField(figure.getCol(), figure.getRow()).setFigure(null);
			// setting new coordinates to the figure
			figure.setCoords(field.getCol(), field.getRow());
			// removing figure
			deleted.setCoords(0, 0);
			return true;
		}
		else {
			int fromX = figure.getCol();
			int fromY = figure.getRow();
			int toX = field.getCol();
			int toY = field.getRow();
			Figure deleted = field.get();
			removeStepsFromIndex(actualMove);
			moves.add(new HistoryMove(deleted,null,figure,board.getField(fromX, fromY),board.getField(toX, toY),board));
			// setting figure to new field
			field.setFigure(figure);
			board.getField(figure.getCol(), figure.getRow()).setFigure(null);
			// setting new coordinates to the figure
			figure.setCoords(field.getCol(), field.getRow());
			return true;
		}
    	
 	
    }
    
    
    public boolean move ( Queen figure, Field field) {
    	
    	int rozdielriadkov = Math.abs(figure.getRow() - field.getRow());
    	int rozdielstlpcov = Math.abs(figure.getCol() - field.getCol());
    	
    	if (!((rozdielriadkov == rozdielstlpcov) || (field.getRow() == figure.getRow() || field.getCol() == figure.getCol()))) {		
    		//dama ide zlym smerom 
    		return false;
    	}
    	
    	Direction smerpohybu = null;
    	
    	if(rozdielriadkov == rozdielstlpcov) {			//hybeme sa po diagonale
    		      	
        	if (figure.getCol() > field.getCol()) {				//vlavo
        		if (figure.getRow() > field.getRow()) {			//dole
        			smerpohybu = Direction.LD;
        		}else {
        			smerpohybu = Direction.LU;					//hore
        		}
        		
        	}else {											//vpravo
        		if (figure.getRow() > field.getRow()) {			//dole
        			smerpohybu = Direction.RD;
        		}else {
        			smerpohybu = Direction.RU;				//hore
        		}
        	}	
    	}
    	else {												//hybeme sa vodorovne/horizontalne
    		if (figure.getCol() == field.getCol()) {		//hore/dole?
    			if (figure.getRow() > field.getRow()) {
    				smerpohybu = Direction.D;
    			}else {
    				smerpohybu = Direction.U;
    			}
    			
    		}else {											  			
    			if (figure.getCol() > field.getCol()) {		//vpravo/vlavo?
    				smerpohybu = Direction.L;
    			}else {
    				smerpohybu = Direction.R;
    			}    			
    		}  		
    	}
    	
    	Field pozicia = board.getField(figure.getCol(), figure.getRow());	//policko na ktorom je figurka
    	while (pozicia.getField(smerpohybu) != null) {								//pohyb z pozicie figurky na cielovu poziciu
    		pozicia = pozicia.getField(smerpohybu);
    		if (!pozicia.equals(field)) {
    			if (!pozicia.isEmpty()) {
    				return false;											//pocas pohybu na cielovu poziciu bola v ceste ina figurka
    			}
    		}else {															//dostala som sa na cielovu poziciu
    			break;
    		}
    	}
    	
	    if (!field.isEmpty()) {
	    	
    		if (field.get() instanceof King) {
    			return false;
    		}
	    	
	    	if (figure.getColor() == field.get().getColor()) {		//ak na ciel. pozicii niekto, nesmie byt rovnakej farby
				return false;
			}
			
			int fromX = figure.getCol();
			int fromY = figure.getRow();
			int toX = field.getCol();
			int toY = field.getRow();
			Figure deleted = field.get();
			removeStepsFromIndex(actualMove);
			moves.add(new HistoryMove(deleted,field,figure,board.getField(fromX, fromY),board.getField(toX, toY),board));
			// setting figure to new field
			field.setFigure(figure);
			board.getField(figure.getCol(), figure.getRow()).setFigure(null);
			// setting new coordinates to the figure
			figure.setCoords(field.getCol(), field.getRow());
			// removing figure
			deleted.setCoords(0, 0);
			return true;
		}
		else {
			int fromX = figure.getCol();
			int fromY = figure.getRow();
			int toX = field.getCol();
			int toY = field.getRow();
			Figure deleted = field.get();
			removeStepsFromIndex(actualMove);
			moves.add(new HistoryMove(deleted,null,figure,board.getField(fromX, fromY),board.getField(toX, toY),board));
			// setting figure to new field
			field.setFigure(figure);
			board.getField(figure.getCol(), figure.getRow()).setFigure(null);
			// setting new coordinates to the figure
			figure.setCoords(field.getCol(), field.getRow());
			return true;
		}	   
    }
    
    
    
    
    public boolean move ( King figure, Field field) {
    	
       	int rozdielriadkov = Math.abs(figure.getRow() - field.getRow());
    	int rozdielstlpcov = Math.abs(figure.getCol() - field.getCol());
    	
    	if ((rozdielriadkov < 2 && rozdielstlpcov < 2)) {
    		
			Field pozicia = board.getField(figure.getCol(), figure.getRow());
			for (Direction dir : Direction.values()){
				if(field.getField(dir)!=null) { // ma suseda v danom smere
					if(!field.getField(dir).equals(pozicia)) { // sused v danom smere nie je policko na ktorom stoji kral
						if(!field.getField(dir).isEmpty()) {   // policko je obsadene
							if(field.getField(dir).get().getColor()!=figure.getColor()) { // policko je obsadene figurkou inej farby
								if(Direction.diagonalDirections().contains(dir)) {
    								if(field.getField(dir).get() instanceof Pawn) { // je to pesiak, hrozi sach
    									return false;
    								}
    								if(field.getField(dir).get() instanceof Bishop) { // je to strelec, hrozi sach
    									return false;
    								}
    								if(field.getField(dir).get() instanceof Queen) { // je to dama, hrozi sach
    									return false;
    								}
    								if(field.getField(dir).get() instanceof King) { // je to kral, hrozi sach
    									return false;
    								}
								}
								else {
									if(field.getField(dir).get() instanceof Queen) { // je to strelec, hrozi sach
    									return false;
    								}
									if(field.getField(dir).get() instanceof Rook) { // je to strelec, hrozi sach
    									return false;
    								}
									if(field.getField(dir).get() instanceof King) { // je to strelec, hrozi sach
    									return false;
    								}
								}
							}
						}
					}
				}
			}
			for (Direction dir: Direction.values()) {
				Field pole = null;
				if(field.getField(dir)== null) {
					continue;
				}
				if(field.getField(dir).equals(board.getField(figure.getCol(), figure.getRow()))) {
					pole = field.getField(dir); // zabranime aby sme prehladavali pole na ktorom stoji kral
				}
				else {
					pole = field; // normalny pohyb
				}
				while(pole.getField(dir)!=null) { // v danom smere hladame susedne polia
					pole = pole.getField(dir);
					if (pole.get()!=null) { // na policku niekto je
						if (pole.get().getColor()!=figure.getColor()) { // ma inu farbu, je to ohrozenie
							// ak je figurka strelec alebo dama, moze nas ohrozit
							if (Direction.diagonalDirections().contains(dir)) {
    							if((field.getField(dir).get() instanceof Bishop) || (field.getField(dir).get() instanceof Queen)) { 
    								// je to strelec alebo dama hrozi sach
									return false;
								}
    							else {
    								// je to ina figurka, nehrozi ohrozenie
    								break;
    							}
							}
							else {
								if((field.getField(dir).get() instanceof Queen)||(field.getField(dir).get() instanceof Rook)) { // je to dama, hrozi sach
									return false;
								}
								else {
									break;
								}
							}
						}
						else { // nasli sme postacivku rovnakej farby, nie je ohrozenie
							break;
						}
					}
				}
			}
    		
    		if(field.isEmpty()) {
				int fromX = figure.getCol();
				int fromY = figure.getRow();
				int toX = field.getCol();
				int toY = field.getRow();
				Figure deleted = field.get();
				removeStepsFromIndex(actualMove);
				moves.add(new HistoryMove(deleted,null,figure,board.getField(fromX, fromY),board.getField(toX, toY),board));
				// setting figure to new field
				field.setFigure(figure);
				board.getField(figure.getCol(), figure.getRow()).setFigure(null);
				// setting new coordinates to the figure
				figure.setCoords(field.getCol(), field.getRow());
				return true;
			}else {
				int fromX = figure.getCol();
				int fromY = figure.getRow();
				int toX = field.getCol();
				int toY = field.getRow();
				Figure deleted = field.get();
				removeStepsFromIndex(actualMove);
				moves.add(new HistoryMove(deleted,field,figure,board.getField(fromX, fromY),board.getField(toX, toY),board));
				// setting figure to new field
				field.setFigure(figure);
				board.getField(figure.getCol(), figure.getRow()).setFigure(null);
				// setting new coordinates to the figure
				figure.setCoords(field.getCol(), field.getRow());
				// removing figure
				deleted.setCoords(0, 0);
				return true;
			}
    	}
    	
    	
    	
    	return false;
    }
    
    private int dryMove(Pawn figure, Field field) {
    	if ((field.getCol() == figure.getCol()) && (field.getRow() == figure.getRow())) {
    		return 0;
    	}
    	if ((figure.getColor() == Color.B && figure.getRow() < field.getRow()) || (figure.getColor() == Color.W && figure.getRow() > field.getRow())) {
    		return 0;
    	}
    	Field fieldWithFigure = board.getField(figure.getCol(), figure.getRow());
    	HashMap <Direction,Field> neighbors =  board.getField(figure.getCol(), figure.getRow()).getFieldsMap();
    	Set<Direction> allowedDirections = new HashSet<Direction>(figure.c == Color.B ? blackAllowedDirections:whiteAllowedDirections);
    	allowedDirections.retainAll(neighbors.keySet());
    	
    	// looking for closest neighbors
    	for (Direction key: allowedDirections) {
    		if (!figure.wasMoved() && (key == Direction.D || key==Direction.U)) {
    			if(neighbors.get(key).getFieldsMap().get(key) != null) {
    				if(neighbors.get(key).getFieldsMap().get(key).equals(field)) {
    					if (!neighbors.get(key).isEmpty()) {
    						return 0;
    					}
    					return 1;
    				}
    			}
    		}
    		if (neighbors.get(key).equals(field)) {
	    		if (key == Direction.U || key == Direction.D) {
	    			if (!neighbors.get(key).isEmpty()) {
						return 0;
					}
	    			
					return 1;
	    		}
	    		if (key == Direction.RU || key == Direction.LU || key == Direction.RD || key == Direction.LD) {
	    			if (neighbors.get(key).isEmpty()) {
	    				return 0;
	    			}
	    			if (figure.getColor() == neighbors.get(key).get().getColor()) {
	    				return 0;
	    			}
	    			
					return 2;
	    			
	    		}
    		}
    	}
    		
        return 0;
    }

    private int dryMove(Rook figure, Field field) {
    	
    	if ((figure.getCol() == field.getCol()) && (figure.getRow() == field.getRow())) {
    		// moving figure to the same place
    		return 0;
    	}
    	
    	if ((figure.getCol() != field.getCol()) && (figure.getRow() != field.getRow())) {
    		// moving figure out of the allowed directions
    		return 0;
    	}
    	if (figure.getCol() == field.getCol()) {
    		if (figure.getRow() < field.getRow()) {
    			// UP
    			if (!field.isEmpty()) {
    				if (field.get().getColor() == figure.getColor())
    					return 0;
    			}
    			for (int i = figure.getRow()+1; i<field.getRow(); i++) {
    				if (!this.board.getField(figure.getCol(), i).isEmpty()) {
    					return 0;
    				}
    			}
    			if (field.isEmpty()) {
    				return 1;
    			}
    			else {
    				return 2;
    			}
    		}
    		else {
    			if (!field.isEmpty()) {
    				if (field.get().getColor() == figure.getColor())
    					return 0;
    			}
    			for (int i = figure.getRow()-1; i>field.getRow(); i--) {
    				if (!this.board.getField(figure.getCol(), i).isEmpty()) {
    					return 0;
    				}
    			}
    			if (field.isEmpty()) {
    				return 1;
    			}
    			else {
    				return 2;
    			}
    		}
    	}
    	else {
    		if (figure.getCol() < field.getCol()) {
    			// RIGHT
    			if (!field.isEmpty()) {
    				if (field.get().getColor() == figure.getColor())
    					return 0;
    			}
    			for (int i = figure.getCol()+1; i<field.getCol(); i++) {
    				if (!this.board.getField(i, figure.getRow()).isEmpty()) {
    					return 0;
    				}
    			}
    			if (field.isEmpty()) {
    				return 1;
    			}
    			else {
    				return 2;
    			}
    		}
    		else {
    			// LEFT
    			if (!field.isEmpty()) {
    				if (field.get().getColor() == figure.getColor())
    					return 0;
    			}
    			for (int i = figure.getCol()-1; i>field.getCol(); i--) {
    				if (!this.board.getField(i, figure.getRow()).isEmpty()) {
    					return 0;
    				}
    			}
    			if (field.isEmpty()) {
    				return 1;
    			}
    			else {
    				return 2;
    			}
    		}
    	}
    	
    }
    
    public int dryMove ( Knight figure, Field field) {
    	if (figure.getRow() == field.getRow()) {
    		return 0;
    	}
    		
    	if (figure.getCol() == field.getCol()) {
    		return 0;   		
    	}
    	
    	int rozdielriadkov = Math.abs(figure.getRow() - field.getRow());
    	int rozdielstlpcov = Math.abs(figure.getCol() - field.getCol());
    	
    	if (!((rozdielriadkov == 2 && rozdielstlpcov == 1) || (rozdielriadkov == 1 && rozdielstlpcov == 2))) {
    		return 0; 
    	}
    		
    	if (!field.isEmpty()) {
    		if (field.get() instanceof King) {
    			return 0;
    		}
    		
    		if (figure.getColor() == field.get().getColor()) {
    			return 0;
    		}
			return 2;
    	}
    	else {
    		
			return 1;
    	}    	
    }
    
    
    public int dryMove ( Bishop figure, Field field) {
    	
    	int rozdielriadkov = Math.abs(figure.getRow() - field.getRow());
    	int rozdielstlpcov = Math.abs(figure.getCol() - field.getCol());
    	
    	if (!(rozdielriadkov == rozdielstlpcov)) {		//!nejde po diagonale
    		return 0; 
    	}
    	
    	if (figure.getRow() == field.getRow() && figure.getCol() == field.getCol()) {	//rovnaka pozicia
    		return 0;
    	}
    		
    	Direction smerpohybu = null;
    	
    	if (figure.getCol() > field.getCol()) {				//vlavo
    		if (figure.getRow() > field.getRow()) {			//dole
    			smerpohybu = Direction.LD;
    		}else {
    			smerpohybu = Direction.LU;					//hore
    		}
    		
    	}else {											//vpravo
    		if (figure.getRow() > field.getRow()) {			//dole
    			smerpohybu = Direction.RD;
    		}else {
    			smerpohybu = Direction.RU;				//hore
    		}
    	}
    		
    	Field pozicia = board.getField(figure.getCol(), figure.getRow());	//policko na ktorom je figurka
    	
    	while (pozicia.getField(smerpohybu) != null) {								//pohyb z pozicie figurky na cielovu poziciu
    		pozicia = pozicia.getField(smerpohybu);
    		if (!pozicia.equals(field)) {
    			if (!pozicia.isEmpty()) {
    				return 0;											//pocas pohybu na cielovu poziciu bola v ceste ina figurka
    			}
    		}else {															//dostala som sa na cielovu poziciu
    			break;
    		}
    	}
    	
	    if (!field.isEmpty()) {
	    	
    		if (field.get() instanceof King) {
    			return 0;
    		}
	    	
	    	if (figure.getColor() == field.get().getColor()) {		//ak na ciel. pozicii niekto, nesmie byt rovnakej farby
				return 0;
			}
			
			
			return 2;
		}
		else {
			
			return 1;
		}
    	
 	
    }
    
    
    public int dryMove ( Queen figure, Field field) {
    	
    	int rozdielriadkov = Math.abs(figure.getRow() - field.getRow());
    	int rozdielstlpcov = Math.abs(figure.getCol() - field.getCol());
    	
    	if (!((rozdielriadkov == rozdielstlpcov) || (field.getRow() == figure.getRow() || field.getCol() == figure.getCol()))) {		
    		//dama ide zlym smerom 
    		return 0;
    	}
    	
    	Direction smerpohybu = null;
    	
    	if(rozdielriadkov == rozdielstlpcov) {			//hybeme sa po diagonale
    		      	
        	if (figure.getCol() > field.getCol()) {				//vlavo
        		if (figure.getRow() > field.getRow()) {			//dole
        			smerpohybu = Direction.LD;
        		}else {
        			smerpohybu = Direction.LU;					//hore
        		}
        		
        	}else {											//vpravo
        		if (figure.getRow() > field.getRow()) {			//dole
        			smerpohybu = Direction.RD;
        		}else {
        			smerpohybu = Direction.RU;				//hore
        		}
        	}	
    	}
    	else {												//hybeme sa vodorovne/horizontalne
    		if (figure.getCol() == field.getCol()) {		//hore/dole?
    			if (figure.getRow() > field.getRow()) {
    				smerpohybu = Direction.D;
    			}else {
    				smerpohybu = Direction.U;
    			}
    			
    		}else {											  			
    			if (figure.getCol() > field.getCol()) {		//vpravo/vlavo?
    				smerpohybu = Direction.L;
    			}else {
    				smerpohybu = Direction.R;
    			}    			
    		}  		
    	}
    	
    	Field pozicia = board.getField(figure.getCol(), figure.getRow());	//policko na ktorom je figurka
    	while (pozicia.getField(smerpohybu) != null) {								//pohyb z pozicie figurky na cielovu poziciu
    		pozicia = pozicia.getField(smerpohybu);
    		if (!pozicia.equals(field)) {
    			if (!pozicia.isEmpty()) {
    				return 0;											//pocas pohybu na cielovu poziciu bola v ceste ina figurka
    			}
    		}else {															//dostala som sa na cielovu poziciu
    			break;
    		}
    	}
    	
	    if (!field.isEmpty()) {
	    	
    		if (field.get() instanceof King) {
    			return 0;
    		}
	    	
	    	if (figure.getColor() == field.get().getColor()) {		//ak na ciel. pozicii niekto, nesmie byt rovnakej farby
				return 0;
			}
			
			
			return 2;
		}
		else {
			
			return 1;
		}	   
    }
    
    
    
    
    public int dryMove ( King figure, Field field) {
    	
       	int rozdielriadkov = Math.abs(figure.getRow() - field.getRow());
    	int rozdielstlpcov = Math.abs(figure.getCol() - field.getCol());
    	
    	if ((rozdielriadkov < 2 && rozdielstlpcov < 2)) {
    		
			Field pozicia = board.getField(figure.getCol(), figure.getRow());
			for (Direction dir : Direction.values()){
				if(field.getField(dir)!=null) { // ma suseda v danom smere
					if(!field.getField(dir).equals(pozicia)) { // sused v danom smere nie je policko na ktorom stoji kral
						if(!field.getField(dir).isEmpty()) {   // policko je obsadene
							if(field.getField(dir).get().getColor()!=figure.getColor()) { // policko je obsadene figurkou inej farby
								if(Direction.diagonalDirections().contains(dir)) {
    								if(field.getField(dir).get() instanceof Pawn) { // je to pesiak, hrozi sach
    									return 0;
    								}
    								if(field.getField(dir).get() instanceof Bishop) { // je to strelec, hrozi sach
    									return 0;
    								}
    								if(field.getField(dir).get() instanceof Queen) { // je to dama, hrozi sach
    									return 0;
    								}
    								if(field.getField(dir).get() instanceof King) { // je to kral, hrozi sach
    									return 0;
    								}
								}
								else {
									if(field.getField(dir).get() instanceof Queen) { // je to strelec, hrozi sach
    									return 0;
    								}
									if(field.getField(dir).get() instanceof Rook) { // je to strelec, hrozi sach
    									return 0;
    								}
									if(field.getField(dir).get() instanceof King) { // je to strelec, hrozi sach
    									return 0;
    								}
								}
							}
						}
					}
				}
			}
			for (Direction dir: Direction.values()) {
				Field pole = null;
				if(field.getField(dir).equals(board.getField(figure.getCol(), figure.getRow()))) {
					pole = field.getField(dir); // zabranime aby sme prehladavali pole na ktorom stoji kral
				}
				else {
					pole = field; // normalny pohyb
				}
				while(pole.getField(dir)!=null) { // v danom smere hladame susedne polia
					pole = pole.getField(dir);
					if (pole.get()!=null) { // na policku niekto je
						if (pole.get().getColor()!=figure.getColor()) { // ma inu farbu, je to ohrozenie
							// ak je figurka strelec alebo dama, moze nas ohrozit
							if (Direction.diagonalDirections().contains(dir)) {
    							if((field.getField(dir).get() instanceof Bishop) || (field.getField(dir).get() instanceof Queen)) { 
    								// je to strelec alebo dama hrozi sach
									return 0;
								}
    							else {
    								// je to ina figurka, nehrozi ohrozenie
    								break;
    							}
							}
							else {
								if((field.getField(dir).get() instanceof Queen)||(field.getField(dir).get() instanceof Rook)) { // je to dama, hrozi sach
									return 0;
								}
								else {
									break;
								}
							}
						}
						else { // nasli sme postacivku rovnakej farby, nie je ohrozenie
							break;
						}
					}
				}
			}
    		
    		if(field.isEmpty()) {
				
				return 1; // nikoho nevyhodi
			}else {
				
				return 2; // niekoho vyhodi
			}
    	}
    	
    	
    	
    	return 0;
    }   
   
    public int dryMove(Figure figure, Field field) {
    	if (figure instanceof Pawn) {
    		return this.dryMove((Pawn)figure, field);
    	}
    	else if(figure instanceof Rook) {
    		return this.dryMove((Rook)figure, field);
    	}
    	else if(figure instanceof Knight){
    		return this.dryMove((Knight)figure, field);
    	}
    	else if(figure instanceof Bishop){
    		return this.dryMove((Bishop)figure, field);
    	}
    	else if(figure instanceof Queen){
    		return this.dryMove((Queen)figure, field);
    	}
    	else {
    		return this.dryMove((King)figure, field);
    	}
    	
    	
    }
    
    @Override
    public boolean undo() {
    	if (moves.size() == 0) {
    		return false;
    	}
    	if (actualMove == -1) {
    		return false;
    	}
    	HistoryMove lastMove = moves.get(actualMove);
    	lastMove.movedFromField.setFigure(lastMove.moved);
    	lastMove.moved.setCoords(lastMove.movedFromField.getCol(), lastMove.movedFromField.getRow());
    	lastMove.movedToField.setFigure(lastMove.deleted);
    	if(lastMove.changed!=null) {
    		lastMove.changed.setCoords(0, 0);
    	}
    	if(lastMove.deleted != null) {
    		lastMove.deleted.setCoords(lastMove.deletedFromField.getCol(), lastMove.deletedFromField.getRow());
    		lastMove.deletedFromField.setFigure(lastMove.deleted);
    	}
    	if (lastMove.moved instanceof Pawn) {
    		if(lastMove.moved.getColor() == Color.B) {
    			if (lastMove.moved.getRow() == 7)
    				((Pawn)lastMove.moved).movedBack();
    		}
    		else {
    			if (lastMove.moved.getRow() == 2)
    				((Pawn)lastMove.moved).movedBack();
    		}
    	
    	}
    	lastMove.moved.setCoords(lastMove.movedFromField.getCol(), lastMove.movedFromField.getRow());
    	for (int i = moves.size()-1; i >= actualMove; i--) {
    		moves.remove(i);
    	}
    	actualMove--;
    	return true;
    }
    public boolean dryUndo() {
    	if (moves.size() == 0) {
    		return false;
    	}
    	if (actualMove == -1) {
    		return false;
    	}
    	HistoryMove lastMove = moves.get(actualMove);
    	lastMove.movedFromField.setFigure(lastMove.moved);
    	lastMove.moved.setCoords(lastMove.movedFromField.getCol(), lastMove.movedFromField.getRow());
    	lastMove.movedToField.setFigure(lastMove.deleted);
    	if(lastMove.changed!=null) {
    		lastMove.changed.setCoords(0, 0);
    	}
    	if(lastMove.deleted != null) {
    		lastMove.deleted.setCoords(lastMove.deletedFromField.getCol(), lastMove.deletedFromField.getRow());
    		lastMove.deletedFromField.setFigure(lastMove.deleted);
    	}
    	if (lastMove.moved instanceof Pawn) {
    		if(lastMove.moved.getColor() == Color.B) {
    			if (lastMove.moved.getRow() == 7)
    				((Pawn)lastMove.moved).movedBack();
    		}
    		else {
    			if (lastMove.moved.getRow() == 2)
    				((Pawn)lastMove.moved).movedBack();
    		}
    	
    	}
    	lastMove.moved.setCoords(lastMove.movedFromField.getCol(), lastMove.movedFromField.getRow());
    	actualMove--;
    	return true;
    }
    
    @Override
    public boolean redo() {
    	if (actualMove+1 == moves.size()) {
    		return false;
    	}
    	if(moves.size() == 0) {
    		return false;
    	}
    	HistoryMove lastMove = moves.get(actualMove+1);
    	if (lastMove.deleted != null) {
    		lastMove.deleted.setCoords(0, 0);
    		}
    	lastMove.movedToField.setFigure(lastMove.moved);
    	lastMove.movedFromField.setFigure(null);
    	lastMove.moved.setCoords(lastMove.movedToField.getCol(), lastMove.movedToField.getRow());
    	if (lastMove.changed!=null) {
    		lastMove.moved.setCoords(0, 0);
    		lastMove.movedToField.setFigure(lastMove.changed);
    		lastMove.changed.setCoords(lastMove.movedToField.getCol(), lastMove.movedToField.getRow());
    	}
    	if(lastMove.moved instanceof Pawn) {
    		((Pawn)lastMove.moved).moved();
    	}
    	actualMove++;
    	return true;
    }
 
    
	public boolean loadgame(File filename) throws IOException, WrongMoveException {
		
		List<String> lines = Files.readAllLines(Paths.get(filename.getAbsolutePath()));
		boolean lastline = false;
		for (String line: lines) {

			if (lastline) {
				throw new WrongMoveException("Zle zapisany tah");
			}
			
			String arrayString[] = line.split("\\s+");

			String moveW="";
			String moveB="";
			try {
			moveW = arrayString[1];
			
			}
			catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				// undo vsetko a skonci chybou
				int tmp = moves.size();
				for (int i = 0; i<tmp;i++) {
					undo();
				}
				moves.clear();
				return false;
			}
			
			try {
				moveB = arrayString[2];
			}
			catch (ArrayIndexOutOfBoundsException e) {
				// do nothing
			}
			
			switch (moveW.charAt(0)) {
            case 'K':  moveW = moveW.substring(1, moveW.length());
            		   if (moveW.matches("([a-h])([1-8])(x?)([a-h])([1-8])([D,V,S,J]?)([+,#]?)")) {	//dlha notacia
            			   Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
            			   Matcher matcher = pattern.matcher(moveW);
            			   matcher.find();
            			   MatchResult results = matcher.toMatchResult();
            			               			   
            			   int from_column = results.group(1).charAt(0) - 96;
            			   int from_row = results.group(2).charAt(0) - 48;
            			   
            			   int to_column = results.group(4).charAt(0) - 96;
            			   int to_row = results.group(5).charAt(0) - 48;
            			   
            			   String take_x = results.group(3);
            			   if(board.getField(from_column, from_row).get() != null) {
            				   if (!(board.getField(from_column, from_row).get() instanceof King))
            					   throw new WrongMoveException("Snazite sa pohnut KRALOM\n"+"na jeho mieste sa nachadza "+board.getField(from_column, from_row).get().getClass().toString()+"\ntah:"+line);
            			   }
            			   else {
            				   throw new WrongMoveException("Takyto tah sa neda vykonat\n"+line);
            			   }
            			   int returnCode = dryMove((King)whiteKing, board.getField(to_column, to_row));
            			   if (returnCode == 1 && take_x == null) {
           		   			// mozeme ho posunut, tak ho posunieme aj realne
           		   			move(whiteKing,board.getField(to_column, to_row));
           		   			}
            			   else if (returnCode == 2 && take_x != null) {
            				   move(whiteKing,board.getField(to_column, to_row));
            			   } 			   
            			   else
            			   {
            				   throw new WrongMoveException("Takyto tah sa neda vykonat\n"+line);
            			   }
            			   
            		   	}
            		   else if (moveW.matches("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$")) { //kratka notacia
            			   Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
            			   Matcher matcher = pattern.matcher(moveW);
            			   matcher.find();
            			   MatchResult results = matcher.toMatchResult();
            			   int help_column = -1;
            			   int help_row = -1;
            			   int to_column = results.group(4).charAt(0)-96;
            			   int to_row = results.group(5).charAt(0)-48;
            			   String take_x = null;
            			   String check_mate = null;
            		   		if (results.group(1) != null ) {			//skratena notacia obsahuje stlpec z ktoreho sa hybeme
            		   			help_column = results.group(1).charAt(0)-96;
            		   		}
            		   		if (results.group(2) != null) {				//skratena notacia obsahuje riadok z ktoreho sa hybeme
            		   			help_row = results.group(2).charAt(0)-48;
            		   		}
            		   		if (results.group(3) != null) {				//v skratenej notacii sa berie figurka
            		   			take_x = results.group(3);
            		   		}
            		   		if (results.group(5) != null) {				//skratena notacia obsahuje sach alebo mat
            		   			check_mate = results.group(6);
            		   		}
            		   		    		   		
            		   		Field to_field = board.getField(to_column, to_row);
            		   		// kontorla ci stlpec ktory nam ma napovedat sedi ak je zadany
            		   		if (help_column != -1) {
            		   			if (whiteKing.getCol()!=help_column) {
            		   				throw new WrongMoveException("Kral nie je na zapisanom stlpci"+line+"\n"+"zapisane: "+Integer.toString(help_row)+"\nskutocnost: "+Integer.toString(whiteKing.getCol()));
            		   			}
            		   		}
            		   		// kontorla ci riadok ktory nam ma napovedat sedi ak je zadany
            		   		if (help_row != -1) {
            		   			if (whiteKing.getRow()!=help_row) {
            		   				throw new WrongMoveException("Kral nie je na zapisanom riadku"+line+"\n"+"zapisane: "+Integer.toString(help_row)+"\nskutocnost: "+Integer.toString(whiteKing.getRow()));
            		   			}
            		   		}
            		   		// skusime ho nezavazne posunut
            		   		int returnCode = dryMove((King)whiteKing, to_field);
            		   		if (returnCode == 1 && take_x == null) {
            		   			// mozeme ho posunut, tak ho posunieme aj realne
            		   			move(whiteKing,to_field);
            		   		}
            		   		else if(returnCode == 2 && take_x != null){
            		   			move(whiteKing,to_field);
            		   		}
            		   		else {
            		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
            		   		}
            		   		
            		   		
            		   	}
            		    else {
            		   		throw new WrongMoveException("Tento riadok nevyhovuje formalnemu zapisu\n"+line);
            		   	}
                     break;
            case 'D':  	moveW = moveW.substring(1, moveW.length());
            			if (moveW.matches("([a-h])([1-8])(x?)([a-h])([1-8])([D,V,S,J]?)([+,#]?)")) {
            				Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
            				Matcher matcher = pattern.matcher(moveW);
            				matcher.find();
            				MatchResult results = matcher.toMatchResult();
            				int from_column = results.group(1).charAt(0) - 96;
            				int from_row = results.group(2).charAt(0) - 48;
            				int to_column = results.group(4).charAt(0) - 96;
            				int to_row = results.group(5).charAt(0) - 48;
							String take_x = results.group(3);
							String check_mate = results.group(6);
							if(board.getField(from_column, from_row).get() != null) {
								if (!(board.getField(from_column, from_row).get() instanceof Queen))
									return false;
							}
							else {
								return false;
							}
							int returnCode = dryMove(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
							if ((returnCode == 1 && take_x.isEmpty()) || (returnCode == 2 && !take_x.isEmpty())) {
          		   			// mozeme ho posunut, tak ho posunieme aj realne
          		   				move(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
          		   				if (!check_mate.isEmpty()) {
          		   					if (check_mate == "+") {
          		   						if (!whiteInCheck()) {
          		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
          		   						}
          		   					}
          		   					else {
          		   						if (!whiteInMate()) {
          		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
          		   						}
          		   					}
          		   				}
	          		   		}
	          		   		else {
	          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
	          		   		}
			 			   	
			 		   	}
			            else if (moveW.matches("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$")) { //kratka notacia
			            	Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
	            			   Matcher matcher = pattern.matcher(moveW);
	            			   matcher.find();
	            			   MatchResult results = matcher.toMatchResult();
	            			   int help_column = -1;
	            			   int help_row = -1;
	            			   int to_column = results.group(4).charAt(0)-96;
	            			   int to_row = results.group(5).charAt(0)-48;
	            			   String take_x = null;
	            			   String check_mate = null;
	            		   		if (results.group(1) != null ) {			//skratena notacia obsahuje stlpec z ktoreho sa hybeme
	            		   			help_column = results.group(1).charAt(0)-96;
	            		   		}
	            		   		if (results.group(2) != null) {				//skratena notacia obsahuje riadok z ktoreho sa hybeme
	            		   			help_row = results.group(2).charAt(0)-48;
	            		   		}
	            		   		if (results.group(3) != null) {				//v skratenej notacii sa berie figurka
	            		   			take_x = results.group(3);
	            		   		}
	            		   		if (results.group(5) != null) {				//skratena notacia obsahuje sach alebo mat
	            		   			check_mate = results.group(6);
	            		   		}
	            		   		    		   		
	            		   		Field to_field = board.getField(to_column, to_row);
	            		   		Queen toBeMoved = null;
	            		   		for (Queen queen: whiteQueens) {
	            		   			if (help_column != -1) {
	            		   				if (queen.getCol() != help_column) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			if (help_row != -1) {
	            		   				if (queen.getRow() != help_row) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			
	            		   			Set<Field> reachable = reachableFields(queen);
	            		   			if (reachable.contains(to_field)) { // figurka dosiahne na pole kam sa ma presunut
	            		   				if (help_column != -1 && help_row != -1) { // v notacii nam dali riadok aj stlpec
		            		   				if (queen.getCol() == help_column && queen.getRow() == help_row) {
		            		   					toBeMoved=queen;
		            		   					break;
		            		   			}
		            		   			if (help_row != -1) { // v notacii nam dal si riadok
		            		   				if (queen.getRow() != help_row) { // ak dama nema rovnaky riadok ako bol zadany, pokracujem dalej
		            		   					continue;
		            		   				}
		            		   				else { // dama ma rovnaky riadok
		            		   					if (toBeMoved != null) { // jednu damu uz sme ale nasli, ide o nejednoznacnost notacie
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else { // este sme ziadnu nenasli, ulozime si ju
		            		   						toBeMoved = queen;
		            		   					}
		            		   				}
		            		   			}
		            		   			if (help_column != -1) { // analogicky ako ked nam zadaju riadok
		            		   				if (queen.getCol() != help_column) {
		            		   					continue;
		            		   				}
		            		   				else {
		            		   					if (toBeMoved != null) {
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else {
		            		   						toBeMoved = queen;
		            		   					}
		            		   				}
		            		   			}
	            		   			}
	            		   			
	            		   		}
	            		   	}
	            		   		int returnCode = dryMove(toBeMoved,to_field);
								if ((returnCode == 1 && take_x == null) || (returnCode == 2 && take_x != null)) {
	          		   			// mozeme ho posunut, tak ho posunieme aj realne
	          		   				move(toBeMoved,to_field);
	          		   				if (check_mate != null) {
	          		   					if (check_mate == "+") {
	          		   						if (!blackInCheck()) {
	          		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
	          		   						}
	          		   					}
	          		   					else {
	          		   						if (!blackInMate()) {
	          		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
	          		   						}
	          		   					}
	          		   				}
		          		   		}
		          		   		else {
		          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
		          		   		}
	            		 }
			            else {
			            	throw new WrongMoveException("Zle zapisany tah "+ line);
			            }
                     break;
            case 'V':  	moveW = moveW.substring(1, moveW.length());
			            if (moveW.matches("[a-h]\\dx?[a-h]\\d[D,V,S,J]?[+,#]?")) {
			            	Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
            				Matcher matcher = pattern.matcher(moveW);
            				matcher.find();
            				MatchResult results = matcher.toMatchResult();
            				int from_column = results.group(1).charAt(0) - 96;
            				int from_row = results.group(2).charAt(0) - 48;
            				int to_column = results.group(4).charAt(0) - 96;
            				int to_row = results.group(5).charAt(0) - 48;
							String take_x = results.group(3);
							String check_mate = results.group(6);
			 			   if(board.getField(from_column, from_row).get() != null) {
			 				   if (!(board.getField(from_column, from_row).get() instanceof Rook))
			 					   return false;
			 			   }
			 			   else {
			 				   return false;
			 			   }
			 			  int returnCode = dryMove(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
							if ((returnCode == 1 && take_x.isEmpty()) || (returnCode == 2 && !take_x.isEmpty())) {
        		   			// mozeme ho posunut, tak ho posunieme aj realne
        		   				move(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
        		   				if (!check_mate.isEmpty()) {
        		   					if (check_mate == "+") {
        		   						if (!whiteInCheck()) {
        		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
        		   						}
        		   					}
        		   					else {
        		   						if (!whiteInMate()) {
        		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
        		   						}
        		   					}
        		   				}
	          		   		}
	          		   		else {
	          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
	          		   		}
			 		   	}else if (moveW.matches("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$")) { //kratka notacia
			            	Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
	            			   Matcher matcher = pattern.matcher(moveW);
	            			   matcher.find();
	            			   MatchResult results = matcher.toMatchResult();
	            			   int help_column = -1;
	            			   int help_row = -1;
	            			   int to_column = results.group(4).charAt(0)-96;
	            			   int to_row = results.group(5).charAt(0)-48;
	            			   String take_x = null;
	            			   String check_mate = null;
	            		   		if (results.group(1) != null ) {			//skratena notacia obsahuje stlpec z ktoreho sa hybeme
	            		   			help_column = results.group(1).charAt(0)-96;
	            		   		}
	            		   		if (results.group(2) != null) {				//skratena notacia obsahuje riadok z ktoreho sa hybeme
	            		   			help_row = results.group(2).charAt(0)-48;
	            		   		}
	            		   		if (results.group(3) != null) {				//v skratenej notacii sa berie figurka
	            		   			take_x = results.group(3);
	            		   		}
	            		   		if (results.group(5) != null) {				//skratena notacia obsahuje sach alebo mat
	            		   			check_mate = results.group(6);
	            		   		}
	            		   		    		   		
	            		   		Field to_field = board.getField(to_column, to_row);
	            		   		Figure toBeMoved = null;
	            		   		for (Rook rook: whiteRooks) {
	            		   			if (help_column != -1) {
	            		   				if (rook.getCol() != help_column) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			if (help_row != -1) {
	            		   				if (rook.getRow() != help_row) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			
	            		   			Set<Field> reachable = reachableFields(rook);
	            		   			if (reachable.contains(to_field)) { // figurka dosiahne na pole kam sa ma presunut
	            		   				if (help_column != -1 && help_row != -1) { // v notacii nam dali riadok aj stlpec
		            		   				if (rook.getCol() == help_column && rook.getRow() == help_row) {
		            		   					toBeMoved=rook;
		            		   					break;
		            		   			}
		            		   			if (help_row != -1) { // v notacii nam dal si riadok
		            		   				if (rook.getRow() != help_row) { // ak figurka nema rovnaky riadok ako bol zadany, pokracujem dalej
		            		   					continue;
		            		   				}
		            		   				else { // figurka ma rovnaky riadok
		            		   					if (toBeMoved != null) { // jednu figurku uz sme ale nasli, ide o nejednoznacnost notacie
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else { // este sme ziadnu nenasli, ulozime si ju
		            		   						toBeMoved = rook;
		            		   					}
		            		   				}
		            		   			}
		            		   			if (help_column != -1) { // analogicky ako ked nam zadaju riadok
		            		   				if (rook.getCol() != help_column) {
		            		   					continue;
		            		   				}
		            		   				else {
		            		   					if (toBeMoved != null) {
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else {
		            		   						toBeMoved = rook;
		            		   					}
		            		   				}
		            		   			}
	            		   			}
	            		   			
	            		   		}
	            		   	}
	            		   		int returnCode = dryMove(toBeMoved,to_field);
								if ((returnCode == 1 && take_x == null) || (returnCode == 2 && take_x != null)) {
	          		   			// mozeme ho posunut, tak ho posunieme aj realne
	          		   				move(toBeMoved,to_field);
	          		   				if (check_mate != null) {
	          		   					if (check_mate == "+") {
	          		   						if (!blackInCheck()) {
	          		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
	          		   						}
	          		   					}
	          		   					else {
	          		   						if (!blackInMate()) {
	          		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
	          		   						}
	          		   					}
	          		   				}
		          		   		}
		          		   		else {
		          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
		          		   		}
	            		 }
			            else {
			            	throw new WrongMoveException("Zle zapisany tah "+ line);
			            }
                     break;
            case 'S':  	moveW = moveW.substring(1, moveW.length());
			            if (moveW.matches("[a-h]\\dx?[a-h]\\d[D,V,S,J]?[+,#]?")) {
			            	Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
            				Matcher matcher = pattern.matcher(moveW);
            				matcher.find();
            				MatchResult results = matcher.toMatchResult();
            				int from_column = results.group(1).charAt(0) - 96;
            				int from_row = results.group(2).charAt(0) - 48;
            				int to_column = results.group(4).charAt(0) - 96;
            				int to_row = results.group(5).charAt(0) - 48;
							String take_x = results.group(3);
							String check_mate = results.group(6);
				 			   if(board.getField(from_column, from_row).get() != null) {
				 				   if (!(board.getField(from_column, from_row).get() instanceof Bishop))
				 					   return false;
				 			   }
				 			   else {
				 				   return false;
				 			   }
				 			  int returnCode = dryMove(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
								if ((returnCode == 1 && take_x.isEmpty()) || (returnCode == 2 && !take_x.isEmpty())) {
	        		   			// mozeme ho posunut, tak ho posunieme aj realne
	        		   				move(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
	        		   				if (!check_mate.isEmpty()) {
	        		   					if (check_mate == "+") {
	        		   						if (!whiteInCheck()) {
	        		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
	        		   						}
	        		   					}
	        		   					else {
	        		   						if (!whiteInMate()) {
	        		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
	        		   						}
	        		   					}
	        		   				}
		          		   		}
		          		   		else {
		          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
		          		   		}
				 		   	}
			            else if (moveW.matches("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$")) { //kratka notacia
			            	Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
	            			   Matcher matcher = pattern.matcher(moveW);
	            			   matcher.find();
	            			   MatchResult results = matcher.toMatchResult();
	            			   int help_column = -1;
	            			   int help_row = -1;
	            			   int to_column = results.group(4).charAt(0)-96;
	            			   int to_row = results.group(5).charAt(0)-48;
	            			   String take_x = null;
	            			   String check_mate = null;
	            		   		if (results.group(1) != null ) {			//skratena notacia obsahuje stlpec z ktoreho sa hybeme
	            		   			help_column = results.group(1).charAt(0)-96;
	            		   		}
	            		   		if (results.group(2) != null) {				//skratena notacia obsahuje riadok z ktoreho sa hybeme
	            		   			help_row = results.group(2).charAt(0)-48;
	            		   		}
	            		   		if (results.group(3) != null) {				//v skratenej notacii sa berie figurka
	            		   			take_x = results.group(3);
	            		   		}
	            		   		if (results.group(5) != null) {				//skratena notacia obsahuje sach alebo mat
	            		   			check_mate = results.group(6);
	            		   		}
	            		   		    		   		
	            		   		Field to_field = board.getField(to_column, to_row);
	            		   		Figure toBeMoved = null;
	            		   		for (Bishop bishop: whiteBishops) {
	            		   			if (help_column != -1) {
	            		   				if (bishop.getCol() != help_column) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			if (help_row != -1) {
	            		   				if (bishop.getRow() != help_row) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			
	            		   			Set<Field> reachable = reachableFields(bishop);
	            		   			if (reachable.contains(to_field)) { // figurka dosiahne na pole kam sa ma presunut
	            		   				if (help_column != -1 && help_row != -1) { // v notacii nam dali riadok aj stlpec
		            		   				if (bishop.getCol() == help_column && bishop.getRow() == help_row) {
		            		   					toBeMoved=bishop;
		            		   					break;
		            		   			}
		            		   			if (help_row != -1) { // v notacii nam dal si riadok
		            		   				if (bishop.getRow() != help_row) { // ak figurka nema rovnaky riadok ako bol zadany, pokracujem dalej
		            		   					continue;
		            		   				}
		            		   				else { // figurka ma rovnaky riadok
		            		   					if (toBeMoved != null) { // jednu figurku uz sme ale nasli, ide o nejednoznacnost notacie
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else { // este sme ziadnu nenasli, ulozime si ju
		            		   						toBeMoved = bishop;
		            		   					}
		            		   				}
		            		   			}
		            		   			if (help_column != -1) { // analogicky ako ked nam zadaju riadok
		            		   				if (bishop.getCol() != help_column) {
		            		   					continue;
		            		   				}
		            		   				else {
		            		   					if (toBeMoved != null) {
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else {
		            		   						toBeMoved = bishop;
		            		   					}
		            		   				}
		            		   			}
	            		   			}
	            		   			
	            		   		}
	            		   	}
	            		   		int returnCode = dryMove(toBeMoved,to_field);
								if ((returnCode == 1 && take_x == null) || (returnCode == 2 && take_x != null)) {
	          		   			// mozeme ho posunut, tak ho posunieme aj realne
	          		   				move(toBeMoved,to_field);
	          		   				if (check_mate != null) {
	          		   					if (check_mate == "+") {
	          		   						if (!blackInCheck()) {
	          		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
	          		   						}
	          		   					}
	          		   					else {
	          		   						if (!blackInMate()) {
	          		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
	          		   						}
	          		   					}
	          		   				}
		          		   		}
		          		   		else {
		          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
		          		   		}
	            		 }
			            else {
			            	throw new WrongMoveException("Zle zapisany tah "+ line);
			            }
                     break;
            case 'J':  moveW = moveW.substring(1, moveW.length());
			            if (moveW.matches("[a-h]\\dx?[a-h]\\d[D,V,S,J]?[+,#]?")) {
			            	Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
            				Matcher matcher = pattern.matcher(moveW);
            				matcher.find();
            				MatchResult results = matcher.toMatchResult();
            				int from_column = results.group(1).charAt(0) - 96;
            				int from_row = results.group(2).charAt(0) - 48;
            				int to_column = results.group(4).charAt(0) - 96;
            				int to_row = results.group(5).charAt(0) - 48;
							String take_x = results.group(3);
							String check_mate = results.group(6);
				 			   if(board.getField(from_column, from_row).get() != null) {
				 				   if (!(board.getField(from_column, from_row).get() instanceof Knight))
				 					   return false;
				 			   }
				 			   else {
				 				   return false;
				 			   }
				 			  int returnCode = dryMove(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
								if ((returnCode == 1 && take_x.isEmpty()) || (returnCode == 2 && !take_x.isEmpty())) {
	        		   			// mozeme ho posunut, tak ho posunieme aj realne
	        		   				move(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
	        		   				if (!check_mate.isEmpty()) {
	        		   					if (check_mate == "+") {
	        		   						if (!whiteInCheck()) {
	        		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
	        		   						}
	        		   					}
	        		   					else {
	        		   						if (!whiteInMate()) {
	        		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
	        		   						}
	        		   					}
	        		   				}
		          		   		}
		          		   		else {
		          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
		          		   		}
				 		   	}
			            else if (moveW.matches("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$")) { //kratka notacia
			            	Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
	            			   Matcher matcher = pattern.matcher(moveW);
	            			   matcher.find();
	            			   MatchResult results = matcher.toMatchResult();
	            			   int help_column = -1;
	            			   int help_row = -1;
	            			   int to_column = results.group(4).charAt(0)-96;
	            			   int to_row = results.group(5).charAt(0)-48;
	            			   String take_x = null;
	            			   String check_mate = null;
	            		   		if (results.group(1) != null ) {			//skratena notacia obsahuje stlpec z ktoreho sa hybeme
	            		   			help_column = results.group(1).charAt(0)-96;
	            		   		}
	            		   		if (results.group(2) != null) {				//skratena notacia obsahuje riadok z ktoreho sa hybeme
	            		   			help_row = results.group(2).charAt(0)-48;
	            		   		}
	            		   		if (results.group(3) != null) {				//v skratenej notacii sa berie figurka
	            		   			take_x = results.group(3);
	            		   		}
	            		   		if (results.group(5) != null) {				//skratena notacia obsahuje sach alebo mat
	            		   			check_mate = results.group(6);
	            		   		}
	            		   		    		   		
	            		   		Field to_field = board.getField(to_column, to_row);
	            		   		Figure toBeMoved = null;
	            		   		for (Knight knight: whiteKnights) {
	            		   			if (help_column != -1) {
	            		   				if (knight.getCol() != help_column) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			if (help_row != -1) {
	            		   				if (knight.getRow() != help_row) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			
	            		   			Set<Field> reachable = reachableFields(knight);
	            		   			if (reachable.contains(to_field)) { // figurka dosiahne na pole kam sa ma presunut
	            		   				if (help_column != -1 && help_row != -1) { // v notacii nam dali riadok aj stlpec
		            		   				if (knight.getCol() == help_column && knight.getRow() == help_row) {
		            		   					toBeMoved=knight;
		            		   					break;
		            		   			}
		            		   			if (help_row != -1) { // v notacii nam dal si riadok
		            		   				if (knight.getRow() != help_row) { // ak figurka nema rovnaky riadok ako bol zadany, pokracujem dalej
		            		   					continue;
		            		   				}
		            		   				else { // figurka ma rovnaky riadok
		            		   					if (toBeMoved != null) { // jednu figurku uz sme ale nasli, ide o nejednoznacnost notacie
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else { // este sme ziadnu nenasli, ulozime si ju
		            		   						toBeMoved = knight;
		            		   					}
		            		   				}
		            		   			}
		            		   			if (help_column != -1) { // analogicky ako ked nam zadaju riadok
		            		   				if (knight.getCol() != help_column) {
		            		   					continue;
		            		   				}
		            		   				else {
		            		   					if (toBeMoved != null) {
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else {
		            		   						toBeMoved = knight;
		            		   					}
		            		   				}
		            		   			}
	            		   			}
	            		   			
	            		   		}
	            		   	}
	            		   		int returnCode = dryMove(toBeMoved,to_field);
								if ((returnCode == 1 && take_x == null) || (returnCode == 2 && take_x != null)) {
	          		   			// mozeme ho posunut, tak ho posunieme aj realne
	          		   				move(toBeMoved,to_field);
	          		   				if (check_mate != null) {
	          		   					if (check_mate == "+") {
	          		   						if (!blackInCheck()) {
	          		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
	          		   						}
	          		   					}
	          		   					else {
	          		   						if (!blackInMate()) {
	          		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
	          		   						}
	          		   					}
	          		   				}
		          		   		}
		          		   		else {
		          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
		          		   		}
	            		 }
			            else {
			            	throw new WrongMoveException("Zle zapisany tah "+ line);
			            }
                     break;
            default: ;	//pesiak
		            	if (moveW.matches("[a-h]\\dx?[a-h]\\d[D,V,S,J]?[+,#]?")) {
		            		Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
            				Matcher matcher = pattern.matcher(moveW);
            				matcher.find();
            				MatchResult results = matcher.toMatchResult();
            				int from_column = results.group(1).charAt(0) - 96;
            				int from_row = results.group(2).charAt(0) - 48;
            				int to_column = results.group(4).charAt(0) - 96;
            				int to_row = results.group(5).charAt(0) - 48;
							String take_x = results.group(3);
							String check_mate = results.group(6);
			 			   if(board.getField(from_column, from_row).get() != null) {
			 				   if (!(board.getField(from_column, from_row).get() instanceof Pawn))
			 					   return false;
			 			   }
			 			   else {
			 				   return false;
			 			   }
			 			  int returnCode = dryMove(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
							if ((returnCode == 1 && take_x.isEmpty()) || (returnCode == 2 && !take_x.isEmpty())) {
      		   			// mozeme ho posunut, tak ho posunieme aj realne
      		   				move(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
      		   				if (!check_mate.isEmpty()) {
      		   					if (check_mate == "+") {
      		   						if (!whiteInCheck()) {
      		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
      		   						}
      		   					}
      		   					else {
      		   						if (!whiteInMate()) {
      		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
      		   						}
      		   					}
      		   				}
	          		   		}
	          		   		else {
	          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
	          		   		}
			 		   	}
		            	else if (moveW.matches("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$")) { //kratka notacia
			            	Pattern pattern = Pattern.compile("^([a-h])?([1-8])?(x)?([a-h])([1-8])([+,#])?$");
	            			   Matcher matcher = pattern.matcher(moveW);
	            			   matcher.find();
	            			   MatchResult results = matcher.toMatchResult();
	            			   int help_column = -1;
	            			   int help_row = -1;
	            			   int to_column = results.group(4).charAt(0)-96;
	            			   int to_row = results.group(5).charAt(0)-48;
	            			   String take_x = null;
	            			   String check_mate = null;
	            		   		if (results.group(1) != null ) {			//skratena notacia obsahuje stlpec z ktoreho sa hybeme
	            		   			help_column = results.group(1).charAt(0)-96;
	            		   		}
	            		   		if (results.group(2) != null) {				//skratena notacia obsahuje riadok z ktoreho sa hybeme
	            		   			help_row = results.group(2).charAt(0)-48;
	            		   		}
	            		   		if (results.group(3) != null) {				//v skratenej notacii sa berie figurka
	            		   			take_x = results.group(3);
	            		   		}
	            		   		if (results.group(6) != null) {				//skratena notacia obsahuje sach alebo mat
	            		   			check_mate = results.group(6);
	            		   		}
	            		   		    		   		
	            		   		Field to_field = board.getField(to_column, to_row);
	            		   		Figure toBeMoved = null;
	            		   		for (Pawn pawn: whitePawns) {
	            		   			if (help_column != -1) {
	            		   				if (pawn.getCol() != help_column) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			if (help_row != -1) {
	            		   				if (pawn.getRow() != help_row) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			
	            		   			Set<Field> reachable = reachableFields(pawn);
	            		   			if (reachable.contains(to_field)) { 
	            		   				// figurka dosiahne na pole kam sa ma presunut
	            		   				if (help_column != -1 && help_row != -1) { // v notacii nam dali riadok aj stlpec
		            		   				if (pawn.getCol() == help_column && pawn.getRow() == help_row) {
		            		   					toBeMoved=pawn;
		            		   					break;
		            		   				}
		            		   			}
		            		   			if (help_row != -1) { // v notacii nam dal si riadok
		            		   				if (pawn.getRow() != help_row) { // ak figurka nema rovnaky riadok ako bol zadany, pokracujem dalej
		            		   					continue;
		            		   				}
		            		   				else { // figurka ma rovnaky riadok
		            		   					if (toBeMoved != null) { // jednu figurku uz sme ale nasli, ide o nejednoznacnost notacie
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else { // este sme ziadnu nenasli, ulozime si ju
		            		   						toBeMoved = pawn;
		            		   						continue;
		            		   					}
		            		   				}
		            		   			}
		            		   			if (help_column != -1) { // analogicky ako ked nam zadaju riadok
		            		   				if (pawn.getCol() != help_column) {
		            		   					continue;
		            		   				}
		            		   				else {
		            		   					if (toBeMoved != null) {
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else {
		            		   						toBeMoved = pawn;
		            		   						continue;
		            		   					}
		            		   				}
		            		   			}
		            		   			if(toBeMoved != null) {
		            		   				throw new WrongMoveException("Nejednoznacny zapis\n"+line);
	            		   			}
		            		   			else {
		            		   				toBeMoved = pawn;
		            		   			}
	            		   			
	            		   		}
	            		   		
	            		   	}
	            		   		int returnCode = dryMove(toBeMoved,to_field);
								if ((returnCode == 1 && take_x == null) || (returnCode == 2 && take_x != null)) {
	          		   			// mozeme ho posunut, tak ho posunieme aj realne
	          		   				move(toBeMoved,to_field);
	          		   				if (check_mate != null) {
	          		   					if (check_mate == "+") {
	          		   						if (!blackInCheck()) {
	          		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
	          		   						}
	          		   					}
	          		   					else {
	          		   						if (!blackInMate()) {
	          		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
	          		   						}
	          		   					}
	          		   				}
		          		   		}
		          		   		else {
		          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
		          		   		}
	            		 }
			            else {
			            	throw new WrongMoveException("Zle zapisany tah "+ line);
			            }
                     break;
        }
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
			if (moveB.equals("")) {
				lastline = true;
				continue;
			}
			switch (moveB.charAt(0)) {
            case 'K':  moveB = moveB.substring(1, moveB.length());
            		   if (moveB.matches("([a-h])([1-8])(x?)([a-h])([1-8])([D,V,S,J]?)([+,#]?)")) {	//dlha notacia
            			   Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
            			   Matcher matcher = pattern.matcher(moveB);
            			   matcher.find();
            			   MatchResult results = matcher.toMatchResult();
            			               			   
            			   int from_column = results.group(1).charAt(0) - 96;
            			   int from_row = results.group(2).charAt(0) - 48;
            			   
            			   int to_column = results.group(4).charAt(0) - 96;
            			   int to_row = results.group(5).charAt(0) - 48;
            			   
            			   String take_x = results.group(3);
            			   if(board.getField(from_column, from_row).get() != null) {
            				   if (!(board.getField(from_column, from_row).get() instanceof King))
            					   throw new WrongMoveException("Snazite sa pohnut KRALOM\n"+"na jeho mieste sa nachadza "+board.getField(from_column, from_row).get().getClass().toString()+"\ntah:"+line);
            			   }
            			   else {
            				   throw new WrongMoveException("Takyto tah sa neda vykonat\n"+line);
            			   }
            			   int returnCode = dryMove((King)whiteKing, board.getField(to_column, to_row));
            			   if (returnCode == 1 && take_x.isEmpty()) {
           		   			// mozeme ho posunut, tak ho posunieme aj realne
           		   			move(whiteKing,board.getField(to_column, to_row));
           		   			}
            			   else if (returnCode == 2 && !take_x.isEmpty()) {
            				   move(whiteKing,board.getField(to_column, to_row));
            			   } 			   
            			   else
            			   {
            				   throw new WrongMoveException("Takyto tah sa neda vykonat\n"+line);
            			   }
            			   
            		   	}
            		   else if (moveB.matches("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$")) { //kratka notacia
            			   Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
            			   Matcher matcher = pattern.matcher(moveB);
            			   matcher.find();
            			   MatchResult results = matcher.toMatchResult();
            			   int help_column = -1;
            			   int help_row = -1;
            			   int to_column = results.group(4).charAt(0)-96;
            			   int to_row = results.group(5).charAt(0)-48;
            			   String take_x = null;
            			   String check_mate = null;
            		   		if (results.group(1) != null ) {			//skratena notacia obsahuje stlpec z ktoreho sa hybeme
            		   			help_column = results.group(1).charAt(0)-96;
            		   		}
            		   		if (results.group(2) != null) {				//skratena notacia obsahuje riadok z ktoreho sa hybeme
            		   			help_row = results.group(2).charAt(0)-48;
            		   		}
            		   		if (results.group(3) != null) {				//v skratenej notacii sa berie figurka
            		   			take_x = results.group(3);
            		   		}
            		   		if (results.group(5) != null) {				//skratena notacia obsahuje sach alebo mat
            		   			check_mate = results.group(6);
            		   		}
            		   		    		   		
            		   		Field to_field = board.getField(to_column, to_row);
            		   		// kontorla ci stlpec ktory nam ma napovedat sedi ak je zadany
            		   		if (help_column != -1) {
            		   			if (whiteKing.getCol()!=help_column) {
            		   				throw new WrongMoveException("Kral nie je na zapisanom stlpci"+line+"\n"+"zapisane: "+Integer.toString(help_row)+"\nskutocnost: "+Integer.toString(whiteKing.getCol()));
            		   			}
            		   		}
            		   		// kontorla ci riadok ktory nam ma napovedat sedi ak je zadany
            		   		if (help_row != -1) {
            		   			if (whiteKing.getRow()!=help_row) {
            		   				throw new WrongMoveException("Kral nie je na zapisanom riadku"+line+"\n"+"zapisane: "+Integer.toString(help_row)+"\nskutocnost: "+Integer.toString(whiteKing.getRow()));
            		   			}
            		   		}
            		   		// skusime ho nezavazne posunut
            		   		int returnCode = dryMove((King)blackKing, to_field);
            		   		if (returnCode == 1 && take_x == null) {
            		   			// mozeme ho posunut, tak ho posunieme aj realne
            		   			move(blackKing,to_field);
            		   		}
            		   		else if(returnCode == 2 && take_x != null){
            		   			move(blackKing,to_field);
            		   		}
            		   		else {
            		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
            		   		}
            		   		
            		   		
            		   	}
            		    else {
            		   		throw new WrongMoveException("Tento riadok nevyhovuje formalnemu zapisu\n"+line);
            		   	}
                     break;
            case 'D':  	moveB = moveB.substring(1, moveB.length());
            			if (moveB.matches("([a-h])([1-8])(x?)([a-h])([1-8])([D,V,S,J]?)([+,#]?)")) {
            				Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
            				Matcher matcher = pattern.matcher(moveB);
            				matcher.find();
            				MatchResult results = matcher.toMatchResult();
            				int from_column = results.group(1).charAt(0) - 96;
            				int from_row = results.group(2).charAt(0) - 48;
            				int to_column = results.group(4).charAt(0) - 96;
            				int to_row = results.group(5).charAt(0) - 48;
							String take_x = results.group(3);
							String check_mate = results.group(6);
							if(board.getField(from_column, from_row).get() != null) {
								if (!(board.getField(from_column, from_row).get() instanceof Queen))
									return false;
							}
							else {
								return false;
							}
							int returnCode = dryMove(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
							if ((returnCode == 1 && take_x.isEmpty()) || (returnCode == 2 && !take_x.isEmpty())) {
          		   			// mozeme ho posunut, tak ho posunieme aj realne
          		   				move(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
          		   				if (!check_mate.isEmpty()) {
          		   					if (check_mate == "+") {
          		   						if (!whiteInCheck()) {
          		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
          		   						}
          		   					}
          		   					else {
          		   						if (!whiteInMate()) {
          		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
          		   						}
          		   					}
          		   				}
	          		   		}
	          		   		else {
	          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
	          		   		}
			 			   	
			 		   	}
			            else if (moveB.matches("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$")) { //kratka notacia
			            	Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
	            			   Matcher matcher = pattern.matcher(moveB);
	            			   matcher.find();
	            			   MatchResult results = matcher.toMatchResult();
	            			   int help_column = -1;
	            			   int help_row = -1;
	            			   int to_column = results.group(4).charAt(0)-96;
	            			   int to_row = results.group(5).charAt(0)-48;
	            			   String take_x = null;
	            			   String check_mate = null;
	            		   		if (results.group(1) != null ) {			//skratena notacia obsahuje stlpec z ktoreho sa hybeme
	            		   			help_column = results.group(1).charAt(0)-96;
	            		   		}
	            		   		if (results.group(2) != null) {				//skratena notacia obsahuje riadok z ktoreho sa hybeme
	            		   			help_row = results.group(2).charAt(0)-48;
	            		   		}
	            		   		if (results.group(3) != null) {				//v skratenej notacii sa berie figurka
	            		   			take_x = results.group(3);
	            		   		}
	            		   		if (results.group(5) != null) {				//skratena notacia obsahuje sach alebo mat
	            		   			check_mate = results.group(6);
	            		   		}
	            		   		    		   		
	            		   		Field to_field = board.getField(to_column, to_row);
	            		   		Queen toBeMoved = null;
	            		   		for (Queen queen: blackQueens) {
	            		   			if (help_column != -1) {
	            		   				if (queen.getCol() != help_column) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			if (help_row != -1) {
	            		   				if (queen.getRow() != help_row) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			
	            		   			Set<Field> reachable = reachableFields(queen);
	            		   			if (reachable.contains(to_field)) { // figurka dosiahne na pole kam sa ma presunut
	            		   				if (help_column != -1 && help_row != -1) { // v notacii nam dali riadok aj stlpec
		            		   				if (queen.getCol() == help_column && queen.getRow() == help_row) {
		            		   					toBeMoved=queen;
		            		   					break;
		            		   			}
		            		   			if (help_row != -1) { // v notacii nam dal si riadok
		            		   				if (queen.getRow() != help_row) { // ak dama nema rovnaky riadok ako bol zadany, pokracujem dalej
		            		   					continue;
		            		   				}
		            		   				else { // dama ma rovnaky riadok
		            		   					if (toBeMoved != null) { // jednu damu uz sme ale nasli, ide o nejednoznacnost notacie
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else { // este sme ziadnu nenasli, ulozime si ju
		            		   						toBeMoved = queen;
		            		   					}
		            		   				}
		            		   			}
		            		   			if (help_column != -1) { // analogicky ako ked nam zadaju riadok
		            		   				if (queen.getCol() != help_column) {
		            		   					continue;
		            		   				}
		            		   				else {
		            		   					if (toBeMoved != null) {
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else {
		            		   						toBeMoved = queen;
		            		   					}
		            		   				}
		            		   			}
	            		   			}
	            		   			
	            		   		}
	            		   	}
	            		   		int returnCode = dryMove(toBeMoved,to_field);
								if ((returnCode == 1 && take_x == null) || (returnCode == 2 && take_x != null)) {
	          		   			// mozeme ho posunut, tak ho posunieme aj realne
	          		   				move(toBeMoved,to_field);
	          		   				if (check_mate != null) {
	          		   					if (check_mate == "+") {
	          		   						if (!blackInCheck()) {
	          		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
	          		   						}
	          		   					}
	          		   					else {
	          		   						if (!blackInMate()) {
	          		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
	          		   						}
	          		   					}
	          		   				}
		          		   		}
		          		   		else {
		          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
		          		   		}
	            		 }
			            else {
			            	throw new WrongMoveException("Zle zapisany tah "+ line);
			            }
                     break;
            case 'V':  	moveB = moveB.substring(1, moveB.length());
			            if (moveB.matches("[a-h]\\dx?[a-h]\\d[D,V,S,J]?[+,#]?")) {
			            	Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
            				Matcher matcher = pattern.matcher(moveB);
            				matcher.find();
            				MatchResult results = matcher.toMatchResult();
            				int from_column = results.group(1).charAt(0) - 96;
            				int from_row = results.group(2).charAt(0) - 48;
            				int to_column = results.group(4).charAt(0) - 96;
            				int to_row = results.group(5).charAt(0) - 48;
							String take_x = results.group(3);
							String check_mate = results.group(6);
			 			   if(board.getField(from_column, from_row).get() != null) {
			 				   if (!(board.getField(from_column, from_row).get() instanceof Rook))
			 					   return false;
			 			   }
			 			   else {
			 				   return false;
			 			   }
			 			  int returnCode = dryMove(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
							if ((returnCode == 1 && take_x.isEmpty()) || (returnCode == 2 && !take_x.isEmpty())) {
        		   			// mozeme ho posunut, tak ho posunieme aj realne
        		   				move(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
        		   				if (!check_mate.isEmpty()) {
        		   					if (check_mate == "+") {
        		   						if (!whiteInCheck()) {
        		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
        		   						}
        		   					}
        		   					else {
        		   						if (!whiteInMate()) {
        		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
        		   						}
        		   					}
        		   				}
	          		   		}
	          		   		else {
	          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
	          		   		}
			 		   	}else if (moveB.matches("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$")) { //kratka notacia
			            	Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
	            			   Matcher matcher = pattern.matcher(moveB);
	            			   matcher.find();
	            			   MatchResult results = matcher.toMatchResult();
	            			   int help_column = -1;
	            			   int help_row = -1;
	            			   int to_column = results.group(4).charAt(0)-96;
	            			   int to_row = results.group(5).charAt(0)-48;
	            			   String take_x = null;
	            			   String check_mate = null;
	            		   		if (results.group(1) != null ) {			//skratena notacia obsahuje stlpec z ktoreho sa hybeme
	            		   			help_column = results.group(1).charAt(0)-96;
	            		   		}
	            		   		if (results.group(2) != null) {				//skratena notacia obsahuje riadok z ktoreho sa hybeme
	            		   			help_row = results.group(2).charAt(0)-48;
	            		   		}
	            		   		if (results.group(3) != null) {				//v skratenej notacii sa berie figurka
	            		   			take_x = results.group(3);
	            		   		}
	            		   		if (results.group(5) != null) {				//skratena notacia obsahuje sach alebo mat
	            		   			check_mate = results.group(6);
	            		   		}
	            		   		    		   		
	            		   		Field to_field = board.getField(to_column, to_row);
	            		   		Figure toBeMoved = null;
	            		   		for (Rook rook: blackRooks) {
	            		   			if (help_column != -1) {
	            		   				if (rook.getCol() != help_column) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			if (help_row != -1) {
	            		   				if (rook.getRow() != help_row) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			
	            		   			Set<Field> reachable = reachableFields(rook);
	            		   			if (reachable.contains(to_field)) { // figurka dosiahne na pole kam sa ma presunut
	            		   				if (help_column != -1 && help_row != -1) { // v notacii nam dali riadok aj stlpec
		            		   				if (rook.getCol() == help_column && rook.getRow() == help_row) {
		            		   					toBeMoved=rook;
		            		   					break;
		            		   			}
		            		   			if (help_row != -1) { // v notacii nam dal si riadok
		            		   				if (rook.getRow() != help_row) { // ak figurka nema rovnaky riadok ako bol zadany, pokracujem dalej
		            		   					continue;
		            		   				}
		            		   				else { // figurka ma rovnaky riadok
		            		   					if (toBeMoved != null) { // jednu figurku uz sme ale nasli, ide o nejednoznacnost notacie
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else { // este sme ziadnu nenasli, ulozime si ju
		            		   						toBeMoved = rook;
		            		   					}
		            		   				}
		            		   			}
		            		   			if (help_column != -1) { // analogicky ako ked nam zadaju riadok
		            		   				if (rook.getCol() != help_column) {
		            		   					continue;
		            		   				}
		            		   				else {
		            		   					if (toBeMoved != null) {
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else {
		            		   						toBeMoved = rook;
		            		   					}
		            		   				}
		            		   			}
	            		   			}
	            		   			
	            		   		}
	            		   	}
	            		   		int returnCode = dryMove(toBeMoved,to_field);
								if ((returnCode == 1 && take_x == null) || (returnCode == 2 && take_x != null)) {
	          		   			// mozeme ho posunut, tak ho posunieme aj realne
	          		   				move(toBeMoved,to_field);
	          		   				if (check_mate != null) {
	          		   					if (check_mate == "+") {
	          		   						if (!blackInCheck()) {
	          		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
	          		   						}
	          		   					}
	          		   					else {
	          		   						if (!blackInMate()) {
	          		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
	          		   						}
	          		   					}
	          		   				}
		          		   		}
		          		   		else {
		          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
		          		   		}
	            		 }
			            else {
			            	throw new WrongMoveException("Zle zapisany tah "+ line);
			            }
                     break;
            case 'S':  	moveB = moveB.substring(1, moveB.length());
			            if (moveB.matches("[a-h]\\dx?[a-h]\\d[D,V,S,J]?[+,#]?")) {
			            	Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
            				Matcher matcher = pattern.matcher(moveB);
            				matcher.find();
            				MatchResult results = matcher.toMatchResult();
            				int from_column = results.group(1).charAt(0) - 96;
            				int from_row = results.group(2).charAt(0) - 48;
            				int to_column = results.group(4).charAt(0) - 96;
            				int to_row = results.group(5).charAt(0) - 48;
							String take_x = results.group(3);
							String check_mate = results.group(6);
				 			   if(board.getField(from_column, from_row).get() != null) {
				 				   if (!(board.getField(from_column, from_row).get() instanceof Bishop))
				 					   return false;
				 			   }
				 			   else {
				 				   return false;
				 			   }
				 			  int returnCode = dryMove(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
								if ((returnCode == 1 && take_x.isEmpty()) || (returnCode == 2 && !take_x.isEmpty())) {
	        		   			// mozeme ho posunut, tak ho posunieme aj realne
	        		   				move(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
	        		   				if (!check_mate.isEmpty()) {
	        		   					if (check_mate == "+") {
	        		   						if (!whiteInCheck()) {
	        		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
	        		   						}
	        		   					}
	        		   					else {
	        		   						if (!whiteInMate()) {
	        		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
	        		   						}
	        		   					}
	        		   				}
		          		   		}
		          		   		else {
		          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
		          		   		}
				 		   	}
			            else if (moveB.matches("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$")) { //kratka notacia
			            	Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
	            			   Matcher matcher = pattern.matcher(moveB);
	            			   matcher.find();
	            			   MatchResult results = matcher.toMatchResult();
	            			   int help_column = -1;
	            			   int help_row = -1;
	            			   int to_column = results.group(4).charAt(0)-96;
	            			   int to_row = results.group(5).charAt(0)-48;
	            			   String take_x = null;
	            			   String check_mate = null;
	            		   		if (results.group(1) != null ) {			//skratena notacia obsahuje stlpec z ktoreho sa hybeme
	            		   			help_column = results.group(1).charAt(0)-96;
	            		   		}
	            		   		if (results.group(2) != null) {				//skratena notacia obsahuje riadok z ktoreho sa hybeme
	            		   			help_row = results.group(2).charAt(0)-48;
	            		   		}
	            		   		if (results.group(3) != null) {				//v skratenej notacii sa berie figurka
	            		   			take_x = results.group(3);
	            		   		}
	            		   		if (results.group(5) != null) {				//skratena notacia obsahuje sach alebo mat
	            		   			check_mate = results.group(6);
	            		   		}
	            		   		    		   		
	            		   		Field to_field = board.getField(to_column, to_row);
	            		   		Figure toBeMoved = null;
	            		   		for (Bishop bishop: blackBishops) {
	            		   			if (help_column != -1) {
	            		   				if (bishop.getCol() != help_column) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			if (help_row != -1) {
	            		   				if (bishop.getRow() != help_row) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			
	            		   			Set<Field> reachable = reachableFields(bishop);
	            		   			if (reachable.contains(to_field)) { // figurka dosiahne na pole kam sa ma presunut
	            		   				if (help_column != -1 && help_row != -1) { // v notacii nam dali riadok aj stlpec
		            		   				if (bishop.getCol() == help_column && bishop.getRow() == help_row) {
		            		   					toBeMoved=bishop;
		            		   					break;
		            		   			}
		            		   			if (help_row != -1) { // v notacii nam dal si riadok
		            		   				if (bishop.getRow() != help_row) { // ak figurka nema rovnaky riadok ako bol zadany, pokracujem dalej
		            		   					continue;
		            		   				}
		            		   				else { // figurka ma rovnaky riadok
		            		   					if (toBeMoved != null) { // jednu figurku uz sme ale nasli, ide o nejednoznacnost notacie
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else { // este sme ziadnu nenasli, ulozime si ju
		            		   						toBeMoved = bishop;
		            		   					}
		            		   				}
		            		   			}
		            		   			if (help_column != -1) { // analogicky ako ked nam zadaju riadok
		            		   				if (bishop.getCol() != help_column) {
		            		   					continue;
		            		   				}
		            		   				else {
		            		   					if (toBeMoved != null) {
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else {
		            		   						toBeMoved = bishop;
		            		   					}
		            		   				}
		            		   			}
	            		   			}
	            		   			
	            		   		}
	            		   	}
	            		   		int returnCode = dryMove(toBeMoved,to_field);
								if ((returnCode == 1 && take_x == null) || (returnCode == 2 && take_x != null)) {
	          		   			// mozeme ho posunut, tak ho posunieme aj realne
	          		   				move(toBeMoved,to_field);
	          		   				if (check_mate != null) {
	          		   					if (check_mate == "+") {
	          		   						if (!blackInCheck()) {
	          		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
	          		   						}
	          		   					}
	          		   					else {
	          		   						if (!blackInMate()) {
	          		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
	          		   						}
	          		   					}
	          		   				}
		          		   		}
		          		   		else {
		          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
		          		   		}
	            		 }
			            else {
			            	throw new WrongMoveException("Zle zapisany tah "+ line);
			            }
                     break;
            case 'J':  moveB = moveB.substring(1, moveB.length());
			            if (moveB.matches("[a-h]\\dx?[a-h]\\d[D,V,S,J]?[+,#]?")) {
			            	Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
            				Matcher matcher = pattern.matcher(moveB);
            				matcher.find();
            				MatchResult results = matcher.toMatchResult();
            				int from_column = results.group(1).charAt(0) - 96;
            				int from_row = results.group(2).charAt(0) - 48;
            				int to_column = results.group(4).charAt(0) - 96;
            				int to_row = results.group(5).charAt(0) - 48;
							String take_x = results.group(3);
							String check_mate = results.group(6);
				 			   if(board.getField(from_column, from_row).get() != null) {
				 				   if (!(board.getField(from_column, from_row).get() instanceof Knight))
				 					   return false;
				 			   }
				 			   else {
				 				   return false;
				 			   }
				 			  int returnCode = dryMove(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
								if ((returnCode == 1 && take_x.isEmpty()) || (returnCode == 2 && !take_x.isEmpty())) {
	        		   			// mozeme ho posunut, tak ho posunieme aj realne
	        		   				move(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
	        		   				if (!check_mate.isEmpty()) {
	        		   					if (check_mate == "+") {
	        		   						if (!whiteInCheck()) {
	        		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
	        		   						}
	        		   					}
	        		   					else {
	        		   						if (!whiteInMate()) {
	        		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
	        		   						}
	        		   					}
	        		   				}
		          		   		}
		          		   		else {
		          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
		          		   		}
				 		   	}
			            else if (moveB.matches("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$")) { //kratka notacia
			            	Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
	            			   Matcher matcher = pattern.matcher(moveB);
	            			   matcher.find();
	            			   MatchResult results = matcher.toMatchResult();
	            			   int help_column = -1;
	            			   int help_row = -1;
	            			   int to_column = results.group(4).charAt(0)-96;
	            			   int to_row = results.group(5).charAt(0)-48;
	            			   String take_x = null;
	            			   String check_mate = null;
	            		   		if (results.group(1) != null ) {			//skratena notacia obsahuje stlpec z ktoreho sa hybeme
	            		   			help_column = results.group(1).charAt(0)-96;
	            		   		}
	            		   		if (results.group(2) != null) {				//skratena notacia obsahuje riadok z ktoreho sa hybeme
	            		   			help_row = results.group(2).charAt(0)-48;
	            		   		}
	            		   		if (results.group(3) != null) {				//v skratenej notacii sa berie figurka
	            		   			take_x = results.group(3);
	            		   		}
	            		   		if (results.group(5) != null) {				//skratena notacia obsahuje sach alebo mat
	            		   			check_mate = results.group(6);
	            		   		}
	            		   		    		   		
	            		   		Field to_field = board.getField(to_column, to_row);
	            		   		Figure toBeMoved = null;
	            		   		for (Knight knight: whiteKnights) {
	            		   			if (help_column != -1) {
	            		   				if (knight.getCol() != help_column) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			if (help_row != -1) {
	            		   				if (knight.getRow() != help_row) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			
	            		   			Set<Field> reachable = reachableFields(knight);
	            		   			if (reachable.contains(to_field)) { // figurka dosiahne na pole kam sa ma presunut
	            		   				if (help_column != -1 && help_row != -1) { // v notacii nam dali riadok aj stlpec
		            		   				if (knight.getCol() == help_column && knight.getRow() == help_row) {
		            		   					toBeMoved=knight;
		            		   					break;
		            		   			}
		            		   			if (help_row != -1) { // v notacii nam dal si riadok
		            		   				if (knight.getRow() != help_row) { // ak figurka nema rovnaky riadok ako bol zadany, pokracujem dalej
		            		   					continue;
		            		   				}
		            		   				else { // figurka ma rovnaky riadok
		            		   					if (toBeMoved != null) { // jednu figurku uz sme ale nasli, ide o nejednoznacnost notacie
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else { // este sme ziadnu nenasli, ulozime si ju
		            		   						toBeMoved = knight;
		            		   					}
		            		   				}
		            		   			}
		            		   			if (help_column != -1) { // analogicky ako ked nam zadaju riadok
		            		   				if (knight.getCol() != help_column) {
		            		   					continue;
		            		   				}
		            		   				else {
		            		   					if (toBeMoved != null) {
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else {
		            		   						toBeMoved = knight;
		            		   					}
		            		   				}
		            		   			}
	            		   			}
	            		   			
	            		   		}
	            		   	}
	            		   		int returnCode = dryMove(toBeMoved,to_field);
								if ((returnCode == 1 && take_x == null) || (returnCode == 2 && take_x != null)) {
	          		   			// mozeme ho posunut, tak ho posunieme aj realne
	          		   				move(toBeMoved,to_field);
	          		   				if (check_mate != null) {
	          		   					if (check_mate == "+") {
	          		   						if (!blackInCheck()) {
	          		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
	          		   						}
	          		   					}
	          		   					else {
	          		   						if (!blackInMate()) {
	          		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
	          		   						}
	          		   					}
	          		   				}
		          		   		}
		          		   		else {
		          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
		          		   		}
	            		 }
			            else {
			            	throw new WrongMoveException("Zle zapisany tah "+ line);
			            }
                     break;
            default: ;	//pesiak
		            	if (moveB.matches("[a-h]\\dx?[a-h]\\d[D,V,S,J]?[+,#]?")) {
		            		Pattern pattern = Pattern.compile("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$");
            				Matcher matcher = pattern.matcher(moveB);
            				matcher.find();
            				MatchResult results = matcher.toMatchResult();
            				int from_column = results.group(1).charAt(0) - 96;
            				int from_row = results.group(2).charAt(0) - 48;
            				int to_column = results.group(4).charAt(0) - 96;
            				int to_row = results.group(5).charAt(0) - 48;
							String take_x = results.group(3);
							String check_mate = results.group(6);
			 			   if(board.getField(from_column, from_row).get() != null) {
			 				   if (!(board.getField(from_column, from_row).get() instanceof Pawn))
			 					   return false;
			 			   }
			 			   else {
			 				   return false;
			 			   }
			 			  int returnCode = dryMove(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
							if ((returnCode == 1 && take_x.isEmpty()) || (returnCode == 2 && !take_x.isEmpty())) {
      		   			// mozeme ho posunut, tak ho posunieme aj realne
      		   				move(board.getField(from_column, from_row).get(),board.getField(to_column, to_row));
      		   				if (!check_mate.isEmpty()) {
      		   					if (check_mate == "+") {
      		   						if (!whiteInCheck()) {
      		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
      		   						}
      		   					}
      		   					else {
      		   						if (!whiteInMate()) {
      		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
      		   						}
      		   					}
      		   				}
	          		   		}
	          		   		else {
	          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
	          		   		}
			 		   	}
		            	else if (moveB.matches("^([a-h]?)([1-8]?)(x?)([a-h])([1-8])([+,#]?)$")) { //kratka notacia
			            	Pattern pattern = Pattern.compile("^([a-h])?([1-8])?(x)?([a-h])([1-8])([+,#])?$");
	            			   Matcher matcher = pattern.matcher(moveB);
	            			   matcher.find();
	            			   MatchResult results = matcher.toMatchResult();
	            			   String test = results.group(0);
	            			   int help_column = -1;
	            			   int help_row = -1;
	            			   int to_column = results.group(4).charAt(0)-96;
	            			   int to_row = results.group(5).charAt(0)-48;
	            			   String take_x = null;
	            			   String check_mate = null;
	            		   		if (results.group(1) != null ) {			//skratena notacia obsahuje stlpec z ktoreho sa hybeme
	            		   			help_column = results.group(1).charAt(0)-96;
	            		   		}
	            		   		if (results.group(2) != null) {				//skratena notacia obsahuje riadok z ktoreho sa hybeme
	            		   			help_row = results.group(2).charAt(0)-48;
	            		   		}
	            		   		if (results.group(3) != null) {				//v skratenej notacii sa berie figurka
	            		   			take_x = results.group(3);
	            		   		}
	            		   		if (results.group(6) != null) {				//skratena notacia obsahuje sach alebo mat
	            		   			check_mate = results.group(6);
	            		   		}
	            		   		    		   		
	            		   		Field to_field = board.getField(to_column, to_row);
	            		   		Figure toBeMoved = null;
	            		   		for (Pawn pawn: blackPawns) {
	            		   			if (help_column != -1) {
	            		   				if (pawn.getCol() != help_column) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			if (help_row != -1) {
	            		   				if (pawn.getRow() != help_row) {
	            		   					continue;
	            		   				}
	            		   			}
	            		   			
	            		   			Set<Field> reachable = reachableFields(pawn);
	            		   			if (reachable.contains(to_field)) { 
	            		   				// figurka dosiahne na pole kam sa ma presunut
	            		   				if (help_column != -1 && help_row != -1) { // v notacii nam dali riadok aj stlpec
		            		   				if (pawn.getCol() == help_column && pawn.getRow() == help_row) {
		            		   					toBeMoved=pawn;
		            		   					break;
		            		   				}
		            		   			}
		            		   			if (help_row != -1) { // v notacii nam dal si riadok
		            		   				if (pawn.getRow() != help_row) { // ak figurka nema rovnaky riadok ako bol zadany, pokracujem dalej
		            		   					continue;
		            		   				}
		            		   				else { // figurka ma rovnaky riadok
		            		   					if (toBeMoved != null) { // jednu figurku uz sme ale nasli, ide o nejednoznacnost notacie
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else { // este sme ziadnu nenasli, ulozime si ju
		            		   						toBeMoved = pawn;
		            		   						continue;
		            		   					}
		            		   				}
		            		   			}
		            		   			if (help_column != -1) { // analogicky ako ked nam zadaju riadok
		            		   				if (pawn.getCol() != help_column) {
		            		   					continue;
		            		   				}
		            		   				else {
		            		   					if (toBeMoved != null) {
		            		   						throw new WrongMoveException("Mozny pohyb s dvoma figurkami, nejednoznacny zapis\n");
		            		   					}
		            		   					else {
		            		   						toBeMoved = pawn;
		            		   						continue;
		            		   					}
		            		   				}
		            		   			}
		            		   			if(toBeMoved != null) {
		            		   				throw new WrongMoveException("Nejednoznacny zapis\n"+line);
	            		   			}
		            		   			else {
		            		   				toBeMoved = pawn;
		            		   			}
	            		   			
	            		   		}
	            		   		
	            		   	}
	            		   		int returnCode = dryMove(toBeMoved,to_field);
								if ((returnCode == 1 && take_x == null) || (returnCode == 2 && take_x != null)) {
	          		   			// mozeme ho posunut, tak ho posunieme aj realne
	          		   				move(toBeMoved,to_field);
	          		   				if (check_mate != null) {
	          		   					if (check_mate == "+") {
	          		   						if (!blackInCheck()) {
	          		   							throw new WrongMoveException("Pohyb "+line+" nedostane krala do sachu");
	          		   						}
	          		   					}
	          		   					else {
	          		   						if (!blackInMate()) {
	          		   						throw new WrongMoveException("Pohyb "+line+" nedostane krala do matu");
	          		   						}
	          		   					}
	          		   				}
		          		   		}
		          		   		else {
		          		   			throw new WrongMoveException("Takto zapisany tah sa neda vykonat\n"+line);
		          		   		}
	            		 }
			            else {
			            	throw new WrongMoveException("Zle zapisant tah");
			            }
                     break;
        }
			
			
			
		}
		while (actualMove > -1) {
			dryUndo();
		}
		return true;
	}


    public String getHistory(){
    	StringBuilder output = new StringBuilder();
    	for (int i = 0; i<moves.size()-1;i+=2) {
    		
    		output.append((i / 2)+1);
    		output.append(". ");
    		output.append(moves.get(i).toString());
    		output.append(" ");
    		output.append(moves.get(i+1).toString());
    		output.append("\n");
    	}
    	
    	if (moves.size() % 2 > 0) {
    		output.append(moves.size()/2+1);
    		output.append(". ");
    		output.append(moves.get(moves.size()-1).toString());
    	}
    	return output.toString();
	}
	
	private static Set<Direction> leftJoinDirectionSets(Set<Direction> left, Set<Direction> right) {
		Set<Direction> result = new HashSet<Field.Direction>();
		for(Direction dir: left) {
			if (!right.contains(dir)) {
				result.add(dir);
			}
		}
		return result;
	}
	
	private static Set<Field> leftJoinFieldSets(Set<Field> left, Set<Field> right) {
		Set<Field> result = new HashSet<Field>();
		for(Field dir: left) {
			if (!right.contains(dir)) {
				result.add(dir);
			}
		}
		return result;
	}
    
	private Set<Field> reachableFields(Figure figure){
		Set<Field> reachable = new HashSet<Field>();
		if(figure.getCol() < 1 || figure.getCol() >8) {
			return reachable;
		}
		for (Field[] row: board.getFields()) {
			for (Field field: row) {
				if (dryMove(figure,field) > 0) {
					reachable.add(field);
				}
			}
		}
		
		return reachable;
	}
	
	private boolean whiteInCheck() {
		Field whiteKingPosition = board.getField(whiteKing.getCol(), whiteKing.getRow());
		Set<Field> reachable;
		for (Figure figure:blackPawns) {
			reachable = reachableFields(figure);
			if (reachable.contains(whiteKingPosition)) {
				return false;
			}
		}
		for (Figure figure:blackBishops) {
			reachable = reachableFields(figure);
			if (reachable.contains(whiteKingPosition)) {
				return false;
			}
		}
		for (Figure figure:blackKnights) {
			reachable = reachableFields(figure);
			if (reachable.contains(whiteKingPosition)) {
				return false;
			}
		}
		for (Figure figure:blackQueens) {
			reachable = reachableFields(figure);
			if (reachable.contains(whiteKingPosition)) {
				return false;
			}
		}
		for (Figure figure:blackRooks) {
			reachable = reachableFields(figure);
			if (reachable.contains(whiteKingPosition)) {
				return false;
			}
		}
		return true;
	}
	private boolean blackInCheck() {
		
		Field blackKingPosition = board.getField(blackKing.getCol(), blackKing.getRow());
		Set<Field> reachable;
		for (Figure figure:whitePawns) {
			reachable = reachableFields(figure);
			if (reachable.contains(blackKingPosition)) {
				return false;
			}
		}
		for (Figure figure:whiteBishops) {
			reachable = reachableFields(figure);
			if (reachable.contains(blackKingPosition)) {
				return false;
			}
		}
		for (Figure figure:whiteKnights) {
			reachable = reachableFields(figure);
			if (reachable.contains(blackKingPosition)) {
				return false;
			}
		}
		for (Figure figure:whiteQueens) {
			reachable = reachableFields(figure);
			if (reachable.contains(blackKingPosition)) {
				return false;
			}
		}
		for (Figure figure:whiteRooks) {
			reachable = reachableFields(figure);
			if (reachable.contains(blackKingPosition)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean whiteInMate() {
		Field whiteKingPosition = board.getField(whiteKing.getCol(), whiteKing.getRow());
		Set<Field> kingSurroundings = new HashSet<Field>();
		
		for (Direction key: whiteKingPosition.getFieldsMap().keySet()) {
			kingSurroundings.add(whiteKingPosition.getFieldsMap().get(key));
		}
		for (Figure figure:blackPawns) {
			kingSurroundings = leftJoinFieldSets(kingSurroundings, reachableFields(figure));
		}
		for (Figure figure:blackBishops) {
			kingSurroundings = leftJoinFieldSets(kingSurroundings, reachableFields(figure));
		}
		for (Figure figure:blackKnights) {
			kingSurroundings = leftJoinFieldSets(kingSurroundings, reachableFields(figure));
		}
		for (Figure figure:blackQueens) {
			kingSurroundings = leftJoinFieldSets(kingSurroundings, reachableFields(figure));
		}
		for (Figure figure:blackRooks) {
			kingSurroundings = leftJoinFieldSets(kingSurroundings, reachableFields(figure));
		}
		
		return kingSurroundings.size() > 0 ? false : true;
	}
	private boolean blackInMate() {
		Field whiteKingPosition = board.getField(blackKing.getCol(), blackKing.getRow());
		Set<Field> kingSurroundings = new HashSet<Field>();
		
		for (Direction key: whiteKingPosition.getFieldsMap().keySet()) {
			kingSurroundings.add(whiteKingPosition.getFieldsMap().get(key));
		}
		for (Figure figure:whitePawns) {
			kingSurroundings = leftJoinFieldSets(kingSurroundings, reachableFields(figure));
		}
		for (Figure figure:whiteBishops) {
			kingSurroundings = leftJoinFieldSets(kingSurroundings, reachableFields(figure));
		}
		for (Figure figure:whiteKnights) {
			kingSurroundings = leftJoinFieldSets(kingSurroundings, reachableFields(figure));
		}
		for (Figure figure:whiteQueens) {
			kingSurroundings = leftJoinFieldSets(kingSurroundings, reachableFields(figure));
		}
		for (Figure figure:whiteRooks) {
			kingSurroundings = leftJoinFieldSets(kingSurroundings, reachableFields(figure));
		}
		
		return kingSurroundings.size() > 0 ? false : true;
	}
	
}
