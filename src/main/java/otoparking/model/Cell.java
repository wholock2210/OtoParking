package otoparking.model;

public class Cell {
    private int id;
    private String symbol;

    public Cell(int id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = (symbol == null) ? "" : symbol;
    }
}
