package com.analyser;

public class IplRunSheetDAO {

    public String player;
    public int match;
    public int inning;
    public double average;
    public int runs;

    public IplRunSheetDAO(IplRunSheetCSV iplRunSheetDAO) {
        player = iplRunSheetDAO.player;
        match = iplRunSheetDAO.match;
        inning = iplRunSheetDAO.innings;
        average = iplRunSheetDAO.average;
        runs = iplRunSheetDAO.runs;
    }


    public IplRunSheetDAO(IplRunSheetDAO iplRunSheetDAO) {
        player = iplRunSheetDAO.player;
        match = iplRunSheetDAO.match;
        inning = iplRunSheetDAO.inning;
        average = iplRunSheetDAO.average;
        runs = iplRunSheetDAO.runs;
    }

    public IplRunSheetDAO() {
    }
}
