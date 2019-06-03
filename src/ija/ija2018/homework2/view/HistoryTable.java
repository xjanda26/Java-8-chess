package ija.ija2018.homework2.view;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @Author: Adam Janda <xjanda26@stud.fit.vutbr.cz>
 */


/**
 * Predstavuje jeden riadok v tabuľke výpisov.
 * Trieda obsahuje getteri a setteri pre jednotlivé hodnoty riadku.
 */
public class HistoryTable {
    private SimpleStringProperty turn;
    private SimpleStringProperty historyWhite;
    private SimpleStringProperty historyBlack;

    public HistoryTable (String newTurn, String newHistoryW, String newHistoryB){
        this.turn = new SimpleStringProperty(newTurn);
        this.historyWhite = new SimpleStringProperty(newHistoryW);
        this.historyBlack = new SimpleStringProperty(newHistoryB);
    }

    public void setTurn(String turn) {
        this.turn.set(turn);
    }

    public void setHistoryWhite(String historyWhite) {
        this.historyWhite.set(historyWhite);
    }

    public void setHistoryBlack(String historyBlack) {
        this.historyBlack.set(historyBlack);
    }

    public String getTurn() {
        return turn.get();
    }

    public SimpleStringProperty turnProperty() {
        return turn;
    }

    public String getHistoryWhite() {
        return historyWhite.get();
    }

    public SimpleStringProperty historyWhiteProperty() {
        return historyWhite;
    }

    public String getHistoryBlack() {
        return historyBlack.get();
    }

    public SimpleStringProperty historyBlackProperty() {
        return historyBlack;
    }
}
