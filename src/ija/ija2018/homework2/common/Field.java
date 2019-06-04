/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2018.homework2.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author xramos00
 */
public interface Field {
    
    public boolean isEmpty();

    public void removeFigure();
    
    public Figure get();
    
    public void setFigure(Figure f);
    
    public int getCol();
    
    public int getRow();
    
    public void addNextField(Direction dir, Field f);
    
    public Field getField(Direction dir);
    
    public HashMap<Direction,Field> getFieldsMap();

    public void setColor(Color color);

    public static enum Direction{
		
		L,LU,U,RU,R,RD,D,LD;
    	
    	public static Set<Direction> crossDirections() {
    		Set<Direction> dirs = new HashSet<Direction>();
    		dirs.add(D);
    		dirs.add(L);
    		dirs.add(R);
    		dirs.add(U);
    		return dirs;
    	}
    	public static Set<Direction> diagonalDirections(){
    		Set<Direction> dirs = new HashSet<Direction>();
    		dirs.add(LU);
    		dirs.add(RU);
    		dirs.add(LD);
    		dirs.add(RD);
    		return dirs;
    	}
    	public static Set<Direction> leftDirections() {
    		Set<Direction> dirs = new HashSet<Direction>();
    		dirs.add(L);
    		dirs.add(LU);
    		dirs.add(LD);
    		return dirs;
    	}
    	public static Set<Direction> rightDirections(){
    		Set<Direction> dirs = new HashSet<Direction>();
    		dirs.add(RU);
    		dirs.add(R);
    		dirs.add(RD);
    		return dirs;
    	}
    	public static Set<Direction> upDirections() {
    		Set<Direction> dirs = new HashSet<Direction>();
    		dirs.add(U);
    		dirs.add(LU);
    		dirs.add(RU);
    		return dirs;
    	}
    	public static Set<Direction> downDirections(){
    		Set<Direction> dirs = new HashSet<Direction>();
    		dirs.add(LD);
    		dirs.add(RD);
    		dirs.add(D);
    		return dirs;
    	}
		
	}
    
    public static enum DiagonalDirections{
    	LU,RU,RD,LD;
    }
    
    public static enum CrossDirection{
		
		L,U,R,D;
		
	}
    public static enum Color{
        W,B;
    }
}
