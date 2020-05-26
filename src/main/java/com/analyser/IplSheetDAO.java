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
        battingAverage = iplRunSheetCSV.battingAverage;
        battingRuns = iplRunSheetCSV.battingRuns;
        battingStrikeRate = iplRunSheetCSV.battingStrikeRate;
        sixes = iplRunSheetCSV.sixes;
        fours = iplRunSheetCSV.fours;

    }

    public IplSheetDAO(IplWicketCSV iplWicketCSV) {
        player = iplWicketCSV.player;
        bowlingAverage = iplWicketCSV.bowlingAverage;
        bowlingRuns = iplWicketCSV.bowlingRuns;
        bowlingStrikeRate = iplWicketCSV.bowlingStrikeRate;
        wickets = iplWicketCSV.wickets;
        economy = iplWicketCSV.economy;
        fiveWickets = iplWicketCSV.fiveWickets;
        fourWickets = iplWicketCSV.fourWickets;

    }

    public IplSheetDAO() {
    }
}
