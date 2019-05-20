/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2018.homework2.common;

import ija.ija2018.homework2.common.Field.Color;

/**
 *
 * @author xramos00
 */
public interface Figure {
    
    //prebrane z fora IJA
    /**
     * Vrátí řetězec reprezentující stav figury..
     * Řetězec je vytvořen podle následujícího formátu: "F[C]col:row",.
     * kde F je symbol figury (P - pěšec, V - věž),.
     * C je barva figury (W - bílá, B - černá)
     * a col a row jsou indexy pole (čísla sloupce a řádku), na kterém je figura umístěna.
     * Příklad: "P[W]4:2".
     * Pokud není figura na žádném poli, je tato část prázdná, např. pouze "V[B]".
     * @return Řetězec reprezentující stav figury.
     */
    public String getState();
    public void setCoords(int x,int y);
    public Color getColor();
    public int getCol();
    public int getRow();
    
}
