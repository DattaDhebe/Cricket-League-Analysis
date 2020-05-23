package com.analyser;

public class IplSheetDAO {

    public String player;
    public int match;
    public int inning;
    public int battingRuns;
    public int bowlingRuns;
    public int sixes;
    public int fours;
    public int wickets;
    public int fiveWickets;
    public int fourWickets;
    public double battingAverage;
    public double bowlingAverage;
    public double strikeRate;
    public double economy;

    public IplSheetDAO(IplRunSheetCSV iplRunSheetCSV) {
        player = iplRunSheetCSV.player;
        match = iplRunSheetCSV.match;
        inning = iplRunSheetCSV.innings;
        battingAverage = iplRunSheetCSV.average;
        battingRuns = iplRunSheetCSV.runs;
        strikeRate = iplRunSheetCSV.strikeRate;
        sixes = iplRunSheetCSV.sixes;
        fours = iplRunSheetCSV.fours;

    }

    public IplSheetDAO(IplWicketCSV iplWicketCSV) {
        player = iplWicketCSV.player;
        match = iplWicketCSV.match;
        inning = iplWicketCSV.innings;
        bowlingAverage = iplWicketCSV.average;
        bowlingRuns = iplWicketCSV.runs;
        strikeRate = iplWicketCSV.strikeRate;
        wickets = iplWicketCSV.wickets;
        economy = iplWicketCSV.economy;
        fiveWickets = iplWicketCSV.fiveWickets;
        fourWickets = iplWicketCSV.fourWickets;

    }

    public IplSheetDAO() {
    }
}
