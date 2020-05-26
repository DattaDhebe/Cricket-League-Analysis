package com.analyser;

public class IplSheetDAO {

    public String player;
    public int battingRuns;
    public int bowlingRuns;
    public int sixes;
    public int fours;
    public int wickets;
    public int fiveWickets;
    public int fourWickets;
    public double battingAverage;
    public double bowlingAverage;
    public double battingStrikeRate;
    public double bowlingStrikeRate;
    public double economy;

    public IplSheetDAO(IplRunSheetCSV iplRunSheetCSV) {
        player = iplRunSheetCSV.player;
        battingAverage = iplRunSheetCSV.average;
        battingRuns = iplRunSheetCSV.runs;
        battingStrikeRate = iplRunSheetCSV.battingStrikeRate;
        sixes = iplRunSheetCSV.sixes;
        fours = iplRunSheetCSV.fours;

    }

    public IplSheetDAO(IplWicketCSV iplWicketCSV) {
        player = iplWicketCSV.player;
        bowlingAverage = iplWicketCSV.average;
        bowlingRuns = iplWicketCSV.runs;
        bowlingStrikeRate = iplWicketCSV.bowlingStrikeRate;
        wickets = iplWicketCSV.wickets;
        economy = iplWicketCSV.economy;
        fiveWickets = iplWicketCSV.fiveWickets;
        fourWickets = iplWicketCSV.fourWickets;

    }

    public IplSheetDAO() {
    }
}
