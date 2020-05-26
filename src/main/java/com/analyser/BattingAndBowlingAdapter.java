package com.analyser;

import java.util.HashMap;
import java.util.Map;

public class BattingAndBowlingAdapter extends IplFileAdapter {

    Map<String, IplSheetDAO> battingData = new HashMap<>();
    Map<String, IplSheetDAO> bowlingData = new HashMap<>();

    public Map<String, IplSheetDAO> loadCricketData(CricketAnalyser.Cricket cricket, String... csvFilePath) throws CricketAnalyserException {
        battingData = super.loadCricketData(IplRunSheetCSV.class, csvFilePath[0]);
        bowlingData = super.loadCricketData(IplWicketCSV.class, csvFilePath[1]);

        bowlingData.values().stream()
                            .filter(iplLeague -> battingData.get(iplLeague.player) != null)
                            .forEach(iplLeague -> {
                                battingData.get(iplLeague.player).bowlingAverage = iplLeague.bowlingAverage;
                                battingData.get(iplLeague.player).wickets = iplLeague.wickets;
                            });
        return battingData;
    }
}
