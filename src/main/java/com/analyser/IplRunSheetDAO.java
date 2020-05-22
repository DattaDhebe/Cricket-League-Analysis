package com.analyser;

public class IplRunSheetDAO {

    public String player;
    public int match;
    public int inning;
    public double average;
    public int runs;


    public IplRunSheetDAO(IplRunSheetCSV iplMostRunCSV) {
        player = iplMostRunCSV.player;
        match = iplMostRunCSV.match;
        inning = iplMostRunCSV.innings;
        average = iplMostRunCSV. average;
    }

    public IplRunSheetDAO() {
    }
}
