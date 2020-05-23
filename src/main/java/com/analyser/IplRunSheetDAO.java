package com.analyser;

public class IplRunSheetDAO {

    public String player;
    public int match;
    public int inning;
    public int runs;
    public int sixes;
    public int fours;
    public int wickets;
    public double average;
    public double strikeRate;
    public double economy;

    public IplRunSheetDAO(IplRunSheetCSV iplRunSheetCSV) {
        player = iplRunSheetCSV.player;
        match = iplRunSheetCSV.match;
        inning = iplRunSheetCSV.innings;
        average = iplRunSheetCSV.average;
        runs = iplRunSheetCSV.runs;
        strikeRate = iplRunSheetCSV.strikeRate;
        sixes = iplRunSheetCSV.sixes;
        fours = iplRunSheetCSV.fours;

    }

    public IplRunSheetDAO(IplWicketCSV iplWicketCSV) {
        player = iplWicketCSV.player;
        match = iplWicketCSV.match;
        inning = iplWicketCSV.innings;
        average = iplWicketCSV.average;
        runs = iplWicketCSV.runs;
        strikeRate = iplWicketCSV.strikeRate;
        wickets = iplWicketCSV.wickets;
        economy = iplWicketCSV.economy;

    }

    public IplRunSheetDAO() {
    }

}
