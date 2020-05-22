package com.analyser;

public class IplRunSheetDAO {

    public String player;
    public int match;
    public int inning;
    public int runs;
    public double average;
    public double strikeRate;


    public IplRunSheetDAO(IplRunSheetCSV iplRunSheetDAO) {
        player = iplRunSheetDAO.player;
        match = iplRunSheetDAO.match;
        inning = iplRunSheetDAO.innings;
        average = iplRunSheetDAO.average;
        runs = iplRunSheetDAO.runs;
        strikeRate = iplRunSheetDAO.strikeRate;
    }

    public IplRunSheetDAO() {
    }
}
